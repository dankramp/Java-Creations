package RocketFlyer;

public class Lazer {
	
	private double x;
	private double y;
	private double speedX;
	
	public Lazer (double x, double y){
		this.x = x;
		this.y = y;
		speedX = 8;
	}
	public void update(){
		x+=speedX;
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	public double getSpeedX() {
		return speedX;
	}
	

}
