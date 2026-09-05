import java.util.*;
//عشان نحسب المتوسط حولنا الستريم لـ DoubleStream صريح بدل الـ Objects العادية.. الحركات دي بتخلي جافا متبلش مجهود في التحويلات وتلاقي الكود طيارة والرامات مرتاحة
class Student {
    String name;
    String department;
    double grade;

    Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    public double getGrade() { return grade; }
}

public class NumericStreamsAndReductions {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        // بنستخدم reduce وبنديلها القيمة الابتدائية 0 ودالة الجمع Integer::sum، فبتبدأ تجمع كل عنصر على المجموع التراكمي للحد ما نطلع برقم واحد
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum of numbers: " + sum);

        // بنستخدم max و min وبنبصيلهم المقارن الطبيعي للأرقام عشان يقارن كل رقم بالتاني ويرجع أعلى وأقل قيمة جوه Optional
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println("Max: " + max.orElse(null) + ", Min: " + min.orElse(null));

        // بنحول الستريم العادي لـ DoubleStream مخصص للعمليات الحسابية عن طريق mapToDouble وده بيتيح لينا نستخدم دالة جاهزة ومباشرة زي average لحساب المتوسط
        double avgGrade = students.stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0.0);
        System.out.println("Average Grade: " + avgGrade);

        // نفس فكرة الجمع التراكمي بس القيمة الابتدائية هنا 1 عشان الضرب، والدالة بتضرب القيمة الحالية في الناتج القديم وتستمر للآخر
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product of numbers: " + product);

        // بنفلتر الأرقام وناخد اللي قيمتها أكبر من الزيرو بس (الموجبة) وبعدين بنحسب عددهم عن طريق count
        long positiveCount = numbers.stream()
                .filter(n -> n > 0)
                .count();
        System.out.println("Positive numbers count: " + positiveCount);
    }
}