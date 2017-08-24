package RocketFlyer;

public class Smoke {
	
	private double speedX;
	private double speedY;
	private double x;
	private double y;
	private int life;
	private int randomSize;
	
	public Smoke(double x, double y, double angle){
		this.x = x;
		this.y = y;
		speedX = -Math.sin(angle)*5;
		speedY = Math.cos(angle)*5;
		life = 0;
		randomSize = (int)(Math.random()*8);
	}
	public void update(){
		x+=speedX;
		y+=speedY;
		speedX*=.92;
		speedY*=.92;
		speedY-=.08;
		life++;
	}
	public double getSpeedX() {
		return speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	public int getLife() {
		return life;
	}
	public int getRandomSize() {
		return randomSize;
	}
	

}
