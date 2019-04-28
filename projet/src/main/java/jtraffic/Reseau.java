package jtraffic;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Reseau extends Pane {
    public Moto m, m2;
    // public ArrayList<Integer> savedPosx = new ArrayList<>();
    // public ArrayList<Integer> savedPosy = new ArrayList<>();
    public ArrayList<Point> savedPos = new ArrayList<>();
    public ArrayList<Double> excludeRowsx = new ArrayList<>();
    public ArrayList<Double> excludeRowsy = new ArrayList<>();
    public ArrayList<Cities> cities = new ArrayList<>();
    public ArrayList<Route> routes = new ArrayList<>();
    public ArrayList<Departemental> departementales = new ArrayList<>();
    public ArrayList<National> nationales = new ArrayList<>();
    public ArrayList<Autoroute> autoroutes = new ArrayList<>();
    public ArrayList<Integer> toremove = new ArrayList<>();
    public ArrayList<Integer> toremoved = new ArrayList<>();
    Random rand = new Random();
    public Label txt;
    // public int posx = 0, posy = 0;
    public Point p;

    public Reseau() {
        setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        createCity();
        // m = new Moto("Jenaj", routes.get(0).posx_begin, routes.get(0).posy_begin);
        // m2 = new Moto("Jenaj", departementales.get(5).posx_begin,
        // departementales.get(5).posy_end);

        // getChildren().add(m);
        // getChildren().add(m2);

    }

    public void createCity() {
        // this.posx = rand.nextInt(1600);
        // this.posy = rand.nextInt(700);
        this.p = new Point(rand.nextInt(1600), rand.nextInt(700));
        cities.add(new Cities(p));
        // exclusions(posx, posy);
        exclusions(p);

        for (int i = 0; i < 6; i++) {
            // this.posx = nextCity(1, 1600, excludeRowsx);
            // this.posy = nextCity(1, 700, excludeRowsy);
            this.p = nextCity(1, 1600, 1, 700, excludeRowsx, excludeRowsy);
            cities.add(new Cities(p));

            exclusions(p);
        }

        for (Cities c : cities) {
            getChildren().add(c.city);
            getChildren().add(c.perif);
            // savedPosx.add(c.posx);
            // savedPosy.add(c.posy);
            savedPos.add(c.p);
        }

        fillMapRoute();
        compareRoute();
        findX();

        for (National var : nationales) {
            if (!toremove.contains(nationales.indexOf(var))) {
                getChildren().add(var);
                txt = new Label("N" + nationales.indexOf(var));
                txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 13));
                Point2D mid = var.p_begin.midpoint(var.p_end);
                txt.relocate(mid.getX(), mid.getY());
                getChildren().add(txt);
            }
        }
        for (Departemental var : departementales) {
            if (!toremoved.contains(departementales.indexOf(var))) {
                getChildren().add(var);
                txt = new Label("D" + departementales.indexOf(var));
                txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 9));
                Point2D mid = var.p_begin.midpoint(var.p_end);
                txt.relocate(mid.getX(), mid.getY());
                getChildren().add(txt);
            }
        }
        for (Autoroute var : autoroutes) {
            getChildren().add(var);
            txt = new Label("A" + autoroutes.indexOf(var));
            txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
            Point2D mid = var.p_begin.midpoint(var.p_end);
            txt.relocate(mid.getX(), mid.getY());
            getChildren().add(txt);
        }

        for (Cities var : cities) {
            txt = new Label("" + cities.indexOf(var));
            txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            txt.relocate(var.p.getX() + 45, var.p.getY() + 65);
            getChildren().add(txt);
        }

    }

    public Point nextCity(int startx, int endx, int starty, int endy, ArrayList<Double> excludeRowsx,
            ArrayList<Double> excludeRowsy) {
        Random rand = new Random();
        int range = endx - startx + 1;
        int range2 = endy - starty + 1;
        double random = rand.nextInt(range) + 1;
        double random2 = rand.nextInt(range2) + 1;
        while (excludeRowsx.contains(random)) {
            random = rand.nextInt(range) + 1;

        }
        while (excludeRowsy.contains(random2)) {
            random2 = rand.nextInt(range2) + 1;
        }

        return new Point(random, random2);

    }

    public void exclusions(Point x) {
        for (double i = x.getX() - 125; i < x.getX() + 125; i++) {
            excludeRowsx.add(i);

        }
        for (double j = x.getY() - 75; j < x.getY() + 75; j++) {
            excludeRowsy.add(j);
        }
    }

    public void printexclusions() {
        for (Double var : excludeRowsx) {
            System.out.print(var + " ");
        }
    }

    public void fillMapRoute() {
        int cD = 0, cA = 0;
        ArrayList<Integer> ban = new ArrayList<>();
        Route r;
        for (Cities var : cities) {
            for (int i = 0; i < 3; i++) {
                cD = rand.nextInt(6);

                do {
                    cA = rand.nextInt(6);
                } while (cA == cities.indexOf(var) || ban.contains(cA));
                switch (cD) {
                case 0:
                    r = new Autoroute(var.p, savedPos.get(cA));
                    routes.add(r);
                    autoroutes.add((Autoroute) r);
                    break;
                case 1:
                case 2:
                    r = new National(var.p, savedPos.get(cA));
                    routes.add(r);
                    nationales.add((National) r);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    r = new Departemental(var.p, savedPos.get(cA));
                    routes.add(r);
                    departementales.add((Departemental) r);

                    break;
                default:
                    r = new Departemental(var.p, savedPos.get(cA));
                    routes.add(r);
                    departementales.add((Departemental) r);
                }

                ban.add(cA);
            }
            ban.clear();

        }

    }

    public void compareRoute() {
        for (Autoroute var : autoroutes) {

            for (National v : nationales) {

                if (var.p_begin.equals(v.p_end) && var.p_end.equals(v.p_begin)) {
                    // System.out.println(nationales.indexOf(v));
                    toremove.add(nationales.indexOf(v));
                }

            }
            /*
             * var.posx_begin == x.posx_end && var.posx_end == x.posx_begin &&
             * var.posy_begin == x.posy_end && var.posy_end == x.posy_begin
             */
            for (Departemental x : departementales) {
                if (var.p_begin.equals(x.p_end) && x.p_end.equals(var.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoved.add(departementales.indexOf(x));

                }
            }

        }

        for (National var : nationales) {
            for (Departemental x : departementales) {
                if (var.p_begin.equals(x.p_end) && var.p_end.equals(x.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoved.add(departementales.indexOf(x));

                }
            }
        }
        for (Departemental var : departementales) {
            for (Departemental x : departementales) {
                if (var != x && var.p_begin.equals(x.p_end) && var.p_end.equals(x.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoved.add(departementales.indexOf(x));

                }
            }
        }
        for (National var : nationales) {
            for (National x : nationales) {
                if (!var.equals(x) && var.p_begin.equals(x.p_end) && var.p_end.equals(x.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremove.add(nationales.indexOf(x));

                }
            }
        }
    }

    public void findX() {

        /*
         * for (Autoroute a : autoroutes) { for (National n : nationales) { for (double
         * i = a.posx_begin; i < a.posx_end; i++) { for (double j = n.posx_begin; j <
         * n.posx_end; j++) { if ((a.coeffD * i + a.b) == (n.coeffD * j + n.b)) {
         * System.out.println((a.coeffD * i + a.b)); nationales.add(new
         * National(n.posx_begin, n.posy_begin, j, (n.coeffD * j + n.b)));
         * nationales.add(new National(j, (n.coeffD * j + n.b), n.posx_end,
         * n.posy_end)); autoroutes.add(new Autoroute(a.posx_begin, a.posy_begin, j,
         * (a.coeffD * j + a.b))); autoroutes.add(new Autoroute(j, (a.coeffD * j + a.b),
         * a.posx_end, a.posy_end)); toremove.add(autoroutes.indexOf(a));
         * toremove.add(nationales.indexOf(n)); } } }
         * 
         * } /* for (Departemental n : departementales) { for(double i = a.posx_begin ;
         * i< a.posx_end ; i++ ){ for (double j = n.posx_begin; j < n.posx_end ; j++) {
         * if( (a.coeffD * i+ a.b) == (n.coeffD*j +n.b) ){ System.out.println((a.coeffD
         * * i+ a.b)); departementales.add(new Departemental(n.posx_begin,n.posy_begin ,
         * j , (n.coeffD*j +n.b))) ; departementales.add(new Departemental(j,(n.coeffD*j
         * +n.b) , n.posx_end, n.posy_end)); autoroutes.add(new
         * Autoroute(a.posx_begin,a.posy_begin , j , (a.coeffD*j +a.b))) ;
         * autoroutes.add(new Autoroute(j,(a.coeffD*j +a.b) , a.posx_end, a.posy_end));
         * toremove.add(autoroutes.indexOf(a));
         * toremoved.add(departementales.indexOf(n)); } } }
         * 
         * }
         * 
         * } /* for (National a : nationales) { for(double i = a.posx_begin ; i<
         * a.posx_end ; i++ ){}
         * 
         * for (Departemental n : departementales) { for (double j = n.posx_begin; j <
         * n.posx_end ; j++) {
         * 
         * }
         * 
         * 
         * } if( (a.coeffD * i+ a.b) == (n.coeffD*j +n.b) ){
         * System.out.println((a.coeffD * i+ a.b)); departementales.add(new
         * Departemental(n.posx_begin,n.posy_begin , j , (n.coeffD*j +n.b))) ;
         * departementales.add(new Departemental(j,(n.coeffD*j +n.b) , n.posx_end,
         * n.posy_end)); nationales.add(new National(a.posx_begin,a.posy_begin , j ,
         * (a.coeffD*j +a.b))) ; nationales.add(new National(j,(a.coeffD*j +a.b) ,
         * a.posx_end, a.posy_end)); toremove.add(nationales.indexOf(a));
         * toremoved.add(departementales.indexOf(n)); } }
         */

        for (Autoroute a : autoroutes) {
            for (National n : nationales) {
                if (a.intersects(n.getBoundsInLocal())) {
                    System.out.println("Yes");
                }

            }
        }
    }
}
