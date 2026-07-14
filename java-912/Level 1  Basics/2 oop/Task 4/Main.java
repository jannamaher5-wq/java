import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Age age = new Age(n);
        age.calculate();
        age.display();
    }
}

class Age {
    int days;
    int years;
    int months;
    int remainingDays;

    Age(int days) {
        this.days = days;
    }

    void calculate() {
        years = days / 365;
        days %= 365;
        months = days / 30;
        remainingDays = days % 30;
    }

    void display() {
        System.out.println(years + " years");
        System.out.println(months + " months");
        System.out.println(remainingDays + " days");
    }
}