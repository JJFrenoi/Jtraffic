package jtraffic;

import javafx.scene.paint.Color;

public class Departemental extends Route {

  public Departemental(Point b, Point e) {
    super(b, e);
    // super.setAffine();
    vitesse = 90;
    setStroke(Color.GREEN);
    setStrokeWidth(3);
  }
}