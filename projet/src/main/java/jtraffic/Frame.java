package jtraffic;

import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Frame extends Application{
     Button closeButton;
    
    @Override
    public void start(Stage stage) throws Exception {
       
        VBox box = new VBox();
        box.setStyle("-fx-alignment: bottom-center;-fx-orientation : vertical;-fx-column-valignment: center ; -fx-vgap : 20px; -fx-hgap:20px; ");
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("JavaFX " + javafxVersion + ", Java " + javaVersion + ".");
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;-fx-alignment: bottom-center");
        box.getChildren().add(closeButton);
        box.getChildren().add(l);
        VBox.setMargin(closeButton, new Insets(10, 10, 10, 10));
        VBox.setMargin(l, new Insets(10, 10, 10, 10));
        closeButton.setOnAction(new ExitButtonListener());
        Scene scene = new Scene(box, 640, 480);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
        
       
    }
    public class ExitButtonListener implements EventHandler<ActionEvent> {

      @Override
      public void handle(ActionEvent arg0) {
        Platform.exit();
      }
    }
}