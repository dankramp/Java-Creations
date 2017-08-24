package MirrorGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class StartingClassMirrors extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private int[][] grid;
	private int highlightX;
	private int highlightY;
	private int level;
	private boolean placeable;
	private ArrayList <Mirror> mirrors = new ArrayList <Mirror>();
	private Level stage;
	private boolean testBoard;
	private int lazerX, lazerY;
	private boolean lazerUp, lazerDown, lazerRight, lazerLeft;
	private boolean gameWon;
	private int counter;
	private boolean advanceLevel;
	private int attempts;
	private int object;
	private boolean allHit;
	private int moveCount;
		
	private final boolean BUILD_MODE = false;
	
	
	public void init() {
		setSize(750, 550);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Reflecto Boards");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		grid = new int[13][10];
		object = 0;
		highlightX = 0;
		highlightY = 0;
		level = 1;
		if (!BUILD_MODE)stage = new Level(level);
		else stage = new Level(100);
		advanceLevel = false;
		testBoard = false;
		lazerX = 525;
		lazerY = 250;
		lazerUp = false;
		lazerDown = false;
		lazerRight = false;
		lazerLeft = false;
		gameWon = false;
		attempts = 0;
		allHit = false;
		moveCount = 0;
		

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			moveCount++;
			if (moveCount>100)moveCount = 0;
			
			//HIGHLIGHT LIMITER
			if (highlightX>9)highlightX = 0;
			else if (highlightX<0)highlightX = 9;
			if (highlightY>8)highlightY = 0;
			else if (highlightY<0)highlightY = 8;
			
			if (!testBoard){
				gameWon = false;
				for (int i = 0; i<stage.getMandatories().size(); i++){
					stage.getMandatories().get(i).setHit(false);
				}
			}
			allHit = true;
			for (int i = 0; i<stage.getMandatories().size(); i++){
				if (!stage.getMandatories().get(i).isHit())allHit = false;
			}
			
			if (advanceLevel&&!BUILD_MODE){
				mirrors.clear();
				level++;
				stage = new Level(level);
				highlightX = 0;
				highlightY = 0;
				attempts = 0;
				advanceLevel = false;
			}
			
			//System.out.println("Left: "+lazerLeft+"\nRight: "+lazerRight+"\nUp: "+lazerUp+"\nDown: "+lazerDown);
			
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(Graphics g) {		//INITIALIZES GRAPHICS - DONT TOUCH except bg color
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		graphics.setColor(Color.BLACK);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		//BOARD
		g.setColor(Color.GRAY);
		g.fillRect(50, 50, 500, 450);
		g.setColor(new Color (150, 150, 150));
		g.fillRect(605, 50, 140, 451);
		//TEXT
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", 50, 20));
		if (!BUILD_MODE) {
			g.drawString("Level: " + level, 610, 75);
			g.drawString("Mirror(s): " + (stage.getMirrorSupply() - mirrors.size()),610, 105);
			g.drawString("Attempts: " + attempts, 610, 135);
			if (!testBoard) {
				g.setFont(new Font("Dialog", 50, 30));
				g.drawString("Press 'B'", 610, 200);
				g.drawString("to test", 610, 230);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Dialog", 50, 20));
				g.drawString("Press SPACE", 610, 320);
				g.drawString("to place mirror", 610, 350);
				g.drawString("Press ENTER", 610, 390);
				g.drawString("to flip mirror", 610, 420);
				g.drawString("Press ESCAPE", 607, 460);
				g.drawString("to clear board", 610, 490);
			}
			if (gameWon) {
				g.setColor(Color.GREEN);
				g.drawString("Level beat!", 610, 190);
			}
			if (!gameWon && testBoard) {
				g.setColor(Color.RED);
				g.drawString("Level failed", 610, 190);
			}
		}
		else {
			g.drawString("X: " + (highlightX*50+75), 610, 75);
			g.drawString("Y: " + (highlightY*50+75), 680, 75);
			g.setFont(new Font("Dialog", 50, 20));
			g.drawString("Use WASD to", 610, 130);
			g.drawString("move target", 610, 160);
			g.drawString("Press SHIFT", 610, 200);
			g.drawString("to switch object", 607, 230);
			if (object==0){
				g.drawString("Mirror:", 607, 275);
				g.drawLine(670, 290, 710, 250);
			}
			else if (object==1){
				g.drawString("Mirror:", 607, 275);
				g.setColor(Color.BLACK);
				g.drawLine(670, 290, 710, 250);
			}
			else if (object==2){
				g.drawString("Block:", 607, 275);
				g.setColor(Color.BLACK);
				g.fillRect(680, 250, 40, 40);
			}
			else if (object==3){
				g.drawString("Pad:", 607, 275);
				g.setColor(new Color (255, 100, 100));
				g.fillRect(680, 250, 40, 40);
			}
			else if (object==4){
				g.drawString("One Way:", 607, 275);
				g.setColor(Color.BLACK);
				int[] x = {700, 700, 740};
				int[] y = {290, 250, 250};
				g.fillPolygon(x, y, 3);
			}
			g.setColor(Color.WHITE);

			g.drawString("Press SPACE", 610, 320);
			g.drawString("to place object", 610, 350);
			g.drawString("Press ENTER", 610, 390);
			g.drawString("to flip object", 610, 420);
			g.drawString("Press ESCAPE", 607, 460);
			g.drawString("to clear board", 610, 490);

		}

		//LAZER HOLES
		if (!gameWon)g.setColor(Color.RED);
		else g.setColor(Color.GREEN);
		g.fillOval(565, 265, 20, 20);
		g.fillOval(stage.getTargetX()-10, stage.getTargetY()-10, 20, 20);
		g.setColor(new Color(255, 255, 255, 200));
		g.fillOval(567, 267, 15, 15);
		g.fillOval(stage.getTargetX()-8, stage.getTargetY()-8, 15, 15);
		if (!gameWon)g.setColor(Color.RED);
		else g.setColor(Color.GREEN);
		g.fillOval(568, 268, 16, 16);
		g.fillOval(stage.getTargetX()-7, stage.getTargetY()-7, 16, 16);
		
		//MIRRORS
		g.setColor(Color.WHITE);
		for (int i = 0; i<mirrors.size(); i++){
			if (mirrors.get(i).isNegativeSlope()){
				g.drawLine(mirrors.get(i).getX()-25, mirrors.get(i).getY()-25, mirrors.get(i).getX()+25, mirrors.get(i).getY()+25); 
			}
			else g.drawLine(mirrors.get(i).getX()-25, mirrors.get(i).getY()+25, mirrors.get(i).getX()+25, mirrors.get(i).getY()-25); 
		}
		g.setColor(Color.BLACK);
		for (int i = 0; i<stage.getMirrors().size(); i++){
			if (stage.getMirrors().get(i).isNegativeSlope()){
				g.drawLine(stage.getMirrors().get(i).getX()-25, stage.getMirrors().get(i).getY()-25, stage.getMirrors().get(i).getX()+25, stage.getMirrors().get(i).getY()+25); 
			}
			else g.drawLine(stage.getMirrors().get(i).getX()-25, stage.getMirrors().get(i).getY()+25, stage.getMirrors().get(i).getX()+25, stage.getMirrors().get(i).getY()-25); 
		}
		for (int i = 0; i<stage.getMandatories().size(); i++){
			if (stage.getMandatories().get(i).isHit())g.setColor(new Color(100, 255, 100));
			else g.setColor(new Color(255, 100, 100));
			g.fillRect(stage.getMandatories().get(i).getX()-24, stage.getMandatories().get(i).getY()-24, 49, 49);
		}
		//GRID
		g.setColor(Color.WHITE);
		for (int row = 1; row<grid.length-1; row++){
			g.drawLine(row*50, 50, row*50, 500);
		}
		for (int col = 0; col<grid[0].length; col++){
			g.drawLine(50, col*50+50, 550, col*50+50);
		}
		//LAZER PATH
		lazerX = 525;
		lazerY = 275;
		lazerUp = false;
		lazerDown = false;
		lazerRight = false;
		lazerLeft = true;
		counter = 0;
		if (!gameWon)g.setColor(Color.RED);
		else g.setColor(Color.GREEN);
		if (testBoard){
			gameWon = false;
			g.drawLine(570, 275, 525, 275);
			g.fillOval(573-moveCount/2, 273, 4, 4);
			while(counter<500){
				//PLAYER MIRRORS
				counter++;
				for (int i = 0; i<mirrors.size(); i++){
					if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&mirrors.get(i).isNegativeSlope()&&lazerLeft){
						lazerLeft = false;
						//lazerRight = false;
						//lazerDown = false;
						lazerUp = true;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&mirrors.get(i).isNegativeSlope()&&lazerUp){
						lazerLeft = true;
						//lazerRight = false;
						//lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&mirrors.get(i).isNegativeSlope()&&lazerRight){
						//lazerLeft = false;
						lazerRight = false;
						//lazerUp = false;
						lazerDown = true;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&mirrors.get(i).isNegativeSlope()&&lazerDown){
						//lazerLeft = false;
						lazerRight = true;
						lazerDown = false;
						//lazerUp = false;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&!mirrors.get(i).isNegativeSlope()&&lazerLeft){
						lazerLeft = false;
						//lazerRight = false;
						lazerDown = true;
						//lazerUp = false;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&!mirrors.get(i).isNegativeSlope()&&lazerUp){
						//lazerLeft = false;
						lazerRight = true;
						//lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&!mirrors.get(i).isNegativeSlope()&&lazerRight){
						//lazerLeft = false;
						lazerRight = false;
						//lazerDown = false;
						lazerUp = true;
						break;
					}
					else if (lazerX==mirrors.get(i).getX()&&lazerY == mirrors.get(i).getY()&&!mirrors.get(i).isNegativeSlope()&&lazerDown){
						lazerLeft = true;
						//lazerRight = false;
						lazerDown = false;
						//lazerUp = false;
						break;
					}
				}
				//PRESET MIRRORS
				for (int i = 0; i<stage.getMirrors().size(); i++){
					if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&stage.getMirrors().get(i).isNegativeSlope()&&lazerLeft){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = false;
						lazerUp = true;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&stage.getMirrors().get(i).isNegativeSlope()&&lazerUp){
						lazerLeft = true;
						lazerRight = false;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&stage.getMirrors().get(i).isNegativeSlope()&&lazerRight){
						lazerLeft = false;
						lazerRight = false;
						lazerUp = false;
						lazerDown = true;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&stage.getMirrors().get(i).isNegativeSlope()&&lazerDown){
						lazerLeft = false;
						lazerRight = true;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&!stage.getMirrors().get(i).isNegativeSlope()&&lazerLeft){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = true;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&!stage.getMirrors().get(i).isNegativeSlope()&&lazerUp){
						lazerLeft = false;
						lazerRight = true;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&!stage.getMirrors().get(i).isNegativeSlope()&&lazerRight){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = false;
						lazerUp = true;
						break;
					}
					else if (lazerX==stage.getMirrors().get(i).getX()&&lazerY == stage.getMirrors().get(i).getY()&&!stage.getMirrors().get(i).isNegativeSlope()&&lazerDown){
						lazerLeft = true;
						lazerRight = false;
						lazerDown = false;
						lazerUp = false;
						break;
					}
				}
				//PRESET BLOCKS
				for (int i = 0; i<stage.getBlocks().size(); i++){
					if (lazerX==stage.getBlocks().get(i).getX()&&lazerY == stage.getBlocks().get(i).getY()){
						lazerUp = false;
						lazerDown = false;
						lazerRight = false;
						lazerLeft = false;
					}
				}
				//PRESET ONE SIDED MIRRORS
				for (int i = 0; i<stage.getOneSided().size(); i++){
					if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("ur")&&lazerLeft){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = false;
						lazerUp = true;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("ur")&&lazerDown){
						lazerLeft = false;
						lazerRight = true;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("ul")&&lazerRight){
						lazerLeft = false;
						lazerRight = false;
						lazerUp = true;
						lazerDown = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("ul")&&lazerDown){
						lazerLeft = true;
						lazerRight = false;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("dl")&&lazerRight){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = true;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("dl")&&lazerUp){
						lazerLeft = true;
						lazerRight = false;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("dr")&&lazerUp){
						lazerLeft = false;
						lazerRight = true;
						lazerDown = false;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()&&stage.getOneSided().get(i).getOrientation().equals("dr")&&lazerLeft){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = true;
						lazerUp = false;
						break;
					}
					else if (lazerX==stage.getOneSided().get(i).getX()&&lazerY == stage.getOneSided().get(i).getY()){
						lazerLeft = false;
						lazerRight = false;
						lazerDown = false;
						lazerUp = false;
						break;
					}
				}
				if (lazerX<=525&&lazerX>50&&lazerY<500&&lazerY>25){
				if ((lazerX!=stage.getTargetX()||lazerY!=stage.getTargetY())){
				if (lazerUp){
					g.drawLine(lazerX, lazerY, lazerX, lazerY-50);
					//g.fillOval((int)(lazerX-Math.cos(moveCount/100.0*Math.PI*2)-2), lazerY-(int)(Math.cos(moveCount/100.0*Math.PI*2))-moveCount/2-2, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4);
					g.fillOval(lazerX-2, lazerY-2-moveCount/2, 4, 4);
					lazerY-=50;
				}
				else if (lazerDown){
					g.drawLine(lazerX, lazerY, lazerX, lazerY+50);
					//g.fillOval((int)(lazerX-Math.cos(moveCount/100.0*Math.PI*2)-2), lazerY-(int)(Math.cos(moveCount/100.0*Math.PI*2))+moveCount/2-2, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4);
					g.fillOval(lazerX-2, lazerY-2+moveCount/2, 4, 4);
					lazerY+=50;
				}
				else if (lazerRight){
					g.drawLine(lazerX, lazerY, lazerX+50, lazerY);
					//g.fillOval((int)(lazerX-Math.cos(moveCount/100.0*Math.PI*2)-2)+moveCount/2, lazerY-(int)(Math.cos(moveCount/100.0*Math.PI*2))-2, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4);
					g.fillOval(lazerX-2+moveCount/2, lazerY-2, 4, 4);
					lazerX+=50;					
				}
				else if (lazerLeft&&lazerX>50){
					g.drawLine(lazerX, lazerY, lazerX-50, lazerY);
					//g.fillOval((int)(lazerX-Math.cos(moveCount/100.0*Math.PI*2)-2)-moveCount/2, lazerY-(int)(Math.cos(moveCount/100.0*Math.PI*2))-2, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4, (int)(2*Math.cos(moveCount/100.0*Math.PI*2))+4);
					g.fillOval(lazerX-2-moveCount/2, lazerY-2, 4, 4);
					lazerX-=50;
				}
				for (int a = 0; a<stage.getMandatories().size(); a++){
					if (lazerX==stage.getMandatories().get(a).getX()&&lazerY==stage.getMandatories().get(a).getY()){
						stage.getMandatories().get(a).setHit(true);
					}
				}
				}
				}
				if (lazerY == stage.getTargetY()&&lazerX == stage.getTargetX()&&allHit)gameWon = true;
			}
		}
		//BLOCKS
		for (int i = 0; i<stage.getBlocks().size(); i++){
			g.setColor(Color.BLACK);
			g.fillRect(stage.getBlocks().get(i).getX()-24, stage.getBlocks().get(i).getY()-24, 49, 49);
		}
		//TRIANGLE
		for (int i = 0; i<stage.getOneSided().size(); i++){
			g.setColor(Color.BLACK);
			g.fillPolygon(stage.getOneSided().get(i).getTriangle());
		}
		//HIGHLIGHTED BLOCK
		if (!testBoard){
			g.setColor(new Color(255, 255, 0, 150));
			g.fillRect(highlightX*50+50, highlightY*50+50, 50, 50);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			if (testBoard)testBoard = false;
			else highlightX++;
			if (gameWon)advanceLevel = true;
			break;
		case KeyEvent.VK_LEFT:
			if (testBoard)testBoard = false;
			else highlightX--;
			if (gameWon)advanceLevel = true;
			break;
		case KeyEvent.VK_UP:
			if (testBoard)testBoard = false;
			else highlightY--;
			if (gameWon)advanceLevel = true;
			break;
		case KeyEvent.VK_DOWN:
			if (testBoard)testBoard = false;
			else highlightY++;
			if (gameWon)advanceLevel = true;
			break;
		case KeyEvent.VK_SPACE:
			if (gameWon)advanceLevel = true;
			if (testBoard)testBoard = false;
			else {
			
			placeable = true;
			for (int i = 0; i<mirrors.size(); i++){
				if (mirrors.get(i).getX()==highlightX*50+75&&mirrors.get(i).getY()==highlightY*50+75){
					placeable = false;
					mirrors.remove(i);
				}
			}
			for (int i = 0; i<stage.getMirrors().size(); i++){
				if (stage.getMirrors().get(i).getX()==highlightX*50+75&&stage.getMirrors().get(i).getY()==highlightY*50+75){
					placeable = false;
					if (BUILD_MODE)stage.getMirrors().remove(i);
				}
			}
			for (int i = 0; i<stage.getBlocks().size(); i++){
				if (stage.getBlocks().get(i).getX()==highlightX*50+75&&stage.getBlocks().get(i).getY()==highlightY*50+75){
					placeable = false;
					if (BUILD_MODE)stage.getBlocks().remove(i);
				}
			}
			for (int i = 0; i<stage.getMandatories().size(); i++){
				if (stage.getMandatories().get(i).getX()==highlightX*50+75&&stage.getMandatories().get(i).getY()==highlightY*50+75){
					placeable = false;
					if (BUILD_MODE)stage.getMandatories().remove(i);
				}
			}
			for (int i = 0; i<stage.getOneSided().size(); i++){
				if (stage.getOneSided().get(i).getX()==highlightX*50+75&&stage.getOneSided().get(i).getY()==highlightY*50+75){
					placeable = false;
					if (BUILD_MODE)stage.getOneSided().remove(i);
				}
			}
			if (!BUILD_MODE){
				if (placeable&&mirrors.size()<stage.getMirrorSupply())mirrors.add(new Mirror(highlightX*50+75, highlightY*50+75, true));
			}
			if (BUILD_MODE){
				if (placeable&&object==0)mirrors.add(new Mirror(highlightX*50+75, highlightY*50+75, true));
				else if (placeable&&object==1)stage.getMirrors().add(new Mirror(highlightX*50+75, highlightY*50+75, true));
				else if (placeable&&object==2)stage.getBlocks().add(new Block(highlightX*50+75, highlightY*50+75));
				else if (placeable&&object==3)stage.getMandatories().add(new Mandatory(highlightX*50+75, highlightY*50+75, false));
				else if (placeable&&object==4)stage.getOneSided().add(new OneSidedMirror(highlightX*50+75, highlightY*50+75, "dr"));
			}
			}
			break;
		case KeyEvent.VK_ENTER:
			if (gameWon)advanceLevel = true;
			if (testBoard)testBoard = false;
			else {
			for (int i = 0; i<mirrors.size(); i++){
				if (mirrors.get(i).getX()==highlightX*50+75&&mirrors.get(i).getY()==highlightY*50+75&&mirrors.get(i).isNegativeSlope()){
					mirrors.get(i).setNegativeSlope(false);
				}
				else if (mirrors.get(i).getX()==highlightX*50+75&&mirrors.get(i).getY()==highlightY*50+75&&!mirrors.get(i).isNegativeSlope()){
					mirrors.get(i).setNegativeSlope(true);
				}
			}
			if (BUILD_MODE){
				for (int i = 0; i<stage.getMirrors().size(); i++){
					if (stage.getMirrors().get(i).getX()==highlightX*50+75&&stage.getMirrors().get(i).getY()==highlightY*50+75&&stage.getMirrors().get(i).isNegativeSlope()){
						stage.getMirrors().get(i).setNegativeSlope(false);
					}
					else if (stage.getMirrors().get(i).getX()==highlightX*50+75&&stage.getMirrors().get(i).getY()==highlightY*50+75&&!stage.getMirrors().get(i).isNegativeSlope()){
						stage.getMirrors().get(i).setNegativeSlope(true);
					}
				}
				for (int i = 0; i<stage.getOneSided().size(); i++){
					if (stage.getOneSided().get(i).getX()==highlightX*50+75&&stage.getOneSided().get(i).getY()==highlightY*50+75&&stage.getOneSided().get(i).getOrientation().equals("dr")){
						stage.getOneSided().remove(i);
						stage.getOneSided().add(new OneSidedMirror(highlightX*50+75,highlightY*50+75, "dl"));
					}
					else if (stage.getOneSided().get(i).getX()==highlightX*50+75&&stage.getOneSided().get(i).getY()==highlightY*50+75&&stage.getOneSided().get(i).getOrientation().equals("dl")){
						stage.getOneSided().remove(i);
						stage.getOneSided().add(new OneSidedMirror(highlightX*50+75,highlightY*50+75, "ul"));
					}
					else if (stage.getOneSided().get(i).getX()==highlightX*50+75&&stage.getOneSided().get(i).getY()==highlightY*50+75&&stage.getOneSided().get(i).getOrientation().equals("ul")){
						stage.getOneSided().remove(i);
						stage.getOneSided().add(new OneSidedMirror(highlightX*50+75,highlightY*50+75, "ur"));
					}
					else if (stage.getOneSided().get(i).getX()==highlightX*50+75&&stage.getOneSided().get(i).getY()==highlightY*50+75&&stage.getOneSided().get(i).getOrientation().equals("ur")){
						stage.getOneSided().remove(i);
						stage.getOneSided().add(new OneSidedMirror(highlightX*50+75,highlightY*50+75, "dr"));
					}
				}
			}
			}
			break;
		case KeyEvent.VK_ESCAPE:
			mirrors.clear();
			testBoard = false;
			if (BUILD_MODE){
				stage.getBlocks().clear();
				stage.getMirrors().clear();
				stage.getMandatories().clear();
			}

			break;
		case KeyEvent.VK_B:
			if (gameWon)advanceLevel = true;
			testBoard^=true;
			lazerLeft = true;
			attempts++;
			break;
		case KeyEvent.VK_SHIFT:
			if (BUILD_MODE){
				object++;
				if (object>4)object = 0;
			}
			if (testBoard)testBoard = false;
			break;
		case KeyEvent.VK_D:
			if (BUILD_MODE){
				if ((stage.getTargetY()>500||stage.getTargetY()<75)&&stage.getTargetX()<550){
					stage.setTargetX(stage.getTargetX()+50);
				}
			}
			if (testBoard)testBoard = false;
			break;
		case KeyEvent.VK_W:
			if (BUILD_MODE){
				if ((stage.getTargetX()<75||stage.getTargetX()>550)&&stage.getTargetY()>50){
					stage.setTargetY(stage.getTargetY()-50);
				}
			}
			if (testBoard)testBoard = false;
			break;
		case KeyEvent.VK_A:
			if (BUILD_MODE){
				if ((stage.getTargetY()>500||stage.getTargetY()<75)&&stage.getTargetX()>25){
					stage.setTargetX(stage.getTargetX()-50);
				}
			}
			if (testBoard)testBoard = false;
			break;
		case KeyEvent.VK_S:
			if (BUILD_MODE){
				if ((stage.getTargetX()<75||stage.getTargetX()>550)&&stage.getTargetY()<500){
					stage.setTargetY(stage.getTargetY()+50);
				}
			}
			if (testBoard)testBoard = false;
			break;
		case KeyEvent.VK_P:
			if (BUILD_MODE){
				for (int i = 0; i<stage.getBlocks().size(); i++){
					System.out.println("blocks.add(new Block("+stage.getBlocks().get(i).getX()+", "+stage.getBlocks().get(i).getY()+"));");
				}
				for (int i = 0; i<stage.getMirrors().size(); i++){
					System.out.println("mirrors.add(new Mirror("+stage.getMirrors().get(i).getX()+", "+stage.getMirrors().get(i).getY()+", "+stage.getMirrors().get(i).isNegativeSlope()+"));");
				}
				for (int i = 0; i<stage.getMandatories().size(); i++){
					System.out.println("mandatories.add(new Mandatory("+stage.getMandatories().get(i).getX()+", "+stage.getMandatories().get(i).getY()+", false));");
				}
				for (int i = 0; i<stage.getOneSided().size(); i++){
					System.out.println("oneSided.add(new OneSidedMirror("+stage.getOneSided().get(i).getX()+", "+stage.getOneSided().get(i).getY()+", \""+stage.getOneSided().get(i).getOrientation()+"\"));");
				}
				System.out.println("mirrorSupply = "+mirrors.size()+";");
				System.out.println("targetX = "+stage.getTargetX()+";");
				System.out.println("targetY = "+stage.getTargetY()+";");
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
