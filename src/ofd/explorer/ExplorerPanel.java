package ofd.explorer;

import ofd.display.AbstractDisplayPanel;
import static ofd.display.DisplayConstants.BACKGROUND;
import ofd.display.DisplayModel;
import ofd.display.DisplayPanelFactory;
import ofd.display.topdown.TopDownDisplay;
import ofd.map.MDirection;
import ofd.map.MGrid;
import ofd.map.POV;
import ofd.map.TestGrid;
import ofd.util.Disposable;
import ofd.util.P;

import javax.swing.*;
import java.awt.*;

/**
 * @author david
 */
public class ExplorerPanel extends JPanel implements Disposable {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final DisplayModel displayModel = new DisplayModel();
  private final KeyboardHandler keyboardHandler;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public ExplorerPanel(DisplayPanelFactory dpf) {
    super(new BorderLayout());
    setBackground(BACKGROUND);
    setFocusable(true);
    setFocusCycleRoot(true);

    AbstractDisplayPanel displayPanel = dpf.createPanel(displayModel);
    this.add(displayPanel, BorderLayout.CENTER);

    keyboardHandler = new KeyboardHandler(this);
  }

  // ////////////////////////////////////////////////////////////
  // Public methods

  public void setLocation(MGrid grid, POV pov) {
    displayModel.setGridAndPOV(grid, pov);
  }

  // ////////////////////////////////////////////////////////////
  // Overrides

  public void dispose() {
    keyboardHandler.dispose();
    displayModel.dispose();
  }

  // ////////////////////////////////////////////////////////////
  // Main program

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      JFrame f = new JFrame();
      f.setTitle(ExplorerPanel.class.getSimpleName());
      f.setBackground(Color.BLUE);
      f.setSize(770, 700);
      f.setLocationByPlatform(true);
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      Container cp = f.getContentPane();
      cp.setLayout(new BorderLayout());

      ExplorerPanel explorerPanel = new ExplorerPanel(new DisplayPanelFactory() {
        public AbstractDisplayPanel createPanel(DisplayModel model) {
          return new TopDownDisplay(model);
        }
      });
      explorerPanel.requestFocusInWindow();

      MGrid grid = new TestGrid();
      POV pov = new POV(new P(0, 0), MDirection.NORTH);

      explorerPanel.setLocation(grid, pov);

      explorerPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

      cp.add(explorerPanel, BorderLayout.CENTER);

      f.setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

}
