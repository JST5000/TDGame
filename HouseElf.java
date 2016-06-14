package playerObjects;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class HouseElf extends Enemy
{	
	public HouseElf(Vector2f loc, Vector2f vel, Vector2f size, Icon img)
//	throws FileNotFoundException;
//	throws IOException;
	{
	
		super(false, 20, 1, 1, loc, vel, size, img);
//		canFly = false;
//		hp = 3;   //Can be changed
//		this.vel = vel;  //Can be changed
//		this.size = size; //Can be changed
//		atk = hp;
//		this.loc = loc;
//		def = 3;  //Can be changed
//		Icon img = new Icon(/* Poopy Icon (Use png, that allows see through shit)*/);
//		this.img = img;
	}
	
	
}
