package playerObjects;

import org.lwjgl.util.vector.Vector2f;

public class Turret {
	
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
	
	//This shows if the unit hits multiple enemies or just one
	private boolean aoe;
	
	//This is the image that represents the turret for in game
	Icon design;
	
	//Sets the fields to the given values
	Turret(Vector2f l, boolean canFly, int a, int r, boolean aoe, Icon i) {
		locationA = l;
		canHitFlying = canFly;
		atk = a;
		range = r;
		this.aoe = aoe;
		design = i;
		firedLast = 0;
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
	
	
	public boolean canHitFlying() {
		return canHitFlying;
	}
	//End of field return section
	
	public void attack(Icon bullet, int milli/*, Enemy e*/) {
		//TODO: Copy Enemy.java from computer at school
		float time = System.currentTimeMillis();
		if(time-firedLast > atkSpd*1000) { 
			//This makes a bullet that targets an enemy trying to kill them by staying locked
			//Bullets explode on collision normally regardless of target
			//Bullet b = new Bullet(bullet, atk/*, e*/);
			//TODO Make the bullet follow the target (Enemy e)
			//Fired last ensures the atkSpd gates the turret's damage
			firedLast = time;
		}
		
			
		
	}

}
