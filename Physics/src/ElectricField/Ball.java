package ElectricField;

public class Ball {

	private double x;
	private double y;
	private double speedX;
	private double speedY;
	private double mass;	//Kg
	
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		speedX = 0;
		speedY = 0;
		mass = 1;
	}
	
	public void update(){
		x+=speedX;
		y+=speedY;
		if (x>600){
			x = 600;
			speedX*=-.8;
		}
		if (x<0){
			x = 0; 
			speedX*=-.8;
		}
		if (y>500){
			y = 500;
			speedY*=-.8;
		}
		if (y<0){
			y = 0;
			speedY*=-.8;
		}
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
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

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
	
	
}
