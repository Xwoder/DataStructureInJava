package 递归;

/**
 * 斐波那契数列计算的尾递归实现
 */
public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci(3));
    }

    public int fibonacci(int n) {
        return fibonacci(n, 1, 1);
    }

    private int fibonacci(int n, int first, int second) {
        System.out.printf("%d, %d, %d\n", n, first, second);
        if (n <= 1) {
            return first;
        }
        return fibonacci(--n, second, first + second);
    }
}
