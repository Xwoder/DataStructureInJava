package Backtracking;

public class FourQueens extends NQueens {

    public FourQueens() {
        super(4);
    }

    public void run() {
        Queen q1 = queens[0];
        Queen q2 = queens[1];
        Queen q3 = queens[2];
        Queen q4 = queens[3];

        for (q1.col = 0; q1.col < numberOfQueens; q1.col++) {
            for (q2.col = 0; q2.col < numberOfQueens; q2.col++) {
                for (q3.col = 0; q3.col < numberOfQueens; q3.col++) {
                    for (q4.col = 0; q4.col < numberOfQueens; q4.col++) {
                        if (isFeasible()) {
                            display();
                        }
                    }
                }
            }
        }
    }


}
