package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    @Provide
    Arbitrary<Integer[]> randomArray() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(0, 1024);
        return integerArbitrary.array(Integer[].class).ofMinSize(1).ofMaxSize(1024);
    }

    private static boolean isArraySorted(Integer[] array) {
        boolean res = true;

        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                res = res && array[i] <= array[i+1];
            }
        }

        return res;
    }

    @Property
    boolean testBubbleSort(@ForAll("randomArray") Integer[] randomArray) {
        return isArraySorted(Sorting.bubblesort(randomArray.clone(), Comparator.naturalOrder()));
    }

    @Property
    boolean testQuickSort(@ForAll("randomArray") Integer[] randomArray) {
        return isArraySorted(Sorting.quicksort(randomArray.clone(), Comparator.naturalOrder()));
    }

    @Property
    boolean testMergeSort(@ForAll("randomArray") Integer[] randomArray) {
        return isArraySorted(Sorting.mergesort(randomArray.clone(), Comparator.naturalOrder()));
    }

    @Property
    boolean testComparingSorts(@ForAll("randomArray") Integer[] randomArray) {
        return Arrays.equals(
                Sorting.mergesort(randomArray.clone(), Comparator.naturalOrder()),
                Sorting.quicksort(randomArray.clone(), Comparator.naturalOrder())
        ) && Arrays.equals(
                Sorting.mergesort(randomArray.clone(), Comparator.naturalOrder()),
                Sorting.bubblesort(randomArray.clone(), Comparator.naturalOrder())
        );
    }

}
