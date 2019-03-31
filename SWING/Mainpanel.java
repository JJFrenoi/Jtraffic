
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Mainpanel extends JPanel implements Runnable {
    public int posx = 0 ; 
    public int posy = 0 ;
    public BufferedImage image;
    
    public Mainpanel(){
        Image img = getToolkit().getImage("img/gcars.png");

		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.out.println("Image not found.");
		}
		image = new BufferedImage(img.getWidth(this), img.getHeight(this),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.drawImage(img, 0, 0, this);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //vertical line
         g.setColor(Color.red);
         g.drawLine(0, 0, 600, 1200);
     
         //horizontal line
         g.setColor(Color.green);
         g.drawLine(60, 45, 988, 788);
     
         //diagonal line 
         g.setColor(Color.blue);
         g.drawLine(20, 78, 450, 120);

         Graphics2D g2D = (Graphics2D) g;
         g2D.drawImage(image, this.posx, this.posy, this);
      
        
         
     
      }
      @Override
      public void run() {
         try {
             for(int i = 0 ; i<1200 ; i++){
                 this.posx++;
                 this.posy++ ; 
                 System.out.println("Re : "+posx + " Y :" +posy);
                 repaint();
                 Thread.sleep(50);
             }
             
         } catch (Exception e) {
             System.out.println(e);
         }
      }

}