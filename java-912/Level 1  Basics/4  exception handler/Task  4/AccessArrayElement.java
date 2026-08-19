import java.util.Scanner;

public class AccessArrayElement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] numbers = {10, 20, 30, 40, 50};

        try {
            System.out.print("Enter index: ");
            int index = input.nextInt();

            System.out.println("Value = " + numbers[index]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index must be between 0 and 4.");
        }

        input.close();
    }
}
