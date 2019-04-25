package jtraffic;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Reseau extends Pane {
    public Moto m, m2; 
    public ArrayList<Integer> savedPosx = new ArrayList<>() ; 
    public ArrayList<Integer> savedPosy = new ArrayList<>() ;
    ArrayList<Integer> excludeRowsx = new ArrayList<>();
    ArrayList<Integer> excludeRowsy = new ArrayList<>();
    public ArrayList<Cities> cities = new ArrayList<>() ; 
    public ArrayList<Route> routes = new ArrayList<>() ;
    public ArrayList<Route> departementales = new ArrayList<>() ;
    Random rand = new Random(); 
    public Label txt ; 
    public int posx = 0 , posy = 0 ; 
    
    public Reseau(){
        setStyle( "-fx-padding: 10;" +
        "-fx-border-style: solid inside;" +
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: blue;");
        createCity(); 
        m = new Moto("Jenaj", routes.get(0).posx_begin, routes.get(0).posy_begin);
        m2 = new Moto("Jenaj", departementales.get(5).posx_begin, departementales.get(5).posy_begin);
        getChildren().add(m.ball);
        getChildren().add(m2.ball);
        //rintexclusions();
    }
    public void createCity(){
        this.posx = rand.nextInt(1600);
        this.posy = rand.nextInt(700);
        cities.add(new Cities(this.posx , this.posy ));
        exclusions(posx, posy);

        for(int i = 0 ; i<6 ; i++){
           this.posx = nextCity(1, 1600, excludeRowsx);
           this.posy = nextCity(1, 700, excludeRowsy);
            
            cities.add(new Cities(this.posx , this.posy ));

            exclusions(posx, posy);
        }

         int j = 1 ; 
         for (Cities c  : cities) {
             getChildren().add(c.city);
             getChildren().add(c.perif);  
             savedPosx.add(c.posx);
             savedPosy.add(c.posy);   
          }
          /*
          for(Cities c : cities ){
              
            for(int i = 0 ; i<4 ; i++){
                int r = rand.nextInt(6);
              
            while( ){
              r =  rand.nextInt(6);
            }

              
              departementales.add(new Route(c.posx, c.posy , savedPosx.get(r),savedPosy.get(r),Color.GOLD ,2));
            }
         
         
        }
      for (Route var : departementales) {
          getChildren().add(var); 
      }
      */
          
          for(Cities c : cities){
            if (j<7){
                routes.add(new Route(c.posx, c.posy , savedPosx.get(j),savedPosy.get(j),Color.RED ,10));
                for (int i = 0; i < 6; i++) {
                    if(i!=j){
                        departementales.add(new Route(c.posx, c.posy , savedPosx.get(i),savedPosy.get(i),Color.GOLD ,2));
                    }
                    
                }
                
                j++;

            }
            
            
          }
          for(Route r : routes ){
            getChildren().add(r); 
          }
          for (Route var : departementales) {
            getChildren().add(var); 
        }
          for (Cities var : cities) {
              txt = new Label(""+cities.indexOf(var));
              txt.setFont(Font.font ("Verdana",FontWeight.BOLD,20));
              txt.relocate(var.posx+45, var.posy+65);
              getChildren().add(txt);
          }
         for (Route var : routes) {
            txt = new Label("A"+routes.indexOf(var));
            txt.setFont(Font.font ("Verdana",FontWeight.LIGHT, 20));
            txt.relocate( (var.posx_begin+var.posx_end)/2 , (var.posy_begin+var.posy_end)/2 ) ;
            getChildren().add(txt);
         }
     
    }
    
    public int nextCity(int start, int end, ArrayList<Integer> excludeRows) {
            Random rand = new Random();
            int range = end - start + 1;
        
            int random = rand.nextInt(range) + 1;
            while(excludeRows.contains(random)) {
                random = rand.nextInt(range) + 1;
            }
        
            return random;
 
 
        }

        public void exclusions(int x , int y ){
            for(int i = x -125 ; i < x+125 ; i++){
                excludeRowsx.add(i);
                
            }
            for(int j = y -75 ; j< y +75 ; j ++){
                excludeRowsy.add(j);
            }
        }
        public void printexclusions(){
            for ( Integer  var : excludeRowsx) {
                System.out.print(var+" "); 
            }
        }
}
