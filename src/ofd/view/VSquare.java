package ofd.view;

import ofd.map.Tile;
import ofd.util.ISquare;

public interface VSquare extends ISquare<VDirection> {
  Tile getTile(VDirection dir);
}
