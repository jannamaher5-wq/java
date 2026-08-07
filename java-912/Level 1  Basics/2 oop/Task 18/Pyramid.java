import java.util.Scanner;

public class Pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Triangle triangle = new Triangle(n);
        triangle.printTriangle();
    }
}

class Triangle {
    int n;

    Triangle(int n) {
        this.n = n;
    }

    void printTriangle() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}