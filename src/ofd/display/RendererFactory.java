package ofd.display;

import ofd.map.TileType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class RendererFactory {

  // ////////////////////////////////////////////////////////////
  // Fields

  private final Map<TileType, TileRenderer> renderers;

  // ////////////////////////////////////////////////////////////
  // Constructor

  /**
   * Private -- use {@link #builder()}
   */
  private RendererFactory(Map<TileType, TileRenderer> renderers) {
    this.renderers = renderers;
  }

  // ////////////////////////////////////////////////////////////
  // Class methods

  /**
   * Returns a new {@link #Builder}
   */
  public static Builder builder() {
    return new Builder();
  }

  // ////////////////////////////////////////////////////////////
  // Instance methods

  public TileRenderer getRenderer(TileType tile) {
    TileRenderer r = renderers.get(tile);
    return r == null ? new NullRenderer() : r;
  }

  // ////////////////////////////////////////////////////////////
  // Helper classes

  public static class Builder {

    private EnumMap<TileType, TileRenderer> map = new EnumMap<TileType, TileRenderer>(TileType.class);

    public Builder add(TileType tile, TileRenderer r) {
      map.put(tile, r);
      return this;
    }

    public RendererFactory create() {
      return new RendererFactory(Collections.unmodifiableMap(map));
    }
  }
}
