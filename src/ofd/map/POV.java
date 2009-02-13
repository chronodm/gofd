package ofd.map;

public class POV {

  // ////////////////////////////////////////////////////////////
  // Final fields

  private final Coord coord;
  private final MDirection fwd;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public POV(Coord coord, MDirection fwd) {
    this.coord = coord;
    this.fwd = fwd;
  }

  // ////////////////////////////////////////////////////////////
  // Accessors

  public Coord getCoord() {
    return coord;
  }

  public MDirection getFwd() {
    return fwd;
  }

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public MSquare getSquare(Grid grid, int xOff, int yOff) {
    MDirection fwd = getFwd();
    Coord pC = getCoord();
    int px = pC.x();
    int py = pC.y();

    switch (fwd) {
      case NORTH: {
        int x = px + xOff;
        int y = py + yOff;
        return grid.getSquare(x, y);
      }
      case EAST: {
        int x = px + yOff;
        int y = py - xOff;
        return grid.getSquare(x, y);
      }
      case SOUTH: {
        int x = px - xOff;
        int y = py - yOff;
        return grid.getSquare(x, y);
      }
      case WEST: {
        int x = px - yOff;
        int y = py + xOff;
        return grid.getSquare(x, y);
      }
    }
    throw new IllegalArgumentException("Unknown direction " + fwd);
  }
}
