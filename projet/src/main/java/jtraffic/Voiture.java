package jtraffic;

import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;

public class Voiture extends Vehicule {

  public String name;
  public Point depart;
  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/ford.png").toExternalForm();
  public Image image = new Image(imageUrl);

  public Voiture(String name, Point d) {
    super(name, d);
    this.name = name;
    this.depart = d;
    setFill(new ImagePattern(image));
    relocate(depart.getX(), depart.getY());

  }

}