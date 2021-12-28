package Tools.printer;

public interface BinaryTreeInfo<E> {
	/**
	 * who is the root node
	 */
	E root();
	/**
	 * how to get the left child of the node
	 */
	E left(E node);
	/**
	 * how to get the right child of the node
	 */
	E right(E node);
	/**
	 * how to print the node
	 */
	Object string(E node);
}
