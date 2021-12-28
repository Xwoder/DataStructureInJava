package 队列.循环双端队列;

public class MyCircularQueueTest {
    public static void main(String[] args) {
        MyCircularQueue<Integer> queue = new MyCircularQueue<>();
        for (int i = 10; i <= 100; i += 10) {
            queue.enQueue(i);
        }

        System.out.println(queue);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueue());
        }
        System.out.println(queue);

        for (int i = 110; i <= 150; i += 10) {
            queue.enQueue(i);
        }
        System.out.println(queue);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
        System.out.println(queue);


        for (int i = 1; i <= 10; i++) {
            queue.enQueue(i);
        }

        System.out.println(queue);

        queue.enQueue(11);
        System.out.println(queue);
    }
}
