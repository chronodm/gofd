package ofd.view.transform;

import ofd.map.MDirection;
import ofd.map.MGrid;
import ofd.map.MSquare;
import ofd.util.IRange;
import ofd.util.P;
import ofd.util.Range;
import ofd.util.V;
import ofd.view.VGrid;
import ofd.view.VSquare;
import ofd.view.View;

/**
 * A {@link VGrid} that clips an underlying {@link MGrid} to
 * a square visual range, and transforms the specified origin
 * point to (0, 0).
 *  
 * @author davidm
 */
public class TransClip extends VGrid {

  // ////////////////////////////////////////////////////////////
  // Final fields

  private final MGrid mgrid;
  private final P origin;
  private final IRange range;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public TransClip(MGrid mgrid, P origin, int vRadius) {
    this.mgrid = mgrid;
    this.origin = origin;
    range = new Range(-vRadius, vRadius);
  }

  // ////////////////////////////////////////////////////////////
  // VGrid
  
  @Override
  public VSquare get(P p) {
    validatePoint(p);

    int dX = p.x();
    int dY = p.y();
    
    MSquare mSq = mgrid.getSquare(origin.translate(new V(dX, dY)));
    
    return View.see(mSq, MDirection.NORTH);
  }

  @Override
  public IRange xRange() {
    return range;
  }

  @Override
  public IRange yRange() {
    return range;
  }

  // ////////////////////////////////////////////////////////////
  // Private
  
  private void validatePoint(P p) {
    assert range.contains(p.x()) : p.x() + " is outside x range " + range;
    assert range.contains(p.y()) : p.y() + " is outside y range " + range;
  }

}
