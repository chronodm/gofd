package ofd.display;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import ofd.map.MGrid;
import ofd.map.POV;
import ofd.util.Disposable;

public class DisplayModel implements Disposable {
  
  // ////////////////////////////////////////////////////////////
  // Mutable fields

  private EventListenerList listeners = new EventListenerList();
  private MGrid grid;
  private POV pov;
  
  // ////////////////////////////////////////////////////////////
  // Constructor

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
    for (ChangeListener l: listeners.getListeners(ChangeListener.class)) {
      l.stateChanged(e);
    }
  }
}
