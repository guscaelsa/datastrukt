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
        return mergeSort(array, 0, array.length);
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        if (begin == end) {
            return new int[] {};
        } else if (begin + 1 == end) {
            return new int[] {array[begin]};
        } else {
            int split = begin + (end - begin) / 2;
            return merge(mergeSort(array, begin, split), mergeSort(array, split, end));
        }
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] arrayA, int[] arrayB) {
        int[] result = new int[arrayA.length + arrayB.length];
        int AIndex = 0;
        int BIndex = 0;

        for (int i = 0; i < result.length; ++i) {
            if (AIndex >= arrayA.length) {
                result[i] = arrayB[BIndex];
                ++BIndex;
            } else if (BIndex >= arrayB.length) {
                result[i] = arrayA[AIndex];
                ++AIndex;
            } else {
                if (arrayA[AIndex] < arrayB[BIndex]) {
                    result[i] = arrayA[AIndex];
                    ++AIndex;
                } else {
                    result[i] = arrayB[BIndex];
                    ++BIndex;
                }
            }
        }
        return result;
    }
}
