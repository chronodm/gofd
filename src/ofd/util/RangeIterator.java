package ofd.util;

import java.util.Iterator;

/**
 * @author david
*/
public class RangeIterator implements Iterator<Integer> {

    // ////////////////////////////////////////////////////////////
    // Final fields

    private final Range range;

    // ////////////////////////////////////////////////////////////
    // Mutable fields

    private int next;

    // ////////////////////////////////////////////////////////////
    // Constructors

    public RangeIterator(Range range) {
        this.range = range;
        next = range.from();
    }

    // ////////////////////////////////////////////////////////////
    // Iterator

    @Override public boolean hasNext() {
        return next < range.to();
    }

    @Override public Integer next() {
        int current = next;
        next++;
        return current;
    }

    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}
