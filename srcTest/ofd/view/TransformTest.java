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
		
		MSquare[][] gridSquares = {
				{mock(MSquare.class), mock(MSquare.class), mock(MSquare.class)},
				{mock(MSquare.class), mock(MSquare.class), mock(MSquare.class)},
				{mock(MSquare.class), mock(MSquare.class), mock(MSquare.class)}
			};
		
		grid = mock(Grid.class);
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				MSquare square = gridSquares[x][y];
				when(square.toString()).thenReturn("[" + x + ", " + y + "]");
				when(grid.getSquare(new Coord(x, y))).thenReturn(square);
			}
		}
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
	public void testMovement() {
		fail("test not implemented");
	}
}
