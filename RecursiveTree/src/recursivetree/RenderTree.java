package recursivetree;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

 /**
  *  Recursion example to create a visible Tree
  *  @author M. Yendt
  *  @version 1.0
  */

public class RenderTree extends JPanel 
{
   public final double GOLDEN_RATIO = (1.0 + Math.sqrt(5)) / 2;
   public final long seed = 1000443533L;
   public Random generator;
   
   private int NTREES = 4;
   private int NBRANCHDEPTH = 1;
   private double THICKNESSREDUCTIONFACTOR = 0.6;
   private double LENGTHREDUCTIONFACTOR = 0.77;
   private double WIND = 5;
  
   public RenderTree () {
      setLayout(null);
      setBackground(Color.white);
   }

   public void setBranches(int n)
   {
      NBRANCHDEPTH = n;
   }
   
   /** Draws a based outline and then uses a recursive routine to draw triangles
    */
   @Override
   public void paint(Graphics g) {
     final Color c = new Color(165,42,42);
     int height = 200 / NTREES + 25;
     int width = getWidth();
     int cx = width/2;
     int basey = getHeight()-50;
     int thick = 20 / NTREES + 1;       
     generator = new Random(seed);
     super.paint(g);

     for (int i=1; i<=NTREES; i++) 
     {
         double random = generator.nextDouble();     
         int h = (int)(height/2 + random * height/2);
         int space = width / (NTREES + 1);
         RecurseLine(g, (int)(i * space), basey, h, 0.0, 20, thick, Color.black, NBRANCHDEPTH);
     }
   }
   
   private Point DrawLine(Graphics g, int x1, int y1, int length, double angle, double thick, Color c)
   {
     Graphics2D g2 = (Graphics2D)g;
     Stroke lineStyle = new BasicStroke((float)thick);
     g2.setStroke(lineStyle); 
     g2.setColor(c);
     int x2 = (int)(x1 - length * Math.sin(angle/180.0*Math.PI));
     int y2 = (int)(y1 - length * Math.cos(angle/180.0*Math.PI));
     g2.drawLine(x1, y1, x2, y2);     
     return new Point(x2, y2);
   }
   
   private void RecurseLine(Graphics g, int x1, int y1, int length, double angle, double angleinc, double thick, Color c, int N)
   {
     Point p = DrawLine(g,x1,y1,length,angle,thick,c);
     if (N > 1) {
       double angle1 = angle + angleinc + WIND;
       double angle2 = angle - angleinc + WIND;
       double angle3 = angle + angleinc/2 + WIND;
       double angle4 = angle - angleinc/2 + WIND;
       thick *= THICKNESSREDUCTIONFACTOR;
       length *= LENGTHREDUCTIONFACTOR; 

       if (N == 5) c = Color.green.darker().darker().darker();
       if (N == 4) c = Color.green.darker().darker();
       if (N == 3) c = Color.green.darker();
       if (N == 2) c = Color.green;
          
       RecurseLine(g, p.x, p.y, length, angle1, angleinc, thick, c, N-1);
       RecurseLine(g, p.x, p.y, length, angle2, angleinc, thick, c, N-1);
       RecurseLine(g, p.x, p.y, length, angle3, angleinc, thick, c, N-1);
       RecurseLine(g, p.x, p.y, length, angle4, angleinc, thick, c, N-1);
     }
   }
}
