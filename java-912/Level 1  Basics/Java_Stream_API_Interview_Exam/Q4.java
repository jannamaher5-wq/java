import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Q4 {
    public static void main(String[] args) {
        String sentence = "Java Spring Java Hibernate Spring Java";
        Map<String, Long> wordCount = Arrays.stream(sentence.split("\\s+"))
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        wordCount.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}