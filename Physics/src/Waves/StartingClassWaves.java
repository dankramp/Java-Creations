package Waves;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class StartingClassWaves extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private double source1y;
	private double source2y;
	private ArrayList <Point> wave1 = new ArrayList <Point> ();
	private ArrayList <Point> wave2 = new ArrayList <Point> ();
	private double counter1;
	private double counter2;
	private int amp1;
	private int amp2;
	private int newAmp1;
	private int newAmp2;
	private int speed1;
	private int speed2;
	private int select1;
	private int select2;
	private double freq1;
	private double freq2;
	
	private boolean twoWaves;
	private boolean linked;
	private boolean constructWave;

	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Wave Simulator");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		source1y = 250;
		amp1 = 100;
		counter1 = 0;
		speed1 = 1;
		select1 = 1;
		freq1 = .2;
		newAmp1 = 100;
		
		source2y = 250;
		amp2 = 150;
		counter2 = 0;
		speed2 = 2;
		select2 = 1;
		freq2 = .1;
		newAmp2 = 150;
				
		twoWaves = true;
		linked = false;
		constructWave = true;

		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			//WAVE 1
			wave1.add(new Point(-2, (int) source1y));
			
			for (int i = 0; i<wave1.size(); i++){
				wave1.get(i).translate(speed1, 0);
				if (wave1.get(i).getX()>602)wave1.remove(i);
			}
			if (Math.abs(source1y-250)<5)amp1 = newAmp1;
			source1y = 250+amp1*Math.sin(counter1/5);
			
			counter1+=freq1;
			if (select1>3)select1 = 1;
			if (select1<1)select1 = 3;
			if (newAmp1>200)newAmp1 = 200;
			if (newAmp1<10)newAmp1 = 10;
			if (speed1<1)speed1 = 1;
			if (speed1>9)speed1 = 9;
			if (freq1>.5)freq1 = .5;
			if (freq1<.02)freq1 = .02;
			
			//WAVE 2
			if (twoWaves){
				if (Math.abs(source2y-250)<10)amp2 = newAmp2;
				source2y = 250+amp2*Math.sin(counter2/5);
				counter2+=freq2;
				if (linked){
					source2y = 500-source1y;
					speed2 = speed1;
				}
				wave2.add(new Point(-2, (int) source2y));
				for (int i = 0; i<wave2.size(); i++){
					wave2.get(i).translate(speed2, 0);
					if (wave2.get(i).getX()>602)wave2.remove(i);
				}
				if (select2>3)select2 = 1;
				if (select2<1)select2 = 3;
				if (newAmp2>200)newAmp2 = 200;
				if (newAmp2<10)newAmp2 = 10;
				if (speed2<1)speed2 = 1;
				if (speed2>9)speed2 = 9;
				if (freq2>.5)freq2 = .5;
				if (freq2<.02)freq2 = .02;
			}
			else {
				counter2 = 0;
				source2y = 250;
				wave2.clear();
			}
			
			repaint();
			try {
				Thread.sleep(7);
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
		//WAVE 1
		try {
			for (int i = 0; i<wave1.size()-1; i++){
				g.setColor(Color.RED);
				g.drawLine((int)wave1.get(i).getX(), (int)wave1.get(i).getY(), (int)wave1.get(i+1).getX(), (int)wave1.get(i+1).getY());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//WAVE 2
		try {
			for (int i = 0; i<wave2.size(); i++){
				g.setColor(Color.BLUE);
				g.drawLine((int)wave2.get(i).getX(), (int)wave2.get(i).getY(), (int)wave2.get(i+1).getX(), (int)wave2.get(i+1).getY());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//WAVE 3
		try {
			for (int i = 0; i<wave1.size()-1; i++){
				g.setColor(Color.GREEN);
				for (int a = 0; a<wave2.size()-1; a++){
					if (wave1.get(i).getX()==wave2.get(a).getX()&&constructWave)g.drawLine((int)wave1.get(i).getX(), (int)wave1.get(i).getY()+(int)wave2.get(a).getY()-500+250, (int)wave1.get(i+1).getX(), (int)wave1.get(i+1).getY()+(int)wave2.get(a+1).getY()-500+250);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		/*
		//INTERSECTION
		try {
			if (twoWaves){
				for (int i = 0; i<wave1.size(); i++){
					for (int a = 0; a<wave2.size(); a++){
						if (wave1.get(i).getX()==wave2.get(a).getX()&&Math.abs(wave1.get(i).getY()-wave2.get(a).getY())<3){
							g.setColor(Color.YELLOW);
							g.fillOval((int)(wave1.get(i).getX())-3, (int)(wave1.get(i).getY()+wave2.get(a).getY())/2-3, 6, 6);
						}
					}
				}
			}
		} catch (Exception e) {
			//System.out.println("Error");
			//e.printStackTrace();
		}
		*/
		//LINE
		g.setColor(Color.BLACK);
		g.fillRect(0, 250, 605, 1);
		//TEXT
		g.setColor(Color.BLACK);
		g.drawString("(WASD)", 30, 23);
		g.drawString("Wave 1:    Amplitude: "+newAmp1+"    Speed: "+speed1+"    Frequency: "+((int)(freq1*100))/100.0, 40, 40);
		if (twoWaves){
			g.drawString("(Arrow Keys)", 30, 463);
			g.drawString("Wave 2:    Amplitude: "+newAmp2+"    Speed: "+speed2+"    Frequency: "+(int)(freq2*100)/100.0, 40, 480);
		}
		g.drawString("Press SPACE to toggle TWO WAVE MODE", 340, 480);
		g.drawString("Press L to toggle WAVE LINK", 340, 500);
		g.drawString("Press SHIFT to toggle INTERFERENCE WAVE", 340, 460);
		//HIGHLIGHTER
		g.setColor(new Color(255, 0, 0, 80));
		if (select1 == 1&&newAmp1!=0)g.fillRect(90, 28, 90, 15);
		if (select1 == 2&&newAmp1/100<1)g.fillRect(178, 28, 55, 15);
		else if (select1 == 2&&newAmp1/100>=1)g.fillRect(185, 28, 55, 15);
		if (select1 == 3&&newAmp1/100>=1)g.fillRect(245, 28, 94, 15);
		else if (select1 == 3&&newAmp1/100<1)g.fillRect(238, 28, 94, 15);

		if (twoWaves){
			if (!linked)g.setColor(new Color(0, 0, 255, 80));
			else g.setColor(new Color(200, 200, 200, 80));
			if (select2 == 1)g.fillRect(90, 468, 90, 15);
			if (select2 == 2&&newAmp2/100<1)g.fillRect(178, 468, 55, 15);
			else if (select2 == 2&&newAmp2/100>=1)g.fillRect(185, 468, 55, 15);
			if (select2 == 3&&newAmp2/100>=1)g.fillRect(245, 468, 94, 15);
			else if (select2 == 3&&newAmp2/100<1)g.fillRect(238, 468, 94, 15);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_SPACE:
			twoWaves^=true;
			break;
		//WAVE 1
		case KeyEvent.VK_D:
			select1++;
			break;
		case KeyEvent.VK_A:
			select1--;
			break;
		case KeyEvent.VK_S:
			if (select1 == 1)newAmp1-=10;
			if (select1 == 2)speed1--;
			if (select1 == 3)freq1-=.02;
			break;
		case KeyEvent.VK_W:
			if (select1 == 1)newAmp1+=10;
			if (select1 == 2)speed1++;
			if (select1 == 3)freq1+=.02;
			break;
		//WAVE 2
		case KeyEvent.VK_RIGHT:
			select2++;
			break;
		case KeyEvent.VK_LEFT:
			select2--;
			break;
		case KeyEvent.VK_DOWN:
			if (select2 == 1&&!linked)newAmp2-=10;
			if (select2 == 2&&!linked)speed2--;
			if (select2 == 3&&!linked)freq2-=.02;
			break;
		case KeyEvent.VK_UP:
			if (select2 == 1&&!linked)newAmp2+=10;
			if (select2 == 2&&!linked)speed2++;
			if (select2 == 3&&!linked)freq2+=.02;
			break;
		case KeyEvent.VK_L:
			linked^=true;
			break;
		case KeyEvent.VK_SHIFT:
			constructWave^=true;
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

}
