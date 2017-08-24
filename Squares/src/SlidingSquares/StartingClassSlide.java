package SlidingSquares;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.Messaging.SyncScopeHelper;

public class StartingClassSlide extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics graphics;
	private Frame frame;
	private Levels stage;
	private int level;
	private Player player;
	private boolean won;
	private int squaresRemaining;
	private double speed;
	private boolean popup;
	private int gameCounter;
	private boolean dead;
	private boolean help;
	
	private boolean simulate;
	private Selector selector;
	
	private boolean testing;
	private String path;
	private ArrayList <BlockStates> moveInfo = new ArrayList <BlockStates> ();
	private boolean initialized = false;
	private boolean noSoln;
	
	private final boolean BUILD_MODE = true;
	
	public void init() {
		setSize(675, 500);	//WINDOW SIZE
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		frame = (Frame) this.getParent().getParent();
		frame.setTitle("SLIDE BOX by Dan Kramp");
		frame.setResizable(false);
	}
	public void start(){		//INITIALIZE EVERYTHING HERE

		if (BUILD_MODE){
			//To begin editing from a preexisting level, change it here. 
			//Level 0 is default for creating.			
			level = 0;
			stage = new Levels(level);
			selector = new Selector((stage.getBoardSize()-.5)*500.0/stage.getBoardSize(), .5*500.0/stage.getBoardSize());
		}
		else {
			//To start playing from a certain level, change it here.
			level = 1;
			try {
				stage = new Levels(level);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Level "+level+" does not exist. \n\nStarting at Level 1 by default.");
				level = 1;
				stage = new Levels(level);
			}
			if (level==1||level==3||level==5||level==10){
				popup = true;
			}
		}
		player = new Player(stage.getPlayerStartX(), stage.getPlayerStartY());
		stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
		
		won = false;
		speed = 100;
		squaresRemaining = 0;
		gameCounter = 0;
		dead = false;
		help = true;
		
		testing = false;
		path = "";
		initialized = false;
		noSoln = false;
				
		Thread thread = new Thread(this);
		thread.start();
	}
	public void reset(){
		
		try {
			stage = new Levels(level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Level "+level+" did not exist.\n\nReturning to Level 1.");
			level = 1;
			won = false;
			popup = true;
			reset();
		}
		player = new Player(stage.getPlayerStartX(), stage.getPlayerStartY());
		won = false;
		stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
		if (level!=5&&level!=3&&level!=1&&level!=10)popup = false;
		dead = false;
		
		moveInfo.clear();
		path = "";
		initialized = false;
		testing = false;
		noSoln = false;
	}
	public void fakeReset(){
		player = new Player(stage.getPlayerStartX(), stage.getPlayerStartY());
		won = false;
		ArrayList<Block> obs = new ArrayList <Block>();
		for (int i = 0; i<stage.getObstacles().size(); i++){
			if (stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("s")||stage.getObstacles().get(i).getType().equals("l")||stage.getObstacles().get(i).getType().equals("t"))obs.add(stage.getObstacles().get(i));
			else if (stage.getObstacles().get(i).getType().equals("p"))obs.add(new Block(stage.getObstacles().get(i).getX(), stage.getObstacles().get(i).getY(), "s"));
		}
		stage.setObstacles(obs);
		stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
		help = false;
		
		moveInfo.clear();
		path = "";
		initialized = false;
		testing = false;
		noSoln = false;
	}
	@Override
	public void run() {		//CONSTANTLY UPDATE or LOOK FOR UPDATES HERE
		while(true){
			
			gameCounter++;
			if (gameCounter>150)gameCounter = 0;
			
			if (!BUILD_MODE||simulate){
				
				stage.setPlayerRadius(250/stage.getBoardSize());
				
			if (player.isMoving()){
				//MOVING UP
				if (player.isMoveUp()){
					for (int i = 0; i<stage.getObstacles().size(); i++){
						if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("l")){
							player.setMoving(false);
							player.setMoveUp(false);
							dead = true;
							break;
						}
						if ((Math.abs((int)(stage.getObstacles().get(i).getY()+500.0/stage.getBoardSize())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&(stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p"))){
							player.setMoving(false);
							player.setMoveUp(false);
							stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
							break;
							//System.out.println("yo");
						}
						if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("s")){
							player.setMoving(false);
							player.setMoveUp(false);
							stage.getObstacles().get(i).setType("p");
							break;
						}
					}
					if ((int)(player.getY())==(int)(250.0/stage.getBoardSize())){
						player.setMoving(false);
						player.setMoveUp(false);
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
					if (player.isMoveUp()){
						for (int z = 0; z<(int)(speed); z++){
							player.setY(player.getY()-500.0/stage.getBoardSize()/speed);
						}
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
				}
				//MOVING DOWN
				if (player.isMoveDown()){
					for (int i = 0; i<stage.getObstacles().size(); i++){
						if ((Math.abs((int)(stage.getObstacles().get(i).getY()-500.0/stage.getBoardSize())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&(stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p"))){
							player.setMoving(false);
							player.setMoveDown(false);
							stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
						}
						else if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("s")){
							player.setMoving(false);
							player.setMoveDown(false);
							stage.getObstacles().get(i).setType("p");
							break;
						}
						if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("l")){
							player.setMoving(false);
							player.setMoveDown(false);
							dead = true;
							break;
						}
					}
					if ((int)(player.getY())==(int)(500.0-250.0/stage.getBoardSize())){
						player.setMoving(false);
						player.setMoveDown(false);
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
					//if (player.isMoveDown())player.setY(player.getY()+1);
					if (player.isMoveDown()){
						for (int z = 0; z<(int)(speed); z++){
							player.setY(player.getY()+(500.0/stage.getBoardSize())/speed);
						}
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
				}
				//MOVING RIGHT
				if (player.isMoveRight()){
					for (int i = 0; i<stage.getObstacles().size(); i++){
						if ((Math.abs((int)(stage.getObstacles().get(i).getX()-500.0/stage.getBoardSize())-(int)(player.getX()))<3&&Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<5)&&(stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p"))){
							player.setMoving(false);
							player.setMoveRight(false);
							stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
						}
						else if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("s")){
							player.setMoving(false);
							player.setMoveRight(false);
							stage.getObstacles().get(i).setType("p");
							break;
						}
						if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("l")){
							player.setMoving(false);
							player.setMoveRight(false);
							dead = true;
							break;
						}
					}
					if ((int)(player.getX())==(int)(500.0-250.0/stage.getBoardSize())){
						player.setMoving(false);
						player.setMoveRight(false);
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
					//if (player.isMoveRight())player.setX(player.getX()+1);
					if (player.isMoveRight()){
						for (int z = 0; z<(int)(speed); z++){
							player.setX(player.getX()+500.0/stage.getBoardSize()/speed);
						}
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
				}
				//MOVING LEFT
				if (player.isMoveLeft()){
					for (int i = 0; i<stage.getObstacles().size(); i++){
						if ((Math.abs((int)(stage.getObstacles().get(i).getX()+500.0/stage.getBoardSize())-(int)(player.getX()))<3&&Math.abs((stage.getObstacles().get(i).getY())-(int)(player.getY()))<5)&&(stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p"))){
							player.setMoving(false);
							player.setMoveLeft(false);
							stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
						}
						else if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("s")){
							player.setMoving(false);
							player.setMoveLeft(false);
							stage.getObstacles().get(i).setType("p");
							break;
						}
						if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("l")){
							player.setMoving(false);
							player.setMoveLeft(false);
							dead = true;
							break;
						}
					}
					if ((int)(player.getX())==(int)(250.0/stage.getBoardSize())){
						player.setMoving(false);
						player.setMoveLeft(false);
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
					//if (player.isMoveLeft())player.setX(player.getX()-1);
					if (player.isMoveLeft()){
						for (int z = 0; z<(int)(speed); z++){
							player.setX(player.getX()-500.0/(stage.getBoardSize())/speed);
						}
						stage.getObstacles().add(new Block(player.getX(), player.getY(), "g"));
					}
				}
			}
			//DUPLICATE DELETER
			for (int i = 0; i<stage.getObstacles().size(); i++){
				for (int a = 0; a<stage.getObstacles().size(); a++){
					if (Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(stage.getObstacles().get(a).getX()))<3&&Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(stage.getObstacles().get(a).getY()))<3&&i!=a){
						if (!stage.getObstacles().get(i).getType().equals("p")&&!stage.getObstacles().get(i).getType().equals("l")&&!stage.getObstacles().get(i).getType().equals("b")&&!stage.getObstacles().get(i).getType().equals("s")){
							stage.getObstacles().remove(i);
							i = 1000;
						}
						break;
					}
				}
			}
			for (int i = 0; i<stage.getObstacles().size(); i++){
				if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("t")){
					for (int a = 0; a<stage.getObstacles().size(); a++){
						if (i!=a&&Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(stage.getObstacles().get(a).getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(stage.getObstacles().get(a).getX()))<3&&stage.getObstacles().get(i).getType().equals("g")){
							stage.getObstacles().remove(a);
						}
					}
					stage.getObstacles().remove(i);
					break;
				}
			}
			for (int i = 0; i<stage.getObstacles().size(); i++){
				if ((Math.abs((int)(stage.getObstacles().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(stage.getObstacles().get(i).getX())-(int)(player.getX()))<5)&&stage.getObstacles().get(i).getType().equals("l")){
					dead = true;
					break;
				}
			}

			squaresRemaining = stage.getBoardSize()*stage.getBoardSize();
			
			for (int i = 0; i<stage.getObstacles().size(); i++){
				if (!stage.getObstacles().get(i).getType().equals("s"))squaresRemaining--;
			}
			//squaresRemaining = stage.getBoardSize()*stage.getBoardSize()-stage.getObstacles().size();
			if (squaresRemaining==0)won = true;
			}
			//LIMITERS
			else{
				if ((int)(selector.getX())>(int)(500-500.0/stage.getBoardSize()/2)){
					selector.setX(500.0-500.0/stage.getBoardSize()/2);
				}
				if ((int)(selector.getX())<(int)(500.0/stage.getBoardSize()/2)){
					selector.setX(500.0/stage.getBoardSize()/2);
				}
				if ((int)(selector.getY())>(500-500.0/stage.getBoardSize()/2)){
					selector.setY(500.0-500.0/stage.getBoardSize()/2);
				}
				if ((int)(selector.getY())<(500.0/stage.getBoardSize()/2)){
					selector.setY(500.0/stage.getBoardSize()/2);
				}
				if ((int)(stage.getPlayerStartX())>(int)(500-500.0/stage.getBoardSize()/2)){
					stage.setPlayerStartX(500.0-500.0/stage.getBoardSize()/2);
				}
				if ((int)(stage.getPlayerStartX())<(int)(500.0/stage.getBoardSize()/2)){
					stage.setPlayerStartX(500.0/stage.getBoardSize()/2);
				}
				if ((int)(stage.getPlayerStartY())>(500-500.0/stage.getBoardSize()/2)){
					stage.setPlayerStartY(500.0-500.0/stage.getBoardSize()/2);
				}
				if ((int)(stage.getPlayerStartY())<(500.0/stage.getBoardSize()/2)){
					stage.setPlayerStartY(500.0/stage.getBoardSize()/2);
				}
				if (squaresRemaining<0)squaresRemaining = 404;
			}
			
			//TEST RUNNER
			if (testing){
				if (!initialized)initializePath();
				else if (squaresRemaining==0){
					System.out.println("\nLevel "+level+" solution: "+path);
					testing = false;
				}
				else if (canMove()&&!dead){
					//System.out.println(path);
					if (path.substring(path.length()-1).equals("U")){
						path = path.substring(0, path.length()-1);
						//System.out.println(path);
						if (canMoveDown()&&!player.isMoving()){
							player.setMoveDown(true);
							player.setMoving(true);
							path+="D";
							ArrayList <Block> prevBlock = new ArrayList <Block>();
							for (int i = 0; i<stage.getObstacles().size(); i++){
								prevBlock.add(i, stage.getObstacles().get(i));
							}
							moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
							initialized = false;
						}
						else if (canMoveLeft()&&!player.isMoving()){
							player.setMoveLeft(true);
							player.setMoving(true);
							path+="L";
							ArrayList <Block> prevBlock = new ArrayList <Block>();
							for (int i = 0; i<stage.getObstacles().size(); i++){
								prevBlock.add(i, stage.getObstacles().get(i));
							}
							moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
							initialized = false;
						}
						else if (canMoveRight()&&!player.isMoving()){
							player.setMoveRight(true);
							player.setMoving(true);
							path+="R";
							ArrayList <Block> prevBlock = new ArrayList <Block>();
							for (int i = 0; i<stage.getObstacles().size(); i++){
								prevBlock.add(i, stage.getObstacles().get(i));
							}
							moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
							initialized = false;
						}
						else moveBack();
					}
					else if (path.substring(path.length()-1).equals("D")){
						path = path.substring(0, path.length()-1);
						//System.out.println(path);
						if (canMoveLeft()&&!player.isMoving()){
							player.setMoveLeft(true);
							player.setMoving(true);
							path+="L";
							ArrayList <Block> prevBlock = new ArrayList <Block>();
							for (int i = 0; i<stage.getObstacles().size(); i++){
								prevBlock.add(i, stage.getObstacles().get(i));
							}
							moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
							initialized = false;
						}
						else if (canMoveRight()&&!player.isMoving()){
							player.setMoveRight(true);
							player.setMoving(true);
							path+="R";
							ArrayList <Block> prevBlock = new ArrayList <Block>();
							for (int i = 0; i<stage.getObstacles().size(); i++){
								prevBlock.add(i, stage.getObstacles().get(i));
							}
							moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
							initialized = false;
						}
						else moveBack();
					}
					else if (path.substring(path.length()-1).equals("L")){
						path = path.substring(0, path.length()-1);
						//System.out.println(path);
						if (canMoveRight()&&!player.isMoving()){
							player.setMoveRight(true);
							player.setMoving(true);
							path+="R";
							ArrayList <Block> prevBlock = new ArrayList <Block>();
							for (int i = 0; i<stage.getObstacles().size(); i++){
								prevBlock.add(i, stage.getObstacles().get(i));
							}
							moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
							initialized = false;
						}
						else moveBack();
					}
					else if (path.substring(path.length()-1).equals("R")){
						path = path.substring(0, path.length()-1);
						moveBack();
					}
					else moveBack();
				}
				else moveBack();
			}
			
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void moveBack(){
		//path = path.substring(0, path.length()-1);
		if (moveInfo.size()!=0){
			stage.getObstacles().clear();
			for (int i = 0; i<moveInfo.get(moveInfo.size()-1).getPrevBlock().size(); i++){
				if ((Math.abs((int)(moveInfo.get(moveInfo.size()-1).getPrevBlock().get(i).getY())-(int)(player.getY()))<3&&Math.abs((int)(moveInfo.get(moveInfo.size()-1).getPrevBlock().get(i).getX())-(int)(player.getX()))<5)&&moveInfo.get(moveInfo.size()-1).getPrevBlock().get(i).getType().equals("p")){
					moveInfo.get(moveInfo.size()-1).getPrevBlock().get(i).setType("s");
				}
			}
			stage.setObstacles(moveInfo.get(moveInfo.size()-1).getPrevBlock());
			player.setX(moveInfo.get(moveInfo.size()-1).getLastX());
			player.setY(moveInfo.get(moveInfo.size()-1).getLastY());
			moveInfo.remove(moveInfo.size()-1);
		}
		else {
			testing = false;
			noSoln = true;
		}
		dead = false;
		//System.out.println(path);
	}
	public void initializePath(){
		if (canMove()&&!dead){
			if (canMoveUp()&&!player.isMoving()){
				player.setMoveUp(true);
				player.setMoving(true);
				path+="U";
				ArrayList <Block> prevBlock = new ArrayList <Block>();
				for (int i = 0; i<stage.getObstacles().size(); i++){
					prevBlock.add(stage.getObstacles().get(i));
				}
				moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
			}
			else if (canMoveDown()&&!player.isMoving()){
				player.setMoveDown(true);
				player.setMoving(true);
				path+="D";
				ArrayList <Block> prevBlock = new ArrayList <Block>();
				for (int i = 0; i<stage.getObstacles().size(); i++){
					prevBlock.add(stage.getObstacles().get(i));
				}
				moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
			}
			else if (canMoveLeft()&&!player.isMoving()){
				player.setMoveLeft(true);
				player.setMoving(true);
				path+="L";
				ArrayList <Block> prevBlock = new ArrayList <Block>();
				for (int i = 0; i<stage.getObstacles().size(); i++){
					prevBlock.add(stage.getObstacles().get(i));
				}
				moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
			}
			else if (canMoveRight()&&!player.isMoving()){
				player.setMoveRight(true);
				player.setMoving(true);
				path+="R";
				ArrayList <Block> prevBlock = new ArrayList <Block>();
				for (int i = 0; i<stage.getObstacles().size(); i++){
					prevBlock.add(stage.getObstacles().get(i));
				}
				moveInfo.add(new BlockStates(player.getX(), player.getY(), prevBlock, path));
			}
		}
		else initialized = true;
	}
	public boolean canMove(){
		//System.out.println("Up: "+canMoveUp()+"\nDown: "+canMoveDown()+"\nRight: "+canMoveRight()+"\nLeft: "+canMoveLeft());
		if (!canMoveUp()&&!canMoveDown()&&!canMoveLeft()&&!canMoveRight())return false;
		else return true;
	}
	public boolean canMoveUp(){
		for (int i = 0; i<stage.getObstacles().size(); i++){
			if (stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p")||stage.getObstacles().get(i).getType().equals("l")){
				if ((Math.abs((int)(stage.getObstacles().get(i).getY()+500.0/stage.getBoardSize())-(int)(player.getY()))<3&&Math.abs((stage.getObstacles().get(i).getX())-(int)(player.getX()))<5))return false;
			}
		}
		if ((int)(player.getY())==(int)(250.0/stage.getBoardSize()))return false;
		return true;
	}
	public boolean canMoveDown(){
		for (int i = 0; i<stage.getObstacles().size(); i++){
			if (stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p")||stage.getObstacles().get(i).getType().equals("l")){
				if ((Math.abs((int)(stage.getObstacles().get(i).getY()-500.0/stage.getBoardSize())-(int)(player.getY()))<3&&Math.abs((stage.getObstacles().get(i).getX())-(int)(player.getX()))<5))return false;
			}
		}
		if ((int)(player.getY())==(int)(500.0-250.0/stage.getBoardSize()))return false;
		return true;
	}
	public boolean canMoveLeft(){
		for (int i = 0; i<stage.getObstacles().size(); i++){
			if (stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p")||stage.getObstacles().get(i).getType().equals("l")){
				if ((Math.abs((int)(stage.getObstacles().get(i).getX()+500.0/stage.getBoardSize())-(int)(player.getX()))<3&&Math.abs((stage.getObstacles().get(i).getY())-(int)(player.getY()))<5))return false;
			}
		}
		if ((int)(player.getX())==(int)(250.0/stage.getBoardSize()))return false;
		return true;
	}
	public boolean canMoveRight(){
		for (int i = 0; i<stage.getObstacles().size(); i++){
			if (stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("b")||stage.getObstacles().get(i).getType().equals("p")||stage.getObstacles().get(i).getType().equals("l")){
				if ((Math.abs((int)(stage.getObstacles().get(i).getX()-500.0/stage.getBoardSize())-(int)(player.getX()))<3&&Math.abs((stage.getObstacles().get(i).getY())-(int)(player.getY()))<5))return false;
			}
		}
		if ((int)(player.getX())==(int)(500.0-250.0/stage.getBoardSize()))return false;
		return true;
	}
	public void update(Graphics g) {		//INITIALIZES GRAPHICS - DONT TOUCH except bg color
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		graphics.setColor(Color.lightGray);		//BACKGROUND COLOR
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(getForeground());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {			//ALL OTHER GRAPHICS
		//BOARD
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 500, 500);
		
		//OBSTACLES
		for (int i = 0; i<stage.getObstacles().size(); i++){
			if (stage.getObstacles().get(i).getX()<500&&stage.getObstacles().get(i).getY()<500){
				if (stage.getObstacles().get(i).getType().equals("g")||stage.getObstacles().get(i).getType().equals("p")||stage.getObstacles().get(i).getType().equals("l")||stage.getObstacles().get(i).getType().equals("t")){
					if (!dead||won)g.setColor(new Color(180, 180, 180));
					else g.setColor(new Color(255, 80, 80));
					if (stage.getObstacles().get(i).getType().equals("l"))g.setColor(new Color(255, (int)(90*Math.cos(gameCounter/75.0*Math.PI))+90, 0));
					if (stage.getObstacles().get(i).getType().equals("t"))g.setColor(Color.GREEN);
					g.fillRect((int)(stage.getObstacles().get(i).getX()-250.0/stage.getBoardSize()+1), (int)(stage.getObstacles().get(i).getY()-250.0/stage.getBoardSize()+1), 500/stage.getBoardSize(), 500/stage.getBoardSize());
				}
				if (stage.getObstacles().get(i).getType().equals("b")){
					g.setColor(Color.BLACK);
					g.fillRect((int)(stage.getObstacles().get(i).getX()-250.0/stage.getBoardSize()+1), (int)(stage.getObstacles().get(i).getY()-250.0/stage.getBoardSize()+1), 500/stage.getBoardSize()-1, 500/stage.getBoardSize()-1);
					g.setColor(new Color(220, 220, 220));
					g.fillRect((int)(stage.getObstacles().get(i).getX()-250.0/stage.getBoardSize())+5, (int)(stage.getObstacles().get(i).getY()-250.0/stage.getBoardSize())+8, 500/stage.getBoardSize()-10, 5);
				}
				if (stage.getObstacles().get(i).getType().equals("s")||stage.getObstacles().get(i).getType().equals("p")){
					g.setColor(Color.ORANGE);
					g.fillRect((int)(stage.getObstacles().get(i).getX()-150.0/stage.getBoardSize()+1), (int)(stage.getObstacles().get(i).getY()-150.0/stage.getBoardSize()+1), 300/stage.getBoardSize()-1, 300/stage.getBoardSize()-1);
					Graphics2D gg = (Graphics2D) g.create();
					gg.rotate(Math.PI/4, (int)(stage.getObstacles().get(i).getX()), (int)(stage.getObstacles().get(i).getY()));
					gg.fillRect((int)(stage.getObstacles().get(i).getX()-150.0/stage.getBoardSize()+1), (int)(stage.getObstacles().get(i).getY()-150.0/stage.getBoardSize()+1), 300/stage.getBoardSize()-1, 300/stage.getBoardSize()-1);
					gg.dispose();
				}		
			}
		}
		
		
		if (!BUILD_MODE||simulate){
		//PLAYER
			g.setColor(new Color(150, 150-(int)(150.0*(squaresRemaining)/(stage.getBoardSize()*stage.getBoardSize())), 0));
			g.fillOval((int)(player.getX()-stage.getPlayerRadius()), (int)(player.getY()-stage.getPlayerRadius()), stage.getPlayerRadius()*2, stage.getPlayerRadius()*2);
			g.setColor(new Color(255, 255-(int)(255.0*(squaresRemaining)/(stage.getBoardSize()*stage.getBoardSize())), 0));
			g.fillOval((int)(player.getX()-stage.getPlayerRadius()), (int)(player.getY()-stage.getPlayerRadius()), stage.getPlayerRadius()*2-3, stage.getPlayerRadius()*2-5);
		
		//TEXT	
			g.setFont(new Font("Dialog", 30, 30));
			g.setColor(new Color(255, 255, 150));
			g.drawString(""+level, 530, 120);
			g.drawString(""+stage.getBoardSize()+"x"+stage.getBoardSize(), 530, 190);
			g.drawString(""+squaresRemaining, 530, 280);
			g.drawString("Arrow Keys", 515, 370);
			g.drawString("ENTER", 530, 460);
			g.setFont(new Font("Dialog", 25, 25));
			g.setColor(Color.WHITE);
			g.drawString("Level: ", 510, 80);
			g.drawString("Board Size:", 510, 150);
			g.drawString("Squares Left: ", 510, 240);
			g.drawString("Move Player:", 510, 330);
			g.drawString("Restart Level:", 510, 420);
			if (!simulate){
				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.setColor(new Color(240, 240, 240));
				g.drawString("SLIDE BOX", 504, 33);
				g.setColor(new Color(120, 120, 120));
				g.drawString("SLIDE BOX", 506, 37);
				g.setColor(Color.BLACK);
				g.drawString("SLIDE BOX", 505, 35);
			}
		}
		else {
			g.setColor(new Color(255, 90, 90, 150));
			g.fillRect((int)(stage.getPlayerStartX()-250.0/stage.getBoardSize()+1), (int)(stage.getPlayerStartY()-250.0/stage.getBoardSize()+1), (int)(500.0/stage.getBoardSize()), (int)(500.0/stage.getBoardSize()));
			g.setColor(new Color(230, 230, 230));
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("BUILD MODE", 522, 27);
			g.setColor(new Color(150, 150, 150));
			g.drawString("BUILD MODE", 524, 33);
			g.setColor(Color.BLACK);
			g.drawString("BUILD MODE", 523, 30);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", 25, 25));
			g.drawString("Board Size:", 510, 70);
			g.drawString("Change Size:", 510, 130);
			g.drawString("Move Player:", 510, 190);
			g.drawString("Move Select:", 510, 250);
			g.drawString("Simulate:", 510, 310);
			g.drawString("Toggle Block:", 510, 370);
			g.drawString("Extra Help:", 510, 430);
			g.setFont(new Font("Dialog", 30, 30));
			g.setColor(new Color(255, 255, 150));
			g.drawString(""+stage.getBoardSize()+"X"+stage.getBoardSize(), 530, 100);
			g.drawString("+/-", 530, 160);
			g.drawString("WASD", 530, 220);
			g.drawString("Arrow Keys", 515, 280);
			g.drawString("ENTER", 530, 340);
			g.drawString("SPACE", 530, 400);
			g.drawString("H", 530, 460);
			g.setColor(new Color(255, 255, 0, 150));
			g.fillRect((int)(selector.getX()-250.0/stage.getBoardSize())+1, (int)(selector.getY()-250.0/stage.getBoardSize())+1, (int)(500.0/stage.getBoardSize()), (int)(500.0/stage.getBoardSize()));
		}
		if (simulate){
			g.setColor(new Color(230, 230, 230));
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("SIMULATING", 523, 28);
			g.setColor(new Color(150, 150, 150));
			g.drawString("SIMULATING", 523, 32);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Dialog", 20, 20));
			g.drawString("SIMULATING", 523, 30);
		}
		//GRID
		g.setColor(Color.YELLOW);
		for (int i = 0; i<stage.getBoardSize()+1; i++){
			g.drawLine(i*500/stage.getBoardSize(), 0, i*500/stage.getBoardSize(), 500);
			g.drawLine(0, i*500/stage.getBoardSize(), 500, i*500/stage.getBoardSize());
		}
		g.setFont(new Font("Dialog", Font.BOLD, 100));
		if (won){
			g.setColor(Color.BLACK);
			g.drawString("You win!", 55, 264);
			g.setColor(Color.WHITE);
			g.drawString("You win!", 55, 260);
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString("Press ENTER to advance", 150, 303);
			g.setColor(Color.WHITE);
			g.drawString("Press ENTER to advance", 150, 300);
		}
		else if (dead){
			g.setColor(Color.BLACK);
			g.drawString("You died.", 55, 264);
			g.setColor(Color.WHITE);
			g.drawString("You died.", 55, 260);
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString("Press ENTER to restart", 150, 303);
			g.setColor(Color.WHITE);
			g.drawString("Press ENTER to restart", 150, 300);
		}
		if (noSoln){
			g.setColor(new Color(220, 220, 220, 240));
			g.fillRect(45, 217, 530, 60);
			g.setFont(new Font("Dialog", Font.BOLD, 40));
			g.setColor(new Color(240, 240, 240));
			g.drawString("No solution from this point.", 55, 258);
			g.setColor(new Color(120, 120, 120));
			g.drawString("No solution from this point.", 55, 262);

			g.setColor(Color.BLACK);
			g.drawString("No solution from this point.", 55, 260);
		}
		//CREDITS
		g.setFont(new Font("Dialog", 15, 15));
		g.setColor(Color.BLACK);
		g.drawString("Created by Dan Kramp", 510, 491);
		g.setColor(Color.YELLOW);
		g.drawString("Created by Dan Kramp", 510, 490);
		//POPUP
		if (popup){
			if (level==1){
				g.setColor(new Color(230, 230, 230, 240));
				g.fillRect(50, 150, 400, 200);
				g.setFont(new Font("Dialog", Font.BOLD, 20));
				g.setColor(Color.BLACK);
				g.drawString("Welcome to", 190, 175);
				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.setColor(new Color(240, 240, 240));
				g.drawString("SLIDE BOX", 164, 203);
				g.setColor(new Color(120, 120, 120));
				g.drawString("SLIDE BOX", 166, 207);
				g.setColor(Color.BLACK);
				g.drawString("SLIDE BOX", 165, 205);
				g.setFont(new Font("Dialog", 20, 20));
				g.drawString("In this game, you will move the red circle", 72, 235);
				g.drawString("around the board with the arrow keys.", 80, 255);
				g.drawString("You must touch every square but you cannot", 57, 275);
				g.drawString("cross your own path. Careful, it's slippery!", 66, 295);

				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.drawString("Press ENTER to continue", 75, 333);
			}
			if (level==3){
				g.setColor(new Color(230, 230, 230, 240));
				g.fillRect(50, 150, 400, 200);
				g.setFont(new Font("Dialog", Font.BOLD, 20));
				g.setColor(Color.BLACK);
				g.drawString("There's a new block!", 100, 190);
				g.setFont(new Font("Dialog", 20, 20));
				g.drawString("This one will stop you in place", 176, 245);
				g.drawString("when you collide with it.", 180, 265);
				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.drawString("Press ENTER to continue", 75, 333);
				
				g.setColor(Color.BLACK);
				g.fillRect(80, 205, 80, 80);
				g.setColor(new Color(220, 220, 220));
				g.fillRect(83, 210, 74, 4);
			}
			if (level==5){
				g.setColor(new Color(230, 230, 230, 240));
				g.fillRect(50, 150, 400, 200);
				g.setFont(new Font("Dialog", Font.BOLD, 20));
				g.setColor(Color.BLACK);
				g.drawString("There's a new block!", 100, 190);
				g.setFont(new Font("Dialog", 20, 20));
				g.drawString("This one will stop you in place", 180, 230);
				g.drawString("when you pass over it.", 180, 250);
				g.drawString("You must pass over it to win.", 180, 280);
				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.drawString("Press ENTER to continue", 75, 333);

				g.setColor(Color.ORANGE);
				g.fillRect(100, 220, 50, 50);
				Graphics2D gg = (Graphics2D) g.create();
				gg.rotate(Math.PI/4, 125, 245);
				gg.fillRect(100, 220, 50, 50);
				gg.dispose();
			}
			if (level==10){
				g.setColor(new Color(230, 230, 230, 240));
				g.fillRect(50, 150, 400, 200);
				g.setFont(new Font("Dialog", Font.BOLD, 20));
				g.setColor(Color.BLACK);
				g.drawString("There's a new block!", 100, 190);
				g.setFont(new Font("Dialog", 20, 20));
				g.drawString("This one will kill you dead", 176, 230);
				g.drawString("when you pass over it.", 180, 250);
				g.drawString("You may end on it and still win.", 170, 280);
				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.drawString("Press ENTER to continue", 75, 333);
				
				g.setColor(new Color(255, (int)(90*Math.cos(gameCounter/75.0*Math.PI))+90, 0));
				g.fillRect(80, 205, 80, 80);
			}
		}
		if (help&&BUILD_MODE){
			g.setColor(new Color(230, 230, 230, 240));
			g.fillRect(50, 150, 400, 200);
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString("Extra Help Menu", 175, 175);
			g.setFont(new Font("Dialog", 18, 18));
			g.drawString("Press           to clear the board of all features.", 60, 220);
			g.drawString("Press           to delete the selected feature.", 60, 250);
			g.drawString("Press     to print the code for the level.", 60, 280);
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("ESC", 113, 220);
			g.drawString("DEL", 113, 250);
			g.drawString("P", 113, 280);

			g.setFont(new Font("Dialog", Font.BOLD, 25));
			g.drawString("Press H to exit menu", 125, 338);
		}
		for (int i = 0; i<moveInfo.size(); i++){
			g.setColor(Color.BLUE);
			//g.fillRect((int)(moveInfo.get(i).getLastX()-250.0/stage.getBoardSize()+1), (int)(moveInfo.get(i).getLastY()-250.0/stage.getBoardSize()+1), 500/stage.getBoardSize(), 500/stage.getBoardSize());
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.BOLD, 30));
			//System.out.println(path.length());
			if (i<path.length())g.drawString(""+path.substring(i, i+1), (int)moveInfo.get(i).getLastX(), (int)moveInfo.get(i).getLastY());
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {		//KEY PRESSED EVENT
		switch (arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			if (!player.isMoving()&&(!BUILD_MODE||simulate)&&!testing){
				if (!popup&&!dead){
					player.setMoving(true);
					player.setMoveUp(true);
				}
			}
			else {
				selector.setY(selector.getY()-500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_DOWN:
			if (!player.isMoving()&&(!BUILD_MODE||simulate)&&!testing){
				if (!popup&&!dead){
					player.setMoving(true);
					player.setMoveDown(true);
				}
			}
			else {
				selector.setY(selector.getY()+500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_RIGHT:
			if (!player.isMoving()&&(!BUILD_MODE||simulate)&&!testing){
				if (!popup&&!dead){
					player.setMoving(true);
					player.setMoveRight(true);
				}
			}
			else {
				selector.setX(selector.getX()+500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_LEFT:
			if (!player.isMoving()&&(!BUILD_MODE||simulate)&&!testing){
				if (!popup&&!dead){
					player.setMoving(true);
					player.setMoveLeft(true);
				}
			}
			else {
				selector.setX(selector.getX()-500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_ENTER:
			if (!BUILD_MODE){
				if (!won){
					reset();
					popup = false;
				}
				if (won){
					level++;
					won = false;
					popup = true;
					reset();
				}
			}
			else {
				dead = false;
				fakeReset();
				simulate^=true;
			}
			noSoln = false;
			break;
		case KeyEvent.VK_EQUALS:
			if (BUILD_MODE&&stage.getBoardSize()<12&&!simulate){
				stage.setBoardSize(stage.getBoardSize()+1);			
				selector.setX(selector.getX()/stage.getBoardSize()*(stage.getBoardSize()-1));
				selector.setY(selector.getY()/stage.getBoardSize()*(stage.getBoardSize()-1));
				
				stage.setPlayerStartX(stage.getPlayerStartX()/stage.getBoardSize()*(stage.getBoardSize()-1));
				stage.setPlayerStartY(stage.getPlayerStartY()/stage.getBoardSize()*(stage.getBoardSize()-1));
				for (int i = 0; i<stage.getObstacles().size(); i++){
					stage.getObstacles().get(i).setX(stage.getObstacles().get(i).getX()/stage.getBoardSize()*(stage.getBoardSize()-1));
					stage.getObstacles().get(i).setY(stage.getObstacles().get(i).getY()/stage.getBoardSize()*(stage.getBoardSize()-1));
				}
			}
			noSoln = false;
			break;
		case KeyEvent.VK_MINUS:
			if (BUILD_MODE&&stage.getBoardSize()>4&&!simulate){
				stage.setBoardSize(stage.getBoardSize()-1);			
				selector.setX(selector.getX()/stage.getBoardSize()*(stage.getBoardSize()+1));
				selector.setY(selector.getY()/stage.getBoardSize()*(stage.getBoardSize()+1));
				
				stage.setPlayerStartX(stage.getPlayerStartX()/stage.getBoardSize()*(stage.getBoardSize()+1));
				stage.setPlayerStartY(stage.getPlayerStartY()/stage.getBoardSize()*(stage.getBoardSize()+1));
				for (int i = 0; i<stage.getObstacles().size(); i++){
					stage.getObstacles().get(i).setX(stage.getObstacles().get(i).getX()/stage.getBoardSize()*(stage.getBoardSize()+1));
					stage.getObstacles().get(i).setY(stage.getObstacles().get(i).getY()/stage.getBoardSize()*(stage.getBoardSize()+1));
				}
			}
			noSoln = false;
			break;
		case KeyEvent.VK_A:
			if (BUILD_MODE&&!simulate){
				stage.setPlayerStartX(stage.getPlayerStartX()-500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_D:
			if (BUILD_MODE&&!simulate){
				stage.setPlayerStartX(stage.getPlayerStartX()+500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;	
		case KeyEvent.VK_W:
			if (BUILD_MODE&&!simulate){
				stage.setPlayerStartY(stage.getPlayerStartY()-500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_S:
			if (BUILD_MODE&&!simulate){
				stage.setPlayerStartY(stage.getPlayerStartY()+500.0/stage.getBoardSize());
			}
			noSoln = false;
			break;
		case KeyEvent.VK_SPACE:
			if (BUILD_MODE&&!simulate){
				boolean removed = false;
				for (int i = 0; i<stage.getObstacles().size(); i++){
					if (stage.getObstacles().get(i).getType().equals("b")&&Math.abs((int)(selector.getX())-(int)(stage.getObstacles().get(i).getX()))<3&&Math.abs((int)(selector.getY())-(int)(stage.getObstacles().get(i).getY()))<3){
						stage.getObstacles().get(i).setType("s");
						removed = true;
					}
					else if (stage.getObstacles().get(i).getType().equals("s")&&Math.abs((int)(selector.getX())-(int)(stage.getObstacles().get(i).getX()))<3&&Math.abs((int)(selector.getY())-(int)(stage.getObstacles().get(i).getY()))<3){
						stage.getObstacles().get(i).setType("l");
						removed = true;
					}
					else if (stage.getObstacles().get(i).getType().equals("l")&&Math.abs((int)(selector.getX())-(int)(stage.getObstacles().get(i).getX()))<3&&Math.abs((int)(selector.getY())-(int)(stage.getObstacles().get(i).getY()))<3){
						stage.getObstacles().get(i).setType("t");
						removed = true;
					}
					else if (stage.getObstacles().get(i).getType().equals("t")&&Math.abs((int)(selector.getX())-(int)(stage.getObstacles().get(i).getX()))<3&&Math.abs((int)(selector.getY())-(int)(stage.getObstacles().get(i).getY()))<3){
						stage.getObstacles().remove(i);
						removed = true;
					}
				}
				if (!removed){
					stage.getObstacles().add(new Block(selector.getX(), selector.getY(), "b"));
					//System.out.println("Block placed.");
				}
			}
			noSoln = false;
			break;
		case KeyEvent.VK_P:
			if (BUILD_MODE&&!simulate){
				System.out.println("\n//Start of code");
				System.out.println("boardSize = "+stage.getBoardSize()+";");
				System.out.println("playerStartX = "+(int)((stage.getPlayerStartX())/499.5*stage.getBoardSize())+".5*500.0/boardSize;");
				System.out.println("playerStartY = "+(int)(stage.getPlayerStartY()/499.5*stage.getBoardSize())+".5*500.0/boardSize;");
				for (int i = 0; i<stage.getObstacles().size(); i++){
					if (stage.getObstacles().get(i).getType().equals("b"))System.out.println("obstacles.add(new Block("+(int)(stage.getObstacles().get(i).getX()/500.0*stage.getBoardSize())+".5*500.0/boardSize, "+(int)(stage.getObstacles().get(i).getY()/500.0*stage.getBoardSize())+".5*500.0/boardSize, \"b\"));");
					else if (stage.getObstacles().get(i).getType().equals("s"))System.out.println("obstacles.add(new Block("+(int)(stage.getObstacles().get(i).getX()/500.0*stage.getBoardSize())+".5*500.0/boardSize, "+(int)(stage.getObstacles().get(i).getY()/500.0*stage.getBoardSize())+".5*500.0/boardSize, \"s\"));");
				}
			}
			noSoln = false;
			break;
		case KeyEvent.VK_ESCAPE:
			if (BUILD_MODE&&!simulate)stage.getObstacles().clear();
			break;
		case KeyEvent.VK_DELETE:
			for (int i = 0; i<stage.getObstacles().size(); i++){
				if (Math.abs((int)(selector.getX())-(int)(stage.getObstacles().get(i).getX()))<3&&Math.abs((int)(selector.getY())-(int)(stage.getObstacles().get(i).getY()))<3){
					stage.getObstacles().remove(i);
					break;
				}
			}
			noSoln = false;
			break;
		case KeyEvent.VK_H:
			if (BUILD_MODE&&!simulate){
				help^=true;
			}
			noSoln = false;
			break;
		case KeyEvent.VK_F5:
			if (!testing&&!popup){
				testing = true;
				simulate = true;
			}
			noSoln = false;
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
