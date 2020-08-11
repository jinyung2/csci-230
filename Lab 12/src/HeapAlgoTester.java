/*  Java Class: HeapAlgoTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 30, 2018
    Description: The tester class containing static methods for worst fit and first fit heap memory management algorithms.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Random;

public class HeapAlgoTester {
    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());
        int[] heap = new int[1024];
        int i = 0;
        int count = 0;
        while (count <= 512){
            int n = rand.nextInt(16) + 5;
            count += n;
            for (int j = 0; j < n; j++){
                if ((i + n) > 1023 || heap[i + n] != 0){
                  break;
                }
                else{
                    heap[i+j] = 1;
                }
            }
            i = rand.nextInt(1024);
        }
        print(heap);
        System.out.println("\nAvailable blocks of memory in heap: " + available(heap));
        System.out.println("\nRunning worst fit: ");
        int[] clone = heap.clone();
        while (true){
            int n = rand.nextInt(16) + 5;
            int[] temp = worstFit(clone,n);
            if (temp != null){
                clone = temp;
            }else{
                break;
            }
        }
        System.out.println("Contents of heap after: ");
        print(clone);
        System.out.println("\nAvailable blocks of memory in heap: " + available(clone));
        System.out.println("\nRunning first fit: ");
        clone = heap.clone();
        while (true){
            int n = rand.nextInt(16) + 5;
            int[] result = firstFit(clone,n);
            if (result != null){
                clone = result;
            }
            else{
                break;
            }
        }
        System.out.println("Contents of heap after: ");
        print(clone);
        System.out.println("\nAvailable blocks of memory in heap: " + available(clone));
    }

    public static int[] worstFit(int[] heap, int size){
        int[] temp = heap;
        int greatest = 0;
        int i = 0;
        int result = 0;
        int finalIndex = 0;
        while(i < 1024){
            if (temp[i] != 0){
                i++;
            }
            else{
                result = computeCont(temp,i);
                if (Math.max(greatest,result) == result){
                    greatest = result;
                    finalIndex = i;
                }
                i += result;
            }
        }
        if (greatest < size){ return null;}
        else if (finalIndex + size < 1023){
            for (int j = 0; j < size; j++){
                temp[finalIndex+j] = 1;
            }
            return temp;
        }
        else{
            return null;
        }
    }

    public static int[] firstFit(int[] heap, int size){
        int[] temp = heap;
        int i = 0;
        while(i < 1024){
            while (temp[i] != 0){
                i++;
            }
            int result = computeCont(temp,i);
            if(result > size && i + size < 1024){
                for (int j = 0; j < size; j++){
                    temp[i+j] = 1;
                }
                return temp;
            }
            else{
                i += result;
            }
        }
        return null;
    }

    public static int computeCont(int[] heap, int index){
        int count = 0;
        while (index != 1024 && heap[index] == 0){
            count++;
            index++;
        }
        return count;
    }

    public static void print(int[] heap){
        for (int k = 0; k < heap.length;k++){
            System.out.print(heap[k] + " ");
        }
    }

    public static int available(int[] heap){
        int count = 0;
        for (int i : heap){
            if (i == 0){
                count++;
            }
        }
        return count;
    }
}
