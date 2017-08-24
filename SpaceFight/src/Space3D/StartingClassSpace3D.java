package Space3D;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class StartingClassSpace3D extends Applet implements Runnable, KeyListener, MouseListener {

	private Image image;
	private Graphics graphics;
	private Point target;
	private ArrayList <Lazer> lazers = new ArrayList <Lazer> ();
	private boolean fired;
	private Point targetChange;
	private ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	
	public void init() {
		setSize(1920, 1080);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Window Name");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			Random rand = new Random();
			if (rand.nextInt(80)==5)enemies.add(new Enemy());
			for (int i = 0; i<lazers.size(); i++){
				lazers.get(i).update();
			}
			for (int i = 0; i<enemies.size(); i++){
				enemies.get(i).update();
				if (enemies.get(i).getLife()>85){
					enemies.remove(i);
					break;
				}
				for (int a = 0; a<lazers.size(); a++){
					if (Math.abs(lazers.get(a).getX()-enemies.get(i).getX())<enemies.get(i).getSize()/2&&Math.abs(lazers.get(a).getY()-enemies.get(i).getY())<enemies.get(i).getSize()/2&&lazers.get(a).isKill()){
						enemies.remove(i);
						break;
					}
				}
			}
			
			for (int i = 0; i<lazers.size(); i++){
				if (lazers.get(i).isKill())lazers.remove(i);
			}
			target = MouseInfo.getPointerInfo().getLocation();
			target.setLocation(target.getX(), target.getY()-55);
			
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
		g.setColor(Color.RED);
		g.fillRect((int)target.getX()-4, (int)target.getY()-40, 8, 80);
		g.fillRect((int)target.getX()-40, (int)target.getY()-4, 80, 8);
		g.setColor(Color.BLACK);
		g.fillOval((int)target.getX()-30, (int)target.getY()-30, 60, 60);
		for (int i = 0; i<lazers.size(); i++){
			g.setColor(Color.GREEN);
			g.fillOval(lazers.get(i).getX()-lazers.get(i).getSize()/2, lazers.get(i).getY()-lazers.get(i).getSize()/2, lazers.get(i).getSize(), lazers.get(i).getSize());
		}
		for (int i = 0; i<enemies.size(); i++){
			g.setColor(new Color(255, 127, 0, (int)(enemies.get(i).getLife()*3)));
			g.fillRect(enemies.get(i).getX()-enemies.get(i).getSize()/2, enemies.get(i).getY()-enemies.get(i).getSize()/2, enemies.get(i).getSize(), enemies.get(i).getSize());
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
			lazers.add(new Lazer(true, target));
			lazers.add(new Lazer(false, target));
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
		if (target!=targetChange&&!fired){
			lazers.add(new Lazer(true, target));
			lazers.add(new Lazer(false, target));
		}
		targetChange = target;
		fired = true;
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
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		fired = false;
		
	}

}
