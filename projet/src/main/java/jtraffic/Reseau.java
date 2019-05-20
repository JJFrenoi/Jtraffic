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
    public ArrayList<Point> savedPos = new ArrayList<>();
    public ArrayList<Point> intersections = new ArrayList<>();
    public ArrayList<Double> excludeRowsx = new ArrayList<>();
    public ArrayList<Double> excludeRowsy = new ArrayList<>();
    public ArrayList<Cities> cities = new ArrayList<>();
    public ArrayList<Route> routes = new ArrayList<>();
    public ArrayList<Route> routesAfterIntersec = new ArrayList<>();
    public ArrayList<Departemental> departementales = new ArrayList<>();
    public ArrayList<National> nationales = new ArrayList<>();
    public ArrayList<Autoroute> autoroutes = new ArrayList<>();
    public ArrayList<Integer> toremoveN = new ArrayList<>();
    public ArrayList<Integer> toremoveD = new ArrayList<>();
    public ArrayList<Integer> toremoveA = new ArrayList<>();
    public ArrayList<Integer> toremoveAfterIntersec = new ArrayList<>();
    Random rand = new Random();
    public Label txt;
    // public int posx = 0, posy = 0;
    public Point p;

    public Reseau() {
        setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        createCity();

        fillMapRoute();
        compareRoute();
        findX();

        printRoute2();
        printIndexCity();
        printIntersection();

        m = new Moto("Jenaj", cities.get(0).p_route);
        m.setPath(pathto(m, cities.get(4)));
        // m2 = new Moto("Jenaj", departementales.get(5).posx_begin,
        // departementales.get(5).posy_end);

        getChildren().add(m);
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
            savedPos.add(c.p_route);
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
                    r = new Autoroute(var.p_route, savedPos.get(cA));

                    autoroutes.add((Autoroute) r);
                    break;
                case 1:
                case 2:
                    r = new National(var.p_route, savedPos.get(cA));

                    nationales.add((National) r);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    r = new Departemental(var.p_route, savedPos.get(cA));

                    departementales.add((Departemental) r);

                    break;
                default:
                    r = new Departemental(var.p_route, savedPos.get(cA));

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
                    toremoveN.add(nationales.indexOf(v));
                }

            }
          
            for (Departemental x : departementales) {
                if (var.p_begin.equals(x.p_end) && x.p_end.equals(var.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoveD.add(departementales.indexOf(x));

                }
            }
            for (Autoroute v : autoroutes) {
                if (var.p_begin.equals(v.p_end) && var.p_end.equals(v.p_begin)) {
                    // System.out.println(nationales.indexOf(v));
                    toremoveA.add(autoroutes.indexOf(v));
                }
            }

        }

        for (National var : nationales) {
            for (Departemental x : departementales) {
                if (var.p_begin.equals(x.p_end) && var.p_end.equals(x.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoveN.add(nationales.indexOf(var));

                }
            }
        }
        for (Departemental var : departementales) {
            for (Departemental x : departementales) {
                if (!var.equals(x) && var.p_begin.equals(x.p_end) && var.p_end.equals(x.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoveD.add(departementales.indexOf(x));

                }
            }
        }
        for (National var : nationales) {
            for (National x : nationales) {
                if (!var.equals(x) && var.p_begin.equals(x.p_end) && var.p_end.equals(x.p_begin)) {
                    // System.out.println(departementales.indexOf(x));
                    toremoveN.add(nationales.indexOf(x));

                }
            }
        }
        // routes.clear();
        fillRoute();
    }

    public void findX() {
        // nationales.clear();
        // departementales.clear();
        // autoroutes.clear();
        for (Route a : routes) {
            for (Route n : routes) {

                Point p = calculIntersection(a.p_begin, a.p_end, n.p_begin, n.p_end);
                if (verifIntersectExist(a.p_begin, a.p_end, n.p_begin, n.p_end, p) && !a.equals(n)) {
                    intersections.add(p);
                    /*
                     * randomRoute(a.p_begin,p); randomRoute(n.p_begin, p); randomRoute(p, a.p_end);
                     * randomRoute(p, n.p_end); //toremoveAfterIntersec.add(routes.indexOf(a));
                     * //toremoveAfterIntersec.add(routes.indexOf(n)); if(a instanceof Autoroute ){
                     * toremoveA.add(routes.indexOf(a)); } else if (a instanceof National){
                     * toremoveN.add(routes.indexOf(a));
                     * 
                     * } else if (a instanceof Departemental ){ toremoveD.add(routes.indexOf(a)); }
                     * else if(n instanceof Autoroute ){ toremoveA.add(routes.indexOf(n)); } else if
                     * (n instanceof National){ toremoveN.add(routes.indexOf(n));
                     * 
                     * } else if (n instanceof Departemental ){ toremoveD.add(routes.indexOf(n)); }
                     * 
                     */
                }
            }
        }

        // compareRoute();

    }

    public ArrayList<Route> pathto(Moto m, Cities c) {
        ArrayList<Route> path = new ArrayList<>();
        Boolean bol = false;
       for (Route var : routes ) {
           if(m.depart.equals(var.p_begin) && c.p_route.equals(var.p_end) && bol == false )
            {
                path.add(var);
                bol = true ; 
            }
            
       }
        
       for (Route var : routes) {
         if ( m.depart.equals(var.p_begin) && bol == false  ){
            path.add(var);
            bol = true ; 
        }
       }
       try {
        while(!path.get(path.size()-1).p_end.equals(c.p_route) )
          {
            bol = false;
             for (Route var : routes) {
                 if (var.p_begin.equals(path.get(path.size()-1).p_end) && var.p_end.equals(c.p_route) && bol == false  ) {
                     bol = true ;
                     path.add(var); 
                                         
                 }
                 else if (var.p_begin.equals(path.get(path.size()-1).p_end)  && bol == false  ){
                    bol = true ; 
                    path.add(var);
                }
                }
                 
        
                
            
             
          }
       } catch (Exception e) {
          System.out.println("marche powa : " +e);
       }
       
        return path;
    }

    public static boolean verifIntersectExist(Point v1, Point v2, Point v3, Point v4, Point intersect) {
        if (intersect == null)
            return false;

        if (intersect.getX() < Math.max(v1.getX(), v2.getX()) && intersect.getX() > Math.min(v1.getX(), v2.getX())
                && intersect.getY() < Math.max(v1.getY(), v2.getY())
                && intersect.getY() > Math.min(v1.getY(), v2.getY())
                && intersect.getX() < Math.max(v3.getX(), v4.getX())
                && intersect.getX() > Math.min(v3.getX(), v4.getX())
                && intersect.getY() < Math.max(v3.getY(), v4.getY())
                && intersect.getY() > Math.min(v3.getY(), v4.getY()))
            return true;
        else
            return false;
    }

    public static Point calculIntersection(Point v1, Point v2, Point v3, Point v4) {
        double xA = v1.getX();
        double yA = v1.getY();
        double xB = v2.getX();
        double yB = v2.getY();
        double xC = v3.getX();
        double yC = v3.getY();
        double xD = v4.getX();
        double yD = v4.getY();

        // Soit (xAB;yAB) une vecteur directeur de la droite (AB)
        double xAB = xB - xA;
        double yAB = yB - yA;

        // Soit l'équation de la droite (AB) du type : yAB = axAB + b
        // a le coefficient directeur
        double coefDirecteurAB = (double) yAB / xAB;
        // Et A un point de la droite (AB) On peut donc trouver b
        // b = yA - coefDirecteurAB * xA
        double bAB = (double) yA - (coefDirecteurAB * xA);
        // On a un équation du type y = ax + b (avec a : coefDireceteurAB)

        // idem mais avec CD
        double xCD = xD - xC;
        double yCD = yD - yC;
        double coefDirecteurCD = (double) yCD / xCD;
        double bCD = (double) yC - (coefDirecteurCD * xC);
        // On a deux équations :
        // y = ax + b
        // y'= a'x+ b'
        // Soit le point d'intersection M((b'-b)/(a-a');(a*Mx)+b)

        double xIntersect = ((bCD - bAB) / (coefDirecteurAB - coefDirecteurCD));
        double yIntersect = (((coefDirecteurAB * xIntersect) + bAB));

        // Tenter une approximation en fonction de
        Point intersect = new Point(xIntersect, yIntersect);
        return intersect;
    }

    void printRoute() {

        for (National var : nationales) {
            if (!toremoveN.contains(nationales.indexOf(var))) {

                getChildren().add(var);
                txt = new Label("N" + nationales.indexOf(var));
                txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 13));
                Point2D mid = var.p_begin.midpoint(var.p_end);
                txt.relocate(mid.getX(), mid.getY());
                getChildren().add(txt);

            }
        }
        for (Departemental var : departementales) {
            if (!toremoveD.contains(departementales.indexOf(var))) {

                getChildren().add(var);
                txt = new Label("D" + departementales.indexOf(var));
                txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 9));
                Point2D mid = var.p_begin.midpoint(var.p_end);
                txt.relocate(mid.getX(), mid.getY());
                getChildren().add(txt);

            }
        }
        for (Autoroute var : autoroutes) {
            if (!toremoveA.contains(autoroutes.indexOf(var))) {
                getChildren().add(var);
                txt = new Label("A" + (autoroutes.indexOf(var) + 1));
                txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
                Point2D mid = var.p_begin.midpoint(var.p_end);
                txt.relocate(mid.getX(), mid.getY());
                getChildren().add(txt);
            }

        }

    }

    public void printRoute2() {
        for (Route var : routes) {
            if (!toremoveAfterIntersec.contains(routes.indexOf(var))) {
                getChildren().add(var);
                if (var instanceof Departemental) {

                    txt = new Label("D" + departementales.indexOf(var));
                    txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 9));
                    Point2D mid = var.p_begin.midpoint(var.p_end);
                    txt.relocate(mid.getX(), mid.getY());
                    getChildren().add(txt);
                } else if (var instanceof National) {

                    txt = new Label("N" + nationales.indexOf(var));
                    txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 13));
                    Point2D mid = var.p_begin.midpoint(var.p_end);
                    txt.relocate(mid.getX(), mid.getY());
                    getChildren().add(txt);
                } else if (var instanceof Autoroute) {

                    txt = new Label("A" + autoroutes.indexOf(var));
                    txt.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
                    Point2D mid = var.p_begin.midpoint(var.p_end);
                    txt.relocate(mid.getX(), mid.getY());
                    getChildren().add(txt);
                }

            }
        }
        /*
         * for (Route r : routesAfterIntersec) { getChildren().add(r); }
         */

    }

    public void printIndexCity() {
        for (Cities var : cities) {
            txt = new Label("" + cities.indexOf(var));
            txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            txt.relocate(var.p.getX() + 45, var.p.getY() + 65);
            getChildren().add(txt);
        }
    }

    public void printIntersection() {
        for (Point var : intersections) {
            getChildren().add(var.r);
        }
    }

    public void randomRoute(Point a, Point b) {

        int cD = rand.nextInt(6);

        Route r;
        switch (cD) {
        case 0:
            r = new Autoroute(a, b);
            // routesAfterIntersec.add(r);
            autoroutes.add((Autoroute) r);
            break;
        case 1:
        case 2:
            r = new National(a, b);
            // routesAfterIntersec.add(r);
            nationales.add((National) r);

            break;
        case 3:
        case 4:
        case 5:
        case 6:
            r = new Departemental(a, b);
            // routesAfterIntersec.add(r);
            departementales.add((Departemental) r);

            break;
        default:
            r = new Departemental(a, b);
            // routesAfterIntersec.add(r);
            departementales.add((Departemental) r);
        }

    }

    public void fillRoute() {
        for (Autoroute var : autoroutes) {
            if (!toremoveA.contains(autoroutes.indexOf(var))) {
                routes.add(var);
            }

        }
        for (Departemental var : departementales) {
            if (!toremoveD.contains(departementales.indexOf(var))) {
                routes.add(var);
            }

        }
        for (National var : nationales) {
            if (!toremoveN.contains(nationales.indexOf(var))) {
                routes.add(var);
            }

        }
    }

    public void routeToAND() {
        for (Route var : routes) {
            if (var instanceof Autoroute) {
                autoroutes.add((Autoroute) var);

            } else if (var instanceof National) {
                nationales.add((National) var);
            } else if (var instanceof Departemental) {
                departementales.add((Departemental) var);
            }
        }
    }

}
