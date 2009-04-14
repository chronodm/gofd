package ofd.display.topdown;

import ofd.display.AbstractRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author david
 */
class TopDownDoorRenderer extends AbstractRenderer {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final TopDownWallRenderer wallRenderer;
  private final TopDownWallRenderer doorRenderer;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public TopDownDoorRenderer(Color fg, Color bg, Rectangle2D viewRect, int mapWidth, int mapHeight) {
    super(fg, bg);
    wallRenderer = new TopDownWallRenderer(fg, viewRect, mapWidth, mapHeight);
    double doorXLength = wallRenderer.getXLength() / 3;
    double doorYLength = wallRenderer.getYLength() / 3;
    double doorThickness = wallRenderer.getThickness() * 3;
    doorRenderer = new TopDownWallRenderer(fg, bg, mapHeight, wallRenderer.getXSqPx(), wallRenderer.getYSqPx(), doorXLength, doorYLength, doorThickness);
  }

  // ////////////////////////////////////////////////////////////
  // Public methods

  public void paintFwd(Graphics2D g, int x, int y) {
    wallRenderer.paintFwd(g, x, y);
    doorRenderer.paintFwd(g, x, y);
  }

  public void paintLeft(Graphics2D g, int x, int y) {
    wallRenderer.paintLeft(g, x, y);
    doorRenderer.paintLeft(g, x, y);
  }

  public void paintRight(Graphics2D g, int x, int y) {
    wallRenderer.paintRight(g, x, y);
    doorRenderer.paintRight(g, x, y);
  }

  public void paintBack(Graphics2D g, int x, int y) {
    wallRenderer.paintBack(g, x, y);
    doorRenderer.paintBack(g, x, y);
  }
}
