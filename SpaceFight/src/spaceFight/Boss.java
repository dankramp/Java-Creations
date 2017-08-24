package spaceFight;

public class Boss {

	private double x;
	private double y;
	private double speedX;
	private double speedY;
	private double speedMult;
	private int health;
	private double yDest;
	private boolean inactive;
	
	public Boss(int level){
		x = 250;
		y = -100;
		speedX = 0;
		speedY = 0;
		speedMult = 3;
		health = 482;
		yDest = 150;
		if (level!=5)inactive = true;
		else inactive = false;
	}
	public void reset(){
		x = 250;
		y = -100;
		speedX = 0;
		speedY = 0;
		speedMult = 3;
		health = 482;
		yDest = 150;
		inactive = false;
	}
	
	public void update(){
		if (!inactive) {
			if (health <= 0)
				yDest = 800;
			if (Math.abs((int) (y) - (int) (yDest)) < 5 && health > 0) {
				yDest = Math.random() * 125 + 50;
			} else if (y < yDest) {
				speedY += .05;
			} else if (y > yDest) {
				speedY -= .05;
			}
			if (speedY > speedMult)
				speedY = speedMult;
			else if (speedY < -speedMult)
				speedY = -speedMult;
			if (speedX > speedMult)
				speedX = speedMult;
			else if (speedX < -speedMult)
				speedX = -speedMult;

			y += speedY;
			x += speedX;
		}
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getyDest() {
		return yDest;
	}

	public void setyDest(double yDest) {
		this.yDest = yDest;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	
}
