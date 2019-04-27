package jtraffic;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Reseau extends Pane {
    public Moto m, m2;
    public ArrayList<Integer> savedPosx = new ArrayList<>();
    public ArrayList<Integer> savedPosy = new ArrayList<>();
    public ArrayList<Integer> excludeRowsx = new ArrayList<>();
    public ArrayList<Integer> excludeRowsy = new ArrayList<>();
    public ArrayList<Cities> cities = new ArrayList<>();
    public ArrayList<Route> routes = new ArrayList<>();
    public ArrayList<Departemental> departementales = new ArrayList<>();
    public ArrayList<National> nationales = new ArrayList<>();
    public ArrayList<Autoroute> autoroutes = new ArrayList<>();
    public ArrayList<Integer> toremove = new ArrayList<>();
    public ArrayList<Integer> toremoved = new ArrayList<>();
    Random rand = new Random();
    public Label txt;
    public int posx = 0, posy = 0;

    public Reseau() {
        setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        createCity();
         //m = new Moto("Jenaj", routes.get(0).posx_begin, routes.get(0).posy_begin);
        //m2 = new Moto("Jenaj", departementales.get(5).posx_begin, departementales.get(5).posy_end);
        
        //getChildren().add(m);
       // getChildren().add(m2);
        
    }

    public void createCity() {
        this.posx = rand.nextInt(1600);
        this.posy = rand.nextInt(700);
        cities.add(new Cities(this.posx, this.posy));
        exclusions(posx, posy);

        for (int i = 0; i < 6; i++) {
            this.posx = nextCity(1, 1600, excludeRowsx);
            this.posy = nextCity(1, 700, excludeRowsy);

            cities.add(new Cities(this.posx, this.posy));

            exclusions(posx, posy);
        }

        for (Cities c : cities) {
            getChildren().add(c.city);
            getChildren().add(c.perif);
            savedPosx.add(c.posx);
            savedPosy.add(c.posy);
        }
       

        fillMapRoute();
        compareRoute();
       
        for (National var : nationales) {
            if(!toremove.contains(nationales.indexOf(var)) ){
            getChildren().add(var);
               
            }
        }
        for (Departemental var : departementales) {
            if(!toremoved.contains(departementales.indexOf(var)) ){
                getChildren().add(var);
                   
                }
        }
        for (Autoroute var : autoroutes) {
            getChildren().add(var);
            txt = new Label("A" + autoroutes.indexOf(var));
            txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
            txt.relocate((var.posx_begin + var.posx_end) / 2, (var.posy_begin + var.posy_end) / 2);
            getChildren().add(txt);
        }
        
      /* for (Route var : routes) {
           getChildren().add(var);
           if (var instanceof Autoroute){
            txt = new Label("A" + routes.indexOf(var));
            txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
            txt.relocate((var.posx_begin + var.posx_end) / 2, (var.posy_begin + var.posy_end) / 2);
            getChildren().add(txt);
        
           }
       }*/
        for (Cities var : cities) {
            txt = new Label("" + cities.indexOf(var));
            txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            txt.relocate(var.posx + 45, var.posy + 65);
            getChildren().add(txt);
        }
        
            
               

        

    }

    public int nextCity(int start, int end, ArrayList<Integer> excludeRows) {
        Random rand = new Random();
        int range = end - start + 1;

        int random = rand.nextInt(range) + 1;
        while (excludeRows.contains(random)) {
            random = rand.nextInt(range) + 1;
        }

        return random;

    }

    public void exclusions(int x, int y) {
        for (int i = x - 125; i < x + 125; i++) {
            excludeRowsx.add(i);

        }
        for (int j = y - 75; j < y + 75; j++) {
            excludeRowsy.add(j);
        }
    }

    public void printexclusions() {
        for (Integer var : excludeRowsx) {
            System.out.print(var + " ");
        }
    }

    public void fillMapRoute() {
        int cD = 0 , cA=0;
        ArrayList<Integer> ban = new ArrayList<>();
        for (Cities var : cities) {
            for (int i = 0; i < 3; i++) {
                cD = rand.nextInt(6);

                do {
                    cA = rand.nextInt(6);
                } while (cA == cities.indexOf(var) || ban.contains(cA) );
               switch (cD) {
                case 0:
                    autoroutes.add(new Autoroute(var.posx, var.posy, savedPosx.get(cA), savedPosy.get(cA)));
                    break;
                case 1 :
                case 2 : 
                    nationales.add(new National(var.posx, var.posy, savedPosx.get(cA), savedPosy.get(cA)));
                    break;
                case 3 :
                case 4 :
                case 5 :
                case 6 :
                    departementales.add(new Departemental(var.posx, var.posy, savedPosx.get(cA), savedPosy.get(cA)));
                    break;
                default: 
                    departementales.add(new Departemental(var.posx, var.posy, savedPosx.get(cA), savedPosy.get(cA)));
               }
             
               ban.add(cA);
            }
            ban.clear();

        }
        
    }
       /* for (Route var : routes) {
            for (Route v : routes) {
                if(var != v && var.posx_begin == v.posx_end && var.posx_end == v.posx_begin && var.posy_begin == v.posy_end && var.posy_end == v.posy_begin ){
                   System.out.println(routes.indexOf(v));
                  toremove.add(v);
                 
               
            }
        }
     
    
        
    }
    /*
    public void detectX(){
        for (Route var : routes) {
            
        }
    }*/
    public void compareRoute(){
        for (Autoroute var : autoroutes) {
            for (National v : nationales) {
                if(var.posx_begin == v.posx_end && var.posx_end == v.posx_begin && var.posy_begin == v.posy_end && var.posy_end == v.posy_begin ){
                    System.out.println(nationales.indexOf(v));
                    toremove.add(nationales.indexOf(v)); 
        
            }
            for (Departemental x: departementales) {
                if(var.posx_begin == x.posx_end && var.posx_end == x.posx_begin && var.posy_begin == x.posy_end && var.posy_end == x.posy_begin ){
                    System.out.println(departementales.indexOf(x));
                    toremoved.add(departementales.indexOf(x)); 
        
            }
        }
    }
    }
    for (National var : nationales) {
        for (Departemental x: departementales) {
            if(var.posx_begin == x.posx_end && var.posx_end == x.posx_begin && var.posy_begin == x.posy_end && var.posy_end == x.posy_begin ){
                System.out.println(departementales.indexOf(x));
                toremoved.add(departementales.indexOf(x)); 
    
        }
    }
}
    }
}
