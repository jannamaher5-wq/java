import java.util.Scanner;

public class LuckyNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        LuckyNumber lucky = new LuckyNumber(n);
        lucky.check();
    }
}

class LuckyNumber {
    int n;

    LuckyNumber(int n) {
        this.n = n;
    }

    void check() {
        int first = n / 10;
        int second = n % 10;

        if ((second != 0 && first % second == 0) ||
            (first != 0 && second % first == 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
