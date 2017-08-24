package slideSimple;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class StartingClassSimple extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private char[][] board;
	private int boardSize;
	private int squaresRemaining;
	private boolean isSlideHoriz;
	private boolean isSlideVert;
	
	private final char NULL = '\u0000';
	private final int UP = 0;
	private final int RIGHT = 1;
	private final int DOWN = 2;
	private final int LEFT = 3;
	
	public void init() {
		setSize(851, 601);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Simple Slide Squares");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		/*
		 * 'O' is the origin with player on it
		 * 'o' is covered origin
		 * 'p' is the player
		 * 'c' is a space that has been covered
		 * 's' is a sticky block
		 * 'S' is a covered sticky block
		 * 'Z' is a player on a sticky block
		 * 'W' is a wall block
		 */
		
		boardSize = 6;
		board = new char[boardSize][boardSize];
		board[2][2] = 'O';
		board[2][3] = 's';
		board[0][3] = 'W';
		squaresRemaining = 34;
		isSlideHoriz = false;
		isSlideVert = false;
		
		//repaint();

		Thread thread = new Thread(this);
		thread.start();
	}
	public void resetLevel() {
		squaresRemaining = 0;
		for (int r = 0; r<boardSize; r++) {
			for (int c = 0; c<boardSize; c++) {
				switch (board[r][c]) {
				case 'c':
				case 'p':
					board[r][c] = NULL;
					squaresRemaining++;
					break;
				case 'Z':
				case 'S':
					board[r][c] = 's';
					squaresRemaining++;
					break;
				case 'o':
					board[r][c] = 'O';
					break;
				case 's':
				case NULL:
					squaresRemaining++;
					break;
				}
			}
		}
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		repaint();
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean solveBoard(char[][] prevBoard) {
		int row = 0, column = 0;
		for (int r = 0; r<boardSize; r++) {
			for (int c = 0; c<boardSize; c++) {
				if (board[r][c] == 'p' || board[r][c] == 'Z' || board[r][c] == 'O') {
					row = r;
					column = c;
					break;
				}
			}
		}
		//System.out.println("\nBoard passed is:");
		//printBoard(prevBoard);
		char[][] currentBoard = new char[boardSize][boardSize];
		for (int r = 0; r<boardSize; r++)
			for (int c = 0; c<boardSize; c++)
				currentBoard[r][c] = board[r][c];
		boolean hasMoved = false;
		if (slideVert(row, column, true)) {
			System.out.println("\nMoved up, board is now:");
			printBoard(board);
			hasMoved = true;
			if (solveBoard(currentBoard)) return true;
		}
		if (slideHoriz(row, column, false)) {
			System.out.println("\nMoved right, board is now:");
			printBoard(board);
			hasMoved = true;
			if (solveBoard(currentBoard)) return true;
		}
		if (slideVert(row, column, false)) {
			System.out.println("\nMoved down, board is now:");
			printBoard(board);
			hasMoved = true;
			if (solveBoard(currentBoard)) return true;
		}
		if (slideHoriz(row, column, true)) {
			System.out.println("\nMoved left, board is now:");
			printBoard(board);
			hasMoved = true;
			if (solveBoard(currentBoard)) return true;
		}
		
		// No possible moves; restore prevBoard
		if (!hasMoved) {
			System.out.println("\nNo possible moves from here, going back to previous board: ");
			board = prevBoard.clone();
			printBoard(board);
		}
		return squaresRemaining == 0;
		
	}
	/**
	 * 
	 * @param row The current row of the player
	 * @param column The current column of the player
	 * @param left True if player is sliding left; false if sliding right
	 * @return True if player can slide at least one block in given direction; else false
	 */
	public boolean slideHoriz(int row, int column, boolean left) {
		int inc = 1;
		if (left)
			inc = -1;
		if ((column < boardSize - 1 && !left) || (left && column > 0)) {
			if (board[row][column + inc] == NULL) {
				if (board[row][column] == 'p')
					board[row][column] = 'c';
				else if (board[row][column] == 'Z')
					board[row][column] = 'S';
				else if (board[row][column] == 'O')
					board[row][column] = 'o';
				board[row][column + inc] = 'p';
				squaresRemaining--;
				run();
				slideHoriz(row, column + inc, left);
				return true;
			}
			else if (board[row][column + inc] == 's') {
				if (board[row][column] == 'p')
					board[row][column] = 'c';
				else if (board[row][column] == 'Z')
					board[row][column] = 'S';
				else if (board[row][column] == 'O')
					board[row][column] = 'o';
				board[row][column + inc] = 'Z';
				squaresRemaining--;
				run();
				return true;
			}
			// If cannot move
			else return false;
		}
		// If at edge of screen
		else return false;
	}
	/**
	 * 
	 * @param row The current row of the player
	 * @param column The current column of the player
	 * @param up True if player is sliding up; false if sliding down
	 * @return True if player can slide at least one block in given direction; else false
	 */
	public boolean slideVert(int row, int column, boolean up) {
		int inc = 1;
		if (up)
			inc = -1;
		if ((row < boardSize - 1 && !up) || (row > 0 && up)) {
			if (board[row + inc][column] == NULL) {
				if (board[row][column] == 'p')
					board[row][column] = 'c';
				else if (board[row][column] == 'Z')
					board[row][column] = 'S';
				else if (board[row][column] == 'O')
					board[row][column] = 'o';
				board[row + inc][column] = 'p';	
				squaresRemaining--;
				run();
				slideVert(row + inc, column, up);
				return true;
			}
			else if (board[row + inc][column] == 's') {
				if (board[row][column] == 'p')
					board[row][column] = 'c';
				else if (board[row][column] == 'Z')
					board[row][column] = 'S';
				else if (board[row][column] == 'O')
					board[row][column] = 'o';
				board[row + inc][column] = 'Z';
				squaresRemaining--;
				run();
				return true;
			}
			// If cannot move one block
			else return false;
		}
		// If at edge
		else return false;
	}
	public void printBoard(char[][] b) {
		System.out.println();
		for (int r = 0; r<boardSize; r++) {
			System.out.println();
			for (int c = 0; c<boardSize; c++) {
				System.out.print(b[r][c] + " ");
			}
		}
	}
	public void update(Graphics g) {		//INITIALIZES GRAPHICS - DONT TOUCH except bg color
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		graphics.setColor(new Color(200, 110, 140));		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, boardSize*100, boardSize*100);
		// Objects
		for (int r = 0; r<boardSize; r++) {
			for (int c = 0; c<boardSize; c++) {
				switch (board[r][c]) {
				case 'p':
				case 'O':
				case 'Z':
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(c*100, r*100, 100, 100);
					g.setColor(Color.RED);
					g.fillOval(c*100, r*100, 100, 100);
					break;
				case 'o':
				case 'c':
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(c*100, r*100, 100, 100);
					break;
				case 'S':
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(c*100, r*100, 100, 100);
				case 's':
					g.setColor(Color.ORANGE);
					g.fillOval(c*100+20, r*100+20, 60, 60);
					break;
				case 'W':
					g.setColor(Color.BLACK);
					g.fillRect(c*100, r*100, 100, 100);
					break;		
				}
			}
		}
		
		// Grid
		g.setColor(Color.YELLOW);
		for (int i = 0; i<=boardSize; i++) {
			g.drawLine(0, i*100, boardSize*100, i*100);
			g.drawLine(i*100, 0, i*100, boardSize*100);
		}
		
		// Text
		g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("Squares Left:", boardSize*100 + 25, 200);
		g.drawString(""+squaresRemaining, boardSize*100 + 45, 235);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		int row = 0, column = 0;
		for (int r = 0; r<boardSize; r++) {
			for (int c = 0; c<boardSize; c++) {
				if (board[r][c] == 'p' || board[r][c] == 'Z' || board[r][c] == 'O') {
					row = r;
					column = c;
					break;
				}
			}
		}
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			System.out.println(slideHoriz(row, column, false));
			break;
		case KeyEvent.VK_LEFT:
			System.out.println(slideHoriz(row, column, true));
			break;
		case KeyEvent.VK_UP:
			System.out.println(slideVert(row, column, true));
			break;
		case KeyEvent.VK_DOWN:
			System.out.println(slideVert(row, column, false));
			break;
		case KeyEvent.VK_ENTER:
			resetLevel();
			break;
		case KeyEvent.VK_F5:
			solveBoard(board);
			break;
		}
		//printBoard();
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
