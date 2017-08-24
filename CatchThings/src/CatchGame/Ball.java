package CatchGame;

import java.awt.Color;

public class Ball {
	
	private double x;
	private double y;
	private double speedY;
	private int radius;
	private Color color;
	
	public Ball(){
		speedY = 0;
		y = -20;
		x = 50+Math.random()*500;
		radius = (int)(Math.random()*3+9);
		//color = Color.WHITE;
		color = new Color((int)(120+Math.random()*135),(int)(120+Math.random()*135),(int)(120+Math.random()*135));
	}
	public void update() {
		y+=speedY;
		speedY += .3;
	}

	public int getX() {
		return (int)x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	

}
