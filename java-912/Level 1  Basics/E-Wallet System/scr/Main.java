import java.util.List;
import java.util.Scanner;

/**
 * Main Class
 * Entry point for the Application Console User Interface (UI).
 * Handles menu navigation, user sessions, inputs, and error handlers.
 */
public class Main {
    private static WalletService walletService = new WalletServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println(" 🌟 WELCOME TO THE DIGITAL BANKING WALLET SYSTEM 🌟");
        System.out.println("==================================================");

        while (true) {
            showMainMenu();
            int choice = readIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    handleSignUp();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 3:
                    System.out.println("Thank you for using Digital Banking Wallet. Goodbye! 👋");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Create New Account (Sign-Up)");
        System.out.println("2. User Authentication (Login)");
        System.out.println("3. Exit System");
    }

    private static void handleSignUp() {
        System.out.println("\n--- CREATE NEW ACCOUNT ---");
        System.out.print("Enter Username (Starts with Upper-case letter): ");
        String username = scanner.next();

        System.out.print("Enter Password (Must contain Upper-case letter & Digit): ");
        String password = scanner.next();

        int age = readIntInput("Enter Age (Minimum 18): ");

        System.out.print("Enter Egyptian Phone Number (11 digits): ");
        String phone = scanner.next();

        try {
            boolean success = walletService.register(username, password, age, phone);
            if (success) {
                System.out.println("✅ Account successfully created! You can now login.");
            }
        } catch (Exception e) {
            System.out.println("❌ Registration Failed: " + e.getMessage());
        }
    }

    private static void handleLogin() {
        System.out.println("\n--- USER LOGIN ---");
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Username: ");
            String username = scanner.next();

            System.out.print("Password: ");
            String password = scanner.next();

            try {
                boolean loggedIn = walletService.login(username, password);
                if (loggedIn) {
                    System.out.println("✅ Login successful!");
                    handleUserMenu();
                    return;
                }
            } catch (Exception e) {
                attempts++;
                System.out.println("❌ " + e.getMessage());
                if (attempts < maxAttempts) {
                    System.out.println("⚠️ Remaining authentication attempts: " + (maxAttempts - attempts));
                } else {
                    System.out.println("🔒 Maximum login attempts exceeded! Returning to Main Menu.");
                }
            }
        }
    }

    private static void handleUserMenu() {
        while (walletService.getLoggedInUser() != null) {
            Account user = walletService.getLoggedInUser();
            System.out.println("\n=== BANKING SERVICES MENU (Logged in: " + user.getUsername() + ") ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds (with OTP)");
            System.out.println("4. Show Account Balance & Details");
            System.out.println("5. Change Password");

            if (user.isAdmin()) {
                System.out.println("6. [Admin] Display All Accounts");
                System.out.println("7. [Admin] Freeze / Unfreeze Account");
            }

            System.out.println("0. Logout");

            int choice = readIntInput("Select a service: ");

            try {
                switch (choice) {
                    case 1:
                        double depAmount = readDoubleInput("Enter deposit amount: ");
                        walletService.deposit(depAmount);
                        System.out.println("✅ Deposit successful! Current Balance: " + user.getBalance() + " EGP");
                        break;
                    case 2:
                        double withAmount = readDoubleInput("Enter withdrawal amount: ");
                        walletService.withdraw(withAmount);
                        System.out.println("✅ Withdrawal successful! Current Balance: " + user.getBalance() + " EGP");
                        break;
                    case 3:
                        handleTransfer();
                        break;
                    case 4:
                        showAccountDetails(user);
                        break;
                    case 5:
                        System.out.print("Enter current password: ");
                        String oldPass = scanner.next();
                        System.out.print("Enter new password: ");
                        String newPass = scanner.next();
                        walletService.changePassword(oldPass, newPass);
                        System.out.println("✅ Password successfully updated!");
                        break;
                    case 6:
                        if (user.isAdmin()) displayAllAccounts();
                        break;
                    case 7:
                        if (user.isAdmin()) handleToggleStatus();
                        break;
                    case 0:
                        walletService.logout();
                        System.out.println("👋 Logged out successfully.");
                        break;
                    default:
                        System.out.println("❌ Invalid choice option.");
                }
            } catch (Exception e) {
                System.out.println("❌ Operation Error: " + e.getMessage());
            }
        }
    }

    private static void handleTransfer() throws Exception {
        System.out.print("Enter recipient username: ");
        String toUser = scanner.next();
        double amount = readDoubleInput("Enter transfer amount: ");

        int generatedOTP = SecurityUtils.generateOTP();
        System.out.println("\n📲 [Simulated SMS Service] Your Verification OTP is: " + generatedOTP);
        int userOTP = readIntInput("Enter OTP code to confirm transfer: ");

        if (userOTP != generatedOTP) {
            System.out.println("❌ Invalid OTP code! Transfer cancelled.");
            return;
        }

        walletService.transfer(toUser, amount);
        System.out.println("✅ Transfer of " + amount + " EGP to " + toUser + " completed successfully!");
        System.out.println("💰 Current Balance: " + walletService.getLoggedInUser().getBalance() + " EGP");
    }

    private static void showAccountDetails(Account acc) {
        System.out.println("\n--- ACCOUNT DETAILS ---");
        System.out.println("Username: " + acc.getUsername());
        System.out.println("Phone Number: " + acc.getPhoneNumber());
        System.out.println("Age: " + acc.getAge());
        System.out.println("Current Balance: " + acc.getBalance() + " EGP");
        System.out.println("Password Status: ********* (Hashed & Encrypted)");
        System.out.println("Account Type: " + (acc.isAdmin() ? "Administrator" : "Standard User"));
    }

    private static void displayAllAccounts() throws Exception {
        List<Account> list = walletService.getAllAccounts();
        System.out.println("\n--- REGISTERED ACCOUNTS DATABASE ---");
        list.stream().forEach(acc ->
                System.out.println("- User: " + acc.getUsername() +
                        " | Phone: " + acc.getPhoneNumber() +
                        " | Balance: " + acc.getBalance() +
                        " EGP | Status: " + (acc.isActive() ? "ACTIVE" : "FROZEN"))
        );
    }

    private static void handleToggleStatus() throws Exception {
        System.out.print("Enter target username to update status: ");
        String target = scanner.next();
        System.out.print("Freeze account? (Type true to freeze, false to activate): ");
        boolean freeze = scanner.nextBoolean();
        walletService.toggleAccountStatus(target, !freeze);
        System.out.println("✅ Account status updated successfully.");
    }

    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid integer number.");
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid numerical amount (e.g. 150.75).");
            }
        }
    }
}
