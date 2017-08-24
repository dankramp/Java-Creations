package SlidingSquares;

import java.awt.Point;

public class Player {

	private double x;
	private double y;
	private boolean moving;
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveRight;
	private boolean moveLeft;
	
	public Player(double X, double Y){
		x = X;
		y = Y;
		moving = false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}

	public boolean isMoveUp() {
		return moveUp;
	}

	public boolean isMoveDown() {
		return moveDown;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
