package playerObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.TextureLoader;

import grid.Grid;


public class Turret {
	
	//A stands for location relative to array
	private Vector2f locationA;
	
	//This determines if the turret can hit flying units
	protected boolean canHitFlying;
	
	//This determines the damage of each attack by the turret
	protected int atk;
	
	//This determines the point at which the turret can attack
	protected int range;
	
	//This is the time in seconds it takes before a turret can fire again.
	protected float atkSpd;
	
	//This number gives the last time it was fired
	private float firedLast;
	
	//This shows if the unit hits multiple enemies or just one
	protected boolean aoe;
	
	protected int id;
	
	protected int level;
	
	protected int cost;
	
	private int[] gridVal;
	
	//This is the image that represents the turret for in game
	Icon design;
	
	//Sets the fields to the given values
	public Turret(Vector2f l, boolean canFly, int a, int r, boolean aoe, Icon i, int atkSpd) {
		locationA = l;
		canHitFlying = canFly;
		atk = a;
		range = r;
		this.aoe = aoe;
		design = i;
		firedLast = 0;
		this.atkSpd=atkSpd;
		id = 1;
		cost = 20;
		level = 1;
		firedLast = atkSpd;
	}
	
	//This section returns fields
	public Icon getDesign() {
	  	return design;
	}
	 
	public int getCost() {
		return cost;
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
	
	public int getId() {
		return id;
	}
	
	public int getFiredLast() {
		return (int) firedLast;
	}
	
	public void setFiredLast(int i) {
		firedLast = i;
	}
	
	
	
	public int[] getGridVal() {
		return gridVal;
	}
	
	public boolean canHitFlying() {
		return canHitFlying;
	}
	//End of field return section
	//draw the turret on the screen with input
	public void draw(Graphics g, int x, int y){
		g.drawImage(getDesign().getDesign(), x, y);
	}
	
	public void setGridVal(int[] i) {
		gridVal = i;
	}
	
	public Bullet attack(Icon bullet, int milli, Enemy e, Grid gr) {
		//TODO: Copy Enemy.java from computer at school
		firedLast += milli;
			Bullet b = new Bullet(bullet, atk, e, new Vector2f(gridVal[0]*gr.GRID_UNIT.x+gr.getLoc().x+15, gridVal[1]*gr.GRID_UNIT.y + gr.getLoc().y+15));
			return b;

		
			//This makes a bullet that targets an enemy trying to kill them by staying locked
			//Bullets explode on collision normally regardless of target
		//	new Icon(new Image(TextureLoader.getTexture("jpg", new FileInputStream("./res/Bullet.png"))))
			
			//TODO Make the bullet follow the target (Enemy e)
			
		
			
			//Fired last ensures the atkSpd gates the turret's damage
			
			
		
		
			
		
	}

	public int getAtkSpd() {
		return (int)atkSpd;
	}

}
