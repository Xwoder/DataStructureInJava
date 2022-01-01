package 二叉树.二叉搜索树.AVL树;

import Tools.printer.BinaryTrees;

public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Integer[] nums = new Integer[]{48, 23, 42, 98, 86, 58,};

        for (Integer num : nums) {
            System.out.println(num + "--------------");
            avlTree.add(num);
            System.out.println("添加完");
            BinaryTrees.println(avlTree);
        }

    }

}

