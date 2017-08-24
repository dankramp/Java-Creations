package CatchGame;

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
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class StartingClassCatch extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private ArrayList <Ball> balls;
	private int gameCount;
	private int mousePos;
	private int catchCount;
	private int totalBalls;
	
	private Image image;
	private Graphics graphics;
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Window Name");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		mousePos = 300;
		balls = new ArrayList<Ball>();
		gameCount = 0;
		catchCount = 0;
		totalBalls = 0;

		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset() {
		mousePos = 300;
		balls.clear();
		gameCount = 0;
		catchCount = 0;
		totalBalls = 0;
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			if (catchCount < 50) {
				gameCount++;
				if (gameCount % 10 == 0)
					balls.add(new Ball());

				
				for (int i = 0; i < balls.size(); i++) {
					balls.get(i).update();
					if (Math.abs(balls.get(i).getY() - 450) < 20 && Math.abs(balls.get(i).getX() - mousePos) < 30+balls.get(i).getRadius()) {
						balls.remove(i);
						catchCount++;
						totalBalls++;
					} else if (balls.get(i).getY() > 520) {
						balls.remove(i);
						totalBalls++;
					}
				}
			}
			
			
			repaint();
			try {
				Thread.sleep(20);
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
		graphics.setColor(Color.GRAY.darker());		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		
		//BALLS in my mouth
		for (Ball b:balls) {
			g.setColor(b.getColor());
			g.fillOval(b.getX()-b.getRadius(), b.getY()-b.getRadius(), 2*b.getRadius(), 2*b.getRadius());
		}
		
		//MOUSE PLATFORM
		g.setColor(Color.ORANGE);
		g.fillRect(mousePos-30, 440, 60, 20);
		g.setColor(Color.ORANGE.brighter());
		g.fillRect(mousePos-30+5, 441, 50, 8);
		
		//TEXT
		g.setColor(Color.BLACK);
		g.setFont(new Font("Dialog", 30, 30));
		g.drawString(""+catchCount, 560, 30);
		g.drawString(""+gameCount, 5, 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", 30, 30));
		g.drawString(""+catchCount, 560, 28);
		g.drawString(""+gameCount, 5, 28);
		
		if (totalBalls!=0) {
			g.setColor(Color.BLACK);
			g.drawString(""+catchCount*100/totalBalls+"%", 5, 60);
			g.setColor(Color.WHITE);
			g.drawString(""+catchCount*100/totalBalls+"%", 5, 58);
		}
		else { 
			g.setColor(Color.BLACK);
			g.drawString("100%", 5, 60);
			g.setColor(Color.WHITE);
			g.drawString("100%", 5, 58);
		}
		
		//FILL BAR
		g.setColor(Color.BLACK);
		g.fillRect(5, 475, 590, 20);
		g.setColor(Color.GRAY);
		g.fillRect(8, 478, 584, 14);
		g.setColor(Color.GREEN);
		g.fillRect(8, 478, catchCount*584/50, 14);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
			reset();
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
	@Override
	public void mouseDragged(MouseEvent arg0) {
		mousePos = (int)arg0.getX();
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
