package ofd.display.fp;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ofd.display.DisplayModel;
import ofd.map.MDirection;
import ofd.map.MGrid;
import ofd.map.MSquare;
import ofd.map.POV;
import ofd.map.TestGrid;
import ofd.map.TileType;
import ofd.util.Disposable;
import ofd.util.P;
import ofd.view.Rotation;
import ofd.view.VDirection;
import ofd.view.VSquare;
import ofd.view.View;

/**
 * A first-person display.
 */
public class FPDisplay extends JPanel implements Disposable {

  // ////////////////////////////////////////////////////////////
  // Constants

  private static final Font FONT = new Font("Lucida Sans Typewriter", Font.BOLD, 18);
  private static final Color BACKGROUND = Color.BLACK;
  private static final Color FOREGROUND = Color.WHITE;

  // ////////////////////////////////////////////////////////////
  // Instance variables
  
  private final ChangeListener listener;
  private final DisplayModel model;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public FPDisplay(DisplayModel model) {
    this.model = model;
    setBackground(BACKGROUND);
    listener = new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        repaint();
      }
    };
    model.addChangeListener(listener);
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

    POV pov = model.getPov();
    MDirection fwd = pov.getFacing();
    P p = pov.getP();
    String pos = p.toString();
    String dir = fwd.toString();
    
    int posW = fm.stringWidth(pos);
    int dirW = fm.stringWidth(dir);

    Rectangle2D viewRect = getViewRect();
    float hCen = (float) viewRect.getCenterX();
    float vCen = (float) viewRect.getCenterY();
    
    g2.drawString(pos, hCen - (posW / 2f), vCen - line / 2f);
    g2.drawString(dir, hCen - (dirW / 2f), vCen + line / 2f);
    
    System.out.println(pov);
    

    MGrid grid = model.getGrid();
    for (MSquare mSq: pov.getView(grid, 5, 5)) {
      VSquare vSq = View.see(mSq, pov.getFacing());
      for (VDirection vDir: VDirection.values()) {
        TileType tileType = vSq.getTile(vDir).type();
        FPTileRenderer renderer = new FPTileRenderer(tileType, vDir);
        P c = grid.getP(mSq);
        
        // TODO: Transform for orientation
        int dx = c.x() - p.x();
        int dy = c.y() - p.y();
        renderer.render(g2, viewRect, dx, dy, getFOV());
      }
    }

    g2.drawString(pos, hCen - (posW / 2f), vCen - line / 2f);
    g2.drawString(dir, hCen - (dirW / 2f), vCen + line / 2f);
  }

  @Override
  public void dispose() {
    model.removeChangeListener(listener);
  }

  // ////////////////////////////////////////////////////////////
  // Private

  private Rectangle2D getViewRect() {
    // TODO figure out what's wrong with this
    Insets insets = getInsets();
    return new Rectangle2D.Double(insets.left, insets.top, getWidth() - insets.right - insets.left, getHeight() - insets.top - insets.bottom);
  }

  private double getFOV() {
    Rectangle2D viewRect = getViewRect();
    return Math.asin(viewRect.getWidth() / (2 * viewRect.getHeight()));
  }

  // ////////////////////////////////////////////////////////////
  // Main program

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      JFrame f = new JFrame();
      f.setTitle(FPDisplay.class.getSimpleName());
      f.setBackground(Color.BLUE);
      f.setSize(800, 450);
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
      
      FPDisplay disp = new FPDisplay(model);
      disp.setBorder(border);

      cp.add(disp, BorderLayout.CENTER);
      
      f.setVisible(true);

      // TODO http://java.sun.com/docs/books/tutorial/uiswing/misc/keybinding.html
      disp.setFocusable(true);
      disp.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          int keyCode = e.getKeyCode();
          System.out.print(e.getKeyChar() + "\t");
          System.out.println(KeyEvent.getKeyText(keyCode));
          POV pov = model.getPov();
          if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            model.setPOV(pov.moveOne(VDirection.FWD));
          } else if (keyCode == KeyEvent.VK_S|| keyCode == KeyEvent.VK_DOWN) {
            model.setPOV(pov.moveOne(VDirection.BACK));
          } else if (keyCode == KeyEvent.VK_A) {
            model.setPOV(pov.moveOne(VDirection.LEFT));
          } else if (keyCode == KeyEvent.VK_D) {
            model.setPOV(pov.moveOne(VDirection.RIGHT));
          } else if (keyCode == KeyEvent.VK_RIGHT) {
            model.setPOV(pov.turnOne(Rotation.CLOCKWISE));
          } else if (keyCode == KeyEvent.VK_LEFT) {
            model.setPOV(pov.turnOne(Rotation.COUNTERCLOCKWISE));
          }
        }
      });
      disp.requestFocusInWindow();

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

}
