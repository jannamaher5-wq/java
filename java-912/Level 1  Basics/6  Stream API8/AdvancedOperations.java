import java.util.*;
import java.util.stream.Collectors;
//ي الترتيب المركب بتاع المرتب والاسم بـ thenComparing جافا ذكية لو لقت إن المرتبات مش زي بعضها بتقفل الموضوع ومبتدخلش تقارن الأسماء عشان مبضيعش وقت عالفاضي
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
    public double getGrade() { return grade; }
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

    public String getName() { return name; }
    public double getSalary() { return salary; }
}

public class AdvancedOperations {
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

        // هنا بنعمل ترتيب مركب، الأول بنرتب حسب المرتب بالـ comparingDouble ولو اتنين طلعوا بنفس المرتب يدخل ينفذ الشرط التاني thenComparing ويرتبهم بالاسم
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println("Sorted Employees (by Salary, Name):");
        sortedEmployees.forEach(e -> System.out.println(e.getName() + " -> " + e.getSalary()));

        // عشان نجيب تاني أعلى رقم، بنشيل التكرار الأول بـ distinct وبعدين بنرتب تنازلي وبنستخدم skip(1) لتخطي أول رقم (اللي هو الأعلى) وناخد اللي بعده بـ findFirst
        Optional<Integer> secondHighest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("Second Highest Number: " + secondHighest.orElse(null));

        // بنكتشف الأرقام المكررة باستخدام HashSet، دالة set.add بترجع false لو الرقم كان موجود من قبل كده وبناءً عليه الـ filter بينتقي الرقم المكرر بس
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = numbers.stream()
                .filter(n -> !seen.add(n))
                .collect(Collectors.toSet());
        System.out.println("Duplicates: " + duplicates);

        // بننضف القائمة من أي عناصر null أو كلمات فاضية وبنستخدم trim عشان نضمن إن النص اللي عبارة عن مسافات بس يتم استبعاده برضه
        List<String> cleanNames = names.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.toList());
        System.out.println("Cleaned Names: " + cleanNames);

        // بنقسم الطلاب بقائمة المجموعات partitioningBy لمجموعتين بناءً على درجة النجاح (الدرجة أكبر من أو تساوي 60)، المجموعة true للناجحين و false للراسبين
        Map<Boolean, List<Student>> passFail = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGrade() >= 60));
        System.out.println("Passed Students Count: " + passFail.get(true).size());
        System.out.println("Failed Students Count: " + passFail.get(false).size());
    }
}