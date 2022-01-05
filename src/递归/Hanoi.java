package 递归;

public class Hanoi {
    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        String p1 = "A";
        String p2 = "B";
        String p3 = "C";
        hanoi.hanoi(3, p1, p2, p3);
    }


    /**
     * 汉诺塔
     * <p>
     * 将位于柱子p1上的n个盘子，搬运到柱子p3上
     *
     * @param n  盘子的数目
     * @param p1 第1根柱子
     * @param p2 第2根柱子
     * @param p3 第3根柱子
     */
    public void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }

        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);
    }

    private void move(int n, String from, String to) {
        System.out.println(n + ": " + from + "-->" + to);
    }
}
