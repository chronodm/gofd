package ofd.map;

import ofd.util.AbstractGrid;
import ofd.util.IGrid;

/**
 * A fixed map {@link IGrid} representing a rectangular map with 0, 0 in the
 * southwest corner and (width, height) in the northeast.
 * 
 * @author davidm
 */
public abstract class MGrid extends AbstractGrid<MDirection, MSquare> {
}
