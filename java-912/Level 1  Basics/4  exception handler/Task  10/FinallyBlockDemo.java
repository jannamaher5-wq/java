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
بلوك finally بيتنفذ في كل الحالات سواء الكود مشي سليم، أو ضرب إيرور وراح للـ catch
بنستخدمه في تقفيل الحاجات المهمة زي قفل الملفات أو الداتابيز عشان متفضلش مفتوحة في الميموري
