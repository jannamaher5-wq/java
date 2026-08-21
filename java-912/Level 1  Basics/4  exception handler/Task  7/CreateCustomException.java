import java.util.Scanner;

class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CreateCustomException {

    public static void checkAge(int age) throws InvalidAgeException {

        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        }

        System.out.println("You are allowed.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter your age: ");
            int age = input.nextInt();

            checkAge(age);

        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        input.close();
    }
}
انا مش مجبر أعتمد بس على إيرورز الجافا الجاهزة أقدر اعمل الإيرور بتاعي
(InvalidAgeException) عشان يخدم قواعد البزنس بتاعتي (زي: السن أقل من 18 سنة ممنوع يدخل


    
