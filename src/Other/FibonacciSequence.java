package Other;

import Tools.tools.Times;

public class FibonacciSequence {
    /*
     * 下面的数列被称为斐波那契数列
     * 0, 1, 1, 2, 3, 5, 8, 13, ...
     *
     * 该数列第0项为0，
     * 第1项为1；
     * 第2项为第0项与第1项的和；
     * 第3项为第1项与第2项的和；
     * 以此类推
     * */
    public static void main(String[] args) {

        int n = 45;

        Times.test("fib_recursive", new Times.Task() {
            @Override
            public void execute() {
                fib_recursive(n);
            }
        });

        Times.test("fib_array", new Times.Task() {
            @Override
            public void execute() {
                fib_array(n);
            }
        });

        Times.test("fib_cursor", new Times.Task() {
            @Override
            public void execute() {
                fib_cursor(n);
            }
        });

        Times.test("fib_cursor_2", new Times.Task() {
            @Override
            public void execute() {
                fib_cursor_2(n);
            }
        });
    }

    /*
     * 计算斐波那契数列
     *
     * 递归实现
     * */
    public static int fib_recursive(int n) {
        if (n <= 1) {
            return n;
        }

        final int num1 = fib_recursive(n - 1);
        final int num2 = fib_recursive(n - 2);
        return num1 + num2;
    }

    /*
     * 计算斐波那契数列
     *
     * 数组实现
     * */
    public static int fib_array(int n) {
        if (n <= 1) {
            return n;
        }

        int[] fibArray = new int[n + 1];
        fibArray[0] = 0;
        fibArray[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }

        return fibArray[n];
    }

    /*
     * 计算斐波那契数列
     *
     * 游标实现
     * */
    public static int fib_cursor(int n) {
        if (n <= 1) {
            return n;
        }

        int fib_1 = 0;
        int fib_2 = 1;

        for (int i = 2; i <= n; i++) {
            final int sum = fib_1 + fib_2;
            fib_1 = fib_2;
            fib_2 = sum;
        }

        return fib_2;
    }

    public static int fib_cursor_2(int n) {
        if (n <= 1) {
            return n;
        }

        int fib_1 = 0;
        int fib_2 = 1;

        for (int i = 2; i <= n; i++) {
            fib_2 += fib_1;
            fib_1 = fib_2 - fib_1;
        }

        return fib_2;
    }
}
