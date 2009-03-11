package ofd.display.topdown;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import ofd.display.AbstractShapeRenderer;
import ofd.view.VDirection;

/**
 * @author david
 */
class TopDownWallRenderer extends AbstractShapeRenderer {

  // ////////////////////////////////////////////////////////////
  // Constants

  static final double DEFAULT_THICKNESS = 3;

  // ////////////////////////////////////////////////////////////
  // Fields

  private final double xSqPx;
  private final double ySqPx;
  private final double xLength;
  private final double yLength;
  private final double thickness;
  private final int mapHeight;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public TopDownWallRenderer(Color fg, Color bg, Rectangle2D viewRect, int mapWidth, int mapHeight) {
    this(fg, bg, mapHeight, viewRect.getWidth() / mapWidth, viewRect.getHeight() / mapHeight, DEFAULT_THICKNESS);
  }

  public TopDownWallRenderer(Color fg, Color bg, int mapHeight, double xSqPx, double ySqPx, double thickness){
    this(fg, bg, mapHeight, xSqPx, ySqPx, xSqPx, ySqPx, thickness);
  }

  public TopDownWallRenderer(Color fg, Color bg, int mapHeight, double xSqPx, double ySqPx, double xLength, double yLength, double thickness){
    super(fg, bg);
    this.mapHeight = mapHeight;
    this.xSqPx = xSqPx;
    this.ySqPx = ySqPx;
    this.xLength = xLength;
    this.yLength = yLength;
    this.thickness = thickness;
  }

  // ////////////////////////////////////////////////////////////
  // Accessors

  public double getXSqPx() {
    return xSqPx;
  }

  public double getYSqPx() {
    return ySqPx;
  }

  public double getXLength() {
    return xLength;
  }

  public double getYLength() {
    return yLength;
  }

  public double getThickness() {
    return thickness;
  }

  // ////////////////////////////////////////////////////////////
  // AbstractShapeRenderer

  @Override protected Shape shapeFwd(int x, int y) {
    double xOff = (xSqPx - xLength) / 2;
    return new Rectangle2D.Double(x0(x) + xOff, y1(y), xLength, thickness);
  }

  @Override protected Shape shapeBack(int x, int y) {
    double xOff = (xSqPx - xLength) / 2;
    return new Rectangle2D.Double(x0(x) + xOff, y0(y), xLength, thickness);
  }

  @Override protected Shape shapeSide(int x, int y, VDirection side) {
    double yOff = (ySqPx - yLength) / 2;
    switch (side) {
      case LEFT:
        return new Rectangle2D.Double(x0(x), y1(y) + yOff, thickness, yLength);
      case RIGHT:
        return new Rectangle2D.Double(x1(x), y1(y) + yOff, thickness, yLength);
      default:
        throw new IllegalArgumentException("Bad side " + side);
    }
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
