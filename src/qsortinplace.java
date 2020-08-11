import java.util.Comparator;
import java.util.Random;

public class qsortinplace{

    public static <E> void qsort(E[] arr, Comparator<E> comp){
        sort(arr,comp,0,arr.length-1);
    }

    private static <E> void sort (E[] arr, Comparator<E> comp, int left, int right){
        if (left >= right) return;
        Random rand = new Random(System.nanoTime());
        int piv = rand.nextInt(right-left+1) + left;
        int L = left;
        int R = right-1;
        E temp = arr[right];
        arr[right] = arr[piv];
        arr[piv] = temp;
        E pivot = arr[right];
        while (L <= R){
            while(L<=R && comp.compare(arr[L],pivot) < 0){
                L++;
            }
            while (L<=R && comp.compare(arr[R],pivot) > 0){
                R--;
            }
            if (L <= R){
                temp = arr[L]; arr[L] = arr[R]; arr[R] = temp;
                L++; R--;
            }
        }
        temp = arr[L]; arr[L] = arr[right]; arr[right] = temp;

        sort(arr,comp, left, L - 1);
        sort(arr,comp,L+1,right);
    }

    private static class IntComp implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) return 1;
            else if (o1 == o2) return 0;
            else return -1;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3,1,2,8,6,5,4,9,0};
        qsort(a,new IntComp());
        for (Integer i : a){
            System.out.print(i + " ");
        }
    }
}
