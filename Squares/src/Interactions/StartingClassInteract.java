package Interactions;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class StartingClassInteract extends Applet implements Runnable, KeyListener, MouseListener {

	private ArrayList <Ball> balls = new ArrayList <Ball>();
	private ArrayList <Box> boxes = new ArrayList <Box>();
	boolean canEmit;
	private Image image;
	private Graphics graphics;
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Interactions Test");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		//balls.add(new Ball());
		//boxes.add(new Box(200, ))
		canEmit = true;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			//System.out.println(System.nanoTime());
			if ((System.nanoTime()/1000000000)%5==1&&canEmit){
				balls.add(new Ball());
				canEmit = false;
			}
			if ((System.nanoTime()/1000000000)%5==0)canEmit = true;
			
			for (int i = 0; i<balls.size(); i++){
				balls.get(i).update();
				if (balls.get(i).getY()>600)balls.remove(i);
			}
			for (Ball b:balls){
				if (Math.sqrt((b.getX()-300)*(b.getX()-300)+(b.getY()-400)*(b.getY()-400))<120){
					double angle = Math.atan2(500-b.getY()-400, b.getX()-300);
					double speedMag = Math.sqrt(b.getSpeedX()*b.getSpeedX()+b.getSpeedY()*b.getSpeedY())*.8;
					b.setSpeedX(speedMag*Math.cos(angle));
					b.setSpeedY(speedMag*Math.sin(angle));
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
		graphics.setColor(Color.WHITE);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		//BALLS
		for (Ball b:balls){
			g.setColor(new Color(255, 230, 220));
			g.fillOval(b.getX()-20, b.getY()-20, 40, 40);
		}
		//Big Ball
		g.setColor(new Color(210, 210, 255));
		g.fillOval(200, 300, 200, 200);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
			//What happens when space is pressed
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

}
