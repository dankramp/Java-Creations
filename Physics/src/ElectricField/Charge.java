package ElectricField;

public class Charge {
	
	private int x;
	private int y;
	private double charge;
	
	public Charge(int x, int y, double c){
		this.x = x;
		this.y = y;
		charge = c;
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

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}
	

}
