public class Lab1 {
    /** Sorting algorithms **/

    // in-place insertion sort.

    public static void insertionSort(int[] array) {
        if (array == null || array.length <= 1)
            return;

        for (int i = 1; i < array.length; i++) {
            int k = i;
            int val = array[i];
            // move the value upwards
            while (k > 0 && array[k - 1] > val) {
                array[k] = array[k - 1];
                k--;
            }
            array[k] = val;
        }
    }

    // Quicksort.

    public static void quickSort(int[] array) {
        throw new UnsupportedOperationException();
    }

    // Quicksort part of an array
    private static void quickSort(int[] array, int begin, int end) {

    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {
        throw new UnsupportedOperationException();
    }

    // Swap two elements in an array
    private static void swap(int[] array, int i, int j)
    {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }

    // Mergesort.

    public static int[] mergeSort(int[] array)
    {
        throw new UnsupportedOperationException();
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end)
    {
        throw new UnsupportedOperationException();
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right)
    {
        throw new UnsupportedOperationException();
    }
}
