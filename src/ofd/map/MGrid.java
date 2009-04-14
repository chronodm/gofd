package ofd.map;

import ofd.util.AbstractGrid;
import ofd.util.IGrid;
import ofd.util.IRange;
import ofd.util.P;

/**
 * A fixed map {@link IGrid} representing a rectangular map with 0, 0 in the
 * southwest corner and (width, height) in the northeast.
 *
 * @author davidm
 */
public abstract class MGrid extends AbstractGrid<MDirection, MSquare> {

  /**
   * Gets the range of valid X coordinates. Note that the <code>from</code>
   * coordinate is always 0 and the <code>to</code> coordinate is always
   * positive.
   *
   * @return the range of valid X coordinates.
   */
  @Override
  public abstract IRange xRange();

  /**
   * Gets the range of valid Y coordinates. Note that the <code>from</code>
   * coordinate is always 0 and the <code>to</code> coordinate is always
   * positive.
   *
   * @return the range of valid Y coordinates.
   */
  @Override
  public abstract IRange yRange();

  /**
   * Returns the square corresponding to the given point.
   * Supports toroidal wrapping for points outside the.
   * map range.
   *
   * @param p The point.
   * @return The square corresponding to the specified point.
   */
  public MSquare getSquare(P p) {
    int x = p.x();
    int y = p.y();

    int w = xRange().size();
    if (x < 0) {
      x = (x % w) + w;
    }
    if (x >= w) {
      x = x % w;
    }

    int h = yRange().size();
    if (y < 0) {
      y = (y % h) + h;
    }
    if (y >= h) {
      y = y % h;
    }

    P mapP = new P(x, y);
    return get(mapP);
  }
}
