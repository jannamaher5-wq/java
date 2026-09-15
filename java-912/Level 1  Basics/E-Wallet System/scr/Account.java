/**
 * Account Class
 * Serves as the domain model for a user account (Encapsulation).
 * Holds essential details including credentials, balance, status, and daily limits.
 */
public class Account {
    private String username;
    private String passwordHash;
    private int age;
    private String phoneNumber;
    private double balance;
    private boolean isAdmin;
    private boolean isActive;
    private double dailyTransferredAmount;

    // Parameterized Constructor
    public Account(String username, String passwordHash, int age, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 0.0;
        this.isAdmin = isAdmin;
        this.isActive = true;
        this.dailyTransferredAmount = 0.0;
    }

    // Getters and Setters
    public String getUsername() { return username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public int getAge() { return age; }
    public String getPhoneNumber() { return phoneNumber; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public boolean isAdmin() { return isAdmin; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public double getDailyTransferredAmount() { return dailyTransferredAmount; }
    public void addDailyTransfer(double amount) { this.dailyTransferredAmount += amount; }
    public void resetDailyTransfer() { this.dailyTransferredAmount = 0.0; }
}
