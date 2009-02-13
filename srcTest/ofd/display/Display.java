package ofd.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.LineMetrics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ofd.map.Coord;
import ofd.map.Grid;
import ofd.map.MDirection;
import ofd.map.MockGrid;
import ofd.map.POV;
import ofd.util.Disposable;
import ofd.view.Rotation;
import ofd.view.VDirection;

public class Display extends JPanel implements Disposable {

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

  public Display(DisplayModel model) {
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
    MDirection fwd = pov.getFwd();
    Coord coord = pov.getCoord();
    String pos = coord.toString();
    String dir = fwd.toString();
    
    int posW = fm.stringWidth(pos);
    int dirW = fm.stringWidth(dir);
    
    float hCen = getWidth() / 2f;
    float vCen = getHeight() / 2f;
    
    g2.drawString(pos, hCen - (posW / 2f), vCen - line / 2f);
    g2.drawString(dir, hCen - (dirW / 2f), vCen + line / 2f);
    
    System.out.println(pov);
  }
  
  @Override
  public void dispose() {
    model.removeChangeListener(listener);
  }

  // ////////////////////////////////////////////////////////////
  // Main program

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      JFrame f = new JFrame();
      f.setBackground(Color.BLUE);
      f.setSize(800, 600);
      f.setLocationByPlatform(true);
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      Container cp = f.getContentPane();
      cp.setLayout(new BorderLayout());

      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border etchedBorder = BorderFactory.createEtchedBorder(Color.CYAN, Color.MAGENTA);
      CompoundBorder border = BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(emptyBorder, etchedBorder), emptyBorder);
      
      Grid grid = new MockGrid(24, 24);
      POV pov = new POV(new Coord(grid.width() / 2, grid.height() / 2), MDirection.NORTH);
      final DisplayModel model = new DisplayModel(grid, pov);
      
      Display disp = new Display(model);
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
          if (keyCode == KeyEvent.VK_W) {
            model.setPOV(pov.move(VDirection.FWD));
          } else if (keyCode == KeyEvent.VK_S) {
            model.setPOV(pov.move(VDirection.BACK));
          } else if (keyCode == KeyEvent.VK_A) {
            model.setPOV(pov.move(VDirection.LEFT));
          } else if (keyCode == KeyEvent.VK_D) {
            model.setPOV(pov.move(VDirection.RIGHT));
          } else if (keyCode == KeyEvent.VK_RIGHT) {
            model.setPOV(pov.turn(Rotation.CLOCKWISE));
          } else if (keyCode == KeyEvent.VK_LEFT) {
            model.setPOV(pov.turn(Rotation.COUNTERCLOCKWISE));
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
