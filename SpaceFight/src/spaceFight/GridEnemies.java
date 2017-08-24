package spaceFight;

public class GridEnemies {

	private String[][] enemies;
	private double[][] x;
	private double[][] xInit;
	private double[][] y;
	private boolean moveRight;
	private double speed;
	private double rightCol, leftCol;
	private int level;
	
	
	public GridEnemies(int lvl){
		speed = .88;
		this.level = lvl;
		if (level == 1){
			enemies = new String[][] { 
					{ "", "", "", "simple", "simple", "", "", "" },
					{ "", "", "simple", "aim", "aim", "simple", "", "" },
					{ "", "", "simple", "aim", "aim", "simple", "", "" },
					{ "", "", "", "bombgiver", "simple", "", "", "" } };
		}
		if (level == 2){
			enemies = new String[][] { 
					{ "aim", "", "simple", "simple", "simple", "simple", "", "aim" },
					{ "", "simple", "aim", "simple", "simple", "aim", "simple", "" },
					{ "", "simple", "aim", "simple", "simple", "aim", "simple", "" },
					{ "aim", "", "simple", "simple", "simple", "simple", "", "aim" } };
		}
		if (level == 5){
			enemies = new String[][] { 
					{ "", "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "", "" } };
		}
		if (level == 69){
			enemies = new String[][] { 
					{ "", "", "", "simple", "simple", "", "", "" },
					{ "", "", "simple", "bombgiver", "aim", "simple", "", "" },
					{ "", "", "simple", "aim", "aim", "simple", "", "" },
					{ "", "", "", "simple", "simple", "", "", "" } };
		}
		x = new double[][] { { 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 } };
		if (level != 69){
			y = new double[][] { { 65, 65, 65, 65, 65, 65, 65, 65 },
				{ 95, 95, 95, 95, 95, 95, 95, 95 },
				{ 125, 125, 125, 125, 125, 125, 125, 125 },
				{ 155, 155, 155, 155, 155, 155, 155, 155 } };
		}
		else {
			y = new double[][] { { -110, -110, -110, -110, -110, -110, -110, -110 },
					{ -80, -80, -80, -80, -80, -80, -80, -80 },
					{ -50, -50, -50, -50, -50, -50, -50, -50 },
					{ -20, -20, -20, -20, -20, -20, -20, -20 } };
		}
		
	}
	public void resetPos(){
		x = new double[][] { { 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 } };
		y = new double[][] { { 65, 65, 65, 65, 65, 65, 65, 65 },
				{ 95, 95, 95, 95, 95, 95, 95, 95 },
				{ 125, 125, 125, 125, 125, 125, 125, 125 },
				{ 155, 155, 155, 155, 155, 155, 155, 155 } };
	}
	public void reset(int lvl){
		speed = .88;
		this.level = lvl;
		if (level == 1){
			enemies = new String[][] { 
					{ "", "", "", "", "", "", "", "" },
					{ "", "", "", "aim", "aim", "", "", "" },
					{ "", "", "", "aim", "aim", "", "", "" },
					{ "", "", "", "bombgiver", "", "", "", "" } };
		}
		if (level == 2){
			enemies = new String[][] { 
					{ "aim", "", "simple", "simple", "simple", "simple", "", "aim" },
					{ "", "simple", "aim", "simple", "simple", "aim", "simple", "" },
					{ "", "simple", "aim", "simple", "simple", "aim", "simple", "" },
					{ "aim", "", "simple", "simple", "simple", "simple", "", "aim" } };
		}
		else if (level == 69){
			enemies = new String[][] { 
					{ "", "", "", "simple", "simple", "", "", "" },
					{ "", "", "simple", "bombgiver", "aim", "simple", "", "" },
					{ "", "", "simple", "aim", "aim", "simple", "", "" },
					{ "", "", "", "simple", "simple", "", "", "" } };
		}
		x = new double[][] { { 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 },
				{ 75, 125, 175, 225, 275, 325, 375, 425 } };
		if (level != 69){
			y = new double[][] { { 65, 65, 65, 65, 65, 65, 65, 65 },
				{ 95, 95, 95, 95, 95, 95, 95, 95 },
				{ 125, 125, 125, 125, 125, 125, 125, 125 },
				{ 155, 155, 155, 155, 155, 155, 155, 155 } };
		}
		else {
			y = new double[][] { { -110, -110, -110, -110, -110, -110, -110, -110 },
					{ -80, -80, -80, -80, -80, -80, -80, -80 },
					{ -50, -50, -50, -50, -50, -50, -50, -50 },
					{ -20, -20, -20, -20, -20, -20, -20, -20 } };
		}
	}
	public void update(){
		for (int row = 0; row<x.length; row++){
			for (int col = 0; col<x[0].length; col++){
				if (x[row][col]>485&&!enemies[row][col].equals("")){
					for (int a = 0; a<x.length; a++){
						for (int b = 0; b<x[0].length; b++){
							if (!StartingClassSpace.isDead())y[a][b]+=15;
							moveRight = false;
						}
					}
				}
				else if (x[row][col]<15&&!enemies[row][col].equals("")){
					for (int a = 0; a<x.length; a++){
						for (int b = 0; b<x[0].length; b++){
							if (!StartingClassSpace.isDead())y[a][b]+=15;
							moveRight = true;
						}
					}
				}	
				if (!StartingClassSpace.isDead())speed += .00003;
				if (speed>2)speed = 2;
				if (moveRight)x[row][col]+=speed;
				else x[row][col]-=speed;
			}
		}		
		if (level == 69){
			if (y[3][0]<155){
				for (int row = 0; row<y.length; row++){
					for (int col = 0; col<y[0].length; col++){
						y[row][col] = y[row][col] + 5;
					}
				}
			}
		}
		
	}

	public String[][] getEnemies() {
		return enemies;
	}

	public void setEnemies(String type, int row, int col) {
		this.enemies[row][col] = type;
	}

	public double[][] getX() {
		return x;
	}

	public void setX(double[][] x) {
		this.x = x;
	}

	public double[][] getY() {
		return y;
	}

	public void setY(double[][] y) {
		this.y = y;
	}
	
	
	
}
