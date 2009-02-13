package ofd.view;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import ofd.map.MDirection;
import ofd.map.MSquare;
import ofd.map.Tile;

import org.junit.Before;
import org.junit.Test;

public class ViewTest {

  // ////////////////////////////////////////////////////////////
  // Fields

  private Tile n;
  private Tile s;
  private Tile e;
  private Tile w;
  private MSquare mSq;

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
  }

  // ////////////////////////////////////////////////////////////
  // Tests

  @Test
  public void testSeeFacingNorth() {
    VSquare vSq = View.see(mSq, MDirection.NORTH);
    assertSame(n, vSq.getTile(VDirection.FWD));
    assertSame(s, vSq.getTile(VDirection.BACK));
    assertSame(w, vSq.getTile(VDirection.LEFT));
    assertSame(e, vSq.getTile(VDirection.RIGHT));
  }

  @Test
  public void testSeeFacingSouth() {
    VSquare vSq = View.see(mSq, MDirection.SOUTH);
    assertSame(s, vSq.getTile(VDirection.FWD));
    assertSame(n, vSq.getTile(VDirection.BACK));
    assertSame(e, vSq.getTile(VDirection.LEFT));
    assertSame(w, vSq.getTile(VDirection.RIGHT));
  }

  @Test
  public void testSeeFacingEast() {
    VSquare vSq = View.see(mSq, MDirection.EAST);
    assertSame(e, vSq.getTile(VDirection.FWD));
    assertSame(w, vSq.getTile(VDirection.BACK));
    assertSame(n, vSq.getTile(VDirection.LEFT));
    assertSame(s, vSq.getTile(VDirection.RIGHT));
  }

  @Test
  public void testSeeFacingWest() {
    VSquare vSq = View.see(mSq, MDirection.WEST);
    assertSame(w, vSq.getTile(VDirection.FWD));
    assertSame(e, vSq.getTile(VDirection.BACK));
    assertSame(s, vSq.getTile(VDirection.LEFT));
    assertSame(n, vSq.getTile(VDirection.RIGHT));
  }
}
