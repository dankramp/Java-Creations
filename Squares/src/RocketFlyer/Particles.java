package RocketFlyer;

public class Particles {

	private double speedX;
	private double speedY;
	private double x;
	private double y;
	private int life;
	
	public Particles(double x, double y){
		speedX = Math.random()*14-7;
		speedY = Math.random()*14-7;
		this.x = x;
		this.y = y;
		life = 0;
	}
	public void update(){
		x+=speedX;
		y+=speedY;
		speedX*=.91;
		speedY*=.91;
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
	
}
