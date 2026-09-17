/**
 * Account Domain Model
 * Stores user data including credentials, salt, balance, status, and security metrics.
 */
public class Account {
    private String username;
    private String passwordHash;
    private String salt;
    private int age;
    private String phoneNumber;
    private double balance;
    private boolean isAdmin;
    private boolean isActive;
    private double dailyTransferredAmount;
    private int failedLoginAttempts;
    private long lastTransactionTime;

    // Constructor
    public Account(String username, String rawPassword, int age, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.salt = SecurityUtils.generateSalt();
        this.passwordHash = SecurityUtils.hashPasswordWithSalt(rawPassword, this.salt);
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 0.0;
        this.isAdmin = isAdmin;
        this.isActive = true;
        this.dailyTransferredAmount = 0.0;
        this.failedLoginAttempts = 0;
        this.lastTransactionTime = 0;
    }

    // Getters and Setters
    public String getUsername() { return username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSalt() { return salt; }
    public int getAge() { return age; }
    public String getPhoneNumber() { return phoneNumber; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public boolean isAdmin() { return isAdmin; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public double getDailyTransferredAmount() { return dailyTransferredAmount; }
    public void addDailyTransfer(double amount) { this.dailyTransferredAmount += amount; }

    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public void incrementFailedAttempts() { this.failedLoginAttempts++; }
    public void resetFailedAttempts() { this.failedLoginAttempts = 0; }

    public long getLastTransactionTime() { return lastTransactionTime; }
    public void setLastTransactionTime(long lastTransactionTime) { this.lastTransactionTime = lastTransactionTime; }
}