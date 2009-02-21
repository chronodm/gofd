package ofd.util;

/**
 * A range of integers.
 * @author david
 */
public interface IRange extends Iterable<Integer> {

    /**
     * @return the start of the range (inclusive)
     */
    int from();

    /**
     * @return the end of the range (exclusive)
     */
    int to();

    /**
     * Can be zero if {@link #to()} == {@link #from()}
     * @return {@link #to()} - {@link #from()}
     */
    int size();
}
