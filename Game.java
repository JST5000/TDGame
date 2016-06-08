package main;

import org.lwjgl.input.Mouse;

import grid.*;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import playerObjects.Enemy;
import playerObjects.Icon;
import playerObjects.Shop;
import playerObjects.Turret;
import grid.MouseHitbox;

public class Game extends BasicGame{
	
	private Icon playGame;
	
	private Icon logo;
	
	private Turret clicked;
	
	private float totalTime;
	
	private int mouseX;
	
	private int mouseY;
	
	private int currScreen;
	
	private Shop turretShop;
	
	private Grid gameGrid;
	
	private boolean hasClicked;
	
	private boolean thereCanOnlyBeONE;
	
	private int money;
	
	private Bunker b;
	
	private int creditDrawing;
	
	private int storylineProgress;
	
	public Game(String name) {
		super(name);
		totalTime = 0;
	}
	
	//Initializes all the necessary values for the main screen and general properties
	public void init(GameContainer gc) {
		//This limits the computer processing power cost
		gc.setTargetFrameRate(60);
		gc.setShowFPS(false);
		currScreen = 0;
		money = 200;
		
		
		
		try {
			Texture t1 = TextureLoader.getTexture("jpg",  new FileInputStream(new File("./res/PlayButtonIcon.jpg")));
			playGame = new Icon(new Image(t1), new Vector2f( 50, 50));
			Texture tHardcoreLemon = TextureLoader.getTexture("jpg", new FileInputStream(new File("./res/hardcoreLemon.jpg")));
			this.logo = new Icon(new Image(tHardcoreLemon), new Vector2f(400, 300));
		}
		
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	//Initializes all the values necessary for the main game page
	public void gameInit(GameContainer gc) {
		//Game grid for the turrets/enemies/ etc.
		gameGrid = new Grid(new Vector2f(40,40), new Vector2f(gc.getWidth()*2/3, gc.getHeight()*2/3), 15);
		b =  new Bunker(20, new Vector2f(8,8), playGame);
		gameGrid.change(8, 8, b.getId());
		
		
		//Used so that the in game things only initialize once.
		thereCanOnlyBeONE = false;
		
		
		//turretShop initialization
		turretShop = new Shop(new Vector2f(gc.getWidth()/5*4-40, 40), new Vector2f(gc.getWidth()/5, gc.getHeight()*2/3));
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				turretShop.addIcon(i, j, playGame);
			}
		}
	}
	
	public void mouseEvents(int mouseX, int mouseY, GameContainer gc) {
		MouseHitbox credits = new MouseHitbox(new Vector2f(40, gc.getHeight()-40), new Vector2f(125, gc.getHeight()-10));
		MouseHitbox credits2 = new MouseHitbox(new Vector2f(40, gc.getHeight()-40), new Vector2f(170, gc.getHeight()-10));
		if(credits.isClicked(mouseX, mouseY) && currScreen == 0) {
			currScreen = 4;
		} else if(credits2.isClicked(mouseX, mouseY) && currScreen != 0) {
			currScreen = 0;
			creditDrawing = 0;
		}
		//If on credit screen
		if(currScreen == 4) {
			//Checks to see if it should display Marlena's easter egg
			MouseHitbox lenaDrawing = new MouseHitbox(new Vector2f(gc.getWidth()/2-90, gc.getHeight()/2+30), new Vector2f(gc.getWidth()/2+90, gc.getHeight()/2+55));
			if(lenaDrawing.isClicked(mouseX, mouseY)) {
				creditDrawing = 2;
			}
			//Checks to see if it should display Jackson's easter egg
			MouseHitbox jacksonDrawing = new MouseHitbox(new Vector2f(gc.getWidth()/2 - 100, gc.getHeight()/2+60), new Vector2f(gc.getWidth()/2 + 100, gc.getHeight()/2+85));
			if(jacksonDrawing.isClicked(mouseX, mouseY)) {
				creditDrawing = 3;
			}
		}
		
		if(currScreen == 0) {
			//This is the mousehitbox for the play button
			MouseHitbox play = new MouseHitbox(new Vector2f(gc.getWidth()/5*2, gc.getHeight()*7/12), new Vector2f(gc.getWidth()/5*3, gc.getHeight()*7/12+50));
			if(play.isClicked(mouseX, mouseY)) {
				currScreen = 1;
			}
			
			//Turrets are rendered constantly as fixed members of the grid, enemies are not a piece of the grid and as such go over it.
			
		} else if(currScreen == 1) {
			Icon [][] shop = turretShop.getStore();
			Vector2f relLoc = new Vector2f(mouseX-gameGrid.getLoc().x, mouseY-gameGrid.getLoc().y);
			//If the player clicked on the game grid, and had a turret selected
			if(hasClicked && relLoc.x<gameGrid.getSize().x && relLoc.y<gameGrid.getSize().y) {
				int x = (int)(relLoc.x/gameGrid.getGrid().length);
				int y = (int)(relLoc.y/gameGrid.getGrid()[0].length);
				//If there is nothing there, or just a ghost wall (id 2) make the id of that spot into the turret currently selected.
				if(gameGrid.getGrid()[x][y] == 0 || gameGrid.getGrid()[x][y] == 2) {
					gameGrid.change(x, y, clicked.getId());
					hasClicked = false;
				}
			}
			MouseHitbox dialogue = new MouseHitbox(new Vector2f(40, gc.getHeight()-250), new Vector2f(gc.getWidth()-40, gc.getHeight()-50));
			if(dialogue.isClicked(mouseX, mouseY)) {
				storylineProgress++;
			}
			
			for(int i = 0; i<shop.length; i++) {
				for(int j = 0; j<shop[i].length; j++) {
					Vector2f[] tlbr = turretShop.iconHitbox(i, j);
					MouseHitbox clickedIcon = new MouseHitbox(tlbr[0], tlbr[1]);
					 
					//This sets the clicked turret as the "clicked" or selected turret for placement
					if(clickedIcon.isClicked(mouseX, mouseY)) {
						clicked = turretShop.getTurret(i, j);
						hasClicked = true;
						money -= clicked.getCost();
					}
				}
			}
			
			
			
			//Stub, add stuff here for more things
		}
	}
	

	
	public void update(GameContainer gc, int milli) {
		totalTime+=milli;
		//gets input
		Input input=gc.getInput();
				
		//records location of mouse when mouse is clicked
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			mouseX=input.getMouseX();
			mouseY=input.getMouseY();
			
			//TODO make this work. This would parse what locations are relevant based upon screen such as
			//for the main menu its just the PLAY button, but in game its the shop, turrets, etc.
			mouseEvents(mouseX, mouseY, gc);
			
			//This makes it so if the situation changes placing a different hitbox in the same spot, it does not continue to click.
			mouseX = -1;
			mouseY = -1;
		}
		
		if(currScreen == 1 && !thereCanOnlyBeONE) {
			gameInit(gc);
			thereCanOnlyBeONE = true;
		}
			
		
		//TODO All of the wave stuffs
		/*if(waveIncoming) {
			//This would return true 1 time before changing to false for each wave (init for waves)
			if(wave.isStarting) {
				for(Enemy e: waveEnemies) {
					e.setPath(gr.getPath(new Vector2f(/*SPAWN LOCATION*///), new Vector2f(/*GOAL LOCATION*/)); //A* pathfinding
					/*startCountdown(); //This draws a "3...2...1...HERETHEYCOME!!!!" sorta thing
				}
			}
			//For each enemy make them move/dance/shuffle/doabarrelroll along the path
			for(Enemy e: waveEnemies) {
				e.move(milli);
				e.render();
			}
		} */
		
	}
	
	public void render(GameContainer gc, org.newdawn.slick.Graphics g) throws SlickException{
		int centerX = gc.getWidth()/2;
		int centerY = gc.getHeight()/2;
		
		//This sets the font for the title page. setFont uses TrueTypeFont for some reason
		Font font = new Font("Serif", Font.BOLD, 24);
		TrueTypeFont t = new TrueTypeFont(font, false);
		g.setFont(t);
		
			//Main Menu = 0
		if(currScreen == 0) {
			
			//Title that appears in the center of the screen with default font and size
			String title = "Welcome To The Tower Defense Game of Legend";
			
			//Finds the length of the string relative to the font
			int titleL = g.getFont().getWidth(title);
			
			//Same with width
			int titleW = g.getFont().getHeight(title);
			
			//Title being printed
			g.setColor(Color.black);
			g.setBackground(new Color(.7f,.7f,.7f));
			g.drawString(title, centerX-titleL/2, centerY-titleW/2);
			//g.resetTransform();
			
			//This makes a box for the player to click PlayGame
			int h = 50;
			String playGame = "PLAY GAME";
			g.drawString(playGame, centerX-g.getFont().getWidth(playGame)/2, centerY+gc.getHeight()/12 + h/2-g.getFont().getLineHeight()/2);
			g.drawRect(centerX-gc.getWidth()/10, centerY+gc.getHeight()/12, gc.getWidth()/5, h);
			
			g.scale(logo.getSize().x/logo.getDesign().getWidth(), logo.getSize().y/logo.getDesign().getHeight());
			this.logo.getDesign().draw(centerX-this.logo.getSize().getX()*6/8, gc.getHeight()/18);
			g.resetTransform();
		}
		//should check if mouse is located in the rectangle ( but IDK man)and if so, change the screen
		
		//make the game screen
		if(currScreen == 1){
			g.setColor(new Color(.7f, .7f, .7f));
			g.fillRect(0,0,gc.getWidth(),gc.getHeight());
			gameGrid.render(g);
			
			//TODO FIX, THIS DOES NOT WORK (Does not scale to size
			turretShop.render(g);
			g.drawString("You have: $"+money+"", gc.getWidth()-200, gc.getHeight()-40);
		
		
		//This draws all of the turrets that are already on the board.
		//TODO fill in the stubs.
			for(int i = 0; i<gameGrid.getGrid().length; i++) {
				for(int j = 0; j<gameGrid.getGrid()[i].length; j++) {
					//TODO REPEAT THIS FOR ALL TURRETS ON THE GRID
					if(gameGrid.getGrid()[i][j] == 1) {
						//Draw wall
					} else if(gameGrid.getGrid()[i][j] == 3 /*First Turret ID*/) {
					//	Turret t1 = new Turret(new Vector2f(i*gameGrid.getSize().x/gameGrid.getGrid().length, j*gameGrid.getSize().y/gameGrid.getGrid()[i].length),
						//		true, 1, 3, false, playGame, 3, 50);
						//Draw Turret type 1
					} else if(gameGrid.getGrid()[i][j] == 4 /*Second Turret ID*/) {
						//Draw Turret type 2
					} else if(gameGrid.getGrid()[i][j] == 5 /*Third Turret ID*/) {
						//Draw Turret type 3
					} else if(gameGrid.getGrid()[i][j] == 6 /*Fourth Turret ID*/) {
						//Draw Turret type 4
					} else if(gameGrid.getGrid()[i][j] == 7 /*Fifth Turret ID*/) {
						//Draw Turret type 5
					} else if(gameGrid.getGrid()[i][j] == 8 /*Sixth Turret ID*/) {
						//Draw Turret type 6
					} else if(gameGrid.getGrid()[i][j] == b.getId() /*Bunker ID*/) {
						//This draws the bunker, ID = 100, This then draws it in the dead center of the map
						b.getDesign().getDesign().draw(gameGrid.getLoc().x+(i-1)*gameGrid.getSize().x/gameGrid.getGrid().length,
														gameGrid.getLoc().y + (j-1)*gameGrid.getSize().y/gameGrid.getGrid()[i].length);
					}	//.............. etc.
				}
			}
			
			g.drawRect(40, gc.getHeight()-250, gc.getWidth()-80, 200);
			//TODO add tutorial text in DialogueCollection.getDialogue(storylineProgress);
			makeItFit(DialogueCollection.getDialogue(storylineProgress), 50, gc.getHeight()-240, gc.getWidth()-100, g);
		
			if(hasClicked) {
				clicked.getDesign().getDesign().draw(mouseX, mouseY);
			}
			
		}
		
		//Credit page layout
		if(currScreen == 4) {
			//TODO ADD LINKS TO THE DRAWING PROJECT UPON CLICKING EACH PERSON'S NAME
			g.setColor(new Color(98, 191, 250));
			g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
			g.setColor(Color.black);
			g.drawString("Coded and Designed by :", gc.getWidth()/2-130, gc.getHeight()/3);
			g.drawString("Sage Minard,", gc.getWidth()/2-75, gc.getHeight()/2);
			g.drawString("Marlena Rehder,", gc.getWidth()/2-90, gc.getHeight()/2+30);
			g.drawString("And Jackson Mills!", gc.getWidth()/2 - 100, gc.getHeight()/2+60);
			g.drawString("June, 2016", gc.getWidth()/2 - 65, gc.getHeight()/2 + 200);
			if(creditDrawing == 2) {
					DrawingCollection.lenaDrawing(g);
			} else if(creditDrawing == 3) {
				try {
					DrawingCollection.jacksonDrawing(g);
				} catch (IOException e) {
					System.out.println("Hey, JacksonDrawing messed up...");
					e.printStackTrace();
					
				}
			}
		}
		
		//Links to the main menu and credit screens
		if(currScreen == 0) {
			g.setColor(Color.black);
			g.drawRect(40, gc.getHeight()-40, 85,  30);
			g.drawString("Credits", 45, gc.getHeight()-40);
		} else {
			g.setColor(Color.black);
			g.drawRect(40, gc.getHeight() - 40, 130, 30);
			g.drawString("Main Menu", 45, gc.getHeight()-40);
		}
		
	}
	
	public void makeItFit(String s, int x, int y, int w, Graphics g) {
		Scanner scan = new Scanner(s);
		int totalHeight = 0;
		int totalLength = 0;
		while(scan.hasNext()) {
			String nextWord = scan.next()+" ";
			int wLength = g.getFont().getWidth(nextWord);
			if(totalLength+wLength<w) {
				totalLength+=wLength;
			} else {
				totalHeight++;
				totalLength = wLength;
			}
			g.drawString(nextWord, x+totalLength-wLength, y+totalHeight*g.getFont().getHeight(nextWord));
		}
		scan.close();
	}
	
	public static void main(String[] args) {
		try{
			Game TD = new Game("Tower Defense");
			AppGameContainer window = new AppGameContainer(TD);
			window.setDisplayMode(900, 900, false);
			window.start();
			TD.init(window);
			
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
}
