package ofd.display.topdown;

import ofd.display.AbstractShapeRenderer;
import ofd.view.VDirection;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 * @author david
 */
class TopDownEyeRenderer extends AbstractShapeRenderer {

  // ////////////////////////////////////////////////////////////
  // Constants

  static final double DEFAULT_THICKNESS = 2;

  // ////////////////////////////////////////////////////////////
  // Fields

  private final double xSqPx;
  private final double ySqPx;
  private final double thickness;
  private final int mapHeight;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public TopDownEyeRenderer(Color fg, Color bg, Rectangle2D viewRect, int mapWidth, int mapHeight) {
    this(fg, bg, mapHeight, viewRect.getWidth() / mapWidth, viewRect.getHeight() / mapHeight, DEFAULT_THICKNESS);
  }

  private TopDownEyeRenderer(Color fg, Color bg, int mapHeight, double xSqPx, double ySqPx, double thickness) {
    super(fg, bg);
    this.mapHeight = mapHeight;
    this.xSqPx = xSqPx;
    this.ySqPx = ySqPx;
    this.thickness = thickness;
  }

  // ////////////////////////////////////////////////////////////
  // AbstractShapeRenderer

  @Override
  protected Shape shapeFwd(int x, int y) {
    BBox bbox = new BBox(x, y);

    GeneralPath path = new GeneralPath();
    path.moveTo(bbox.xCen, bbox.y1);
    path.lineTo(bbox.x1, bbox.y0);
    path.lineTo(bbox.x0, bbox.y0);
    path.lineTo(bbox.xCen, bbox.y1);

    return path;
  }

  @Override
  protected Shape shapeBack(int x, int y) {
    BBox bbox = new BBox(x, y);

    GeneralPath path = new GeneralPath();
    path.moveTo(bbox.xCen, bbox.y0);
    path.lineTo(bbox.x1, bbox.y1);
    path.lineTo(bbox.x0, bbox.y1);
    path.lineTo(bbox.xCen, bbox.y0);

    return path;
  }

  @Override
  protected Shape shapeSide(int x, int y, VDirection side) {

    BBox bbox = new BBox(x, y);

    GeneralPath path = new GeneralPath();
    if (side == VDirection.LEFT) {
      path.moveTo(bbox.x0, bbox.yCen);
      path.lineTo(bbox.x1, bbox.y0);
      path.lineTo(bbox.x1, bbox.y1);
      path.lineTo(bbox.x0, bbox.yCen);
    } else if (side == VDirection.RIGHT) {
      path.moveTo(bbox.x1, bbox.yCen);
      path.lineTo(bbox.x0, bbox.y0);
      path.lineTo(bbox.x0, bbox.y1);
      path.lineTo(bbox.x1, bbox.yCen);
    } else {
      throw new IllegalArgumentException("Bad side " + side);
    }

    return path;
  }

  // ////////////////////////////////////////////////////////////
  // Helper classes

  private class BBox {
    public final double xCen;
    public final double yCen;
    public final double x0;
    public final double x1;
    public final double y0;
    public final double y1;

    public BBox(int x, int y) {
      xCen = (x0(x) + x1(x)) / 2;
      yCen = (y0(y) + y1(y)) / 2;

      double radius = 2.5 * thickness;

      x0 = xCen - radius;
      x1 = xCen + radius;
      y0 = yCen + radius;
      y1 = yCen - radius;
    }

    // ////////////////////////////////////////////////////////////
    // Private methods

    private double x0(int x) {
      return (x * xSqPx);
    }

    private double y0(int y) {
      return -thickness + ((mapHeight - y) * ySqPx);
    }

    private double x1(int x) {
      return -thickness + (x0(x) + xSqPx);
    }

    private double y1(int y) {
      return thickness + (y0(y) - ySqPx);
    }

  }

}