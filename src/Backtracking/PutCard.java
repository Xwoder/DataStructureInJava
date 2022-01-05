package Backtracking;

import java.util.Arrays;

public class PutCard {

    private int[] boxes;

    private int numberOfBoxes;

    public PutCard(int numberOfBoxes) {
        this.numberOfBoxes = numberOfBoxes;
        this.boxes = new int[numberOfBoxes];
    }

    public void run() {
        pubCardInBox(0);
    }

    /**
     * 在编号为 boxNo 的盒子里放卡片
     * @param boxNo 盒子的编号
     */
    private void pubCardInBox(int boxNo) {
        if (boxNo >= numberOfBoxes) {
            System.out.println(Arrays.toString(boxes));
            return;
        }

        for (int card = 1; card <= numberOfBoxes; card++) {
            boxes[boxNo] = card;
            pubCardInBox(boxNo + 1);
        }
    }
}
