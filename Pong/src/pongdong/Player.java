package pongdong;

public class Player {

	private int x;
	private int y;
	private int speedY;
	private int height;
	private int width;
	private boolean moveUp = false;
	private boolean moveDown = false;
	
	public Player(int x){
		this.x = x;
		y = 250;
		speedY = 0;
		height = 100;
		width = 20;
	}
	public void reset(){
		y = 250;
		speedY = 0;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void update(){
		y+=speedY;
		if (moveUp){
			speedY = -4;
		}
		else if (moveDown){
			speedY = 4;
		}
		else speedY = 0;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}
	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}
	

}
