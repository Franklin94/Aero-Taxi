package DAO;

import Model.Avion;
import Model.Persona;
import Model.Vuelos;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VuelosDAO {
    
    //CREADOR DE VUELOS, MODIFICADOR DE DATOS, PROGRAMACIÓN DE VUELOS, CANCELACIÓN DE VUELOS.
    
    
    //Saver de vuelos:
    public void flightSaver(Vuelos vuelo){
        
        String path = "\\flights";
        File file = new File(path);
        ArrayList<Vuelos> flights = confirmedFlights();//revisamos si ya hay vuelos previamente guardados.
        ObjectMapper mapper = new ObjectMapper();
       
        if(flights == null){
                    
            try{
                mapper.writeValue(file, vuelo);


            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
            System.out.println("El avión ha sido guardado exitosamente.");
        }
        else{
            flights.add(vuelo);
            }
            try{    
                mapper.writeValue(file,flights);//Sobrescribios el archivo con los nuevos aviones.
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
            System.out.println("El vuelo ha sido guardado exitosamente.");
        }
    
    //Confirmed flights:
    public ArrayList<Vuelos> confirmedFlights() {
       
        String path = "\\flights";
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        ArrayList<Vuelos> flights = null;
        
        if(file.exists()){//Verificación de existencia del archivo.
            
            try{
                flights = mapper.readValue(file, ArrayList.class);
                
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
            
        }
        return flights;
    }
    
    
}
