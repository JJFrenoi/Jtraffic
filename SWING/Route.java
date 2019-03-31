 
import java.awt.*;

import javax.swing.*;
 
public class Route extends JLabel {
   
 public Route(){
   
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
     
 
  }
  
}