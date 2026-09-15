import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * SecurityUtils Class
 * Provides helper security utility methods for cryptographic operations
 * such as SHA-256 password hashing and dynamic OTP generation.
 */
public class SecurityUtils {

    // Hashes raw password input using SHA-256 algorithm
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred while hashing password.", e);
        }
    }

    // Generates a random 4-digit One-Time Password (OTP)
    public static int generateOTP() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
}