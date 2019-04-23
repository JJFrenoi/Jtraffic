package jtraffic;

import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class Frame extends Application{
  static final double W = 1640;
static final double H = 880;
      public Thread m ; 
    @Override
    public void start(Stage stage) throws Exception {
        
        BorderPane bp = new BorderPane(); 

        Reseau p = new Reseau() ;
        
        HBox hbox = new HBox() ; 
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: rgba(55, 0, 244, 0.5);");

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: rgba(255, 0, 244, 0.5);");

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("JavaFX " + javafxVersion + ", Java " + javaVersion + ".");

        Label title = new Label("DOUDY TRES MECHANTE") ; 
        title.setFont(Font.font ("Verdana",FontWeight.BOLD,20));


        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;-fx-alignment: bottom-center");
       
        hbox.getChildren().add(title);
             
                        
        box.getChildren().add(closeButton);
        box.getChildren().add(l);
             
                        
        VBox.setMargin(closeButton, new Insets(10, 10, 10, 10));
        VBox.setMargin(l, new Insets(10, 10, 10, 10));
        
        bp.setTop(hbox);
        bp.setCenter(p);
        bp.setBottom(box);

        closeButton.setOnAction(new ExitButtonListener());

        Scene scene = new Scene(bp, W, H);
        scene.setFill(null);
             

        stage.setScene(scene);
        stage.show();
       
      
        p.m.play(p.routes);
       
      
     
    }
    
     
    public class ExitButtonListener implements EventHandler<ActionEvent> {

      @Override
      public void handle(ActionEvent arg0) {
        Platform.exit();
      
      }
    }
}