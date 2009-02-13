package ofd.map;

public interface Grid {
	MSquare getSquare(Coord coord);
	
	int width();
	
	int height();
}
