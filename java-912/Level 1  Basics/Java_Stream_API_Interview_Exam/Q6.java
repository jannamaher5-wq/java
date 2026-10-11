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

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class Q6 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Ahmed", "IT", 10000),
            new Employee("Mohamed", "IT", 15000),
            new Employee("Sara", "HR", 8000),
            new Employee("Omar", "HR", 12000),
            new Employee("Ali", "Finance", 20000)
        );

        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));

        avgSalaryByDept.forEach((dept, avg) -> System.out.println(dept + " = " + avg));
    }
}