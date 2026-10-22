package service;

import exception.DuplicatePlateException;
import exception.VehicleNotFoundException;
import model.Vehicle;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class RegistrationService {
    private List<Vehicle> vehicleList = new ArrayList<>();
    private Map<String, Vehicle> plateIndex = new HashMap<>();
    private Set<String> registeredPlates = new HashSet<>();

    public void registerVehicle(Vehicle v) {
        String normalizedPlate = v.getPlateNumber().toUpperCase();
        if (registeredPlates.contains(normalizedPlate)) {
            throw new DuplicatePlateException(normalizedPlate);
        }
        vehicleList.add(v);
        plateIndex.put(normalizedPlate, v);
        registeredPlates.add(normalizedPlate);
    }

    public Vehicle findByPlate(String plate) {
        String normalizedPlate = plate.toUpperCase();
        Vehicle v = plateIndex.get(normalizedPlate);
        if (v == null) {
            throw new VehicleNotFoundException(plate);
        }
        return v;
    }

    public void deleteVehicle(String plate) {
        Vehicle v = findByPlate(plate);
        vehicleList.remove(v);
        plateIndex.remove(v.getPlateNumber().toUpperCase());
        registeredPlates.remove(v.getPlateNumber().toUpperCase());
    }

    public void updateOwner(String plate, String newOwner) {
        Vehicle v = findByPlate(plate);
        v.setOwnerName(newOwner);
    }

    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehicleList);
    }

    public List<Vehicle> filterByType(String type) {
        return vehicleList.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByOwner(String ownerName) {
        return vehicleList.stream()
                .filter(v -> v.getOwnerName().toLowerCase().contains(ownerName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getExpiredRegistrations(int currentYear) {
        return vehicleList.stream()
                .filter(v -> (currentYear - v.getRegistrationYear()) > 5)
                .sorted(Comparator.comparingInt(Vehicle::getRegistrationYear))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getSortedByYear(boolean ascending) {
        Comparator<Vehicle> comp = Comparator.comparingInt(Vehicle::getRegistrationYear);
        if (!ascending) comp = comp.reversed();
        return vehicleList.stream().sorted(comp).collect(Collectors.toList());
    }

    public void printStatistics() {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles registered in the system.");
            return;
        }

        IntSummaryStatistics stats = vehicleList.stream()
                .mapToInt(Vehicle::getRegistrationYear)
                .summaryStatistics();

        Map<String, Long> byType = vehicleList.stream()
                .collect(Collectors.groupingBy(Vehicle::getVehicleType, Collectors.counting()));

        Map<Boolean, Long> byStatus = vehicleList.stream()
                .collect(Collectors.partitioningBy(v -> v.getStatus().equalsIgnoreCase("ACTIVE"), Collectors.counting()));

        System.out.println("==== REGISTRATION STATISTICS ====");
        System.out.printf("Total Vehicles : %d\n", stats.getCount());
        System.out.printf("Average Year   : %.1f\n", stats.getAverage());
        System.out.printf("Newest Vehicle : %d\n", stats.getMax());
        System.out.printf("Oldest Vehicle : %d\n", stats.getMin());
        System.out.println("Vehicles by Type:");
        byType.forEach((type, count) -> System.out.printf("  - %-10s : %d\n", type, count));
        System.out.printf("ACTIVE vehicles : %d\n", byStatus.getOrDefault(true, 0L));
        System.out.printf("EXPIRED vehicles: %d\n", byStatus.getOrDefault(false, 0L));
    }

    public void saveDataToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(vehicleList);
            System.out.println("Data successfully saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadDataFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No saved file found. Starting with empty/seed data.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Vehicle> loadedList = (List<Vehicle>) ois.readObject();
            vehicleList.clear();
            plateIndex.clear();
            registeredPlates.clear();
            for (Vehicle v : loadedList) {
                vehicleList.add(v);
                plateIndex.put(v.getPlateNumber().toUpperCase(), v);
                registeredPlates.add(v.getPlateNumber().toUpperCase());
            }
            System.out.println("Data successfully loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}
