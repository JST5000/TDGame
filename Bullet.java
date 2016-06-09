package playerObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	private Icon bullet;
	private int atk;
	private Enemy e;
	private int speed;
	private boolean alive;
	private Vector2f location;
	private int lifespan;
	public static int MAX_LIFETIME;
	
	Bullet(Icon b, int atk, Enemy e, Vector2f location) {//add enemy as a parameter to lock onto, adds location (which should be the turret)
		bullet = b;
		this.atk = atk;
		this.e=e;
		speed=15
		alive=true;
		this.location=location;
		lifespan=0;
		MAX_LIFETIME=3000;
	}
	
	public void draw(Graphics g, Vector2f locationP, int angle) {
		
		//This draws the bullet and rotates it on its corner before drawing the object
		//At the location it is supposed to be at.
		g.rotate(locationP.getX(), locationP.getY(), angle);
		
		//Makes the image the same size as the size vector
		g.scale(bullet.getDesign().getWidth()/bullet.getSize().getX(), bullet.getDesign().getHeight()/bullet.getSize().getY());
		
		//Draws the image of the bullet
		g.drawImage(bullet.getDesign(), locationP.getX(), locationP.getY());
		
		//Undoes the transformations for future drawings
		g.resetTransform();
	}
	
	public void update (int millis){//takes time passed as a parameter
		if(lifespan>MAX_LIFETIME){
			alive=false;
		}
		if(alive){
			Vector2f addSpeed=speed;
			addSpeed=addSpeed.scale(millis/1000);//should scale the new vector with the amount of time passed
			location.add(addSpeed);//should add the two vectors to get a new position
			lifespan+=millis;
		}
		
	}
	
	public void render(GameContainer gc, Graphics g){
		g.drawImage(bullet.getImage, location.getx(), location.getY());
		
		
	}
	
	public int getDamage() {
		return atk;
	}
}
