package RocketFlyer;

public class Square {
	
	private double angle;
	private double speedX;
	private double speedY;
	private double x;
	private double y;
	private boolean boost;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean dead;
	
	public Square (){
		x = 300;
		y = 250;
		speedX = 0;
		speedY = 0;
		boost = false;
		angle = 0;
	}
	public void update(){
		speedY+=.25;
		y+=speedY;
		x+=speedX;
		if (dead)boost = true;
		if (boost&&!dead)speedY-=.5;
		if (x>600&&!dead){
			x = 600;
			moveRight = false;
		}
		if (x<0&&!dead){
			x = 0;
			moveLeft = false;
		}
		if (y<0&&!dead){
			y = 1;
			speedY = 0;
		}
		if (y>550&&!dead){
			y = 548;
			speedY = 0;
		}
		if (moveLeft&&!moveRight&&!dead){
			speedX-=.2;
			angle-=.02;
		}
		else if (moveRight&&!moveLeft&&!dead){
			speedX+=.2;
			angle+=.02;
		}
		else if (((moveRight&&moveLeft)||(!moveLeft&&!moveRight))&&!dead){
			speedX*=.97;
			angle*=.9;
		}
		
		if (angle>.4)angle = .4;
		if (angle<-.4)angle = -.4;
		
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public boolean isBoost() {
		return boost;
	}
	public void setBoost(boolean boost) {
		this.boost = boost;
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
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	

}
