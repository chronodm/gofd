package ofd.util;


/**
 * @author davidm
 */
public abstract class AbstractGrid<D extends IDirection<D>, S extends ISquare<D>> implements IGrid<S> {

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public P getP(S square) {
    for (int x : xRange()) {
      for (int y : yRange()) {
        P c = new P(x, y);
        S sq = get(c);
        if (sq.equals(square)) {
          return c;
        }
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * Returns the square corresponding to the given point.
   * Supports toroidal wrapping for points outside the.
   * map range.
   * @param p The point.
   * @return The square corresponding to the specified point.
   */
  public S getSquare(P p) {
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
