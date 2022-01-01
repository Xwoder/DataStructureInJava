package 二叉树;

public class BinaryNode<E> {

    public E element;
    public BinaryNode<E> parent;
    public BinaryNode<E> left;
    public BinaryNode<E> right;

    public BinaryNode(E element) {
        this.element = element;
    }

    public BinaryNode(E element, BinaryNode<E> parent) {
        this.element = element;
        this.parent = parent;
    }


    /**
     * 是否节点的度为2
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
