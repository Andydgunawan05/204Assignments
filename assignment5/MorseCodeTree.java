/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: builds and manages a binary tree used to convert morse code to english
 * Due: 12/02/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
    private TreeNode<String> root; // root of the morse code tree

    // initializes the tree by calling buildTree
    public MorseCodeTree() {
        buildTree();
    }

    @Override
    public TreeNode<String> getRoot() {
        return root;
    }

    @Override
    public void setRoot(TreeNode<String> newNode) {
        this.root = newNode;
    }

    @Override
    public void insert(String code, String letter) {
        if (root == null) {
            root = new TreeNode<>("");
        }
        addNode(root, code, letter);
    }

    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {
        if (code.length() == 1) {
            if (code.equals(".")) {
                root.setLeftChild(new TreeNode<>(letter));
            } else if (code.equals("-")) {
                root.setRightChild(new TreeNode<>(letter));
            }
        } else {
            TreeNode<String> nextNode = code.charAt(0) == '.' ? root.getLeftChild() : root.getRightChild();
            if (nextNode == null) {
                nextNode = new TreeNode<>("");
                if (code.charAt(0) == '.') {
                    root.setLeftChild(nextNode);
                } else {
                    root.setRightChild(nextNode);
                }
            }
            addNode(nextNode, code.substring(1), letter);
        }
    }

    @Override
    public String fetch(String code) {
        return fetchNode(root, code);
    }

    @Override
    public String fetchNode(TreeNode<String> root, String code) {
        if (code.length() == 0) {
            return root.getData();
        } else if (code.charAt(0) == '.') {
            return fetchNode(root.getLeftChild(), code.substring(1));
        } else {
            return fetchNode(root.getRightChild(), code.substring(1));
        }
    }

    @Override
    public LinkedConverterTreeInterface<String> delete(String data) {
        throw new UnsupportedOperationException("Delete the operation that is not supported in MorseCodeTree");
    }

    @Override
    public LinkedConverterTreeInterface<String> update() {
        throw new UnsupportedOperationException("Update the operation that is not supported in MorseCodeTree");
    }

    @Override
    public void buildTree() {
        root = new TreeNode<>("");
        // level 1
        insert(".", "e");
        insert("-", "t");
        // level 2
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        // level 3
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        // level 4
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> result = new ArrayList<>();
        LNRoutputTraversal(root, result);
        return result;
    }

    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if (root != null) {
            LNRoutputTraversal(root.getLeftChild(), list);
            list.add(root.getData());
            LNRoutputTraversal(root.getRightChild(), list);
        }
    }
}
