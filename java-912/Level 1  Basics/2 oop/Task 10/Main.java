import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long c = scanner.nextLong();
        long d = scanner.nextLong();

        Puzzle puzzle = new Puzzle(a, b, c, d);
        puzzle.check();
    }
}

class Puzzle {
    long a, b, c, d;

    Puzzle(long a, long b, long c, long d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    void check() {
        if (a + b - c == d ||
            a - b + c == d ||
            a + b * c == d ||
            a * b + c == d ||
            a - b * c == d ||
            a * b - c == d) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}