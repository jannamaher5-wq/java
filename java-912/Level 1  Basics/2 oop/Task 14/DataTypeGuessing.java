import java.util.Scanner;

public class DataTypeGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextLong();
        long k = scanner.nextLong();
        long a = scanner.nextLong();

        DataType dataType = new DataType(n, k, a);
        dataType.check();
    }
}

class DataType {
    long n;
    long k;
    long a;

    DataType(long n, long k, long a) {
        this.n = n;
        this.k = k;
        this.a = a;
    }

    void check() {
        long product = n * k;

        if (product % a != 0) {
            System.out.println("double");
        } else {
            long result = product / a;

            if (result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE) {
                System.out.println("int");
            } else {
                System.out.println("long long");
            }
        }
    }
}
