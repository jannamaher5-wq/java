import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class Q10 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Ahmed", "IT", 10000),
            new Employee("Mohamed", "IT", 18000),
            new Employee("Ali", "IT", 15000),
            new Employee("Omar", "IT", 22000),
            new Employee("Sara", "HR", 12000),
            new Employee("Mona", "HR", 16000),
            new Employee("Nour", "HR", 14000),
            new Employee("Hana", "HR", 9000),
            new Employee("Khaled", "Finance", 20000),
            new Employee("Youssef", "Finance", 25000),
            new Employee("Mostafa", "Finance", 18000)
        );

        Map<String, List<Employee>> top3HighestPaidPerDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()
                            .thenComparing(Employee::getName))
                        .limit(3)
                        .collect(Collectors.toList())
                )
            ));

        top3HighestPaidPerDept.forEach((dept, emps) -> {
            System.out.println(dept + ":");
            emps.forEach(e -> System.out.println("  " + e.name + " | " + e.salary));
        });
    }
}