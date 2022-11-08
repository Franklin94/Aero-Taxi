package DAO;

import Model.Avion;
import Model.Bronze;
import Model.Gold;
import Model.Silver;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AvionDAO {

    public AvionDAO() {
    }
    
    //Método para levantar los aviones pre-existentes:
    public ArrayList<Avion> actualAvion(){
        
        String path = "airplanes";
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        ArrayList<Avion> planes = null;
        
        if(file.exists()){//Verificación de existencia del archivo.
            
            try{
                planes = mapper.readValue(file, ArrayList.class);
                
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
            
        }
        return planes;
        
        
    }
    
    //Método para guardar aviones nuevos:
    public void guardarAvion(Avion plane) throws IOException{

        String path = "\\airplanes";
        File file = new File(path);
        ArrayList<Avion> aircraft = actualAvion();//revisamos si ya hay aviones previamente guardados.
        ObjectMapper mapper = new ObjectMapper();
        
        if(aircraft == null){
                    
            try{
                mapper.writeValue(file, plane);


            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
        }
        else{
            aircraft.add(plane);
            }
            try{    
                mapper.writeValue(file,aircraft);//Sobrescribios el archivo con los nuevos aviones.
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
        }
    //Aviones disponibles para realizar vuelos:
        
    public ArrayList<Avion> availablePlanes(LocalDate date){
            
        ArrayList<Avion> allPlanes = actualAvion();
        ArrayList<Avion> availablePlanes = null;
        int k=0;
            
        for(Avion aircraft: allPlanes){
            if(aircraft.getDateOfUse().equals(date)){
                aircraft.setAvailable(false);
            }
            else{
                availablePlanes.add(aircraft);
            }
        }
            return availablePlanes;
    }
    //Seleccionador de avion deseado.
    public Avion selectAirplaneByType(String date){
        
        LocalDate departureDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        ArrayList<Avion> aircrafts = availablePlanes(departureDate);
        Scanner input = new Scanner(System.in);
        boolean proseguir = true;
        Avion selectedAirplane = null;
        boolean bronze, silver, gold;
        bronze=silver=gold=false;
        
        for(Avion airplane: aircrafts){
            if(airplane instanceof Bronze){
                bronze = true;                
            }
            else if(airplane instanceof Silver){
                silver = true;
            }
            else if(airplane instanceof Gold){
                gold = true;
            }
        }
        
        while(proseguir == true){    
            System.out.println("A continuación, se muestran los tipos de avión que hay disponibles. Seleccione el tipo de avión que desea:");
            
            if(bronze == true){
                System.out.println("1.Bronze.");
            }
            else if(silver == true){
                System.out.println("2.Silver.");
            }
            else if(gold == true){
                System.out.println("3.Gold.");
            }
            String answer = input.next();

            switch(answer){
                case "1":
                    for(Avion airplane: aircrafts){
                        if(airplane instanceof Bronze){
                            System.out.println(airplane.toString());                          
                        }
                    }
                    System.out.println("Ingrese la patente del avión que desea elegir:");
                    String patente = input.next();
                    for(Avion airplane: aircrafts){
                        if(airplane.getPatente().equals(patente)){
                            selectedAirplane = airplane;
                        }
                    }
                    proseguir = false;
                    break;
                case "2":
                    for(Avion airplane: aircrafts){
                        if(airplane instanceof Silver){
                            System.out.println(airplane.toString());
                        }
                    }
                    System.out.println("Ingrese la patente del avión que desea elegir:");
                    String patente2 = input.next();
                    for(Avion airplane: aircrafts){
                        if(airplane.getPatente().equals(patente2)){
                            selectedAirplane = airplane;
                        }
                    }
                    proseguir = false;
                    break;
                case "3":
                    for(Avion airplane: aircrafts){
                        if(airplane instanceof Gold){
                            System.out.println(airplane.toString());
                        }
                    }
                    System.out.println("Ingrese la patente del avión que desea elegir:");
                    String patente3 = input.next();
                    for(Avion airplane: aircrafts){
                        if(airplane.getPatente().equals(patente3)){
                            selectedAirplane = airplane;
                        }
                    }
                    proseguir = false;
                    break;
                default:
                    System.out.println("Ha ingresado una opción no válida. Por favor, vuelva a intentarlo.");
                    break;
                    
            }       
            
        }
        return selectedAirplane;
    }
       
}
    
  
  
    

