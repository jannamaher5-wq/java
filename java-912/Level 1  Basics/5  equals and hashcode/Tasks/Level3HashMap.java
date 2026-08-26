import java.util.*;

public class Level3HashMap {

    static class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // المساواة معتمدة على الـ id والاسم مع بعض
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();

        Person key1 = new Person(101, "Karim");
        map.put(key1, "Employee");

        // بنضيف كي تاني بنفس البيانات بالضبط عشان نشوف هيعمل update ولا هيضيفه من جديد
        Person key2 = new Person(101, "Karim");
        map.put(key2, "Manager");

        System.out.println("--- Level 3: HashMap ---");
        System.out.println("Map size: " + map.size()); // المفروض يفضل 1
        System.out.println("Value of key1: " + map.get(key1)); // القيمة بقت Manager

        // بنحاول نجيب القيمة باستخدام أوبجكت جديد بس نفس البيانات
        Person lookupKey = new Person(101, "Karim");
        System.out.println("Retrieval with new identical object: " + map.get(lookupKey));

        // هنا بنعدل في الـ Key بعد ما حطيناه في الـ Map عشان نشوف مشكلة الـ Mutable Key
        key1.id = 202;
        // هيطلع null عشان الـ hashCode اتغير والـ Map مش عارفة تلاقيه في مكانه القديم
        System.out.println("Retrieval after modifying key's ID: " + map.get(key1));
    }
}