package Backtracking;

public class EightQueens4 {

    private final int numberOfQueens;

    private final int[] queens;

    /* 列是否被占用 */
    private final boolean[] colsOccupied;

    /* 自左向右的对角线是否被占用 */
    private final boolean[] leftTopOccupied;

    /* 自右向左的对角线是否被占用 */
    private final boolean[] rightTopOccupied;

    public EightQueens4(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        this.queens = new int[numberOfQueens];
        this.colsOccupied = new boolean[numberOfQueens];
        int lengthOfSlash = (numberOfQueens << 1) + 1;
        this.leftTopOccupied = new boolean[lengthOfSlash];
        this.rightTopOccupied = new boolean[lengthOfSlash];
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
            int leftTopSlashIndex = row - col + numberOfQueens - 1;
            int rightTopSlashIndex = row + col;

            if (colsOccupied[col]
                    || leftTopOccupied[leftTopSlashIndex]
                    || rightTopOccupied[rightTopSlashIndex]) {
                continue;
            }

            queens[row] = col;

            colsOccupied[col] = true;
            leftTopOccupied[leftTopSlashIndex] = true;
            rightTopOccupied[rightTopSlashIndex] = true;

            placeQueenAtRow(row + 1);

            colsOccupied[col] = false;
            leftTopOccupied[leftTopSlashIndex] = false;
            rightTopOccupied[rightTopSlashIndex] = false;

        }
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
