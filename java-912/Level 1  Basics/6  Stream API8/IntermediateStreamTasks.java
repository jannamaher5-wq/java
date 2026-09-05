import java.util.*;
import java.util.stream.Collectors;
//لو استخدمت findFirst فهي بتجيب أول عنصر بالظبط وبتدقق حتى لو الشغل شغال باراليل ومتوزع.. إنما لو مش فارق معاك مين الأول وعايز أسرع نتيجة تخلص بيه استخدم findAny أسرع بكتير

public class IntermediateStreamTasks {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        // بنعمل تصفية للـ null الأول وبعدين بنفلتر الأسماء اللي عدد حروفها أكتر من 5، وفي الآخر بنستخدم count عشان نرجع عدد العناصر مش العناصر نفسها
        long countLong = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.length() > 5)
                .count();
        System.out.println("Count > 5 chars: " + countLong);

        // بنتدور على أول عنصر بيحقق الشرط إنه يبدأ بحرف M ودالة findFirst بترجع Optional عشان لو مالقتش حاجة الكود ما يضربش ونقدر نتعامل مع القيمة بـ orElse
        Optional<String> firstM = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.startsWith("M"))
                .findFirst();
        System.out.println("First name starting with M: " + firstM.orElse("None"));

        // دالة anyMatch بتمشي على القائمة وتشوف لو فيه على الأقل عنصر واحد بيقبل القسمة على 5 ومش صفر بترجع true فوراً وبتقف بدل ما تلف على باقي القائمة
        boolean hasDivBy5 = numbers.stream()
                .anyMatch(n -> n != 0 && n % 5 == 0);
        System.out.println("Is any number divisible by 5? " + hasDivBy5);

        // بنجمع العناصر ونحطها جوه Set باستخدام Collectors.toSet() وده بشكل تلقائي بيمسح أي تكرار لأن الـ Set مابتقبلش عناصر مكررة
        Set<Integer> numberSet = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println("Collected to Set: " + numberSet);

        // دالة skip بتهمل أول 3 عناصر في الستريم وبتبدأ تجمع وتبعت من العنصر الرابع للآخر
        List<Integer> skipped = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("After skipping 3 elements: " + skipped);
    }
}