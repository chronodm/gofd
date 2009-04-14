package ofd.util;

/**
 * @author david
 */
public interface IGrid<S> {

  /**
   * Gets the range of valid X coordinates. Note that
   * this may or may not include a zero.
   *
   * @return the range of valid X coordinates.
   */
  IRange xRange();

  /**
   * Gets the range of valid Y coordinates. Note that
   * this may or may not include a zero.
   *
   * @return the range of valid Y coordinates.
   */
  IRange yRange();

  /**
   * Gets the square at the specified point.
   *
   * @param p The canonical point (within the map range)
   * @return the square at the specified point.
   * @throws IllegalArgumentException if <code>p</code> is outside the map range.
   */
  S get(P p);
}
