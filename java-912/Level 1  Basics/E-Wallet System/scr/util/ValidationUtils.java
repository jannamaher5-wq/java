package util;

import java.util.List;

/**
 * util.ValidationUtils Class
 * Validates registration constraints via Streams API.
 */
public class ValidationUtils {

    public static boolean isValidUsername(String username) {
        if (username == null || username.length() < 3) return false;
        return Character.isUpperCase(username.charAt(0));
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) return false;

        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        return hasUpper && hasDigit;
    }

    public static boolean isValidAge(int age) {
        return age >= 18;
    }

    public static boolean isValidEgyptianPhone(String phone) {
        if (phone == null || phone.length() != 11) return false;

        List<String> validPrefixes = List.of("010", "011", "012", "015");

        boolean isValidPrefix = validPrefixes.stream().anyMatch(phone::startsWith);
        boolean isAllDigits = phone.chars().allMatch(Character::isDigit);

        return isValidPrefix && isAllDigits;
    }
}