package jtraffic;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Moto extends Circle {

  public String name;
  //public double posx = 0, posy = 0, endx = 0, endy = 0;
  public Point depart ; 
  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/bike.png").toExternalForm();
  public Image image = new Image(imageUrl);
  public ArrayList<Route> path ; 

  // public Circle ball = new Circle(7, Color.RED);

  // public Bounds bounds ;
  public ArrayList<Timeline> timeline = new ArrayList<>();

  public Moto(String name, Point d ) {
    super(7, Color.RED);
    this.name = name;
    this.depart = d ; 
    // imoto = new ImageView(image);
    setFill(new ImagePattern(image));
    relocate(depart.getX(), depart.getY());

  }
 public void setPath(ArrayList<Route> path ){
   this.path = path ; 
 }

  public void play() {
    // bounds = p.getBoundsInLocal();
    for (Route r : path) {

      timeline.add(new Timeline(
          new KeyFrame(Duration.seconds(r.timeto()), new KeyValue(layoutXProperty(), r.p_end.getX() - getRadius()),
              new KeyValue(layoutYProperty(), r.p_end.getY() - getRadius()))));
      // ball.relocate( r.posx_end-ball.getRadius(), r.posy_end-ball.getRadius());

    }

    SequentialTransition sequence = new SequentialTransition();
    for (Timeline var : timeline) {
      sequence.getChildren().add(var);
    }
    //sequence.setCycleCount(Timeline.INDEFINITE);
   // sequence.setAutoReverse(true);
    sequence.play();

  }

}