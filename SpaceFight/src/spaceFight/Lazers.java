package spaceFight;

public class Lazers {

	private double x;
	private double y;
	private double speedY;
	private double speedX;
	private double speedMult;
	private String type;
	
	public Lazers(double x, double y, boolean up, double speedX, String type){
		this.x = x;
		this.y = y;
		speedMult = 4;
		this.type = type;
		if (up)speedY = speedMult*-1;
		else speedY = speedMult;
		this.speedX = speedX;
	}
	public void update(){
		y+=speedY;
		x+=speedX;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
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
	public double getSpeedMult() {
		return speedMult;
	}
	public void setSpeedMult(double speedMult) {
		this.speedMult = speedMult;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSpeedX() {
		return speedX;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	
}
