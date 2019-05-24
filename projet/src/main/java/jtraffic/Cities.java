package jtraffic;



import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class Cities {

  public ClassLoader classLoader = getClass().getClassLoader();
  public String imageUrl = classLoader.getResource("img/city.png").toExternalForm();
  public Image image = new Image(imageUrl);
  public Node city = new ImageView(image);
  public Point p;
  public Circle perif = new Circle();
  public Point p_route ; 

  public Cities(Point p) {
    this.p = p;
    this.p_route = new Point(p.getX()+115, p.getY()+65 );
    perif.setCenterX(p.getX() + 45);
    perif.setCenterY(p.getY() + 65);
    perif.setRadius(70);
    perif.setFill(Color.TRANSPARENT);
    perif.setStroke(Color.BLUEVIOLET);
    perif.setStrokeWidth(6);
    perif.setStrokeType(StrokeType.CENTERED);
    perif.setStrokeLineCap(StrokeLineCap.ROUND);
    perif.setStrokeLineJoin(StrokeLineJoin.ROUND);

    this.city.relocate(p.getX(), p.getY());
  }

}
