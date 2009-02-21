package ofd.util;

/**
 * @author david
 */
public interface IGrid<S> {
    IRange xRange();
    IRange yRange();
    S get(P p);
}
