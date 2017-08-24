package MirrorGame;

import java.awt.Polygon;

public class OneSidedMirror {
	
	private String orientation;
	private Polygon triangle;
	private int x;
	private int y;
	
	public OneSidedMirror(int x, int y, String o){
		orientation = o;
		int[] xCoords = null;
		int[] yCoords = null;
		if (o.equals("dr")){
			xCoords = new int[]{0, 48, 0};
			yCoords = new int[]{0, 0, 48};
		}
		if (o.equals("dl")){
			xCoords = new int[] {0, 48, 48};
			yCoords = new int[]{0, 0, 48};
		}
		if (o.equals("ur")){
			xCoords = new int[]{0, 48, 0};
			yCoords = new int[]{0, 48, 48};
		}
		if (o.equals("ul")){
			xCoords = new int[]{48, 48, 0};
			yCoords = new int[]{0, 48, 48};
		}
		this.x = x;
		this.y = y;
		triangle = new Polygon(xCoords, yCoords, 3);
		triangle.translate(x-24, y-24);
		
	}

	public String getOrientation() {
		return orientation;
	}

	public Polygon getTriangle() {
		return triangle;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	

}
