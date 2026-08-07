import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<>();

        pq.add("Banana");
        pq.add("Apple");
        pq.add("Cherry");

        System.out.println(pq.peek());

        pq.remove("Banana");
        pq.add("Durian");

        pq.poll();

        System.out.println(pq);
    }
}