package main;

import org.lwjgl.input.Mouse;

import grid.*;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import playerObjects.Icon;
import playerObjects.Shop;
import grid.MouseHitbox;

public class Game extends BasicGame{
	
	private Icon playGame;
	
	private Icon logo;
	
	private float totalTime;
	
	private Texture t1;
	
	private int mouseX;
	
	private int mouseY;
	
	int currScreen;
	
	public Game(String name) {
		super(name);
		totalTime = 0;
	}
	
	public void init(GameContainer gc) {
		//This limits the computer processing power cost
		gc.setTargetFrameRate(60);
		gc.setShowFPS(false);
		currScreen = 0;
		
		
		
		try {
			Texture t1 = TextureLoader.getTexture("jpg",  new FileInputStream(new File("./res/PlayButtonIcon.jpg")));
			playGame = new Icon(new Image(t1), new Vector2f( 75, 100));
			Texture tHardcoreLemon = TextureLoader.getTexture("jpg", new FileInputStream(new File("./res/hardcoreLemon.jpg")));
			this.logo = new Icon(new Image(tHardcoreLemon), new Vector2f(400, 300));
		}
		
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void mouseEvents(int mouseX, int mouseY, GameContainer gc) {
		if(currScreen == 0) {
			//This is the mousehitbox for the play button
			MouseHitbox play = new MouseHitbox(new Vector2f(gc.getWidth()/5*2, gc.getHeight()*7/12), new Vector2f(gc.getWidth()/5*3, gc.getHeight()*7/12+50));
			if(play.isClicked(mouseX, mouseY)) {
				currScreen = 1;
			}
			
		} else if(currScreen == 1) {
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
			g.setBackground(new Color(.5f,.5f,.5f));
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
			g.setColor(new Color(.5f, .5f, .5f));
			g.fillRect(0,0,gc.getWidth(),gc.getHeight());
			Grid gameGrid = new Grid(new Vector2f(40,40), new Vector2f(gc.getWidth()*2/3, gc.getHeight()*2/3), 15);
			gameGrid.render(g);
			Shop turretShop = new Shop(new Vector2f(gc.getWidth()/5*4-40, 40), new Vector2f(gc.getWidth()/5, gc.getHeight()*2/3));
			for(int i = 0; i<4; i++) {
				for(int j = 0; j<4; j++) {
					turretShop.addIcon(i, j, playGame);
				}
			}
			//TODO FIX, THIS DOES NOT WORK (Does not scale to size
			turretShop.render(g);
		}
	}
	
	public static void main(String[] args) {
		try{
			Game TD = new Game("Tower Defense");
			AppGameContainer window = new AppGameContainer(TD);
			window.setDisplayMode(1000, 800, false);
			window.start();
			TD.init(window);
			
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
}
