package WhackMole;

public class HitMarker {

	private int x;
	private int y;
	private int radius;
	
	public HitMarker(int x, int y){
		this.x = x;
		this.y = y;
		radius = 5;
	}
	
	public void update(){
		radius++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}
	
}
