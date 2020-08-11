/*  Java Class: StrArrLinearSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: June 11, 2018
    Description: Linear time sorting algorithm for 3 String arrays. Iterates through each array, incrementing when the lowest value amongst the three is found. This allows for one time traversal of each array which would produce linear time  O (3n) ~ O(n) where n is the size of each array.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class StrArrLinearSort {
    public static void main(String[] args) {
        String[] a = {"john","laura","victor"};
        System.out.print("Names in first array: ");
        print(a);
        String[] b = {"dom","jade","shelby"};
        System.out.print("\nNames in second array: ");
        print(b);
        String[] c = {"ben","holland","tom"};
        System.out.print("\nNames in third array: ");
        print(c);
        String[] result = sort(a,b,c);
        System.out.println("\n\nFinal sorted array: ");
        print(result);
    }

    public static void print(String[] a){
        for (String s : a){
            System.out.print(s + " ");
        }
    }
    public static String[] sort(String[] a, String[] b, String[] c){
        String[] result = new String[a.length + b.length + c.length];
        int index = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while (index < result.length){
            if (index == result.length-1){
                if (i < a.length){
                    result[index] = a[i];
                }
                else if (j < b.length){
                    result[index] = b[j];
                }
                else{
                    result[index] = c[k];
                }
                break;
            }
            if (i < a.length){
                if (j < b.length){
                    if (a[i].compareTo(b[j]) < 0){
                        if (k < c.length){
                            if (a[i].compareTo(c[k]) < 0){
                                result[index] = a[i];
                                index++;
                                i++;
                            }
                            else{
                                result[index] = c[k];
                                index++;
                                k++;
                            }

                        }
                    }
                    else{
                        if (k < c.length){
                            if (b[j].compareTo(c[k]) < 0){
                                result[index] = b[j];
                                index++;
                                j++;
                            }
                            else{
                                result[index] = c[k];
                                index++;
                                k++;
                            }

                        }
                    }

                }
                else if (k < c.length){
                    if (a[i].compareTo(c[k]) < 0){
                        result[index] = a[i];
                        index++;
                        i++;
                    }
                    else{
                        result[index] = c[k];
                        index++;
                        k++;
                    }
                }
                else{
                    for (int p = index; p < result.length; p++){
                        result[p] = a[i];
                        i++;
                    }
                }

            }
            else if (j < b.length){
                if (k < c.length){
                    if (b[j].compareTo(c[k]) < 0){
                        result[index] = b[j];
                        index++;
                        j++;
                    }
                    else{
                        result[index] = c[k];
                        index++;
                        k++;
                    }
                }
                else{
                    for (int p = index; p < result.length; p++){
                        result[p] = b[j];
                        j++;
                    }
                }
            }else{
                if (k < c.length){
                    for (int p = index; p < result.length; p++){
                        result[p] = c[k];
                        k++;
                    }
                }
            }
        }
        return result;
    }
}
