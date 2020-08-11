/*  Java Class: PartitionTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: 4/11/2018
    Description: Tester for the partition class. Creates 4 clusters and performs union operations, and calls find method to test if the correct value is returned (the leader).

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class PartitionTester {
    public static void main(String[] args) {
        Partition<Integer> partition = new Partition<>();
        System.out.println("Create initial cluster of Integer 1: ");
        Position<Integer> onePos = partition.makeCluster(1);
        System.out.println("\nUnion on successive clusters ranging from 2 to 64: ");
        for (int i = 2; i < 65; i++){
            partition.union(onePos,partition.makeCluster(i));
        }
        System.out.println("\nRunning the find method to compute the time: ");
        /*partition.setCounter(0);*/
        System.out.print("The leader element is: " + partition.find(onePos).getElement() + " and the number of operations for 64 data entries is: " + partition.getCounter());
    }
}
