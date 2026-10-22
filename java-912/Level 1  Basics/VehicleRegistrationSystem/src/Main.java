
import exception.DuplicatePlateException;
import exception.VehicleNotFoundException;
import exception.InvalidInputException;
import model.*;
import service.RegistrationService;
import util.InputValidator;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class Main {
    // باقي الكود...
    private static final String DATA_FILE = "vehicles_data.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        service.loadDataFromFile(DATA_FILE);
        if (service.getAllVehicles().isEmpty()) {
            try {
                service.registerVehicle(new Car("CAR-001", "Ahmed Ali", 2019, "EXPIRED", 4));
                service.registerVehicle(new Car("CAR-002", "Sara Kamel", 2023, "ACTIVE", 2));
                service.registerVehicle(new Truck("TRK-001", "Mohamed Said", 2021, "ACTIVE", 10.0));
                service.registerVehicle(new Truck("TRK-002", "Laila Nour", 2017, "EXPIRED", 5.5));
                service.registerVehicle(new Motorcycle("MOT-001", "Omar Fathi", 2022, "ACTIVE", "Sport"));
                service.registerVehicle(new Motorcycle("MOT-002", "Nadia Hamed", 2024, "ACTIVE", "Cruiser"));
            } catch (Exception ignored) {}
        }

        while (true) {
            System.out.println("\n===== VEHICLE REGISTRATION SYSTEM v1.0 =====");
            System.out.println("1. Register New Vehicle");
            System.out.println("2. Search Vehicle by Plate");
            System.out.println("3. Update Owner Name");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. List All Vehicles");
            System.out.println("6. Filter by Vehicle Type");
            System.out.println("7. Show Owner History");
            System.out.println("8. Show Expired Registrations");
            System.out.println("9. Statistics Report");
            System.out.println("10. Save & Exit");
            System.out.println("0. Exit Without Saving");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Plate Number (e.g., ABC-1234): ");
                        String plate = sc.nextLine().toUpperCase();
                        InputValidator.validatePlateNumber(plate);

                        System.out.print("Enter Owner Full Name: ");
                        String owner = sc.nextLine();
                        InputValidator.validateOwnerName(owner);

                        System.out.print("Enter Vehicle Type (Car, Truck, Motorcycle): ");
                        String type = sc.nextLine();
                        InputValidator.validateVehicleType(type);

                        System.out.print("Enter Registration Year (1990-" + Year.now().getValue() + "): ");
                        int year = sc.nextInt();
                        sc.nextLine();
                        InputValidator.validateYear(year);

                        System.out.print("Enter Status (ACTIVE / EXPIRED): ");
                        String status = sc.nextLine().toUpperCase();

                        Vehicle v = null;
                        if (type.equalsIgnoreCase("Car")) {
                            System.out.print("Enter Number of Doors: ");
                            int doors = sc.nextInt();
                            sc.nextLine();
                            v = new Car(plate, owner, year, status, doors);
                        } else if (type.equalsIgnoreCase("Truck")) {
                            System.out.print("Enter Cargo Capacity (Tons): ");
                            double capacity = sc.nextDouble();
                            sc.nextLine();
                            v = new Truck(plate, owner, year, status, capacity);
                        } else if (type.equalsIgnoreCase("Motorcycle")) {
                            System.out.print("Enter Engine Type: ");
                            String engine = sc.nextLine();
                            v = new Motorcycle(plate, owner, year, status, engine);
                        }

                        if (v != null) {
                            service.registerVehicle(v);
                            System.out.println("Vehicle registered successfully!");
                        }
                        break;

                    case 2:
                        System.out.print("Enter Plate Number to search: ");
                        String searchPlate = sc.nextLine();
                        Vehicle found = service.findByPlate(searchPlate);
                        System.out.println("Found: " + found);
                        System.out.println("Label: " + found.getRegistrationLabel());
                        break;

                    case 3:
                        System.out.print("Enter Plate Number to update: ");
                        String updatePlate = sc.nextLine();
                        System.out.print("Enter New Owner Name: ");
                        String newOwner = sc.nextLine();
                        InputValidator.validateOwnerName(newOwner);
                        service.updateOwner(updatePlate, newOwner);
                        System.out.println("Owner updated successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Plate Number to delete: ");
                        String delPlate = sc.nextLine();
                        service.deleteVehicle(delPlate);
                        System.out.println("Vehicle deleted successfully!");
                        break;

                    case 5:
                        List<Vehicle> all = service.getAllVehicles();
                        if (all.isEmpty()) {
                            System.out.println("No vehicles registered.");
                        } else {
                            all.forEach(System.out::println);
                        }
                        break;

                    case 6:
                        System.out.print("Enter Type to filter (Car, Truck, Motorcycle): ");
                        String fType = sc.nextLine();
                        List<Vehicle> filtered = service.filterByType(fType);
                        filtered.forEach(System.out::println);
                        break;

                    case 7:
                        System.out.print("Enter Owner Name (or part of it): ");
                        String oName = sc.nextLine();
                        List<Vehicle> owned = service.getVehiclesByOwner(oName);
                        owned.forEach(System.out::println);
                        break;

                    case 8:
                        List<Vehicle> expired = service.getExpiredRegistrations(Year.now().getValue());
                        if (expired.isEmpty()) {
                            System.out.println("No expired registrations found.");
                        } else {
                            expired.forEach(System.out::println);
                        }
                        break;

                    case 9:
                        service.printStatistics();
                        break;

                    case 10:
                        service.saveDataToFile(DATA_FILE);
                        System.out.println("Exiting application. Goodbye!");
                        sc.close();
                        return;

                    case 0:
                        System.out.println("Exiting without saving. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 10.");
                }
            } catch (DuplicatePlateException | VehicleNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
