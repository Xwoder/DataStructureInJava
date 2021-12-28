package 表.链表.双链表.循环双链表;

public class MySingleCircularLinkedListTest {
    public static void main(String[] args) {
        MySingleCircularLinkedList<Integer> circularLinkedList = new MySingleCircularLinkedList<>();
        circularLinkedList.add(10);
        circularLinkedList.add(20);
        circularLinkedList.add(30);
        System.out.println(circularLinkedList);
        circularLinkedList.add(1, 15);
        System.out.println(circularLinkedList);
        circularLinkedList.add(2, 25);
        System.out.println(circularLinkedList);
        circularLinkedList.remove(0);
        System.out.println(circularLinkedList);
        circularLinkedList.remove(circularLinkedList.size() - 1);
        System.out.println(circularLinkedList);
    }
}
