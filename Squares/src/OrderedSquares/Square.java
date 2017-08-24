package OrderedSquares;

public class Square implements Comparable{

	private int x;
	private int y;
	private int numVal;
	private boolean grabbed;
	private boolean outOfPlace;
	
	public Square(int x, int y, int num){
		this.x = x;
		this.y = y;
		numVal = num;
		grabbed = false;
		outOfPlace = false;
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

	public int getNumVal() {
		return numVal;
	}

	public void setNumVal(int numVal) {
		this.numVal = numVal;
	}

	public boolean isGrabbed() {
		return grabbed;
	}

	public void setGrabbed(boolean grabbed) {
		this.grabbed = grabbed;
	}

	public boolean isOutOfPlace() {
		return outOfPlace;
	}

	public void setOutOfPlace(boolean outOfPlace) {
		this.outOfPlace = outOfPlace;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof Square){
			Square s = (Square)arg0;
			return numVal-s.getNumVal();
		}
		return 0;
	}
	
	
}
