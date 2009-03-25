package ofd.util;

import java.util.Iterator;

/**
 * Default range implementation.
 * @author david
 */
public class Range implements IRange {

    // ////////////////////////////////////////////////////////////
    // Fields

    private final int from;
    private final int to;

    // ////////////////////////////////////////////////////////////
    // Constructor

    public Range(int from, int to) {
        assert from <= to : "! " + from + " <= " +to;
        this.from = from;
        this.to = to;
    }

    // ////////////////////////////////////////////////////////////
    // IRange

    @Override public int from() {
        return from;
    }

    @Override public int to() {
            return to;
    }

    @Override public int size() {
        return to - from;
    }

    @Override public Iterator<Integer> iterator() {
        return new RangeIterator(this);
    }
    
    @Override
    public boolean contains(int i) {
      return i >= from && i < to;
    }

}
