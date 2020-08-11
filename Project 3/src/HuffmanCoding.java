/*  Java Class: HuffmanCoding.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: Main class for the Huffman Coding algorithm.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.text.DecimalFormat;
import java.util.*;


public class HuffmanCoding {

    private HuffmanTree tree;
    private TreeMap<Character,Integer> frequency;
    private PriorityQueue<HuffmanTree> priq;
    private String bitRep;
    private int numChar;
    private int totalBits;

    public String getBitRep() {
        return bitRep;
    }

    public void setBitRep(String bitRep) {
        if (bitRep.length() > 8){
            StringBuilder temp = new StringBuilder(bitRep);
            for (int i = 8; i < bitRep.length();i+=9){
                temp.insert(i,' ');
            }
            this.bitRep = temp.toString();
        }
        else{
            this.bitRep = bitRep;
        }
    }

    public HuffmanCoding(){
        priq = new PriorityQueue(new NodeComp());
        frequency = new TreeMap<>();
    }

    public String compress(String s){
        setBitRep("");
        numChar = s.length();
        freq(s);
        treeify();
        Set set = frequency.entrySet();
        String result = tree.computeBits();
        result = result + "*****\nNumber of characters: " + numChar + "\nNumber of bits: ";
        String bitRep = "";
        for (Character c : s.toCharArray()){
            TreeNode node = tree.findCharInLeaves(c);
            String bit = node.getBitRep();
            totalBits += node.getNumBits();
            bitRep = bitRep + bit;
        }
        result = result + totalBits;
        setBitRep(bitRep);
        return result;
    }



    public String decompress(String s){
        String result = "";
            HuffmanTree DeCompTree = new HuffmanTree();
            String[] arr = s.split("\n");
            int ref = 0;
            for (int i = 0; i < arr.length; i++){
                if (arr[i].equals("*****")){
                    ref = i + 1;
                    break;
                }
                String[] innerArr = arr[i].split(" ");
                if (innerArr[0].equals("\\n")){
                    innerArr[0] = "\n";
                }
                if (innerArr[0].equals("\u2423")){
                    innerArr[0] = " ";
                }
                String character = innerArr[0];
                String bitPath = innerArr[1];
                TreeNode curr = DeCompTree.getRoot();
                for (int j = 0; j < bitPath.length(); j++){
                    if (bitPath.charAt(j) == '0'){
                        if (curr.getLeft() == null){
                            DeCompTree.addLeft(curr, new TreeNode(0));
                        }
                        curr = curr.getLeft();
                    }
                    if (bitPath.charAt(j) == '1'){
                        if (curr.getRight() == null){
                            DeCompTree.addRight(curr,new TreeNode(1));
                        }
                        curr = curr.getRight();
                    }
                    if (j == bitPath.length()-1){
                        curr.setVal(character.charAt(0));
                    }
                }
            }
            int numChar = 0;
            int numBits = 0;
            String bits = "";
            for (int k = ref; k < arr.length; k++){
                String str = arr[k];
                str = str.replaceAll("[^0-9]+", "");
                if (k == ref){
                    numChar = Integer.parseInt(str);
                }
                if (k == ref+1){
                    numBits = Integer.parseInt(str);
                }
                if (k == arr.length-1){
                    bits = str;
                }
            }
            TreeNode finder = DeCompTree.getRoot();
            for (int i = 0; i < bits.length();i++){
                Character num = bits.charAt(i);
                if (num == '0'){
                    finder = finder.getLeft();
                }
                if (num == '1'){
                    finder = finder.getRight();
                }
                if (finder.isLeaf()){
                    result = result + finder.getVal();
                    finder = DeCompTree.getRoot();
                }
            }

        return result;
    }

    private void freq(String s){
        char[] temp = s.toCharArray();
        Arrays.sort(temp);
        for (char c : temp){
            if (frequency.get(c) != null){
                frequency.put(c,frequency.get(c) + 1);
            }
            frequency.putIfAbsent(c, 1);
        }
    }

    private void treeify(){
        Set set = frequency.entrySet();
        for (Object p : set){
            Map.Entry curr = (Map.Entry)p;
            priq.add(new HuffmanTree(new TreeNode((Integer)curr.getValue(),(Character)curr.getKey())));
        }
        while (priq.size() > 1){
            HuffmanTree T1 = priq.poll();
            HuffmanTree T2 = priq.poll();
            TreeNode T1Root = T1.getRoot();
            TreeNode T2Root = T2.getRoot();
            HuffmanTree T = new HuffmanTree(new TreeNode(T1Root.getKey()+T2Root.getKey(),null));
            T.getRoot().setLeft(T1Root);
            T.getRoot().setRight(T2Root);
            T.setSize(T.getSize() + T1.getSize() + T2.getSize());

            T1Root.setParent(T.getRoot());
            T2Root.setParent(T.getRoot());
            priq.add(T);
        }
        tree = priq.remove();
    }

    private static class NodeComp implements Comparator<HuffmanTree> {

        @Override
        public int compare(HuffmanTree o1, HuffmanTree o2) {
            if (o1.getRoot().getKey() > o2.getRoot().getKey()){
                return 1;
            }
            else if (o1.getRoot().getKey() == o2.getRoot().getKey()){
                return 0;
            }
            else{
                return -1;
            }
        }

    }
    public HuffmanTree getTree() {
        return tree;
    }
    public TreeMap<Character, Integer> getFrequency() {
        return frequency;
    }

    public int getNumChar() {
        return numChar;
    }

    public int getTotalBits() {
        return totalBits;
    }

    public void setTotalBits(int totalBits) {
        this.totalBits = totalBits;
    }

}
