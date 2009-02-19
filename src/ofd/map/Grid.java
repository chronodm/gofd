package ofd.map;

import java.util.*;

public abstract class Grid {

  /*
   TODO __REDESIGN__: 01) rename Coord to P

   TODO __REDESIGN__: 02) add: interface Range implements Iterable<Integer> {
     int from();
     int to();
     int size();
   }

   TODO __REDESIGN__: 03) interface IGrid<S> { // plus AbstractGrid<S>
     Range xRange();
     Range yRange();
     S get(P p);
   }
   
   TODO __REDESIGN__: 04) rename Grid to MGrid (implements IGrid<MSquare>)
   
   TODO __REDESIGN__: 05) add VGrid interface (extends IGrid<VSquare>)
                         (note: uses relative coordinates rather than absolute;
                         size is always odd, ranges are -fov to +fov)
   
   TODO __REDESIGN__: 06) adapt MapDisplay to show POV w/ location, direction, FOV
   
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
                                      return new Coord(xm, ym);
                                    case SOUTH:
                                      return new Coord(-xm, -ym);
                                    case EAST:
                                      return new Coord(ym, -xm);
                                    case WEST:
                                      return new Coord(-ym, xm);
                                    default:
                                      throw new IllegalArgumentException("Bad direction " + fwd); 
                                  }
                                }

   TODO __REDESIGN__: 09) rewrite 1st-person display using Displayulator

   */

  // ////////////////////////////////////////////////////////////
  // Abstracts

  public abstract MSquare getSquare(Coord coord);

  public abstract int width();

  public abstract int height();

  // ////////////////////////////////////////////////////////////
  // Utility methods

  public Coord getCoord(MSquare square) {
    for (int x = 0; x < width(); x++) {
      for (int y = 0; y < height(); y++) {
        Coord c = new Coord(x, y);
        MSquare sq = getSquare(c);
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
    int w = width();
    if (x < 0) {
      x = (x % w) + w;
    }
    if (x >= w) {
      x = x % w;
    }

    int h = height();
    if (y < 0) {
      y = (y % h) + h;
    }
    if (y >= h) {
      y = y % h;
    }

    Coord mapCoord = new Coord(x, y);
    return getSquare(mapCoord);
  }
}
