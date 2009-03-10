package ofd;

public class ToDo {
  /*

  TODO __REDESIGN__: generalize MGrid to VGrid (may include generalizing MSquare/VSquare)

  TODO __REDESIGN__: add POV.see(MGrid) (returns VGrid) layering:
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

  TODO __REDESIGN__: add layer to TopDownDisplay to show POV w/ location, direction, FOV
  
  TODO __REDESIGN__: rewrite 1st-person display using Displayulator
  
  */

  /*

  TODO __EDITOR__: Add screen point -> model translation to TopDownDisplay: square, nearest wall (w/distance)
  
  TODO __EDITOR__: Add layer to TopDownDisplay:
                   - Mouse listener
                   - Mouseover & clicks for squares and walls
                   - Context menu for squares and walls
                   - Render mouseover (highlight)
                   - Render click (diff. color while dispatching event, cf. browser)
                   
  TODO __EDITOR__: Create editor frame w/tabbed views

   */
}
