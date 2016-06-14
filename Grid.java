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

   public class Enemy {
   
      protected boolean canFly;        //Can it fly?!!?!?!?
   
      private int hp;                    //Health of enemy
   
      protected int atk;                //How much enemy damages base
   
      protected int def;                //Defense of enemy
   
      protected Vector2f loc;            //Location of enemy
   
      protected Vector2f vel;            //Speed of enemy
   
      protected Vector2f size;        //Size of enemy
   
      protected Icon img;                //Image for said enemy
      
      private boolean alive;
      
      private int reward;
      
      private boolean killed;
      
      private boolean damaged;
   
    //makes an enemy with the traits specified
      public Enemy(boolean canFly, int hp, int atk, int def, Vector2f loc, Vector2f vel, Vector2f size, Icon img) {
         this.canFly = canFly;
         this.setHp(hp);
         this.atk = atk;
         this.def = def;
         this.loc = loc;
         this.vel = vel;
         this.size = size;
         this.img = img;
         reward = 4;
         alive = true;
         killed = false;
         damaged = true;
      }
   
      public boolean getKilled() {
    	  return killed;
      }
      
      public void setKilled(boolean b) {
    	  killed = b;
      }
      
      public boolean getDamaged() {
    	  return damaged;
      }
      
      public void setDamaged(boolean b) {
    	  damaged = b;
      }
      
      
    //Creates grid at location
      public void render(Graphics g)
      {
			if(getHp() > 0)
			{
				g.drawImage(img.getDesign(),loc.x, loc.y);
			}
			else
			{
				alive = false;
			}
      }
      
      public int move(Vector2f goal, int milli) {
    	  Vector2f unit = new Vector2f(0,0);
    	  if(loc.x != goal.x) {
    		  if(loc.x>goal.x) {
    			  unit.x = -1;
    		  } else {
    			  unit.x = 1;
    		  }
    	  }
    	  
    	  if(loc.y != goal.y) {
    		  if(loc.y > goal.y) {
    			  unit.y = -1;
    		  } else {
    			  unit.y = 1;
    		  }
    	  }
    	  if(loc.y < goal.y+10 && loc.y > goal.y-10 && loc.x > goal.x-10 && loc.x < goal.x + 10) {
    		  alive = false;
    		  hp = 0;
    		  return atk;
    	  }
    	  loc = loc.add(new Vector2f(vel.x*unit.x, vel.y*unit.y).scale((float)milli/1000));
    	  return 0;
      }
      
      public int getReward() {
    	  return reward;
      }
      
      public boolean isAlive() {
    	  return alive;
      }
   
      public void hit()
      {
         setHp(getHp() - 1);
      }
       
      public boolean canFly()
      {
         return canFly;
      }
      
      public void setLoc(Vector2f l) {
    	  loc = l;
      }
      
      public Vector2f getLoc() {
    	  return loc;
      }
   
      public int hp()
      {
         return getHp();
      }
   
      public int atk()
      {
         return atk;
      }
   
      public int def()
      {
         return def;
      }
   
      public Vector2f findLocation()
      {
         return loc;
      }
   
      public Icon img()
      {
         return img;
      }
      public Vector2f getDirection(Vector2f one, Vector2f two)
      {
      	int a = ((int)one.getX());
      	int b = ((int)one.getY());
      	int c = ((int)two.getX());
	int d = ((int)two.getY());
	//I made this into unit vectors, so you multiply their value by the size of 1 grid box
	Vector2f left = new Vector2f((-1), 0);
	Vector2f right = new Vector2f((1), 0);
	Vector2f up = new Vector2f(0, (-1));
	Vector2f down = new Vector2f(0, 1);
      
	 if(a > c)
      	 {
         return left;
      }
      else if (c > a)
      {
         return right;
      }
      else if (b > d)
      {
         return down; 
      }
      else
      {
         return up;
      }
   }

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

   }
