package ofd.map;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class POVTest {

  // ////////////////////////////////////////////////////////////
  // Fields

  private Grid grid;
  private MSquare[][] gsq;

  // ////////////////////////////////////////////////////////////
  // Fixture

  @Before
  public void setUp() {
    gsq = new MSquare[][] {
        { mock(MSquare.class), mock(MSquare.class), mock(MSquare.class) },
        { mock(MSquare.class), mock(MSquare.class), mock(MSquare.class) },
        { mock(MSquare.class), mock(MSquare.class), mock(MSquare.class) } };
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        MSquare square = gsq[x][y];
        when(square.toString()).thenReturn("[" + x + ", " + y + "]");
      }
    }
    grid = new Grid() {

      @Override
      public MSquare getSquare(Coord coord) {
        return gsq[coord.x()][coord.y()];
      }

      @Override
      public int height() {
        return 3;
      }

      @Override
      public int width() {
        return 3;
      }

    };
  }

  // ////////////////////////////////////////////////////////////
  // Tests

  @Test
  public void testGetSquare() {
    POV pov = new POV(new Coord(0, 0), MDirection.NORTH);
    assertSame(gsq[2][1], pov.getSquare(grid, 2, 1));
    assertSame(gsq[2][0], pov.getSquare(grid, 2, 0));
    assertSame(gsq[2][2], pov.getSquare(grid, 2, 2));
    assertSame(gsq[0][1], pov.getSquare(grid, 0, 1));
    assertSame(gsq[0][0], pov.getSquare(grid, 0, 0));
    assertSame(gsq[0][2], pov.getSquare(grid, 0, 2));
    assertSame(gsq[1][1], pov.getSquare(grid, 1, 1));
    assertSame(gsq[1][0], pov.getSquare(grid, 1, 0));
    assertSame(gsq[1][2], pov.getSquare(grid, 1, 2));
  }

  @Test
  public void testGetSquareWrap() {
    POV pov = new POV(new Coord(0, 0), MDirection.NORTH);
    assertSame(gsq[2][1], pov.getSquare(grid, -1, 1));
    assertSame(gsq[2][0], pov.getSquare(grid, -1, 0));
    assertSame(gsq[2][2], pov.getSquare(grid, -1, -1));
    assertSame(gsq[0][1], pov.getSquare(grid, 0, 1));
    assertSame(gsq[0][0], pov.getSquare(grid, 0, 0));
    assertSame(gsq[0][2], pov.getSquare(grid, 0, -1));
    assertSame(gsq[1][1], pov.getSquare(grid, 1, 1));
    assertSame(gsq[1][0], pov.getSquare(grid, 1, 0));
    assertSame(gsq[1][2], pov.getSquare(grid, 1, -1));
  }

  @Test
  public void testGetSquareRotateEast() {
    POV pov = new POV(new Coord(0, 0), MDirection.EAST);
    assertSame(gsq[2][1], pov.getSquare(grid, -1, -1));
    assertSame(gsq[2][0], pov.getSquare(grid, 0, -1));
    assertSame(gsq[2][2], pov.getSquare(grid, 1, -1));
    assertSame(gsq[0][1], pov.getSquare(grid, -1, 0));
    assertSame(gsq[0][0], pov.getSquare(grid, 0, 0));
    assertSame(gsq[0][2], pov.getSquare(grid, 1, 0));
    assertSame(gsq[1][1], pov.getSquare(grid, -1, 1));
    assertSame(gsq[1][0], pov.getSquare(grid, 0, 1));
    assertSame(gsq[1][2], pov.getSquare(grid, 1, 1));
  }

  @Test
  public void testGetSquareRotateSouth() {
    POV pov = new POV(new Coord(0, 0), MDirection.SOUTH);
    assertSame(gsq[2][1], pov.getSquare(grid, 1, -1));
    assertSame(gsq[2][0], pov.getSquare(grid, 1, 0));
    assertSame(gsq[2][2], pov.getSquare(grid, 1, 1));
    assertSame(gsq[0][1], pov.getSquare(grid, 0, -1));
    assertSame(gsq[0][0], pov.getSquare(grid, 0, 0));
    assertSame(gsq[0][2], pov.getSquare(grid, 0, 1));
    assertSame(gsq[1][1], pov.getSquare(grid, -1, -1));
    assertSame(gsq[1][0], pov.getSquare(grid, -1, 0));
    assertSame(gsq[1][2], pov.getSquare(grid, -1, 1));
  }

  @Test
  public void testGetSquareRotateWest() {
    POV pov = new POV(new Coord(0, 0), MDirection.WEST);
    assertSame(gsq[2][1], pov.getSquare(grid, 1, 1));
    assertSame(gsq[2][0], pov.getSquare(grid, 0, 1));
    assertSame(gsq[2][2], pov.getSquare(grid, -1, 1));
    assertSame(gsq[0][1], pov.getSquare(grid, 1, 0));
    assertSame(gsq[0][0], pov.getSquare(grid, 0, 0));
    assertSame(gsq[0][2], pov.getSquare(grid, -1, 0));
    assertSame(gsq[1][1], pov.getSquare(grid, 1, -1));
    assertSame(gsq[1][0], pov.getSquare(grid, 0, -1));
    assertSame(gsq[1][2], pov.getSquare(grid, -1, -1));
  }

  @Test
  public void testGetSquareTranslate() {
    POV pov = new POV(new Coord(1, 1), MDirection.NORTH);
    assertSame(gsq[0][2], pov.getSquare(grid, -1, 1));
    assertSame(gsq[0][1], pov.getSquare(grid, -1, 0));
    assertSame(gsq[0][0], pov.getSquare(grid, -1, -1));
    assertSame(gsq[1][2], pov.getSquare(grid, 0, 1));
    assertSame(gsq[1][1], pov.getSquare(grid, 0, 0));
    assertSame(gsq[1][0], pov.getSquare(grid, 0, -1));
    assertSame(gsq[2][2], pov.getSquare(grid, 1, 1));
    assertSame(gsq[2][1], pov.getSquare(grid, 1, 0));
    assertSame(gsq[2][0], pov.getSquare(grid, 1, -1));
  }

  @Test
  public void testGetSquareTranslateEast() {
    POV pov = new POV(new Coord(1, 1), MDirection.EAST);
    assertSame(gsq[0][2], pov.getSquare(grid, -1, -1));
    assertSame(gsq[0][1], pov.getSquare(grid, 0, -1));
    assertSame(gsq[0][0], pov.getSquare(grid, 1, -1));
    assertSame(gsq[1][2], pov.getSquare(grid, -1, 0));
    assertSame(gsq[1][1], pov.getSquare(grid, 0, 0));
    assertSame(gsq[1][0], pov.getSquare(grid, 1, 0));
    assertSame(gsq[2][2], pov.getSquare(grid, -1, 1));
    assertSame(gsq[2][1], pov.getSquare(grid, 0, 1));
    assertSame(gsq[2][0], pov.getSquare(grid, 1, 1));
  }

  @Test
  public void testGetSquareTranslateSouth() {
    POV pov = new POV(new Coord(1, 1), MDirection.SOUTH);
    assertSame(gsq[0][2], pov.getSquare(grid, 1, -1));
    assertSame(gsq[0][1], pov.getSquare(grid, 1, 0));
    assertSame(gsq[0][0], pov.getSquare(grid, 1, 1));
    assertSame(gsq[1][2], pov.getSquare(grid, 0, -1));
    assertSame(gsq[1][1], pov.getSquare(grid, 0, 0));
    assertSame(gsq[1][0], pov.getSquare(grid, 0, 1));
    assertSame(gsq[2][2], pov.getSquare(grid, -1, -1));
    assertSame(gsq[2][1], pov.getSquare(grid, -1, 0));
    assertSame(gsq[2][0], pov.getSquare(grid, -1, 1));
  }

  @Test
  public void testGetSquareTranslateWest() {
    POV pov = new POV(new Coord(1, 1), MDirection.WEST);
    assertSame(gsq[0][2], pov.getSquare(grid, 1, 1));
    assertSame(gsq[0][1], pov.getSquare(grid, 0, 1));
    assertSame(gsq[0][0], pov.getSquare(grid, -1, 1));
    assertSame(gsq[1][2], pov.getSquare(grid, 1, 0));
    assertSame(gsq[1][1], pov.getSquare(grid, 0, 0));
    assertSame(gsq[1][0], pov.getSquare(grid, -1, 0));
    assertSame(gsq[2][2], pov.getSquare(grid, 1, -1));
    assertSame(gsq[2][1], pov.getSquare(grid, 0, -1));
    assertSame(gsq[2][0], pov.getSquare(grid, -1, -1));
  }
}