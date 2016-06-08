import java.awt.*;

public class graphicStuff{
   
   public static void main(String[] args){
   
   DrawingPanel panel = new DrawingPanel(100,100);
   Graphics g=panel.getGraphics();
   panel.setBackground(Color.LIGHT_GRAY);
   g.setColor(Color.RED);
   
   g.drawOval(10,10, 80,80);
   g.drawOval(15,15,70,70);
   g.drawOval(45,9,10,10);
   
   g.drawLine(10,25,90,25);
   g.drawLine(10,75,90,75);
   
   g.drawLine(10,25,50,75);
   g.drawLine(50,75,90,25);
   g.drawLine(10,25,50,25);
   g.drawLine(50,25,90,75);
   g.drawLine(50,25,10,75);
   
   g.drawLine(50,30,65,48);
   g.drawLine(65,48,35,48);
   g.drawLine(35,48,50,30);
   

   }
   
 }