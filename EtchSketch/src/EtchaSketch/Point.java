package EtchaSketch;

public class Point {

	private int x;
	private int y;
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;
	private double speedMult;
	
	public Point(){
		x = 300;
		y = 250;
		moveUp = false;
		moveDown = false;
		moveRight = false;
		moveLeft = false;
		speedMult = 2;
	}
	public void update(){
		if (moveRight)x+=speedMult;
		if (moveLeft)x-=speedMult;
		if (moveUp)y-=speedMult;
		if (moveDown)y+=speedMult;
		
		if (x>540)x = 540;
		else if (x<60)x = 60;
		if (y>340)y = 340;
		else if (y<60)y = 60;
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
	public boolean isMoveUp() {
		return moveUp;
	}
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}
	public boolean isMoveDown() {
		return moveDown;
	}
	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}
	public boolean isMoveLeft() {
		return moveLeft;
	}
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	public boolean isMoveRight() {
		return moveRight;
	}
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	public double getSpeedMult() {
		return speedMult;
	}
	public void setSpeedMult(double speedMult) {
		this.speedMult = speedMult;
	}
	
}
