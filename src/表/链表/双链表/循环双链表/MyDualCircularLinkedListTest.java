package 表.链表.双链表.循环双链表;

public class MyDualCircularLinkedListTest {
    public static void main(String[] args) {
        MyDualCircularLinkedList<Integer> list = new MyDualCircularLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println(list);

        list.add(0, 0);
        System.out.println(list);

        list.remove(0);
        System.out.println(list);
        list.remove(list.size() - 1);
        System.out.println(list);

        list.remove(1);
        System.out.println(list);
    }
}
