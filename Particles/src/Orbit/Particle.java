package Orbit;

import java.util.Random;

public class Particle {
	
	private int x;
	private int y;
	private int radX;
	private int radY;
	private double angleX;
	private double angleY;
	
	public Particle(){
		Random rand = new Random();
		radX = rand.nextInt(50)+200;
		radY = rand.nextInt(50)+200;
		angleX = Math.random()*Math.PI*2;
		angleY = Math.random()*Math.PI*2;
		
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

	public int getRadX() {
		return radX;
	}

	public void setRadX(int radX) {
		this.radX = radX;
	}

	public int getRadY() {
		return radY;
	}

	public void setRadY(int radY) {
		this.radY = radY;
	}

	public double getAngleX() {
		return angleX;
	}

	public void setAngleX(double angleX) {
		this.angleX = angleX;
	}

	public double getAngleY() {
		return angleY;
	}

	public void setAngleY(double angleY) {
		this.angleY = angleY;
	}
	

}
