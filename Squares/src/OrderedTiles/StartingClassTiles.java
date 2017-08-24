package OrderedTiles;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartingClassTiles extends Applet implements Runnable, KeyListener, MouseListener {
	
	private int[][] tiles;
	private int r0, c0; // coordinates of empty space
	private boolean won;
	private boolean solveMode;
	private boolean shouldRepaint;
	private long previousTime;
	private long deltaTime;

	private Image image;
	private Graphics graphics;
	
	public void init() {
		setSize(401, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Slidey Tiles");
		frame.setResizable(false);
		frame.setLocationRelativeTo(this);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		// Initializes 2D array from 0-15
		tiles = new int[4][4];
		for (int r = 0; r<4; r++) {
			for (int c = 0; c<4; c++) {
				if (r==3 && c==3) tiles[r][c] = 0; //sets 0 to bottom right corner
				else tiles[r][c] = 4*r+c+1;
			}
		}
		r0 = 3;
		c0 = 3;
		// Randomly moves blank tile 50000 times
		for (int i = 0; i < 50000; i++) {
			int rand = (int) (Math.random() * 4);
			switch (rand) {
			case 0: // move blank space to the right
				if (c0 < 3) {
					tiles[r0][c0] = tiles[r0][c0 + 1];
					tiles[r0][c0 + 1] = 0;
					c0++;
				}
				break;
			case 1: // move blank space to the left
				if (c0 > 0) {
					tiles[r0][c0] = tiles[r0][c0 - 1];
					tiles[r0][c0 - 1] = 0;
					c0--;
				}
				break;
			case 2: // move blank space up
				if (r0 > 0) {
					tiles[r0][c0] = tiles[r0 - 1][c0];
					tiles[r0 - 1][c0] = 0;
					r0--;
				}
				break;
			case 3: // move blank space down
				if (r0 < 3) {
					tiles[r0][c0] = tiles[r0 + 1][c0];
					tiles[r0 + 1][c0] = 0;
					r0++;
				}
				break;
			}
		}
		
		// moves blank tile to bottom right corner
		while (c0 != 3 || r0 != 3) { 
			if (c0 < 3) switchTiles(r0, c0+1);
			else if (r0 < 3) switchTiles(r0+1, c0);
		}
		
		won = false;
		solveMode = false;
		shouldRepaint = true;
		previousTime = System.nanoTime();
		deltaTime = 500000000L;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			long currentTime = System.nanoTime();
			if (solveMode && currentTime > previousTime + deltaTime) {
				previousTime = currentTime;
				System.out.println("dusbd");
				//Finds first number out of place 1-8
				int toMove = 9;
				for (int i = 1; i<9; i++) {
					if (tiles[i/4][i%4] != i-1) {
						toMove = i;
						break;
					}
				}
				// For numbers 1-8
				if (toMove < 9) {
					
				}
				
			}
			if(shouldRepaint)
				next();
		}
	}
	/**
	 * Triggers repaint
	 */
	public void next() {
		shouldRepaint = false;
		repaint();
	}
	/**
	 * Switches referenced tile with blank tile, if valid switch
	 * @param r The row number
	 * @param c The column number
	 * @return True if switch was made; false if switch is invalid
	 */
	public boolean switchTiles(int r, int c) {
		
		if ((Math.abs(r-r0)==1&&Math.abs(c-c0)==0) || (Math.abs(r-r0)==0&&Math.abs(c-c0)==1)) { //If selected tile is valid to move
			int a = tiles[r][c];
			tiles[r][c] = 0;
			tiles[r0][c0] = a;
			r0 = r;
			c0 = c;
			// checks for winning board
			won = true;
			for (int row = 0; row<4; row++) {
				for (int col = 0; col<4; col++) {
					if (row==3 && col==3) won&=tiles[row][col] == 0;
					else won&=tiles[row][col] == 4*row+col+1;
				}
			}
			return true;
		}
		return false;
	}
	public void update(Graphics g) {		//INITIALIZES GRAPHICS - DONT TOUCH except bg color
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		graphics.setColor(Color.WHITE);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		//Paints tiles
		for (int r = 0; r<4; r++) {
			for (int c = 0; c<4; c++) {
				if (tiles[r][c] != 0) {
					g.setColor(new Color(255, 220, 100));
					g.fillRect(c * 100, r * 100, 100, 100);
					g.setColor(Color.BLACK);
					g.drawRect(c * 100, r * 100, 100, 100);
					g.setColor(Color.BLUE);
					g.setFont(new Font("Dialog", 30, 30));
					g.drawString("" + tiles[r][c], c * 100 + 35, r * 100 + 55);
				}
			}
		}
		// Win title
		if (won) g.drawString("Winner!", 100, 450);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
			solveMode^=true;
			break;
		case KeyEvent.VK_ENTER:
			start();
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
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getY()<400) shouldRepaint = switchTiles(arg0.getY()/100, arg0.getX()/100);
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
