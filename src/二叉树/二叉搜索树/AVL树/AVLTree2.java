package 二叉树.二叉搜索树.AVL树;

import 二叉树.BinaryNode;
import 二叉树.二叉搜索树.MyBinarySearchTree;

import java.util.Comparator;

public class AVLTree2<E> extends MyBinarySearchTree<E> {

    public AVLTree2(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree2() {
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
    protected void afterRemove(BinaryNode<E> node) {
        super.afterRemove(node);
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

    private void rotate(AVLTreeNode<E> root, /* 子树的根节点 */
                        AVLTreeNode<E> a, AVLTreeNode<E> b, AVLTreeNode<E> c,
                        AVLTreeNode<E> d,
                        AVLTreeNode<E> e, AVLTreeNode<E> f, AVLTreeNode<E> g) {
        d.parent = root.parent;
        if (root.isLeftChild()) {
            root.parent.left = d;
        } else if (root.isRightChild()) {
            root.parent.right = d;
        } else {
            this.root = d;
        }

        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        b.updateHeight();

        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.updateHeight();


        d.left = b;
        if (b != null) {
            b.parent = d;
        }

        d.right = f;
        if (f != null) {
            f.parent = d;
        }

        d.updateHeight();

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
                rotate(grand, (AVLTreeNode<E>) child.left, child, (AVLTreeNode<E>) child.right, parent, (AVLTreeNode<E>) parent.right, grand, (AVLTreeNode<E>) grand.right);
            } else {
                /* LR */
                rotate(grand, (AVLTreeNode<E>) parent.left, parent, (AVLTreeNode<E>) child.left, child, (AVLTreeNode<E>) child.right, grand, (AVLTreeNode<E>) grand.right);
            }
        } else {
            /* R */
            if (child.isRightChild()) {
                /* RR */
                rotate(grand, (AVLTreeNode<E>) grand.left, grand, (AVLTreeNode<E>) parent.left, parent, (AVLTreeNode<E>) child.left, child, (AVLTreeNode<E>) child.right);
            } else {
                /* RL */
                rotate(grand, (AVLTreeNode<E>) grand.left, grand, (AVLTreeNode<E>) child.left, child, (AVLTreeNode<E>) child.right, parent, (AVLTreeNode<E>) parent.right);
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


        /**
         * 是否父节点的左子树
         *
         * @return 是否父节点的左子树
         */
        public boolean isLeftChild() {
            return this.parent != null && this.parent.left == this;
        }

        /**
         * 是否父节点的右子树
         *
         * @return 是否父节点的右子树
         */
        public boolean isRightChild() {
            return this.parent != null && this.parent.right == this;
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

