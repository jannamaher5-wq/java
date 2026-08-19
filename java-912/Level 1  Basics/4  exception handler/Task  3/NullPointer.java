public class NullPointer {
    
    public static void printUpperCase(String text) {
        System.out.println(text.toUpperCase());
    }

    public static void main(String[] args) {

        String text = null;

        try {
            printUpperCase(text);

        } catch (NullPointerException e) {
            System.out.println("Error: String is null.");
        }
    }
}
