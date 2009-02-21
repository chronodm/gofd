package ofd.map;

import ofd.view.VDirection;

public enum MDirection {

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
  // Public methods

  /**
   * Returns this {@link MDirection}'s corresponding {@link VDirection}, assuming
   * no rotation (i.e., north == up)
   */
  public VDirection v() {
    return vDir;
  }
}
