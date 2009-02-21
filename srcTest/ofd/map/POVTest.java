package ofd.map;

import static org.junit.Assert.*;

import ofd.view.Rotation;
import ofd.view.VDirection;
import ofd.util.P;

import org.junit.Before;
import org.junit.Test;

public class POVTest {

  // ////////////////////////////////////////////////////////////
  // Fields

  private MockGrid grid;
  private MSquare[][] gsq;

  // ////////////////////////////////////////////////////////////
  // Fixture

  @Before
  public void setUp() {
    int gridW = 3;
    int gridH = 3;
    grid = new MockGrid(gridW, gridH);
    gsq = grid.getGsq();
  }

  // ////////////////////////////////////////////////////////////
  // Tests

  @Test
  public void testGetSquare() {
    POV pov = new POV(new P(0, 0), MDirection.NORTH);
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
    POV pov = new POV(new P(0, 0), MDirection.NORTH);
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
  public void testGetSquareWrapWacky() {
    POV pov = new POV(new P(-3, -3), MDirection.NORTH);
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
    POV pov = new POV(new P(0, 0), MDirection.EAST);
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
    POV pov = new POV(new P(0, 0), MDirection.SOUTH);
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
    POV pov = new POV(new P(0, 0), MDirection.WEST);
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
    POV pov = new POV(new P(1, 1), MDirection.NORTH);
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
    POV pov = new POV(new P(1, 1), MDirection.EAST);
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
    POV pov = new POV(new P(1, 1), MDirection.SOUTH);
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
    POV pov = new POV(new P(1, 1), MDirection.WEST);
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
  
  @Test
  public void testMoveNorth() {
    MDirection fwd = MDirection.NORTH;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.move(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFwd());
    assertEquals(new P(0, 1), moved.getP());

    POV moved2 = moved.move(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFwd());
    assertEquals(new P(1, 1), moved2.getP());

    POV moved3 = moved2.move(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFwd());
    assertEquals(new P(1, 0), moved3.getP());

    POV moved4 = moved3.move(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFwd());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testMoveSouth() {
    MDirection fwd = MDirection.SOUTH;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.move(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFwd());
    assertEquals(new P(0, -1), moved.getP());

    POV moved2 = moved.move(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFwd());
    assertEquals(new P(-1, -1), moved2.getP());

    POV moved3 = moved2.move(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFwd());
    assertEquals(new P(-1, 0), moved3.getP());

    POV moved4 = moved3.move(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFwd());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testMoveEast() {
    MDirection fwd = MDirection.EAST;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.move(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFwd());
    assertEquals(new P(1, 0), moved.getP());

    POV moved2 = moved.move(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFwd());
    assertEquals(new P(1, -1), moved2.getP());

    POV moved3 = moved2.move(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFwd());
    assertEquals(new P(0, -1), moved3.getP());

    POV moved4 = moved3.move(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFwd());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testMoveWest() {
    MDirection fwd = MDirection.WEST;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.move(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFwd());
    assertEquals(new P(-1, 0), moved.getP());

    POV moved2 = moved.move(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFwd());
    assertEquals(new P(-1, 1), moved2.getP());

    POV moved3 = moved2.move(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFwd());
    assertEquals(new P(0, 1), moved3.getP());

    POV moved4 = moved3.move(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFwd());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testTurnCW() {
    P c = new P(1, 1);
    POV pov = new POV(c, MDirection.NORTH);

    POV turn = pov.turn(Rotation.CLOCKWISE);
    assertNotSame(turn, pov);
    assertEquals(c, turn.getP());
    assertEquals(MDirection.EAST, turn.getFwd());

    POV turn2 = turn.turn(Rotation.CLOCKWISE);
    assertNotSame(turn2, pov);
    assertEquals(c, turn2.getP());
    assertEquals(MDirection.SOUTH, turn2.getFwd());

    POV turn3 = turn2.turn(Rotation.CLOCKWISE);
    assertNotSame(turn3, pov);
    assertEquals(c, turn3.getP());
    assertEquals(MDirection.WEST, turn3.getFwd());

    POV turn4 = turn3.turn(Rotation.CLOCKWISE);
    assertNotSame(turn4, pov);
    assertEquals(c, turn4.getP());
    assertEquals(MDirection.NORTH, turn4.getFwd());
  }
  
  @Test
  public void testTurnCCW() {
    P c = new P(1, 1);
    POV pov = new POV(c, MDirection.NORTH);

    POV turn = pov.turn(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn, pov);
    assertEquals(c, turn.getP());
    assertEquals(MDirection.WEST, turn.getFwd());

    POV turn2 = turn.turn(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn2, pov);
    assertEquals(c, turn2.getP());
    assertEquals(MDirection.SOUTH, turn2.getFwd());

    POV turn3 = turn2.turn(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn3, pov);
    assertEquals(c, turn3.getP());
    assertEquals(MDirection.EAST, turn3.getFwd());

    POV turn4 = turn3.turn(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn4, pov);
    assertEquals(c, turn4.getP());
    assertEquals(MDirection.NORTH, turn4.getFwd());
  }
}