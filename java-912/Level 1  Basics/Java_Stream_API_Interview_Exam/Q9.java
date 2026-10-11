import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Q9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6);

        List<Map.Entry<Integer, Long>> top3 = numbers.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()))
            .limit(3)
            .collect(Collectors.toList());

        top3.forEach(e -> System.out.println(e.getKey() + "=" + e.getValue() + " occurrences"));
    }
}