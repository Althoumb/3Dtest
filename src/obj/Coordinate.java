package obj;

public class Coordinate {
	private double x, y, z;
	
	public Coordinate (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public double getDistance(Coordinate coordinate) {
		return Math.pow((coordinate.getX() - this.x)*(coordinate.getX() - this.x) + (coordinate.getY() - this.y)*(coordinate.getY() - this.y) + (coordinate.getZ() - this.z)*(coordinate.getZ() - this.z), 0.5);
	}
	
}
