package ofd.display.fp;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import ofd.display.AbstractShapeRenderer;
import ofd.view.VDirection;

/**
 * @author david
 */
class FPWallRenderer extends AbstractShapeRenderer {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final Displayulator d;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public FPWallRenderer(Displayulator d, Color fg, Color bg) {
    super(fg, bg);
    this.d = d;
  }

  // ////////////////////////////////////////////////////////////
  // AbstractShapeRenderer

  @Override protected Shape shapeFwd(int x, int y) {
    double y0 = fwdY(y);
    double x0 = d.screenX(leftX(x), y0);
    double x1 = d.screenX(rightX(x), y0);
    double z0 = d.screenUp(y0);
    double z1 = d.screenDown(y0);
    return new Rectangle2D.Double(x0, z0, x1-x0, z1-z0);
  }

  // TODO special cases for backY == 0?
  @Override protected Shape shapeSide(int x, int y, VDirection side) {
    double xs = getX(x, side);
    double y0 = fwdY(y);
    double y1 = backY(y);
    double x0 = d.screenX(xs, y0);
    double x1 = d.screenX(xs, y1);
    double up0 = d.screenUp(y0);
    double down0 = d.screenDown(y0);
    double up1 = d.screenUp(y1);
    double down1 = d.screenDown(y1);

    GeneralPath path = new GeneralPath();
    path.moveTo(x0, up0);
    path.lineTo(x1, up1);
    path.lineTo(x1, down1);
    path.lineTo(x0, down0);
    path.lineTo(x0, up0);

    return path;
  }

  @Override protected Shape shapeBack(int x, int y) {
    return null;
  }

  // ////////////////////////////////////////////////////////////
  // Misc. private methods

  private double getX(int x, VDirection side) {
    switch (side) {
      case LEFT:
        return leftX(x);
      case RIGHT:
        return rightX(x);
      default:
        throw new IllegalArgumentException("Bad side " + side);
    }
  }

  private double leftX(int x) {
    // camera at center of square
    return x + -0.5;
  }

  private double rightX(int x) {
    // camera at center of square
    return x + 0.5;
  }

  private double fwdY(int y) {
    // camera at back of square
    return y + 1;
  }

  private double backY(int y) {
    // camera at back of square
    return y;
  }

}
