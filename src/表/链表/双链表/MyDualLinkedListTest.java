package 表.链表.双链表;

public class MyDualLinkedListTest {

    public static void main(String[] args) {
        // List.LinkedList.DualLinkedList.MyDualLinkedList<Integer> linkedList = new List.LinkedList.DualLinkedList.MyDualLinkedList<>();
        //
        // linkedList.add(10);
        // System.out.println(linkedList);
        // linkedList.add(20);
        // System.out.println(linkedList);
        // linkedList.add(0, 0);
        // System.out.println(linkedList);
        // linkedList.add(30);
        // System.out.println(linkedList);
        //
        // linkedList.add(3,25);
        // System.out.println(linkedList);
        //
        // linkedList.remove(4);
        // System.out.println(linkedList);

        MyDualLinkedList<Integer> linkedList = new MyDualLinkedList<>();
        linkedList.add(10);
        System.out.println(linkedList);
        // 1个元素，删除
        linkedList.remove(0);
        System.out.println(linkedList);
        // 2个元素，删除第0个元素
        linkedList.add(10);
        linkedList.add(20);
        System.out.println(linkedList);
        linkedList.remove(1);
        System.out.println(linkedList);



        // linkedList.add(1, 15);
        //
        // System.out.println(linkedList);
        //
        // System.out.println(linkedList.indexOf(40));
        //
        // System.out.println(linkedList.remove(linkedList.size - 1));
        // System.out.println(linkedList);
        //
        // System.out.println(linkedList.indexOf(null));
    }
}
