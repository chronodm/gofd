package ofd.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import ofd.util.P;
import ofd.util.V;
import ofd.view.Rotation;
import ofd.view.VDirection;

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
    V move8 = new V(2, 1);
    assertSame(gsq[2][1], pov.getSquare(grid, move8));
    V move7 = new V(2, 0);
    assertSame(gsq[2][0], pov.getSquare(grid, move7));
    V move6 = new V(2, 2);
    assertSame(gsq[2][2], pov.getSquare(grid, move6));
    V move5 = new V(0, 1);
    assertSame(gsq[0][1], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[0][0], pov.getSquare(grid, move4));
    V move3 = new V(0, 2);
    assertSame(gsq[0][2], pov.getSquare(grid, move3));
    V move2 = new V(1, 1);
    assertSame(gsq[1][1], pov.getSquare(grid, move2));
    V move1 = new V(1, 0);
    assertSame(gsq[1][0], pov.getSquare(grid, move1));
    V move = new V(1, 2);
    assertSame(gsq[1][2], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareWrap() {
    POV pov = new POV(new P(0, 0), MDirection.NORTH);
    int xOff2 = -1;
    V move8 = new V(xOff2, 1);
    assertSame(gsq[2][1], pov.getSquare(grid, move8));
    int xOff1 = -1;
    V move7 = new V(xOff1, 0);
    assertSame(gsq[2][0], pov.getSquare(grid, move7));
    int xOff = -1;
    int yOff2 = -1;
    V move6 = new V(xOff, yOff2);
    assertSame(gsq[2][2], pov.getSquare(grid, move6));
    V move5 = new V(0, 1);
    assertSame(gsq[0][1], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[0][0], pov.getSquare(grid, move4));
    int yOff1 = -1;
    V move3 = new V(0, yOff1);
    assertSame(gsq[0][2], pov.getSquare(grid, move3));
    V move2 = new V(1, 1);
    assertSame(gsq[1][1], pov.getSquare(grid, move2));
    V move1 = new V(1, 0);
    assertSame(gsq[1][0], pov.getSquare(grid, move1));
    int yOff = -1;
    V move = new V(1, yOff);
    assertSame(gsq[1][2], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareWrapWacky() {
    POV pov = new POV(new P(-3, -3), MDirection.NORTH);
    int xOff2 = -1;
    V move8 = new V(xOff2, 1);
    assertSame(gsq[2][1], pov.getSquare(grid, move8));
    int xOff1 = -1;
    V move7 = new V(xOff1, 0);
    assertSame(gsq[2][0], pov.getSquare(grid, move7));
    int xOff = -1;
    int yOff2 = -1;
    V move6 = new V(xOff, yOff2);
    assertSame(gsq[2][2], pov.getSquare(grid, move6));
    V move5 = new V(0, 1);
    assertSame(gsq[0][1], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[0][0], pov.getSquare(grid, move4));
    int yOff1 = -1;
    V move3 = new V(0, yOff1);
    assertSame(gsq[0][2], pov.getSquare(grid, move3));
    V move2 = new V(1, 1);
    assertSame(gsq[1][1], pov.getSquare(grid, move2));
    V move1 = new V(1, 0);
    assertSame(gsq[1][0], pov.getSquare(grid, move1));
    int yOff = -1;
    V move = new V(1, yOff);
    assertSame(gsq[1][2], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareRotateEast() {
    POV pov = new POV(new P(0, 0), MDirection.EAST);
    int xOff2 = -1;
    int yOff2 = -1;
    V move8 = new V(xOff2, yOff2);
    assertSame(gsq[2][1], pov.getSquare(grid, move8));
    int yOff1 = -1;
    V move7 = new V(0, yOff1);
    assertSame(gsq[2][0], pov.getSquare(grid, move7));
    int yOff = -1;
    V move6 = new V(1, yOff);
    assertSame(gsq[2][2], pov.getSquare(grid, move6));
    int xOff1 = -1;
    V move5 = new V(xOff1, 0);
    assertSame(gsq[0][1], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[0][0], pov.getSquare(grid, move4));
    V move3 = new V(1, 0);
    assertSame(gsq[0][2], pov.getSquare(grid, move3));
    int xOff = -1;
    V move2 = new V(xOff, 1);
    assertSame(gsq[1][1], pov.getSquare(grid, move2));
    V move1 = new V(0, 1);
    assertSame(gsq[1][0], pov.getSquare(grid, move1));
    V move = new V(1, 1);
    assertSame(gsq[1][2], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareRotateSouth() {
    POV pov = new POV(new P(0, 0), MDirection.SOUTH);
    int yOff2 = -1;
    V move8 = new V(1, yOff2);
    assertSame(gsq[2][1], pov.getSquare(grid, move8));
    V move7 = new V(1, 0);
    assertSame(gsq[2][0], pov.getSquare(grid, move7));
    V move6 = new V(1, 1);
    assertSame(gsq[2][2], pov.getSquare(grid, move6));
    int yOff1 = -1;
    V move5 = new V(0, yOff1);
    assertSame(gsq[0][1], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[0][0], pov.getSquare(grid, move4));
    V move3 = new V(0, 1);
    assertSame(gsq[0][2], pov.getSquare(grid, move3));
    int xOff2 = -1;
    int yOff = -1;
    V move2 = new V(xOff2, yOff);
    assertSame(gsq[1][1], pov.getSquare(grid, move2));
    int xOff1 = -1;
    V move1 = new V(xOff1, 0);
    assertSame(gsq[1][0], pov.getSquare(grid, move1));
    int xOff = -1;
    V move = new V(xOff, 1);
    assertSame(gsq[1][2], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareRotateWest() {
    POV pov = new POV(new P(0, 0), MDirection.WEST);
    V move8 = new V(1, 1);
    assertSame(gsq[2][1], pov.getSquare(grid, move8));
    V move7 = new V(0, 1);
    assertSame(gsq[2][0], pov.getSquare(grid, move7));
    int xOff2 = -1;
    V move6 = new V(xOff2, 1);
    assertSame(gsq[2][2], pov.getSquare(grid, move6));
    V move5 = new V(1, 0);
    assertSame(gsq[0][1], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[0][0], pov.getSquare(grid, move4));
    int xOff1 = -1;
    V move3 = new V(xOff1, 0);
    assertSame(gsq[0][2], pov.getSquare(grid, move3));
    int yOff2 = -1;
    V move2 = new V(1, yOff2);
    assertSame(gsq[1][1], pov.getSquare(grid, move2));
    int yOff1 = -1;
    V move1 = new V(0, yOff1);
    assertSame(gsq[1][0], pov.getSquare(grid, move1));
    int xOff = -1;
    int yOff = -1;
    V move = new V(xOff, yOff);
    assertSame(gsq[1][2], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareTranslate() {
    POV pov = new POV(new P(1, 1), MDirection.NORTH);
    int xOff2 = -1;
    V move8 = new V(xOff2, 1);
    assertSame(gsq[0][2], pov.getSquare(grid, move8));
    int xOff1 = -1;
    V move7 = new V(xOff1, 0);
    assertSame(gsq[0][1], pov.getSquare(grid, move7));
    int xOff = -1;
    int yOff2 = -1;
    V move6 = new V(xOff, yOff2);
    assertSame(gsq[0][0], pov.getSquare(grid, move6));
    V move5 = new V(0, 1);
    assertSame(gsq[1][2], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[1][1], pov.getSquare(grid, move4));
    int yOff1 = -1;
    V move3 = new V(0, yOff1);
    assertSame(gsq[1][0], pov.getSquare(grid, move3));
    V move2 = new V(1, 1);
    assertSame(gsq[2][2], pov.getSquare(grid, move2));
    V move1 = new V(1, 0);
    assertSame(gsq[2][1], pov.getSquare(grid, move1));
    int yOff = -1;
    V move = new V(1, yOff);
    assertSame(gsq[2][0], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareTranslateEast() {
    POV pov = new POV(new P(1, 1), MDirection.EAST);
    int xOff2 = -1;
    int yOff2 = -1;
    V move8 = new V(xOff2, yOff2);
    assertSame(gsq[0][2], pov.getSquare(grid, move8));
    int yOff1 = -1;
    V move7 = new V(0, yOff1);
    assertSame(gsq[0][1], pov.getSquare(grid, move7));
    int yOff = -1;
    V move6 = new V(1, yOff);
    assertSame(gsq[0][0], pov.getSquare(grid, move6));
    int xOff1 = -1;
    V move5 = new V(xOff1, 0);
    assertSame(gsq[1][2], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[1][1], pov.getSquare(grid, move4));
    V move3 = new V(1, 0);
    assertSame(gsq[1][0], pov.getSquare(grid, move3));
    int xOff = -1;
    V move2 = new V(xOff, 1);
    assertSame(gsq[2][2], pov.getSquare(grid, move2));
    V move1 = new V(0, 1);
    assertSame(gsq[2][1], pov.getSquare(grid, move1));
    V move = new V(1, 1);
    assertSame(gsq[2][0], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareTranslateSouth() {
    POV pov = new POV(new P(1, 1), MDirection.SOUTH);
    int yOff2 = -1;
    V move8 = new V(1, yOff2);
    assertSame(gsq[0][2], pov.getSquare(grid, move8));
    V move7 = new V(1, 0);
    assertSame(gsq[0][1], pov.getSquare(grid, move7));
    V move6 = new V(1, 1);
    assertSame(gsq[0][0], pov.getSquare(grid, move6));
    int yOff1 = -1;
    V move5 = new V(0, yOff1);
    assertSame(gsq[1][2], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[1][1], pov.getSquare(grid, move4));
    V move3 = new V(0, 1);
    assertSame(gsq[1][0], pov.getSquare(grid, move3));
    int xOff2 = -1;
    int yOff = -1;
    V move2 = new V(xOff2, yOff);
    assertSame(gsq[2][2], pov.getSquare(grid, move2));
    int xOff1 = -1;
    V move1 = new V(xOff1, 0);
    assertSame(gsq[2][1], pov.getSquare(grid, move1));
    int xOff = -1;
    V move = new V(xOff, 1);
    assertSame(gsq[2][0], pov.getSquare(grid, move));
  }

  @Test
  public void testGetSquareTranslateWest() {
    POV pov = new POV(new P(1, 1), MDirection.WEST);
    V move8 = new V(1, 1);
    assertSame(gsq[0][2], pov.getSquare(grid, move8));
    V move7 = new V(0, 1);
    assertSame(gsq[0][1], pov.getSquare(grid, move7));
    int xOff2 = -1;
    V move6 = new V(xOff2, 1);
    assertSame(gsq[0][0], pov.getSquare(grid, move6));
    V move5 = new V(1, 0);
    assertSame(gsq[1][2], pov.getSquare(grid, move5));
    V move4 = new V(0, 0);
    assertSame(gsq[1][1], pov.getSquare(grid, move4));
    int xOff1 = -1;
    V move3 = new V(xOff1, 0);
    assertSame(gsq[1][0], pov.getSquare(grid, move3));
    int yOff2 = -1;
    V move2 = new V(1, yOff2);
    assertSame(gsq[2][2], pov.getSquare(grid, move2));
    int yOff1 = -1;
    V move1 = new V(0, yOff1);
    assertSame(gsq[2][1], pov.getSquare(grid, move1));
    int xOff = -1;
    int yOff = -1;
    V move = new V(xOff, yOff);
    assertSame(gsq[2][0], pov.getSquare(grid, move));
  }
  
  @Test
  public void testMoveNorth() {
    MDirection fwd = MDirection.NORTH;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.moveOne(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFacing());
    assertEquals(new P(0, 1), moved.getP());

    POV moved2 = moved.moveOne(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFacing());
    assertEquals(new P(1, 1), moved2.getP());

    POV moved3 = moved2.moveOne(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFacing());
    assertEquals(new P(1, 0), moved3.getP());

    POV moved4 = moved3.moveOne(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFacing());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testMoveSouth() {
    MDirection fwd = MDirection.SOUTH;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.moveOne(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFacing());
    assertEquals(new P(0, -1), moved.getP());

    POV moved2 = moved.moveOne(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFacing());
    assertEquals(new P(-1, -1), moved2.getP());

    POV moved3 = moved2.moveOne(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFacing());
    assertEquals(new P(-1, 0), moved3.getP());

    POV moved4 = moved3.moveOne(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFacing());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testMoveEast() {
    MDirection fwd = MDirection.EAST;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.moveOne(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFacing());
    assertEquals(new P(1, 0), moved.getP());

    POV moved2 = moved.moveOne(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFacing());
    assertEquals(new P(1, -1), moved2.getP());

    POV moved3 = moved2.moveOne(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFacing());
    assertEquals(new P(0, -1), moved3.getP());

    POV moved4 = moved3.moveOne(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFacing());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testMoveWest() {
    MDirection fwd = MDirection.WEST;
    POV pov = new POV(new P(0, 0), fwd);
    
    POV moved = pov.moveOne(VDirection.FWD);
    assertNotSame(moved, pov);
    assertSame(fwd, moved.getFacing());
    assertEquals(new P(-1, 0), moved.getP());

    POV moved2 = moved.moveOne(VDirection.RIGHT);
    assertNotSame(moved2, moved);
    assertSame(fwd, moved2.getFacing());
    assertEquals(new P(-1, 1), moved2.getP());

    POV moved3 = moved2.moveOne(VDirection.BACK);
    assertNotSame(moved3, moved2);
    assertSame(fwd, moved3.getFacing());
    assertEquals(new P(0, 1), moved3.getP());

    POV moved4 = moved3.moveOne(VDirection.LEFT);
    assertNotSame(moved4, moved3);
    assertSame(fwd, moved4.getFacing());
    assertEquals(new P(0, 0), moved4.getP());
  }

  @Test
  public void testTurnCW() {
    P c = new P(1, 1);
    POV pov = new POV(c, MDirection.NORTH);

    POV turn = pov.turnOne(Rotation.CLOCKWISE);
    assertNotSame(turn, pov);
    assertEquals(c, turn.getP());
    assertEquals(MDirection.EAST, turn.getFacing());

    POV turn2 = turn.turnOne(Rotation.CLOCKWISE);
    assertNotSame(turn2, pov);
    assertEquals(c, turn2.getP());
    assertEquals(MDirection.SOUTH, turn2.getFacing());

    POV turn3 = turn2.turnOne(Rotation.CLOCKWISE);
    assertNotSame(turn3, pov);
    assertEquals(c, turn3.getP());
    assertEquals(MDirection.WEST, turn3.getFacing());

    POV turn4 = turn3.turnOne(Rotation.CLOCKWISE);
    assertNotSame(turn4, pov);
    assertEquals(c, turn4.getP());
    assertEquals(MDirection.NORTH, turn4.getFacing());
  }
  
  @Test
  public void testTurnCCW() {
    P c = new P(1, 1);
    POV pov = new POV(c, MDirection.NORTH);

    POV turn = pov.turnOne(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn, pov);
    assertEquals(c, turn.getP());
    assertEquals(MDirection.WEST, turn.getFacing());

    POV turn2 = turn.turnOne(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn2, pov);
    assertEquals(c, turn2.getP());
    assertEquals(MDirection.SOUTH, turn2.getFacing());

    POV turn3 = turn2.turnOne(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn3, pov);
    assertEquals(c, turn3.getP());
    assertEquals(MDirection.EAST, turn3.getFacing());

    POV turn4 = turn3.turnOne(Rotation.COUNTERCLOCKWISE);
    assertNotSame(turn4, pov);
    assertEquals(c, turn4.getP());
    assertEquals(MDirection.NORTH, turn4.getFacing());
  }
}