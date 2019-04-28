
package jtraffic;

import javafx.scene.paint.Color;

public class Autoroute extends Route {
  int vitesse = 130;

  public Autoroute(Point b, Point e) {
    super(b, e);
    // super.setAffine();
    vitesse = 130;
    setStroke(Color.RED);
    setStrokeWidth(10);
  }
}