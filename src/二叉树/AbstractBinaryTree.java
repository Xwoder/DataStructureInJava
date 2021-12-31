package 二叉树;

import Tools.printer.BinaryTreeInfo;
import 二叉树.二叉搜索树.MyBinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;


public abstract class AbstractBinaryTree<E> implements BinaryTreeInfo<BinaryNode<E>> {


    protected int size;
    /* 根节点 */
    protected BinaryNode<E> root;

    protected abstract void add(E element);

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    public int heightNonRecursive() {

        int height = 0;
        if (root == null) {
            return height;
        }


        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize = 1;
        while (!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();
            --levelSize;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                ++height;
            }
        }

        return height;
    }

    /**
     * 高度，递归实现
     *
     * @return 高度
     */
    public int heightRecursive() {
        return heightRecursive(root);
    }

    private int heightRecursive(BinaryNode<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /* 中序遍历 */
    public void inorderTraversal(MyBinarySearchTree.Visitor<E> visitor) {

        if (root == null || visitor == null) {
            return;
        }
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(BinaryNode<E> root, MyBinarySearchTree.Visitor<E> visitor) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, visitor);
        visitor.visit(root.element);
        inorderTraversal(root.right, visitor);
    }

    public boolean isComplete() {
        if (root == null) {
            return false;
        }

        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean requireLeafNode = false;
        while (!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();

            if (requireLeafNode && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }


            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // 要求后续节点是叶子节点
                requireLeafNode = true;
            }
        }
        return true;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public BinaryNode<E> left(BinaryNode<E> node) {
        return node.left;
    }

    public void levelOrderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }

        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    protected abstract BinaryNode<E> node(E element);

    /* 后续遍历 */
    public void postorderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(BinaryNode<E> root, Visitor<E> visitor) {

        if (root == null) {
            return;
        }

        postorderTraversal(root.left, visitor);
        postorderTraversal(root.right, visitor);
        visitor.visit(root.element);
    }

    /* 先序遍历 */
    public void preorderTraversal(MyBinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(BinaryNode<E> root, MyBinarySearchTree.Visitor<E> visitor) {

        if (root == null) {
            return;
        }

        visitor.visit(root.element);
        preorderTraversal(root.left, visitor);
        preorderTraversal(root.right, visitor);
    }

    protected abstract void remove(E element);

    @Override
    public BinaryNode<E> right(BinaryNode<E> node) {
        return node.right;
    }

    @Override
    public BinaryNode<E> root() {
        return root;
    }

    public int size() {
        return size;
    }

    @Override
    public Object string(BinaryNode<E> node) {
        E parentElement = (node.parent == null) ? null : node.parent.element;
        return node.element + "_p(" + parentElement + ')';
    }

    public static abstract class Visitor<E> {
        public abstract void visit(E element);
    }
}
