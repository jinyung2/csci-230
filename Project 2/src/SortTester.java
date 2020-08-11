import java.io.*;
import java.util.Comparator;
import java.util.stream.Stream;


public class SortTester {

    public static void main(String[] args) {
        MapEntry<Integer,String>[] listIntStr1k = new MapEntry[1000];
        MapEntry<String,Integer>[] listStrInt1k = new MapEntry[1000];
        MapEntry<Integer,String>[] listIntStr100k = new MapEntry[100000];
        MapEntry<String,Integer>[] listStrInt100k = new MapEntry[100000];
        MapEntry<Integer, String>[] tester = new MapEntry[10];
        int j = 0;
        for (int i = tester.length - 1; i >= 0; i--){
            Integer temp = i;
            tester[j] = new MapEntry(i, temp.toString());
            j++;
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/home/john/Documents/CSCI230/p2Results.txt"))){
            FileReader small1kRaw = new FileReader("/home/john/Downloads/small1k.txt");
            FileReader large100kRaw = new FileReader("/home/john/Downloads/large100k.txt");
            StringBuilder small1k = new StringBuilder(toString(small1kRaw));
            StringBuilder large100k = new StringBuilder(toString(large100kRaw));
            // creates Integer,String and String,Integer array lists of 1k and 100k values//
            compileIntStrList(listIntStr1k, small1k.toString());
            compileStrIntList(listStrInt1k, small1k.toString());
            compileIntStrList(listIntStr100k, large100k.toString());
            compileStrIntList(listStrInt100k, large100k.toString());
            StringBuilder p2ResultText = new StringBuilder();
            AbstractSort selection = new SelectionSort(1000);
            AbstractSort insertion = new InsertionSort(1000);
            AbstractSort quick1k = new QuickSort(1000);
            AbstractSort merge1k = new MergeSort(1000);
            AbstractSort heap1k = new HeapSort(1000);
            AbstractSort hybrid1k = new HybridSort(1000);
            AbstractSort quick100k = new QuickSort(100000);
            AbstractSort merge100k = new MergeSort(100000);
            AbstractSort heap100k = new HeapSort(100000);
            AbstractSort hybrid100k = new HybridSort(100000);

                selection.sort(listIntStr1k.clone(), new IntComp());
                p2ResultText.append(selection.generateReport() + "\n");
                selection.sort(listStrInt1k.clone(), new StrComp());
                p2ResultText.append(selection.generateReport() + "\n");

                insertion.sort(listIntStr1k.clone(), new IntComp());
                p2ResultText.append(insertion.generateReport() + "\n");
                insertion.sort(listStrInt1k.clone(), new StrComp());
                p2ResultText.append(insertion.generateReport() + "\n");

                quick1k.sort(listIntStr1k.clone(), new IntComp());
                p2ResultText.append(quick1k.generateReport() + "\n");
                quick1k.sort(listStrInt1k.clone(), new StrComp());
                p2ResultText.append(quick1k.generateReport() + "\n");

                merge1k.sort(listIntStr1k.clone(), new IntComp());
                p2ResultText.append(merge1k.generateReport() + "\n");
                merge1k.sort(listStrInt1k.clone(), new StrComp());
                p2ResultText.append(merge1k.generateReport() + "\n");

                heap1k.sort(listIntStr1k.clone(), new IntComp());
                p2ResultText.append(heap1k.generateReport() + "\n");
                heap1k.sort(listStrInt1k.clone(), new StrComp());
                p2ResultText.append(heap1k.generateReport() + "\n");

                hybrid1k.sort(listIntStr1k.clone(), new IntComp());
                p2ResultText.append(hybrid1k.generateReport() + "\n");
                hybrid1k.sort(listStrInt1k.clone(), new StrComp());
                p2ResultText.append(hybrid1k.generateReport() + "\n");

                quick100k.sort(listIntStr100k.clone(), new IntComp());
                p2ResultText.append(quick100k.generateReport() + "\n");
                quick100k.sort(listStrInt100k.clone(), new StrComp());
                p2ResultText.append(quick100k.generateReport() + "\n");

                merge100k.sort(listIntStr100k.clone(), new IntComp());
                p2ResultText.append(merge100k.generateReport() + "\n");
                merge100k.sort(listStrInt100k.clone(), new StrComp());
                p2ResultText.append(merge100k.generateReport() + "\n");

                heap100k.sort(listIntStr100k.clone(), new IntComp());
                p2ResultText.append(heap100k.generateReport() + "\n");
                heap100k.sort(listStrInt100k.clone(), new StrComp());
                p2ResultText.append(heap100k.generateReport() + "\n");

                hybrid100k.sort(listIntStr100k.clone(), new IntComp());
                p2ResultText.append(hybrid100k.generateReport() + "\n");
                hybrid100k.sort(listStrInt100k.clone(), new StrComp());
                p2ResultText.append(hybrid100k.generateReport() + "\n");

                    writer.write(p2ResultText.toString());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static StringBuilder toString(FileReader file){
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(file);
        Stream<String> stream = reader.lines();
        Object[] temp = stream.toArray();
        for(Object s : temp){
            result.append(s.toString() + "  ");
        }
        return result;
    }

    private static void compileIntStrList(Entry<Integer,String>[] list, String s){
        int j = 0;
            String[] strArr = s.split("  ");
            for (int i = 0; i < strArr.length;i++){
                if (!strArr[i].equals("")){
                    strArr[i] = strArr[i].replace(" ","");
                    Integer cur = Integer.parseInt(strArr[i]);
                    list[j++] = new MapEntry(cur, strArr[i]);
                }
        }
    }

    private static void compileStrIntList(Entry<String,Integer>[] list, String s){
        int j = 0;
            String[] strArr = s.split("  ");
            for (int i = 0; i < strArr.length;i++){
                if (!strArr[i].equals("")){
                    strArr[i] = strArr[i].replace(" ","");
                    Integer cur = Integer.parseInt(strArr[i]);
                    list[j] = new MapEntry(strArr[i], cur);
                    j++;
                }
        }
    }

    //debugging use//
    private static void print(Entry[] list){
        for(Entry e : list){
            System.out.print("<" + e.getKey() + ", " + e.getValue() + ">\t");
        }
        System.out.println("\n\n");
    }

    private static class IntComp implements Comparator<Integer> {
        public int compare(Integer a, Integer b){
            return a.compareTo(b);
        }
    }

    private static class StrComp implements Comparator<String>{
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

}
