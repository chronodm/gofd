package ofd.map;

import ofd.util.IGrid;
import ofd.util.P;

/**
 * A fixed map {@link IGrid} representing a rectangular map with 0, 0 in the
 * southwest corner and (width, height) in the northeast.
 * 
 * @author davidm
 */
public abstract class MGrid implements IGrid<MSquare> {

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public P getP(MSquare square) {
    for (int x : xRange()) {
      for (int y : yRange()) {
        P c = new P(x, y);
        MSquare sq = get(c);
        if (sq.equals(square)) {
          return c;
        }
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * Handles toroidal wrapping
   */
  public MSquare getSquare(int x, int y) {
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
