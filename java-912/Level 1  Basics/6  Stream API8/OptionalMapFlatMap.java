import java.util.*;
import java.util.stream.Collectors;
//كلمة Flat يعني حاجة مسطحة ومفرودة.. لو عندك لستة جوه لستة ومقرفاك، الـ FlatMap بتدخل تفرد ده كله وتخليه خط واحد ماشي مستقيم وسهل تتعامل معاه
public class OptionalMapFlatMap {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        // القوائم المتداخلة (List inside List) بنفردها ونخليها قائمة واحدة بسيطة باستخدام flatMap مع دالة التحويل Collection::stream
        List<String> flatList = nestedWords.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened List: " + flatList);

        // بنفرد الكلمات القائمة وبعدين بنقسم كل كلمة لحروف بالـ split وبنفرد الحروف برضه بـ flatMap وفي الآخر بنلغي التكرارات بـ distinct
        List<String> uniqueChars = nestedWords.stream()
                .flatMap(Collection::stream)
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique Characters: " + uniqueChars);

        // لما بنتعامل مع قائمة من نوع Optional، بنفلتر القيم اللي موجودة فعلاً بـ isPresent وبعدين بنستخرج القيمة نفسها بـ get
        List<Optional<String>> optionals = Arrays.asList(Optional.of("Java"), Optional.empty(), Optional.of("Stream"));
        List<String> presentValues = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Non-empty Optionals: " + presentValues);

        // بنفلتر أي اسم null وبعدين بنستخدم دالة map عشان نحول كل نص من القائمة إلى رقمه اللي بيمثل طول الكلمة (String::length)
        List<Integer> nameLengths = names.stream()
                .filter(Objects::nonNull)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Lengths of Names: " + nameLengths);

        // بنختار الأسماء اللي مش null واللي بتبدأ بحرف A ونحولها كلها لكابيتال عن طريق map ونجمعهم في List جديدة
        List<String> upperA = names.stream()
                .filter(s -> s != null && s.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Upper 'A' Names: " + upperA);
    }
}