import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;

public class Hippogriff extends Enemy
{
	public Hippogriff(Vector2f loc, Vector2f vel, Vector2f size)
	throws FileNotFoundException;
	throws IOException;
	{
	
		super(true, 2, 2, 2, loc, vel, size, new Image(2, 2));
// 		canFly = true;
// 		hp = 2;   //Can be changed
// 		this.vel = vel;  //Can be changed
// 		this.size = size; //Can be changed
// 		atk = hp;
// 		this.loc = loc;
// 		def = 2;  //Can be changed
// 		Image img = new Image(/* Poopy image (Use png, that allows see through shit)*/);
// 		this.img = img;
	}
}