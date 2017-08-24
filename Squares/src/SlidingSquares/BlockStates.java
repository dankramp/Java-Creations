package SlidingSquares;

import java.util.ArrayList;

public class BlockStates {
	
	private double lastX;
	private double lastY;
	private ArrayList <Block> prevBlock = new ArrayList <Block>();
	private String currentPath;
	
	public BlockStates(double x, double y, ArrayList<Block> block, String p){
		lastX = x;
		lastY = y;
		prevBlock = block;
		currentPath = p;
	}

	public double getLastX() {
		return lastX;
	}

	public void setLastX(double lastX) {
		this.lastX = lastX;
	}

	public double getLastY() {
		return lastY;
	}

	public void setLastY(double lastY) {
		this.lastY = lastY;
	}

	public ArrayList<Block> getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(ArrayList<Block> prevBlock) {
		this.prevBlock = prevBlock;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	

}
