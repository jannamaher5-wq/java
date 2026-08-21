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
الـ null يعني "ولا حاجه"، ومينفعش تطلب من "ولا حاجه" إنه ينفذ أمر زي toUpperCase() وساعتها هيطلعلى NullPointerException
     فهمت إني لازم أأمّن نفسي وأتأكد إن المتغير مش فاضي قبل ما أطلب منه أي عملية
