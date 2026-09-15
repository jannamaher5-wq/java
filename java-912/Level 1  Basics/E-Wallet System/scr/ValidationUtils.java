import java.util.List;

/**
 * ValidationUtils Class
 * Performs validation checks on user registration inputs using Java Streams API.
 */
public class ValidationUtils {

    // Validates username format (must start with an uppercase letter and be at least 3 characters long)
    public static boolean isValidUsername(String username) {
        if (username == null || username.length() < 3) return false;
        return Character.isUpperCase(username.charAt(0));
    }

    // Validates password complexity (must contain at least 1 uppercase letter, 1 digit, and length >= 6)
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) return false;

        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        return hasUpper && hasDigit;
    }

    // Validates user age limit (must be at least 18 years old)
    public static boolean isValidAge(int age) {
        return age >= 18;
    }

    // Validates Egyptian phone number format (11 digits, starts with 010, 011, 012, or 015)
    public static boolean isValidEgyptianPhone(String phone) {
        if (phone == null || phone.length() != 11) return false;

        List<String> validPrefixes = List.of("010", "011", "012", "015");

        boolean isValidPrefix = validPrefixes.stream().anyMatch(phone::startsWith);
        boolean isAllDigits = phone.chars().allMatch(Character::isDigit);

        return isValidPrefix && isAllDigits;
    }
}
