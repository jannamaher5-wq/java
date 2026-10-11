import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 20, 20, 8, 15, 10);
        Integer secondHighest = numbers.stream()
            .distinct()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst()
            .orElse(null);

        System.out.println("Second Highest Number: " + secondHighest);
    }
}