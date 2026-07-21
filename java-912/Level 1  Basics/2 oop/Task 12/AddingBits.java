import java.util.Scanner;

public class AddingBits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long a = scanner.nextLong();
        long b = scanner.nextLong();

        Adder adder = new Adder(a, b);
        adder.add();
    }
}

class Adder {
    long a;
    long b;

    Adder(long a, long b) {
        this.a = a;
        this.b = b;
    }

    void add() {
        System.out.println(a ^ b);
    }
}
