import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Level2HashSet {

    static class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // المساواة هنا معتمدة على الـ id
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id;
        }

        // الـ hashCode برضه شغال على الـ id عشان الـ HashSet تعرف تقراه صح
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();

        // بنضيف 10 عناصر وفيهم حاجات متكررة في الـ id
        set.add(new Person(1, "Ali"));
        set.add(new Person(1, "Ali"));       // متكرر بنفس الاسم والـ id
        set.add(new Person(2, "Sara"));
        set.add(new Person(2, "Mona"));      // id متكرر بس الاسم مختلف
        set.add(new Person(3, "Omar"));
        set.add(new Person(4, "Ziad"));
        set.add(new Person(5, "Hassan"));
        set.add(new Person(5, "Hassan"));    // متكرر
        set.add(new Person(6, "Nour"));
        set.add(new Person(7, "Khaled"));

        System.out.println("--- Level 2: HashSet ---");
        System.out.println("Total inserted objects: 10");
        // الـ HashSet هتشيل المتكرر على حسب الـ id وهيفضل الحاجات الفريدة بس
        System.out.println("Remaining objects in HashSet: " + set.size());
    }
}