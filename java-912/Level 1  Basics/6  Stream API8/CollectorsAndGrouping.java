import java.util.*;
import java.util.stream.Collectors;
//الـ groupingBy العادية بترمي النتائج في HashMap من غير ترتيب.. بس لو عاوز النتايج تطلع مترتبة ومنظمة لوحدها أبجدي أو رقمي ممكن تخليها ترجع في TreeMap وخلصنا
class Student {
    String name;
    String department;
    double grade;

    Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
}

class Employee {
    String name;
    int age;
    String department;
    double salary;

    Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class CollectorsAndGrouping {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        // بنجمع الطلاب في Map المفتوح بتاعها هو اسم القسم والـ Value قائمة الطلاب اللي فيه، باستخدام Collectors.groupingBy
        Map<String, List<Student>> studentsByDept = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println("Students by Department: " + studentsByDept.keySet());

        // بنقسم عناصر القائمة لفرقين بس (true أو false) عن طريق partitioningBy بناءً على الشرط نصوص الأرقام الزوجية
        Map<Boolean, List<Integer>> evenOddMap = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even (true) / Odd (false): " + evenOddMap);

        // بنشيل القيم الفاضية والـ null وبعد كده بنربط بقية النصوص ببعض في String واحد يفصل بين كل كلمة والتانية بفاصلة ومسافة
        String csvNames = names.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("Comma Separated: " + csvNames);

        // بنعمل تجميع للموظفين بحسب سنهم، وبدل ما نرجع قائمة بالموظفين بنمرر Collectors.counting() عشان نرجع العدد الإجمالي لكل سن
        Map<Integer, Long> ageCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));
        System.out.println("Count per Age: " + ageCount);

        // بنعمل تجميع للموظفين بالأقسام وبنحسب متوسط مرتبات الموظفين جوه كل قسم باستعمال الـ Collector الثانوي Collectors.averagingDouble
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("Average Salary per Dept: " + avgSalaryByDept);
    }
}