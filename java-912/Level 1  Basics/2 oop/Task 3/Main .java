import java.util.Scanner;
 class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();

        String[] parts = input.split("(?<=[-+*/])|(?=[-+*/])");

        int num1 = Integer.parseInt(parts[0]);
        char op = parts[1].charAt(0);
        int num2 = Integer.parseInt(parts[2]);

        Calculator calc = new Calculator(num1, op, num2);
        System.out.println(calc.getResult());
        
        sc.close();
    }
}

class Calculator {
    private int a;
    private char op;
    private int b;

    public Calculator(int num1, char operation, int num2) {
        a = num1;
        op = operation;
        b = num2;
    }

    public int getResult() {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }
}