package 栈;

public class MySequentialStackTest {
    public static void main(String[] args) {
        MySequentialStack<Integer> stack = new MySequentialStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack);

        System.out.println(stack.pop());
        stack.push(40);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
