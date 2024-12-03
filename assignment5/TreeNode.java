/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: generic TreeNode class for a binary tree
 * Due: 12/02/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

public class TreeNode<T> {
    private T data; // node data
    private TreeNode<T> leftChild; // left child
    private TreeNode<T> rightChild; // right child

    /**
     * creates a new TreeNode with data
     * left and right child are set to null
     */
    public TreeNode(T dataNode) {
        this.data = dataNode;
        this.leftChild = null;
        this.rightChild = null;
    }

    //creates a copy of another TreeNode
    public TreeNode(TreeNode<T> node) {
        this.data = node.getData();
        this.leftChild = (node.getLeftChild() != null) ? new TreeNode<>(node.getLeftChild()) : null;
        this.rightChild = (node.getRightChild() != null) ? new TreeNode<>(node.getRightChild()) : null;
    }

    //getter for the node's data
    public T getData() {
        return this.data;
    }

    //getter for the left child
    public TreeNode<T> getLeftChild() {
        return this.leftChild;
    }

    //setter for the left child
    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    //getter for the right child
    public TreeNode<T> getRightChild() {
        return this.rightChild;
    }

    //setter for the right child
    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
