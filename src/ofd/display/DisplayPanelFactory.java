package ofd.display;

/**
 * @author david
 */
public interface DisplayPanelFactory {
  AbstractDisplayPanel createPanel(DisplayModel model);
}
