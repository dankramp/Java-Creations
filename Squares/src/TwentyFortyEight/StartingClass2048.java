package TwentyFortyEight;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartingClass2048 extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private int[][] grid;
	
	public void init() {
		setSize(820, 820);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Twenty Forty-Eight by Dan Kramp");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		grid = new int[4][4];
		//for (int i = 0; i<4; i++)
		//	grid[i][3] = 2;
		addRandomTile(true);
		addRandomTile(true);

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			
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
		graphics.setColor(Color.GRAY);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		// Grid
		g.setColor(Color.GRAY.brighter());
		for (int r = 0; r<4; r++) {
			for (int c = 0; c<4; c++) {
				g.fillRect(c*200 + 20, r*200 + 20, 180, 180);
			}
		}
		
		// Squares
		for (int r = 0; r<4; r++) {
			for (int c = 0; c<4; c++) {
				if (grid[r][c] != 0) {
					g.setColor(Color.WHITE);
					g.fillRect(c*200 + 20, r*200 + 20, 180, 180);
					g.setColor(Color.GRAY.darker());
					g.setFont(new Font("Dialog", Font.BOLD, 65));
					if (grid[r][c] < 10)
						g.drawString(""+grid[r][c], c*200 + 90, r*200 + 130);
					else if (grid[r][c] < 100)
						g.drawString(""+grid[r][c], c*200 + 72, r*200 + 130);
					else if (grid[r][c] < 1000)
						g.drawString(""+grid[r][c], c*200 + 56, r*200 + 130);
					else if (grid[r][c] < 10000)
						g.drawString(""+grid[r][c], c*200 + 38, r*200 + 130);
					else 
						g.drawString(""+grid[r][c], c*200 + 19, r*200 + 130);
				}
			}
		}
	}
	public void addRandomTile(boolean start) {
		// Find empty squares
		// emptySquares contains numEmpty elements
		int[] emptySquares = new int[16];
		int numEmpty = 0;
		for (int r = 0; r<4; r++) {
			for (int c = 0; c<4; c++) {
				if (grid[r][c] == 0)
					emptySquares[numEmpty++] = 4*r+c;
			}
		}
		// Place new square there
		int newSquareCoords = (int)(Math.random()*numEmpty);
		//System.out.println("Place at " + emptySquares[newSquareCoords]/4 + ", " + emptySquares[newSquareCoords]%4);
		// 10% chance new square is a 4; 90% is a 2
		if ((int)(Math.random()*10) == 0 && !start) 
			grid[emptySquares[newSquareCoords]/4][emptySquares[newSquareCoords]%4] = 4;
		else
			grid[emptySquares[newSquareCoords]/4][emptySquares[newSquareCoords]%4] = 2;
	}
	public void slideRight() {
		// r = y, c = x -> move across rows
		boolean moved = false;
		for (int r = 0; r<4; r++) {
			// Start from left, move right
			// Stopping tile marks last possible position for
			// tile to be moved into (avoids double combinations)
			int stoppingTile = 4;
			for (int c = 2; c >= 0; c--) {
				for (int a = c; a<=2; a++) {
					if (grid[r][a] != 0 && a+1 < stoppingTile) {
						if (grid[r][a+1] == 0) {
							grid[r][a+1] = grid[r][a];
							grid[r][a] = 0;
							moved = true;
						}
						else if (grid[r][a+1] == grid[r][a]) {
							grid[r][a+1] = 2*grid[r][a+1];
							grid[r][a] = 0;
							moved = true;
						}
						else
							stoppingTile = a+1;
					}
					else
						break;
				}
			}
		}
		if (moved)
			addRandomTile(false);
	}
	public void slideLeft() {
		// r = y, c = x -> move across rows
		boolean moved = false;
		for (int r = 0; r<4; r++) {
			// Start from right, move left
			// Stopping tile marks last possible position for
			// tile to be moved into (avoids double combinations)
			int stoppingTile = -1;
			for (int c = 1; c < 4; c++) {
				for (int a = c; a >= 1; a--) {
					if (grid[r][a] != 0 && a-1 > stoppingTile) {
						if (grid[r][a-1] == 0) {
							grid[r][a-1] = grid[r][a];
							grid[r][a] = 0;
							moved = true;
						}
						else if (grid[r][a-1] == grid[r][a]) {
							grid[r][a-1] = 2*grid[r][a-1];
							grid[r][a] = 0;
							moved = true;
						}
						else 
							stoppingTile = a-1;
					}
					else
						break;
				}
			}
		}
		if (moved)
			addRandomTile(false);
	}
	public void slideUp() {
		// r = y, c = x -> move across rows
		boolean moved = false;
		for (int c = 0; c<4; c++) {
			// Start from top, move down
			// Stopping tile marks last possible position for
			// tile to be moved into (avoids double combinations)
			int stoppingTile = -1;
			for (int r = 1; r < 4; r++) {
				for (int a = r; a >= 1; a--) {
					if (grid[a][c] != 0 && a-1 > stoppingTile) {
						if (grid[a-1][c] == 0) {
							grid[a-1][c] = grid[a][c];
							grid[a][c] = 0;
							moved = true;
						}
						else if (grid[a-1][c] == grid[a][c]) {
							grid[a-1][c] = 2*grid[a-1][c];
							grid[a][c] = 0;
							moved = true;
						}
						else 
							stoppingTile = a-1;
					}
					else
						break;
				}
			}
		}
		if (moved)
			addRandomTile(false);
	}
	public void slideDown() {
		// r = y, c = x -> move across rows
		boolean moved = false;
		for (int c = 0; c<4; c++) {
			// Start from bottom, move up
			// Stopping tile marks last possible position for
			// tile to be moved into (avoids double combinations)
			int stoppingTile = 4;
			for (int r = 2; r >= 0; r--) {
				for (int a = r; a <=2; a++) {
					if (grid[a][c] != 0 && a+1 < stoppingTile) {
						if (grid[a+1][c] == 0) {
							grid[a+1][c] = grid[a][c];
							grid[a][c] = 0;
							moved = true;
						}
						else if (grid[a+1][c] == grid[a][c]) {
							grid[a+1][c] = 2*grid[a+1][c];
							grid[a][c] = 0;
							moved = true;
						}
						else 
							stoppingTile = a+1;
					}
					else
						break;
				}
			}
		}
		if (moved)
			addRandomTile(false);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			slideRight();
			break;
		case KeyEvent.VK_LEFT:
			slideLeft();
			break;
		case KeyEvent.VK_UP:
			slideUp();
			break;
		case KeyEvent.VK_DOWN:
			slideDown();
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
