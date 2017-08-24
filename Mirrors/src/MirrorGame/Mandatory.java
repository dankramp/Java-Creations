package MirrorGame;

public class Mandatory {
	
	private int x;
	private int y;
	private boolean hit;
	
	public Mandatory(int x, int y, boolean hit){
		this.x = x;
		this.y = y;
		this.hit = hit;
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

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	

}
