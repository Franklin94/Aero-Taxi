package Core;

public class Principal {
    
    public static void main(String[] args){
       Sistema AeroTaxi = new Sistema(); 
       Menu menu = new Menu(AeroTaxi);
       menu.startingPage();
    }
    
}
