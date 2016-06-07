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
   
    //makes an enemy with the traits specified
      public Enemy(boolean canFly, int hp, int atk, int def, Vector2f loc, Vector2f vel, Vector2f size, Icon img) {
         this.canFly = canFly;
         this.hp = hp;
         this.atk = atk;
         this.def = def;
         this.loc = loc;
         this.vel = vel;
         this.size = size;
         this.img = img;
      }
   
    //Creates grid at location
      public void render()
      {
			if(hp > 0)
			{
         img.getDesign().draw(loc.x, loc.y);
			}
      }
   
      public void hit()
      {
         hp--;
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
   
      public Vector2f findVector()
      {
         return loc;
      }
   
      public Icon img()
      {
         return img;
      }
   }
