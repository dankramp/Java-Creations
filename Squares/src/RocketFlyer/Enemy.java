package RocketFlyer;

import java.awt.Color;

public class Enemy {

	private double x;
	private int y;
	private double speedX;
	private int sizeX;
	private int sizeY;
	private Color color;
	
	public Enemy(){
		x = 650;
		y = (int)(Math.random()*400+50);
		speedX = -2-(int)(Math.random()*2);
		sizeX = (int)(Math.random()*45+20);
		sizeY = (int)(Math.random()*50+25);
		color = (new Color((int)(Math.random()*105+130),(int)(Math.random()*105+130),(int)(Math.random()*105+130)));
	}
	public void update(){
		x+=speedX;
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return y;
	}
	public double getSpeedX() {
		return speedX;
	}
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public Color getColor() {
		return color;
	}
	
}
