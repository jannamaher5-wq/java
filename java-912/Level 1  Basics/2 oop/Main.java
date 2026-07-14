import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        if (input.hasNextInt()) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            
            NumberFinder finder = new NumberFinder(a, b, c);
            finder.printMinAndMax();
        }
        
        input.close();
    }
}

class NumberFinder {
    private int num1;
    private int num2;
    private int num3;

    public NumberFinder(int num1, int num2, int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public void printMinAndMax() {
        
        int min = Math.min(num1, Math.min(num2, num3));
        
        int max = Math.max(num1, Math.max(num2, num3));
        
           System.out.println(min + " " + max);
    }
}