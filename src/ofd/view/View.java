package ofd.view;

import ofd.map.MDirection;
import ofd.map.MSquare;
import ofd.map.Tile;

public class View {

  // ////////////////////////////////////////////////////////////
  // Public class methods

  public static VSquare see(MSquare ms, MDirection facing) {
    return new SquareWrapper(ms, facing);
  }
  
  // ////////////////////////////////////////////////////////////
  // Inner classes

  private static class SquareWrapper implements VSquare {

    private final MSquare ms;
    private final MDirection facing;

    public SquareWrapper(MSquare ms, MDirection facing) {
      this.ms = ms;
      this.facing = facing;
    }

    @Override
    public Tile getTile(VDirection dir) {
      int index = (dir.ordinal() + facing.ordinal()) % 4;
      MDirection mdir = MDirection.values()[index];
      return ms.getTile(mdir);
    }
  }
}
