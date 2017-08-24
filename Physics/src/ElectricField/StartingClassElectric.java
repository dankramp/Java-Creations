package ElectricField;

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
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class StartingClassElectric extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private Image image;
	private Graphics graphics;
	private Ball ball;
	private boolean play;
	private ArrayList <Charge> charges = new ArrayList <Charge> ();
	private ArrayList <Tracer> tracers = new ArrayList <Tracer> ();
	private int counter;
	private Point mousePos;
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Electric Field Hockey");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE

		ball = new Ball(50, 250);
		play = false;
		counter = 0;
		mousePos = new Point(0,0);
		
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			
			if (play){
				ball.update();
				counter++;
				if (counter%10 == 0)tracers.add(new Tracer((int)(ball.getX()), (int)(ball.getY())));
			}
			else {
				ball.setX(50);
				ball.setY(250);
				ball.setSpeedX(0);
				ball.setSpeedY(0);
				tracers.clear();
				counter = 0;
			}
			
			for (int i = 0; i<charges.size(); i++){
				double acc = 20*(charges.get(i).getCharge())/(distance(charges.get(i))*distance(charges.get(i)))/ball.getMass();
				//System.out.println("angle: "+angle(charges.get(i)));
				if (distance(charges.get(i))>3)ball.setSpeedX(ball.getSpeedX()+acc*Math.cos(Math.toRadians(angle(charges.get(i)))));
				//System.out.println("X: "+acc*Math.cos(Math.toRadians(angle(charges.get(i)))));
				if (distance(charges.get(i))>3)ball.setSpeedY(ball.getSpeedY()-acc*Math.sin(Math.toRadians(angle(charges.get(i)))));
				//System.out.println("Y: "+-acc*Math.sin(Math.toRadians(angle(charges.get(i)))));
			}
			if (ball.getSpeedX()>6)ball.setSpeedX(6);
			if (ball.getSpeedX()<-6)ball.setSpeedX(-6);
			if (ball.getSpeedY()>6)ball.setSpeedY(6);
			if (ball.getSpeedY()<-6)ball.setSpeedY(-6);
			
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public double distance(Charge c){
		return (Math.sqrt((ball.getY()-c.getY())*(ball.getY()-c.getY())+((ball.getX()-c.getX())*(ball.getX()-c.getX()))));
	}
	public double angle(Charge c){
		if (ball.getX()>c.getX())return (Math.atan(((double)(ball.getY()-c.getY()))/(double)(c.getX()-ball.getX()))*180/Math.PI+180);
		else if (ball.getX()<c.getX())return (Math.atan(((double)(ball.getY()-c.getY()))/(double)(c.getX()-ball.getX()))*180/Math.PI+360);
		else return 0;
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
		g.setColor(Color.RED);
		//g.fillOval((int)mousePos.getX(), (int)mousePos.getY(), 10, 10);
		g.setColor(Color.GRAY);
		for (Tracer t:tracers){
			g.fillOval(t.getX()-1, t.getY()-1, 2, 2);
		}
		for (int i = 0; i<charges.size(); i++){
			if (charges.get(i).getCharge()>0){
				g.setColor(new Color((int)(255-charges.get(i).getCharge()*51), (int)(255-charges.get(i).getCharge()*51), 255));
				g.fillOval(charges.get(i).getX()-10, charges.get(i).getY()-10, 20, 20);
				g.setColor(new Color(255, 255, (int)(255-charges.get(i).getCharge()*51)));
				g.fillRect((int)(charges.get(i).getX()-1), (int)(charges.get(i).getY()-7), 3, 15);
				g.fillRect((int)(charges.get(i).getX()-7), (int)(charges.get(i).getY()-1), 15, 3);
			}
			else if (charges.get(i).getCharge()<0){
				g.setColor(new Color(255, (int)(255+charges.get(i).getCharge()*51), (int)(255+charges.get(i).getCharge()*51)));
				g.fillOval(charges.get(i).getX()-10, charges.get(i).getY()-10, 20, 20);
				g.setColor(new Color(255, 255, (int)(255+charges.get(i).getCharge()*51)));
				g.fillRect((int)(charges.get(i).getX()-7), (int)(charges.get(i).getY()-1), 15, 3);
			}
			else {
				g.setColor(Color.LIGHT_GRAY);
				g.drawOval(charges.get(i).getX()-10, charges.get(i).getY()-10, 20, 20);
			}
		}
		g.setColor(Color.GREEN);
		g.fillOval((int)(ball.getX()-10), (int)(ball.getY()-10), 20, 20);
		g.setColor(Color.YELLOW);
		//g.fillRect(ball.getX()-1, ball.getY()-8, 3, 16);
		g.fillRect((int)(ball.getX()-7), (int)(ball.getY()-1), 16, 3);
		g.setColor(Color.BLACK);
		boolean display = false;
		for (int i = charges.size()-1; i>=0; i--){
			if (!display&&Math.abs((int)(mousePos.getX())-charges.get(i).getX())<10&&Math.abs((int)(mousePos.getY())-charges.get(i).getY())<10)
			{
				g.drawString("Charge: "+(int)(charges.get(i).getCharge()*10)/10.0+" C", 50, 470);
				display = true;
			}
		}
		if (play)g.drawString("Playing...", 400, 470);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			for (int i = charges.size()-1; i>=0; i--){
				if (Math.abs((int)(mousePos.getX())-charges.get(i).getX())<10&&Math.abs((int)(mousePos.getY())-charges.get(i).getY())<10&&(charges.get(i).getCharge()<5)){
					charges.get(i).setCharge(charges.get(i).getCharge()+.5);
					break;
				}
			}
			break;
		case KeyEvent.VK_DOWN:
			for (int i = charges.size()-1; i>=0; i--){
				if (Math.abs((int)(mousePos.getX())-charges.get(i).getX())<10&&Math.abs((int)(mousePos.getY())-charges.get(i).getY())<10&&(charges.get(i).getCharge()>-5)){
					charges.get(i).setCharge(charges.get(i).getCharge()-.5);
					break;
				}
			}
			break;
		case KeyEvent.VK_ENTER:
			play^=true;
			break;
		case KeyEvent.VK_ESCAPE:
			play^=true;
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
	public void mousePressed(MouseEvent e) {
		boolean deleteOne = false;
		for (Charge c:charges) {
			if (Math.sqrt((e.getX()-c.getX())*(e.getX()-c.getX())+(e.getY()-c.getY())*(e.getY()-c.getY()))<10) {
				deleteOne = true;
				charges.remove(c);
				break;
			}
		}
		if (!deleteOne) charges.add(new Charge((int) (e.getX()), (int) (e.getY()), 1));
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = e.getPoint();
		
	}

}
