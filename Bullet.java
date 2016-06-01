package playerObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	private Icon bullet;
	private int atk;
	
	Bullet(Icon b, int atk) {
		bullet = b;
		this.atk = atk;
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
	
	public int getDamage() {
		return atk;
	}
}
