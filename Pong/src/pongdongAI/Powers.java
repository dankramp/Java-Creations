package pongdongAI;

import java.util.Random;

public class Powers {

	private String power;
	private int x;
	private int y;
	private double speedY;
	private Random random;
	private boolean isIn;
	
	public Powers(){
		power = "";
		x = -15;
		y = -15;
		speedY = 0;
		isIn = false;
	}
	public void reset(){
		power = "";
		x = -15;
		y = -15;
		speedY = 0;
		isIn = false;
		
	}
	
	public void update(){
		y += speedY;
		if (StartingClassAI.isGameStarted()&&!isIn){
			int which = (int)(Math.random()*2);
			if (which == 0){
				power = "widen";
			}
			else if (which == 1){
				power = "fastball";
			}
			int appear = (int)(Math.random()*300);
			if (!isIn&&appear == 1){
				y = -15;
				x = (int)(Math.random()*400+50);
				speedY = 2;
				isIn = true;
			}
		}
		if (!isIn)speedY = 0;
		if (y>515){
			isIn = false;
		}
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
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
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	public Random getRandom() {
		return random;
	}
	public void setRandom(Random random) {
		this.random = random;
	}
	public boolean isIn() {
		return isIn;
	}
	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}
	
}
