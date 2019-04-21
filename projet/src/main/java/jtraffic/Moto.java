package jtraffic;

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

public class Moto implements Runnable  {

  
  public String name;
  public double posx = Frame.W / 2, posy = Frame.H / 2;
  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/bike.png").toExternalForm();
  public Image image = new Image(imageUrl); 
  
  public Circle ball = new Circle(7, Color.RED);
        
 //public  Bounds bounds ;
 //public Timeline timeline ; 
 
  
  public Moto(String name ) {
    this.name = name;
    //imoto = new ImageView(image);
    ball.setFill(new ImagePattern(image));
    ball.relocate(posx, posy);
    
  }

  @Override
  public void run() {
      try{
        

      }catch(Exception e ){
        System.out.print(e);
      }
  }
  


 
   /*     
      public void play(Route r ){
        // bounds = p.getBoundsInLocal();
         timeline = new Timeline(new KeyFrame(Duration.seconds(13), 
                    new KeyValue(ball.layoutXProperty(), r.posx_end-ball.getRadius())));

        timeline.setCycleCount(2);
        timeline.play();
       
      }
    */
  


/*void moveMotoBy(double dx, double dy) {
  if (dx == 0 && dy == 0) return;

  final double cx = imoto.getBoundsInLocal().getWidth()  / 2;
  final double cy = imoto.getBoundsInLocal().getHeight() / 2;

  double x = cx + imoto.getLayoutX() + dx;
  double y = cy + imoto.getLayoutY() + dy;

  moveMotoTo(x, y);
}*/

/*void moveMotoTo(double x, double y) {
  final double cx = imoto.getBoundsInLocal().getWidth()  / 2;
  final double cy = imoto.getBoundsInLocal().getHeight() / 2;

  if (x - cx >= 0 &&
      x + cx <= Frame.W &&
      y - cy >= 0 &&
      y + cy <= Frame.H) {
      imoto.relocate(x - cx, y - cy);
  }
}
*/
}