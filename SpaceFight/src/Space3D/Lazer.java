package Space3D;

import java.awt.Point;

public class Lazer {
	
	private double x;
	private double y;
	private Point target;
	private int lifeExpectancy;
	private double moveX;
	private double moveY;
	private boolean kill;
	private double size;
	
	public Lazer (boolean right, Point target){
		if (right)x = 1920;
		else x = 0;
		y = 1080;
		this.target = target;
		lifeExpectancy = 20;
		moveX = (target.getX()-x)/lifeExpectancy;
		moveY = (target.getY()-y)/lifeExpectancy;
		kill = false;
		size = 50;
	}
	
	public void update(){
		if (!(y<=target.getY())){
			x+=moveX;
			y+=moveY;
			size/=1.1;
		}
		else kill = true;
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}
	public boolean isKill(){
		return kill;
	}
	public int getSize(){
		return (int)size;
	}
}
