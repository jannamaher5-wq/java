package ui;

import model.Account;
import model.Transaction;
import service.WalletService;
import service.WalletServiceImpl;
import util.OtpSession;
import util.SecurityUtils;

import java.util.List;
import java.util.Scanner;

/**
 * User Interface entry point handling console menus, input loops, and display formatting.
 */
public class Main {
    private static final WalletService walletService = new WalletServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Application entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println(" 🌟 WELCOME TO THE DIGITAL BANKING WALLET SYSTEM 🌟");
        System.out.println("==================================================");

        while (true) {
            showMainMenu();
            int choice = readIntInput("Select an option: ");

            switch (choice) {
                case 1 -> handleSignUp();
                case 2 -> handleLogin();
                case 3 -> {
                    System.out.println("Thank you for using Digital Banking Wallet. Goodbye! 👋");
                    return;
                }
                default -> System.out.println("❌ Invalid choice option.");
            }
        }
    }

    /** Displays entry menu choices. */
    private static void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Create New Account (Sign-Up)");
        System.out.println("2. User Authentication (Login)");
        System.out.println("3. Exit System");
    }

    /** Prompts user registration inputs and handles account creation. */
    private static void handleSignUp() {
        System.out.println("\n--- CREATE NEW ACCOUNT ---");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        int age = readIntInput("Age: ");
        System.out.print("Egyptian Phone Number: ");
        String phone = scanner.next();

        try {
            if (walletService.register(username, password, age, phone)) {
                System.out.println("✅ Account created successfully!");
            }
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    /** Handles login inputs and session activation. */
    private static void handleLogin() {
        System.out.println("\n--- USER LOGIN ---");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        try {
            if (walletService.login(username, password)) {
                System.out.println("✅ Login successful!");
                handleUserMenu();
            }
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    /** Sub-menu executing authorized banking operations for logged-in user. */
    private static void handleUserMenu() {
        while (walletService.getLoggedInUser() != null) {
            Account user = walletService.getLoggedInUser();
            System.out.println("\n=== BANKING SERVICES MENU (Logged in: " + user.getUsername() + ") ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds (OTP)");
            System.out.println("4. Show Account Details");
            System.out.println("5. View Transaction History");
            System.out.println("6. Change Password");
            System.out.println("7. Delete My Account");

            if (user.isAdmin()) {
                System.out.println("8. [Admin] Display All Accounts");
                System.out.println("9. [Admin] Freeze / Unfreeze Account");
                System.out.println("10. [Admin] Delete Any Account");
            }

            System.out.println("0. Logout");
            int choice = readIntInput("Select an option: ");

            try {
                switch (choice) {
                    case 1 -> {
                        double amt = readDoubleInput("Deposit amount: ");
                        walletService.deposit(amt);
                        System.out.println("✅ Deposit successful. New Balance: " + user.getBalance() + " EGP");
                    }
                    case 2 -> {
                        double amt = readDoubleInput("Withdrawal amount: ");
                        walletService.withdraw(amt);
                        System.out.println("✅ Withdrawal successful. New Balance: " + user.getBalance() + " EGP");
                    }
                    case 3 -> handleTransfer();
                    case 4 -> showAccountDetails(user);
                    case 5 -> displayTransactionHistory();
                    case 6 -> {
                        System.out.print("Current password: ");
                        String oldP = scanner.next();
                        System.out.print("New password: ");
                        String newP = scanner.next();
                        walletService.changePassword(oldP, newP);
                        System.out.println("✅ Password updated successfully.");
                    }
                    case 7 -> {
                        System.out.print("Confirm account deletion? (yes/no): ");
                        if (scanner.next().equalsIgnoreCase("yes")) {
                            walletService.deleteAccount(user.getUsername());
                            System.out.println("✅ Account deleted.");
                        }
                    }
                    case 8 -> { if (user.isAdmin()) displayAllAccounts(); }
                    case 9 -> { if (user.isAdmin()) handleToggleStatus(); }
                    case 10 -> {
                        if (user.isAdmin()) {
                            System.out.print("Username to delete: ");
                            walletService.deleteAccount(scanner.next());
                            System.out.println("✅ Target account deleted.");
                        }
                    }
                    case 0 -> {
                        walletService.logout();
                        System.out.println("👋 Logged out successfully.");
                    }
                    default -> System.out.println("❌ Invalid choice option.");
                }
            } catch (Exception e) {
                System.out.println("❌ Operation Error: " + e.getMessage());
            }
        }
    }

    /** Prompts transfer data and verifies via simulated SMS OTP session. */
    private static void handleTransfer() throws Exception {
        System.out.print("Recipient username: ");
        String toUser = scanner.next();
        double amount = readDoubleInput("Transfer amount: ");

        OtpSession otpSession = walletService.generateOtpForTransfer();
        System.out.println("📲 [Simulated SMS OTP]: " + otpSession.getCode() + " (Valid for 60s)");
        int userOTP = readIntInput("Enter OTP code: ");

        walletService.transfer(toUser, amount, userOTP, otpSession);
        System.out.println("✅ Transfer completed successfully!");
    }

    /** Prints masked account profile details. */
    private static void showAccountDetails(Account acc) {
        System.out.println("\n--- ACCOUNT DETAILS ---");
        System.out.println("Username: " + acc.getUsername());
        System.out.println("Phone Number: " + SecurityUtils.maskPhoneNumber(acc.getPhoneNumber()));
        System.out.println("Age: " + acc.getAge());
        System.out.println("Current Balance: " + acc.getBalance() + " EGP");
        System.out.println("Password Status: *********");
        System.out.println("Account Type: " + (acc.isAdmin() ? "Administrator" : "Standard User"));
    }

    /** Prints historical transaction entries for logged-in user. */
    private static void displayTransactionHistory() throws Exception {
        List<Transaction> history = walletService.getMyTransactionHistory();
        System.out.println("\n--- TRANSACTION HISTORY LOGS ---");
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            history.forEach(transaction -> System.out.println(transaction));
        }
    }

    /** Displays entire accounts database (Admin restricted). */
    private static void displayAllAccounts() throws Exception {
        List<Account> list = walletService.getAllAccounts();
        System.out.println("\n--- REGISTERED ACCOUNTS DATABASE ---");
        list.forEach(acc -> System.out.println("- User: " + acc.getUsername() +
                " | Phone: " + SecurityUtils.maskPhoneNumber(acc.getPhoneNumber()) +
                " | Balance: " + acc.getBalance() +
                " EGP | Status: " + (acc.isActive() ? "ACTIVE" : "LOCKED/FROZEN")));
    }

    /** Modifies target account status between active/frozen (Admin restricted). */
    private static void handleToggleStatus() throws Exception {
        System.out.print("Target username: ");
        String target = scanner.next();
        System.out.print("Freeze account? (true/false): ");
        boolean freeze = scanner.nextBoolean();
        walletService.toggleAccountStatus(target, !freeze);
        System.out.println("✅ Account status updated successfully.");
    }

    /** Validates and parses integer inputs safely preventing console exceptions. */
    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid integer input!");
            }
        }
    }

    /** Validates and parses double numerical inputs safely. */
    private static double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid numerical amount!");
            }
        }
    }
}