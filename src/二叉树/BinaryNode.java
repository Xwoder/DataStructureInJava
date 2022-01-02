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

}
