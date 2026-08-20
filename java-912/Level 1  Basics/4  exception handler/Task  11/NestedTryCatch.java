public class NestedTryCatch {
    public static void main(String[] args) {

        try {
            try {
                int result = 10 / 0;
                System.out.println(result);
            } catch (NullPointerException e) {
                System.out.println("Null Pointer Exception");
            }

        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception caught by outer catch!");
        }
    }
}