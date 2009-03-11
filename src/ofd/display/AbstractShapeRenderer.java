package ofd.display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import ofd.view.VDirection;

/**
 * @author david
 */
public abstract class AbstractShapeRenderer extends AbstractRenderer {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final Stroke stroke;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public AbstractShapeRenderer(Color fg, Color bg) {
    super(fg, bg);
    stroke = new BasicStroke(2);
  }

  // ////////////////////////////////////////////////////////////
  // TileRenderer

  @Override public void paintFwd(Graphics2D g, int x, int y) {
    paint(g, shapeFwd(x, y));
  }

  @Override public void paintLeft(Graphics2D g, int x, int y) {
    paint(g, shapeSide(x, y, VDirection.LEFT));
  }

  @Override public void paintRight(Graphics2D g, int x, int y) {
    paint(g, shapeSide(x, y, VDirection.RIGHT));
  }

  @Override public void paintBack(Graphics2D g, int x, int y) {
    paint(g, shapeBack(x, y));
  }

  // ////////////////////////////////////////////////////////////
  // Private methods

  private void paint(Graphics2D g, Shape shape) {
    if (shape != null) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setColor(fg);
      g2.setStroke(stroke);
      g2.draw(shape);
      g2.setColor(bg);
      g2.fill(shape);
    }
  }

  // ////////////////////////////////////////////////////////////
  // Abstracts

  protected abstract Shape shapeFwd(int x, int y);

  protected abstract Shape shapeBack(int x, int y);

  protected abstract Shape shapeSide(int x, int y, VDirection side);
}
