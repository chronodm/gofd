package ofd.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ofd.util.P;
import ofd.util.V;
import ofd.view.Rotation;
import ofd.view.VDirection;

public class POV {

  // ////////////////////////////////////////////////////////////
  // Final fields

  private final P p;
  private final MDirection facing;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public POV(P p, MDirection facing) {
    this.p = p;
    this.facing = facing;
  }

  // ////////////////////////////////////////////////////////////
  // Overrides

  @Override
  public String toString() {
    return "POV(" + p.x() + ", " + p.y() + ", " + facing;
  }

  // ////////////////////////////////////////////////////////////
  // Accessors

  public P getP() {
    return p;
  }

  public MDirection getFacing() {
    return facing;
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
        V move = new V(xOff, yOff);
        squares.add(getSquare(grid, move));
      }
    }
    return Collections.unmodifiableList(squares);
  }

  // ////////////////////////////////////////////////////////////
  // Transformations

  public POV moveOne(VDirection dir) {
    return new POV(getFacing().translate(getP(), dir.moveOne()), facing);
  }

  public POV turnOne(Rotation dir) {
    return new POV(p, dir.turnOne(facing));
  }

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public MSquare getSquare(MGrid grid, V move) {
    P toC = getFacing().translate(getP(), move);
    return grid.getSquare(toC);
  }

}
