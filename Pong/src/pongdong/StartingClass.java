package pongdong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private Player player1, player2;
	private Ball ball;
	private int score1 = 0, score2 = 0;
	private boolean scored1 = false, scored2 = false;
	private boolean gameStarted = false;
	private static double angle;
	private double angle2;
	private Random random;
	
	public void init() {
		setSize(500, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Window Name");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		player1 = new Player(20);
		player2 = new Player(480);
		ball = new Ball();
		random = new Random();
		angle = Math.PI;//random.nextDouble()*Math.PI/2-Math.PI/4;

		
		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		/*if (score1>score2){		
			angle = random.nextDouble()*Math.PI/2-Math.PI/4+Math.PI;
		}
		else if (score1<score2){		
			angle = random.nextDouble()*Math.PI/2-Math.PI/4;
		}
		else {
			if (random.nextDouble()>.5)angle = random.nextDouble()*Math.PI/2-Math.PI/4;
			else angle = random.nextDouble()*Math.PI/2-Math.PI/4+Math.PI;
		}
		*/
		angle = 0;
		ball.reset();
		gameStarted = false;
		player1.reset();
		player2.reset();
		
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			player1.update();
			player2.update();
			ball.update();
			if (ball.getX()<250){
				angle2 = Math.atan((ball.getY()-player1.getY())/(ball.getX()-player1.getX()))/2;
			}
			else if (ball.getX()>250){
				angle2 = (Math.atan((ball.getY()-player2.getY())/(ball.getX()-player2.getX()))/2+Math.PI);
			}
			if (ball.getSpeedMult()>0)gameStarted = true;
			
			if (player1.getY()-player1.getHeight()/2-5<0)player1.setY(player1.getHeight()/2+4);		//BOUNDS PLAYERS
			if (player1.getY()+player1.getHeight()/2+5>500)player1.setY(496-player1.getHeight()/2);
			if (player2.getY()-player2.getHeight()/2-5<0)player2.setY(player2.getHeight()/2+4);
			if (player2.getY()+player2.getHeight()/2+5>500)player2.setY(496-player2.getHeight()/2);			
			
			if (ball.getY()<ball.getRadius()||ball.getY()>500-ball.getRadius())ball.setSpeedY(ball.getSpeedY()*-1);		//BOUNCES BALL OFF TOP AND BOTTOM
			
			if ((ball.getX()-ball.getRadius()<player1.getX()+player1.getWidth()/2&&Math.abs(ball.getY()-player1.getY())<player1.getHeight()/2)||(ball.getX()+ball.getRadius()>player2.getX()-player2.getWidth()/2&&Math.abs(ball.getY()-player2.getY())<player2.getHeight()/2)){
				angle = angle2;
				ball.setSpeedX(Math.cos(angle)*ball.getSpeedMult());
				ball.setSpeedY(Math.sin(angle)*ball.getSpeedMult());			
				}
	
			if (ball.getX()>500){
				scored1 = true;
				reset();
			}
			if (ball.getX()<0){
				scored2 = true;
				reset();
			}
			if (scored1){
				score1++;
				scored1 = false;
			}
			if (scored2){
				score2++;
				scored2 = false;
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
		graphics.setColor(Color.BLACK);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		g.setColor(Color.GREEN);		//PLAYERS
		g.fillRect(player1.getX()-player1.getWidth()/2, player1.getY()-(player1.getHeight()/2), player1.getWidth(), (player1.getHeight()));
		g.fillRect(player2.getX()-player2.getWidth()/2, player2.getY()-(player2.getHeight()/2), player2.getWidth(), (player2.getHeight()));

		g.setColor(Color.RED);		//BALL
		g.fillRect((int)(ball.getX()-ball.getRadius()), (int)(ball.getY()-ball.getRadius()), ball.getRadius()*2, ball.getRadius()*2);
		g.setColor(Color.WHITE);
		g.fillRect(247, 0, 6, 500);
		
		g.setColor(Color.WHITE); 	//TEXT
		g.setFont(new Font ("Dialog", 50, 40));
		g.drawString(""+score1, 200, 40);
		g.drawString(""+score2, 275, 40);

	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_W:
			player1.setMoveUp(true);
			break;
		case KeyEvent.VK_S:
			player1.setMoveDown(true);
			break;
		}
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			player2.setMoveUp(true);
			break;
		case KeyEvent.VK_DOWN:
			player2.setMoveDown(true);
			break;
		case KeyEvent.VK_ENTER:
			if (!gameStarted){
				ball.setSpeedMult(5);
				ball.setSpeedX(Math.cos(angle)*ball.getSpeedMult());
				ball.setSpeedY(Math.sin(angle)*ball.getSpeedMult());
			}
			break;
		}
	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_W:
			player1.setMoveUp(false);
		case KeyEvent.VK_S:
			player1.setMoveDown(false);
		}
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			player2.setMoveUp(false);
			break;
		case KeyEvent.VK_DOWN:
			player2.setMoveDown(false);
			break;
		}
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	

}
