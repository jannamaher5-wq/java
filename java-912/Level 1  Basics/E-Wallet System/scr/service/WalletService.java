package service;

import model.Account;
import model.Transaction;
import util.OtpSession;
import java.util.List;

/**
 * Interface contract defining wallet business logic, user authorization, and banking operations.
 */
public interface WalletService {
    boolean register(String username, String rawPassword, int age, String phone) throws Exception;
    boolean login(String username, String rawPassword) throws Exception;
    void deposit(double amount) throws Exception;
    void withdraw(double amount) throws Exception;
    OtpSession generateOtpForTransfer() throws Exception;
    void transfer(String destinationUsername, double amount, int inputOtp, OtpSession otpSession) throws Exception;
    void changePassword(String oldRawPassword, String newRawPassword) throws Exception;
    List<Account> getAllAccounts() throws Exception;
    void toggleAccountStatus(String username, boolean active) throws Exception;
    void deleteAccount(String username) throws Exception;
    List<Transaction> getMyTransactionHistory() throws Exception;
    void logout();
    Account getLoggedInUser();
}