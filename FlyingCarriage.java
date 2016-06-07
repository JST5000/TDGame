
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
public class FlyingCarriage extends Enemy
{
	public FlyingCarriage(Vector2f loc, Vector2f vel, Vector2f size, Icon img)
// 	throws FileNotFoundException;
// 	throws IOException;
	{
	
		super(true, 50, 20, 5, loc, vel, size, img);
// 		canFly = true;
// 		hp = 5;   //Can be changed
// 		this.vel = vel;  //Can be changed
// 		this.size = size; //Can be changed
// 		atk = hp;
// 		this.loc = loc;
// 		def = 5;  //Can be changed
// 		Icon img = new Icon(/* Poopy Icon (Use png, that allows see through shit)*/);
// 		this.img = img;
	}
}
