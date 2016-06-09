package playerObjects;

import org.newdawn.slick.Graphics;
import org.lwjgl.util.vector.Vector2f;

public class Turret {
	
	private Vector2f location; //real location in pixels THEY'RE A MAN'S UNIT OF MEASUREMENT
	
	//A stands for location relative to array
	private Vector2f locationA;
	
	//This determines if the turret can hit flying units
	private boolean canHitFlying;
	
	//This determines the damage of each attack by the turret
	private int atk;
	
	//This determines the point at which the turret can attack
	private int range;
	
	//This is the time in seconds it takes before a turret can fire again.
	private float atkSpd;
	
	//This number gives the last time it was fired
	private float firedLast;
	
	private boolean hit;//this shows whether the bullet hits
	
	//This shows if the unit hits multiple enemies or just one
	private boolean aoe;
	
	private int money;//amount of money gained from attacks
	
	//This is the image that represents the turret for in game
	Icon design;
	
	//Sets the fields to the given values
	Turret(Vector2f l, boolean canFly, int a, int r, boolean aoe, Icon i, int atkSpd) {//atkSpd in milli 
		locationA = l;
		canHitFlying = canFly;
		atk = a;
		range = r;
		this.aoe = aoe;
		design = i;
		firedLast = 0;
		this.atkSpd=atkSpd;
		location= new Vector2f( (gr.getSize().x /gr.getGrid().length), (gt.getSize().y/gr.getGrid()[0].length));
		money=0;
	
      }
	
	//This section returns fields
	public Icon getDesign() {
	  	return design;
	}
	 
	
	public Vector2f getLoc() {
		return locationA;
	}
	
	public boolean isAoe() {
		return aoe;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getAtk() {
		return atk;
	}
	
	public Vector2f getRealLoc(){
		return location;
	}
	
	public boolean canHitFlying() {
		return canHitFlying;
	}
	
	public int getMoney(){
		int money2=money;
		money=0;
		return money2();
	}
	//End of field return section
	//draw the turret on the screen with input
	public void draw(Graphics g, int x, int y){
		g.drawImage(Turret.getDesign(), x, y);
	}
	
	public void attack(Icon bullet, int milli, Enemy e) {
		//TODO: Copy Enemy.java from computer at school
		float time = System.currentTimeMillis();
		if(time-firedLast > atkSpd) { 
			//This makes a bullet that targets an enemy trying to kill them by staying locked
			//Bullets explode on collision normally regardless of target
			Bullet b = new Bullet(bullet, atk, e, location);//gives the bullet an origin in pixels
			//TODO Make the bullet follow the target (Enemy e)
			//done yo I think; the bullet needs to update though and not sure how? 
			boolean hit= b.underFire();//checks if the bullet hit anything
			if(hit){
				e.hit(b.getDamage);
				if(! (e.isAlive()) ){
					money=e.getMoney();
				}
			}
			
			
			//Fired last ensures the atkSpd gates the turret's damage
			firedLast = time;
		}
		
			
		
	}

}
