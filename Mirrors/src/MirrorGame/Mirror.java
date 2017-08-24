package MirrorGame;

public class Mirror {
	
	private boolean negativeSlope;
	private int x;
	private int y;
	
	public Mirror(int x, int y, boolean n){
		this.x = x;
		this.y = y;
		negativeSlope = n;
	}

	public boolean isNegativeSlope() {
		return negativeSlope;
	}

	public void setNegativeSlope(boolean negativeSlope) {
		this.negativeSlope = negativeSlope;
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
	

}
