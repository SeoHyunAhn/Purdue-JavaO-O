/**
 * Created by student on 11/7/16.
 */
public class SortingExample {
    public static void main(String[] args) {
        final int SIZE= 1000;
        int [] data =new int[SIZE];

        for (int i=0; i<SIZE; i++){
            data[i] = (int)(Math.random()*1000);

        }
        selectionSortArray(data);

        for (int i=0; i<SIZE; i++) {
            if (data[i] < data[i] - 1)
                System.out.println("EEEE   Not sorted");
            System.out.println(data[i]);
        }
    }
    public static void selectionSortArray(int[] array){
        int firstUnsortedIndex = 0;
        int lowestIndex;
        int temp;

        for (firstUnsortedIndex=0; firstUnsortedIndex<array.length-1; firstUnsortedIndex++){
            lowestIndex=getIndexofSmallestValue(array,firstUnsortedIndex);
            temp=array[firstUnsortedIndex];
            array[firstUnsortedIndex]=array[lowestIndex];
            array[lowestIndex]=temp;
        }
    }
    public static int getIndexofSmallestValue(int[] array, int start){
        int smallest;
        int indexOfSmallest;

        indexOfSmallest=start;
        smallest=array[start];
        for (int i=start+1; i<array.length; i++){
            if (array[i]<smallest){
                smallest=array[i];
                indexOfSmallest=i;
            }
        }
        return indexOfSmallest;
    }
}
