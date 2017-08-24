package Buckets;

import java.util.ArrayList;

public class Bucket {
	
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public ArrayList<Pipe> connectedPipes;
	public int waterHeight;
	
	public Bucket(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		waterHeight = 0;
		connectedPipes = new ArrayList<Pipe>();
	}

}
