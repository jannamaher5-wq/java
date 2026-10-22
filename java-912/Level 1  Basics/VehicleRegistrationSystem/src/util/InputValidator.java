package util;

import exception.InvalidInputException;
import java.time.Year;

public class InputValidator {

    public static void validatePlateNumber(String plateNumber) {
        if (plateNumber == null || !plateNumber.matches("[A-Z0-9-]{3,10}")) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }
    }

    public static void validateOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().length() < 3 || !ownerName.matches("[a-zA-Z\\s]+")) {
            throw new InvalidInputException("Owner name must contain only letters and be at least 3 chars");
        }
    }

    public static void validateVehicleType(String type) {
        if (type == null || (!type.equalsIgnoreCase("Car") && !type.equalsIgnoreCase("Truck") && !type.equalsIgnoreCase("Motorcycle"))) {
            throw new InvalidInputException("Invalid type. Use: Car, Truck, Motorcycle");
        }
    }

    public static void validateYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1990 || year > currentYear) {
            throw new InvalidInputException("Year must be between 1990 and " + currentYear);
        }
    }
}