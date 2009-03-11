/**
 *
 */
package ofd.map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import ofd.util.P;
import ofd.util.Range;

public class MockGrid extends MGrid {

  private final MSquare[][] gsq;
  private final int gridW;
  private final int gridH;

  public MockGrid(int gridW, int gridH) {
    this.gridW = gridW;
    this.gridH = gridH;
    gsq = new MSquare[gridW][gridH];
    for (int x = 0; x < gridW; x++) {
      for (int y = 0; y < gridH; y++) {
        MSquare square = mock(MSquare.class);
        gsq[x][y] = square;
        when(square.toString()).thenReturn("[" + x + ", " + y + "]");
      }
    }
  }

  public MSquare[][] getGsq() {
    return gsq;
  }

  @Override
  public MSquare get(P p) {
    return gsq[p.x()][p.y()];
  }

  @Override
  public Range yRange() {
    return new Range(0, gridH);
  }

  @Override
  public Range xRange() {
    return new Range(0, gridW);
  }
}