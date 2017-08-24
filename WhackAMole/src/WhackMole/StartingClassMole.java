package WhackMole;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class StartingClassMole extends Applet implements Runnable, KeyListener, MouseListener {

	private Image image;
	private Graphics graphics;
	private Frame frame;
	
	private BufferedImage moleman;
	private BufferedImage bottomHalf;
	private BufferedImage topHalf;
	private Point mousePos;
	private boolean click;
	private int[][] molePos;
	private int score;
	private ArrayList<HitMarker> hits;
	private int gameTime;
	private ArrayList <Particle> particles;
	private int lastTime;
	
	private boolean gameStart;
	
	private File highScore;
	private int highscore;
	private Scanner reader;
	
	public void init() {
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(dim.width, dim.height); to set full screen
		setSize(600, 700);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		frame = (Frame) this.getParent().getParent();
		frame.setTitle("WHACK-a-MOLE");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		try {
			moleman = ImageIO.read(new File("Mole.png"));
			bottomHalf = ImageIO.read(new File("Bottom Open.png"));
			topHalf = ImageIO.read(new File("Top Open.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not valid.");
		}
		mousePos = new Point(0,0);
		click = false;
		molePos = new int[3][3];
		for (int r = 0; r<molePos.length; r++){
			for (int c = 0; c<molePos[0].length; c++){
				molePos[r][c] = -91;
			}
		}
		score = 0;
		hits = new ArrayList<HitMarker>();
		gameStart = false;
		gameTime = 30*60+59;
		particles = new ArrayList<Particle>();
		lastTime = 0;
		
		try {
			highScore = new File("scores.txt");
			reader = new Scanner(highScore);
			String num = reader.nextLine();
			//System.out.println("High score" +num);
			highscore = Integer.parseInt(num.trim());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("High score file not found.");
			highscore = 0;
		}

		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		gameStart = true;
		gameTime = 30*60+59;
		for (int r = 0; r<molePos.length; r++){
			for (int c = 0; c<molePos[0].length; c++){
				molePos[r][c] = -91;
			}
		}
		score = 0;
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			if (gameStart)gameTime--;
			if ((gameTime+5)/60==0&&gameStart){
				gameStart = false;
				if (score>highscore){
					highscore = score;
					try {
						highScore.createNewFile();
						FileWriter writer = new FileWriter(highScore);
						writer.write(""+highscore);
						writer.flush();
						writer.close();
					} catch (IOException e) {
						System.out.println("Unable to write file");
					}
				}
				JOptionPane.showMessageDialog(frame, "You finished with a score of: "+score, "GAME OVER", 2);
			}
			//UPDATES
			for (int i = 0; i<hits.size(); i++){
				hits.get(i).update();
				if (hits.get(i).getRadius()>40)hits.remove(i);
			}
			for (int i = 0; i<particles.size(); i++){
				particles.get(i).update();
				if (particles.get(i).getLife()>15)particles.remove(i);
			}
			//HITTING MOLE
			if (click&&gameStart){
				click = false;
				for (int r = 0; r<molePos.length; r++){
					for (int c = 0; c<molePos[0].length; c++){
						int centerX = 50+c*200 + 50;
						int centerY = 200*r+30+Math.abs(molePos[r][c])+(100-Math.abs(molePos[r][c]))/2;
						if (Math.abs(mousePos.getX()-centerX)<50&&Math.abs(mousePos.getY()-centerY)<(100-Math.abs(molePos[r][c]))/2&&molePos[r][c]>-91){
							molePos[r][c] = -91;//Math.abs(molePos[r][c])+1;
							score++;
							for (int i = 0; i<50; i++){
								particles.add(new Particle((int)mousePos.getX(), (int)mousePos.getY()));
							}
							hits.add(new HitMarker((int)mousePos.getX(), (int)mousePos.getY()));
						}
					}
				}
			}
			//FINDS LAST TIME ONE POPPED UP
			for (int r = 0; r<molePos.length; r++){
				for (int c = 0; c<molePos[0].length; c++){
					if (molePos[r][c]>-91)lastTime = gameTime;
				}
			}
			Random random = new Random();
			//MOVEMENT INTIALIZER
			if (gameStart){
				//System.out.println("A");
				int randX = random.nextInt(3);
				int randY = random.nextInt(3);
				if (molePos[randX][randY]==-91&&random.nextInt(40)==0||lastTime-gameTime>20){
					molePos[randX][randY] = -90;
					//System.out.println("B");
				}
				else if (molePos[randX][randY]==0&&random.nextInt(3)==0){
					molePos[randX][randY] = 1;
					//System.out.println("D");
				}
			}
			//MOVEMENT MECHANICS
			for (int r = 0; r<molePos.length; r++){
				for (int c = 0; c<molePos[0].length; c++){
					if (molePos[r][c]>-91&&molePos[r][c]!=0&&molePos[r][c]<91)molePos[r][c]+=9;
					if (molePos[r][c]==91)molePos[r][c] = -91;
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
		graphics.setColor(Color.GRAY.darker());		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		for (int r = 0; r<3; r++){
			for (int c = 0; c<3; c++){
				g.drawImage(topHalf, c*200, 200*r, null);
				g.drawImage(moleman, 50+c*200, 200*r+10+Math.abs(molePos[r][c]), null);
				g.drawImage(bottomHalf, c*200, 200*r+100, null);
				g.setColor(Color.YELLOW);
//				int centerX = 50+c*200 + 50;
//				int centerY = 200*r+30+Math.abs(molePos[r][c])+ (100-Math.abs(molePos[r][c]))/2;
//				g.drawRect(50+c*200, 200*r+30+Math.abs(molePos[r][c]), 100, 100-Math.abs(molePos[r][c]));
//				g.fillRect(centerX-3, centerY-3, 6, 6);
			}
		}
//		g.setColor(Color.GREEN);
//		for (int i = 0; i<hits.size(); i++){
//			g.drawOval(hits.get(i).getX()-hits.get(i).getRadius(),hits.get(i).getY()-hits.get(i).getRadius(),hits.get(i).getRadius()*2, hits.get(i).getRadius()*2);
//		}
		g.setColor(Color.GRAY);
		g.fillRect(0, 600, 620, 200);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", 30, 30));
		g.drawString("Score: "+score, 5, 630);
		g.drawString("High Score: "+highscore, 5, 660);
		if (gameTime/60<10)g.setColor(Color.RED);
		g.drawString(""+gameTime/60, 290, 630);
		
		g.setColor(Color.RED);
		for (int i = 0; i<particles.size(); i++){
			g.fillOval(particles.get(i).getX()-1, particles.get(i).getY()-1, 2, 2);
		}
		
//		g.setColor(new Color(255, 0, 0, 100));
//		g.fillOval((int)mousePos.getX()-15, (int)mousePos.getY()-15, 30, 30);
//		g.fillOval((int)mousePos.getX()-4, (int)mousePos.getY()-4, 8, 8);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
		if (!gameStart)reset();
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
		mousePos = arg0.getPoint();
		click = true;
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
