package ofd.display;

import ofd.view.VDirection;

import java.awt.*;

/**
 * @author david
 */
public interface TileRenderer {

  void paint(Graphics2D g, int x, int y, VDirection dir);

  void paintFwd(Graphics2D g, int x, int y);

  void paintLeft(Graphics2D g, int x, int y);

  void paintRight(Graphics2D g, int x, int y);

  void paintBack(Graphics2D g, int x, int y);
}
