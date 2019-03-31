
 
public class Main {
    public static void main(String s[]) {
    
     Frame a = new Frame();
     a.display(); 
     new Thread(a.panneau).start();  
      
       
    }
}