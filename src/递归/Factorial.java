package 递归;

public class Factorial {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();

        System.out.println(factorial.factorial(4));
    }

    public int factorial(int n) {
        int product = n;

        return factorial(n - 1, product);
    }

    private int factorial(int n, int product) {
        if (n == 1) {
            return product;
        }
        product *= n;
        return factorial(n - 1, product);
    }
}
