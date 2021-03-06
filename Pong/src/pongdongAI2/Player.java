package pongdongAI2;

public class Player {

	private int x;
	private int y;
	private double speedY;
	private int height;
	private int width;
	private double speedMult;
	
	public Player(int x, double speedMult){
		this.x = x;
		y = 250;
		speedY = 0;
		height = 90;
		width = 20;
		this.speedMult = speedMult;
	}
	public void reset(double speedMult){
		y = 250;
		speedY = 0;
		height = 90;
		this.speedMult = speedMult;
	}
	public void update(){
		y+=speedY;
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
	public double getSpeedMult() {
		return speedMult;
	}
	public void setSpeedMult(double speedMult) {
		this.speedMult = speedMult;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	

}
