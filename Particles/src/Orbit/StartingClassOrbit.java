package Orbit;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class StartingClassOrbit extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private ArrayList <Particle> particles = new ArrayList <Particle>();
	private double localAngleX;
	private double localAngleY;
	
	public void init() {
		setSize(800, 800);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("3D Orbit Particles");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		for (int i = 0; i<100; i++){
			particles.add(new Particle());
		}
		localAngleX = 0;
		localAngleY = 0;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			//localAngleX+=.01;
			//localAngleY+=.01;
			if (localAngleY>Math.PI*2)localAngleY = 0;
			
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
		for (int i = 0; i<particles.size(); i++){
			//int radius = (int)(Math.cos(particles.get(i).getAngleY()+localAngleY)*4)+6;
			int radius = 6;
			g.setColor(new Color(250-radius*25, 250-radius*25, 255));
			g.drawOval((int)(400+(Math.sin(particles.get(i).getAngleX()+localAngleX)*particles.get(i).getRadX()))-radius, (int)(400+(Math.sin(particles.get(i).getAngleY()+localAngleY)*particles.get(i).getRadY()))-radius, radius*2, radius*2);
		}
		g.setColor(Color.RED);
		g.fillOval(395, 395, 10, 10);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			localAngleY-=.05;
			break;
		case KeyEvent.VK_DOWN:
			localAngleY+=.05;
			break;
		case KeyEvent.VK_RIGHT:
			localAngleX-=.05;
			break;
		case KeyEvent.VK_LEFT:
			localAngleX+=.05;
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
