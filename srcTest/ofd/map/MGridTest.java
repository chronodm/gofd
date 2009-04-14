package ofd.map;

import ofd.util.IRange;
import ofd.util.P;
import ofd.util.Range;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

/**
 * @author david
 */
public class MGridTest {

  // ////////////////////////////////////////////////////////////
  // Fields

  private MGrid delegate;
  private MGrid grid;
  private P sw;
  private P se;
  private P nw;
  private P ne;
  private P mid;
  private MSquare sSW;
  private MSquare sSE;
  private MSquare sNW;
  private MSquare sNE;
  private MSquare sMid;

  // ////////////////////////////////////////////////////////////
  // Fixture

  @Before
  public void setUp() {
    delegate = mock(MGrid.class);
    grid = new TestGrid(delegate);

    Range xRange = new Range(0, 7);
    Range yRange = new Range(0, 11);

    when(delegate.xRange()).thenReturn(xRange);
    when(delegate.yRange()).thenReturn(yRange);

    sw = new P(0, 0);
    se = new P(6, 0);
    nw = new P(0, 10);
    ne = new P(6, 10);
    mid = new P(3, 5);

    sSW = mock(MSquare.class);
    when(sSW.toString()).thenReturn("S:" + sw.toString());
    sSE = mock(MSquare.class);
    when(sSE.toString()).thenReturn("S:" + se.toString());
    sNW = mock(MSquare.class);
    when(sNW.toString()).thenReturn("S:" + nw.toString());
    sNE = mock(MSquare.class);
    when(sNE.toString()).thenReturn("S:" + ne.toString());
    sMid = mock(MSquare.class);
    when(sMid.toString()).thenReturn("S:" + mid.toString());

    when(delegate.get(sw)).thenReturn(sSW);
    when(delegate.get(se)).thenReturn(sSE);
    when(delegate.get(nw)).thenReturn(sNW);
    when(delegate.get(ne)).thenReturn(sNE);
    when(delegate.get(mid)).thenReturn(sMid);
  }

  // ////////////////////////////////////////////////////////////
  // Test methods

  @Test
  public void testGetSquareSimple() {
    assertSame(sSW, grid.get(sw));
    assertSame(sSE, grid.get(se));
    assertSame(sNW, grid.get(nw));
    assertSame(sNE, grid.get(ne));
    assertSame(sMid, grid.get(mid));
  }

  @Test
  public void testGetSquareWrapSW() {
    List<P> ps = Arrays.asList(
            new P(7, 0),
            new P(0, 11),
            new P(-7, 0),
            new P(0, -11),
            new P(7, 11),
            new P(-7, -11),
            new P(7, -11),
            new P(-7, 11)
    );

    for (P p : ps) {
      assertSame("Wrong square for " + p, sSW, grid.getSquare(p));
    }
  }

  @Test
  public void testGetSquareWrapSE() {
    List<P> ps = Arrays.asList(
            new P(13, 0),
            new P(6, 11),
            new P(-8, 0),
            new P(6, -11),
            new P(13, 11),
            new P(-8, -11),
            new P(13, -11),
            new P(-8, 11)
    );

    for (P p : ps) {
      assertSame("Wrong square for " + p, sSE, grid.getSquare(p));
    }
  }

  @Test
  public void testGetSquareWrapNW() {
    List<P> ps = Arrays.asList(
            new P(7, 10),
            new P(0, 21),
            new P(-7, 10),
            new P(0, -12),
            new P(7, 21),
            new P(-7, -12),
            new P(7, -12),
            new P(-7, 21)
    );

    for (P p : ps) {
      assertSame("Wrong square for " + p, sNW, grid.getSquare(p));
    }
  }

  @Test
  public void testGetSquareWrapNE() {
    List<P> ps = Arrays.asList(
            new P(13, 10),
            new P(6, 21),
            new P(-8, 10),
            new P(6, -12),
            new P(13, 21),
            new P(-8, -12),
            new P(13, -12),
            new P(-8, 21)
    );

    for (P p : ps) {
      assertSame("Wrong square for " + p, sNE, grid.getSquare(p));
    }
  }

  @Test
  public void testGetSquareWrapMid() {
    List<P> ps = Arrays.asList(
            new P(10, 5),
            new P(3, 16),
            new P(-4, 5),
            new P(3, -6),
            new P(10, 16),
            new P(-4, -6),
            new P(10, -6),
            new P(-4, 16)
    );

    for (P p : ps) {
      assertSame("Wrong square for " + p, sMid, grid.getSquare(p));
    }
  }

  // ////////////////////////////////////////////////////////////
  // Helper classes

  /**
   * Simple implementation that uses a delegate to mock necessary
   * abstract superclass methods.
   */
  private static class TestGrid extends MGrid {
    private final MGrid delegate;

    public TestGrid(MGrid delegate) {
      this.delegate = delegate;
    }

    public IRange xRange() {
      return delegate.xRange();
    }

    public IRange yRange() {
      return delegate.yRange();
    }

    public MSquare get(P p) {
      return delegate.get(p);
    }
  }
}
