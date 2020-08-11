import java.util.Arrays;
import java.util.Collections;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class TestingEnvironment {
    public static void main(String[] args) {
        int num[] = {10,10,10,4,5,6,7,8,9,10};
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num2[] = new int[n];
        int num3;

        System.out.println("The size of num2 array: " + num2.length);
        int counter = 0;
        char letters[] = {'a','b','c'};
        int size = num.length;
        String s = "aweifjo;awejifio;awef";
        char c = 'c';
        System.out.println("While Loop:\n");
        while (counter < size){
            if (counter == size - 1){
                System.out.println(num[counter]);
            }
            else{
                System.out.print(num[counter] + ", ");
            }
            counter++;
        }
        System.out.println("\nFor Loop:\n");
        for (int i = 0; i < num.length; i++){
            if (i == num.length-1){
                System.out.print(num[i]);
            }else{
                System.out.print(num[i] + ", ");
            }
        }
        System.out.println("\nFor Each Loop:\n");
        for (int number : num){
            if (number == num[num.length-1]){
                System.out.print(number);
            }else{
                System.out.print(number + ", ");
            }
        }
        System.out.println();
        for (int ch : letters){
            System.out.print(ch + " ");
        }
    }
public static int factorial(int n){
        if (n == 1){ return 1;}
        return n * factorial(n-1);
        }
        }
