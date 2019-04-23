package jtraffic;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;

public class Reseau extends Pane {
    public Moto m ; 
    public ArrayList<Integer> savedPosx = new ArrayList<>() ; 
    public ArrayList<Integer> savedPosy = new ArrayList<>() ;
    ArrayList<Integer> excludeRowsx = new ArrayList<>();
    ArrayList<Integer> excludeRowsy = new ArrayList<>();
    public ArrayList<Cities> cities = new ArrayList<>() ; 
    public ArrayList<Route> routes = new ArrayList<>() ;
    Random rand = new Random(); 
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
        getChildren().add(m.ball);
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
         for (Cities c  : cities) {
             getChildren().add(c.city);
             getChildren().add(c.perif);  
             savedPosx.add(c.posx);
             savedPosy.add(c.posy);   
          }
          int i = 0 ;
          int j = 1 ; 
          for(Cities c : cities){
            if (i<7 && j<7){
                routes.add(new Route(savedPosx.get(i), savedPosy.get(i) , savedPosx.get(j),savedPosy.get(j)));
                getChildren().add(routes.get(i)); 
                i++;
                j++;
            }
            
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
            for(int i = x -70 ; i < x+70 ; i++){
                excludeRowsx.add(i);
                
            }
            for(int j = y -70 ; j< y +70 ; j ++){
                excludeRowsy.add(j);
            }
        }
        public void printexclusions(){
            for ( Integer  var : excludeRowsx) {
                System.out.print(var+" "); 
            }
        }
}
