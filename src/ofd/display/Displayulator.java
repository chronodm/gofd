package ofd.display;

/**
 * Utility class for display calculations
 * @author davidm
 */
public class Displayulator {

  // ////////////////////////////////////////////////////////////
  // Constants

  private static final double CAMERA_TO_FLLOR = -0.5;

  private static final double CAMERA_TO_CEILING = 0.5;

  // ////////////////////////////////////////////////////////////
  // Fields

  /** View width in pixels */
  private final double w;

  /** Field of view in radians*/
  private final double fov;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public Displayulator(double w, double fov) {
    this.w = w;
    this.fov = fov;
  }
  
  // ////////////////////////////////////////////////////////////
  // Private utility methods

  private double theta(double x, double y) {
    assert y > 0;
    double r = distance(x, y);
    return Math.acos(y / r);
  }
  
  private double distance(double x1, double y1) {
    return Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
  }
  
  // ////////////////////////////////////////////////////////////
  // Public methods

  public double screenX(double x, double y) {
    double theta = theta(x, y);
    double ratio = theta / (fov / 2);
    return (w / 2) + (w * ratio);
  }
  
  public double screenZPlus(double y) {
    return screenX(CAMERA_TO_CEILING, y);
  }
  
  public double screenZMinus(double y) {
    return screenX(CAMERA_TO_FLLOR, y);
  }
}
