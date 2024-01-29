package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private Comparator<T> comparator;
    private List<T> values;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.values = new ArrayList<>();
    }

    public T pop() {
        if (count() == 0) throw new NoSuchElementException();
        return values.remove(0);
    }

    public T peek() {
        if (count() == 0) throw new NoSuchElementException();
        return values.get(0);
    }

    public void push(T element) {
        if (count() == 0){
            values.add(element);
        } else {
            if (comparator.compare(values.get(0), element) < 0) {
                int idxToAdd = -1;
                boolean searching = true;
                for (int i = 1; i < values.size(); i++) {
                    if (searching && comparator.compare(values.get(i), element) >= 0) { // [1, 2, 4, 6]
                        idxToAdd = i;
                        searching = false;
                    }
                }
                if (idxToAdd > -1) {
                    values.add(idxToAdd, element);
                } else values.add(element);
            } else {
                values.add(0, element);
            }
        }
    }

    public int count() {
        return values.size();
    }

}
