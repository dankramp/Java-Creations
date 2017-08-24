package Interactions;

public class Ball {
	
	private double x;
	private double y;
	private double speedX;
	private double speedY;
	
	public Ball(){
		x = 300;
		y = 50;
		speedX = Math.random()*4-2;
		speedY = 0;
	}
	public void update(){
		x+=speedX;
		y+=speedY;
		speedY+=.6;
	}
	public int getX() {
		return (int)x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return (int)y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	public double getSpeedX() {
		return speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	

}
