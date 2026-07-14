import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long l1 = scanner.nextLong();
        long r1 = scanner.nextLong();
        long l2 = scanner.nextLong();
        long r2 = scanner.nextLong();

        Interval interval = new Interval(l1, r1, l2, r2);
        interval.intersection();
    }
}

class Interval {
    long l1, r1, l2, r2;

    Interval(long l1, long r1, long l2, long r2) {
        this.l1 = l1;
        this.r1 = r1;
        this.l2 = l2;
        this.r2 = r2;
    }

    void intersection() {
        long start = Math.max(l1, l2);
        long end = Math.min(r1, r2);

        if (start <= end) {
            System.out.println(start + " " + end);
        } else {
            System.out.println(-1);
        }
    }
}