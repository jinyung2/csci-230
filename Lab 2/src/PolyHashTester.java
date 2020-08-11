/*  Java Class: PolyHashTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 12, 2018
    Description: The test driver for the Poly Hash method of converting a String to an integer to be used as a hashcode. Test cases used at 0, 37, 40, 41.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.io.*;

public class PolyHashTester {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        PolyHash hashThirtySeven = new PolyHash();
        PolyHash hashForty = new PolyHash();
        PolyHash hashFortyOne = new PolyHash();
        PolyHash hashZero = new PolyHash();
        try {
            reader = new BufferedReader(new FileReader("/home/john/Documents/CSCI230/usdeclarPC.txt"));
            String curLine;
            while((curLine = reader.readLine()) != null){
                curLine = curLine.toLowerCase();
                curLine = curLine.trim();
                String[] holder = curLine.split(" ");
                for (int i = 0; i < holder.length; i++){
                    holder[i] = holder[i].replaceAll("\\W","");
                    hashThirtySeven.PolyAdd(holder[i],37);
                    hashForty.PolyAdd(holder[i],40);
                    hashFortyOne.PolyAdd(holder[i], 41);
                    hashZero.PolyAdd(holder[i], 0);
                }
            }
            System.out.println("Using a = 37: ");
            hashThirtySeven.debug();
            System.out.println("Using a = 40: ");
            hashForty.debug();
            System.out.println("Using a = 41: ");
            hashFortyOne.debug();
            System.out.println("Using a = 0: ");
            hashZero.debug();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                reader.close();
            }
        }

    }
}
