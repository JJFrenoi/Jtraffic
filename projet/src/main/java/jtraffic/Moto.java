package jtraffic;

import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;

public class Moto extends Vehicule {

  public String name;
  public Point depart;
  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/bike.png").toExternalForm();
  public Image image = new Image(imageUrl);

  public Moto(String name, Point d) {
    super(name, d);
    this.name = name;
    this.depart = d;
    setFill(new ImagePattern(image));
    relocate(depart.getX(), depart.getY());

  }

}