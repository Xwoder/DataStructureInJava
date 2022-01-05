package Backtracking;

import java.util.HashSet;
import java.util.Set;

public class NQueens {
    protected int numberOfQueens;
    protected Queen[] queens;

    public NQueens(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        this.queens = new Queen[numberOfQueens];
        for (int i = 0; i < numberOfQueens; i++) {
            queens[i] = new Queen(i);
        }
    }

    public void display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfQueens - 1; i++) {
            Queen queen = queens[i];
            sb.append(queen.toString()).append(", ");
        }

        Queen lastQueen = queens[numberOfQueens - 1];
        sb.append(lastQueen);
        System.out.println(sb);
    }

    protected boolean isFeasible() {
        Set<Integer> set = new HashSet<>();
        for (Queen queen : queens) {
            set.add(queen.col);
        }

        if (set.size() != numberOfQueens) {
            return false;
        }

        for (int i = 0; i < queens.length; i++) {
            Queen q1 = queens[i];
            for (int j = 0; j < queens.length && i != j; j++) {

                Queen q2 = queens[j];
                if (q1.isInStraightLine(q2)) {
                    return false;
                }
            }
        }
        return true;
    }
}
