package squares;

public class Player {

	private double x = 300;
	private double y = 250;
	private double speedX = 0;
	private double speedY = 0;
	private double speedMult;
	
	public Player(double speedMult){
		this.speedMult = speedMult;
	}
	public double getSpeedMult() {
		return speedMult;
	}
	public void update(){
		x+=speedX;
		y+=speedY;
		if(x>485)x=485;
		else if (x<15)x=15;
		if(y>485)y=485;
		else if (y<15)y=15;
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
	
	
	
	
	
	
}
