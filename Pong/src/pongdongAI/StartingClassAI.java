package pongdongAI;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class StartingClassAI extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private Player AI, player;
	private Ball ball;
	private int score1 = 0, score2 = 0;
	private static boolean gameStarted = false;
	private static double angle;
	private double angle2;
	private Powers power;
	private String difficulty;
	private boolean hard = false;
	private double desiredY;
	private double collideY;
	private double bounceX;
	private boolean personScored;
	private Frame frame;
	
	public void init() {
		setSize(500, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		frame = (Frame) this.getParent().getParent();
		frame.setTitle("PONG vs AI");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		AI = new Player(20, 2);		//AI
		player = new Player(480, 3);		//PLAYER
		ball = new Ball();
		angle = 0;
		power = new Powers();
		difficulty = "EASY";
		collideY = 50;
		bounceX = 0;
		personScored = false;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		angle = 0;
		ball.reset();
		gameStarted = false;
		AI.reset(2);
		player.reset(3);
		power.reset();
		String who;
		if (personScored)who = "You";
		else who = "The enemy";
		JOptionPane.showMessageDialog(frame, who+ " scored a point!", "Point Scored!", 2);
		personScored = false;
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			AI.update();
			player.update();
			ball.update();
			//power.update();
			if (difficulty.equals("HARD")&&!hard){
				AI.setSpeedMult(AI.getSpeedMult()+.5);
				hard = true;
			}
			else if (difficulty.equals("EASY")&&hard){
				AI.setSpeedMult(AI.getSpeedMult()-.5);
				hard = false;
			}
			//BOUNCES BALL OFF PLAYER OR AI
			if ((ball.getX()-ball.getRadius()<AI.getX()+AI.getWidth()/2&&ball.getX()-ball.getRadius()>AI.getX()+AI.getWidth()/2-6&&Math.abs(ball.getY()-AI.getY())<AI.getHeight()/2+ball.getRadius())||(ball.getX()+ball.getRadius()>player.getX()-player.getWidth()/2&&ball.getX()+ball.getRadius()<player.getX()-player.getWidth()/2+6&&Math.abs(ball.getY()-player.getY())<player.getHeight()/2+ball.getRadius())){
				if (ball.getX()<250){
					angle2 = Math.atan((ball.getY()-AI.getY())/(ball.getX()-AI.getX()))/2;
				}
				else if (ball.getX()>250){
					angle2 = (Math.atan((ball.getY()-player.getY())/(ball.getX()-player.getX()))/2+Math.PI);
				}
				if (ball.getX()<250)ball.setX(AI.getX()+AI.getWidth()/2+ball.getRadius());
				else ball.setX(player.getX()-player.getWidth()/2-ball.getRadius());
				angle = angle2;
				ball.setSpeedX(Math.cos(angle)*ball.getSpeedMult());
				ball.setSpeedY(Math.sin(angle)*ball.getSpeedMult());			
			}
			//AI CONTROLS
			if (ball.getSpeedX()<0){
				//CALCULATES WHERE BALL WILL COLLIDE
				double timeToCollision = Math.abs(((ball.getX()-ball.getRadius())-(AI.getX()+AI.getWidth()/2))/ball.getSpeedX());
				collideY = timeToCollision*ball.getSpeedY()+ball.getY();
				if (collideY>500-ball.getRadius()){
					double timeToBounce = Math.abs(500-ball.getRadius()-ball.getY())/ball.getSpeedY();
					bounceX = timeToBounce*ball.getSpeedX()+ball.getX();
				}
				//System.out.println("Colliding at: "+collideY);
				if (player.getY()<300&&player.getY()>200){
					if (Math.abs(ball.getSpeedY())>1){
						if (AI.getY()-collideY>5){
							AI.setSpeedY(-AI.getSpeedMult());
						}
						else if (AI.getY()-ball.getY()<-5){
							AI.setSpeedY(AI.getSpeedMult());
						}
						else AI.setSpeedY(-.3);
					}
					else {
						if (AI.getY()-ball.getY()>AI.getHeight()/2-8){			
							AI.setSpeedY(-AI.getSpeedMult());
						}
						else if (AI.getY()-ball.getY()<8-AI.getHeight()/2){
							AI.setSpeedY(AI.getSpeedMult());
						}
						else AI.setSpeedY(.3);
					}
				}
				else {
					//AIMS AWAY FROM PLAYER
					if (player.getY()>=300){
						desiredY = (collideY-20)/(500-(AI.getX()+AI.getWidth()/2+ball.getRadius()))*(AI.getWidth()/2+ball.getRadius())+collideY+12;
						//System.out.println("Moving to: "+desiredY);
						if (AI.getY()>desiredY){
							if (AI.getY()-desiredY<AI.getSpeedMult())AI.setSpeedY(desiredY-AI.getY());
							else AI.setSpeedY(-AI.getSpeedMult());
						}
						else if (AI.getY()<desiredY){
							if (desiredY-AI.getY()<AI.getSpeedMult())AI.setSpeedY(desiredY-AI.getY());
							else AI.setSpeedY(AI.getSpeedMult());
						}
						else AI.setSpeedY(0);
					}
					else if (player.getY()<=200){
						desiredY = (collideY-480)/(500-AI.getX()-AI.getWidth()/2-ball.getRadius())*(AI.getWidth()/2+ball.getRadius())+collideY-12;
						//System.out.println("Moving to: "+desiredY);
						if (AI.getY()>desiredY){
							if (AI.getY()-desiredY<AI.getSpeedMult())AI.setSpeedY(AI.getY()-desiredY);
							else AI.setSpeedY(-AI.getSpeedMult());
						}
						else if (AI.getY()<desiredY){
							if (desiredY-AI.getY()<AI.getSpeedMult())AI.setSpeedY(desiredY-AI.getY());
							else AI.setSpeedY(AI.getSpeedMult());
						}
						else AI.setSpeedY(0);
					}
				}
			}
			//MOVE AI TO CENTER
			else if (ball.getSpeedX()>0){
				if (AI.getY()>252)AI.setSpeedY(-AI.getSpeedMult()/1.5);
				else if (AI.getY()<248)AI.setSpeedY(AI.getSpeedMult()/1.5);
				else AI.setSpeedY(0);
			}
			if (ball.getSpeedMult()>0)gameStarted = true;
			
			//BOUNDS PLAYER and AI
			if (AI.getY()-AI.getHeight()/2-5<0)AI.setY(AI.getHeight()/2+4);
			if (AI.getY()+AI.getHeight()/2+5>500)AI.setY(496-AI.getHeight()/2);
			if (player.getY()-player.getHeight()/2-5<0)player.setY(player.getHeight()/2+4);
			if (player.getY()+player.getHeight()/2+5>500)player.setY(496-player.getHeight()/2);			
			//BOUNCES BALL OFF TOP AND BOTTOM
			if (ball.getY()<ball.getRadius())ball.setSpeedY(Math.abs(ball.getSpeedY()));
			if (ball.getY()>500-ball.getRadius())ball.setSpeedY(Math.abs(ball.getSpeedY())*-1);
			//POINT SCORED
			if (ball.getX()>500){
				score1++;
				reset();
			}
			if (ball.getX()<0){
				score2++;
				personScored = true;
				reset();
			}
			//GETTING POWERS
			if (Math.abs(ball.getX()-power.getX())<25&&Math.abs(ball.getY()-power.getY())<25){
				if (ball.getSpeedX()>0&&difficulty.equals("HARD")){
					if (power.getPower().equals("widen")){
						AI.setHeight(140);
						power.reset();
					}
					else if (power.getPower().equals("fastball")){
						ball.setSpeedMult(7);
						AI.setSpeedMult(3.7);
						power.reset();
					}
				}
				if (ball.getSpeedX()<0){
					if (power.getPower().equals("widen")){
						power.reset();
						player.setHeight(140);
					}
					else if (power.getPower().equals("fastball")){
						ball.setSpeedMult(7);
						player.setSpeedMult(5);
						power.reset();
					}
				}
			}
			repaint();
			if (difficulty.equals("EASY")) {
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (difficulty.equals("HARD")){
				try {
					Thread.sleep(11);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		g.fillRect((int)(AI.getX()-AI.getWidth()/2), (int)AI.getY()-(AI.getHeight()/2), AI.getWidth(), (AI.getHeight()));
		g.fillRect((int)player.getX()-player.getWidth()/2, (int)player.getY()-(player.getHeight()/2), player.getWidth(), (player.getHeight()));

		g.setColor(Color.RED);		//BALL
		g.fillRect((int)(ball.getX()-ball.getRadius()), (int)(ball.getY()-ball.getRadius()), ball.getRadius()*2, ball.getRadius()*2);
		g.setColor(Color.WHITE);
		g.fillRect(247, 0, 6, 500);
		
		g.setColor(Color.WHITE); 	//TEXT
		g.setFont(new Font ("Dialog", 50, 40));
		g.drawString(""+score1, 200, 40);
		g.drawString(""+score2, 275, 40);
		g.drawString(""+difficulty, 10, 40);
		
		if (power.getPower().equals("widen")){
			g.setColor(Color.BLUE);
		}
		else if (power.getPower().equals("fastball")){
			g.setColor(Color.ORANGE);
		}
		g.fillRect(power.getX()-15, power.getY()-15, 30, 30);
		
		g.setColor(Color.WHITE);
		g.fillOval((int)bounceX-3, 497, 6, 6);
//		g.drawLine((int)ball.getX(), (int)ball.getY(), (int)(ball.getSpeedX()*500+ball.getX()), (int)(ball.getSpeedY()*500+ball.getY()));
//		g.fillOval((int)AI.getX()+AI.getWidth()/2-4, (int)collideY-4, 8, 8);
//		g.setColor(Color.YELLOW);
//		g.drawLine((int)AI.getX()+AI.getWidth()/2, (int)desiredY, 500, 20);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			player.setSpeedY(-player.getSpeedMult());
			break;
		case KeyEvent.VK_DOWN:
			player.setSpeedY(player.getSpeedMult());
			break;
		case KeyEvent.VK_ENTER:
			if (!gameStarted){
				ball.setSpeedMult(5);
				ball.setSpeedX(Math.cos(angle)*ball.getSpeedMult());
				ball.setSpeedY(Math.sin(angle)*ball.getSpeedMult());
			}
			break;
		case KeyEvent.VK_1:
			difficulty = "EASY";
			break;
		case KeyEvent.VK_2:
			difficulty = "HARD";
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
	}
	

	@Override
	
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			player.setSpeedY(0);
		case KeyEvent.VK_DOWN:
			player.setSpeedY(0);
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
	public static boolean isGameStarted() {
		return gameStarted;
	}
	
	
	

}
