package 集合;

public class MyListSetTest {
    public static void main(String[] args) {
        MySet<Integer> listSet = new MyListSet<>();
        listSet.add(10);
        listSet.add(20);
        listSet.add(30);
        listSet.add(20);
        listSet.add(10);

        listSet.traversal(new MySet.Visitor<Integer>() {
            @Override
            void visit(Integer element) {
                System.out.println(element);
            }
        });
    }
}
