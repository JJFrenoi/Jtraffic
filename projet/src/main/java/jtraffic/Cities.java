package jtraffic;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class Cities  {

  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/city.png").toExternalForm();
  public Image image = new Image(imageUrl); 
  public Node city = new ImageView(image);
  public int posx = 0 , posy = 0 ; 
  public Circle perif = new Circle();
  
  public Cities(int posx , int posy ) {
    this.posx = posx ; 
    this.posy = posy ;
    perif.setCenterX(posx+45);
    perif.setCenterY(posy+65);
    perif.setRadius(70);
    perif.setFill(Color.TRANSPARENT);
    perif.setStroke(Color.BLUEVIOLET);
    perif.setStrokeWidth(6);
    perif.setStrokeType(StrokeType.CENTERED);
    perif.setStrokeLineCap(StrokeLineCap.ROUND);
    perif.setStrokeLineJoin(StrokeLineJoin.ROUND);

    
       
    this.city.relocate(posx, posy); 
  }
  
  
}
