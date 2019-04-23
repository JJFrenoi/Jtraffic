package jtraffic;

import java.util.ArrayList;

import com.sun.marlin.IntArrayCache;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Moto  {

  
  public String name;
  public double posx = 0, posy = 0 , endx = 0  , endy = 0 ; 
  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/bike.png").toExternalForm();
  public Image image = new Image(imageUrl); 
  
  public Circle ball = new Circle(7, Color.RED);
        
 //public  Bounds bounds ;
 public Timeline timeline = new Timeline();
 
  
  public Moto(String name , double posx , double posy ) {
    this.name = name;
    this.posx = posx ; 
    this.posy = posy ;
    //imoto = new ImageView(image);
    ball.setFill(new ImagePattern(image));
    ball.relocate(posx, posy);
    
  }
/*
  @Override
  public void run() {
      try{
        for(int i = 0 ; i < Frame.H ; i++ )
        {
          
          System.out.print(i);
          Thread.sleep(400); 
        }
          
      }catch(Exception e ){
        System.out.print(e);
      }
  }
  */
  


 
       
      public void play(ArrayList<Route> routes ){
        // bounds = p.getBoundsInLocal();
        for (Route r : routes) {
          timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), 
          new KeyValue(ball.layoutXProperty(), r.posx_end-ball.getRadius()),
          new KeyValue(ball.layoutYProperty(), r.posy_end-ball.getRadius()))) ;
          timeline.play();
         ball.relocate( r.posx_end-ball.getRadius(), r.posy_end-ball.getRadius());
         

        }
        ball.relocate( posx-ball.getRadius(), posy-ball.getRadius());
    
     timeline.playFromStart();
        
       
      }
  
  

/*
void moveMotoBy(double dx, double dy) {
  if (dx == 0 && dy == 0) return;

  final double cx = ball.getBoundsInLocal().getWidth()  / 2;
  final double cy = ball.getBoundsInLocal().getHeight() / 2;

  double x = cx + ball.getLayoutX() + dx;
  double y = cy + ball.getLayoutY() + dy;

  moveMotoTo(x, y);
}

void moveMotoTo(double x, double y) {
  final double cx = ball.getBoundsInLocal().getWidth()  / 2;
  final double cy = ball.getBoundsInLocal().getHeight() / 2;

  if (x - cx >= 0 &&
      x + cx <= Frame.W &&
      y - cy >= 0 &&
      y + cy <= Frame.H) {
      ball.relocate(x - cx, y - cy);
  }
}
*/
}