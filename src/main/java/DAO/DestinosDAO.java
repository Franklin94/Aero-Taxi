package DAO;

import java.util.Scanner;

public class DestinosDAO {
    
    //Atributes:
    
    public enum localidad{BuenosAires,Cordoba,SANTIAGO,MONTEVIDEO};
    
    //Methods:

    public DestinosDAO(){
        
    }
    public localidad[] mostrarDestinos(){
        
        return localidad.values();
    }
  

    public localidad seleccionarDestino() {
        
        Scanner input = new Scanner(System.in);
        int j=0;
        boolean flag = true;
        String answer2;
        int index;
        String answer = "1";
        
        for(localidad city: localidad.values()){
            System.out.println((j+1)+"."+city);
        }
        while(flag == true){
            System.out.println("Seleccione la localidad (presione el número que figura al lado de la misma):");
            answer = input.next();
            
            switch(answer){

                case "1":
                    System.out.println(localidad.BuenosAires);
                    System.out.println("¿La ciudad seleccionada es correcta?\nPresione 1 para confirmar ó cualquier otra tecla para volver a elegir.");
                    answer2 = input.next();
                    if(answer2.equals("1")){
                        flag = false;
                    }
                    break;
                case "2":
                    System.out.println(localidad.Cordoba);
                    System.out.println("¿La ciudad seleccionada es correcta?\nPresione 1 para confirmar ó cualquier otra tecla para volver a elegir.");
                    answer2 = input.next();
                    if(answer2.equals("1")){
                        flag = false;
                    }
                    break;
                case "3":
                    System.out.println(localidad.MONTEVIDEO);
                    System.out.println("¿La ciudad seleccionada es correcta?\nPresione 1 para confirmar ó cualquier otra tecla para volver a elegir.");
                    answer2 = input.next();
                    if(answer2.equals("1")){
                        flag = false;
                    }
                    break;
                case "4":
                    System.out.println(localidad.SANTIAGO);
                    System.out.println("¿La ciudad seleccionada es correcta?\nPresione 1 para confirmar ó cualquier otra tecla para volver a elegir.");
                    answer2 = input.next();
                    if(answer2.equals("1")){
                        flag = false;
                    }
                    break;
                default:
                    System.out.println("La opción elegida no se corresponde con ningún destino.");
                    break;
            }
        }
        index = Integer.parseInt(answer)-1;//Parseo de la respuesta.
        
        return mostrarDestinos()[index];//Devuelve la posición del array nativo de cities.
    }
    public int cantidadKm(localidad origen, localidad destino){
        int cantkm = 0;
        
        if((origen == localidad.BuenosAires && destino == localidad.Cordoba )|| (origen == localidad.Cordoba && destino == localidad.BuenosAires)){
            cantkm = 695;
        }
        else if((origen == localidad.BuenosAires && destino == localidad.MONTEVIDEO )|| (origen == localidad.MONTEVIDEO && destino == localidad.BuenosAires)){
            cantkm = 950;
        }
        else if((origen == localidad.BuenosAires && destino == localidad.SANTIAGO )|| (origen == localidad.SANTIAGO && destino == localidad.BuenosAires)){
            cantkm = 1400;
        }
        else if((origen == localidad.MONTEVIDEO && destino == localidad.Cordoba )|| (origen == localidad.Cordoba && destino == localidad.MONTEVIDEO)){
            cantkm = 1190;
        }
        else if((origen == localidad.SANTIAGO && destino == localidad.Cordoba )|| (origen == localidad.Cordoba && destino == localidad.SANTIAGO)){
            cantkm = 1050;    
        }
        else if((origen == localidad.SANTIAGO && destino == localidad.MONTEVIDEO )|| (origen == localidad.MONTEVIDEO && destino == localidad.SANTIAGO)){
            cantkm = 2100;
        }
        return cantkm;
    }
    
}
