package util;

import java.util.List;

/**
 * Utility class handling data format validations using Java Stream API.
 */
public class ValidationUtils {

    /**
     * Validates if username starts with uppercase and meets minimum length constraint.
     *
     * @param username Target username input.
     * @return True if valid, false otherwise.
     */
    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= 3 && Character.isUpperCase(username.charAt(0));
    }

    /**
     * Checks password complexity constraints (>= 6 chars, >= 1 uppercase letter, >= 1 digit).
     *
     * @param password Target raw password input.
     * @return True if meets complexity requirements, false otherwise.
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) return false;
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        return hasUpper && hasDigit;
    }

    /**
     * Verifies if user age complies with banking regulations (>= 18).
     *
     * @param age Target user age.
     * @return True if 18 or older, false otherwise.
     */
    public static boolean isValidAge(int age) {
        return age >= 18;
    }

    /**
     * Validates if phone number conforms to Egyptian standard formats (010, 011, 012, 015).
     *
     * @param phone Target phone number string.
     * @return True if valid 11-digit Egyptian phone number, false otherwise.
     */
    public static boolean isValidEgyptianPhone(String phone) {
        if (phone == null || phone.length() != 11) return false;
        List<String> validPrefixes = List.of("010", "011", "012", "015");
        return validPrefixes.stream().anyMatch(phone::startsWith) && phone.chars().allMatch(Character::isDigit);
    }
}