package Pendelum;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class StartingClassPendelum extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private double length;
	private int mass;
	private double angle;
	private int selector;
	private boolean simulating;
	private double maxAngle;
	private double rate;
	private double counter;
	private double chosenAngle, angleChange;
	private boolean labMode;
	private List<Double> times = new ArrayList <Double>();
	private boolean light;
	
	public void init() {
		setSize(600, 500);		//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Pendelum Simulator");
		frame.setResizable(false);
		
	}
	public void start(){		//INITIALIZE EVERYTHING HERE
		
		length = 1;
		mass = 50;
		angle = 20;
		selector = 1;
		simulating = false;
		maxAngle = 0;
		rate = .05;
		counter = 0;
		chosenAngle = 20;
		labMode = false;
		angleChange = 20;
		light = false;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			if (selector>3)selector = 1;
			if (selector<1)selector = 3;
			
			if (length<.1)length = .1;
			if (length>2.1)length = 2.1;
			
			if (angle<5&&!simulating) angle = 5;
			if (angle>45&&!simulating)angle = 45;
			
			if (mass<10)mass = 10;
			if (mass>500)mass = 500;
			
			if (!simulating){
				maxAngle = angle;
				chosenAngle = angle;
				counter = 0;
				rate = Math.sqrt(.049/(length*200));
			}
			//ad = kv/mass
			//v = Math.sqrt(.049/(length*200)(1-Math.cos(Math.toRadians(angle+90)))
			
			
			if (simulating){
				//rate += Math.sqrt(.049/(length*200)*(1-Math.cos(Math.toRadians(angle))));
				//rate = -Math.sqrt(2*.049*(Math.sin(Math.toRadians(angle+90))*length*200)*(1-Math.cos(Math.toRadians(angle))))/(Math.sin(Math.toRadians(angle+90))*length*200)/(Math.sin(Math.toRadians(angle+90))*length*200);
				//angle-=(Math.cos(rate)*counter)*maxAngle/60;
				angleChange = angle;
				angle = Math.sin(rate*counter+Math.PI/2)*maxAngle;
				if (labMode&&((angle<0&&angleChange>0)||(angle>0&&angleChange<0))){
					times.add(Math.floor(counter/201.6*1000)/1000);
					if (times.size()>10)times.remove(0);
					light = true;
				}
				else light = false;
				maxAngle-=Math.sqrt(.049/(length*200)*(1-Math.cos(Math.toRadians(maxAngle-Math.abs(angle)))));
				//System.out.println(maxAngle);
				//System.out.println(angle);
				/*
				if (maxAngle-angle<.03&&print){
					System.out.println(Math.floor(counter/201.6*1000)/1000+" s");
					print = false;
				}
				else print = true;
				*/
				counter+=3.57;
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
		//LINE
		g.setColor(Color.BLACK);
		g.drawLine(300, 0, 300+(int)(Math.cos(Math.toRadians(angle+90))*length*200), (int)(Math.sin(Math.toRadians(angle+90))*length*200));
		//HIGHLIGHT
		g.setColor(new Color(255, 255, 140));
		if (selector == 1&&!simulating)g.fillRect(5, 465, 105, 25);
		if (selector == 2&&!simulating)g.fillRect(125, 465, 85, 25);
		if (selector == 3&&!simulating)g.fillRect(235, 465, 98, 25);
		//BALL
		g.setColor(Color.GRAY);
		g.fillOval(300+(int)(Math.cos(Math.toRadians(angle+90))*length*200)-(mass/30+5), (int)(Math.sin(Math.toRadians(angle+90))*length*200)-(mass/30+5), (mass/30+5)*2, (mass/30+5)*2);
		g.setColor(Color.WHITE);
		g.fillOval(300+(int)(Math.cos(Math.toRadians(angle+90))*length*200)-(mass/40+5), (int)(Math.sin(Math.toRadians(angle+90))*length*200)-(mass/40+5), (mass/40+5)*2, (mass/40+5)*2);
		g.setColor(Color.GRAY);
		g.fillOval(300+(int)(Math.cos(Math.toRadians(angle+90))*length*200)-(mass/50+5), (int)(Math.sin(Math.toRadians(angle+90))*length*200)-(mass/50+5), (mass/40+5)*2, (mass/40+5)*2);

		//TEXT
		g.setColor(Color.BLACK);
		g.drawString("Angle (deg): "+Math.floor(angle*100)/100, 10, 480);
		g.drawString("Mass (g): "+mass, 130, 480);
		g.drawString("Length (m): "+Math.floor(length*100)/100, 240, 480);
		if (simulating){
			g.drawString("Simulating...", 400, 480);
			if (!labMode){
				g.drawString("Period: "+Math.floor(2*Math.PI*Math.sqrt(length/9.81)*1000)/1000+" s", 400, 50);
				g.drawString("Total Energy: "+Math.floor((double)(mass)*9.81*100*length*Math.sin(Math.toRadians(maxAngle)))/100000+" J", 430, 90);
				g.drawString("Potential Energy: "+Math.floor((double)(mass)*9.81*10*length*Math.sin(Math.toRadians(Math.abs(angle))))/10000+" J", 430, 110);
				g.drawString("Kinetic Energy: "+(Math.floor((double)(mass)*9.81*10*length*Math.sin(Math.toRadians(maxAngle))-(double)(mass)*9.81*10*length*Math.sin(Math.toRadians(Math.abs(angle))))/10000)+" J", 430, 130);
			}
		}
		else {
			g.drawString("Press ENTER to begin simulation", 400, 480);
			if (!labMode)g.drawString("Press SPACE to enter LAB MODE", 400, 500);
			else g.drawString("Press SPACE to exit LAB MODE", 400, 500);
		}
		if (labMode){
			g.drawString("Period Data: ", 410, 70);
			g.drawString("LAB MODE", 400, 50);
			for (int i = 0; i<times.size()&&i<10; i++){
				g.drawString(""+times.get(i)+" s", 420, 90+i*20);
			}
		}
		//TIMER
		//g.drawString("" + Math.floor(counter/201.6*1000)/1000, 50, 50);
		//PHOTOGATE
		if (labMode){
			g.setColor(new Color(100, 100, 100));
			g.fillRect(295, (int)(length*200+mass/30-5), 10, 30);
			if (!light)g.setColor(Color.RED);
			else g.setColor(Color.GREEN);
			g.fillOval(298, (int)(length*200+mass/30), 4, 4);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_LEFT:
			selector--;
			simulating = false;
			angle = chosenAngle;
			break;
		case KeyEvent.VK_RIGHT:
			selector++;
			simulating = false;
			angle = chosenAngle;
			break;
		case KeyEvent.VK_UP:
			if (selector==1)angle++;
			if (selector==2)mass+=10;
			if (selector==3)length+=.05;
			if (simulating)angle = chosenAngle;
			simulating = false;
			break;
		case KeyEvent.VK_DOWN:
			if (selector==1)angle--;
			if (selector==2)mass-=10;
			if (selector==3)length-=.05;
			if (simulating)angle = chosenAngle;
			simulating = false;
			break;
		case KeyEvent.VK_ENTER:
			simulating^=true;
			if (!simulating)angle = chosenAngle;
			else times.clear();
			//else System.out.println("Period readings:");
			break;
		case KeyEvent.VK_SPACE:
			labMode^=true;
			simulating = false;
			angle = chosenAngle;
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
