package 二叉树.二叉搜索树.AVL树;

import Tools.printer.BinaryTrees;

public class AVLTreeTest {

    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        AVLTree2<Integer> avlTree2 = new AVLTree2<>();
        Integer[] nums = new Integer[]{89, 28, 10, 2, 58, 97, 71, 22, 96};

        for (Integer num : nums) {
            System.out.println("添加元素：" + num);
            avlTree2.add(num);
        }

        BinaryTrees.println(avlTree2);
    }

    private static void test1() {
        AVLTree1<Integer> avlTree1 = new AVLTree1<>();
        Integer[] nums = new Integer[]{58, 23, 100, 3, 74, 25, 49, 7, 86, 84, 24, 78, 60, 35, 37};

        for (Integer num : nums) {
            System.out.println(num + "--------------");
            avlTree1.add(num);
            System.out.println("添加完");
            BinaryTrees.println(avlTree1);
        }
    }

}

