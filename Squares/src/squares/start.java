package squares;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class start extends Applet implements Runnable, KeyListener {
	
	private Player player;
	private Enemy[] enemies = new Enemy[2];
	//private Enemy enemy, enemy2;
	private Image image;
	private Graphics graphics;
	private boolean dead = false;
	private double[] angle = new double[2];
	
	public void init() {
		setSize(500, 500);
		setBackground(Color.BLUE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Squares");
		frame.setResizable(false);
		
	}
	public void start(){
		player = new Player(6);
		enemies[0] = new Enemy(50, 50, 4);
		enemies[1] = new Enemy(450, 450, 4.5);

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		while(true){
			
			player.update();
			enemies[0].update();
			enemies[1].update();
			repaint();
			for (int i = 0; i<2; i++){
				double slope = (player.getY()-enemies[i].getY())/(player.getX()-enemies[i].getY());
				angle[i] = (Math.atan(slope));
				System.out.println(angle[i]);
				double up = -(player.getY()-enemies[i].getY())/Math.sqrt(Math.pow(player.getY()-enemies[i].getY(), 2)+Math.pow(player.getX()-enemies[i].getX(),2));
				double over = Math.cos(angle[i]);
				if (player.getX()<enemies[i].getX())over*=-1;
				enemies[i].setSpeedY(up*-enemies[i].getSpeedMult());
				enemies[i].setSpeedX(over*enemies[i].getSpeedMult());
			
				if (Math.sqrt(Math.pow(player.getX()-enemies[i].getX(), 2) + Math.pow(player.getY()-enemies[i].getY(), 2))<35){
					dead = true;
					player.setSpeedX(0);
					player.setSpeedY(0);
					enemies[i].setSpeedX(0);
					enemies[i].setSpeedY(0);
				}
			}
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g){
		g.fillOval((int)(player.getX()-15), (int)(player.getY()-15), 30, 30);
		g.setColor(Color.RED);
		for (int i = 0; i<2; i++){
			g.fillOval((int)(enemies[i].getX()-20), (int)(enemies[i].getY()-20), 40, 40);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (!dead){
			switch(arg0.getKeyCode()){
			case KeyEvent.VK_UP:
				player.setSpeedY(-player.getSpeedMult());
				break;
			case KeyEvent.VK_DOWN:
				player.setSpeedY(player.getSpeedMult());
				break;
			case KeyEvent.VK_RIGHT:
				player.setSpeedX(player.getSpeedMult());
				break;
			case KeyEvent.VK_LEFT:
				player.setSpeedX(-player.getSpeedMult());
				break;
			}
		}
	}

		

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			player.setSpeedY(0);
			break;
		case KeyEvent.VK_DOWN:
			player.setSpeedY(0);
			break;
		case KeyEvent.VK_RIGHT:
			player.setSpeedX(0);
			break;
		case KeyEvent.VK_LEFT:
			player.setSpeedX(0);
			break;
		
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
