package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int arrayLength = array.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < arrayLength; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    T tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                    swapped = true;
                }
            }
            arrayLength--;
        } while (swapped);

        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        quicksortAux(array, 0, array.length - 1, comparator);

        return array;
    }

    private static <T> void quicksortAux(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int partitionIndex = partition(array, low, high, comparator);

            quicksortAux(array, low, partitionIndex - 1, comparator);
            quicksortAux(array, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        T tmp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = tmp;

        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length > 1) {
            int mid = array.length / 2;

            T[] arrayLeft = Arrays.copyOfRange(array, 0, mid);
            T[] arrayRight = Arrays.copyOfRange(array, mid, array.length);

            mergesort(arrayLeft, comparator);
            mergesort(arrayRight, comparator);

            merge(array, arrayLeft, arrayRight, comparator);
        }
        return array;
    }

    private static <T> void merge(T[] array, T[] arrayLeft, T[] arrayRight, Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arrayLeft.length && j < arrayRight.length) {
            if (comparator.compare(arrayLeft[i], arrayRight[j]) <= 0) {
                array[k++] = arrayLeft[i++];
            } else {
                array[k++] = arrayRight[j++]; // Was arrayLeft, causing outOfBounds exception (testMergeSort and testComparingSorts)
            }
        }

        while (i < arrayLeft.length) {
            array[k++] = arrayLeft[i++];
        }

        while (j < arrayRight.length) {
            array[k++] = arrayRight[j++];
        }
    }

}
