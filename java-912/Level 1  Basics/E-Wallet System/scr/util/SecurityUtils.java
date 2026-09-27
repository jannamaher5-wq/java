package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class providing cryptographic algorithms, secure salt generation, and data masking.
 */
public class SecurityUtils {

    /**
     * Generates a cryptographically strong 16-byte random salt.
     *
     * @return Base64 encoded string representation of the random salt.
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Computes SHA-256 cryptographic hash of a raw password appended with salt.
     *
     * @param password Plain-text raw password string.
     * @param salt     Random salt string to prevent Rainbow Table attacks.
     * @return Hexadecimal string representation of hashed password.
     */
    public static String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest((password + salt).getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred during password hashing", e);
        }
    }

    /**
     * Masks an Egyptian phone number for security purposes (e.g., 010*******12).
     *
     * @param phone Raw 11-digit phone number.
     * @return Masked phone string protecting private digits.
     */
    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 11) return phone;
        return phone.substring(0, 3) + "*******" + phone.substring(9);
    }
}