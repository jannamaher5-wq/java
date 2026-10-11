import java.util.*;
import java.util.function.Function;
import java.util.function.BinaryOperator;
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

public class Q7 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Ahmed", "IT", 10000),
            new Employee("Mohamed", "IT", 18000),
            new Employee("Sara", "HR", 9000),
            new Employee("Omar", "HR", 14000),
            new Employee("Ali", "Finance", 20000)
        );

        Map<String, Employee> highestPaidByDept = employees.stream()
            .collect(Collectors.toMap(
                Employee::getDepartment,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));

        highestPaidByDept.forEach((dept, emp) -> System.out.println(dept + " : " + emp.name));
    }
}