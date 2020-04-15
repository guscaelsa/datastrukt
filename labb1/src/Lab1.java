public class Lab1 {
    /** Sorting algorithms **/

    // Insertion sort.

    public static void insertionSort(int[] array) {
        throw new UnsupportedOperationException();
    }

    // Quicksort.

    public static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        quickSort(array, 0, array.length);
    }


    // Quicksort part of an array
    private static void quickSort(int[] array, int begin, int end) {
        int pivotIndex = partition(array, begin, end);
        if (begin < pivotIndex - 1) {
            quickSort(array, begin, pivotIndex - 1);
        }
        if (pivotIndex < end){
            quickSort(array, pivotIndex, end);
        }
    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {
        int pivot = array[begin];
        int low = begin + 1;
        int high = end;

        for (int j = begin + 1; j < high; j++) {
            if (array[j] < pivot) {
                swap(array, low, j);
                low++;
            }
        }

        swap(array, begin, low - 1);
        return low;
    }

    // Swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }

    // Mergesort.

    public static int[] mergeSort(int[] array) {
        throw new UnsupportedOperationException();
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        throw new UnsupportedOperationException();
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right) {
        throw new UnsupportedOperationException();
    }
}
