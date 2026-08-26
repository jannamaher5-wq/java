import java.util.*;

public class Level4RealWorld {

    // 1. سيناريو المنتج: التساوي بالـ code بس
    static class Product {
        String code;
        double price;

        public Product(String code, double price) {
            this.code = code;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(code, product.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }

    // 2. سيناريو الطالب: التساوي بالـ id
    static class Student {
        int id;
        String email;

        public Student(int id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    // 3. سيناريو العربية: حاطين رقم اللوحة final عشان يفضل ثابت وما يعملش مشكلة في الـ HashMap
    static class Car {
        private final String plateNumber;
        private String color;

        public Car(String plateNumber, String color) {
            this.plateNumber = plateNumber;
            this.color = color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Car car = (Car) o;
            return Objects.equals(plateNumber, car.plateNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(plateNumber);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Level 4: Real-world Scenarios ---");

        // تجربة المنتجات: كود متكرر بس سعر مختلف
        Set<Product> products = new HashSet<>();
        products.add(new Product("P100", 25.0));
        products.add(new Product("P100", 40.0));
        System.out.println("Product HashSet Size: " + products.size()); // هيرجع 1

        // تجربة الطلاب: id متكرر وإيميل مختلف
        Set<Student> students = new HashSet<>();
        students.add(new Student(1, "a@test.com"));
        students.add(new Student(1, "b@test.com"));
        System.out.println("Student HashSet Size: " + students.size()); // هيرجع 1

        // تجربة العربية في הـ HashMap
        Map<Car, String> carMap = new HashMap<>();
        Car car = new Car("ABC-123", "Red");
        carMap.put(car, "Active Service");

        // لو غيرنا اللون (حاجة مش داخلة في الـ hashCode) القراءة من الـ Map هتفضل شغال عادي
        car.setColor("Blue");
        System.out.println("Car retrieval after changing color: " + carMap.get(car));
    }
}