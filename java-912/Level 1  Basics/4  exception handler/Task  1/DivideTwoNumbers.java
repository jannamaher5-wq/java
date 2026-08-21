import java.util.Scanner;

public class DivideTwoNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter first integer: ");
            int num1 = input.nextInt();

            System.out.print("Enter second integer: ");
            int num2 = input.nextInt();

            int result = num1 / num2;

            System.out.println("Result = " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        }

        input.close();
    }
}
الكمبيوتر لو قسمنا على صفر هيرميلى ArithmeticException 
    فايدة الـ try-catch هنا إن البرنامج ميفصلش في وش المستخدم فجأة Crash
    ويطلع للمستخدم رساله توضحله انو ينفعش نقسم على صفر
