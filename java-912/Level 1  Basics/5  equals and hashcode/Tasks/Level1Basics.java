
public class Level1Basics {
    static class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // هنا بنقارن شخصين بالـ id بس
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id;
        }

        // سيبنا hashCode زي ما هو بتاع الـ Object العادي
        // عشان نشوف الإيرور أو اللخبطة اللي هتصل لما أعمل equals ومعدلش hashCode
    }

    public static void main(String[] args) {
        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ahmed");

        System.out.println("--- Level 1: Basics ---");
        // هيطلع true عشان الـ equals شغال على الـ id
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        
        // هيطلعوا مختلفين عشان الـ hashCode معملنالوش Override
        System.out.println("p1 hashCode: " + p1.hashCode());
        System.out.println("p2 hashCode: " + p2.hashCode());
        System.out.println("Are hashCodes equal? " + (p1.hashCode() == p2.hashCode()));
    }
}