package WhackMole;

public class Particle {

	private double x;
	private double y;
	private double speedX;
	private double speedY;
	private double life;
	
	public Particle(int x, int y){
		this.x = x;
		this.y = y;
		speedX = Math.random()*6-3;
		speedY = Math.random()*6-3;
		life = 0;
	}
	public void update(){
		speedX*=.98;
		speedY+=.1;
		x+=speedX;
		y+=speedY;
		life+=Math.random();
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	public int getLife() {
		return (int)life;
	}
	
}
