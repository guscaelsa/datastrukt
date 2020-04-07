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
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int l = 0; // index into left
        int r = 0; // index into right
        int i = 0; // index into result
        for (;i<result.length; ++i) {
            if (l >= left.length) {
                result[i] = right[r];
                ++r;
            } else if (r >= right.length) {
                result[i] = left[l];
                ++l;
            } else {
                if (left[l] < right[r]) {
                    result[i] = left[l];
                    ++l;
                } else {
                    result[i] = right[r];
                    ++r;
                }
            }
        }
        return result;
    }
}
