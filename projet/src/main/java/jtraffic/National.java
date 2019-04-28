package jtraffic;

import javafx.scene.paint.Color;

public class National extends Route {
  public National(Point b, Point e) {
    super(b, e);
    // super.setAffine();
    vitesse = 110;
    setStroke(Color.BLUE);
    setStrokeWidth(5);
  }

}