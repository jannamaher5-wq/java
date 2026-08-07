import java.util.Scanner;
 
public class MaxAndMin {
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
        // حساب أصغر رقم بين الثلاثة
        int min = Math.min(num1, Math.min(num2, num3));
        
        // حساب أكبر رقم بين الثلاثة
        int max = Math.max(num1, Math.max(num2, num3));
        
        // طباعة الناتج بالشكل المطلوب تماماً: الأصغر ثم الأكبر وبينهما مسافة
        System.out.println(min + " " + max);
    }
}