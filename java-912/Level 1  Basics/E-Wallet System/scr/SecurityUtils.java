import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * SecurityUtils Class
 * Handles cryptographic hashing, random salt generation, and data masking.
 */
public class SecurityUtils {

    // Generates a cryptographically strong random salt
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hashes raw password using SHA-256 with Salt
    public static String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + salt;
            byte[] encodedHash = digest.digest(saltedPassword.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred while hashing password", e);
        }
    }

    // Masks Egyptian Phone Number (e.g., 010*******12)
    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 11) return phone;
        return phone.substring(0, 3) + "*******" + phone.substring(9);
    }
}