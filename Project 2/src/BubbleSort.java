import java.util.Comparator;

public class BubbleSort<K,V> extends AbstractSort<K,V> {

    public BubbleSort(int size) {
        super(size);
        setSortingMethod("Bubble Sort");
    }

    @Override
    protected void sort(MapEntry<K,V>[] entries, Comparator<K> comp) {
        RunTime r = new RunTime();
        int length = getSize();
        boolean swapped = false;
        do{
            swapped = false;
            for (int i = 0; i < length-1; i++){
                keyComp++;
                if (comp.compare(entries[i].getKey(), entries[i+1].getKey()) > 0){
                    dataMove = dataMove + 3;
                    MapEntry<K,V> temp = entries[i];
                    entries[i] = entries[i+1];
                    entries[i+1] = temp;
                    swapped = true;
                }
            }
            length--;
        }while(swapped);
        r.setEndTime();
        setEntries(entries);
        setRunTime(r.computeTime());
    }
}
