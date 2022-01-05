package Backtracking;

public class EightQueens2 extends NQueens {

    public EightQueens2(int numberOfQueens) {
        super(numberOfQueens);
    }

    public void run() {
        placeQueenAtRow(0);
    }

    private void placeQueenAtRow(int row) {
        if (row >= numberOfQueens) {
            if (isFeasible()) {
                display();
            }

            return;
        }

        for (int col = 0; col < numberOfQueens; col++) {
            if (isValid(row, col)) {
                queens[row].col = col;
                placeQueenAtRow(row + 1);
            }
        }
    }

    /**
     * 第 row 行第 col 列是否可以摆放皇后
     *
     * @param row 行索引
     * @param col 列索引
     * @return 是否可以摆放
     */
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            Queen queen = queens[i];
            if (queen.col < 0) {
                continue;
            }

            if (queen.col == col) {
                return false;
            }

            boolean isInStraightLine = Math.abs(queen.col - col) == Math.abs(queen.row - row);
            if (isInStraightLine) {
                return false;
            }
        }
        return true;
    }
}
