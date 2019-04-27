package jtraffic;

import javafx.scene.paint.Color;

public class Departemental extends Route {
    
 public Departemental(double posx_begin , double posy_begin , double posx_end , double posy_end){
   super(posx_begin, posy_begin, posx_end, posy_end);
   vitesse = 90 ; 
   setStroke(Color.GREEN);
   setStrokeWidth(3);
 }    
}