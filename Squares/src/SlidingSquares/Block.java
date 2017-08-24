package SlidingSquares;

public class Block {

	private double x;
	private double y;
	private String type;
	
	public Block(double x, double y, String t){
		this.x = x;
		this.y = y;
		type = t;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
