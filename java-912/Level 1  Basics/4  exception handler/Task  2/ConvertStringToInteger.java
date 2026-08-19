import java.util.Scanner;

public class ConvertStringToInteger {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            String text = input.nextLine();

            int number = Integer.parseInt(text);

            System.out.println("Number = " + number);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        }

        input.close();
    }
}
