import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        Interval interval = new Interval(a, b);
        interval.check();
    }
}

class Interval {
    int odd;
    int even;

    Interval(int odd, int even) {
        this.odd = odd;
        this.even = even;
    }

    void check() {
        if (odd == 0 && even == 0) {
            System.out.println("NO");
        } else if (Math.abs(odd - even) <= 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}