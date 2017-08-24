package Waves;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class StartingClassAdditiveWaves extends Applet implements Runnable, KeyListener, MouseListener {

	private Image image;
	private Graphics graphics;
	private Frame frame;
	private int herz1, herz2, herz3;
	private ArrayList <Integer> wave1, wave2, wave3;
	private int h, w;
	private int waveSelect;
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		frame = (Frame) this.getParent().getParent();
		frame.setTitle("Additive Synthesis");
		frame.setResizable(true);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		herz1 = 500;
		herz2 = 200;
		herz3 = 100;
		w = 600;
		h = 500;
		wave1 = new ArrayList<Integer> ();
		wave2 = new ArrayList<Integer> ();
		wave3 = new ArrayList<Integer> ();
		for (int i = 0; i<w; i+=2) {
			wave1.add((int)(h*Math.sin(i/2.0/w*herz1)));
			wave2.add((int)(h*Math.sin(i/2.0/w*herz2)));
			wave3.add((int)(h*Math.sin(i/2.0/w*herz3)));
		}
		waveSelect = 0;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			if (w!=frame.getWidth()-22||h!=frame.getHeight()-109) {
				wave1.clear();
				wave2.clear();
				wave3.clear();
				w = frame.getWidth()-22;
				h = frame.getHeight() - 109;
				for (int i = 0; i<w; i+=2) {
					wave1.add((int)(h*Math.sin(i/2.0/w*herz1)));
					wave2.add((int)(h*Math.sin(i/2.0/w*herz2)));
					wave3.add((int)(h*Math.sin(i/2.0/w*herz3)));
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
		graphics.setColor(Color.WHITE);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		int height = (frame.getHeight()-109);
		int width = frame.getWidth()-22;
		// Background
		g.setColor(new Color(210, 210, 210));
		g.fillRect(0, 0, frame.getWidth(), height/3);
		g.setColor(new Color(120, 120, 120));
		g.fillRect(0, height/3, frame.getWidth(), height*6/9);
		g.setColor(new Color(140, 140, 140));
		g.fillRect(0, height/3, frame.getWidth(), height*4/9);
		g.setColor(new Color(160, 160, 160));
		g.fillRect(0, height/3, frame.getWidth(), height*2/9);
		
		// First Wave
		g.setColor(Color.RED);
		for (int i = 0; i<wave1.size()-1; i++) {
			g.drawLine(2*i, (int)(wave1.get(i)/11+height*4/9), 2*(i+1), (int)(wave1.get(i+1)/11+height*4/9));
		}
		// Second wave
		g.setColor(Color.GREEN);
		for (int i = 0; i<wave2.size()-1; i++) {
			g.drawLine(2*i, (int)(wave2.get(i)/11+height*6/9), 2*(i+1), (int)(wave2.get(i+1)/11+height*6/9));
		}
		// Third wave
		g.setColor(Color.BLUE);
		for (int i = 0; i<wave3.size()-1; i++) {
			g.drawLine(2*i, (int)(wave3.get(i)/11+height*8/9), 2*(i+1), (int)(wave3.get(i+1)/11+height*8/9));
		}
		//Interference Wave
		g.setColor(Color.YELLOW);
		for (int i = 0; i<wave1.size()-1; i++) {
			g.drawLine(2*i, (int)((wave1.get(i)+wave2.get(i)+wave3.get(i))/18+height/6), 2*(i+1), (int)((wave1.get(i+1)+wave2.get(i+1)+wave3.get(i+1))/18+height/6));
		}
		
		//Selector
		g.setColor(new Color(255, 255, 0, 80));
		switch (waveSelect) {
		case 1:
			g.fillRect(0, height/3, width, height*2/9);
			break;
		case 2:
			g.fillRect(0, height*5/9, width, height*2/9);
			break;
		case 3:
			g.fillRect(0, height*7/9, width, height*2/9);
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_LEFT:
			if (waveSelect == 1) {
				herz1 += 5;
				wave1.clear();
				for (int i = 0; i<w; i+=2) {
					wave1.add((int)(h*Math.sin(i/2.0/w*herz1)));
				}
			}
			if (waveSelect == 2) {
				herz2 += 5;
				wave2.clear();
				for (int i = 0; i<w; i+=2) {
					wave2.add((int)(h*Math.sin(i/2.0/w*herz2)));
				}
			}
			if (waveSelect == 3) {
				herz3 += 5;
				wave3.clear();
				for (int i = 0; i<w; i+=2) {
					wave3.add((int)(h*Math.sin(i/2.0/w*herz3)));
				}
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (waveSelect == 1 && herz1 > 0) {
				herz1 -= 5;
				wave1.clear();
				for (int i = 0; i<w; i+=2) {
					wave1.add((int)(h*Math.sin(i/2.0/w*herz1)));
				}
			}
			if (waveSelect == 2 && herz2 > 0) {
				herz2 -= 5;
				wave2.clear();
				for (int i = 0; i<w; i+=2) {
					wave2.add((int)(h*Math.sin(i/2.0/w*herz2)));
				}
			}
			if (waveSelect == 3 && herz3 > 0) {
				herz3 -= 5;
				wave3.clear();
				for (int i = 0; i<w; i+=2) {
					wave3.add((int)(h*Math.sin(i/2.0/w*herz3)));
				}
			}
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
		int height = frame.getHeight() - 109;
		if (arg0.getY() > (height/3) && arg0.getY() < (height)*5/9) waveSelect = 1;
		else if (arg0.getY() > (height*5/9) && arg0.getY() < (height)*7/9) waveSelect = 2;
		else if (arg0.getY() > (height*7/9) && arg0.getY() < height) waveSelect = 3;
		else waveSelect = 0;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
