package RocketFlyer;
import java.util.Random;


public class Power {
	
	private double y;
	private int x;
	private double speedY;
	private String type;
	
	public Power(){
		y = -20;
		x = (int)(Math.random()*500+75);
		speedY = 1+Math.random()*2;
		Random rand = new Random();
		if (rand.nextBoolean())type = "ram";
		else type = "health";
	}
	public void update(){
		y+=speedY;
	}
	public int getY() {
		return (int)y;
	}
	public int getX() {
		return x;
	}
	public double getSpeedY() {
		return speedY;
	}
	public String getType() {
		return type;
	}
	

}
