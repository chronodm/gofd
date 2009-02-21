package ofd.view;

import ofd.util.IGrid;

/**
 * A variable map with 0, 0 in the center, representing the {@link ofd.map.POV} location, with
 * coordinates ranging from -fov to +fov in both x and y directions, such that
 * the squares immediately forward, right, back and left of the POV are (0, 1),
 * (1, 0), (0, -1) and (-1, 0) respectively.
 * <p>
 * A {@link VGrid} is always square, and may not represent an accurate
 * tiling of the underlying map, in the sense that the edges do not connect.
 * Instead, it represents what is visible from a given POV. As the POV
 * moves, the {@link VGrid} changes.
 * @author david
 */
public interface VGrid extends IGrid<VSquare> {
}
