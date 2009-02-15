package ofd.display;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.border.*;

import ofd.map.*;
import static ofd.map.MDirection.*;
import static ofd.map.TileType.*;

public class MapDisplay extends JPanel {

  // ////////////////////////////////////////////////////////////
  // Constants

  private static final Color BACKGROUND = Color.BLACK;
  private static final Color FOREGROUND = Color.WHITE;
  private static final Font FONT = new Font("Lucida Sans Typewriter", Font.PLAIN, 9);
  
  // ////////////////////////////////////////////////////////////
  // Instance variables

  private final Grid grid;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public MapDisplay(Grid grid) {
    this.grid = grid;
    setBackground(BACKGROUND);
  }
  
  // ////////////////////////////////////////////////////////////
  // Overrides

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(FOREGROUND);
    g2.setFont(FONT);

    FontMetrics fm = g2.getFontMetrics();
    LineMetrics lm = fm.getLineMetrics("M", g);
    float em = lm.getHeight();
    float line = 1.2f * em;

    Rectangle2D viewRect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);

    double xSqPx = viewRect.getWidth() / grid.width();
    double ySqPx = viewRect.getHeight() / grid.height();
    
    for (int x = 0; x < grid.width(); x++) {
      for (int y = 0; y < grid.height(); y++) {
        MSquare sq = grid.getSquare(x, y);
        
        double th = 3;
        
        double x0 = (x * xSqPx);
        double y0 = -th + ((grid.height() - y) * ySqPx);
        double x1 = -th + (x0 + xSqPx);
        double y1 = th + (y0 - ySqPx);
        
        TileType n = sq.getTile(NORTH).type();
        TileType e = sq.getTile(EAST).type();
        TileType s = sq.getTile(SOUTH).type();
        TileType w = sq.getTile(WEST).type();
        
        float hCen = (float) (x0 + xSqPx / 2);
        float vCen = (float) (y0 + ySqPx / 2);

        String pos = x + ", " + y;
        int posW = fm.stringWidth(pos);
//        g2.drawString(pos, hCen - (posW / 2f), vCen - line / 2f);
        g2.drawString(pos, (float) x0 + line, (float) (y0 - line));
        
        System.out.println(pos);
        for (MDirection dir: MDirection.values()) {
          TileType t = sq.getTile(dir).type();
          System.out.println("\t" + dir + "\t" + t);
        }
        
        
        double yDoorPx = (y0 - y1) / 3;
        double xDoorPx = (x1 - x0) / 3;
        double yDoorTh = yDoorPx / 3;
        double xDoorTh = xDoorPx / 3;
        
        if (n != NONE) {
          Rectangle2D rect = new Rectangle2D.Double(x0, y1, xSqPx, th);
          g2.draw(rect);
          if (n == DOOR) {
            rect = new Rectangle2D.Double(x0 + xDoorPx, y1 + th, xDoorPx, yDoorTh);
            System.out.println("\t\t" + rect.getX() + ", " + rect.getY() + ": " + rect.getWidth() + ", " + rect.getHeight());
            g2.draw(rect);
          }
        }
        if (s != NONE) {
          Rectangle2D rect = new Rectangle2D.Double(x0, y0, xSqPx, th);
          g2.draw(rect);
          if (s == DOOR) {
            rect = new Rectangle2D.Double(x0 + xDoorPx, y0 - yDoorTh, xDoorPx, yDoorTh);
            System.out.println("\t\t" + rect.getX() + ", " + rect.getY() + ": " + rect.getWidth() + ", " + rect.getHeight());
            g2.draw(rect);
          }
        }
        if (e != NONE) {
          Rectangle2D rect = new Rectangle2D.Double(x1, y1, th, ySqPx);
          g2.draw(rect);
          if (e == DOOR) {
            rect = new Rectangle2D.Double(x1 - xDoorTh, y1 + yDoorPx, xDoorTh, yDoorPx);
            System.out.println("\t\t" + rect.getX() + ", " + rect.getY() + ": " + rect.getWidth() + ", " + rect.getHeight());
            g2.draw(rect);
          }
        }
        if (w != NONE) {
          Rectangle2D rect = new Rectangle2D.Double(x0, y1, th, ySqPx);
          g2.draw(rect);
          if (w == DOOR) {
            rect = new Rectangle2D.Double(x0 + th, y1 + yDoorPx, xDoorTh, yDoorPx);
            System.out.println("\t\t" + rect.getX() + ", " + rect.getY() + ": " + rect.getWidth() + ", " + rect.getHeight());
            g2.draw(rect);
          }
        }
      }
    }
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

      Grid grid = new TestGrid();
      MapDisplay display = new MapDisplay(grid);
      
      cp.add(display, BorderLayout.CENTER);
      
      f.setVisible(true);
    
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

}
