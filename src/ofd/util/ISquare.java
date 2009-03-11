package ofd.util;

import ofd.map.Tile;

public interface ISquare<D extends IDirection<D>> {
  Tile getTile(D dir);
}
