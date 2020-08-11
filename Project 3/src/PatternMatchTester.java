/*  Java Class: PatternMatchTester.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: The tester class that tests various patterns in and not in the text for both the usdelcarPC.txt and humanDNA.txt.

    I certify that the code below is my own work.

	Exception(s): N/A
screes
*/

import java.io.*;
import java.util.stream.Stream;

public class PatternMatchTester {
    public static void main(String[] args) {

        try{
            FileReader usDeclar = new FileReader("/home/john/Documents/CSCI230/usdeclarPC.txt");
            FileReader humanDNA = new FileReader("/home/john/Documents/CSCI230/humanDNA.txt");
            BMMatch BM = new BMMatch();
            KMPMatch KMP = new KMPMatch();
            String USDec = toStringB(usDeclar).toString().toLowerCase();
            String DNA = toStringB(humanDNA).toString().toLowerCase();
            int index = BM.find(USDec, "america");
            String USDecResult = BM.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = BM.find(USDec, "aeiou");
            USDecResult = BM.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = BM.find(USDec, "waging");
            USDecResult = BM.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = BM.find(USDec, "dissolutions");
            USDecResult = BM.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = KMP.find(USDec, "america");
            USDecResult = KMP.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = KMP.find(USDec, "aeiou");
            USDecResult = KMP.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = KMP.find(USDec, "waging");
            USDecResult = KMP.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = KMP.find(USDec, "dissolutions");
            USDecResult = KMP.generateReport(index, USDec.length());
            System.out.println(USDecResult);
            index = BM.find(DNA, "agagagtaaaaaa");
            String DNAResult = BM.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = BM.find(DNA, "aactattctctg");
            DNAResult = BM.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = BM.find(DNA, "tagtac");
            DNAResult = BM.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = BM.find(DNA, "agtcgtacxagtcg");
            DNAResult = BM.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = KMP.find(DNA, "agagagtaaaaaa");
            DNAResult = KMP.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = KMP.find(DNA, "aactattctctg");
            DNAResult = KMP.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = KMP.find(DNA, "tagtac");
            DNAResult = KMP.generateReport(index, DNA.length());
            System.out.println(DNAResult);
            index = KMP.find(DNA, "agtcgtacxagtcg");
            DNAResult = KMP.generateReport(index, DNA.length());
            System.out.println(DNAResult);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static StringBuilder toStringB(FileReader file){
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(file);
        Stream<String> stream = reader.lines();
        Object[] temp = stream.toArray();
        for(Object s : temp){
            result.append(s.toString());
            result.append(System.getProperty("line.separator"));
        }
        return result;
    }

}
