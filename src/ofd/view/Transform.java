package ofd.view;

import ofd.map.*;

public class Transform {

  // ////////////////////////////////////////////////////////////
  // Public class methods

  public static VSquare toVSquare(MSquare ms, MDirection fwd) {
    return new SquareWrapper(ms, fwd);
  }

  public static MSquare getSquare(Grid grid, POV pov, int xOff, int yOff) {
    MDirection fwd = pov.getFwd();
    Coord pC = pov.getCoord();
    int px = pC.x();
    int py = pC.y();
    
    switch (fwd) {
      case NORTH: {
        int x = px + xOff;
        int y = py + yOff;
        return getSquare(grid, x, y);
      }
      case EAST: {
        int x = px + yOff;
        int y = py - xOff;
        return getSquare(grid, x, y);
      }
      case SOUTH: {
        int x = px - xOff;
        int y = py - yOff;
        return getSquare(grid, x, y);
      }
      case WEST: {
        int x = px - yOff;
        int y = py + xOff;
        return getSquare(grid, x, y);
      }
    }
    throw new IllegalArgumentException("Unknown direction " + fwd);
  }

  // ////////////////////////////////////////////////////////////
  // Private

  /** Handles toroidal wrapping */
  private static MSquare getSquare(Grid grid, int x, int y) {
    int w = grid.width();
    if (x < 0) {
      x = (x % w) + w;
    } else if (x > w) {
      x = x % w;
    }

    int h = grid.height();
    if (y < 0) {
      y = (y % h) + h;
    } else if (y > h) {
      y = y % h;
    }

    Coord mapCoord = new Coord(x, y);
    return grid.getSquare(mapCoord);
  }

  // ////////////////////////////////////////////////////////////
  // Inner classes

  private static class SquareWrapper implements VSquare {

    private final int offset;
    private final MSquare ms;

    public SquareWrapper(MSquare ms, MDirection fwd) {
      this.ms = ms;
      offset = fwd.ordinal();
    }

    @Override
    public Tile getTile(VDirection dir) {
      int index = (dir.ordinal() + offset) % 4;
      MDirection mdir = MDirection.values()[index];
      return ms.getTile(mdir);
    }
  }
}
