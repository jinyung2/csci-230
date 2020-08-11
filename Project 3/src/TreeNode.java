/*  Java Class: TreeNode.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: The Node within the Huffman Tree. Used a separate class as opposed to nested classes like those used in the book for,in my opinion, cleaner code management.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Queue;

public class TreeNode {

    private Integer key;
    private Character val;
    private TreeNode parent, left, right;
    private String bitRep;
    private int numBits;

    public TreeNode(Integer key){
        this.key = key;
        this.val = null;
        parent = null;
        left = null;
        right = null;
    }

    public TreeNode(Integer key, Character val){
        this.key = key;
        this.val = val;
        parent = null;
        left = null;
        right = null;
    }

    public boolean isLeaf() { return val != null; }

    public TreeNode getParent() { return parent; }

    public void setParent(TreeNode parent) { this.parent = parent; }

    public TreeNode getLeft() { return left; }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() { return right; }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Integer getKey() { return key; }

    public void setKey(Integer key) { this.key = key; }

    public Character getVal() { return val; }

    public void setVal(Character val) { this.val = val; }

    public String toString(TreeNode t){
        StringBuilder result = new StringBuilder();
        result.append("<" + t.getKey() + "," + t.getVal()+ ">");

        return result.toString();
    }

    public String getBitRep() {
        return bitRep;
    }

    public void setBitRep(String bitRep) {
        this.bitRep = bitRep;
    }

    public int getNumBits() {
        return numBits;
    }

    public void setNumBits(int numBits) {
        this.numBits = numBits;
    }

}
