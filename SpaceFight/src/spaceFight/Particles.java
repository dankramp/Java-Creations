package spaceFight;

import java.awt.Color;

public class Particles {

	private int x;
	private int y;
	private double speedX;
	private double speedY;
	private double speedMult;
	private double radius;
	private double random;
	private Color color;
	
	public Particles(int x, int y, double rad, double speed, Color c){
		this.x = x;
		this.y = y;
		speedMult = Math.random()*speed+2;
		double angle = Math.random()*Math.PI*2;
		speedX = Math.cos(angle)*speedMult;
		speedY = Math.sin(angle)*speedMult;
		radius = rad;
		random = Math.random()*.09+.89;
		color = c;
	}
	public void update(){
		x+=speedX;
		y+=speedY;
		speedX*=.8;
		speedY*=.8;
		radius*=random;
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
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}
	
}
