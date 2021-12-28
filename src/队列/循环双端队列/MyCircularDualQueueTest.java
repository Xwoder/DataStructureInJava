package 队列.循环双端队列;

public class MyCircularDualQueueTest {

    public static void main(String[] args) {
        MyCircularDualQueue<Integer> queue = new MyCircularDualQueue<>();

        for (int i = 1; i <= 5; i++) {
            queue.enQueueRear(i * 10);
        }
        System.out.println(queue);

        for (int i = 1; i <= 5; i++) {
            queue.enQueueFront(i * 100);
        }
        System.out.println(queue);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueueRear());
        }
        System.out.println(queue);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueueFront());
        }
        System.out.println(queue);
    }
}
