import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        sc.useDelimiter("(?<=[+\\-*/])|(?=[+\\-*/])|\\s+");
        
        if (sc.hasNextInt()) {
            int a = sc.nextInt();
            char op = sc.next().charAt(0);
            int b = sc.nextInt();

            Calculator calc = new Calculator(a, op, b);
            System.out.println(calc.calculate());
        }
        sc.close();
    }
}

class Calculator {
    private int a, b;
    private char op;

    public Calculator(int a, char op, int b) {
        this.a = a;
        this.op = op;
        this.b = b;
    }

    public int calculate() {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: return 0;
        }
    }
}