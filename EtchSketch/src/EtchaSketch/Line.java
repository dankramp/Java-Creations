package EtchaSketch;

public class Line {

	private int x;
	private int y;
	private int trans;
	
	public Line(int x, int y){
		this.x = x;
		this.y = y;
		trans = 130;
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

	public int getTrans() {
		return trans;
	}

	public void setTrans(int trans) {
		this.trans = trans;
	}
	
	
}
