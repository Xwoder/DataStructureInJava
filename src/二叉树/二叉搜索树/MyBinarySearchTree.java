package 二叉树.二叉搜索树;

import 二叉树.AbstractBinaryTree;
import 二叉树.BinaryNode;

import java.util.Comparator;

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


    private int compare(E o1, E o2) {
        if (comparator == null) {
            return ((Comparable) o1).compareTo(o2);
        } else {
            return comparator.compare(o1, o2);
        }
    }


    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element should not be null");
        }
    }


    protected BinaryNode<E> node(E element) {
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


    @Override
    protected void remove(E element) {
        remove(node(element));
    }

    private void remove(BinaryNode<E> node) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            /*
             * 如果是叶子节点，则直接删除该节点
             */
            if (node.parent.left == node /* 若待删除的节点在父节点的左子树上 */) {
                node.parent.left = null;
            } else if (node.parent.right == node /* 若待删除的节点在父节点的右子树上 */) {
                node.parent.right = null;
            } else {
                // 该分支中的代码不可能被执行
                return;
            }
        } else if (node.left == null || node.right == null) {
            /*
             * 仅有一个子节点
             */
            BinaryNode<E> parent = node.parent;

            if (parent == null) {
                /* 是根节点 */
                if (root.left != null) {
                    root = node.left;
                } else if (root.right != null) {
                    root = node.right;
                } else {
                    throw new RuntimeException("本条件分支不应该被执行");
                }
                root.parent = null;
            } else {
                if (node.left == null) {
                    if (parent.left == node) {
                        parent.left = node.right;
                    } else if (parent.right == node) {
                        parent.right = node.right;
                    } else {
                        throw new RuntimeException("本条件分支不应该被执行");
                    }
                    node.right.parent = parent;
                } else if (node.right == null) {
                    if (parent.left == node) {
                        parent.left = node.left;
                    } else if (parent.right == node) {
                        parent.right = node.left;
                    } else {
                        // 该分支中的代码不可能被执行
                        throw new RuntimeException("本条件分支不应该被执行");
                    }
                    node.left.parent = parent;
                }
            }

        } else if (node.hasTwoChildren()) {
            BinaryNode<E> s = successor(node);
            node.element = s.element;
            remove(s);
        } else {
            throw new RuntimeException("本条件分支不应该被执行");
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


}
