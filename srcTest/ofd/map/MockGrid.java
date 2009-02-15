/**
 * 
 */
package ofd.map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockGrid extends Grid {

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
  public MSquare getSquare(Coord coord) {
    return gsq[coord.x()][coord.y()];
  }

  @Override
  public int height() {
    return gridH;
  }

  @Override
  public int width() {
    return gridW;
  }
  }