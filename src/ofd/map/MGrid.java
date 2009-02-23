package ofd.map;

import ofd.util.IGrid;
import ofd.util.P;

/**
 * A fixed map {@link IGrid} representing a rectangular map with 0, 0 in the
 * southwest corner and (width, height) in the northeast.
 * 
 * @author davidm
 */
public abstract class MGrid implements IGrid<MSquare> {

  /*

   TODO __REDESIGN__: 05) use AbstractBorder.getInteriorRectangle()

   TODO __REDESIGN__: 06) adapt TopDownDisplay to show POV w/ location, direction, FOV
   
   TODO __REDESIGN__: 07) generalize to VGrid (may include generalizing MSquare/VSquare)

   TODO __REDESIGN__: 08) add POV.see(MGrid) (returns VGrid) layering:
                         VGrid -> Rotation -> Translation/Cliption -> MGrid
                         
                         Translation/Cliption:
                           Given:
                             - an MGrid,
                             - a (non-rotated) POV origin,
                             - and an x and y offset
                           Return:
                             - the corresponding MSquare as follows (for x, y):

                                // origin: POV in map coordinates
                                // offset: view coordinate of the square we're looking for
                                // map size: size of the map (used for wrapping)
                                int mapCoord(int origin, int offset, int mapSize) {
                                  int c = (origin + offset) % mapSize;
                                  if (c >= 0) {
                                    return c;
                                  }
                                  return c + mapSize;
                                }

                         Rotation:
                           Given:
                             - A non-rotated VGrid produced by Translation/Cliption
                             - A POV facing direction
                           Return:
                             - A rotated VGrid, with each square properly rotated

                                P getRotatedPoint(P unrotated, MDirection fwd) {
                                  int xm = unrotated.x();
                                  int ym = unrotated.y();
                                  switch (fwd) {
                                    case NORTH:
                                      return new P(xm, ym);
                                    case SOUTH:
                                      return new P(-xm, -ym);
                                    case EAST:
                                      return new P(ym, -xm);
                                    case WEST:
                                      return new P(-ym, xm);
                                    default:
                                      throw new IllegalArgumentException("Bad direction " + fwd); 
                                  }
                                }

   TODO __REDESIGN__: 09) rewrite 1st-person display using Displayulator

   */

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public P getP(MSquare square) {
    for (int x : xRange()) {
      for (int y : yRange()) {
        P c = new P(x, y);
        MSquare sq = get(c);
        if (sq.equals(square)) {
          return c;
        }
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * Handles toroidal wrapping
   */
  public MSquare getSquare(int x, int y) {
    int w = xRange().size();
    if (x < 0) {
      x = (x % w) + w;
    }
    if (x >= w) {
      x = x % w;
    }

    int h = yRange().size();
    if (y < 0) {
      y = (y % h) + h;
    }
    if (y >= h) {
      y = y % h;
    }

    P mapP = new P(x, y);
    return get(mapP);
  }
}
