package ofd.view;

import java.util.*;

import ofd.map.*;

public class View {

  // ////////////////////////////////////////////////////////////
  // Public class methods

  public static VSquare see(MSquare ms, MDirection facing) {
    return new SquareWrapper(ms, facing);
  }
  
  // ////////////////////////////////////////////////////////////
  // Inner classes

  private static class SquareWrapper implements VSquare {

    private final int offset;
    private final MSquare ms;

    public SquareWrapper(MSquare ms, MDirection facing) {
      this.ms = ms;
      offset = facing.ordinal();
    }

    @Override
    public Tile getTile(VDirection dir) {
      // TODO share logic with POV
      int index = (dir.ordinal() + offset) % 4;
      MDirection mdir = MDirection.values()[index];
      return ms.getTile(mdir);
    }
  }
}
