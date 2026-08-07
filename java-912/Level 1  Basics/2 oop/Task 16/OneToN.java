import java.util.Scanner;

public class OneToN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Numbers numbers = new Numbers(n);
        numbers.printNumbers();
    }
}

class Numbers {
    int n;

    Numbers(int n) {
        this.n = n;
    }

    void printNumbers() {
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }
}