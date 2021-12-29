package 二叉树.二叉搜索树;

import Tools.printer.BinaryTreeInfo;
import 二叉树.AbstractBinaryTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Node<E> {
    E element;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E element) {
        this.element = element;
    }

    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
    }

    /**
     * 是否节点的度为2
     *
     * @return
     */
    public boolean hasTwoChildren() {
        return this.left != null && this.right != null;
    }

    /**
     * 是否叶子节点
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public String toString() {
        return "Node(" + element + ')';
    }
}

public class MyBinarySearchTree<E> extends AbstractBinaryTree implements BinaryTreeInfo<Node<E>> {

    /* 比较器 */
    private final Comparator<E> comparator;
    private int size;
    /* 根节点 */
    private Node root;

    public MyBinarySearchTree() {
        this(null);
    }

    public MyBinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<>(element, null);
        } else {
            Node<E> parent = null;
            Node<E> cur = root;

            int compareResult = 0;
            while (cur != null) {
                parent = cur;
                compareResult = compare(element, cur.element);
                if (compareResult < 0) {
                    cur = cur.left;
                } else if (compareResult > 0) {
                    cur = cur.right;
                } else {
                    System.out.println("已存在该值");
                    return;
                }
            }

            Node<E> newNode = new Node<>(element, parent);
            if (compareResult < 0) {
                parent.left = newNode;
            } else if (compareResult > 0) {
                parent.right = newNode;
            } else {
                System.out.println("已存在该值");
            }
        }
        ++size;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    private int compare(E o1, E o2) {
        if (comparator == null) {
            return ((Comparable) o1).compareTo(o2);
        } else {
            return comparator.compare(o1, o2);
        }
    }

    public boolean contains(E element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element should not be null");
        }
    }

    public int heightNonRecursive() {

        int height = 0;
        if (root == null) {
            return height;
        }


        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize = 1;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
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

    private int heightRecursive(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /* 中序遍历 */
    public void inorderTraversal(Visitor<E> visitor) {

        if (root == null || visitor == null) {
            return;
        }
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> root, Visitor<E> visitor) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, visitor);
        visitor.visit(root.element);
        inorderTraversal(root.right, visitor);
    }

    @Override
    public boolean isComplete() {
        if (root == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean requireLeafNode = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

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
    public Node<E> left(Node<E> node) {
        return node.left;
    }

    public void levelOrderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /* 后续遍历 */
    public void postorderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> root, Visitor<E> visitor) {

        if (root == null) {
            return;
        }

        postorderTraversal(root.left, visitor);
        postorderTraversal(root.right, visitor);
        visitor.visit(root.element);
    }

    /**
     * 获取指定节点的前驱结点
     *
     * @param node 指定节点
     *
     * @return 前驱节点
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }


        return node.parent;
    }

    /* 先序遍历 */
    public void preorderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> root, Visitor<E> visitor) {

        if (root == null) {
            return;
        }

        visitor.visit(root.element);
        preorderTraversal(root.left, visitor);
        preorderTraversal(root.right, visitor);
    }

    public void remove(E element) {

    }

    @Override
    public Node<E> right(Node<E> node) {
        return node.right;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    public int size() {
        return size;
    }

    @Override
    public Object string(Node<E> node) {
        E parentElement = (node.parent == null) ? null : node.parent.element;
        return node.element + "_p(" + parentElement + ')';
    }

    /**
     * 获取指定节点的后继结点
     *
     * @param node 指定节点
     *
     * @return 后继节点
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    public static abstract class Visitor<E> {
        abstract void visit(E element);
    }
}
