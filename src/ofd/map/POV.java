package ofd.map;

import ofd.util.P;
import ofd.view.Rotation;
import ofd.view.VDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class POV {

  // ////////////////////////////////////////////////////////////
  // Final fields

  private final P p;
  private final MDirection fwd;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public POV(P p, MDirection fwd) {
    this.p = p;
    this.fwd = fwd;
  }

  // ////////////////////////////////////////////////////////////
  // Overrides

  @Override
  public String toString() {
    return "POV(" + p.x() + ", " + p.y() + ", " + fwd;
  }

  // ////////////////////////////////////////////////////////////
  // Accessors

  public P getP() {
    return p;
  }

  public MDirection getFwd() {
    return fwd;
  }

  public Iterable<MSquare> getView(MGrid grid, int maxY, int maxX) {
    if (maxY < 1) {
      throw new IllegalArgumentException();
    }
    if (maxX < 1) {
      throw new IllegalArgumentException();
    }

    List<MSquare> squares = new ArrayList<MSquare>();
    for (int yOff = maxY; yOff >= 1; yOff--) {
      for (int xOff = -maxX; xOff < maxX; xOff++) {
        // TODO: Narrow as we get closer
        squares.add(getSquare(grid, xOff, yOff));
      }
    }
    return Collections.unmodifiableList(squares);
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

    P newC = move(x, y);
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
    return new POV(p, newFwd);
  }

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public MSquare getSquare(MGrid grid, int xOff, int yOff) {
    P toC = move(xOff, yOff);
    return grid.getSquare(toC.x(), toC.y());
  }

  private P move(int xOff, int yOff) {
    MDirection fwd = getFwd();
    P pC = getP();
    int px = pC.x();
    int py = pC.y();

    P toC;

    // TODO Move switch to MDirection
    switch (fwd) {
      case NORTH: {
        int x = px + xOff;
        int y = py + yOff;
        toC = new P(x, y);
        break;
      }
      case EAST: {
        int x = px + yOff;
        int y = py - xOff;
        toC = new P(x, y);
        break;
      }
      case SOUTH: {
        int x = px - xOff;
        int y = py - yOff;
        toC = new P(x, y);
        break;
      }
      case WEST: {
        int x = px - yOff;
        int y = py + xOff;
        toC = new P(x, y);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown direction " + fwd);
      }
    }
    return toC;
  }
}
