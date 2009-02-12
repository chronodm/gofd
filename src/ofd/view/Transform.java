package ofd.view;

import ofd.map.*;

public class Transform {
	
	// ////////////////////////////////////////////////////////////
	// Class methods
	
	public static VSquare toVSquare(MSquare ms, MDirection fwd) {
		return new SquareWrapper(ms, fwd);
	}
	
	public static MSquare getSquare(Grid grid, POV pov, int xOff, int yOff) {
		return grid.getSquare(pov.getCoord());
	}
	
	// ////////////////////////////////////////////////////////////
	// Inner classes
	
	private static class SquareWrapper implements VSquare {

		private final int offset;
		private final MSquare ms;

		public SquareWrapper(MSquare ms, MDirection fwd) {
			this.ms = ms;
			offset = fwd.ordinal();
		}

		@Override
		public Tile getTile(VDirection dir) {
			int index = (dir.ordinal() + offset) % 4;
			MDirection mdir = MDirection.values()[index];
			return ms.getTile(mdir);
		}
	}
}
