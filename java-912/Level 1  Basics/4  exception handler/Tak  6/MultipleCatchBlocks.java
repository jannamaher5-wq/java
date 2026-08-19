public class MultipleCatchBlocks {
    public static void main(String[] args) {

        String text = null;
        int number = 10;
        int zero = 0;

        try {
            System.out.println(text.toUpperCase());

            int result = number / zero;
            System.out.println(result);

        } catch (NullPointerException e) {
            System.out.println("Error: String is null.");

        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        }
    }
}
