package SlidingSquares;

import java.util.ArrayList;

public class Levels {
	
	private ArrayList<Block> obstacles = new ArrayList<Block>();
	private int boardSize;
	private double playerStartX;
	private double playerStartY;
	private int playerRadius;
	
	public Levels(int level){
		if (level==0){
			boardSize = 5;
			playerStartX = 50;
			playerStartY = 50;
		}
		if (level==1){
			boardSize = 4;
			playerStartX = 0.5*500.0/boardSize;
			playerStartY = 0.5*500.0/boardSize;
		}
		if (level==2){
			boardSize = 5;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 2.5*500.0/boardSize;
		}
		if (level==3){
			//Start of code
			boardSize = 5;
			playerStartX = 1.5*500.0/boardSize;
			playerStartY = 4.5*500.0/boardSize;
			obstacles.add(new Block(0.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(2.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
		}
		if (level==4){
			//Start of code
			boardSize = 5;
			playerStartX = 1.5*500.0/boardSize;
			playerStartY = 1.5*500.0/boardSize;
			obstacles.add(new Block(3.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(2.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
		}
		if (level==5){
			boardSize = 5;
			playerStartX = 1.5*500.0/boardSize;
			playerStartY = 0.5*500.0/boardSize;
			obstacles.add(new Block(1.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(4.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 3.5*500.0/boardSize, "s"));
			obstacles.add(new Block(1.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(0.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
		}
		if (level==6){
			boardSize = 6;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 5.5*500.0/boardSize;
			obstacles.add(new Block(0.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 4.5*500.0/boardSize, "s"));
			obstacles.add(new Block(4.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
		}

		if (level==7){
			boardSize = 6;
			playerStartX = 3.5*500.0/boardSize;
			playerStartY = 3.5*500.0/boardSize;
			obstacles.add(new Block(2.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(2.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
		}
		if (level==8){
			boardSize = 6;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 2.5*500.0/boardSize;
			obstacles.add(new Block(4.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 3.5*500.0/boardSize, "s"));
			obstacles.add(new Block(1.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
			obstacles.add(new Block(0.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
		}
		if (level==9){
			boardSize = 6;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 4.5*500.0/boardSize;
			obstacles.add(new Block(5.5*500.0/boardSize, 4.5*500.0/boardSize, "s"));
			obstacles.add(new Block(3.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(4.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
		}
		if (level==10){
			boardSize = 6;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 2.5*500.0/boardSize;
			obstacles.add(new Block(3.5*500.0/boardSize, 2.5*500.0/boardSize, "l"));
		}
		if (level==11){
			boardSize = 7;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 2.5*500.0/boardSize;
			obstacles.add(new Block(1.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(0.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
			obstacles.add(new Block(6.5*500.0/boardSize, 6.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 1.5*500.0/boardSize, "s"));
			obstacles.add(new Block(5.5*500.0/boardSize, 4.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 5.5*500.0/boardSize, "s"));
			obstacles.add(new Block(1.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
		}
		if (level==12){
			boardSize = 7;
			playerStartX = 3.5*500.0/boardSize;
			playerStartY = 3.5*500.0/boardSize;
			obstacles.add(new Block(3.5*500.0/boardSize, 0.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(2.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
		}
		if (level==13){
			boardSize = 7;
			playerStartX = 3.5*500.0/boardSize;
			playerStartY = 2.5*500.0/boardSize;
			obstacles.add(new Block(2.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 3.5*500.0/boardSize, "b"));
			obstacles.add(new Block(2.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(0.5*500.0/boardSize, 4.5*500.0/boardSize, "s"));
			obstacles.add(new Block(1.5*500.0/boardSize, 5.5*500.0/boardSize, "s"));
			obstacles.add(new Block(5.5*500.0/boardSize, 3.5*500.0/boardSize, "s"));
			obstacles.add(new Block(3.5*500.0/boardSize, 4.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
		}
		if (level==14){
			boardSize = 7;
			playerStartX = 4.5*500.0/boardSize;
			playerStartY = 4.5*500.0/boardSize;
			obstacles.add(new Block(5.5*500.0/boardSize, 6.5*500.0/boardSize, "s"));
			obstacles.add(new Block(5.5*500.0/boardSize, 1.5*500.0/boardSize, "s"));
			obstacles.add(new Block(6.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(4.5*500.0/boardSize, 1.5*500.0/boardSize, "s"));
			obstacles.add(new Block(3.5*500.0/boardSize, 5.5*500.0/boardSize, "s"));
			obstacles.add(new Block(1.5*500.0/boardSize, 6.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
		}
		if (level==15){
			boardSize = 7;
			playerStartX = 2.5*500.0/boardSize;
			playerStartY = 4.5*500.0/boardSize;
			obstacles.add(new Block(2.5*500.0/boardSize, 6.5*500.0/boardSize, "b"));
			obstacles.add(new Block(4.5*500.0/boardSize, 5.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 2.5*500.0/boardSize, "b"));
			obstacles.add(new Block(1.5*500.0/boardSize, 0.5*500.0/boardSize, "s"));
			obstacles.add(new Block(6.5*500.0/boardSize, 4.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 3.5*500.0/boardSize, "s"));
			obstacles.add(new Block(3.5*500.0/boardSize, 1.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 5.5*500.0/boardSize, "s"));
		}
		if (level==16){
			boardSize = 8;
			playerStartX = 0.5*500.0/boardSize;
			playerStartY = 2.5*500.0/boardSize;
			obstacles.add(new Block(5.5*500.0/boardSize, 5.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 5.5*500.0/boardSize, "s"));
			obstacles.add(new Block(2.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(5.5*500.0/boardSize, 2.5*500.0/boardSize, "s"));
			obstacles.add(new Block(0.5*500.0/boardSize, 4.5*500.0/boardSize, "s"));
			obstacles.add(new Block(1.5*500.0/boardSize, 6.5*500.0/boardSize, "b"));
			obstacles.add(new Block(3.5*500.0/boardSize, 7.5*500.0/boardSize, "b"));
			obstacles.add(new Block(5.5*500.0/boardSize, 6.5*500.0/boardSize, "b"));
		}
		

		playerRadius = 250/boardSize;
	}


	public ArrayList<Block> getObstacles() {
		return obstacles;
	}

	public double getPlayerStartX() {
		return playerStartX;
	}
	public double getPlayerStartY() {
		return playerStartY;
	}
	public int getPlayerRadius() {
		return playerRadius;
	}

	public void setPlayerStartX(double playerStartX) {
		this.playerStartX = playerStartX;
	}

	public void setPlayerStartY(double playerStartY) {
		this.playerStartY = playerStartY;
	}

	public void setPlayerRadius(int playerRadiusX) {
		this.playerRadius = playerRadiusX;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}


	public void setObstacles(ArrayList<Block> obstacles) {
		this.obstacles = obstacles;
	}
	

}
