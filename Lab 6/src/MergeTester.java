/*  Java Class: MergeTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: 4/11/2018
    Description:Tests the union, intersection and subtract from Merge class.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class MergeTester {
    public static void main(String[] args) {
        Integer[] a = {1,2,3,4,5};
        Integer[] b = {3,5,6,7,8};
        System.out.print("Contents of a: ");
        print(a);
        System.out.print("Contents of b: ");
        print(b);
        Merge<Integer> merger = new Merge<>();
        Object[] c = merger.unionMerge(a,b);
        System.out.println("Result of merge: ");
        print(c);
        c = merger.intMerge(a,b);
        System.out.println("Result of intersection: ");
        print(c);
        c = merger.subMerge(a,b);
        System.out.println("Result of subtraction: ");
        print(c);
    }

    static void print(Object[] a){
        for(Object i : a){
            if (i != null) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
