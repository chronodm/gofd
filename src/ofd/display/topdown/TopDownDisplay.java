package ofd.display.topdown;

import ofd.display.AbstractDisplayPanel;
import static ofd.display.DisplayConstants.*;
import ofd.display.DisplayModel;
import ofd.display.RendererFactory;
import ofd.map.*;
import ofd.util.P;
import ofd.view.VDirection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 * A top-down display.
 */
public class TopDownDisplay extends AbstractDisplayPanel {

  // ////////////////////////////////////////////////////////////
  // Constructor

  public TopDownDisplay(DisplayModel model) {
    super(model);
  }

  // ////////////////////////////////////////////////////////////
  // Overrides

  @Override
  protected void paintDisplay(Graphics2D g2) {
    MGrid grid = getGrid();

    final Rectangle2D viewRect = getViewRect();

    g2.setClip(new Rectangle2D.Double(viewRect.getX() - 1, viewRect.getY() - 1, viewRect.getWidth() + 2, viewRect.getHeight() + 2));
    g2.translate(viewRect.getX(), viewRect.getY());
    g2.setColor(FOREGROUND);

    POV pov = model.getPov();

    final int mapWidth = grid.xRange().size();
    final int mapHeight = grid.yRange().size();

    RendererFactory renderers = RendererFactory.builder()
            .add(TileType.WALL, new TopDownWallRenderer(FOREGROUND, viewRect, mapWidth, mapHeight))
            .add(TileType.DOOR, new TopDownDoorRenderer(FOREGROUND, BACKGROUND, viewRect, mapWidth, mapHeight))
            .create();

    TopDownEyeRenderer eye = new TopDownEyeRenderer(FOREGROUND, BACKGROUND, viewRect, mapWidth, mapHeight);

    for (int x : grid.xRange()) {
      for (int y : grid.yRange()) {
        P p = new P(x, y);

        if (p.equals(pov.getP())) {
          MDirection facing = pov.getFacing();
          VDirection v = facing.v();
          eye.paint(g2, x, y, v);
        }

        MSquare sq = grid.getSquare(p);
        for (MDirection d : MDirection.values()) {
          TileType t = sq.getTile(d).type();
          VDirection v = d.v();
          renderers.getRenderer(t).paint(g2, x, y, v);
        }
        paintCoords(g2, viewRect, x, y, sq);
      }
    }
  }

  // ////////////////////////////////////////////////////////////
  // Private methods

  private Rectangle2D getViewRect() {
    Insets insets = getInsets();
    return new Rectangle2D.Double(insets.left, insets.top, getWidth() - insets.right - insets.left, getHeight() - insets.top - insets.bottom);
  }

  private MGrid getGrid() {
    return model.getGrid();
  }

  private void paintCoords(Graphics2D g2, Rectangle2D viewRect, int x, int y, MSquare sq) {
    g2.setFont(FONT);

    MGrid grid = getGrid();
    int mapWidth = grid.xRange().size();
    int mapHeight = grid.yRange().size();
    FontMetrics fm = g2.getFontMetrics();
    LineMetrics lm = fm.getLineMetrics("M", g2);
    float em = lm.getHeight();
    float line = 1.2f * em;
    double xSqPx = viewRect.getWidth() / mapWidth;
    double ySqPx = viewRect.getHeight() / mapHeight;
    double th = 3;
    double x0 = (x * xSqPx);
    double y0 = -th + ((mapHeight - y) * ySqPx);
    String pos = x + ", " + y;
    g2.drawString(pos, (float) x0 + line, (float) (y0 - line));
  }

  // ////////////////////////////////////////////////////////////
  // Main program

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      JFrame f = new JFrame();
      f.setTitle(TopDownDisplay.class.getSimpleName());
      f.setBackground(Color.BLUE);
      f.setSize(770, 700);
      f.setLocationByPlatform(true);
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      Container cp = f.getContentPane();
      cp.setLayout(new BorderLayout());

      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border etchedBorder = BorderFactory.createEtchedBorder(Color.CYAN, Color.MAGENTA);
      CompoundBorder border = BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(emptyBorder, etchedBorder), emptyBorder);

      MGrid grid = new TestGrid();
      POV pov = new POV(new P(0, 0), MDirection.NORTH);
      final DisplayModel model = new DisplayModel(grid, pov);
      TopDownDisplay display = new TopDownDisplay(model);

      display.setBorder(border);

      cp.add(display, BorderLayout.CENTER);

      f.setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
