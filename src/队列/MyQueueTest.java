package 队列;

public class MyQueueTest {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 1; i <= 10; i++) {
            queue.enQueue(i * 10);
        }

        while (!queue.isEmpty()) {
            var element = queue.deQueue();
            System.out.println(element);
        }
    }
}
