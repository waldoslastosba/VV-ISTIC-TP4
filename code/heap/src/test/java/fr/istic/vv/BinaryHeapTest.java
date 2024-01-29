package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {

    @Provide
    Arbitrary<List<Integer>> randomList() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(0, 1024);
        return integerArbitrary.list().ofMinSize(1).ofMaxSize(1024);
    }

    @Property
    boolean testPushAndPop(@ForAll("randomList") List<Integer> randomList) {
        boolean res = true;
        BinaryHeap binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        for (Integer i : randomList) {
            binaryHeap.push(i);
        }

        randomList.sort(Comparator.naturalOrder());

        for (Integer i : randomList) {
            res = res && (binaryHeap.pop().equals(i)); // Comparaison was with == instead of equals
        }

        return res;
    }

    @Property
    boolean testPushAndPeek(@ForAll("randomList") List<Integer> randomList) {
        boolean res = true;
        BinaryHeap binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        for (Integer i : randomList) {
            binaryHeap.push(i);
        }

        randomList.sort(Comparator.naturalOrder());

        for (Integer i : randomList) {
            res = res && (binaryHeap.peek().equals(i)); // Comparaison was with == instead of equals
            binaryHeap.pop();
        }

        return res;
    }

    @Property
    boolean testCount(@ForAll("randomList") List<Integer> randomList) {
        boolean res = true;
        BinaryHeap binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        for (Integer i : randomList) {
            binaryHeap.push(i);
        }

        return randomList.size() == binaryHeap.count();
    }

    @Property
    boolean testPopException() {
        BinaryHeap binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        try {
            binaryHeap.pop();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Property
    boolean testPeekException() {
        BinaryHeap binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        try {
            binaryHeap.peek();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
