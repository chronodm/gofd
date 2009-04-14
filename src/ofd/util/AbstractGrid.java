package ofd.util;


/**
 * @author davidm
 */
public abstract class AbstractGrid<D extends IDirection<D>, S extends ISquare<D>> implements IGrid<S> {

  // ////////////////////////////////////////////////////////////
  // Utility methods

  /**
   * Gets the canonical coordinates for the specified square.
   *
   * @param square The square to get coordinates for.
   * @return The coordinates, within the ranges returned by {@link #xRange()}
   *         and {@link #yRange()}.
   * @throws IllegalArgumentException for squares not found in this map.
   */
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

}
