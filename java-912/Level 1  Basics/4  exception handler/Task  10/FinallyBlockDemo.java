public class FinallyBlockDemo {
    public static void main(String[] args) {

        try {
            System.out.println("Inside try.");

            int result = 10 / 0;

            System.out.println(result);

        } catch (ArithmeticException e) {
            System.out.println("Inside catch.");
            System.out.println("Error: Cannot divide by zero.");

        } finally {
            System.out.println("Inside finally.");
            System.out.println("Finally always executes.");
        }
    }
}
