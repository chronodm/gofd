package ofd.map;

import static ofd.map.MDirection.*;
import static ofd.map.TileType.*;
import ofd.util.P;
import ofd.util.Range;

import java.util.HashMap;
import java.util.Map;

public class TestGrid extends MGrid {

  // ////////////////////////////////////////////////////////////
  // Constants

  private static final int HEIGHT = 10;
  private static final int WIDTH = 11;

  // ////////////////////////////////////////////////////////////
  // Fields

  private MSquareImpl[][] squares = new MSquareImpl[WIDTH][HEIGHT];

  // ////////////////////////////////////////////////////////////
  // Initializer

  {
    for (int x = 0; x < WIDTH; x++) {
      for (int y = 0; y < HEIGHT; y++) {
        MSquareImpl sq = new MSquareImpl();
        squares[x][y] = sq;

        if (x == 0) {
          sq.set(WEST, WALL);
        } else if (x == 10) {
          sq.set(EAST, WALL);
        }

        if (y == 0) {
          sq.set(SOUTH, WALL);
        } else if (y == 9) {
          sq.set(NORTH, WALL);
        }
      }
    }

    for (int x = 1; x < 10; x++) {
      squares[x][0].set(NORTH, WALL);
      squares[x][1].set(SOUTH, WALL);
      squares[x][8].set(NORTH, WALL);
      squares[x][9].set(SOUTH, WALL);
    }
    for (int x = 2; x < 10; x++) {
      squares[x][1].set(NORTH, WALL);
      squares[x][2].set(SOUTH, WALL);
    }
    for (int x = 1; x < 9; x++) {
      squares[x][7].set(NORTH, WALL);
      squares[x][8].set(SOUTH, WALL);
    }
    for (int x = 2; x < 8; x++) {
      squares[x][2].set(NORTH, WALL);
      squares[x][3].set(SOUTH, WALL);
      squares[x][5].set(NORTH, WALL);
      squares[x][6].set(SOUTH, WALL);
    }
    for (int x = 3; x < 9; x++) {
      squares[x][3].set(NORTH, WALL);
      squares[x][4].set(SOUTH, WALL);
      squares[x][6].set(NORTH, WALL);
      squares[x][7].set(SOUTH, WALL);
    }
    for (int x = 3; x < 8; x++) {
      squares[x][4].set(NORTH, WALL);
      squares[x][5].set(SOUTH, WALL);
    }
    squares[5][4].set(NORTH, DOOR);
    squares[5][5].set(SOUTH, DOOR);

    for (int y = 2; y < 8; y++) {
      squares[0][y].set(EAST, WALL);
      squares[1][y].set(WEST, WALL);
      squares[9][y].set(EAST, WALL);
      squares[10][y].set(WEST, WALL);
    }
    for (int y = 3; y < 7; y++) {
      squares[1][y].set(EAST, WALL);
      squares[2][y].set(WEST, WALL);
      squares[8][y].set(EAST, WALL);
      squares[9][y].set(WEST, WALL);
    }
    squares[1][7].set(EAST, DOOR);
    squares[2][7].set(WEST, DOOR);
    squares[8][2].set(EAST, DOOR);
    squares[9][2].set(WEST, DOOR);

    squares[2][4].set(EAST, WALL);
    squares[3][4].set(WEST, WALL);
    squares[7][5].set(EAST, WALL);
    squares[8][5].set(WEST, WALL);
  }

  // ////////////////////////////////////////////////////////////
  // MGrid

  @Override
  public MSquare get(P p) {
    return squares[p.x()][p.y()];
  }

  @Override
  public Range yRange() {
    return new Range(0, HEIGHT);
  }

  @Override
  public Range xRange() {
     return new Range(0, WIDTH);
  }

  // ////////////////////////////////////////////////////////////
  // Helper classes

  private static class TileImpl implements Tile {

    private final TileType type;

    public TileImpl(TileType type) {
      this.type = type;
    }

    @Override
    public TileType type() {
      return type;
    }
  }

  private static class MSquareImpl implements MSquare {

    private Map<MDirection, Tile> tiles = new HashMap<MDirection, Tile>() {
      {
        for (MDirection dir : MDirection.values()) {
          put(dir, new TileImpl(NONE));
        }
      }
    };

    public void set(MDirection dir, TileType type) {
      tiles.put(dir, new TileImpl(type));
    }

    @Override
    public Tile getTile(MDirection direction) {
      return tiles.get(direction);
    }

  }

}
