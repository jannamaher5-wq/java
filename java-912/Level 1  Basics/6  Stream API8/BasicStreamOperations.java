import java.util.*;
import java.util.stream.Collectors;
//عمل filter وتعمل map للصبح جافا مش هتنفذ أي حاجة ولا هتحرك صباع.. هي بتكسل ومبتشتغلش غير لما تحط في الآخر حاجة نهائية زي .collect(). 
// الحركة دي بتوفر وقت المعالجة والرامات جامد

public class BasicStreamOperations {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        // هنا بنستخدم filter عشان نعدي على كل رقم ونشوف هل باقي قسمته على 2 بيساوي 0 ولا لأ، وبكده بنحتفظ بالأرقام الزوجية بس
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evens);

        // بنفلتر الأسماء اللي بتبدأ بحرف A بس الأهم حطينا شرط name != null الأول عشان لو القائمة فيها عناصر فاضية المبرمج ما يواجهش NullPointerException
        List<String> aNames = names.stream()
                .filter(name -> name != null && name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Names starting with A: " + aNames);

        // هنا بنحول النصوص كلها لحروف كابيتال باستخدام String::toUpperCase وتجنبنا القيم الـ null باستخدام Objects::nonNull عشان نضمن إن الدالة تتطبق على نصوص سليمة بس
        List<String> upperNames = names.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperNames);

        // بنرتب الأرقام ترتيب تنازلي عن طريق إعطاء الدالة sorted المقارن Comparator.reverseOrder() عشان تعكس الترتيب الافتراضي
        List<Integer> sortedDesc = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Sorted Descending: " + sortedDesc);

        // دالة distinct بتعدي على العناصر وتلغي أي عنصر مكرر بناءً على دالة equals الخاصة بالـ Object، وبكده يفضل العناصر الفريدة بس
        List<Integer> uniqueNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique Numbers: " + uniqueNumbers);
    }
}