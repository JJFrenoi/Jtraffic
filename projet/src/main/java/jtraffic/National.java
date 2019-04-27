package jtraffic;

import javafx.scene.paint.Color;

public class National extends Route {
 public National(double posx_begin , double posy_begin , double posx_end , double posy_end){
   super(posx_begin, posy_begin, posx_end, posy_end);
   vitesse = 110 ; 
   setStroke(Color.BLUE);
   setStrokeWidth(5);
 }   
 
 
}