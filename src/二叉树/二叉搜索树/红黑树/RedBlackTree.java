package 二叉树.二叉搜索树.红黑树;

import 二叉树.BinaryNode;
import 二叉树.二叉搜索树.MyBinarySearchTree;

import java.util.Comparator;

enum RedBlackColor {
    RED("Red"),
    BLACK("Black");

    private String name;

    RedBlackColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


public class RedBlackTree<E> extends MyBinarySearchTree<E> {

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(BinaryNode<E> node) {
        super.afterAdd(node);
    }

    @Override
    protected void afterRemove(BinaryNode<E> node) {
        super.afterRemove(node);
    }

    /**
     * 红黑树节点类
     */
    private static class RedBlackTreeNode<E> extends BinaryNode<E> {
        private RedBlackColor color;

        public RedBlackTreeNode(E element) {
            this(element, null, RedBlackColor.RED);
        }

        public RedBlackTreeNode(E element, BinaryNode<E> parent) {
            this(element, parent, RedBlackColor.RED);
        }

        public RedBlackTreeNode(E element, BinaryNode<E> parent, RedBlackColor color) {
            super(element, parent);
            this.color = color;
        }

        /**
         * 染成黑色
         */
        public void blacken() {
            this.color = RedBlackColor.BLACK;
        }

        /**
         * 染成红色
         */
        public void redden() {
            this.color = RedBlackColor.RED;
        }

        /**
         * 是否是红色
         *
         * @return 是否是红色
         */
        private boolean isRed() {
            return this.color == RedBlackColor.RED;
        }

        /**
         * 是否是黑色
         *
         * @return 是否是黑色
         */
        private boolean isBlack() {
            return this.color == RedBlackColor.BLACK;
        }

        /**
         * 获得当前节点的兄弟节点
         *
         * @return 兄弟节点
         */
        public RedBlackTreeNode<E> sibling() {
            if (isLeftChild()) {
                return (RedBlackTreeNode<E>) parent.right;
            } else if (isRightChild()) {
                return (RedBlackTreeNode<E>) parent.left;
            } else {
                return null;
            }
        }
    }
}
