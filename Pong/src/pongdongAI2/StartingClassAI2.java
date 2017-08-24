package pongdongAI2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class StartingClassAI2 extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private Player AI, AI2;
	private Ball ball;
	private int score1 = 0, score2 = 0;
	private boolean scored1 = false, scored2 = false;
	private static boolean gameStarted = false;
	private static double angle;
	private double angle2, angle3;
	private Random random;
	private Powers power;
	private int colorCounter = 0;
	private AudioClip wallHit;
	private AudioClip playerHit;
	private AudioClip powerGet;
	private AudioClip point;
	
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
		AI = new Player(20, 3.0);		//AI
		AI2 = new Player(480, 3.0);		//AI2
		ball = new Ball();
		random = new Random();
		angle = random.nextDouble()*Math.PI/2-Math.PI/4;
		power = new Powers();
		wallHit = getAudioClip(getDocumentBase(),"Wall Hit.wav");
		playerHit = getAudioClip(getDocumentBase(),"Player Hit.wav");
		powerGet = getAudioClip(getDocumentBase(),"Power Get.wav");
		point = getAudioClip(getDocumentBase(),"Point.wav");

		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		angle = random.nextDouble()*Math.PI/2-Math.PI/4;

		ball.reset();
		gameStarted = false;
		AI.reset(3);
		AI2.reset(3);
		power.reset();
		
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			//colorCounter++;
			AI.update();
			AI2.update();
			ball.update();
			power.update();
			if (power.isIn()){
				angle3 = Math.atan((power.getY()-AI.getY())/(power.getX()-AI.getX()))/2;
			}
			if (ball.getSpeedX() < 0) {
				if (AI2.getY() < 300 && AI2.getY() > 200) {
					if (Math.abs(ball.getSpeedY()) > 1) {
						if (AI.getY() - ball.getY() > 5) { // AI CONTROLLER
							AI.setSpeedY(-AI.getSpeedMult());
						} else if (AI.getY() - ball.getY() < -5) {
							AI.setSpeedY(AI.getSpeedMult());
						} else
							AI.setSpeedY(0);
					}
					else {
						if (AI.getY() - ball.getY() > AI.getHeight() / 2 - 8) {
							AI.setSpeedY(-AI.getSpeedMult());
						} else if (AI.getY() - ball.getY() < 8 - AI
								.getHeight() / 2) {
							AI.setSpeedY(AI.getSpeedMult());
						} else
							AI.setSpeedY(0);
					}
				} 
				else if (AI2.getY() > 300) {
						if (AI.getY() - AI.getHeight() / 2 + 5 < ball.getY())AI.setSpeedY(AI.getSpeedMult());
						else if (AI.getY() - AI.getHeight() / 2 + 5 > ball.getY())AI.setSpeedY(-AI.getSpeedMult());						
						else {
							AI.setSpeedY(0);
							System.out.println("Workin");
						}
				}
				else if (AI2.getY() < 200) {
					if (AI.getY() + AI.getHeight() / 2 - 5 < ball.getY())AI.setSpeedY(AI.getSpeedMult());
					else if (AI.getY() + AI.getHeight() / 2 - 5 > ball.getY())AI.setSpeedY(-AI.getSpeedMult());
					else{
						AI.setSpeedY(0);
						System.out.println("Workin");
					}
				}
				if (AI2.getY() > 250)
					AI2.setSpeedY(-AI2.getSpeedMult() / 2.5); // AI2 RETURN TO
																// CENTER
				else if (AI2.getY() < 250)
					AI2.setSpeedY(AI2.getSpeedMult() / 2.5);
				else
					AI2.setSpeedY(0);
			}
		
			else if (ball.getSpeedX() > 0) {
				if (AI.getY() < 300 && AI.getY() > 200) {
					if (Math.abs(ball.getSpeedY()) > 1) {
						if (AI2.getY() - ball.getY() > 5) { // AI2 CONTROLLER
							AI2.setSpeedY(-AI2.getSpeedMult());
						} else if (AI2.getY() - ball.getY() < -5) {
							AI2.setSpeedY(AI2.getSpeedMult());
						} else
							AI2.setSpeedY(0);
					} else {
						if (AI2.getY() - ball.getY() > AI2.getHeight() / 2 - 8) {
							AI2.setSpeedY(-AI2.getSpeedMult());
						} else if (AI2.getY() - ball.getY() < 8 - AI2
								.getHeight() / 2) {
							AI2.setSpeedY(AI2.getSpeedMult());
						} else
							AI2.setSpeedY(0);
					}
				}
				else if (AI.getY() > 300) {
					if (AI2.getY() - AI2.getHeight() / 2 + 5 < ball.getY())AI2.setSpeedY(AI2.getSpeedMult());
					else if (AI2.getY() - AI2.getHeight() / 2 + 5 > ball.getY())AI2.setSpeedY(-AI2.getSpeedMult());						
					else {
						AI2.setSpeedY(0);
						System.out.println("Workin");
					}
			}
			else if (AI.getY() < 200) {
				if (AI2.getY() + AI2.getHeight() / 2 - 5 < ball.getY())AI2.setSpeedY(AI2.getSpeedMult());
				else if (AI2.getY() + AI2.getHeight() / 2 - 5 > ball.getY())AI2.setSpeedY(-AI2.getSpeedMult());
				else{
					AI2.setSpeedY(0);
					System.out.println("Workin");
				}
			}
				if (AI.getY()>250)AI.setSpeedY(-AI.getSpeedMult()/2);		//AI RETURN TO CENTER
				else if (AI.getY()<250)AI.setSpeedY(AI.getSpeedMult()/2);
				else AI.setSpeedY(0);
			}
			if (ball.getX()<250){
				angle2 = Math.atan((ball.getY()-AI.getY())/(ball.getX()-AI.getX()))/2;
			}
			else if (ball.getX()>250){
				angle2 = (Math.atan((ball.getY()-AI2.getY())/(ball.getX()-AI2.getX()))/2+Math.PI);
			}
			if (ball.getSpeedMult()>0)gameStarted = true;
			
			if (AI.getY()-AI.getHeight()/2-5<0)AI.setY(AI.getHeight()/2+4);		//BOUNDS PLAYERS
			if (AI.getY()+AI.getHeight()/2+5>500)AI.setY(496-AI.getHeight()/2);
			if (AI2.getY()-AI2.getHeight()/2-5<0)AI2.setY(AI2.getHeight()/2+4);
			if (AI2.getY()+AI2.getHeight()/2+5>500)AI2.setY(496-AI2.getHeight()/2);			
			
			if (ball.getY()<ball.getRadius()){
				ball.setSpeedY(Math.abs(ball.getSpeedY()));
				wallHit.play();
			}
			if (ball.getY()>500-ball.getRadius()){
				ball.setSpeedY(Math.abs(ball.getSpeedY())*-1);		//BOUNCES BALL OFF TOP AND BOTTOM
				wallHit.play();
			}
			
			if ((ball.getX()-ball.getRadius()<AI.getX()+AI.getWidth()/2&&Math.abs(ball.getY()-AI.getY())<AI.getHeight()/2+ball.getRadius())||(ball.getX()+ball.getRadius()>AI2.getX()-AI2.getWidth()/2&&Math.abs(ball.getY()-AI2.getY())<AI2.getHeight()/2+ball.getRadius())){
				angle = angle2;
				ball.setSpeedX(Math.cos(angle)*ball.getSpeedMult());
				ball.setSpeedY(Math.sin(angle)*ball.getSpeedMult());	
				playerHit.play();
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
				point.play();
				scored1 = false;
			}
			if (scored2){
				score2++;
				point.play();
				scored2 = false;
			}
			
			if (Math.abs(ball.getX()-power.getX())<25&&Math.abs(ball.getY()-power.getY())<25){	//GETTING POWERS
				powerGet.play();
				if (ball.getSpeedX()>0){
					if (power.getPower().equals("widen")){
						AI.setHeight(125);
						power.reset();
					}
					else if (power.getPower().equals("fastball")){
						ball.setSpeedMult(7);
						AI.setSpeedMult(3.8);
						power.reset();
					}
				}
				if (ball.getSpeedX()<0){
					if (power.getPower().equals("widen")){
						power.reset();
						AI2.setHeight(125);
					}
					else if (power.getPower().equals("fastball")){
						ball.setSpeedMult(7);
						AI2.setSpeedMult(3.8);
						power.reset();
					}
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
		graphics.setColor(Color.BLACK);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		g.setColor(Color.GREEN);		//PLAYERS
		g.fillRect(AI.getX()-AI.getWidth()/2, AI.getY()-(AI.getHeight()/2), AI.getWidth(), (AI.getHeight()));
		g.fillRect(AI2.getX()-AI2.getWidth()/2, AI2.getY()-(AI2.getHeight()/2), AI2.getWidth(), (AI2.getHeight()));

		if (colorCounter == 0)g.setColor(Color.RED);
		if (colorCounter == 1)g.setColor(Color.GREEN);
		if (colorCounter == 2)g.setColor(Color.YELLOW);
		if (colorCounter == 3)g.setColor(Color.BLUE);
		if (colorCounter == 4){
			g.setColor(Color.ORANGE);
			colorCounter = 0;
		}
		//BALL
		g.fillRect((int)(ball.getX()-ball.getRadius()), (int)(ball.getY()-ball.getRadius()), ball.getRadius()*2, ball.getRadius()*2);
		g.setColor(Color.WHITE);
		g.fillRect(247, 0, 6, 500);
		
		g.setColor(Color.WHITE); 	//TEXT
		g.setFont(new Font ("Dialog", 50, 40));
		g.drawString(""+score1, 200, 40);
		g.drawString(""+score2, 275, 40);
		
		if (power.getPower().equals("widen")){
			g.setColor(Color.BLUE);
		}
		else if (power.getPower().equals("fastball")){
			g.setColor(Color.ORANGE);
		}
		g.fillRect((int)(power.getX()-15), (int)(power.getY()-15), 30, 30);

	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_ENTER:
			//if (!gameStarted){
				reset();
				ball.setSpeedMult(5);
				ball.setSpeedX(Math.cos(angle)*ball.getSpeedMult());
				ball.setSpeedY(Math.sin(angle)*ball.getSpeedMult());
			//}
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
	}
	

	@Override
	
	public void keyReleased(KeyEvent arg0) {
		
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
