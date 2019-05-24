package jtraffic;

import java.util.Random;

import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;

enum Marque2 {
  Yamaha, Suzuki, Honda, Ducati, Triumph, BMW, Kawasaki;

  public static Marque2 getRandomMarque() {
      Random random = new Random();
      return values()[random.nextInt(values().length)];
  }
}

public class Moto extends Vehicule {

 
  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/bike.png").toExternalForm();
  public Image image = new Image(imageUrl);

  public Moto( Point d) {
    super( Marque2.getRandomMarque().toString() ,  d);
    setFill(new ImagePattern(image));
    relocate(depart.getX(), depart.getY());

  }

}