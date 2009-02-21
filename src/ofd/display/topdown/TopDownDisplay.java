package ofd.display.topdown;

import ofd.map.*;
import ofd.view.VDirection;
import ofd.display.TileRenderer;
import ofd.display.NullRenderer;
import ofd.display.DisplayModel;
import ofd.util.P;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.util.EnumMap;
import java.util.Map;

/**
 * A top-down display.
 */
public class TopDownDisplay extends JPanel {

  // ////////////////////////////////////////////////////////////
  // Constants

  private static final Color BACKGROUND = Color.BLACK;
  private static final Color FOREGROUND = Color.WHITE;
  private static final Font FONT = new Font("Lucida Sans Typewriter", Font.PLAIN, 9);

  // ////////////////////////////////////////////////////////////
  // Instance variables

  private final DisplayModel model;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public TopDownDisplay(DisplayModel model) {
    this.model = model;
    setBackground(BACKGROUND);
  }

  // ////////////////////////////////////////////////////////////
  // Overrides

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    MGrid grid = getGrid();

    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(FOREGROUND);

    final Rectangle2D viewRect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
    final int mapWidth = grid.xRange().size();
    final int mapHeight = grid.yRange().size();

    Map<TileType, TileRenderer> renderers = new EnumMap<TileType, TileRenderer>(TileType.class) {{
      put(TileType.WALL, new TopDownWallRenderer(FOREGROUND, BACKGROUND, viewRect, mapWidth, mapHeight));
      put(TileType.DOOR, new TopDownDoorRenderer(FOREGROUND, BACKGROUND, viewRect, mapWidth, mapHeight));
      put(TileType.NONE, new NullRenderer());
    }};

    for (int x : grid.xRange()) {
      for (int y : grid.yRange()) {
        MSquare sq = grid.getSquare(x, y);
        for (MDirection d : MDirection.values()) {
          TileType t = sq.getTile(d).type();
          VDirection v = d.v();
          renderers.get(t).paint(g2, x, y, v);
        }
        paintCoords(g2, viewRect, x, y, sq);
      }
    }
  }

  // ////////////////////////////////////////////////////////////
  // Private methods  

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

//    System.out.println(pos);
//    for (MDirection dir : MDirection.values()) {
//      TileType t = sq.getTile(dir).type();
//      System.out.println("\t" + dir + "\t" + t);
//    }
  }

  // ////////////////////////////////////////////////////////////
  // Main program

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      JFrame f = new JFrame();
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

      cp.add(display, BorderLayout.CENTER);

      f.setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
