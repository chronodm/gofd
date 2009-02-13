package ofd.view;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import ofd.map.*;

import org.junit.Before;
import org.junit.Test;

public class TransformTest {

	// ////////////////////////////////////////////////////////////
	// Fields

	private Tile n;
	private Tile s;
	private Tile e;
	private Tile w;
	private MSquare mSq;
	private Grid grid;
	private MSquare[][] gsq;

	// ////////////////////////////////////////////////////////////
	// Fixture

	@Before
	public void setUp() {
		n = mock(Tile.class);
		when(n.toString()).thenReturn("north");
		
		s = mock(Tile.class);
		when(s.toString()).thenReturn("south");

		e = mock(Tile.class);
		when(e.toString()).thenReturn("east");

		w = mock(Tile.class);
		when(w.toString()).thenReturn("west");

		mSq = mock(MSquare.class);
		when(mSq.getTile(MDirection.NORTH)).thenReturn(n);
		when(mSq.getTile(MDirection.SOUTH)).thenReturn(s);
		when(mSq.getTile(MDirection.EAST)).thenReturn(e);
		when(mSq.getTile(MDirection.WEST)).thenReturn(w);
		
		gsq = new MSquare[][] {
				{mock(MSquare.class), mock(MSquare.class), mock(MSquare.class)},
				{mock(MSquare.class), mock(MSquare.class), mock(MSquare.class)},
				{mock(MSquare.class), mock(MSquare.class), mock(MSquare.class)}
			};
		grid = mock(Grid.class);
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				MSquare square = gsq[x][y];
				when(square.toString()).thenReturn("[" + x + ", " + y + "]");
				when(grid.getSquare(new Coord(x, y))).thenReturn(square);
			}
		}
		when(grid.width()).thenReturn(3);
		when(grid.height()).thenReturn(3);
	}
	
	// ////////////////////////////////////////////////////////////
	// Tests
	
	@Test
	public void testFacingNorth() {
		VSquare vSq = Transform.toVSquare(mSq, MDirection.NORTH);
		assertSame(n, vSq.getTile(VDirection.FWD));
		assertSame(s, vSq.getTile(VDirection.BACK));
		assertSame(w, vSq.getTile(VDirection.LEFT));
		assertSame(e, vSq.getTile(VDirection.RIGHT));
	}

	@Test
	public void testFacingSouth() {
		VSquare vSq = Transform.toVSquare(mSq, MDirection.SOUTH);
		assertSame(s, vSq.getTile(VDirection.FWD));
		assertSame(n, vSq.getTile(VDirection.BACK));
		assertSame(e, vSq.getTile(VDirection.LEFT));
		assertSame(w, vSq.getTile(VDirection.RIGHT));
	}

	@Test
	public void testFacingEast() {
		VSquare vSq = Transform.toVSquare(mSq, MDirection.EAST);
		assertSame(e, vSq.getTile(VDirection.FWD));
		assertSame(w, vSq.getTile(VDirection.BACK));
		assertSame(n, vSq.getTile(VDirection.LEFT));
		assertSame(s, vSq.getTile(VDirection.RIGHT));
	}

	@Test
	public void testFacingWest() {
		VSquare vSq = Transform.toVSquare(mSq, MDirection.WEST);
		assertSame(w, vSq.getTile(VDirection.FWD));
		assertSame(e, vSq.getTile(VDirection.BACK));
		assertSame(s, vSq.getTile(VDirection.LEFT));
		assertSame(n, vSq.getTile(VDirection.RIGHT));
	}

	@Test
	public void testLocation() {
		POV pov = new POV(new Coord(0, 0), MDirection.NORTH);
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, 2, 1));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, 2, 0));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, 2, 2));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 0, 1));
		assertSame(gsq[0][0], Transform.getSquare(grid, pov, 0, 0));
		assertSame(gsq[0][2], Transform.getSquare(grid, pov, 0, 2));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 1, 1));
		assertSame(gsq[1][0], Transform.getSquare(grid, pov, 1, 0));
		assertSame(gsq[1][2], Transform.getSquare(grid, pov, 1, 2));
	}
	
	@Test
	public void testLocationWrap() {
		POV pov = new POV(new Coord(0, 0), MDirection.NORTH);
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, -1, 1));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, -1, -1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 1, 1));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, 1, -1));
	}

	@Test
	public void testLocationRotateEast() {
		POV pov = new POV(new Coord(0, 0), MDirection.EAST);
		assertSame(gsq[2][1], Transform.getSquare(grid, pov, -1, -1));
		assertSame(gsq[2][0], Transform.getSquare(grid, pov, 0, -1));
		assertSame(gsq[2][2], Transform.getSquare(grid, pov, 1, -1));
		assertSame(gsq[0][1], Transform.getSquare(grid, pov, -1, 0));
		assertSame(gsq[0][0], Transform.getSquare(grid, pov, 0, 0));
		assertSame(gsq[0][2], Transform.getSquare(grid, pov, 1, 0));
		assertSame(gsq[1][1], Transform.getSquare(grid, pov, -1, 1));
		assertSame(gsq[1][0], Transform.getSquare(grid, pov, 0, 1));
		assertSame(gsq[1][2], Transform.getSquare(grid, pov, 1, 1));
	}

  @Test
  public void testLocationRotateSouth() {
    POV pov = new POV(new Coord(0, 0), MDirection.SOUTH);
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, 1, -1));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, 1, 1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, -1, -1));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, -1, 1));
  }

  @Test
  public void testLocationRotateWest() {
    POV pov = new POV(new Coord(0, 0), MDirection.WEST);
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, 1, 1));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, -1, 1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 1, -1));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, -1, -1));
  }
  
  @Test
  public void testLocationTranslate() {
    POV pov = new POV(new Coord(1, 1), MDirection.NORTH);
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, -1, 1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, -1, -1));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, 1, 1));
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, 1, -1));
  }
  
  @Test
  public void testLocationTranslateEast() {
    POV pov = new POV(new Coord(1, 1), MDirection.EAST);
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, -1, -1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, 1, -1));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, -1, 1));
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, 1, 1));
  }

  @Test
  public void testLocationTranslateSouth() {
    POV pov = new POV(new Coord(1, 1), MDirection.SOUTH);
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, 1, -1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, 1, 1));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, -1, -1));
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, -1, 1));
  }

  @Test
  public void testLocationTranslateWest() {
    POV pov = new POV(new Coord(1, 1), MDirection.WEST);
    assertSame(gsq[0][2], Transform.getSquare(grid, pov, 1, 1));
    assertSame(gsq[0][1], Transform.getSquare(grid, pov, 0, 1));
    assertSame(gsq[0][0], Transform.getSquare(grid, pov, -1, 1));
    assertSame(gsq[1][2], Transform.getSquare(grid, pov, 1, 0));
    assertSame(gsq[1][1], Transform.getSquare(grid, pov, 0, 0));
    assertSame(gsq[1][0], Transform.getSquare(grid, pov, -1, 0));
    assertSame(gsq[2][2], Transform.getSquare(grid, pov, 1, -1));
    assertSame(gsq[2][1], Transform.getSquare(grid, pov, 0, -1));
    assertSame(gsq[2][0], Transform.getSquare(grid, pov, -1, -1));
  }
}
