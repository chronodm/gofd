package ofd.map;

public abstract class Grid {

  // ////////////////////////////////////////////////////////////
  // Abstracts

  public abstract MSquare getSquare(Coord coord);

  public abstract int width();

  public abstract int height();

  // ////////////////////////////////////////////////////////////
  // Utility methods

  /**
   * Handles toroidal wrapping
   */
  public MSquare getSquare(int x, int y) {
    int w = width();
    if (x < 0) {
      x = (x % w) + w;
    }
    if (x >= w) {
      x = x % w;
    }

    int h = height();
    if (y < 0) {
      y = (y % h) + h;
    }
    if (y >= h) {
      y = y % h;
    }

    Coord mapCoord = new Coord(x, y);
    return getSquare(mapCoord);
  }
}
