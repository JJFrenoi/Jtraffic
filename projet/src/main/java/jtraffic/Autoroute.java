
package jtraffic;

import javafx.scene.paint.Color;

public class Autoroute extends Route {
    int vitesse = 130 ; 
 public Autoroute(double posx_begin , double posy_begin , double posx_end , double posy_end){
   super(posx_begin, posy_begin, posx_end, posy_end);
   vitesse = 130 ;
   setStroke(Color.RED);
   setStrokeWidth(10);
 }    
}