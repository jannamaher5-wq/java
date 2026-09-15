import java.util.List;

/**
 * WalletService Interface
 * Defines core contracts and operational services provided by the banking wallet system.
 */
public interface WalletService {
    boolean register(String username, String rawPassword, int age, String phone) throws Exception;
    boolean login(String username, String rawPassword) throws Exception;
    void deposit(double amount) throws Exception;
    void withdraw(double amount) throws Exception;
    void transfer(String destinationUsername, double amount) throws Exception;
    void changePassword(String oldRawPassword, String newRawPassword) throws Exception;
    List<Account> getAllAccounts() throws Exception;
    void toggleAccountStatus(String username, boolean active) throws Exception;
    void logout();
    Account getLoggedInUser();
}