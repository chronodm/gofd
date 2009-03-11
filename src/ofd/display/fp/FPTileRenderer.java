package ofd.display.fp;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import ofd.map.TileType;
import ofd.view.VDirection;

public class FPTileRenderer {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final TileType type;
  private final VDirection side;

  // ////////////////////////////////////////////////////////////
  // Constructor
  
  public FPTileRenderer(TileType type, VDirection side) {
    this.type = type;
    this.side = side;
  }

  // ////////////////////////////////////////////////////////////
  // Utility methods
  
  private double theta(double x, double y) {
    double r = distance(x, y);
    return Math.acos(y / r);
  }
  
  private double distance(double x1, double y1) {
    return Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
  }
  
  // ////////////////////////////////////////////////////////////
  // FOV methods
  
  private double rEdge(double y1, double fovAngle) {
    return y1 * Math.cos(fovAngle / 2);
  }
  
  private double xEdge(double y1, double fovAngle) {
    return rEdge(y1, fovAngle) * Math.sin(fovAngle / 2);
  }
  
  private double fovWidth(double y1, double fovAngle) {
    return 2 * (xEdge(y1, fovAngle));
  }

  // ////////////////////////////////////////////////////////////
  // Rendering
  
  private double xPxPos(double x, double y1, double fovAngle, double fovWidthPx) {
    double xFrac = x / xEdge(y1, fovAngle);
    return xFrac * (fovWidthPx / 2);
  }
  
  // ////////////////////////////////////////////////////////////
  //
  
  public void render(Graphics2D g, Rectangle2D viewRect, int dx, int dy, double fovAngle) {
    if (type == TileType.NONE) {
      return;
    }

    Graphics2D g2 = (Graphics2D) g.create();
    
    double fovWidthPx = viewRect.getWidth();

    // Standing in middle of square
    double x1 = dx - 0.5; 
    double x2 = dx + 0.5;
    double y1 = dy - 0.5;
    double y2 = dy + 0.5;

    double x1y2px = xPxPos(x1, y2, fovAngle, fovWidthPx);
    double x2y2px = xPxPos(x2, y2, fovAngle, fovWidthPx);
    double z1y2px = xPxPos(-0.5, y2, fovAngle, fovWidthPx);
    double z2y2px = xPxPos(0.5, y2, fovAngle, fovWidthPx);

    double x1y1px = xPxPos(x1, y1, fovAngle, fovWidthPx);
    double x2y1px = xPxPos(x2, y1, fovAngle, fovWidthPx);
    double z1y1px = xPxPos(-0.5, y1, fovAngle, fovWidthPx);
    double z2y1px = xPxPos(0.5, y1, fovAngle, fovWidthPx);
    
    double cenX = viewRect.getCenterX();
    double cenY = viewRect.getCenterY();

    if (side == VDirection.FWD) {
      double width = x2y2px - x1y2px;
      double height = z2y2px - z1y2px;
      
      double x0 = cenX + x1y2px;
      double z0 = cenY + z1y2px;
      
      System.out.println(x0 + ", " + z0 + ", " + width + ", " + height);
      
      Rectangle2D rect = new Rectangle2D.Double(x0, z0, width, height);
      g2.draw(rect);
      
      rect = new Rectangle2D.Double(x0 + 1, z0 + 1, width - 2, height - 2);
      g2.setColor(g2.getBackground());
      g2.fill(rect);
    } else if (side == VDirection.RIGHT || side == VDirection.LEFT) {
      //// Left
      // x2y2px, z2y2px
      // x2y2px, z1y2px
      
      //// Right
      // x2y1px, z2y1px
      // x2y1px, z1y2px

      GeneralPath path = new GeneralPath();
      path.moveTo(cenX + x2y2px, cenY + z2y2px);
      path.lineTo(cenX + x2y1px, cenY + z2y1px);
      path.lineTo(cenX + x2y1px, cenY + z1y1px);
      path.lineTo(cenX + x2y2px, cenY + z1y2px);
      path.lineTo(cenX + x2y2px, cenY + z2y2px);
      
//      System.out.println((cenX + x2y2px) + ", " + (cenY + z2y2px));
//      System.out.println((cenX + x2y1px) + ", " + (cenY + z2y1px));
//      System.out.println((cenX + x2y1px) + ", " + (cenY + z1y1px));
//      System.out.println((cenX + x2y2px) + ", " + (cenY + z1y2px));
      
      g2.draw(path);

      
      path = new GeneralPath();
      path.moveTo(cenX + x2y2px + 1, cenY + z2y2px + 1);
      path.lineTo(cenX + x2y1px - 1, cenY + z2y1px + 1);
      path.lineTo(cenX + x2y1px - 1, cenY + z1y1px - 1);
      path.lineTo(cenX + x2y2px + 1, cenY + z1y2px - 1);
      path.lineTo(cenX + x2y2px + 1, cenY + z2y2px + 1);
      g2.setColor(g2.getBackground());
      g2.fill(path);
    }
    
  }
 
}
