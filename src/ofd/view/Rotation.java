package ofd.view;

import ofd.util.IDirection;

public enum Rotation {
  CLOCKWISE, COUNTERCLOCKWISE;
  
  public <D extends IDirection<D>> D turnOne(D from) {
      return this == CLOCKWISE ? from.next() : from.previous();
  }
}
