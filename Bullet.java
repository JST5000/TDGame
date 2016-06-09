package playerObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	private Icon bullet;//design of the bullet
	private int atk;//damage the bullet does
	private enemy e;//homing on enemy
	private Vector2f speed;//how fast the bullet travels
	private boolean alive;//whether the bullet is still functional
	private Vector2f location;//where the bullet is 
	private int lifespan;//how long the bullet has been alive, to avoid rendering it too long
	public static int MAX_LIFETIME;//cap on how long the bullet could possibly exist
	private Vector2f origin;//where the turret is; where the bullet originates
	boolean hit;//whether the bullet has hit anything
	boolean aoe;//the aoe of the bullet
	
	Bullet(Icon b, int atk, Enemy e, Vector2f location) {//add enemy as a parameter to lock onto, adds location (which should be the turret)
		bullet = b;
		this.atk = atk;
		this.e=e;
		speed=new Vector2f(10,10);//dummy vector
		alive=true;
		this.location=location;
		lifespan=0;
		MAX_LIFETIME=3000;
		hit=false;
	}
	
	public void draw(Graphics g, Vector2f locationP, int angle) {
		
		origin=new Vector2f=locationP;
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
	
	public void update (int milli){//takes time passed as a parameter (not sure if this works at all)
		if(lifespan>MAX_LIFETIME){
			alive=false;
		}
		if(alive){
			//moves the bullet
			Vector2f addSpeed=speed;
			Vector2f eLocation=e.getLocation;
			int eX=(int)eLocation.getX();
			int eY=(int)eLocation.getY();
			int oX=(int)location.getX();
			int oY=(int)location.getY();
			Vector2f whereDoIGo=(eX-oX,eY-oY);//should create a vector betwen the origin and the enemy. 
			addSpeed=whereDoIGo.scale(milli/1000);//should scale the new vector with the amount of time passed
			location.add(addSpeed);//should add the two vectors to get a new position
			lifespan+=milli;
			
			//checks for collisions
			int bX=location.getX();
			int bY=location.getY();
			if(!aoe){
				if(((bX-eX)>=14) && ((bX-eX) >= -14)){
					if(((bY-eY) <= 14) && (bY-eY > -14)){
						alive=false;//removes the bullet
						//says that the enemy has been hit
						hit=true;
					}
				}
			}
			else{//currently aoe increases area of effect by 2 because I'm lazy
				if(((bX-eX)>=28) && ((bX-eX) >= -28)){
					if(((bY-eY) <= 28) && (bY-eY > -28)){
						alive=false;//removes the bullet
						//says that the enemy has been hit
						hit=true;
					}
				}	
			}
			
		}
		
	}
	
	public void render(GameContainer gc, Graphics g){
		g.drawImage(bullet.getImage, location.getx(), location.getY());
		
		
	}
	
	public boolean underFire() {//returns whether a collision has occured with an enemy
		return hit;
	}
	
	public Vector2f getLocation(){//returns the location vector for hitboxes
		return location;
	}
	
	
	public int getDamage() {
		return atk;
	}
}
