import java.util.Random;

public class qSelect {

    private static int partition(Integer[] a, int l, int r, int pivot){
        Integer pivotVal = a[pivot];
        Integer temp = a[r];
        a[r] = a[pivot];
        a[pivot] = temp;
        int resultIndex = l;
        for (int i = l; i < r; i++){
            if (a[i] < pivotVal){
                temp = a[resultIndex];
                a[resultIndex] = a[i];
                a[i] = temp;
                resultIndex++;
            }
        }
        temp = a[r];
        a[r] = a[resultIndex];
        a[resultIndex] = temp;
        return resultIndex;
    }

    private static int select(Integer[] a, int l, int r, int k){
        while(true){
            Random rand = new Random(System.nanoTime());
            if (l == r){return a[l];}
            int pivotIndex = rand.nextInt(r-l+1)+l;
            pivotIndex = partition(a,l,r,pivotIndex);
            if (k == pivotIndex) return a[k];
            else if (k < pivotIndex) r = pivotIndex -1;
            else l = pivotIndex + 1;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5,6,4,2,8,7,0,1,1,1,11,3,9,9,9,9,9};
        int result = select(a,0,a.length-1, 2);
        System.out.println(result);
    }
}
