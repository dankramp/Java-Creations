package RocketFlyer;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StartingClassRocket extends Applet implements Runnable, KeyListener {

	private Frame frame;
	private Image image;
	private Graphics graphics;
	private Square square;
	private ArrayList <Smoke> smoke = new ArrayList <Smoke>();
	private ArrayList <Lazer> lazers = new ArrayList <Lazer>();
	private ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	private ArrayList <Particles> particles = new ArrayList <Particles>();
	private ArrayList <Power> powers = new ArrayList <Power>();
	private int health;
	private int health2;
	private double gameCounter;
	private boolean gameStarted;
	private boolean ram;
	private int ramStartCount;
	private int highScore = 0;
	private FileWriter writer;
	
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		frame = (Frame) this.getParent().getParent();
		frame.setTitle("Rocket Square");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		health = 550;
		health2 = 550;
		square = new Square();
		gameCounter = 0;
		gameStarted = false;
		ram = false;
		ramStartCount = -50;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run(){		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			//HIGH SCORE CHECKER
			File high = new File("RocketFlyer/High Score.txt");
			try {
				Scanner h = new Scanner(high);
				String score = h.nextLine();
				highScore = Integer.parseInt(score);
			} catch (FileNotFoundException e1) {
				System.out.println("There is no high score sheet.");
			}
			
			//ENEMIES
			for (int i = 0; i<particles.size(); i++){
				particles.get(i).update();
				if (particles.get(i).getLife()>24){
					particles.remove(i);
				}
			}
			for (int i = 0; i<powers.size(); i++){
				powers.get(i).update();
				if (Math.abs(powers.get(i).getX()-square.getX())<23&&Math.abs(powers.get(i).getY()-square.getY())<23){
					for (int b = 0; b<30; b++){
						particles.add(new Particles(powers.get(i).getX(), powers.get(i).getY()));
					}
					if (powers.get(i).getType().equals("health")&&health<500)health+=100;
					else if (powers.get(i).getType().equals("health")&&health==500)health+=50;
					else if (powers.get(i).getType().equals("ram")){
						ram = true;
						ramStartCount = (int)gameCounter;
					}
					powers.remove(i);
					break;
				}
				if (powers.get(i).getY()>520){
					powers.remove(i);
					break;
				}
				for (int a = 0; a<lazers.size(); a++){
					if (Math.abs(lazers.get(a).getX()-powers.get(i).getX())<18&&Math.abs(lazers.get(a).getY()-powers.get(i).getY())<13){
						for (int b = 0; b<30; b++){
							particles.add(new Particles(powers.get(i).getX(), powers.get(i).getY()));
						}
						if (powers.get(i).getType().equals("health")&&health<500)health+=100;
						else if (powers.get(i).getType().equals("health")&&health==500)health+=50;
						else if (powers.get(i).getType().equals("ram")){
							ram = true;
							ramStartCount = (int)gameCounter;
						}
						powers.remove(i);
						lazers.remove(a);
						i = 100000;
						break;
					}
				}
				
			}
			Random rand = new Random();
			if (rand.nextInt((int)(80-gameCounter/25))==5&&gameStarted)enemies.add(new Enemy());
			if (rand.nextInt(1000)==5&&gameStarted)powers.add(new Power());
			for (int i = 0; i<enemies.size(); i++){
				enemies.get(i).update();
				if (enemies.get(i).getX()-enemies.get(i).getSizeX()/2+5<0){
					for (int a = 0; a<50; a++){
						particles.add(new Particles(enemies.get(i).getX(), enemies.get(i).getY()));
					}
					health-=enemies.get(i).getSizeX()*enemies.remove(i).getSizeY()/50;
					break;
				}
				if (Math.abs(enemies.get(i).getX()-square.getX())<enemies.get(i).getSizeX()/2+10&&Math.abs(enemies.get(i).getY()-square.getY())<enemies.get(i).getSizeY()/2+10){
					for (int a = 0; a<50; a++){
						particles.add(new Particles(enemies.get(i).getX(), enemies.get(i).getY()));
					}
					if (!ram)health-=health-=enemies.get(i).getSizeX()*enemies.remove(i).getSizeY()/50;;
					break;
				}
				for (int a = 0; a<lazers.size(); a++){
					if (Math.abs(lazers.get(a).getX()-enemies.get(i).getX())<5+enemies.get(i).getSizeX()/2&&Math.abs(lazers.get(a).getY()-enemies.get(i).getY())<enemies.get(i).getSizeY()/2){
						for (int b = 0; b<50; b++){
							particles.add(new Particles(enemies.get(i).getX(), enemies.get(i).getY()));
						}
						enemies.remove(i);
						lazers.remove(a);
						i = 100000;
						break;
					}
				}
			}
			//SQUARE
			square.update();
			if (ram&&ramStartCount+35<gameCounter){
				ramStartCount = -50;
				ram = false;
			}
			//SMOKE
			if (square.isBoost())smoke.add(new Smoke(square.getX(), square.getY(), square.getAngle()));
			for (int i = 0; i<smoke.size(); i++){
				smoke.get(i).update();
				if (smoke.get(i).getLife()>50)smoke.remove(i);
			}
			//LAZERS
			for (int i = 0; i<lazers.size(); i++){
				lazers.get(i).update();
				if (lazers.get(i).getX()>600)lazers.remove(i);
			}
			//GAME
			if (health>0&&gameStarted)gameCounter+=.08;
			else if (gameStarted){
				square.setDead(true);
				particles.add(new Particles(square.getX(), square.getY()));
				particles.add(new Particles(square.getX(), square.getY()));
			}
			if (health<0)health = 0;
			
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
		
		//LAZERS
		for (int i = 0; i<lazers.size(); i++){
			g.setColor(Color.RED);
			g.drawLine(lazers.get(i).getX()-5, lazers.get(i).getY(), lazers.get(i).getX()+5, lazers.get(i).getY());
		}
		//SMOKE
		for (int i = 0; i<smoke.size(); i++){
			if (smoke.get(i).getLife()>=5)g.setColor(new Color(205+smoke.get(i).getLife(), 205+smoke.get(i).getLife(), 205+smoke.get(i).getLife()));
			else if (smoke.get(i).getLife()>1)g.setColor(new Color(255-smoke.get(i).getLife()*9, 128+smoke.get(i).getLife()*18, smoke.get(i).getLife()*51));
			else g.setColor(new Color(255, 128, 0));
			g.fillOval(smoke.get(i).getX()-5-smoke.get(i).getLife()/10-smoke.get(i).getRandomSize()/2, smoke.get(i).getY()-5-smoke.get(i).getLife()/10-smoke.get(i).getRandomSize()/2, 10+smoke.get(i).getLife()/5+smoke.get(i).getRandomSize(), 10+smoke.get(i).getLife()/5+smoke.get(i).getRandomSize());
		}
		//SQUARE
		if (!ram)g.setColor(Color.GRAY);
		else g.setColor((new Color((int)(Math.random()*105+130),(int)(Math.random()*105+130),(int)(Math.random()*105+130))));
		Graphics2D gg = (Graphics2D) g.create();
		gg.rotate(square.getAngle(), square.getX()-10, square.getY()-10);
		gg.fillRect(square.getX()-10, square.getY()-10, 20, 20);
		gg.dispose();
		//ENEMIES
		for (int i = 0; i<enemies.size(); i++){
			g.setColor(enemies.get(i).getColor());
			g.fillRect(enemies.get(i).getX()-enemies.get(i).getSizeX()/2, enemies.get(i).getY()-enemies.get(i).getSizeY()/2, enemies.get(i).getSizeX(), enemies.get(i).getSizeY());
		}
		//POWERS
		for (int i = 0; i<powers.size(); i++){
			if (powers.get(i).getType().equals("ram")){
				g.setColor((new Color((int)(Math.random()*105+130),(int)(Math.random()*105+130),(int)(Math.random()*105+130))));
				g.fillRect(powers.get(i).getX()-13, powers.get(i).getY()-13, 26, 26);
			}
			else {
				g.setColor(Color.RED);
				g.fillRect(powers.get(i).getX()-13, powers.get(i).getY()-13, 26, 26);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Dialog", 22, 22));
				g.drawString("H", powers.get(i).getX()-8, powers.get(i).getY()+8);
			}
		}
		//PARTICLES
		for (int i = 0; i<particles.size(); i++){
			g.setColor(Color.RED);
			g.fillOval(particles.get(i).getX()-2, particles.get(i).getY()-2, 4-particles.get(i).getLife()/6, 4-particles.get(i).getLife()/6);
		}
		//HEALTH BAR
		g.setColor(Color.BLACK);
		g.fillRect(16, 10, 554, 20);
		g.setColor(Color.WHITE);
		g.fillRect(18, 11, 550, 18);
		if (health>300)g.setColor(Color.GREEN);
		else if (health<=300&&health>100)g.setColor(new Color(255, 215, 0));
		else g.setColor(Color.RED);
		g.fillRect(18, 11, health, 18);
		//DAMAGE
		if (health2>health){
			g.setColor(Color.RED);
			g.fillRect(0, 0, 600, 500);
		}
		else if (health>health2){
			g.setColor(new Color (0, 255, 255));
			g.fillRect(18, 11, health, 18);
		}
		health2 = health;
		//TEXT
		g.setColor(Color.BLACK);
		g.setFont(new Font("Dialog", 15, 15));
		g.drawString(""+(int)gameCounter, 575, 25);
		g.drawString("High score: "+highScore, 490, 50);
		if (square.isDead()){
			g.setFont(new Font("Dialog", 100, 100));
			g.drawString("YOU DEAD", 45, 275);
			g.setFont(new Font("Dialog", 40, 40));
			g.drawString("Your final score was "+(int)gameCounter, 100, 315);
		}
		if (!gameStarted){
			g.setFont(new Font("Dialog", 30, 30));
			g.drawString("Press SPACE to begin", 150, 480);
		}
		if (gameStarted&&gameCounter<13){
			g.setFont(new Font("Dialog", 70, 70));
			g.drawString("GAME STARTED", 34, 250);
		}
		if (ram){
			g.setFont(new Font("Dialog", 25, 25));
			g.drawString(""+(ramStartCount-(int)gameCounter+35), 290, 55);
		}
		
		//WALL
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 5, 550);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			square.setBoost(true);
			break;
		case KeyEvent.VK_RIGHT:
			square.setMoveRight(true);
			break;
		case KeyEvent.VK_LEFT:
			square.setMoveLeft(true);
			break;
		case KeyEvent.VK_SPACE:
			lazers.add(new Lazer(square.getX()+5, square.getY()));
			gameStarted = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			square.setBoost(false);
			break;
		case KeyEvent.VK_RIGHT:
			square.setMoveRight(false);
			break;
		case KeyEvent.VK_LEFT:
			square.setMoveLeft(false);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
