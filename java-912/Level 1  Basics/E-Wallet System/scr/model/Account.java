package model;

import util.SecurityUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain model representing a user bank account with security metrics and balance information.
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
    private List<Transaction> transactionHistory;

    /**
     * Constructs a new user Account with secure salted password hashing.
     *
     * @param username    Unique identifier for the user account.
     * @param rawPassword Raw plain-text password to be salted and hashed.
     * @param age         User age (must meet legal requirements).
     * @param phoneNumber Egyptian standard phone number.
     * @param isAdmin     Administrative privilege flag.
     */
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
        this.transactionHistory = new ArrayList<>();

        addTransaction("SIGN_UP", 0, "Account created successfully.");
    }

    /**
     * Appends a new transaction entry to the user's transaction history.
     *
     * @param type    Transaction operation category.
     * @param amount  Transaction monetary value.
     * @param details Descriptive information about the operation.
     */
    public void addTransaction(String type, double amount, String details) {
        this.transactionHistory.add(new Transaction(type, amount, details));
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
    public void setActive(boolean active) { this.isActive = active; }
    public double getDailyTransferredAmount() { return dailyTransferredAmount; }
    public void addDailyTransfer(double amount) { this.dailyTransferredAmount += amount; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public void incrementFailedAttempts() { this.failedLoginAttempts++; }
    public void resetFailedAttempts() { this.failedLoginAttempts = 0; }
    public long getLastTransactionTime() { return lastTransactionTime; }
    public void setLastTransactionTime(long lastTransactionTime) { this.lastTransactionTime = lastTransactionTime; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
}