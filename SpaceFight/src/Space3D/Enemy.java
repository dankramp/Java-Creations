package Space3D;

public class Enemy {

	private int x;
	private int y;
	private int life;
	private int size;
	
	public Enemy(){
		x = (int)(Math.random()*1820+50);
		y = (int)(Math.random()*900+50);
		life = 0;
		size = 10;
	}
	public void update(){
		life++;
		size+=life/10;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getLife() {
		return life;
	}
	public int getSize() {
		return size;
	}
	
}
