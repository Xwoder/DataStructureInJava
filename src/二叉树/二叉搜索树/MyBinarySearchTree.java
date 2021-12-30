package 二叉树.二叉搜索树;

import 二叉树.AbstractBinaryTree;
import 二叉树.BinaryNode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class MyBinarySearchTree<E> extends AbstractBinaryTree<E> {

    /* 比较器 */
    private final Comparator<E> comparator;


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
            root = new BinaryNode<>(element, null);
        } else {
            BinaryNode<E> parent = null;
            BinaryNode<E> cur = root;

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

            BinaryNode<E> newNode = new BinaryNode<>(element, parent);
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
        return node(element) != null;
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
    public void inorderTraversal(Visitor<E> visitor) {

        if (root == null || visitor == null) {
            return;
        }
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(BinaryNode<E> root, Visitor<E> visitor) {
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


    private BinaryNode<E> node(E element) {
        BinaryNode<E> cur = root;
        while (cur != null) {
            int compareResult = compare(element, cur.element);
            if (compareResult < 0) {
                cur = cur.left;
            } else if (compareResult > 0) {
                cur = cur.right;
            } else {
                return cur;
            }
        }

        return null;
    }

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

    /**
     * 获取指定节点的前驱结点
     *
     * @param node 指定节点
     *
     * @return 前驱节点
     */
    protected BinaryNode<E> predecessor(BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        BinaryNode<E> p = node.left;
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

    private void preorderTraversal(BinaryNode<E> root, Visitor<E> visitor) {

        if (root == null) {
            return;
        }

        visitor.visit(root.element);
        preorderTraversal(root.left, visitor);
        preorderTraversal(root.right, visitor);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(BinaryNode<E> node) {
        if (node == null) {
            return;
        }

        BinaryNode<E> nodeToBeDeleted = node;

        if (nodeToBeDeleted.hasTwoChildren()) {
            BinaryNode<E> predecessor = predecessor(nodeToBeDeleted);
            nodeToBeDeleted.element = predecessor.element;

            nodeToBeDeleted = predecessor;
        }

        if (nodeToBeDeleted.isLeaf()) {
            BinaryNode<E> parent = nodeToBeDeleted.parent;
            if (parent == null) {
                root = null;
            } else {
                if (parent.left == nodeToBeDeleted) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else {
            if (nodeToBeDeleted.parent == null) {
                root = nodeToBeDeleted.left != null ? node.right : node.left;
            } else {
                if (nodeToBeDeleted.left == null) {
                    nodeToBeDeleted.element = nodeToBeDeleted.right.element;
                    nodeToBeDeleted.right = null;
                } else {
                    nodeToBeDeleted.element = nodeToBeDeleted.left.element;
                    nodeToBeDeleted.left = null;
                }
            }
        }

        --size;
    }


    /**
     * 获取指定节点的后继结点
     *
     * @param node 指定节点
     *
     * @return 后继节点
     */
    protected BinaryNode<E> successor(BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        BinaryNode<E> p = node.right;
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
