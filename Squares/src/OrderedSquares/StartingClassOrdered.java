package OrderedSquares;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class StartingClassOrdered extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private ArrayList<Square> squares = new ArrayList<Square>();
	private boolean clicked;
	private Point mousePos;
	private boolean dragging;
	private boolean holding;
	private Point posFrom;
	private boolean won;
	
	private boolean solveMode;
	
	private Image image;
	private Graphics graphics;
	private Frame frame;
	
	public void init() {
		setSize(801, 801);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		frame = (Frame) this.getParent().getParent();
		frame.setTitle("Ordered Squares");
		frame.setResizable(false);
		frame.setLocationRelativeTo(this);
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		//IMPLEMENT "TAP TO SWITCH" CAPABILITES
				
		int x = JOptionPane.showConfirmDialog(null,
				"Randomize board?", "Game Start", JOptionPane.YES_NO_OPTION);
		//RANDOMIZES BOARD
		if (x == 0) {
			//Initializes board to winning board
			int[][] nums = new int[4][4];
			for (int i = 0; i<4; i++) {
				for (int a = 0; a<4; a++) {
					nums[i][a] = i+1+1*a*4;
				}
			}
			nums[3][3] = 0;
			//Randomly switches blocks around
			for (int c = 0; c<100000; c++){
				int orient = 0;
				int mag = 0;
				label: for (int i = 0; i<4; i++) {
					for (int a = 0; a<4; a++) {
						if (nums[i][a] == 0) {
							orient = (int) (Math.random() * 2);
							if (Math.random()*2>1) mag = 1;
							else mag = -1;
							if (orient == 0) {
								if (i == 0 || i == 3) {
									mag = (2 - i) / (Math.abs(2 - i));
								}
								
								nums[i][a] = nums[i + mag][a];
								nums[i + mag][a] = 0;
							} 
							else {
								if (a == 0 || a == 3) {
									mag = (2 - a) / (Math.abs(2 - a));
								}
								nums[i][a] = nums[i][a + mag];
								nums[i][a + mag] = 0;
							}
							break label;
						}
					}
				}
			}
			//Moves empty space to top right corner
			while (true){
				for (int i = 0; i<4; i++) {
					for (int a = 0; a<4; a++) {
						if (nums[i][a]==0&&(i!=0)) {
							nums[i][a] = nums[i-1][a];
							nums[i-1][a] = 0;
						}
						if (nums[i][a]==0&&(a!=0)) {
							nums[i][a] = nums[i][a-1];
							nums[i][a-1] = 0;
						}
					}
				}
				if (nums[0][0] == 0)break;
			}
			int blockWidth = frame.getWidth()/4;
			//Adds squares to ArrayList
			
			for (int i = 0; i<4; i++) {
				int total = 1;
				for (int a = 0; a<4; a++) {
					total*=nums[a][i];
					//System.out.print(nums[a][i]+"  ");
					if (nums[i][a]!=0)squares.add(new Square(i * blockWidth + blockWidth/2, a * blockWidth + blockWidth/2, nums[i][a]));
				}
				//System.out.println("    "+total);
			}
			/*int[][] test2 = {{2, 4, 5, 11},{3,3, 7, 14}, {5, 2, 1, 0}, {4, 3, 1, 7}};
			for (int i = 0; i<test2.length; i++) {
				for (int a = 0; a<test2.length; a++) {
					System.out.print(test2[i][a]+"   ");
				}
				System.out.println();
			}
			System.out.println("\n"+sqDeterminant(test2));*/
			
			//System.out.println("\n"+sqDeterminant(nums));
		}
		//Initializes winning board
		else {
			int blockWidth = frame.getWidth()/4;
			for (int i = 0; i < 4; i++) {
				for (int a = 0; a < 4; a++) {
					if (!(a == 3 && i == 3))
						squares.add(new Square(i * blockWidth + blockWidth/2, a * blockWidth + blockWidth/2,i+1+1*a*4));
				}
			}
		}
		
		mousePos = new Point(0,0);
		dragging = false;
		clicked = false;
		holding = false;
		posFrom = new Point(0,0);
		won = false;
		
		solveMode = false;

		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		ArrayList <Integer> nums = new ArrayList <Integer>();
		for (int i = 0; i<15; i++){
			nums.add(i+1);
		}
		squares.clear();
		for (int i = 0; i<4; i++) {
			for (int a = 0; a<4; a++) {
				int rand = (int)(Math.random()*nums.size());
				if (!(a==0&&i==0))squares.add(new Square(i*150+75, a*150+75, nums.remove(rand)));
			}
		}
		JOptionPane.showMessageDialog(frame, "Play again?", "Game Won!", 2);
		mousePos = new Point(0,0);
		dragging = false;
		clicked = false;
		holding = false;
		posFrom = new Point(0,0);
		won = false;
	}
	public int sqDeterminant(int[][] matrix){
		int size = matrix.length;
		int det = 0;
		if (size==2){
			return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
		}
		for (int i = 0; i<size; i++){
			int[][] nextMatr = new int[size-1][size-1];
			for (int col = 0; col<size; col++){
				for (int row = 0; row<size-1; row++){
					if (col<i)nextMatr[row][col] = matrix[row+1][col];
					else if (col>i)nextMatr[row][col-1] = matrix[row+1][col];
				}
			}
			det+=(((i+1)%2)*2-1)*matrix[0][i]*sqDeterminant(nextMatr);
		}
		return det;
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			int bw = frame.getWidth()/4;
			if (clicked&&!holding) {
				for (Square s:squares) {
					if (Math.abs(mousePos.getX()-s.getX())<bw/2&&Math.abs(mousePos.getY()-s.getY())<bw/2) {
						s.setGrabbed(true);
						holding = true;
						//System.out.println("grabbed");
						posFrom = new Point(s.getX(), s.getY());
						break;
					}
				}
			}
			for (Square s:squares) {
				if (s.isGrabbed()) {
					s.setX((int)mousePos.getX());
					s.setY((int)mousePos.getY());
				}
			}
			if (!clicked&&!dragging){
				holding = false;
				for (Square s:squares) {
					if (s.isGrabbed()){
						s.setGrabbed(false);
						boolean validLoc = true;
						for (Square q:squares) {
							if (!s.equals(q)&&(Math.abs(s.getX()-q.getX())<bw/2)&&(Math.abs(s.getY()-q.getY())<bw/2)){
								validLoc = false;
							}
						}
						if (validLoc&&(Math.abs(s.getX()-posFrom.getX())<bw*3/2&&Math.abs(s.getY()-posFrom.getY())<bw*3/2)&&((Math.abs((s.getX()/bw*bw+bw/2)-posFrom.getX())==bw)^(Math.abs((s.getY()/bw*bw+bw/2)-posFrom.getY())==bw))&&s.getX()<bw*4&&s.getY()<bw*4){
							s.setX((s.getX()/bw*bw+bw/2));
							s.setY((s.getY()/bw*bw+bw/2));
						}
						else {
							s.setX((int)posFrom.getX());
							s.setY((int)posFrom.getY());
						}
					}
					
				}
			}
			if (solveMode){
				
			}
			won = true;
			for (Square s:squares) {
				if (!((int)((s.getX()+bw/2)/bw+s.getY()/bw*4)==s.getNumVal())||s.isGrabbed()) won = false;
			}
			if (won) System.out.println(won);
			
			dragging = false;
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int magnitude(int x, int y){
		return (x-y)/Math.abs(x-y);
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
		int blockWidth = frame.getWidth()/4;
		g.setFont(new Font("Dialog", Font.BOLD, 20));
		for (Square s:squares) {
			if (!s.isGrabbed()){
				g.setColor(new Color(255, 255, 140));
				g.fillRect(s.getX() - blockWidth/2, s.getY() - blockWidth/2, blockWidth, blockWidth);
				g.setColor(Color.BLACK);
				g.drawRect(s.getX() - blockWidth/2, s.getY() - blockWidth/2, blockWidth, blockWidth);
				g.drawString("" + s.getNumVal(), s.getX() - 10, s.getY());
			}
		}
		g.setFont(new Font("Dialog", Font.BOLD, 23));
		for (Square s:squares) {
			if (s.isGrabbed()) {
				g.setColor(new Color(0, 0, 0, 80));
				g.fillRect(s.getX() - blockWidth/2 - 5, s.getY() - blockWidth/2, blockWidth+20, blockWidth+20);
				g.setColor(new Color(255, 255, 140));
				g.fillRect(s.getX() - blockWidth/2 - 10, s.getY() - blockWidth/2 - 10, blockWidth+20, blockWidth+20);
				g.setColor(Color.BLACK);
				g.drawRect(s.getX() - blockWidth/2 - 10, s.getY() - blockWidth/2 - 10, blockWidth+20, blockWidth+20);
				g.drawString("" + s.getNumVal(), s.getX() - 9, s.getY());
			}
		}
		g.setColor(Color.RED);
		g.fillOval((int)mousePos.getX(), (int)mousePos.getY(), 4, 4);
		if (won)g.drawString("You won!", 50, 50);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
			solveMode^=true;
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
		if (!solveMode) {
			clicked = true;
			dragging = true;
			mousePos = arg0.getPoint();
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		clicked = false;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (!solveMode) {
			mousePos = arg0.getPoint();
			dragging = true;
		}
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
