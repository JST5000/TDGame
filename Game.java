package main;

import org.lwjgl.input.Mouse;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import playerObjects.Icon;

public class Game extends BasicGame{
	
	String name;
	
	Icon playGame;
	
	Icon logo;
	
	float totalTime;
	
	Texture t1;
	
	public Game(String name) {
		super(name);
		totalTime = 0;
	}
	
	public void init(GameContainer gc) {
		//This limits the computer processing power cost
		gc.setTargetFrameRate(60);
		gc.setShowFPS(false);
		
		
		
		try {
			
			
			//TODO check into "psychiatrist home" because somehow the texture made it compile fine
			Texture t1 = TextureLoader.getTexture("jpg", new FileInputStream(new File("./res/PlayButtonIcon.jpg")));
			this.playGame = new Icon(new Image(t1), new Vector2f(100, 50));
			
			Texture tHardcoreLemon = TextureLoader.getTexture("jpg", new FileInputStream(new File("./res/hardcoreLemon.jpg")));
			this.logo = new Icon(new Image(tHardcoreLemon), new Vector2f(400, 300));
		}
		
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void update(GameContainer gc, int milli) {
		totalTime+=milli;
		
		//REMOVE THIS WHEN GAME IS CORRECTLY SET UP, ITS JUST A SHUT DOWN SWITCH IN CASE I FORGET
		//REMOVE IT. THIS MEANS YOU FUTURE CODER. I WILL FIND YOU IF THIS IS NOT REMOVED.
		if(totalTime/200>1000) {
			gc.exit();
		}
	}
	
	public int getStrWidth(String s, GameContainer gc) {
		return gc.getDefaultFont().getWidth(s);
	}
	
	public int getStrHeight(String s, GameContainer gc) {
		return gc.getDefaultFont().getHeight(s);
	}
	
	public void render(GameContainer gc, org.newdawn.slick.Graphics g) throws SlickException{
		int centerX = gc.getWidth()/2;
		int centerY = gc.getHeight()/2;
		int currScreen = 0;
		
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
			this.playGame.getDesign().draw(centerX-this.playGame.getSize().getX()/2, centerY+gc.getHeight()/6);
			
			g.scale(logo.getSize().x/logo.getDesign().getWidth(), logo.getSize().y/logo.getDesign().getHeight());
			this.logo.getDesign().draw(centerX-this.logo.getSize().getX()/2, gc.getHeight()/8);
			g.resetTransform();
		}
	}
	
	public static void main(String[] args) {
		try{
			Game TD = new Game("Tower Defense");
			AppGameContainer window = new AppGameContainer(TD);
			window.setDisplayMode(1000, 1000, false);
			window.start();
			TD.init(window);
			
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
}
