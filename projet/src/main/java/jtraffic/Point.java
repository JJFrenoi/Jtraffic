package jtraffic;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Point2D {

    Circle r = new Circle(10,Color.YELLOW); 
    public Point(double x, double y) {
        super(x, y);
        r.relocate(x, y);
    }
}