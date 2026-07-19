import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextLong();
        long m = scanner.nextLong();
        long k = scanner.nextLong();

        Katryoshka katryoshka = new Katryoshka(n, m, k);
        katryoshka.calculate();
    }
}

class Katryoshka {
    long eyes;
    long mouths;
    long bodies;

    Katryoshka(long eyes, long mouths, long bodies) {
        this.eyes = eyes;
        this.mouths = mouths;
        this.bodies = bodies;
    }

    void calculate() {
        long result = Math.min(eyes, Math.min(mouths, bodies));

        eyes -= result;
        mouths -= result;
        bodies -= result;

        result += Math.min(eyes / 2, bodies);

        System.out.println(result);
    }
}