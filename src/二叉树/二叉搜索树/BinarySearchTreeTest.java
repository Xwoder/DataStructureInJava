package 二叉树.二叉搜索树;

import 公共类.Person;
import Tools.printer.BinaryTrees;
import 公共类.Visitor;

import java.util.Comparator;
import java.util.Random;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        {
            MyBinarySearchTree<Integer> bst1 = new MyBinarySearchTree(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            bst1.add(20);
            bst1.add(10);
            bst1.add(50);
            bst1.add(30);
            bst1.add(40);

            System.out.println(bst1);
        }

        {
            MyBinarySearchTree<Integer> bst2 = new MyBinarySearchTree<>();

            bst2.add(20);
            bst2.add(10);
            bst2.add(50);
            bst2.add(30);
            bst2.add(40);

            System.out.println(bst2);
        }

        {

            Random random = new Random(47);
            MyBinarySearchTree<Person> bst3 = new MyBinarySearchTree<>();

            for (int i = 1; i <= 10; i++) {
                bst3.add(new Person(random.nextInt(10, 50)));
            }

            System.out.println(bst3);
        }

        {

            Random random = new Random(47);
            MyBinarySearchTree<Integer> bst4 = new MyBinarySearchTree<>();

            for (int i = 1; i <= 10; i++) {
                bst4.add(random.nextInt(10));
            }

            BinaryTrees.println(bst4);

            bst4.preorderTraversal(new Visitor<Integer>() {
                @Override
                public void visit(Integer node) {
                    System.out.print(node + ", ");
                }
            });
            System.out.println();

            bst4.inorderTraversal(new Visitor<Integer>() {
                @Override
                public void visit(Integer node) {
                    System.out.print(node + ", ");
                }
            });
            System.out.println();

            bst4.postorderTraversal(new Visitor<Integer>() {
                @Override
                public void visit(Integer node) {
                    System.out.print(node + ", ");
                }
            });
            System.out.println();

            bst4.levelOrderTraversal(new Visitor<Integer>() {
                @Override
                public void visit(Integer node) {
                    System.out.print(node + ", ");
                }
            });
            System.out.println();
        }

        {
            Random random = new Random(47);
            MyBinarySearchTree<Integer> bst5 = new MyBinarySearchTree<>();

            for (int i = 1; i <= 10; i++) {
                bst5.add(random.nextInt(10));
            }

            System.out.println(bst5.heightRecursive());
            System.out.println(bst5.heightNonRecursive());
        }

        {
            MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();
            Integer[] nums = new Integer[]{7, 2, 9, 1};

            for (Integer num : nums) {
                bst.add(num);
                BinaryTrees.println(bst);
            }

            BinaryTrees.println(bst);
            System.out.println(bst.isComplete());
        }


    }
}
