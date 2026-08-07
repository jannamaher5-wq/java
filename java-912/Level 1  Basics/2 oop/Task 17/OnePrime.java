import java.util.Scanner;

public class OnePrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();

        Prime prime = new Prime(x);
        prime.checkPrime();
    }
}

class Prime {
    int x;

    Prime(int x) {
        this.x = x;
    }

    void checkPrime() {
        boolean isPrime = true;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}