package 二叉树;

import Tools.printer.BinaryTreeInfo;


public abstract class AbstractBinaryTree<E> implements BinaryTreeInfo<BinaryNode<E>> {


    protected int size;
    /* 根节点 */
    protected BinaryNode<E> root;

    public abstract boolean isComplete();

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public BinaryNode<E> left(BinaryNode<E> node) {
        return node.left;
    }

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


}
