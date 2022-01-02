package 二叉树.二叉搜索树.AVL树;

import 二叉树.BinaryNode;
import 二叉树.二叉搜索树.MyBinarySearchTree;

import java.util.Comparator;

public class AVLTree1<E> extends MyBinarySearchTree<E> {

    public AVLTree1(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree1() {
        super(null);
    }

    @Override
    protected void afterAdd(BinaryNode<E> node) {
        super.afterAdd(node);
        AVLTreeNode<E> avlNode = (AVLTreeNode<E>) node;
        while (avlNode != null) {
            if (isBalanced(avlNode)) {
                /*
                 * 当前节点是平衡的，则更新高度
                 */
                avlNode.updateHeight();
                avlNode = (AVLTreeNode<E>) avlNode.parent;
            } else {
                updateBalance(avlNode);
                break;
            }
        }
    }

    @Override
    protected BinaryNode<E> createNode(E element, BinaryNode<E> parent) {
        return new AVLTreeNode<>(element, parent);
    }

    private boolean isBalanced(AVLTreeNode<E> node) {
        return Math.abs(node.balanceFactor()) <= 1;
    }

    private void rotateLeft(AVLTreeNode<E> grand) {
        AVLTreeNode<E> parent = (AVLTreeNode<E>) grand.right;
        AVLTreeNode<E> child = (AVLTreeNode<E>) parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(parent, grand, child);
    }

    private void rotateRight(AVLTreeNode<E> grand) {
        AVLTreeNode<E> parent = (AVLTreeNode<E>) grand.left;
        AVLTreeNode<E> transferred = (AVLTreeNode<E>) parent.right;
        grand.left = transferred;
        parent.right = grand;

        afterRotate(parent, grand, transferred);
    }

    private void afterRotate(AVLTreeNode<E> parent, AVLTreeNode<E> grand, AVLTreeNode<E> transferred) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        if (transferred != null) {
            transferred.parent = grand;
        }

        grand.parent = parent;

        grand.updateHeight();
        parent.updateHeight();
    }


    @Override
    public Object string(BinaryNode<E> node) {
        AVLTreeNode<E> avlNode = (AVLTreeNode<E>) node;
        E parentElement = (node.parent == null) ? null : node.parent.element;
        return node.element + "_p(" + parentElement + ')' + "_h(" + avlNode.height + ')';
    }

    private void updateBalance(AVLTreeNode<E> grand) {
        AVLTreeNode<E> parent = grand.tallerChild();
        AVLTreeNode<E> child = parent.tallerChild();

        if (parent.isLeftChild()) {
            /* L */
            if (child.isLeftChild()) {
                /* LL */
                rotateRight(grand);
            } else {
                /* LR */
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            /* R */
            if (child.isRightChild()) {
                /* RR */
                rotateLeft(grand);
            } else {
                /* RL */
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    protected static class AVLTreeNode<E> extends BinaryNode<E> {

        protected int height;

        public AVLTreeNode(E element) {
            this(element, null);
        }

        public AVLTreeNode(E element, BinaryNode<E> parent) {
            super(element, parent);
            /* 新创建的节点默认高度必然为0 */
            height = 1;
        }

        public int balanceFactor() {
            int leftHeight = (left == null) ? 0 : ((AVLTreeNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLTreeNode<E>) right).height;

            return leftHeight - rightHeight;
        }



        public AVLTreeNode<E> tallerChild() {
            int leftHeight = (left == null) ? 0 : ((AVLTreeNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLTreeNode<E>) right).height;

            if (leftHeight > rightHeight) {
                return (AVLTreeNode<E>) left;
            } else if (leftHeight < rightHeight) {
                return (AVLTreeNode<E>) right;
            }

            if (this.isLeftChild()) {
                return (AVLTreeNode<E>) left;
            } else {
                return (AVLTreeNode<E>) right;
            }
        }

        public void updateHeight() {
            int leftHeight = (left == null) ? 0 : ((AVLTreeNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLTreeNode<E>) right).height;

            height = 1 + Math.max(leftHeight, rightHeight);
        }
    }
}

