package Backtracking;

public class EightQueensDemo {

    public static void main(String[] args) {

        int numberOfQueens = 8;

        EightQueens1 queens = new EightQueens1(numberOfQueens);
        queens.run();

        EightQueens2 queens2 = new EightQueens2(numberOfQueens);
        queens2.run();

        EightQueens3 queens3 = new EightQueens3(numberOfQueens);
        queens3.run();

        EightQueens4 queens4 = new EightQueens4(numberOfQueens);
        queens4.run();

    }

}
