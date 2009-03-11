package ofd.view;

import ofd.util.IDirection;
import ofd.util.V;

public enum VDirection implements IDirection<VDirection>{
  FWD, RIGHT, BACK, LEFT;
  
  // ////////////////////////////////////////////////////////////
  // IDirection
  
  @Override
  public VDirection next() {
    VDirection[] values = values();
    int valueCount = values.length;
    return values[(this.ordinal() + 1) % valueCount];
  }

  @Override
  public VDirection previous() {
    VDirection[] values = values();
    int valueCount = values.length;
    return values[(this.ordinal() + valueCount - 1) % valueCount];
  }

  public V moveOne() {
    int dx, dy;

    switch (this) {
      case FWD: {
        dx = 0;
        dy = 1;
        break;
      }
      case BACK: {
        dx = 0;
        dy = -1;
        break;
      }
      case LEFT: {
        dx = -1;
        dy = 0;
        break;
      }
      case RIGHT: {
        dx = 1;
        dy = 0;
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown direction " + this);
      }
    }

    V vector = new V(dx, dy);
    return vector;
  }
}
