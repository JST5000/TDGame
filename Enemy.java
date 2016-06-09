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
   
      protected int hp;                    //Health of enemy
   
      protected int atk;                //How much enemy damages base
   
      protected int def;                //Defense of enemy
   
      protected Vector2f loc;            //Location of enemy
   
      protected Vector2f vel;            //Speed of enemy
   
      protected Vector2f size;        //Size of enemy
   
      protected Icon img;                //Image for said enemy
      
      private int reward;//reward per enemy
   
      private boolean alive; //whether enemy is alive or no
      
    //makes an enemy with the traits specified
      public Enemy(boolean canFly, int hp, int atk, int def, Vector2f loc, Vector2f vel, Vector2f size, Icon img, int reward) {
         this.canFly = canFly;
         this.hp = hp;
         this.atk = atk;
         this.def = def;
         this.loc = loc;
         this.vel = vel;
         this.size = size;
         this.img = img;
         this.reward=reward;
         alive=true;
      }
   
    //Creates grid at location
      public void render()
      {
			if(hp > 0)
			{
         img.getDesign().draw(loc.x, loc.y);
			}
		else{
			alive=false;
		}
      }
   
      public void hit(int damage)
      {
         hp=hp-damage;
      }
       
      public boolean canFly()
      {
         return canFly;
      }
   
      public int hp()
      {
         return hp;
      }
   
      public int atk()
      {
         return atk;
      }
   
      public int def()
      {
         return def;
      }
   
      public int getMoney(){//returns amount of money for killing enemy
      return reward;
      }
      
      public Vector2f findLocation()
      {
         return loc;
      }
      
      public boolean isAlive(){
      	return alive;
      }
   
      public Icon img()
      {
         return img;
      }
      public Vector2f getDirection(Vector2f one, Vector2f two)
      {
      	int a = ((int)one.getX);
      	int b = ((int)one.getY);
      	int c = ((int)two.getX);
	int d = ((int)two.getY);
	Vector2f left = new Vector2f((x - 1), y);
	Vector2f right = new Vector2f((x + 1), y));
	Vector2f up = new Vector2f(x, (y -1));
	Vector2f down = new Vector2f(x, (y + 1));
      
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
   public void move()
   {
   	if(loc.y != 8)
   	{
   		loc.y += 1;	
   	}
   	else if(loc.x > 8)
   	{
   		loc.x -= 1;
   	}
   	else
   	{
   		loc.x += 1;
   	}
   }

   }
