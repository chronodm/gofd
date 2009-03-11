package ofd.map;

import ofd.util.IDirection;
import ofd.util.P;
import ofd.util.V;
import ofd.view.VDirection;

public enum MDirection implements IDirection<MDirection> {

  // ////////////////////////////////////////////////////////////
  // Enum instances

  NORTH(VDirection.FWD), EAST(VDirection.RIGHT), SOUTH(VDirection.BACK), WEST(VDirection.LEFT);

  // ////////////////////////////////////////////////////////////
  // Fields

  private final VDirection vDir;

  // ////////////////////////////////////////////////////////////
  // Constructor

  private MDirection(VDirection vDir) {
    this.vDir = vDir;
  }

  // ////////////////////////////////////////////////////////////
  // IDirection
  
  @Override
  public MDirection next() {
    MDirection[] values = values();
    int valueCount = values.length;
    return values[(this.ordinal() + 1) % valueCount];
  }

  @Override
  public MDirection previous() {
    MDirection[] values = values();
    int valueCount = values.length;
    return values[(this.ordinal() + valueCount - 1) % valueCount];
  }
  
  // ////////////////////////////////////////////////////////////
  // Public methods

  /**
   * @return this {@link MDirection}'s corresponding {@link VDirection}, assuming
   * no rotation (i.e., north == up)
   */
  public VDirection v() {
    return vDir;
  }

  /**
   * Translates (moves) the specified distance from the specified point , in the coordinate space implied by this
   * {@link MDirection}, i.e. +y = forward, -y = back, +x = right, -x = left.
   * 
   * @return the point offset from the specified point by <code>distance</code>.
   * @param from The starting point
   * @param distance The distance to translate, in the coordinate space implied by this
   * {@link MDirection}.
   */
  public P translate(P from, V distance) {
    int px = from.x();
    int py = from.y();

    int dx = distance.dx();
    int dy = distance.dy();

    switch (this) {
      case NORTH: {
        int x = px + dx;
        int y = py + dy;
        return new P(x, y);
      }
      case EAST: {
        int x = px + dy;
        int y = py - dx;
        return new P(x, y);
      }
      case SOUTH: {
        int x = px - dx;
        int y = py - dy;
        return new P(x, y);
      }
      case WEST: {
        int x = px - dy;
        int y = py + dx;
        return new P(x, y);
      }
      default: {
        throw new IllegalArgumentException("Unknown direction " + this);
      }
    }
  }
}
