package jtraffic;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Vehicule extends Circle {
    public String name;
    public Point depart;
    public ArrayList<Route> path;
    public Label txt ;  

    public ArrayList<Timeline> timeline = new ArrayList<>();

    public Vehicule(String name, Point d) {
        super(7, Color.RED);
        this.name = name;
        this.depart = d;
        relocate(depart.getX(), depart.getY());

    }

    public void setPath(ArrayList<Route> path) {
        this.path = path;
    }

    public void play() {
        
        for (Route r : path) {
            
            timeline.add(new Timeline(new KeyFrame(Duration.seconds(r.timeto()),
                    new KeyValue(layoutXProperty(), r.p_end.getX() - getRadius()),
                    new KeyValue(layoutYProperty(), r.p_end.getY() - getRadius()))));
           
        }

        SequentialTransition sequence = new SequentialTransition();
        for (Timeline var : timeline) {
            sequence.getChildren().add(var);
        }
         sequence.setCycleCount(Timeline.INDEFINITE);
        sequence.setAutoReverse(true);
        sequence.play();

    }

}