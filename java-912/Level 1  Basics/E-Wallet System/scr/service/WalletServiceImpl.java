package service;

import model.Account;
import model.Transaction;
import util.OtpSession;
import util.SecurityUtils;
import util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Core business implementation handling system security, transaction limits, and account lifecycle.
 */
public class WalletServiceImpl implements WalletService {
    private final List<Account> accounts;
    private Account loggedInUser;

    public static final double MIN_TRANSACTION = 10.0;
    public static final double MAX_TRANSACTION = 50000.0;
    public static final double DAILY_LIMIT = 100000.0;

    /**
     * Initializes service storage and setups default administrator account.
     */
    public WalletServiceImpl() {
        this.accounts = new ArrayList<>();
        this.loggedInUser = null;

        // Auto-creates default system Administrator
        accounts.add(new Account("IAM", "IAM123", 30, "01000000000", true));
    }

    /**
     * Registers a new account after validating inputs and preventing duplicate records.
     */
    @Override
    public boolean register(String username, String rawPassword, int age, String phone) throws Exception {
        validateRegistrationInputs(username, rawPassword, age, phone);
        checkDuplicateUser(username, phone);

        Account newAccount = new Account(username, rawPassword, age, phone, false);
        accounts.add(newAccount);
        return true;
    }

    /**
     * Authenticates user credentials with account lockout protection on 3 failed attempts.
     */
    @Override
    public boolean login(String username, String rawPassword) throws Exception {
        if (username == null || username.isBlank() || rawPassword == null || rawPassword.isBlank()) {
            throw new Exception("Login inputs cannot be empty.");
        }

        Account account = findAccountByUsername(username);

        if (!account.isActive()) {
            throw new Exception("Access denied! Account is locked or frozen.");
        }

        String inputHash = SecurityUtils.hashPasswordWithSalt(rawPassword, account.getSalt());

        if (!account.getPasswordHash().equals(inputHash)) {
            account.incrementFailedAttempts();
            if (account.getFailedLoginAttempts() >= 3) {
                account.setActive(false);
                account.addTransaction("LOCKOUT", 0, "Account locked after 3 failed login attempts.");
                throw new Exception("🚨 Account locked automatically due to 3 failed login attempts!");
            }
            throw new Exception("Incorrect password. Failed attempts: " + account.getFailedLoginAttempts() + "/3");
        }

        account.resetFailedAttempts();
        account.addTransaction("LOGIN", 0, "Successful user authentication.");
        this.loggedInUser = account;
        return true;
    }

    /**
     * Deposits funds to active user account balance.
     */
    @Override
    public void deposit(double amount) throws Exception {
        checkLoggedIn();
        if (amount <= 0) throw new Exception("Deposit amount must be positive.");

        loggedInUser.setBalance(loggedInUser.getBalance() + amount);
        loggedInUser.addTransaction("DEPOSIT", amount, "Funds deposited to balance.");
    }

    /**
     * Withdraws funds from active user balance ensuring sufficient credit.
     */
    @Override
    public void withdraw(double amount) throws Exception {
        checkLoggedIn();
        if (amount <= 0) throw new Exception("Withdrawal amount must be positive.");
        if (amount > loggedInUser.getBalance()) throw new Exception("Insufficient funds.");

        loggedInUser.setBalance(loggedInUser.getBalance() - amount);
        loggedInUser.addTransaction("WITHDRAW", amount, "Funds withdrawn from balance.");
    }

    /**
     * Creates new OTP session for transaction verification.
     */
    @Override
    public OtpSession generateOtpForTransfer() throws Exception {
        checkLoggedIn();
        return new OtpSession();
    }

    /**
     * Executes atomic peer-to-peer transfer with OTP verification and velocity checks.
     */
    @Override
    public void transfer(String destinationUsername, double amount, int inputOtp, OtpSession otpSession) throws Exception {
        checkLoggedIn();
        validateTransferLimits(destinationUsername, amount, inputOtp, otpSession);

        Account receiver = findAccountByUsername(destinationUsername);
        if (!receiver.isActive()) throw new Exception("Destination account is frozen.");

        long currentTime = System.currentTimeMillis();

        loggedInUser.setBalance(loggedInUser.getBalance() - amount);
        loggedInUser.addDailyTransfer(amount);
        loggedInUser.setLastTransactionTime(currentTime);
        loggedInUser.addTransaction("TRANSFER_OUT", amount, "Transferred to user: " + destinationUsername);

        receiver.setBalance(receiver.getBalance() + amount);
        receiver.addTransaction("TRANSFER_IN", amount, "Received from user: " + loggedInUser.getUsername());
    }

    /**
     * Updates password credentials ensuring complexity and non-matching old password.
     */
    @Override
    public void changePassword(String oldRawPassword, String newRawPassword) throws Exception {
        checkLoggedIn();
        String oldHash = SecurityUtils.hashPasswordWithSalt(oldRawPassword, loggedInUser.getSalt());

        if (!loggedInUser.getPasswordHash().equals(oldHash)) {
            throw new Exception("Old password verification failed.");
        }
        if (!ValidationUtils.isValidPassword(newRawPassword)) {
            throw new Exception("New password fails complexity requirements.");
        }

        String newHash = SecurityUtils.hashPasswordWithSalt(newRawPassword, loggedInUser.getSalt());
        if (oldHash.equals(newHash)) throw new Exception("New password must differ from current password.");

        loggedInUser.setPasswordHash(newHash);
        loggedInUser.addTransaction("PASSWORD_CHANGE", 0, "Updated security credentials.");
    }

    /**
     * Retrieves all accounts registered in database (Admin restricted).
     */
    @Override
    public List<Account> getAllAccounts() throws Exception {
        checkAdmin();
        return accounts;
    }

    /**
     * Changes target account status between active and frozen (Admin restricted).
     */
    @Override
    public void toggleAccountStatus(String username, boolean active) throws Exception {
        checkAdmin();
        Account acc = findAccountByUsername(username);
        acc.setActive(active);
    }

    /**
     * Deletes user account record from system storage.
     */
    @Override
    public void deleteAccount(String username) throws Exception {
        checkLoggedIn();
        if (!loggedInUser.isAdmin() && !loggedInUser.getUsername().equalsIgnoreCase(username)) {
            throw new Exception("Unauthorized action to delete another account.");
        }

        Account accToDelete = findAccountByUsername(username);
        accounts.remove(accToDelete);

        if (loggedInUser.getUsername().equalsIgnoreCase(username)) {
            logout();
        }
    }

    /**
     * Fetches transaction history log entries of logged-in user.
     */
    @Override
    public List<Transaction> getMyTransactionHistory() throws Exception {
        checkLoggedIn();
        return loggedInUser.getTransactionHistory();
    }

    /**
     * Terminates active session context.
     */
    @Override
    public void logout() {
        if (loggedInUser != null) {
            loggedInUser.addTransaction("LOGOUT", 0, "User logged out.");
        }
        this.loggedInUser = null;
    }

    @Override
    public Account getLoggedInUser() { return loggedInUser; }

    // Helper Methods for Clean Code Architecture
    private void validateRegistrationInputs(String username, String password, int age, String phone) throws Exception {
        if (!ValidationUtils.isValidUsername(username)) throw new Exception("Invalid username criteria.");
        if (!ValidationUtils.isValidPassword(password)) throw new Exception("Weak password standard.");
        if (!ValidationUtils.isValidAge(age)) throw new Exception("User must be 18 or older.");
        if (!ValidationUtils.isValidEgyptianPhone(phone)) throw new Exception("Invalid Egyptian phone number.");
    }

    private void checkDuplicateUser(String username, String phone) throws Exception {
        boolean exists = accounts.stream().anyMatch(acc ->
                acc.getUsername().equalsIgnoreCase(username) || acc.getPhoneNumber().equals(phone));
        if (exists) throw new Exception("Username or phone number already exists.");
    }

    private void validateTransferLimits(String destinationUsername, double amount, int inputOtp, OtpSession otpSession) throws Exception {
        if (otpSession == null || !otpSession.validate(inputOtp)) throw new Exception("Invalid or expired OTP.");
        if (destinationUsername.equalsIgnoreCase(loggedInUser.getUsername())) throw new Exception("Cannot transfer funds to self.");
        if (System.currentTimeMillis() - loggedInUser.getLastTransactionTime() < 10000) throw new Exception("Rapid transaction detected! Wait 10s.");
        if (amount < MIN_TRANSACTION || amount > MAX_TRANSACTION) throw new Exception("Amount out of valid transaction bounds.");
        if (loggedInUser.getDailyTransferredAmount() + amount > DAILY_LIMIT) throw new Exception("Daily transfer limit exceeded.");
        if (amount > loggedInUser.getBalance()) throw new Exception("Insufficient balance.");
    }

    private Account findAccountByUsername(String username) throws Exception {
        return accounts.stream()
                .filter(acc -> acc.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new Exception("Account not found: " + username));
    }

    private void checkLoggedIn() throws Exception {
        if (loggedInUser == null) throw new Exception("User authorization required.");
    }

    private void checkAdmin() throws Exception {
        checkLoggedIn();
        if (!loggedInUser.isAdmin()) throw new Exception("Action restricted to Administrators.");
    }
}// صنعت بمشقه✨.............