/*  Java Class: HashTester.java
    Author: Jin Choi
    Class: CSCI 230
    Date: Mar 20, 2018
    Description: The main driver to test all the different hashing schemes. Contains menu features to select which hashing scheme and what load factor you would like to use for a specified trial run. Also outputs the six different reports that were required for project.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.io.*;
import java.util.stream.Stream;
import java.util.Scanner;
import java.math.BigInteger;

public class HashTester {
    public static void main(String[] args) throws IOException{
        AbstractHashMap<Integer, String> map = null;
            BufferedReader reader = null;
            try{
                Scanner sc = new Scanner(System.in);
                String fileName = "/home/john/Documents/CSCI230/p1large.txt";
                reader = new BufferedReader(new FileReader(fileName));
                Integer numberEntries;
                String current = "";
                Stream<String> stream = reader.lines();
                Object[] input = stream.toArray();
                numberEntries = Integer.parseInt(input[0].toString()); // first line of file is n.
                double loadFactor = 0;
                Double N = numberEntries.doubleValue();
                boolean loopSwitch = true;
                while(loopSwitch){
                    System.out.println("Choose load factor: \n1) 0.25\n2) 0.5\n3) 0.75\n4) 0.9");
                    int selection = sc.nextInt();
                    switch(selection){
                        case 1: loadFactor = 0.25; N *= 4.0; break;
                        case 2: loadFactor = 0.5; N *= 2.0; break;
                        case 3: loadFactor = 0.75; N *= 4/3.0; break;
                        case 4: loadFactor = 0.9; N *= 10/9.0; break;
                        default: System.out.println("Invalid load factor."); break;
                    }
                    if (selection == 1 || selection == 2 || selection == 3 || selection == 4){ loopSwitch = false; } // turns off the loop of the load factor selection.
                    if (selection == -1){
                        break;
                    }
                }
                BigInteger size = new BigInteger(String.valueOf(N.intValue()));
                size = size.nextProbablePrime();                    // Finds the next available prime number for load factor.
                System.out.println("Select Hashing Scheme: \n1) Chain Hash Map \n2) Probe Hash Map \n3) Double Hash Map\nEnter -1 to Quit.");
                Scanner mapChoice = new Scanner(System.in);
                int mapSelect = 0;
                while(mapChoice.hasNext()){
                    mapSelect = mapChoice.nextInt();
                    if(mapSelect == -1){
                        System.out.println("Terminating Program.");break;
                    }
                    switch(mapSelect){
                        case 1: map = new ChainHashMap<Integer, String>(size.intValue()); break;
                        case 2: map = new ProbeHashMap<Integer, String>(size.intValue()); break;
                        case 3: map = new DoubleHashMap<Integer, String>(size.intValue()); break;
                        default: System.out.println("Invalid Input."); break;
                    }
                    if (mapSelect == 1 || mapSelect == 2 || mapSelect == 3){break;}
                }
                for (int i = 1; i < input.length; i++){
                    current = input[i].toString();
                    current = current.replace(" ","");  // UN/ANNOTATE FOR DIFFERENT FILE!!
                    String[] strArray = current.split("\t");     // EDIT REGEX FOR DIFFERENT FILE!!
                    for(int j = 0; j < strArray.length; j++){
                        String currStr = strArray[j];
                        String currVal = "";
                        char[] temp = currStr.toCharArray();
                        int length = temp.length;
                        for(int k = length-1; k >=0; k--){            //flips the key into the value.
                            currVal += temp[k];
                        }
                        if (!currStr.equals("")){
                            map.put(Integer.parseInt(currStr), currVal);
                        }
                    }
                }
                /*System.out.println(map.entrySet().toString());*/        // for debugging
                System.out.println("Using the file: " + fileName + " with a load factor of " + loadFactor +": ");
                map.generateReport();
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

