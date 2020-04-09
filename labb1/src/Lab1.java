public class Lab1 {
    /** Sorting algorithms **/

    // Insertion sort.

    public static void insertionSort(int[] array) {
        throw new UnsupportedOperationException();
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
