package Backtracking;

public class EightQueens1 extends NQueens {

    public EightQueens1(int numberOfQueens) {
        super(numberOfQueens);
    }

    public void run() {
        placeQueen(0);
    }

    private void placeQueen(int k) {
        if (k >= numberOfQueens) {
            if (isFeasible()) {
                display();
            }
            return;
        }

        for (int col = 0; col < numberOfQueens; col++) {
            queens[k].col = col;
            placeQueen(k + 1);
        }
    }
}
