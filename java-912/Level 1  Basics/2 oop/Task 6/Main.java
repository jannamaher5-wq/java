import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();

        Power power = new Power(a, b, c, d);
        power.compare();
    }
}

class Power {
    double a, b, c, d;

    Power(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    void compare() {
        if (b * Math.log(a) > d * Math.log(c)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}