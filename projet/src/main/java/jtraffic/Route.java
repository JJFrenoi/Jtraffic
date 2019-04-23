package jtraffic;

import com.sun.javafx.geom.Shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class Route extends Line{

  public double posx_begin  ;
  public double posy_begin  ; 
  public double posx_end  ; 
  public double posy_end  ;
  //Line line1 = new Line();
  //Line line2 = new Line();
    
  public Route(double posx_begin , double posy_begin , double posx_end , double posy_end){
    super(posx_begin +91,  posy_begin+65 ,posx_end +91 , posy_end+65 );
    this.posx_begin = posx_begin+91; 
    this.posy_begin = posy_begin+65;
    setFill(Color.BLACK);
    setStroke(Color.RED);
    setStrokeWidth(10);
    setStrokeType(StrokeType.CENTERED);
    setStrokeLineCap(StrokeLineCap.ROUND);
    setStrokeLineJoin(StrokeLineJoin.ROUND);
   /*  setX(posx_begin+91);mvn compile exec:java -X

    setY(posy_begin+65);
    setArcWidth(30.0); 
    setArcHeight(20.0);  
   // setEndX(posx_end);
    setWidth(400); 
    setHeight(20);
    setFill(Color.TRANSPARENT);
    setStroke(Color.BLACK);
    
    */

    this.posx_end = posx_end +91 ; 
    this.posy_end = posy_end + 65;
    
   /* line1.setStartX(posx_begin+91);
    line1.setStartY(posy_begin+65);
    line1.setEndX(posx_end);
    line1.setEndY(posy_end);
    line2.setStartX(posx_begin+91);
    line2.setStartY(posy_begin+65 +20);
    line2.setEndX(posx_end);
    line2.setEndY(posy_end);*/


   }    
} 