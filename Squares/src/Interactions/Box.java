package Interactions;

public class Box {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private double angle;
	
	public Box(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	

}
