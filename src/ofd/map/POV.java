package ofd.map;

import ofd.view.VDirection;
import ofd.view.Rotation;

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
  // Overrides

  @Override
  public String toString() {
    return "POV(" + coord.x() + ", " + coord.y() + ", " + fwd;
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
  // Transformations

  public POV move(VDirection dir) {
    int x, y;

    // TODO Move switch to VDirection
    switch (dir) {
      case FWD: {
        x = 0;
        y = 1;
        break;
      }
      case BACK: {
        x = 0;
        y = -1;
        break;
      }
      case LEFT: {
        x = -1;
        y = 0;
        break;
      }
      case RIGHT: {
        x = 1;
        y = 0;
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown direction " + dir);
      }
    }

    Coord newC = move(x, y);
    return new POV(newC, fwd);
  }

  public POV turn(Rotation dir) {
    int offset;
    // TODO move switch to Rotation
    switch (dir) {
      case CLOCKWISE:
        offset = 1;
        break;
      case COUNTERCLOCKWISE:
        offset = 3; // -1 hack
        break;
      default:
        throw new IllegalArgumentException("Unknown direction " + dir);
    }
    // TODO share logic with View
    int index = (fwd.ordinal() + offset) % 4;
    MDirection newFwd = MDirection.values()[index];
    return new POV(coord, newFwd);
  }

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public MSquare getSquare(Grid grid, int xOff, int yOff) {
    Coord toC = move(xOff, yOff);
    return grid.getSquare(toC.x(), toC.y());
  }

  private Coord move(int xOff, int yOff) {
    MDirection fwd = getFwd();
    Coord pC = getCoord();
    int px = pC.x();
    int py = pC.y();

    Coord toC;

    // TODO Move switch to MDirection
    switch (fwd) {
      case NORTH: {
        int x = px + xOff;
        int y = py + yOff;
        toC = new Coord(x, y);
        break;
      }
      case EAST: {
        int x = px + yOff;
        int y = py - xOff;
        toC = new Coord(x, y);
        break;
      }
      case SOUTH: {
        int x = px - xOff;
        int y = py - yOff;
        toC = new Coord(x, y);
        break;
      }
      case WEST: {
        int x = px - yOff;
        int y = py + xOff;
        toC = new Coord(x, y);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown direction " + fwd);
      }
    }
    return toC;
  }
}
