package Backtracking;

public class EightQueens3 {

    private int numberOfQueens;

    private int[] queens;

    public EightQueens3(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        this.queens = new int[numberOfQueens];
    }

    public void run() {
        placeQueenAtRow(0);
    }

    private void placeQueenAtRow(int row) {
        if (row >= numberOfQueens) {
            displayCoordinate();
            displayChessboard();
            return;
        }

        for (int col = 0; col < numberOfQueens; col++) {
            if (isValid(row, col)) {
                queens[row] = col;
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
        for (int queenRow = 0; queenRow < row; queenRow++) {
            int queenCol = queens[queenRow];
            if (queenCol < 0) {
                continue;
            }

            if (queenCol == col) {
                return false;
            }

            boolean isInStraightLine = (Math.abs(queenCol - col) == (row - queenRow));
            if (isInStraightLine) {
                return false;
            }
        }
        return true;
    }

    private void displayCoordinate() {
        StringBuilder sb = new StringBuilder();
        int queenRow = 0;
        for (; queenRow < numberOfQueens - 1; queenRow++) {
            int queenCol = queens[queenRow];
            sb.append("(").append(queenRow).append(", ").append(queenCol).append("), ");
        }

        int queenCol = queens[queenRow];
        sb.append("(").append(queenRow).append(", ").append(queenCol).append(")");
        System.out.println(sb);
    }

    private void displayChessboard() {
        for (int queenRow = 0; queenRow < numberOfQueens; queenRow++) {
            int queenCol = queens[queenRow];
            for (int col = 0; col < numberOfQueens; col++) {
                System.out.print(" " + (col == queenCol ? 1 : 0));
            }
            System.out.println();
        }
    }
}
