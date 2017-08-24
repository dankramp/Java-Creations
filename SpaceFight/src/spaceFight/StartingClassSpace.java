package spaceFight;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class StartingClassSpace extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private Graphics2D g2d;
	private Player player;
	private ArrayList <Lazers> lazers = new ArrayList <Lazers>();
	private GridEnemies enemies;
	private boolean lazerFired = false;
	private int level;
	private Random random;
	private static boolean dead;
	private double angle;
	private boolean[][] stars;
	private double battery;
	private boolean batteryDown, batteryLocked;
	private int counter, lives;
	private boolean levelComplete;
	private double angleCounter;
	private int color;
	private Boss boss;
	private int bossCounter, temp, bossAttack, temp2;
	private boolean bossSummon;
	private boolean gameOver;
	private boolean rapidFire = false, bombGet = true;
	private ArrayList <String> cheatcodes = new ArrayList <String>();
	private ArrayList <Particles> particles = new ArrayList <Particles>();
	private AudioClip playerFire;
	private ArrayList <AudioClip> enemyFire = new ArrayList <AudioClip>();
	private AudioClip explode;
	private int lazercount = 0;


	
	public void init() {
		setSize(500, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("SPACE FUN TIME");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE

		playerFire = getAudioClip(getDocumentBase(),"Player_Lazer.wav");
		for (int i = 0; i<10; i++){
			enemyFire.add(getAudioClip(getDocumentBase(),"Enemy_Lazer.wav"));
		}
		explode = getAudioClip(getDocumentBase(),"Explosion.wav");

		
		level = 5;
		lives = 3;
		temp = 0;
		temp2 = 400;
		counter = 0;
		battery = 100;
		color = 0;
		bossAttack = 0;
		batteryLocked = false;
		image = null;
		random = new Random();
		stars = new boolean[getHeight()][getWidth()];
		for (int row = 0; row<stars.length; row++){
			for (int col = 0; col<stars[0].length; col++){
				stars[row][col] = false;
			}
		}
		for (int row = 1; row<stars.length-2; row++){
			for (int col = 1; col<stars[0].length-2; col++){
				if (random.nextInt(1500)==2&&stars[row-1][col]!=true&&stars[row][col-1]!=true&&stars[row-1][col-1]!=true&&stars[row+1][col-1]!=true&&stars[row+1][col]!=true&&stars[row+1][col+1]!=true&&stars[row][col+1]!=true&&stars[row-1][col+1]!=true)stars[row][col] = true;
				else stars[row][col] = false;
			}
		}
		dead = false;
		batteryDown = false;
		enemies = new GridEnemies(level);
		player = new Player();
		boss = new Boss(level);
		gameOver = false;
		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		dead = false;
		player.reset();
		if (levelComplete)enemies.reset(level);
		if (level == 5&&levelComplete)boss.reset();
		levelComplete = false;
		lazers.removeAll(lazers);
		battery = 100;
		for (int i = 0; i<cheatcodes.size(); i++){
			cheatcodes.remove(i);
		}
		rapidFire = false;
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			player.update();
			for (int i = 0; i<particles.size(); i++){
				particles.get(i).update();
			}
			for (int i = 0; i<lazers.size(); i++){
				lazers.get(i).update();
				if (lazers.get(i).getType().equals("secondary")){
					if (lazers.get(i).getX()<player.getX())lazers.get(i).setSpeedX(lazers.get(i).getSpeedX()+.2);
					if (lazers.get(i).getX()>player.getX())lazers.get(i).setSpeedX(lazers.get(i).getSpeedX()-.2);
					if (lazers.get(i).getSpeedX()>2.5)lazers.get(i).setSpeedX(2.5);
					if (lazers.get(i).getSpeedX()<-2.5)lazers.get(i).setSpeedX(-2.5);
				}
				if ((lazers.get(i).getY()<-10||lazers.get(i).getY()>510))lazers.remove(i);
			}
			boss.update();
			enemies.update();
			angleCounter++;
			//COLOR CHANGER
			if (color<0)color = 4;
			else if (color>4)color = 0;
			
			//ENEMIES FIRING
			for (int row = 0; row<enemies.getEnemies().length; row++){
				for (int col = 0; col<enemies.getEnemies()[0].length; col++){
					if (random.nextInt(300)==2&&enemies.getEnemies()[row][col].equals("simple")&&!dead){
						lazers.add(new Lazers(enemies.getX()[row][col], enemies.getY()[row][col], false, 0, "primary"));
						enemyFire.get(lazercount).play();
						lazercount++;
						if (lazercount>8)lazercount = 0;
					}
					if (random.nextInt(500)==2&&enemies.getEnemies()[row][col].equals("aim")&&!dead){
						angle = player.getX()-enemies.getX()[row][col];
						lazers.add(new Lazers(enemies.getX()[row][col], enemies.getY()[row][col], false, angle/85, "secondary"));
						enemyFire.get(lazercount).play();
						lazercount++;
						if (lazercount>8)lazercount = 0;
					}
				}
			}
			
			//BATTERY STUFF
			if (!batteryLocked)battery+=.3;
			if (batteryLocked)battery+=.2;
			if (batteryDown){
				batteryDown = false;
				battery-=12;
			}
			if (battery>100)battery=100;
			if (battery<0)batteryLocked = true;
			if (battery>25)batteryLocked = false;
			
			//KILL ENEMIES
			for (int row = 0; row<enemies.getEnemies().length; row++){
				for (int col = 0; col<enemies.getEnemies()[0].length; col++){
					for (int i = 0; i<lazers.size(); i++){
						if ((Math.abs(lazers.get(i).getY()-enemies.getY()[row][col])<15&&lazers.get(i).getSpeedY()<0&&Math.abs(enemies.getX()[row][col]-lazers.get(i).getX())<10)&&!enemies.getEnemies()[row][col].equals("")&&!dead){
							if (enemies.getEnemies()[row][col].equals("bombgiver")){
								enemies.setEnemies("", row, col);
								lazers.remove(i);
								bombGet = true;
								explode.play();
								break;
							}
							enemies.setEnemies("", row, col);
							for (int a = 0; a<30; a++){
								particles.add(new Particles((int)(enemies.getX()[row][col]), (int)(enemies.getY()[row][col]), 2.6, 4, Color.WHITE));
							}
							lazers.remove(i);
							explode.play();
							break;
						}
						if ((Math.abs(lazers.get(i).getY()-enemies.getY()[row][col])<15&&lazers.get(i).getSpeedY()<0&&Math.abs(enemies.getX()[row][col]-lazers.get(i).getX())<10&&lazers.get(i).getType().equals("bomb"))&&!enemies.getEnemies()[row][col].equals("")&&!dead){
							enemies.setEnemies("", row, col);
							if (row<3)enemies.setEnemies("", row+1, col);
							if (row>0)enemies.setEnemies("", row-1, col);
							if (col<7)enemies.setEnemies("", row, col+1);
							if (col>0)enemies.setEnemies("", row, col-1);
							for (int a = 0; a<120; a++){
								particles.add(new Particles((int)(enemies.getX()[row][col]), (int)(enemies.getY()[row][col]), 3, 6, new Color(0,255,255)));
							}
							explode.play();
							lazers.remove(i);
							break;
						}
					}
				}
			}
			//KILL PLAYER
			for (int i = 0; i<lazers.size(); i++){
				if (Math.abs(lazers.get(i).getY()-player.getY())<15&&lazers.get(i).getSpeedY()>0&&Math.abs(player.getX()-lazers.get(i).getX())<10&&lazers.get(i).getType().equals("primary")&&!levelComplete){
					lazers.remove(i);
					player.setHealth(player.getHealth()-34);
					break;
				}
				if (Math.abs(lazers.get(i).getY()-player.getY())<12&&lazers.get(i).getSpeedY()>0&&Math.abs(player.getX()-lazers.get(i).getX())<11&&lazers.get(i).getType().equals("secondary")&&!levelComplete){
					lazers.remove(i);
					player.setHealth(player.getHealth()-34);
				}
			}
			if (player.getHealth()<0){
				player.setHealth(0);
				dead = true;
				explode.play();
				for (int a = 0; a<50; a++){
					particles.add(new Particles((int)(player.getX()), (int)(player.getY()), 3.8, 4.3, Color.WHITE));
				}
			}
			if (lives <=0&&dead)gameOver = true;
			for (int row = 0; row<enemies.getEnemies().length; row++){
				for (int col = 0; col<enemies.getEnemies()[0].length; col++){
					if (enemies.getY()[row][col]>player.getY()-20&&!enemies.getEnemies()[row][col].equals("")){
						dead = true;
					}
				}
			}
			
			//BOSS
			if (!boss.isInactive()) {
				if (boss.getX() + 25 < player.getX() && boss.getHealth() > 0)
					boss.setSpeedX(boss.getSpeedX() + .1);
				if (boss.getX() + 25 > player.getX() && boss.getHealth() > 0)
					boss.setSpeedX(boss.getSpeedX() - .1);
				bossCounter++;
				if (bossCounter > 250 && !bossSummon) {
					bossCounter = 0;
					bossAttack = random.nextInt(10);
				}
				if ((bossCounter == 201 || bossCounter == 208
						|| bossCounter == 215 || bossCounter == 222 || bossCounter == 229)
						&& bossAttack < 7 && boss.getHealth() > 0&&!dead) {
					lazers.add(new Lazers(boss.getX(), boss.getY() + 50, false,
							0, "primary"));
					lazers.add(new Lazers(boss.getX() + 50, boss.getY() + 50,
							false, 0, "primary"));
				} else if ((bossCounter == 201 || bossCounter == 215 || bossCounter == 229)
						&& boss.getHealth() > 0
						&& bossAttack >= 7
						&& bossAttack < 9&&!dead) {
					lazers.add(new Lazers(boss.getX() + 25, boss.getY() + 50,
							false, 0, "secondary"));
				} else if (bossAttack == 9&&!dead&&boss.getHealth()>0) {
					if (!bossSummon)enemies.reset(69);
					bossSummon = true;
					boss.setyDest(-200);
				}

				for (int i = 0; i < lazers.size(); i++) {
					if (Math.abs(lazers.get(i).getY() - (boss.getY() + 25)) < 30
							&& Math.abs(lazers.get(i).getX()- (boss.getX() + 25)) < 25
							&& lazers.get(i).getSpeedY() < 0 && !dead&& !lazers.get(i).getType().equals("bomb")) {
						boss.setHealth(boss.getHealth() - 10);
						lazers.remove(i);
						battery+=12;
						break;
					}
					if (Math.abs(lazers.get(i).getY() - (boss.getY() + 25)) < 30
							&& Math.abs(lazers.get(i).getX()- (boss.getX() + 25)) < 25
							&& lazers.get(i).getSpeedY() < 0 && !dead && lazers.get(i).getType().equals("bomb")) {
						boss.setHealth(boss.getHealth() - 90);
						for (int a = 0; a<120; a++){
							particles.add(new Particles((int)(lazers.get(i).getX()), (int)(lazers.get(i).getY()), 3, 8, new Color(0,255,255)));
						}
						lazers.remove(i);
					}
				}
				if (boss.getyDest()==800&&bossCounter%15==0){
					for (int a = 0; a<50; a++){
						particles.add(new Particles((int)(boss.getX()+25), (int)(boss.getY()+25), 4.5, 7, Color.WHITE));
					}
					for (int row = 0; row<enemies.getEnemies().length; row++){
						for (int col = 0; col<enemies.getEnemies()[0].length; col++){
							if (!enemies.getEnemies()[row][col].equals("")){
								enemies.setEnemies("", row, col);
								for (int a = 0; a<30; a++){
									particles.add(new Particles((int)(enemies.getX()[row][col]), (int)(enemies.getY()[row][col]), 2.6, 4, Color.WHITE));
								}
							}
						}
					}
				}
			}
			//PARTICLES
			for (int i = 0; i<particles.size(); i++){
				if ((int)(particles.get(i).getRadius())==0)particles.remove(i);
			}
			for (int i = 0; i<lazers.size(); i++){
				if (lazers.get(i).getType().equals("bomb")){
					for (int a = 0; a<10; a++){
						particles.add(new Particles((int)(lazers.get(i).getX()), (int)(lazers.get(i).getY()), 1.5, 2, new Color(100, 255, 255)));
					}
				}
			}

			//LEVEL COMPLETE
			counter = 0;
			for (int row = 0; row<enemies.getEnemies().length; row++){
				for (int col = 0; col<enemies.getEnemies()[0].length; col++){
					if (!enemies.getEnemies()[row][col].equals(""))counter++;
					if (level == 5&&!bossSummon){
						if (boss.getHealth()>0)counter++;
					}
				}
			}
			if (counter == 0&&!bossSummon)levelComplete = true;
			if (counter == 0&&bossSummon){
				bossSummon = false;
			}
			
			//CHEAT CODE
			if (cheatcodes.size()>=4){
				for (int i = 0; i<cheatcodes.size()-3; i++){
					if (cheatcodes.get(i).equals("F")&&cheatcodes.get(i+1).equals("I")&&cheatcodes.get(i+2).equals("R")&&cheatcodes.get(i+3).equals("E"))rapidFire = true;
				}
			}
			if (rapidFire&&angleCounter%10==0&&!dead&&!levelComplete&&!gameOver){
				lazers.add(new Lazers(player.getX(), player.getY(), true, 0, "primary"));
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
		//STARS
		g.setColor(Color.WHITE);
		for (int row = 0; row<stars.length; row++){
			for (int col = 0; col<stars[0].length; col++){
				if (stars[row][col])g.fillOval(row, col, 2, 2);
			}
		}
		//THE LAZERS
		Color lightBlue = new Color(0, 255, 255);
		for (int i = 0; i<lazers.size(); i++){
			g.setColor(Color.RED);
			if (lazers.get(i).getType().equals("primary")&&lazers.get(i).getSpeedY()>0)g.fillRect((int)(lazers.get(i).getX()), (int)(lazers.get(i).getY())-5, 1, 10);
			if (lazers.get(i).getType().equals("primary")&&lazers.get(i).getSpeedY()<0){
				g.setColor(Color.GREEN);
				g.fillRect((int)(lazers.get(i).getX()), (int)(lazers.get(i).getY())-5, 1, 10);
			}
			if (lazers.get(i).getType().equals("secondary")){
				g.setColor(Color.ORANGE);
				g.fillOval((int)(lazers.get(i).getX())-2, (int)(lazers.get(i).getY())-2, 4, 4);
			}
			if (lazers.get(i).getType().equals("bomb")){
				g.setColor(lightBlue);
				g.fillOval((int)(lazers.get(i).getX())-5, (int)(lazers.get(i).getY())-5, 10, 10);
				Color lightBlueFade = new Color(0, 255, 255, 127);
				g.setColor(lightBlueFade);
				g.fillOval((int)(lazers.get(i).getX())-12, (int)(lazers.get(i).getY())-12, 24, 24);
			}

		}
		//THE ENEMIES
		for (int row = 0; row<enemies.getEnemies().length; row++){
			for (int col = 0; col<enemies.getEnemies()[0].length; col++){
				if (enemies.getEnemies()[row][col].equals("simple")){
					g.setColor(Color.BLUE);
					g.fillRect((int)(enemies.getX()[row][col])-10, (int)(enemies.getY()[row][col])-10, 20, 20);
					//File img = new File("spaceFight/Alien.png");
					/*
					try {
						Image image = ImageIO.read(img);
						g.drawImage(image, (int)(enemies.getX()[row][col])-10, (int)(enemies.getY()[row][col])-10, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
				}
				if (enemies.getEnemies()[row][col].equals("aim")){
					g.setColor(Color.ORANGE);
					g.drawRect((int)(enemies.getX()[row][col])-10, (int)(enemies.getY()[row][col])-10, 20, 20);
				}
				if (enemies.getEnemies()[row][col].equals("bombgiver")){
					g.setColor(lightBlue);
					g.fillRect((int)(enemies.getX()[row][col])-9, (int)(enemies.getY()[row][col])-9, 19, 19);
					g.setColor(Color.white);
					g.drawRect((int)(enemies.getX()[row][col])-10, (int)(enemies.getY()[row][col])-10, 20, 20);
				}
			}
		}
		//THE PLAYER
		if (color == 0){
		Color gray = new Color(255, 255, 255);
		g.setColor(gray.darker());
		}
		else if (color == 1)g.setColor(Color.YELLOW);
		else if (color == 2){
			Color pink = new Color(255, 23, 186);
			g.setColor(pink);
		}
		else if (color == 3)g.setColor(Color.WHITE);
		else if (color == 4)g.setColor(Color.BLACK);
		if (!dead)g.fillRect((int)(player.getX()-10), (int)(player.getY()-10), 20, 20);
		
		//BATTERY
		g.setColor(Color.WHITE);
		g.drawRect(3, 483, 103, 13);
		if (battery>25&&!bombGet)g.setColor(Color.YELLOW);
		else if (battery<=25&&!bombGet) g.setColor(Color.RED);
		if (bombGet)g.setColor(lightBlue);
		g.fillRect(5, 485, (int)(battery), 10);
		
		//HEALTH BAR
		g.setColor(Color.WHITE);
		g.drawRect(115, 483, 103, 13);
		if (player.getHealth()>50)g.setColor(Color.GREEN);
		else if (player.getHealth()<50&&player.getHealth()>25)g.setColor(Color.YELLOW);
		else g.setColor(Color.RED);
		g.fillRect(117, 485, (int)(player.getHealth()), 10);
		
		//BOSS
		Graphics2D gg = (Graphics2D) g.create();
	    gg.rotate(angleCounter/20, (int)(boss.getX())+25, (int)(boss.getY())+25);
	    gg.setColor(Color.GREEN);
	    gg.rotate(angleCounter/40, (int)(boss.getX())+25, (int)(boss.getY())+25);
	    gg.drawRect((int)(boss.getX()), (int)(boss.getY()), 50, 50);
	    gg.rotate(-(angleCounter)/10, (int)(boss.getX())+25, (int)(boss.getY())+25);
	    gg.drawRect((int)(boss.getX()), (int)(boss.getY()), 50, 50);
	    gg.dispose();
	    if (level == 5){
			if (boss.getHealth()>240)g.setColor(Color.GREEN);
			else if (boss.getHealth()<240&&boss.getHealth()>120)g.setColor(Color.YELLOW);
			else g.setColor(Color.RED);
			g.fillRect(8, 5, boss.getHealth(), 13);
			g.setColor(Color.WHITE);
			g.drawRect(8, 5, 482, 13);
		}
	    if (temp>boss.getHealth()){
	    	gg.setColor(Color.WHITE);
	    	gg.rotate(angleCounter/40, (int)(boss.getX())+25, (int)(boss.getY())+25);
	 	    gg.fillRect((int)(boss.getX()), (int)(boss.getY()), 50, 50);
	 	    gg.rotate(-(angleCounter)/10, (int)(boss.getX())+25, (int)(boss.getY())+25);
	 	    gg.fillRect((int)(boss.getX()), (int)(boss.getY()), 50, 50);
		    g.fillRect(8, 5, boss.getHealth(), 13);
	    }
	    temp = boss.getHealth();
	    
	    //PLAYER HIT
	    g.setColor(Color.RED);
	    if (temp2>player.getHealth()){
	    	g.fillRect(0, 0, 500, 500);
	    	temp2 = player.getHealth();
	    }

		//TEXT
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", 50, 20));
		g.drawString("LIVES: "+lives, 400, 494);
		if (dead&&!gameOver){
			g.setFont(new Font("Dialog", 50, 60));
			g.drawString("YOU ARE DEAD", 30, 240);
			g.setFont(new Font("Dialog", 50, 25));
			g.drawString("press ENTER to continue", 115, 274);
		}
		if(levelComplete&&!gameOver){		
			g.setFont(new Font("Dialog", 50, 52));
			g.drawString("LEVEL COMPLETE", 23, 240);
			g.setFont(new Font("Dialog", 50, 25));
			g.drawString("press ENTER to continue", 115, 274);
		}
		if(gameOver){		
			g.setFont(new Font("Dialog", 50, 75));
			g.drawString("GAME OVER", 23, 240);
			g.setFont(new Font("Dialog", 50, 25));
			g.drawString("press ESCAPE to quit", 120, 274);
		}
		if (bombGet&&!dead&&!levelComplete&&!gameOver){
			g.setFont(new Font("Dialog", 50, 35));
			g.drawString("Press B to fire BOMB", 90, 370);
		}
		//PARTICLES
		for (int i = 0; i<particles.size(); i++){
			g.setColor(particles.get(i).getColor());
			g.fillOval(particles.get(i).getX()-(int)(particles.get(i).getRadius()), particles.get(i).getY()-(int)(particles.get(i).getRadius()), (int)(particles.get(i).getRadius()*2), (int)(particles.get(i).getRadius()*2));
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			if (!dead&&!levelComplete&&!gameOver)player.setMoveRight(true);
			break;
		case KeyEvent.VK_LEFT:
			if (!dead&&!levelComplete&&!gameOver)player.setMoveLeft(true);
			break;
		case KeyEvent.VK_SPACE:
			if (!lazerFired&&!dead&&!batteryLocked&&!levelComplete&&!gameOver&&!rapidFire){
				lazers.add(new Lazers(player.getX(), player.getY(), true, 0, "primary"));
				lazerFired = true;
				batteryDown = true;
				playerFire.play();
			}
			break;
		case KeyEvent.VK_ENTER:
			if (dead&&!gameOver){
				lives-=1;
				dead = false;
				enemies.resetPos();
				reset();
				break;
			}
			if (levelComplete&&!gameOver){
				level++;
				reset();
				break;
			}
		case KeyEvent.VK_B:
			if (bombGet){
				lazers.add(new Lazers(player.getX(), player.getY(), true, 0, "bomb"));
				bombGet = false;
			}
		case KeyEvent.VK_F:
			cheatcodes.add("F");
			break;
		case KeyEvent.VK_I:
			cheatcodes.add("I");
			break;
		case KeyEvent.VK_R:
			cheatcodes.add("R");
			break;
		case KeyEvent.VK_E:
			cheatcodes.add("E");
			break;
		case KeyEvent.VK_W:
			color++;
			break;
		case KeyEvent.VK_S:
			color--;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			player.setMoveRight(false);
			break;
		case KeyEvent.VK_LEFT:
			player.setMoveLeft(false);
			break;
		case KeyEvent.VK_SPACE:
			lazerFired = false;
			break;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public static boolean isDead() {
		return dead;
	}
	

}
