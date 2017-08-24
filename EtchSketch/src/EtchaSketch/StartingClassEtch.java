package EtchaSketch;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class StartingClassEtch extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private Point point;
	private ArrayList <Line> line = new ArrayList <Line> ();
	private double leftAngle, rightAngle;
	private int shake;
	private boolean shaking;
	private double counter;
	
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Airators by Sketcher");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		point = new Point();
		leftAngle = 0;
		rightAngle = 0;
		shake = 0;
		shaking = false;
		counter = 0;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			point.update();
			if (point.isMoveRight()||point.isMoveDown()||point.isMoveLeft()||point.isMoveUp())line.add(new Line(point.getX(), point.getY()));
			
			if (point.isMoveDown())rightAngle-=.03;
			else if (point.isMoveUp())rightAngle+=.03;
			if (point.isMoveLeft())leftAngle-=.03;
			else if (point.isMoveRight())leftAngle+=.03;
			
			//REMOVE POINTS
			for (int i = 0; i<line.size(); i++){
				if (line.get(i).getTrans()>210)line.remove(i);
			}
			
			//SHAKES
			if (shaking){
				counter+=.5;
				shake = (int)((90-counter*2.5)*Math.sin(counter));
				if (counter>10*Math.PI){
					shaking = false;
					shake = 0;
					counter = 0;
				}
				for (int i = 0; i<line.size(); i++){
					line.get(i).setTrans((int)(130+counter/10/Math.PI*100));
				}
			}
			
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
		graphics.setColor(new Color(240, 240, 240));		//BACKGROUND COLOR
		//graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		//BOARD
		g.setColor(Color.RED);
		g.fillRect(0, shake, 600, 500);
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, shake, 50, 50);
		g.fillRect(550, shake, 50, 50);
		g.fillRect(0, 450+shake, 50, 50);
		g.fillRect(550, 450+shake, 50, 50);
		g.setColor(Color.RED);
		g.fillOval(0, shake, 100, 100);
		g.fillOval(500, shake, 100, 100);
		g.fillOval(500, 400+shake, 100, 100);
		g.fillOval(0, 400+shake, 100, 100);
		//SCREEN
		g.setColor(new Color (220, 220, 220));
		g.fillRect(50, 50+shake, 500, 300);
		g.setColor(Color.RED);	
		g.fillRect(50, 50+shake, 20, 20);
		g.fillRect(50, 330+shake, 20, 20);
		g.fillRect(530, 50+shake, 20, 20);
		g.fillRect(530, 330+shake, 20, 20);
		g.setColor(new Color (220, 220, 220));
		g.fillOval(50, 50+shake, 40, 40);
		g.fillOval(50, 310+shake, 40, 40);
		g.fillOval(510, 50+shake, 40, 40);
		g.fillOval(510, 310+shake, 40, 40);
		g.setColor(new Color (190, 190, 190));
		g.fillRect(65, 50+shake, 470, 4);
		//LINES
		for (int i = 0; i<line.size(); i++){
			g.setColor(new Color(line.get(i).getTrans(), line.get(i).getTrans(), line.get(i).getTrans()));
			g.fillOval(line.get(i).getX()-2, line.get(i).getY()-2+shake, 5, 5);
		}
		//POINT
		g.setColor(new Color(180, 180, 180));
		g.fillOval(point.getX()-2, point.getY()-2+shake, 5, 5);
		//WHEEL SHADOWS
		g.setColor(new Color(190, 0, 0));
		g.fillRect(100, 420+shake, 101, 20);
		g.fillRect(400, 420+shake, 101, 20);
		g.fillOval(100, 390+shake, 100, 100);
		g.fillOval(400, 390+shake, 100, 100);
		//WHEELS
		g.setColor(new Color(230, 230, 230));
		g.fillOval(100, 370+shake, 100, 100);
		g.fillOval(400, 370+shake, 100, 100);
		g.setColor(new Color(250, 250, 250));
		g.fillOval(101, 371+shake, 98, 98);
		g.fillOval(401, 371+shake, 98, 98);
		g.setColor(new Color(230, 230, 230));
		g.fillOval(102, 373+shake, 96, 96);
		g.fillOval(402, 373+shake, 96, 96);

		g.setColor(new Color (110, 110, 110));
		for (int i = 0; i<16; i++){
			g.drawLine(450, 420+shake, 450+(int)(49*Math.cos(rightAngle+i*Math.PI/8)), 420+(int)(49*Math.sin(rightAngle+i*Math.PI/8))+shake);
			g.drawLine(150, 420+shake, 150+(int)(49*Math.cos(leftAngle+i*Math.PI/8)), 420+(int)(49*Math.sin(leftAngle+i*Math.PI/8))+shake);
		}
		g.setColor(new Color(230, 230, 230));
		g.fillOval(105, 375+shake, 90, 90);
		g.fillOval(405, 375+shake, 90, 90);
		g.setColor(new Color(245, 245, 245));
		g.fillOval(110, 380+shake, 80, 80);
		g.fillOval(410, 380+shake, 80, 80);

		//TEXT
		g.setColor(new Color(190, 0, 0));
		g.setFont(new Font("Dialog", 20, 20));
		g.drawString("Sketch-'N'-Etch", 232, 412+shake);
		g.setFont(new Font("Dialog", 15, 15));
		g.drawString("by Dan Kramp", 255, 432+shake);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", 20, 20));
		g.drawString("Sketch-'N'-Etch", 232, 410+shake);
		g.setFont(new Font("Dialog", 15, 15));
		g.drawString("by Dan Kramp", 255, 430+shake);
		//HIGHLIGHTS
		g.setColor(new Color (255, 240, 240));
		g.fillRect(50, 6+shake, 500, 5);
		g.fillRect(60, 354+shake, 480, 2);
		g.setColor(Color.RED);
		g.fillOval(40, 7+shake, 520, 5);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			point.setMoveRight(true);
			break;
		case KeyEvent.VK_LEFT:
			point.setMoveLeft(true);
			break;
		case KeyEvent.VK_UP:
			point.setMoveUp(true);
			break;
		case KeyEvent.VK_DOWN:
			point.setMoveDown(true);
			break;
		case KeyEvent.VK_SPACE:
			shaking = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			point.setMoveRight(false);
			break;
		case KeyEvent.VK_LEFT:
			point.setMoveLeft(false);
			break;
		case KeyEvent.VK_UP:
			point.setMoveUp(false);
			break;
		case KeyEvent.VK_DOWN:
			point.setMoveDown(false);
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
