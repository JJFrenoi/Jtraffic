package jtraffic;

import com.sun.javafx.geom.Shape;
import java.lang.Math.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
  public Color p ;
  public int taille ; 
  public int vitesse ; 
  
    
  public Route(double posx_begin , double posy_begin , double posx_end , double posy_end  ){
    super(posx_begin +115,  posy_begin+65 ,posx_end +115 , posy_end+65 );
    this.posx_begin = posx_begin+115; 
    this.posy_begin = posy_begin+65;
    //setFill(Color.BLACK);
    setStrokeType(StrokeType.CENTERED);
    setStrokeLineCap(StrokeLineCap.ROUND);
    setStrokeLineJoin(StrokeLineJoin.ROUND);
   /*  setX(posx_begin+91);

    setY(posy_begin+65);
    setArcWidth(30.0); 
    setArcHeight(20.0);  
   // setEndX(posx_end);
    setWidth(400); 
    setHeight(20);
    setFill(Color.TRANSPARENT);
    setStroke(Color.BLACK);
    
    */

    this.posx_end = posx_end +115 ; 
    this.posy_end = posy_end + 65;
    
 

   }    
  public  double timeto( ){
    double t = 0 ; 
    t = Math.sqrt(Math.pow((posx_end-posx_begin),2) +  Math.pow((posy_end-posy_begin),2)      ) / vitesse;
    return t ; 
   }
} 