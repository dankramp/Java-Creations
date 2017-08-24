package spaceFight;

public class Player {

	private double x;
	private double y;
	private double speedMult;
	private double speedX;
	private boolean moveRight;
	private boolean moveLeft;
	private int health;
	
	public Player(){
		x = 250;
		y = 450;
		speedMult = .14;
		speedX = 0;
		moveRight = false;
		moveLeft = false;
		health = 100;
	}
	public void update(){
		if (moveRight&&!moveLeft){
			speedX += speedMult;
		}
		else if (moveLeft&&!moveRight){
			speedX -= speedMult;
		}
		if (!moveLeft&&!moveRight){
			speedX*=.94;
		}
		if (speedX>3)speedX = 3;
		if (speedX<-3)speedX = -3;
		
		x += speedX;
		if (x>485)x = 485;
		if (x<15)x = 15;
	}
	public void reset(){
		x = 250;
		y = 450;
		speedMult = .14;
		speedX = 0;
		moveRight = false;
		moveLeft = false;
		health = 100;
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
	public double getSpeedMult() {
		return speedMult;
	}
	public void setSpeedMult(double speedMult) {
		this.speedMult = speedMult;
	}
	public double getSpeedX() {
		return speedX;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public boolean isMoveRight() {
		return moveRight;
	}
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	public boolean isMoveLeft() {
		return moveLeft;
	}
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	
	
}
