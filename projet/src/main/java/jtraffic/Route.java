package jtraffic;

import com.sun.javafx.geom.Shape;
import java.lang.Math.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class Route extends Line {
  public Point p_begin;
  public Point p_end;
  // public double posx_begin ;
  // public double posy_begin ;
  // public double posx_end ;
  // public double posy_end ;
  public Color p;
  public int taille;
  public int vitesse;
  public double coeffD = 0;
  public double b = 0;

  public Route(Point b, Point e) {
    super(b.getX(), b.getY() , e.getX(), e.getY() );
    this.p_begin = new Point(b.getX(), b.getY() );
    setStrokeType(StrokeType.CENTERED);
    setStrokeLineCap(StrokeLineCap.ROUND);
    setStrokeLineJoin(StrokeLineJoin.ROUND);
    this.p_end = new Point(e.getX(), e.getY());
    setAffine();

  }

  public double timeto() {
    double t = 0;
    t = Math.sqrt(Math.pow((p_end.getX() - p_begin.getX()), 2) + Math.pow((p_end.getY() - p_begin.getY()), 2))
        / vitesse;
    return t;
  }

  public void setAffine() {
    coeffD = ((p_end.getY() - p_begin.getY()) / (p_end.getX() - p_begin.getX()));
    b = p_begin.getY() - coeffD;
    // System.out.println(coeffD +" b = " + b);
  }
}