package ofd.map;

import ofd.util.ISquare;

public interface MSquare extends ISquare<MDirection> {
  Tile getTile(MDirection direction);
}
