import java.util.*;

public class Q5 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java", "SpringBoot", "Microservices", "Hibernate", "Docker");
        String longestWord = words.stream()
            .reduce((w1, w2) -> w1.length() >= w2.length() ? w1 : w2)
            .orElse(null);

        System.out.println("Longest String: " + longestWord);
    }
}