package 表.链表.单链表;

public class MySingleLinkedListTest {

    public static void main(String[] args) {
        MySingleLinkedList<Integer> linkedList = new MySingleLinkedList<>();

        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(0, 0);
        linkedList.add(30);

        linkedList.add(1, 15);

        System.out.println(linkedList);

        System.out.println(linkedList.indexOf(40));

        System.out.println(linkedList.remove(linkedList.size() - 1));
        System.out.println(linkedList);

        System.out.println(linkedList.indexOf(null));
    }
}
