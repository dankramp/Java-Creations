

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StartingClass extends Applet implements Runnable, KeyListener, MouseListener  {

	private Image image;
	private Graphics graphics;
	private int[][] cells; 
	private boolean simulate;
	private boolean clicked;
	private String addCode;
	private String errorCode;
	private int lasti, lasta;
	private BufferedImage design;
	private int speed;
	
	public void init() {
		setSize(600, 630);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("The Game of Life");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		cells = new int[200][200]; //MAX: 600 x 600 -> This is resolution
		errorCode = "";
		lasti = 0;
		lasta = 0;
		
		for (int i = 0; i<cells.length; i++){
			for (int a = 0; a<cells[0].length; a++){
				if ((int)(Math.random()*4)==0)cells[i][a] = 1;
				else cells[i][a] = 0;
			}
		}
		
		addCode = "";
		simulate = false;
		try {
			design = ImageIO.read(new File("Puffer.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot read image file");
		}
		
		speed = 70;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			if (simulate){
				int[][] nextCells = new int[cells.length][cells[0].length];
				for (int i = 0; i<cells.length; i++){
					for (int a = 0; a<cells[0].length; a++){
						int liveCellCount = 0;
						for (int row = i-1; row<i+2; row++){
							for (int col = a-1; col<a+2; col++){
								if (row>=0&&row<cells.length&&col>=0&&col<cells[0].length&&cells[row][col]==1&&!(row==i&&col==a))liveCellCount++;
							}
						}
						if (cells[i][a]==1&&liveCellCount<2)nextCells[i][a] = 0;
						if (cells[i][a]==1&&liveCellCount>3)nextCells[i][a] = 0;
						if (cells[i][a]==1&&(liveCellCount==2||liveCellCount==3))nextCells[i][a] = 1;
						if (cells[i][a]==0&&liveCellCount==3)nextCells[i][a] = 1;
					}
				}
				cells = nextCells;
			}
			if (clicked) {
				try {
					cells[(int)(MouseInfo.getPointerInfo().getLocation().getX()-3)/(600/cells.length)][(int)(MouseInfo.getPointerInfo().getLocation().getY()-70)/(600/cells.length)] = 1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lasti = (int)(MouseInfo.getPointerInfo().getLocation().getX()-3)/(600/cells.length);
				lasta = (int)(MouseInfo.getPointerInfo().getLocation().getY()-70)/(600/cells.length);
			}
			
			
			repaint();
			try {
				Thread.sleep(speed);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String addShape(String code){
		boolean goodFormat = true;
		boolean placeable = true;
		String type = "";
		int xpos = 0;
		int ypos = 0;
		try {
			type = code.substring(0, code.indexOf(" "));
			code = code.substring(code.indexOf(" ")+1);
			xpos = Integer.parseInt(code.substring(0, code.indexOf(" ")));
			ypos = Integer.parseInt(code.substring(code.indexOf(" ")+1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			goodFormat = false;
		}
		if (goodFormat){
			if (type.equals("GOSPERGLIDERGUN")){
				if (xpos<cells.length-38&&xpos>=0&&ypos<cells[0].length-11&&ypos>=0){
					cells[xpos+1][ypos+5] = 1;
					cells[xpos+1][ypos+6] = 1;
					cells[xpos+2][ypos+5] = 1;
					cells[xpos+2][ypos+6] = 1;
					cells[xpos+11][ypos+5] = 1;
					cells[xpos+11][ypos+6] = 1;
					cells[xpos+11][ypos+7] = 1;
					cells[xpos+12][ypos+8] = 1;
					cells[xpos+12][ypos+4] = 1;
					cells[xpos+13][ypos+9] = 1;
					cells[xpos+13][ypos+3] = 1;
					cells[xpos+14][ypos+9] = 1;
					cells[xpos+14][ypos+3] = 1;
					cells[xpos+15][ypos+6] = 1;
					cells[xpos+16][ypos+4] = 1;
					cells[xpos+16][ypos+8] = 1;
					cells[xpos+17][ypos+7] = 1;
					cells[xpos+17][ypos+6] = 1;
					cells[xpos+18][ypos+6] = 1;
					cells[xpos+17][ypos+5] = 1;
					cells[xpos+21][ypos+5] = 1;
					cells[xpos+21][ypos+4] = 1;
					cells[xpos+21][ypos+3] = 1;
					cells[xpos+22][ypos+5] = 1;
					cells[xpos+22][ypos+4] = 1;
					cells[xpos+22][ypos+3] = 1;
					cells[xpos+23][ypos+2] = 1;
					cells[xpos+23][ypos+6] = 1;
					cells[xpos+25][ypos+2] = 1;
					cells[xpos+25][ypos+6] = 1;
					cells[xpos+25][ypos+1] = 1;
					cells[xpos+25][ypos+7] = 1;
					cells[xpos+35][ypos+3] = 1;
					cells[xpos+35][ypos+4] = 1;
					cells[xpos+36][ypos+3] = 1;
					cells[xpos+36][ypos+4] = 1;

					return "Gosper Glider Gun placed.";
				}
				else return "Invalid placement.";
				
			}
			if (type.equals("PULSAR")){
				if (xpos<cells.length-12&&xpos>=0&&ypos<cells[0].length-12&&ypos>=0){
					cells[xpos+0][ypos+2] = 1;
					cells[xpos+0][ypos+3] = 1;
					cells[xpos+0][ypos+4] = 1;
					cells[xpos+0][ypos+8] = 1;
					cells[xpos+0][ypos+9] = 1;
					cells[xpos+0][ypos+10] = 1;
					cells[xpos+2][ypos+0] = 1;
					cells[xpos+2][ypos+5] = 1;
					cells[xpos+2][ypos+7] = 1;
					cells[xpos+2][ypos+12] = 1;
					cells[xpos+3][ypos+0] = 1;
					cells[xpos+3][ypos+5] = 1;
					cells[xpos+3][ypos+7] = 1;
					cells[xpos+3][ypos+12] = 1;
					cells[xpos+4][ypos+0] = 1;
					cells[xpos+4][ypos+5] = 1;
					cells[xpos+4][ypos+7] = 1;
					cells[xpos+4][ypos+12] = 1;
					cells[xpos+5][ypos+2] = 1;
					cells[xpos+5][ypos+3] = 1;
					cells[xpos+5][ypos+4] = 1;
					cells[xpos+5][ypos+8] = 1;
					cells[xpos+5][ypos+9] = 1;
					cells[xpos+5][ypos+10] = 1;
					cells[xpos+7][ypos+2] = 1;
					cells[xpos+7][ypos+3] = 1;
					cells[xpos+7][ypos+4] = 1;
					cells[xpos+7][ypos+8] = 1;
					cells[xpos+7][ypos+9] = 1;
					cells[xpos+7][ypos+10] = 1;
					cells[xpos+8][ypos+0] = 1;
					cells[xpos+8][ypos+5] = 1;
					cells[xpos+8][ypos+7] = 1;
					cells[xpos+8][ypos+12] = 1;
					cells[xpos+9][ypos+0] = 1;
					cells[xpos+9][ypos+5] = 1;
					cells[xpos+9][ypos+7] = 1;
					cells[xpos+9][ypos+12] = 1;
					cells[xpos+10][ypos+0] = 1;
					cells[xpos+10][ypos+5] = 1;
					cells[xpos+10][ypos+7] = 1;
					cells[xpos+10][ypos+12] = 1;
					cells[xpos+12][ypos+2] = 1;
					cells[xpos+12][ypos+3] = 1;
					cells[xpos+12][ypos+4] = 1;
					cells[xpos+12][ypos+8] = 1;
					cells[xpos+12][ypos+9] = 1;
					cells[xpos+12][ypos+10] = 1;
					
					return "Pulsar placed.";
				}
				else return "Invalid placement.";
			}
			if (type.equals("PICTURE")){
				if (xpos<cells.length-design.getWidth()&&xpos>=0&&ypos<cells[0].length-design.getHeight()&&ypos>=0){
					int[] pixel;
					for (int y = 0; y < design.getHeight(); y++) {
				    	for (int x = 0; x < design.getWidth(); x++) {
				        	pixel = design.getRaster().getPixel(x, y, new int[3]);
				        	//System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2]);
				        	if (pixel[0]!=255||pixel[1]!=255||pixel[2]!=255){
				        		cells[xpos+x][ypos+y] = 1;
				        		//System.out.println("cells[xpos+"+(x)+"][ypos+"+(y)+"] = 1;");
				        	}
				    	}
					}
					return "Picture placed.";
				}
				else return "Invalid placement.";
			}
			else return "Invalid shape.";
		}
		else return "Invalid input format";
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
		g.setColor(Color.BLACK);
		for (int i = 0; i<cells.length; i++){
			g.setColor(Color.LIGHT_GRAY);
			if (cells.length<=200){
				g.drawLine(i*(600/cells.length), 0, i*(600/cells.length), 600);
				g.drawLine(0, (i)*(600/cells.length), 600, (i)*(600/cells.length));
			}
		}
		for (int i = 0; i<cells.length; i++){
			g.setColor(Color.BLACK);
			for (int a = 0; a<cells[0].length; a++){
				/*
				int liveCellCount = 0;
				for (int row = i-1; row<i+2; row++){
					for (int col = a-1; col<a+2; col++){
						if (row>=0&&row<100&&col>=0&&col<100&&cells[row][col]==1&&!(row==i&&col==a))liveCellCount++;
					}
				}
				if (liveCellCount == 0){
					g.setColor(Color.GREEN);
					for (int row = i-1; row<i+2; row++){
						for (int col = a-1; col<a+2; col++){
							g.fillRect((600/cells.length)*row, (600/cells[0].length)*col, (600/cells.length), (600/cells[0].length));
						}
					}
				}
				if (liveCellCount == 1)g.setColor(new Color(140, 140, 140));
				if (liveCellCount == 2)g.setColor(new Color(180, 180, 180));
				if (liveCellCount == 3)g.setColor(new Color(220, 220, 220));
				if (liveCellCount >= 4)g.setColor(new Color(235, 235, 235));
				*/
				if (cells[i][a]==1)g.fillRect((600/cells.length)*i, (600/cells[0].length)*a, (600/cells.length), (600/cells[0].length));
			}
		}
		g.drawLine(0, 600, 600, 600);
		g.setFont(new Font("Dialog", 20, 20));
		g.drawString(""+addCode+"|", 280, 620);
		g.setFont(new Font("Dialog", 20, 20));
		g.drawString(errorCode, 10, 620);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_F5:
			simulate^=true;
			break;
		case KeyEvent.VK_ESCAPE:
			for (int i = 0; i<cells.length; i++){
				for (int a = 0; a<cells[0].length; a++){
					cells[i][a] = 0;
				}
			}
			break;
		case KeyEvent.VK_A:
			addCode+="A";
			break;
		case KeyEvent.VK_B:
			addCode+="B";
			break;
		case KeyEvent.VK_C:
			addCode+="C";
			break;
		case KeyEvent.VK_D:
			addCode+="D";
			break;
		case KeyEvent.VK_E:
			addCode+="E";
			break;
		case KeyEvent.VK_F:
			addCode+="F";
			break;
		case KeyEvent.VK_G:
			addCode+="G";
			break;
		case KeyEvent.VK_H:
			addCode+="H";
			break;
		case KeyEvent.VK_I:
			addCode+="I";
			break;
		case KeyEvent.VK_J:
			addCode+="J";
			break;
		case KeyEvent.VK_K:
			addCode+="K";
			break;
		case KeyEvent.VK_L:
			addCode+="L";
			break;
		case KeyEvent.VK_M:
			addCode+="M";
			break;
		case KeyEvent.VK_N:
			addCode+="N";
			break;
		case KeyEvent.VK_O:
			addCode+="O";
			break;
		case KeyEvent.VK_P:
			addCode+="P";
			break;
		case KeyEvent.VK_Q:
			addCode+="Q";
			break;
		case KeyEvent.VK_R:
			addCode+="R";
			break;
		case KeyEvent.VK_S:
			addCode+="S";
			break;
		case KeyEvent.VK_T:
			addCode+="T";
			break;
		case KeyEvent.VK_U:
			addCode+="U";
			break;
		case KeyEvent.VK_V:
			addCode+="V";
			break;
		case KeyEvent.VK_W:
			addCode+="W";
			break;
		case KeyEvent.VK_X:
			addCode+="X";
			break;
		case KeyEvent.VK_Y:
			addCode+="Y";
			break;
		case KeyEvent.VK_Z:
			addCode+="Z";
			break;
		case KeyEvent.VK_0:
			addCode+="0";
			break;
		case KeyEvent.VK_1:
			addCode+="1";
			break;
		case KeyEvent.VK_2:
			addCode+="2";
			break;
		case KeyEvent.VK_3:
			addCode+="3";
			break;
		case KeyEvent.VK_4:
			addCode+="4";
			break;
		case KeyEvent.VK_5:
			addCode+="5";
			break;
		case KeyEvent.VK_6:
			addCode+="6";
			break;
		case KeyEvent.VK_7:
			addCode+="7";
			break;
		case KeyEvent.VK_8:
			addCode+="8";
			break;
		case KeyEvent.VK_9:
			addCode+="9";
			break;
		case KeyEvent.VK_SPACE:
			addCode+=" ";
			break;
		case KeyEvent.VK_BACK_SPACE:
			addCode = addCode.substring(0, addCode.length()-1);
			break;
		case KeyEvent.VK_ENTER:
			errorCode = addShape(addCode);
			addCode = "";
			break;
		case KeyEvent.VK_DELETE:
			cells[lasti][lasta] = 0;
			break;
		case KeyEvent.VK_SHIFT:
			int mini = 600;
			int mina = 600;
			for (int i = 0; i<cells.length; i++){
				for (int a = 0; a<cells[0].length; a++){
					if (cells[i][a] == 1&&i<mini)mini = i;
					if (cells[i][a]==1&&a<mina)mina = a;
				}
			}
			for (int i = 0; i<cells.length; i++){
				for (int a = 0; a<cells[0].length; a++){
					if (cells[i][a] == 1){
						System.out.println("cells[xpos+"+(i-mini)+"][ypos+"+(a-mina)+"] = 1;");
					}
				}
			}
			break;
		case KeyEvent.VK_EQUALS:
			speed=speed*4/5;
			break;
		case KeyEvent.VK_MINUS:
			speed=(speed+1)*5/4;
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
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		clicked = true;
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		clicked = false;
	}

}
