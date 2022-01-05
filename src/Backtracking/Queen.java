package Backtracking;

public class Queen {

    public int col;
    public int row;

    public Queen(int row) {
        this.row = row;
        this.col = -1;
    }

    public Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ')';
    }

    public boolean isInStraightLine(Queen q) {
        return Math.abs((this.row - q.row)) == Math.abs((this.col - q.col));
    }
}
