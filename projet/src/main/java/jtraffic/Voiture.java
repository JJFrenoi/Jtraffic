package jtraffic;

import java.util.Random;

import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;
enum Marque {
  Ford, Fiat, Renault, Peugeot, Honda, Citroen, Audi;

  public static Marque getRandomMarque() {
      Random random = new Random();
      return values()[random.nextInt(values().length)];
  }
}

public class Voiture extends Vehicule {


  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/ford.png").toExternalForm();
  public Image image = new Image(imageUrl);

  public Voiture( Point d) {
    super( Marque.getRandomMarque().toString() ,  d);
    setFill(new ImagePattern(image));
    relocate(depart.getX(), depart.getY());

  }

}