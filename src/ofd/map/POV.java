package ofd.map;

public class POV {
	private final Coord coord;
	private final MDirection fwd;

	public POV(Coord coord, MDirection fwd) {
		this.coord = coord;
		this.fwd = fwd;
	}

	public Coord getCoord() {
		return coord;
	}

	public MDirection getFwd() {
		return fwd;
	}
}
