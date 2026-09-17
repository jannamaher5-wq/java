import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * WalletServiceImpl Class
 * Core Business Logic implementing account policies, fraud detection, and transaction security.
 */
public class WalletServiceImpl implements WalletService {
    private List<Account> accounts;
    private Account loggedInUser;

    public static final double MIN_TRANSACTION = 10.0;
    public static final double MAX_TRANSACTION = 50000.0;
    public static final double DAILY_LIMIT = 100000.0;

    public WalletServiceImpl() {
        this.accounts = new ArrayList<>();
        this.loggedInUser = null;

        // Admin Account Creation
        Account admin = new Account("IAM", "IAM123", 30, "01000000000", true);
        accounts.add(admin);
    }

    @Override
    public boolean register(String username, String rawPassword, int age, String phone) throws Exception {
        if (!ValidationUtils.isValidUsername(username)) {
            throw new Exception("Invalid username! Must start with an uppercase letter and be at least 3 characters long.");
        }
        if (!ValidationUtils.isValidPassword(rawPassword)) {
            throw new Exception("Weak password! Must contain at least one uppercase letter, one digit, and be at least 6 characters.");
        }
        if (!ValidationUtils.isValidAge(age)) {
            throw new Exception("Age constraint violation! User must be 18 years or older.");
        }
        if (!ValidationUtils.isValidEgyptianPhone(phone)) {
            throw new Exception("Invalid phone number! Must be a valid 11-digit Egyptian phone number.");
        }

        boolean exists = accounts.stream()
                .anyMatch(acc -> acc.getUsername().equalsIgnoreCase(username) || acc.getPhoneNumber().equals(phone));

        if (exists) {
            throw new Exception("Registration conflict! Username or phone number already exists.");
        }

        Account newAccount = new Account(username, rawPassword, age, phone, false);
        accounts.add(newAccount);
        return true;
    }

    @Override
    public boolean login(String username, String rawPassword) throws Exception {
        if (username == null || username.trim().isEmpty() || rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new Exception("Login credentials cannot be empty.");
        }

        Optional<Account> userOpt = accounts.stream()
                .filter(acc -> acc.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (userOpt.isEmpty()) {
            throw new Exception("Authentication failed! Username not found.");
        }

        Account account = userOpt.get();

        if (!account.isActive()) {
            throw new Exception("Access denied! Account is locked/frozen due to security policies or Admin action.");
        }

        String inputHash = SecurityUtils.hashPasswordWithSalt(rawPassword, account.getSalt());

        if (!account.getPasswordHash().equals(inputHash)) {
            account.incrementFailedAttempts();

            // Account Lockout Policy
            if (account.getFailedLoginAttempts() >= 3) {
                account.setActive(false);
                throw new Exception("🚨 SECURITY AUDIT LOG: Account locked automatically due to 3 consecutive failed login attempts!");
            }
            throw new Exception("Authentication failed! Incorrect password. Failed attempts: " + account.getFailedLoginAttempts() + "/3");
        }

        account.resetFailedAttempts();
        this.loggedInUser = account;
        return true;
    }

    @Override
    public void deposit(double amount) throws Exception {
        checkLoggedIn();
        if (amount <= 0) {
            throw new Exception("Deposit amount must be greater than 0.0.");
        }
        loggedInUser.setBalance(loggedInUser.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        checkLoggedIn();
        if (amount <= 0) {
            throw new Exception("Withdrawal amount must be greater than 0.0.");
        }
        if (amount > loggedInUser.getBalance()) {
            throw new Exception("Insufficient funds! Transaction exceeds available balance.");
        }
        loggedInUser.setBalance(loggedInUser.getBalance() - amount);
    }

    @Override
    public OtpSession generateOtpForTransfer() throws Exception {
        checkLoggedIn();
        return new OtpSession();
    }

    @Override
    public void transfer(String destinationUsername, double amount, int inputOtp, OtpSession otpSession) throws Exception {
        checkLoggedIn();

        if (otpSession == null || !otpSession.validate(inputOtp)) {
            throw new Exception("Transfer failed! Invalid OTP code.");
        }

        // Velocity Check: Rapid Transaction Detection (10-second window)
        long currentTime = System.currentTimeMillis();
        if (currentTime - loggedInUser.getLastTransactionTime() < 10000) {
            throw new Exception("🚨 SECURITY ALERT: Rapid transaction detected! Please wait 10 seconds between operations.");
        }

        if (destinationUsername.equalsIgnoreCase(loggedInUser.getUsername())) {
            throw new Exception("Invalid transfer operation! Cannot transfer funds to self.");
        }

        if (amount < MIN_TRANSACTION || amount > MAX_TRANSACTION) {
            throw new Exception("Transaction amount out of bounds! Range allowed: " + MIN_TRANSACTION + " to " + MAX_TRANSACTION + " EGP.");
        }

        if (loggedInUser.getDailyTransferredAmount() + amount > DAILY_LIMIT) {
            throw new Exception("Daily transfer limit exceeded! Maximum allowed daily limit: " + DAILY_LIMIT + " EGP.");
        }

        if (amount > loggedInUser.getBalance()) {
            throw new Exception("Insufficient balance to execute transfer.");
        }

        // Fraud Alert (Large Amount Warning)
        if (amount >= loggedInUser.getBalance() * 0.8) {
            System.out.println("⚠️ FRAUD ALERT: High-value transaction detected (Transfer exceeds 80% of current balance).");
        }

        Account receiver = accounts.stream()
                .filter(acc -> acc.getUsername().equalsIgnoreCase(destinationUsername))
                .findFirst()
                .orElseThrow(() -> new Exception("Transfer destination account not found."));

        if (!receiver.isActive()) {
            throw new Exception("Destination account is frozen or inactive.");
        }

        loggedInUser.setBalance(loggedInUser.getBalance() - amount);
        loggedInUser.addDailyTransfer(amount);
        loggedInUser.setLastTransactionTime(currentTime);

        receiver.setBalance(receiver.getBalance() + amount);
    }

    @Override
    public void changePassword(String oldRawPassword, String newRawPassword) throws Exception {
        checkLoggedIn();

        String oldHash = SecurityUtils.hashPasswordWithSalt(oldRawPassword, loggedInUser.getSalt());
        if (!loggedInUser.getPasswordHash().equals(oldHash)) {
            throw new Exception("Verification failed! Old password does not match.");
        }

        if (!ValidationUtils.isValidPassword(newRawPassword)) {
            throw new Exception("New password fails security policy requirements.");
        }

        String newHash = SecurityUtils.hashPasswordWithSalt(newRawPassword, loggedInUser.getSalt());
        if (oldHash.equals(newHash)) {
            throw new Exception("New password must be different from the previous password.");
        }

        loggedInUser.setPasswordHash(newHash);
    }

    @Override
    public List<Account> getAllAccounts() throws Exception {
        checkAdmin();
        return accounts;
    }

    @Override
    public void toggleAccountStatus(String username, boolean active) throws Exception {
        checkAdmin();
        Account acc = accounts.stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new Exception("Target account not found."));
        acc.setActive(active);
    }

    @Override
    public void logout() {
        this.loggedInUser = null;
    }

    @Override
    public Account getLoggedInUser() { return loggedInUser; }

    private void checkLoggedIn() throws Exception {
        if (loggedInUser == null) throw new Exception("Unauthorized access! Please login first.");
    }

    private void checkAdmin() throws Exception {
        checkLoggedIn();
        if (!loggedInUser.isAdmin()) throw new Exception("Access denied! Action restricted to Administrators.");
    }
}
// صنعت بمشقه✨.............