import java.util.Scanner;

public class WinterSale {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x = scanner.nextDouble();
        double p = scanner.nextDouble();

        Discount discount = new Discount(x, p);
        discount.calculate();
    }
}

class Discount {
    double x;
    double p;

    Discount(double x, double p) {
        this.x = x;
        this.p = p;
    }

    void calculate() {
        double originalPrice = (p * 100) / (100 - x);
        System.out.printf("%.2f%n", originalPrice);
    }
}
