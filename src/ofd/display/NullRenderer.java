package ofd.display;

import java.awt.Graphics2D;

import ofd.view.VDirection;

/**
 * A renderer that doesn't render
 * @author david
 */
public class NullRenderer implements TileRenderer {

  public void paint(Graphics2D g, int x, int y, VDirection dir) {
    // Does nothing
  }

  public void paintFwd(Graphics2D g, int x, int y) {
    // Does nothing
  }

  public void paintLeft(Graphics2D g, int x, int y) {
    // Does nothing
  }

  public void paintRight(Graphics2D g, int x, int y) {
    // Does nothing
  }

  public void paintBack(Graphics2D g, int x, int y) {
    // Does nothing
  }

}
