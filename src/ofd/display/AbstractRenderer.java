package ofd.display;

import ofd.view.VDirection;

import java.awt.*;

/**
 * @author david
 */
public abstract class AbstractRenderer implements TileRenderer {

  // ////////////////////////////////////////////////////////////
  // Fields

  protected final Color fg;
  protected final Color bg;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public AbstractRenderer(Color fg, Color bg) {
    this.fg = fg;
    this.bg = bg;
  }

  // ////////////////////////////////////////////////////////////
  // TileRenderer

  @Override public void paint(Graphics2D g, int x, int y, VDirection dir) {
    if (VDirection.FWD == dir) {
      paintFwd(g, x, y);
    } else if (VDirection.LEFT == dir) {
      paintLeft(g, x, y);
    } else if (VDirection.RIGHT == dir) {
      paintRight(g, x, y);
    } else if (VDirection.BACK == dir) {
      paintBack(g, x, y);
    } else {
      throw new IllegalArgumentException("Bad direction " + dir);
    }
  }
}
