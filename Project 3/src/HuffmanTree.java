/*  Java Class: HuffmanTree.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: A simple class that represents the binary tree produced by the Huffman Coding.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class HuffmanTree {

    private TreeNode root;
    private int size;
    private ArrayList<TreeNode> leaves = new ArrayList<>();

    public HuffmanTree(){
        root = new TreeNode(0);
        size = 1;
    }

    public HuffmanTree(TreeNode t){
        root = t;
        size = 1;
    }

    public void findLeaves(TreeNode curr){
        if (curr.isLeaf()){
            leaves.add(curr);
        }
        if (curr.getLeft() != null){
            findLeaves(curr.getLeft());
        }
        if (curr.getRight() != null){
            findLeaves(curr.getRight());
        }
    }
    public TreeNode find(TreeNode node, Character c){
        if (node.getVal() == c){
            return node;
        }
        if (node.getLeft() != null){
            find(node.getLeft(),c);
        }
        if (node.getRight() != null){
            find(node.getRight(),c);
        }
        return null;
    }

    public TreeNode findCharInLeaves(Character c){
        TreeNode result = null;
        for (TreeNode node : leaves){
            if (node.getVal() == c){
                result = node;
                break;
            }
        }
        return result;
    }

    public void addLeft(TreeNode parent, TreeNode child){
        size++;
        parent.setLeft(child);
        child.setParent(parent);
    }

    public void addRight(TreeNode parent, TreeNode child){
        size++;
        parent.setRight(child);
        child.setParent(parent);
    }

    public String computeBits(){
        findLeaves(root);
        StringBuilder result = new StringBuilder();
        for (TreeNode node : leaves) {
            StringBuilder current = new StringBuilder();
            TreeNode curr = node;
            Character c = curr.getVal();
            while (!curr.equals(root)) {
                TreeNode parent = curr.getParent();
                if (curr.equals(parent.getLeft())) {
                    current.append("0");
                } else if (curr.equals(parent.getRight())) {
                    current.append("1");
                }

                curr = curr.getParent();
            }
            node.setNumBits(current.length());
            StringBuilder temp = new StringBuilder(current);
            node.setBitRep(temp.reverse().toString());
            if (c == '\n'){
                current.append(" n\\");
            }
            else if (c == ' '){
                current.append(" \u2423");
            }
            else{
                current.append(" " + c);
            }

            current.reverse();
            result.append(current + "\n");
        }
        return result.toString();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public ArrayList<TreeNode> getLeaves() {
        return leaves;
    }


    /*      FOR DEBUGGING     */
    public String toString(){
        String result = "";
        Queue<TreeNode> queue = new ArrayBlockingQueue<>(getSize());
        queue.add(root);
        while (queue.size() > 0){
            TreeNode curr = queue.remove();
            if (curr.getVal() != null){
                if (curr.getVal().equals('\n')){
                    result = result + "<" + curr.getKey() + "," + "\\n" + ">" + curr.isLeaf() + " ";
                }
                else if (curr.getVal().equals(' ')){
                    result = result + "<" + curr.getKey() + "," + "\u2423" + ">" + curr.isLeaf() + " ";
                }
                else{
                    result = result + "<" + curr.getKey() + "," + curr.getVal() + ">" + curr.isLeaf() + " ";
                }
            }
            else{
                result = result + "<" + curr.getKey() + "," + curr.getVal() + ">" + curr.isLeaf() + " ";
            }
            if (curr.getLeft() != null){
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null){
                queue.add(curr.getRight());
            }

        }
        return result;
    }
}
