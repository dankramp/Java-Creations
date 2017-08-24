package pongdongAI;

public class Ball {

	private double x;
	private double y;
	private double speedX;
	private double speedY;
	private double speedMult;
	private int radius;
	
	public Ball (){
		x = 250;
		y = 250;
		radius = 10;
		speedMult = 0;
		speedX = Math.cos(StartingClassAI.getAngle())*speedMult;
		speedY = Math.sin(StartingClassAI.getAngle())*speedMult;
	}
	public void update(){
		x+=speedX;
		y+=speedY;
	}
	public void reset(){
		x = 250;
		y = 250;
		speedMult = 0;
		speedX = Math.cos(StartingClassAI.getAngle())*speedMult;
		speedY = Math.sin(StartingClassAI.getAngle())*speedMult;
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

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getSpeedMult() {
		return speedMult;
	}

	public void setSpeedMult(double speedMult) {
		this.speedMult = speedMult;
	}

	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
