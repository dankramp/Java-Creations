package squares;

public class Enemy {

	private double x = 200;
	private double y = 200;
	private double speedX;
	private double speedY;
	private double speedMult;
	
	public Enemy (){
		x = 200;
		y = 200;
	}
	public Enemy(int x, int y, double speedMult){
		this.x = x;
		this.y = y;
		this.speedMult = speedMult;
	}


	public double getSpeedMult() {
		return speedMult;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void update(){
		x+=speedX;
		y+=speedY;
		/*
		if(x>480)speedX*=-1;
		else if (x<20)speedX*=-1;
		if(y>480)speedY*=-1;
		else if (y<20)speedY*=-1;
		*/
		
	}
	
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	
	
	
	
}
