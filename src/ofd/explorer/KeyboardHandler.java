package ofd.explorer;

import ofd.util.Disposable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages all keyboard events for an {@link ExplorerPanel}.
 *
 * @author david
 */
class KeyboardHandler implements KeyListener, Disposable {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final Map<Integer, ActionListener> listeners = new HashMap<Integer, ActionListener>();
  private final ExplorerPanel explorerPanel;

  // ////////////////////////////////////////////////////////////
  // Constructors

  public KeyboardHandler(ExplorerPanel explorerPanel) {
    this.explorerPanel = explorerPanel;
    explorerPanel.addKeyListener(this);
  }

  // ////////////////////////////////////////////////////////////
  // Public methods

  public void addListener(int keyCode, ActionListener l) {
    if (l == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    if (listeners.containsKey(l)) {
      throw new IllegalArgumentException("A listener is already registered for code " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")");
    }
    listeners.put(keyCode, l);
  }

  // ////////////////////////////////////////////////////////////
  // Overrides

  @Override
  public void dispose() {
    explorerPanel.removeKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent event) {
    // do nothing
  }

  public void keyPressed(KeyEvent event) {
    // do nothing
  }

  public void keyReleased(KeyEvent event) {
    int keyCode = event.getKeyCode();
    String text = KeyEvent.getKeyText(keyCode);
    System.out.println(keyCode + ": " + text);
    ActionListener listener = listeners.get(keyCode);
    if (listener != null) {
      listener.actionPerformed(new ActionEvent(this, 0, text));
    }
  }
}
