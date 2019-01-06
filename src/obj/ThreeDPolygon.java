package obj;

import java.util.ArrayList;

public class ThreeDPolygon {
	private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	
	public ThreeDPolygon(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	public ArrayList<Coordinate> getCoordinates() {
		return this.coordinates;
	}
}
