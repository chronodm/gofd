package ofd.display;

import static ofd.display.DisplayConstants.BACKGROUND;
import ofd.util.Disposable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author david
 */
public abstract class AbstractDisplayPanel extends JPanel implements Disposable {

  // ////////////////////////////////////////////////////////////
  // Fields

  protected final DisplayModel model;
  private final ChangeListener modelListener;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public AbstractDisplayPanel(DisplayModel model) {
    this.model = model;
    setBackground(BACKGROUND);
    modelListener = new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        repaint();
      }
    };
    model.addChangeListener(modelListener);
  }

  // ////////////////////////////////////////////////////////////
  // Public methods

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (model != null) {
      Graphics2D g2 = (Graphics2D) g.create();
      paintDisplay(g2);
    }
  }

  @Override
  public void dispose() {
    model.removeChangeListener(modelListener);
  }

  // ////////////////////////////////////////////////////////////
  // Abstracts

  protected abstract void paintDisplay(Graphics2D g2);
}
