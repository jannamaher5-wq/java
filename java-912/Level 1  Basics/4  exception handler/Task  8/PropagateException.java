public class PropagateException {

    public static void divide() throws ArithmeticException {

        int number = 10;
        int zero = 0;

        int result = number / zero;

        System.out.println(result);
    }

    public static void calculate() throws ArithmeticException {
        divide();
    }

    public static void main(String[] args) {

        try {
            calculate();

        } catch (ArithmeticException e) {
            System.out.println("Exception propagated to main.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
ال Propagation معناه تسلسل نقل المشكلة الميثود اللي مش جاهزة تعالج الخطأ بتسيبه للي فوقيها يتعامل معاه
الميثود بتفضل تطلع للميثود الى فوق لو لاقت ميثود فيها try و cach المشكله هتتحل والكود يكمل عادى ولو ملاقتش هتفضل المشكله مكمله لغايه متوصل ال main ويحصل crash
