package sierpinski;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.lang.Thread;

public class Sierpinski extends JPanel {
    
    static int height = 800;
    static int width = 800;
    
    static int maximumLevel = 9;
    static boolean animateAllLevelsUpToMaximum = true;
    static int waitTimeBetweenFrames = 500;
    static int currLevel = 1;
    
    static boolean labelsOn = true;
    static int label = 1;
    
    
    public void drawSquare( Graphics2D g, int level, int p1x,int p1y, int p2x, int p2y,int  p3x, int p3y, int p4x, int p4y) {
       g.setColor( Color.yellow);
       
       //BASE CASE
       if (level == 1) {
            fillSquare(g,p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y);
            label++;
        } 
       
        //RECURSIVE CALLS
        else {
           int x1 = p1x + ((p2x-p1x)/3);
           System.out.println(x1);
           int x2 = p1x + 2*((p2x - p1x)/3);
           
           int y1 = p1y + ((p4y- p1y)/3);
           int y2 = p1y + 2*((p4y-p1y)/3);
           
           drawSquare(g, level - 1 , p1x, p1y, x1,p1y, x1, y1, p1x, y1 );
           drawSquare(g, level - 1, x1+ 10, p1y, x2, p1y, x2, y1, x1+ 10, y1);
           drawSquare(g, level - 1, x2+10, p1y, p2x,p2y, p2x,y1,x2+10,y1);
           drawSquare(g, level - 1, p1x, y1+10, x1,y1+10,x1,y2,p1x, y2);
           drawSquare(g, level - 1, p1x, y2+10, x1,y2+10, x1,p3y, p1x, p3y);
           drawSquare(g, level - 1, x1+10, y2+10, x2, y2+10, x2, p3y, x1+10,p3y );
           drawSquare(g, level - 1, x2+ 10, y1+ 10, p2x,y1+10, p2x, y2, x2+10, y2);
           drawSquare(g, level - 1, x2+10, y2+10, x2+10, p3y, p2x, p3y, p1x, y2+10);
        }
    }

  
    
    
    
    public static void fillSquare( Graphics g,  int p1x, int p1y, int p2x, int p2y,int  p3x, int p3y, int p4x, int p4y)  {
           int[] xvalues = {p1x, p2x, p3x, p4x};
           int[] yvalues = {p1y, p2y, p3y, p4y};
           
           Polygon rectangle = new Polygon( xvalues, yvalues, 4 );
           g.fillPolygon( rectangle );
           
           //IF LABELS OPTION IS CHOSEN, WRITE THE NUMBER OF THE TRIANGLE AS ITS DRAWN
           if ( labelsOn && currLevel < 20 ) {
                g.setColor(Color.red);
           
                int xLabel = (p1x + p2x + p3x + p4x)/4;
                int yLabel = (p1y + p2y + p3y + p4y)/4;
        
                g.drawString( Integer.toString(label), xLabel, yLabel );
           }
    }
    
    
    public void paint( Graphics g ) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        int padding = 50;
      
        drawSquare( g2, currLevel, padding, padding, width-padding, padding,width-padding, height-padding, padding, height-padding);     
   }
    
    
    public static void main(String[] args) {
        JFrame screen = new JFrame();
        
        screen.add( new Sierpinski() );
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setBackground(Color.black);
        screen.setSize( width, height );
        screen.setVisible(true);
        
        if ( animateAllLevelsUpToMaximum ) {
            
            for ( int i=1; i < maximumLevel; i++ ) {
                sleep( waitTimeBetweenFrames );
                label = 1;
                screen.repaint();
                currLevel++;
            }  
        }
        
        else
            currLevel = maximumLevel;
   }

    
    public static void sleep( int duration ) {
        try {
              Thread.sleep( duration );
            }
        catch (Exception e) {
            }
    }

   
    

}