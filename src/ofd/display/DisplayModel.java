package ofd.display;

import ofd.map.*;
import ofd.util.Disposable;
import ofd.util.IRange;
import ofd.util.P;
import ofd.util.Range;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class DisplayModel implements Disposable {

  // ////////////////////////////////////////////////////////////
  // Mutable fields

  private EventListenerList listeners = new EventListenerList();
  private MGrid grid;
  private POV pov;

  // ////////////////////////////////////////////////////////////
  // Constructor

  public DisplayModel() {
    this(new DummyGrid(), new POV(new P(0, 0), MDirection.NORTH));
  }

  public DisplayModel(MGrid grid, POV pov) {
    if (grid == null) {
      throw new NullPointerException("grid cannot be null");
    }
    if (pov == null) {
      throw new NullPointerException("pov cannot be null");
    }
    this.grid = grid;
    this.pov = pov;
  }

  // ////////////////////////////////////////////////////////////
  // Accessors

  public void setGridAndPOV(MGrid grid, POV pov) {
    if (grid == null) {
      throw new NullPointerException("grid cannot be null");
    }
    if (pov == null) {
      throw new NullPointerException("pov cannot be null");
    }
    this.grid = grid;
    this.pov = pov;
    fireModelChanged();
  }

  public void setGrid(MGrid grid) {
    if (grid == null) {
      throw new NullPointerException();
    }
    this.grid = grid;
    fireModelChanged();
  }

  public void setPOV(POV pov) {
    if (pov == null) {
      throw new NullPointerException();
    }
    this.pov = pov;
    fireModelChanged();
  }

  public MGrid getGrid() {
    return grid;
  }

  public POV getPov() {
    return pov;
  }

  // ////////////////////////////////////////////////////////////
  // Public methods

  public void addChangeListener(ChangeListener l) {
    listeners.add(ChangeListener.class, l);
  }

  public void removeChangeListener(ChangeListener l) {
    listeners.remove(ChangeListener.class, l);
  }

  public void dispose() {
    listeners = new EventListenerList();
  }

  // ////////////////////////////////////////////////////////////
  // Private methods

  private void fireModelChanged() {
    ChangeEvent e = new ChangeEvent(this);
    for (ChangeListener l : listeners.getListeners(ChangeListener.class)) {
      l.stateChanged(e);
    }
  }

  // ////////////////////////////////////////////////////////////
  // Helper classes

  private static final class DummyGrid extends MGrid {

    private final Tile tile = new Tile() {
      public TileType type() {
        return TileType.NONE;
      }
    };

    private final MSquare square = new MSquare() {
      public Tile getTile(MDirection direction) {
        return tile;
      }
    };

    private final Range range = new Range(0, 1);

    @Override
    public IRange xRange() {
      return range;
    }

    @Override
    public IRange yRange() {
      return range;
    }

    public MSquare get(P p) {
      return square;
    }
  }

}
