package MirrorGame;

import java.util.ArrayList;

public class Level {
	
	private int level;
	private ArrayList <Mirror> mirrors = new ArrayList<Mirror>();
	private ArrayList <Block> blocks = new ArrayList <Block>();
	private ArrayList <Mandatory> mandatories = new ArrayList <Mandatory>();
	private ArrayList <OneSidedMirror> oneSided = new ArrayList <OneSidedMirror>();
	private int mirrorSupply;
	private int targetX;
	private int targetY;
	
	public Level(int l){
		level = l;
		if (level == 1){
			mirrors.add(new Mirror(275, 275, true));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 225;
			
		}
		if (level == 2){
			mirrors.add(new Mirror(425, 425, false));
			mirrors.add(new Mirror(425, 175, true));
			mirrors.add(new Mirror(125, 175, false));
			mirrorSupply = 1;
			targetX = 25;
			targetY = 425;

		}
		if (level == 3){
			blocks.add(new Block(225, 375));
			blocks.add(new Block(175, 375));
			blocks.add(new Block(125, 375));
			blocks.add(new Block(75, 375));
			blocks.add(new Block(75, 475));
			blocks.add(new Block(125, 475));
			blocks.add(new Block(175, 475));
			blocks.add(new Block(225, 475));
			blocks.add(new Block(275, 475));
			blocks.add(new Block(325, 475));
			blocks.add(new Block(375, 475));
			blocks.add(new Block(425, 475));
			blocks.add(new Block(475, 475));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(475, 375));
			blocks.add(new Block(525, 375));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(525, 325));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(525, 175));
			blocks.add(new Block(525, 125));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(475, 75));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(475, 175));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(375, 375));
			blocks.add(new Block(325, 375));
			blocks.add(new Block(275, 375));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(75, 225));
			blocks.add(new Block(75, 175));
			blocks.add(new Block(75, 125));
			blocks.add(new Block(75, 75));
			blocks.add(new Block(125, 75));
			blocks.add(new Block(175, 75));
			blocks.add(new Block(225, 75));
			blocks.add(new Block(275, 75));
			blocks.add(new Block(325, 75));
			blocks.add(new Block(375, 75));
			blocks.add(new Block(425, 75));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(375, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(275, 125));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(125, 175));
			blocks.add(new Block(175, 175));
			blocks.add(new Block(225, 175));
			blocks.add(new Block(275, 175));
			blocks.add(new Block(325, 175));
			blocks.add(new Block(375, 175));
			blocks.add(new Block(425, 175));
			blocks.add(new Block(75, 275));
			blocks.add(new Block(125, 275));
			blocks.add(new Block(175, 275));
			blocks.add(new Block(225, 275));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(75, 325));
			mirrors.add(new Mirror(475, 325, false));
			mirrors.add(new Mirror(425, 275, true));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 425;

		}
		if (level == 4){
			blocks.add(new Block(525, 225));
			blocks.add(new Block(525, 175));
			blocks.add(new Block(525, 125));
			blocks.add(new Block(475, 75));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(475, 175));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(525, 325));
			blocks.add(new Block(475, 325));
			blocks.add(new Block(475, 375));
			blocks.add(new Block(525, 375));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(475, 475));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(425, 475));
			blocks.add(new Block(375, 475));
			blocks.add(new Block(325, 475));
			blocks.add(new Block(275, 475));
			blocks.add(new Block(225, 475));
			blocks.add(new Block(175, 475));
			blocks.add(new Block(125, 475));
			blocks.add(new Block(75, 475));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(275, 375));
			blocks.add(new Block(225, 375));
			blocks.add(new Block(175, 375));
			blocks.add(new Block(125, 375));
			blocks.add(new Block(75, 375));
			blocks.add(new Block(75, 325));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(375, 375));
			blocks.add(new Block(375, 325));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(375, 175));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(75, 225));
			blocks.add(new Block(75, 175));
			blocks.add(new Block(125, 175));
			blocks.add(new Block(175, 175));
			blocks.add(new Block(275, 175));
			blocks.add(new Block(175, 275));
			blocks.add(new Block(125, 275));
			blocks.add(new Block(75, 275));
			blocks.add(new Block(325, 175));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(175, 75));
			blocks.add(new Block(125, 75));
			blocks.add(new Block(75, 75));
			blocks.add(new Block(75, 125));
			blocks.add(new Block(125, 125));
			mirrors.add(new Mirror(425, 425, false));
			mirrors.add(new Mirror(425, 125, true));
			mirrors.add(new Mirror(425, 275, true));
			mirrors.add(new Mirror(225, 75, false));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 425;

		}
		if (level == 5){
			blocks.add(new Block(75, 225));
			blocks.add(new Block(75, 325));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(275, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(425, 175));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(475, 175));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(475, 375));
			blocks.add(new Block(475, 325));
			blocks.add(new Block(425, 325));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(175, 425));
			blocks.add(new Block(125, 425));
			mirrors.add(new Mirror(375, 275, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 275;
			blocks.add(new Block(75, 225));
			blocks.add(new Block(75, 325));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(275, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(425, 175));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(475, 175));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(475, 375));
			blocks.add(new Block(475, 325));
			blocks.add(new Block(425, 325));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(175, 425));
			blocks.add(new Block(125, 425));
			mirrors.add(new Mirror(375, 275, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 275;



		}
		if (level ==6 ){
			blocks.add(new Block(375, 225));
			blocks.add(new Block(425, 175));
			blocks.add(new Block(375, 175));
			blocks.add(new Block(375, 125));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(375, 325));
			blocks.add(new Block(375, 375));
			blocks.add(new Block(375, 425));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(225, 375));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(175, 425));
			blocks.add(new Block(125, 425));
			blocks.add(new Block(175, 375));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(225, 175));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(175, 175));
			mirrors.add(new Mirror(75, 275, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 225, false));
			mirrors.add(new Mirror(325, 275, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 325, false));
			mirrors.add(new Mirror(525, 325, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrorSupply = 1;
			targetX = 325;
			targetY = 525;

		}
		if (level == 7){
			blocks.add(new Block(525, 325));
			blocks.add(new Block(425, 325));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(525, 125));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(125, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(325, 75));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(525, 175));
			blocks.add(new Block(525, 475));
			mirrors.add(new Mirror(375, 375, false));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(475, 275, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(375, 175, false));
			mirrors.add(new Mirror(375, 75, false));
			mirrors.add(new Mirror(275, 75, true));
			mirrorSupply = 2;
			targetX = 575;
			targetY = 375;

		}
		if (level == 100){
			mirrorSupply = 500;
			targetX = 25;
			targetY = 275;
		}
		if (level==8){
			blocks.add(new Block(125, 175));
			blocks.add(new Block(475, 175));
			blocks.add(new Block(475, 375));
			blocks.add(new Block(125, 375));
			blocks.add(new Block(275, 275));
			blocks.add(new Block(325, 275));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(275, 225));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(225, 475, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(425, 475, false));
			mirrors.add(new Mirror(475, 475, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(475, 75, false));
			mirrors.add(new Mirror(425, 75, true));
			mirrors.add(new Mirror(375, 75, false));
			mirrors.add(new Mirror(325, 75, true));
			mirrors.add(new Mirror(275, 75, false));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(175, 75, false));
			mirrors.add(new Mirror(125, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(425, 425, true));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 425;


		}
		if (level==9)
		{
			mandatories.add(new Mandatory(275, 175, false));
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(425, 275, false));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 125;
		}
		if (level==10){
			mirrors.add(new Mirror(275, 475, false));
			mirrors.add(new Mirror(325, 475, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(275, 75, true));
			mirrors.add(new Mirror(325, 75, false));
			mirrors.add(new Mirror(525, 475, false));
			mandatories.add(new Mandatory(375, 75, false));
			mandatories.add(new Mandatory(425, 75, false));
			mandatories.add(new Mandatory(475, 75, false));
			mandatories.add(new Mandatory(525, 125, false));
			mandatories.add(new Mandatory(525, 175, false));
			mandatories.add(new Mandatory(525, 225, false));
			mandatories.add(new Mandatory(525, 275, false));
			mandatories.add(new Mandatory(525, 325, false));
			mandatories.add(new Mandatory(525, 375, false));
			mandatories.add(new Mandatory(525, 425, false));
			mandatories.add(new Mandatory(475, 475, false));
			mandatories.add(new Mandatory(425, 475, false));
			mandatories.add(new Mandatory(375, 475, false));
			mandatories.add(new Mandatory(225, 475, false));
			mandatories.add(new Mandatory(175, 475, false));
			mandatories.add(new Mandatory(125, 475, false));
			mandatories.add(new Mandatory(75, 425, false));
			mandatories.add(new Mandatory(75, 375, false));
			mandatories.add(new Mandatory(75, 325, false));
			mandatories.add(new Mandatory(75, 275, false));
			mandatories.add(new Mandatory(75, 225, false));
			mandatories.add(new Mandatory(75, 175, false));
			mandatories.add(new Mandatory(75, 125, false));
			mandatories.add(new Mandatory(125, 75, false));
			mandatories.add(new Mandatory(175, 75, false));
			mandatories.add(new Mandatory(225, 75, false));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 275;

		}
		if (level==11)
		{
			blocks.add(new Block(375, 225));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(75, 225));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(75, 275, false));
			mirrors.add(new Mirror(525, 125, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(525, 475, false));
			mandatories.add(new Mandatory(325, 225, false));
			mandatories.add(new Mandatory(425, 125, false));
			mandatories.add(new Mandatory(425, 325, false));
			mirrorSupply = 3;
			targetX = 575;
			targetY = 175;

		}
		if(level==12)
		{
			blocks.add(new Block(125, 125));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(525, 125));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(425, 325));
			blocks.add(new Block(525, 325));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(125, 425));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(525, 175));
			blocks.add(new Block(525, 375));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(275, 125));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(175, 475, false));
			mirrors.add(new Mirror(175, 75, true));
			mirrors.add(new Mirror(375, 75, false));
			mirrors.add(new Mirror(375, 275, true));
			mirrors.add(new Mirror(175, 275, true));
			mandatories.add(new Mandatory(175, 325, false));
			mandatories.add(new Mandatory(275, 325, false));
			mandatories.add(new Mandatory(375, 325, false));
			mandatories.add(new Mandatory(475, 325, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;

		}
		if (level==13)
		{
			blocks.add(new Block(125, 125));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(375, 425));
			blocks.add(new Block(325, 375));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(225, 375));
			blocks.add(new Block(175, 425));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(275, 125));
			blocks.add(new Block(375, 125));
			blocks.add(new Block(175, 375));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(275, 375));
			blocks.add(new Block(375, 375));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(475, 375));
			mirrors.add(new Mirror(125, 325, false));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(125, 175, false));
			mirrors.add(new Mirror(375, 175, true));
			mirrors.add(new Mirror(375, 275, false));
			mirrors.add(new Mirror(75, 75, false));
			mandatories.add(new Mandatory(475, 225, false));
			mandatories.add(new Mandatory(525, 225, false));
			mandatories.add(new Mandatory(525, 175, false));
			mandatories.add(new Mandatory(475, 175, false));
			mandatories.add(new Mandatory(75, 225, false));
			mandatories.add(new Mandatory(125, 275, false));
			mandatories.add(new Mandatory(125, 225, false));
			mandatories.add(new Mandatory(75, 275, false));
			mandatories.add(new Mandatory(275, 175, false));
			mandatories.add(new Mandatory(325, 175, false));
			mandatories.add(new Mandatory(275, 225, false));
			mandatories.add(new Mandatory(325, 225, false));
			mirrorSupply = 7;
			targetX = 475;
			targetY = 25;
		}
		if (level==14)
		{
			blocks.add(new Block(75, 475));
			blocks.add(new Block(125, 475));
			blocks.add(new Block(175, 475));
			blocks.add(new Block(225, 475));
			blocks.add(new Block(275, 475));
			blocks.add(new Block(325, 475));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(425, 475));
			blocks.add(new Block(275, 175));
			blocks.add(new Block(375, 125));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(75, 75));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(125, 75, false));
			mirrors.add(new Mirror(125, 175, true));
			mirrors.add(new Mirror(525, 225, true));
			mirrors.add(new Mirror(75, 325, false));
			mirrors.add(new Mirror(75, 425, true));
			mirrors.add(new Mirror(175, 425, false));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(75, 275, true));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(75, 125, false));
			mirrors.add(new Mirror(525, 325, false));
			mandatories.add(new Mandatory(175, 125, false));
			mandatories.add(new Mandatory(425, 225, false));
			mandatories.add(new Mandatory(125, 375, false));
			mirrorSupply = 2;
			targetX = 175;
			targetY = 25;


		}
		if (level==15)
		{
			blocks.add(new Block(75, 325));
			blocks.add(new Block(75, 425));
			blocks.add(new Block(75, 375));
			blocks.add(new Block(75, 475));
			blocks.add(new Block(75, 275));
			blocks.add(new Block(75, 225));
			blocks.add(new Block(75, 175));
			blocks.add(new Block(75, 125));
			blocks.add(new Block(75, 75));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(125, 475, true));
			mirrors.add(new Mirror(125, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(325, 275, false));
			mirrors.add(new Mirror(325, 375, true));
			mirrors.add(new Mirror(425, 375, false));
			mandatories.add(new Mandatory(225, 325, false));
			mandatories.add(new Mandatory(125, 325, false));
			mandatories.add(new Mandatory(125, 425, false));
			mandatories.add(new Mandatory(225, 425, false));
			mandatories.add(new Mandatory(475, 475, false));
			mandatories.add(new Mandatory(525, 425, false));
			mandatories.add(new Mandatory(525, 325, false));
			mandatories.add(new Mandatory(325, 325, false));
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(425, 325, false));
			mandatories.add(new Mandatory(375, 375, false));
			mandatories.add(new Mandatory(475, 275, false));
			mandatories.add(new Mandatory(425, 225, false));
			mandatories.add(new Mandatory(375, 175, false));
			mandatories.add(new Mandatory(175, 175, false));
			mandatories.add(new Mandatory(125, 125, false));
			mandatories.add(new Mandatory(175, 75, false));
			mandatories.add(new Mandatory(225, 125, false));
			mandatories.add(new Mandatory(225, 225, false));
			mandatories.add(new Mandatory(325, 225, false));
			mandatories.add(new Mandatory(275, 275, false));
			mandatories.add(new Mandatory(175, 275, false));
			mandatories.add(new Mandatory(325, 125, false));
			mandatories.add(new Mandatory(375, 75, false));
			mandatories.add(new Mandatory(475, 75, false));
			mandatories.add(new Mandatory(525, 125, false));
			mandatories.add(new Mandatory(525, 225, false));
			mirrorSupply = 8;
			targetX = 375;
			targetY = 525;
		}
		if (level==16)
		{
			mirrors.add(new Mirror(125, 425, false));
			mirrors.add(new Mirror(175, 375, false));
			mirrors.add(new Mirror(225, 325, false));
			mirrors.add(new Mirror(275, 275, false));
			mirrors.add(new Mirror(275, 225, true));
			mirrors.add(new Mirror(175, 125, true));
			mirrors.add(new Mirror(125, 125, false));
			mirrors.add(new Mirror(125, 175, true));
			mirrors.add(new Mirror(175, 225, true));
			mirrors.add(new Mirror(175, 275, false));
			mirrors.add(new Mirror(125, 275, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(225, 475, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(425, 475, false));
			mirrors.add(new Mirror(475, 475, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(125, 75, false));
			mirrors.add(new Mirror(75, 75, true));
			mirrors.add(new Mirror(175, 75, true));
			mirrors.add(new Mirror(225, 75, false));
			mirrors.add(new Mirror(275, 75, true));
			mirrors.add(new Mirror(325, 75, false));
			mirrors.add(new Mirror(375, 75, true));
			mirrors.add(new Mirror(425, 75, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(525, 75, false));
			mirrors.add(new Mirror(525, 125, true));
			mirrors.add(new Mirror(525, 175, false));
			mirrors.add(new Mirror(475, 175, true));
			mirrors.add(new Mirror(425, 175, false));
			mirrors.add(new Mirror(425, 225, true));
			mirrors.add(new Mirror(425, 275, false));
			mirrors.add(new Mirror(375, 325, false));
			mirrors.add(new Mirror(375, 375, true));
			mirrors.add(new Mirror(325, 375, false));
			mirrors.add(new Mirror(325, 325, true));
			mirrors.add(new Mirror(225, 175, true));
			mandatories.add(new Mandatory(525, 375, false));
			mandatories.add(new Mandatory(475, 225, false));
			mandatories.add(new Mandatory(425, 325, false));
			mandatories.add(new Mandatory(225, 425, false));
			mandatories.add(new Mandatory(225, 375, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 125;

		}
		if (level==17)
		{
			mirrors.add(new Mirror(225, 225, false));
			mirrors.add(new Mirror(275, 175, false));
			mirrors.add(new Mirror(425, 175, true));
			mirrors.add(new Mirror(475, 225, true));
			mirrors.add(new Mirror(475, 325, false));
			mirrors.add(new Mirror(425, 375, false));
			mirrors.add(new Mirror(275, 375, true));
			mirrors.add(new Mirror(225, 325, true));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(125, 425, true));
			mirrors.add(new Mirror(75, 375, true));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(525, 425, false));
			mirrors.add(new Mirror(525, 125, true));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(175, 75, false));
			mirrors.add(new Mirror(125, 125, false));
			mirrors.add(new Mirror(75, 175, false));
			mirrors.add(new Mirror(525, 275, true));
			mirrors.add(new Mirror(525, 225, true));
			mirrors.add(new Mirror(325, 425, true));
			mandatories.add(new Mandatory(425, 225, false));
			mandatories.add(new Mandatory(325, 175, false));
			mandatories.add(new Mandatory(425, 325, false));
			mandatories.add(new Mandatory(525, 375, false));
			mandatories.add(new Mandatory(125, 175, false));
			mandatories.add(new Mandatory(225, 425, false));
			mandatories.add(new Mandatory(475, 125, false));
			mandatories.add(new Mandatory(375, 75, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;

		}
		if (level==18)
		{
			blocks.add(new Block(525, 125));
			blocks.add(new Block(425, 75));
			blocks.add(new Block(425, 175));
			blocks.add(new Block(375, 375));
			blocks.add(new Block(425, 475));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(175, 275));
			blocks.add(new Block(225, 275));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(125, 175));
			blocks.add(new Block(125, 375));
			blocks.add(new Block(175, 375));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(325, 175));
			blocks.add(new Block(375, 75));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(325, 325));
			mirrors.add(new Mirror(475, 275, false));
			mirrors.add(new Mirror(275, 275, true));
			mirrors.add(new Mirror(225, 325, true));
			mirrors.add(new Mirror(225, 375, true));
			mirrors.add(new Mirror(175, 225, false));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(325, 75, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(425, 325, true));
			mandatories.add(new Mandatory(525, 375, false));
			mirrorSupply = 4;
			targetX = 475;
			targetY = 25;

		}
		if (level==19)
		{
			mirrors.add(new Mirror(325, 375, true));
			mirrors.add(new Mirror(275, 325, true));
			mirrors.add(new Mirror(225, 275, true));
			mirrors.add(new Mirror(175, 225, true));
			mirrors.add(new Mirror(225, 125, true));
			mirrors.add(new Mirror(275, 175, true));
			mirrors.add(new Mirror(325, 225, true));
			mirrors.add(new Mirror(325, 125, true));
			mirrors.add(new Mirror(375, 75, true));
			mirrors.add(new Mirror(175, 375, true));
			mirrors.add(new Mirror(225, 425, true));
			mirrors.add(new Mirror(375, 425, true));
			mirrors.add(new Mirror(75, 275, true));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(125, 125, true));
			mirrors.add(new Mirror(125, 75, false));
			mirrors.add(new Mirror(175, 75, true));
			mirrors.add(new Mirror(125, 175, false));
			mirrors.add(new Mirror(525, 325, false));
			mirrors.add(new Mirror(525, 125, true));
			mirrors.add(new Mirror(375, 275, true));
			mirrors.add(new Mirror(325, 75, false));
			mandatories.add(new Mandatory(225, 375, false));
			mandatories.add(new Mandatory(125, 225, false));
			mandatories.add(new Mandatory(325, 325, false));
			mandatories.add(new Mandatory(375, 175, false));
			mirrorSupply = 2;
			targetX = 125;
			targetY = 525;

		}
		if (level==20)
		{
			blocks.add(new Block(175, 475));
			blocks.add(new Block(225, 475));
			blocks.add(new Block(375, 475));
			blocks.add(new Block(425, 475));
			blocks.add(new Block(425, 75));
			blocks.add(new Block(375, 75));
			blocks.add(new Block(225, 75));
			blocks.add(new Block(175, 75));
			mirrors.add(new Mirror(225, 275, false));
			mirrors.add(new Mirror(275, 225, false));
			mirrors.add(new Mirror(325, 275, false));
			mirrors.add(new Mirror(275, 325, false));
			mirrors.add(new Mirror(175, 225, false));
			mirrors.add(new Mirror(225, 175, false));
			mirrors.add(new Mirror(325, 375, false));
			mirrors.add(new Mirror(375, 325, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(225, 425, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(375, 425, false));
			mirrors.add(new Mirror(425, 425, true));
			mirrors.add(new Mirror(475, 475, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(175, 425, false));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(125, 75, true));
			mirrors.add(new Mirror(175, 125, true));
			mirrors.add(new Mirror(225, 125, false));
			mirrors.add(new Mirror(275, 75, false));
			mirrors.add(new Mirror(325, 75, true));
			mirrors.add(new Mirror(375, 125, true));
			mirrors.add(new Mirror(425, 125, false));
			mirrors.add(new Mirror(475, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(475, 175, true));
			mirrors.add(new Mirror(525, 225, true));
			mirrors.add(new Mirror(525, 175, false));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(525, 275, true));
			mirrors.add(new Mirror(525, 325, false));
			mirrors.add(new Mirror(525, 375, true));
			mandatories.add(new Mandatory(325, 325, false));
			mandatories.add(new Mandatory(225, 325, false));
			mandatories.add(new Mandatory(225, 225, false));
			mandatories.add(new Mandatory(325, 225, false));
			mirrorSupply = 1;
			targetX = 25;
			targetY = 275;
		}
		if (level==31)
		{
			blocks.add(new Block(275, 75));
			blocks.add(new Block(325, 75));
			blocks.add(new Block(375, 75));
			blocks.add(new Block(425, 75));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(475, 75));
			blocks.add(new Block(75, 175));
			blocks.add(new Block(125, 175));
			blocks.add(new Block(175, 175));
			blocks.add(new Block(125, 425));
			blocks.add(new Block(175, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(125, 375));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(325, 375));
			mirrors.add(new Mirror(75, 125, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(325, 125, true));
			mirrors.add(new Mirror(325, 225, false));
			mirrors.add(new Mirror(225, 225, false));
			mirrors.add(new Mirror(375, 125, false));
			mirrors.add(new Mirror(425, 125, true));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(375, 475, false));
			mirrors.add(new Mirror(475, 475, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(175, 375, true));
			mirrors.add(new Mirror(125, 325, true));
			mirrors.add(new Mirror(275, 375, true));
			mirrors.add(new Mirror(325, 425, true));
			mirrors.add(new Mirror(475, 125, false));
			mirrors.add(new Mirror(525, 125, true));
			mandatories.add(new Mandatory(325, 275, false));
			mandatories.add(new Mandatory(275, 225, false));
			mandatories.add(new Mandatory(325, 175, false));
			mandatories.add(new Mandatory(275, 125, false));
			mandatories.add(new Mandatory(375, 225, false));
			mandatories.add(new Mandatory(425, 175, false));
			mandatories.add(new Mandatory(125, 475, false));
			mandatories.add(new Mandatory(75, 425, false));
			mandatories.add(new Mandatory(375, 325, false));
			mandatories.add(new Mandatory(225, 175, false));
			mandatories.add(new Mandatory(175, 125, false));
			mandatories.add(new Mandatory(125, 75, false));
			mirrorSupply = 3;
			targetX = 425;
			targetY = 525;

		}
		if (level==21)
		{
			mirrors.add(new Mirror(275, 275, true));
			mirrors.add(new Mirror(275, 225, false));
			mirrors.add(new Mirror(325, 225, true));
			mirrors.add(new Mirror(325, 275, false));
			mirrors.add(new Mirror(175, 175, true));
			mirrors.add(new Mirror(175, 125, false));
			mirrors.add(new Mirror(225, 125, true));
			mirrors.add(new Mirror(225, 175, false));
			mirrors.add(new Mirror(125, 275, true));
			mirrors.add(new Mirror(175, 275, false));
			mirrors.add(new Mirror(175, 225, true));
			mirrors.add(new Mirror(125, 225, false));
			mirrors.add(new Mirror(225, 375, true));
			mirrors.add(new Mirror(175, 375, false));
			mirrors.add(new Mirror(175, 425, true));
			mirrors.add(new Mirror(225, 425, false));
			mirrors.add(new Mirror(425, 125, true));
			mirrors.add(new Mirror(375, 125, false));
			mirrors.add(new Mirror(375, 175, true));
			mirrors.add(new Mirror(425, 175, false));
			mirrors.add(new Mirror(425, 375, false));
			mirrors.add(new Mirror(475, 375, true));
			mirrors.add(new Mirror(475, 425, false));
			mirrors.add(new Mirror(425, 425, true));
			mirrors.add(new Mirror(375, 375, true));
			mirrors.add(new Mirror(325, 375, false));
			mirrors.add(new Mirror(325, 425, true));
			mirrors.add(new Mirror(375, 425, false));
			mirrors.add(new Mirror(475, 325, false));
			mirrors.add(new Mirror(425, 325, true));
			mirrors.add(new Mirror(425, 275, false));
			mirrors.add(new Mirror(475, 275, true));
			mirrors.add(new Mirror(275, 175, true));
			mirrors.add(new Mirror(325, 175, false));
			mirrors.add(new Mirror(325, 125, true));
			mirrors.add(new Mirror(275, 125, false));
			mandatories.add(new Mandatory(125, 375, false));
			mandatories.add(new Mandatory(125, 475, false));
			mandatories.add(new Mandatory(75, 425, false));
			mandatories.add(new Mandatory(75, 325, false));
			mandatories.add(new Mandatory(125, 175, false));
			mandatories.add(new Mandatory(225, 225, false));
			mandatories.add(new Mandatory(275, 375, false));
			mandatories.add(new Mandatory(325, 325, false));
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(425, 225, false));
			mandatories.add(new Mandatory(475, 175, false));
			mandatories.add(new Mandatory(525, 225, false));
			mandatories.add(new Mandatory(525, 325, false));
			mirrorSupply = 9;
			targetX = 25;
			targetY = 275;

		}
		if (level==22)
		{
			blocks.add(new Block(325, 125));
			blocks.add(new Block(375, 125));
			blocks.add(new Block(375, 175));
			blocks.add(new Block(325, 175));
			mirrors.add(new Mirror(325, 325, true));
			mirrors.add(new Mirror(275, 275, true));
			mirrors.add(new Mirror(225, 225, true));
			mirrors.add(new Mirror(175, 325, false));
			mirrors.add(new Mirror(175, 175, true));
			mirrors.add(new Mirror(175, 125, false));
			mirrors.add(new Mirror(375, 325, false));
			mirrors.add(new Mirror(425, 275, false));
			mirrors.add(new Mirror(475, 225, false));
			mirrors.add(new Mirror(175, 375, true));
			mirrors.add(new Mirror(125, 325, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 175, false));
			mirrors.add(new Mirror(125, 125, false));
			mirrors.add(new Mirror(175, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(125, 375, false));
			mandatories.add(new Mandatory(225, 325, false));
			mandatories.add(new Mandatory(175, 275, false));
			mandatories.add(new Mandatory(375, 375, false));
			mirrorSupply = 1;
			targetX = 575;
			targetY = 325;
		}
		if (level==23)
		{
			blocks.add(new Block(125, 125));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(275, 125));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(525, 325));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(425, 325));
			blocks.add(new Block(375, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(125, 425));
			blocks.add(new Block(75, 325));
			blocks.add(new Block(75, 225));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(525, 375, true));
			mirrors.add(new Mirror(75, 375, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 175, false));
			mirrors.add(new Mirror(275, 175, false));
			mirrors.add(new Mirror(275, 375, true));
			mandatories.add(new Mandatory(425, 275, false));
			mandatories.add(new Mandatory(325, 475, false));
			mandatories.add(new Mandatory(275, 475, false));
			mandatories.add(new Mandatory(275, 75, false));
			mandatories.add(new Mandatory(325, 75, false));
			mandatories.add(new Mandatory(125, 225, false));
			mandatories.add(new Mandatory(125, 325, false));
			mandatories.add(new Mandatory(275, 225, false));
			mandatories.add(new Mandatory(275, 325, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;
		}
		if (level==24)
		{
			blocks.add(new Block(175, 325));
			blocks.add(new Block(75, 325));
			mirrors.add(new Mirror(375, 475, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(75, 375, true));
			mirrors.add(new Mirror(75, 275, false));
			mirrors.add(new Mirror(525, 375, false));
			mirrors.add(new Mirror(525, 325, true));
			mirrors.add(new Mirror(325, 425, false));
			mirrors.add(new Mirror(125, 425, true));
			mirrors.add(new Mirror(125, 225, false));
			mirrors.add(new Mirror(375, 225, true));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(175, 175, false));
			mirrors.add(new Mirror(225, 125, false));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(175, 75, false));
			mirrors.add(new Mirror(175, 125, true));
			mirrors.add(new Mirror(125, 125, false));
			mirrors.add(new Mirror(125, 175, true));
			mirrors.add(new Mirror(75, 175, false));
			mirrors.add(new Mirror(75, 225, true));
			mirrors.add(new Mirror(75, 125, true));
			mirrors.add(new Mirror(125, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 425, false));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(225, 475, false));
			mirrors.add(new Mirror(425, 475, true));
			mirrors.add(new Mirror(475, 475, false));
			mandatories.add(new Mandatory(325, 375, false));
			mandatories.add(new Mandatory(375, 325, false));
			mandatories.add(new Mandatory(325, 275, false));
			mandatories.add(new Mandatory(275, 325, false));
			mandatories.add(new Mandatory(325, 475, false));
			mandatories.add(new Mandatory(275, 425, false));
			mandatories.add(new Mandatory(375, 425, false));
			mandatories.add(new Mandatory(225, 375, false));
			mandatories.add(new Mandatory(225, 275, false));
			mandatories.add(new Mandatory(425, 375, false));
			mandatories.add(new Mandatory(175, 425, false));
			mandatories.add(new Mandatory(175, 225, false));
			mandatories.add(new Mandatory(125, 275, false));
			mandatories.add(new Mandatory(125, 375, false));
			mandatories.add(new Mandatory(275, 225, false));
			mandatories.add(new Mandatory(425, 275, false));
			mirrorSupply = 4;
			targetX = 275;
			targetY = 25;

		}
		if (level==25)
		{
			mirrors.add(new Mirror(475, 125, true));
			mirrors.add(new Mirror(475, 275, false));
			mirrors.add(new Mirror(325, 275, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 275, true));
			mirrors.add(new Mirror(75, 325, false));
			mirrors.add(new Mirror(175, 475, false));
			mirrors.add(new Mirror(225, 475, true));
			mirrors.add(new Mirror(275, 475, false));
			mirrors.add(new Mirror(325, 475, true));
			mirrors.add(new Mirror(375, 475, false));
			mirrors.add(new Mirror(425, 475, true));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(525, 425, false));
			mirrors.add(new Mirror(375, 425, true));
			mirrors.add(new Mirror(425, 425, false));
			mirrors.add(new Mirror(275, 425, true));
			mirrors.add(new Mirror(175, 425, true));
			mirrors.add(new Mirror(175, 375, false));
			mirrors.add(new Mirror(275, 375, false));
			mirrors.add(new Mirror(375, 375, false));
			mirrors.add(new Mirror(475, 375, false));
			mirrors.add(new Mirror(525, 125, false));
			mirrors.add(new Mirror(475, 75, false));
			mandatories.add(new Mandatory(225, 125, false));
			mandatories.add(new Mandatory(175, 125, false));
			mandatories.add(new Mandatory(125, 175, false));
			mandatories.add(new Mandatory(125, 225, false));
			mandatories.add(new Mandatory(175, 275, false));
			mandatories.add(new Mandatory(225, 275, false));
			mandatories.add(new Mandatory(275, 225, false));
			mandatories.add(new Mandatory(275, 175, false));
			mandatories.add(new Mandatory(325, 225, false));
			mandatories.add(new Mandatory(325, 175, false));
			mandatories.add(new Mandatory(375, 125, false));
			mandatories.add(new Mandatory(425, 125, false));
			mandatories.add(new Mandatory(475, 175, false));
			mandatories.add(new Mandatory(475, 225, false));
			mandatories.add(new Mandatory(425, 275, false));
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(325, 325, false));
			mandatories.add(new Mandatory(275, 325, false));
			mandatories.add(new Mandatory(125, 325, false));
			mandatories.add(new Mandatory(75, 125, false));
			mandatories.add(new Mandatory(125, 75, false));
			mirrorSupply = 6;
			targetX = 275;
			targetY = 25;
		}
		if (level==26)
		{
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(125, 425, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(125, 125, false));
			mirrors.add(new Mirror(475, 425, false));
			mirrors.add(new Mirror(475, 125, true));
			mirrors.add(new Mirror(75, 275, false));
			mirrors.add(new Mirror(75, 225, true));
			mandatories.add(new Mandatory(325, 425, false));
			mandatories.add(new Mandatory(275, 125, false));
			mandatories.add(new Mandatory(325, 275, false));
			mandatories.add(new Mandatory(275, 275, false));
			mandatories.add(new Mandatory(475, 225, false));
			mandatories.add(new Mandatory(125, 325, false));
			mirrorSupply = 2;
			targetX = 525;
			targetY = 25;
		}
		if (level==27)
		{
			blocks.add(new Block(525, 175));
			blocks.add(new Block(75, 175));
			blocks.add(new Block(375, 175));
			blocks.add(new Block(375, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(225, 175));
			mirrors.add(new Mirror(275, 475, false));
			mirrors.add(new Mirror(325, 475, true));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(275, 125, true));
			mirrors.add(new Mirror(325, 125, false));
			mirrors.add(new Mirror(75, 125, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(525, 125, false));
			mirrors.add(new Mirror(525, 225, true));
			mandatories.add(new Mandatory(275, 175, false));
			mandatories.add(new Mandatory(225, 225, false));
			mandatories.add(new Mandatory(225, 275, false));
			mandatories.add(new Mandatory(275, 325, false));
			mandatories.add(new Mandatory(325, 325, false));
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(375, 225, false));
			mandatories.add(new Mandatory(325, 175, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;
		}
		if (level==28)
		{
			blocks.add(new Block(175, 175));
			blocks.add(new Block(275, 175));
			blocks.add(new Block(375, 175));
			blocks.add(new Block(475, 175));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(225, 375));
			blocks.add(new Block(475, 325));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(325, 325));
			blocks.add(new Block(325, 375));
			mirrors.add(new Mirror(75, 275, false));
			mirrors.add(new Mirror(75, 225, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(125, 425, true));
			mirrors.add(new Mirror(125, 125, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(425, 325, false));
			mirrors.add(new Mirror(225, 325, true));
			mandatories.add(new Mandatory(225, 275, false));
			mandatories.add(new Mandatory(325, 225, false));
			mandatories.add(new Mandatory(425, 275, false));
			mandatories.add(new Mandatory(125, 325, false));
			mandatories.add(new Mandatory(125, 175, false));
			mandatories.add(new Mandatory(75, 375, false));
			mandatories.add(new Mandatory(225, 125, false));
			mandatories.add(new Mandatory(325, 125, false));
			mandatories.add(new Mandatory(425, 125, false));
			mandatories.add(new Mandatory(375, 425, false));
			mandatories.add(new Mandatory(275, 425, false));
			mirrorSupply = 4;
			targetX = 575;
			targetY = 225;
		}
		if (level==29)
		{
			blocks.add(new Block(275, 225));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(275, 125));
			blocks.add(new Block(375, 125));
			blocks.add(new Block(475, 125));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(375, 325));
			blocks.add(new Block(475, 325));
			blocks.add(new Block(475, 425));
			blocks.add(new Block(375, 425));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(175, 425));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(425, 475, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(325, 375, false));
			mirrors.add(new Mirror(325, 175, true));
			mirrors.add(new Mirror(125, 175, false));
			mirrors.add(new Mirror(125, 375, true));
			mandatories.add(new Mandatory(225, 375, false));
			mandatories.add(new Mandatory(375, 275, false));
			mirrorSupply = 2;
			targetX = 225;
			targetY = 525;
		}
		if (level==30)
		{
			blocks.add(new Block(325, 275));
			mirrors.add(new Mirror(375, 75, true));
			mirrors.add(new Mirror(425, 75, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(525, 125, true));
			mirrors.add(new Mirror(525, 175, false));
			mirrors.add(new Mirror(475, 225, false));
			mirrors.add(new Mirror(475, 325, true));
			mirrors.add(new Mirror(525, 375, true));
			mirrors.add(new Mirror(525, 425, false));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(425, 475, true));
			mirrors.add(new Mirror(375, 425, true));
			mirrors.add(new Mirror(375, 375, false));
			mirrors.add(new Mirror(375, 475, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(225, 425, true));
			mirrors.add(new Mirror(225, 375, false));
			mirrors.add(new Mirror(175, 425, false));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 425, false));
			mirrors.add(new Mirror(75, 375, true));
			mirrors.add(new Mirror(75, 325, false));
			mirrors.add(new Mirror(125, 275, false));
			mirrors.add(new Mirror(175, 225, false));
			mirrors.add(new Mirror(175, 175, true));
			mirrors.add(new Mirror(125, 125, true));
			mirrors.add(new Mirror(75, 75, true));
			mirrors.add(new Mirror(125, 75, false));
			mirrors.add(new Mirror(275, 75, true));
			mirrors.add(new Mirror(225, 75, false));
			mirrors.add(new Mirror(175, 75, true));
			mirrors.add(new Mirror(375, 275, false));
			mirrors.add(new Mirror(525, 225, true));
			mirrors.add(new Mirror(525, 325, false));
			mirrors.add(new Mirror(125, 175, false));
			mirrors.add(new Mirror(75, 125, false));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(75, 275, true));
			mirrors.add(new Mirror(125, 225, true));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(225, 475, false));
			mandatories.add(new Mandatory(175, 125, false));
			mandatories.add(new Mandatory(475, 125, false));
			mandatories.add(new Mandatory(475, 425, false));
			mandatories.add(new Mandatory(125, 375, false));
			mandatories.add(new Mandatory(325, 475, false));
			mirrorSupply = 2;
			targetX = 325;
			targetY = 25;

		}
		if (level==32)
		{
			blocks.add(new Block(525, 475));
			blocks.add(new Block(475, 475));
			blocks.add(new Block(425, 475));
			blocks.add(new Block(375, 475));
			blocks.add(new Block(225, 475));
			blocks.add(new Block(175, 475));
			blocks.add(new Block(125, 475));
			blocks.add(new Block(75, 475));
			mirrors.add(new Mirror(175, 375, true));
			mirrors.add(new Mirror(225, 375, false));
			mirrors.add(new Mirror(325, 275, false));
			mirrors.add(new Mirror(125, 325, true));
			mirrors.add(new Mirror(125, 275, false));
			mirrors.add(new Mirror(175, 225, false));
			mirrors.add(new Mirror(175, 175, true));
			mirrors.add(new Mirror(375, 275, true));
			mirrors.add(new Mirror(425, 325, true));
			mirrors.add(new Mirror(425, 375, false));
			mirrors.add(new Mirror(375, 375, true));
			mirrors.add(new Mirror(425, 275, false));
			mirrors.add(new Mirror(475, 225, false));
			mirrors.add(new Mirror(475, 175, true));
			mirrors.add(new Mirror(425, 125, true));
			mirrors.add(new Mirror(375, 125, false));
			mirrors.add(new Mirror(325, 175, false));
			mirrors.add(new Mirror(275, 75, false));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(525, 425, false));
			mirrors.add(new Mirror(75, 425, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(475, 125, false));
			mirrors.add(new Mirror(525, 175, false));
			mirrors.add(new Mirror(525, 125, true));
			mirrors.add(new Mirror(525, 75, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(425, 75, false));
			mirrors.add(new Mirror(125, 375, false));
			mirrors.add(new Mirror(475, 375, true));
			mirrors.add(new Mirror(525, 225, true));
			mirrors.add(new Mirror(375, 75, true));
			mandatories.add(new Mandatory(125, 175, false));
			mandatories.add(new Mandatory(75, 125, false));
			mandatories.add(new Mandatory(325, 75, false));
			mandatories.add(new Mandatory(375, 325, false));
			mandatories.add(new Mandatory(175, 275, false));
			mandatories.add(new Mandatory(375, 175, false));
			mandatories.add(new Mandatory(425, 225, false));
			mandatories.add(new Mandatory(375, 425, false));
			mandatories.add(new Mandatory(225, 425, false));
			mandatories.add(new Mandatory(525, 325, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;

		}
		if (level==33)
		{
			blocks.add(new Block(475, 425));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(375, 275));
			blocks.add(new Block(175, 375));
			mirrors.add(new Mirror(125, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 125, true));
			mirrors.add(new Mirror(175, 75, false));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(275, 75, false));
			mirrors.add(new Mirror(325, 75, true));
			mirrors.add(new Mirror(375, 75, false));
			mirrors.add(new Mirror(425, 75, true));
			mirrors.add(new Mirror(475, 75, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 125, false));
			mirrors.add(new Mirror(525, 425, true));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(475, 475, true));
			mirrors.add(new Mirror(425, 475, false));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(225, 475, false));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 425, false));
			mirrors.add(new Mirror(75, 375, true));
			mirrors.add(new Mirror(75, 325, false));
			mirrors.add(new Mirror(75, 275, true));
			mirrors.add(new Mirror(75, 225, false));
			mirrors.add(new Mirror(525, 375, false));
			mirrors.add(new Mirror(525, 325, true));
			mirrors.add(new Mirror(475, 175, false));
			mirrors.add(new Mirror(475, 225, true));
			mirrors.add(new Mirror(475, 275, false));
			mirrors.add(new Mirror(525, 225, false));
			mirrors.add(new Mirror(525, 175, true));
			mandatories.add(new Mandatory(275, 375, false));
			mandatories.add(new Mandatory(325, 125, false));
			mandatories.add(new Mandatory(125, 225, false));
			mirrorSupply = 1;
			targetX = 25;
			targetY = 175;

		}
		if (level==34)
		{
			blocks.add(new Block(175, 225));
			blocks.add(new Block(175, 325));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(375, 325));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(375, 425));
			blocks.add(new Block(475, 475));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(75, 75));
			blocks.add(new Block(75, 475));
			blocks.add(new Block(75, 425));
			blocks.add(new Block(75, 375));
			blocks.add(new Block(125, 425));
			blocks.add(new Block(125, 475));
			blocks.add(new Block(175, 475));
			mirrors.add(new Mirror(125, 275, false));
			mirrors.add(new Mirror(125, 375, true));
			mirrors.add(new Mirror(175, 425, true));
			mirrors.add(new Mirror(225, 475, true));
			mirrors.add(new Mirror(75, 325, true));
			mirrors.add(new Mirror(425, 475, false));
			mirrors.add(new Mirror(475, 425, false));
			mirrors.add(new Mirror(525, 375, false));
			mirrors.add(new Mirror(525, 125, true));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(125, 75, false));
			mirrors.add(new Mirror(75, 125, false));
			mirrors.add(new Mirror(475, 375, false));
			mirrors.add(new Mirror(425, 425, false));
			mandatories.add(new Mandatory(475, 325, false));
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(225, 425, false));
			mandatories.add(new Mandatory(275, 125, false));
			mandatories.add(new Mandatory(125, 175, false));
			mirrorSupply = 3;
			targetX = 225;
			targetY = 25;

		}
		if (level==35)
		{
			blocks.add(new Block(325, 325));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(225, 425));
			blocks.add(new Block(425, 425));
			blocks.add(new Block(425, 325));
			blocks.add(new Block(225, 325));
			blocks.add(new Block(125, 325));
			blocks.add(new Block(525, 325));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(225, 225));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(225, 125));
			blocks.add(new Block(325, 125));
			blocks.add(new Block(425, 125));
			blocks.add(new Block(525, 125));
			mirrors.add(new Mirror(275, 275, true));
			mirrors.add(new Mirror(75, 375, true));
			mirrors.add(new Mirror(75, 425, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(375, 275, false));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(275, 175, true));
			mirrors.add(new Mirror(475, 475, false));
			mandatories.add(new Mandatory(375, 325, false));
			mandatories.add(new Mandatory(225, 275, false));
			mandatories.add(new Mandatory(225, 375, false));
			mirrorSupply = 3;
			targetX = 275;
			targetY = 525;

		}
		if (level==36)
		{
			blocks.add(new Block(475, 475));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(525, 425));
			blocks.add(new Block(275, 325));
			blocks.add(new Block(325, 375));
			blocks.add(new Block(475, 375));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 175, true));
			mirrors.add(new Mirror(525, 225, false));
			mirrors.add(new Mirror(225, 475, false));
			mirrors.add(new Mirror(175, 475, true));
			mirrors.add(new Mirror(125, 475, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(175, 75, false));
			mirrors.add(new Mirror(225, 75, true));
			mirrors.add(new Mirror(125, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 125, true));
			mirrors.add(new Mirror(275, 75, false));
			mirrors.add(new Mirror(325, 75, true));
			mirrors.add(new Mirror(375, 75, false));
			mirrors.add(new Mirror(425, 75, true));
			mirrors.add(new Mirror(475, 75, false));
			mirrors.add(new Mirror(75, 175, false));
			mirrors.add(new Mirror(275, 475, true));
			mirrors.add(new Mirror(325, 475, false));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(425, 475, false));
			mirrors.add(new Mirror(475, 425, false));
			mirrors.add(new Mirror(525, 375, false));
			mandatories.add(new Mandatory(125, 125, false));
			mandatories.add(new Mandatory(375, 225, false));
			mandatories.add(new Mandatory(175, 125, false));
			mandatories.add(new Mandatory(425, 325, false));
			mandatories.add(new Mandatory(275, 175, false));
			mirrorSupply = 4;
			targetX = 575;
			targetY = 125;

		}
		if (level==37)
		{
			blocks.add(new Block(325, 225));
			blocks.add(new Block(425, 375));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(125, 425));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(425, 125, true));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(525, 425, false));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(425, 275, true));
			mirrors.add(new Mirror(225, 175, false));
			mirrors.add(new Mirror(175, 225, false));
			mirrors.add(new Mirror(125, 175, false));
			mirrors.add(new Mirror(125, 225, true));
			mandatories.add(new Mandatory(275, 475, false));
			mandatories.add(new Mandatory(425, 175, false));
			mandatories.add(new Mandatory(125, 125, false));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;
		}
		if (level==38)
		{
			blocks.add(new Block(275, 75));
			blocks.add(new Block(125, 225));
			blocks.add(new Block(75, 225));
			blocks.add(new Block(75, 125));
			blocks.add(new Block(75, 175));
			blocks.add(new Block(75, 75));
			blocks.add(new Block(125, 75));
			blocks.add(new Block(225, 75));
			blocks.add(new Block(175, 75));
			blocks.add(new Block(175, 125));
			blocks.add(new Block(125, 125));
			blocks.add(new Block(125, 175));
			blocks.add(new Block(175, 175));
			blocks.add(new Block(225, 475));
			blocks.add(new Block(275, 475));
			blocks.add(new Block(325, 475));
			blocks.add(new Block(375, 475));
			blocks.add(new Block(325, 425));
			blocks.add(new Block(275, 425));
			blocks.add(new Block(525, 475));
			blocks.add(new Block(75, 475));
			blocks.add(new Block(75, 275));
			blocks.add(new Block(325, 75));
			blocks.add(new Block(375, 75));
			blocks.add(new Block(525, 75));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(475, 325));
			mirrors.add(new Mirror(125, 275, false));
			mirrors.add(new Mirror(125, 375, true));
			mirrors.add(new Mirror(325, 125, true));
			mirrors.add(new Mirror(225, 125, false));
			mirrors.add(new Mirror(325, 375, true));
			mirrors.add(new Mirror(375, 425, true));
			mirrors.add(new Mirror(425, 475, true));
			mirrors.add(new Mirror(275, 375, false));
			mirrors.add(new Mirror(225, 425, false));
			mirrors.add(new Mirror(175, 475, false));
			mirrors.add(new Mirror(125, 475, true));
			mirrors.add(new Mirror(75, 425, true));
			mirrors.add(new Mirror(75, 325, false));
			mirrors.add(new Mirror(175, 225, false));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(525, 425, false));
			mirrors.add(new Mirror(375, 125, false));
			mirrors.add(new Mirror(425, 75, false));
			mirrors.add(new Mirror(475, 75, true));
			mirrors.add(new Mirror(525, 125, true));
			mandatories.add(new Mandatory(325, 225, false));
			mandatories.add(new Mandatory(225, 325, false));
			mandatories.add(new Mandatory(275, 275, false));
			mirrorSupply = 1;
			targetX = 575;
			targetY = 375;
		}
		if (level==39)
		{
			mirrors.add(new Mirror(275, 225, false));
			mirrors.add(new Mirror(225, 275, false));
			mirrors.add(new Mirror(325, 225, true));
			mirrors.add(new Mirror(375, 275, true));
			mirrors.add(new Mirror(325, 175, false));
			mirrors.add(new Mirror(375, 125, false));
			mirrors.add(new Mirror(275, 175, true));
			mirrors.add(new Mirror(225, 125, true));
			mirrors.add(new Mirror(325, 475, true));
			mirrors.add(new Mirror(275, 475, false));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(525, 75, true));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(75, 475, true));
			mandatories.add(new Mandatory(275, 275, false));
			mandatories.add(new Mandatory(325, 275, false));
			mandatories.add(new Mandatory(375, 175, false));
			mandatories.add(new Mandatory(375, 225, false));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 325;
		}
		if (level==40)
		{
			blocks.add(new Block(225, 375));
			blocks.add(new Block(475, 425));
			mirrors.add(new Mirror(125, 375, false));
			mirrors.add(new Mirror(375, 175, true));
			mirrors.add(new Mirror(375, 325, true));
			mirrors.add(new Mirror(475, 225, false));
			mirrors.add(new Mirror(425, 125, true));
			mirrors.add(new Mirror(175, 125, false));
			mirrors.add(new Mirror(75, 75, false));
			mirrors.add(new Mirror(125, 225, true));
			mirrors.add(new Mirror(475, 175, true));
			mirrors.add(new Mirror(375, 125, false));
			mirrors.add(new Mirror(75, 175, true));
			mirrors.add(new Mirror(225, 275, true));
			mirrors.add(new Mirror(75, 425, false));
			mirrors.add(new Mirror(75, 475, true));
			mirrors.add(new Mirror(125, 325, true));
			mirrors.add(new Mirror(125, 275, false));
			mirrors.add(new Mirror(425, 325, false));
			mirrors.add(new Mirror(525, 475, false));
			mirrors.add(new Mirror(325, 425, false));
			mirrors.add(new Mirror(175, 475, false));
			mirrors.add(new Mirror(275, 325, false));
			mandatories.add(new Mandatory(175, 225, false));
			mandatories.add(new Mandatory(425, 175, false));
			mandatories.add(new Mandatory(275, 175, false));
			mandatories.add(new Mandatory(225, 125, false));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 125;
		}
		if (level == 41){
			blocks.add(new Block(225, 225));
			blocks.add(new Block(175, 225));
			blocks.add(new Block(275, 225));
			blocks.add(new Block(325, 225));
			blocks.add(new Block(375, 225));
			blocks.add(new Block(425, 225));
			blocks.add(new Block(475, 225));
			blocks.add(new Block(525, 225));
			blocks.add(new Block(75, 225));
			mirrors.add(new Mirror(125, 275, true));
			mirrors.add(new Mirror(125, 175, false));
			mandatories.add(new Mandatory(275, 275, false));
			mandatories.add(new Mandatory(175, 275, false));
			mandatories.add(new Mandatory(225, 175, false));
			mirrorSupply = 1;
			targetX = 275;
			targetY = 25;
		}
		if (level == 42){
			blocks.add(new Block(375, 275));
			mandatories.add(new Mandatory(475, 275, false));
			mandatories.add(new Mandatory(275, 275, false));
			mirrorSupply = 4;
			targetX = 25;
			targetY = 275;
		}
		if (level == 43){
			oneSided.add(new OneSidedMirror(275, 275, "dr"));
			oneSided.add(new OneSidedMirror(275, 325, "ur"));
			oneSided.add(new OneSidedMirror(325, 325, "ul"));
			oneSided.add(new OneSidedMirror(325, 225, "dl"));
			oneSided.add(new OneSidedMirror(225, 225, "dr"));
			oneSided.add(new OneSidedMirror(225, 325, "dl"));
			mirrorSupply = 1;
			targetX = 25;
			targetY = 275;
		}
		if (level==44)
		{
			blocks.add(new Block(375, 125));
			blocks.add(new Block(225, 125));
			mirrors.add(new Mirror(375, 475, true));
			mirrors.add(new Mirror(475, 475, false));
			mirrors.add(new Mirror(475, 275, true));
			mandatories.add(new Mandatory(175, 275, false));
			mandatories.add(new Mandatory(425, 275, false));
			mandatories.add(new Mandatory(75, 375, false));
			mandatories.add(new Mandatory(275, 425, false));
			mandatories.add(new Mandatory(425, 475, false));
			oneSided.add(new OneSidedMirror(525, 275, "dr"));
			oneSided.add(new OneSidedMirror(225, 175, "dl"));
			oneSided.add(new OneSidedMirror(375, 325, "dl"));
			oneSided.add(new OneSidedMirror(75, 325, "dr"));
			oneSided.add(new OneSidedMirror(75, 225, "ur"));
			oneSided.add(new OneSidedMirror(75, 475, "ur"));
			oneSided.add(new OneSidedMirror(75, 75, "dr"));
			oneSided.add(new OneSidedMirror(525, 75, "dl"));
			oneSided.add(new OneSidedMirror(525, 475, "ul"));
			oneSided.add(new OneSidedMirror(375, 175, "dr"));
			oneSided.add(new OneSidedMirror(325, 125, "dl"));
			oneSided.add(new OneSidedMirror(275, 125, "dr"));
			oneSided.add(new OneSidedMirror(175, 125, "dl"));
			oneSided.add(new OneSidedMirror(425, 125, "dr"));
			mirrorSupply = 3;
			targetX = 25;
			targetY = 275;

		}
		if (level==45)
		{
			blocks.add(new Block(75, 75));
			mirrors.add(new Mirror(325, 275, true));
			mirrors.add(new Mirror(175, 175, false));
			mandatories.add(new Mandatory(225, 175, false));
			mandatories.add(new Mandatory(175, 375, false));
			mandatories.add(new Mandatory(225, 75, false));
			mandatories.add(new Mandatory(375, 425, false));
			mandatories.add(new Mandatory(325, 425, false));
			mandatories.add(new Mandatory(75, 275, false));
			mandatories.add(new Mandatory(525, 325, false));
			oneSided.add(new OneSidedMirror(325, 475, "ul"));
			oneSided.add(new OneSidedMirror(375, 475, "ur"));
			oneSided.add(new OneSidedMirror(225, 475, "ur"));
			oneSided.add(new OneSidedMirror(175, 475, "ul"));
			oneSided.add(new OneSidedMirror(475, 475, "ul"));
			oneSided.add(new OneSidedMirror(525, 425, "ul"));
			oneSided.add(new OneSidedMirror(75, 425, "ur"));
			oneSided.add(new OneSidedMirror(125, 475, "ur"));
			oneSided.add(new OneSidedMirror(525, 75, "dl"));
			oneSided.add(new OneSidedMirror(475, 125, "dl"));
			oneSided.add(new OneSidedMirror(475, 175, "ul"));
			oneSided.add(new OneSidedMirror(125, 75, "dr"));
			oneSided.add(new OneSidedMirror(75, 125, "dr"));
			mirrorSupply = 1;
			targetX = 325;
			targetY = 25;
		}
		if (level == 46){
			mandatories.add(new Mandatory(375, 275, false));
			mandatories.add(new Mandatory(225, 275, false));
			mandatories.add(new Mandatory(525, 275, false));
			mandatories.add(new Mandatory(325, 425, false));
			mandatories.add(new Mandatory(275, 425, false));
			mandatories.add(new Mandatory(75, 275, false));
			oneSided.add(new OneSidedMirror(325, 275, "ur"));
			oneSided.add(new OneSidedMirror(275, 275, "ul"));
			oneSided.add(new OneSidedMirror(275, 325, "dl"));
			oneSided.add(new OneSidedMirror(325, 325, "dr"));
			oneSided.add(new OneSidedMirror(325, 75, "dr"));
			oneSided.add(new OneSidedMirror(525, 75, "dl"));
			oneSided.add(new OneSidedMirror(525, 475, "ul"));
			oneSided.add(new OneSidedMirror(475, 425, "ul"));
			oneSided.add(new OneSidedMirror(275, 75, "dl"));
			oneSided.add(new OneSidedMirror(75, 75, "dr"));
			oneSided.add(new OneSidedMirror(75, 425, "ur"));
			mirrorSupply = 2;
			targetX = 25;
			targetY = 275;
		}
	
	}
		

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Mirror> getMirrors() {
		return mirrors;
	}

	public void setMirrors(ArrayList<Mirror> mirrors) {
		this.mirrors = mirrors;
	}

	public int getMirrorSupply() {
		return mirrorSupply;
	}

	public void setMirrorSupply(int mirrorSupply) {
		this.mirrorSupply = mirrorSupply;
	}

	public int getTargetY() {
		return targetY;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public int getTargetX() {
		return targetX;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public ArrayList<Mandatory> getMandatories() {
		return mandatories;
	}

	public void setMandatories(ArrayList<Mandatory> mandatories) {
		this.mandatories = mandatories;
	}

	public ArrayList<OneSidedMirror> getOneSided() {
		return oneSided;
	}
	

}
