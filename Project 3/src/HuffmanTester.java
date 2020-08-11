/*  Java Class: HuffmanTester.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: The driver class for the Huffman Coding. Implemented the improved huffman coding extra credit by outputting moneyOut without the bit representation and instead storing it as bytes within the moneyCompress.dat file.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.io.*;
import java.util.stream.Stream;

public class HuffmanTester {
    public static void main(String[] args) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/home/john/Documents/CSCI230/result.txt",true))) {
            FileReader moneyIn = new FileReader("/home/john/Documents/CSCI230/moneyIn.txt");
            FileReader moneyOut = new FileReader("/home/john/Documents/CSCI230/moneyOut.txt");
            String money = toStringB(moneyIn).toString();
            HuffmanCoding huffman = new HuffmanCoding();
            String test = huffman.compress(money);
            String compressionText = "The result of compression: \n";
            System.out.println(compressionText + test + "\n");
            writer.append(compressionText + test);
            writer.newLine();
            try(BufferedOutputStream datWriter = new BufferedOutputStream(new FileOutputStream("/home/john/Documents/CSCI230/moneyCompress.dat"))){
                String[] bytes = huffman.getBitRep().split(" ");
                byte[] output = new byte[bytes.length];
                for (int i = 0; i < bytes.length;i++){
                    output[i] = ByteSize(bytes[i]);
                }
                datWriter.write(output);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            money = toStringB(moneyOut).toString();
            test = huffman.decompress(money);
            String decompressionText = "The result of decompression: \n";
            System.out.println(decompressionText + test + "\n");
            writer.append(decompressionText + test);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder toStringB(FileReader file){
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(file);
        Stream<String> stream = reader.lines();
        Object[] temp = stream.toArray();
        for(int i = 0; i < temp.length;i++){
            result.append(temp[i].toString());
            if (i != temp.length-1){
                result.append(System.getProperty("line.separator"));
            }
        }
        return result;
    }

    private static byte ByteSize(String s){
        byte result;
        result = (byte)Integer.parseInt(s,2);
        return result;
    }
}
