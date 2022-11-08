package DAO;

import Model.Avion;
import Model.Cliente;
import Model.Persona;
import Model.Vuelos;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VuelosDAO {
    
    //Atributes:
    private final String path ="flights"; 
    private ObjectMapper mapper;
    //CREADOR DE VUELOS, MODIFICADOR DE DATOS, PROGRAMACIÓN DE VUELOS, CANCELACIÓN DE VUELOS.
    
    
    //Saver de vuelos:
    public void flightSaver(Vuelos vuelo){
        
        File file = new File(path);
        ArrayList<Vuelos> flights = confirmedFlights();//revisamos si ya hay vuelos previamente guardados.
       
        if(flights == null){
                    
            try{
                mapper.writeValue(file, vuelo);


            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
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
        }
    
    //Confirmed flights:
    public ArrayList<Vuelos> confirmedFlights() {//getAllFlights()
       
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
    public Vuelos getFlight(LocalDate departureDate, Cliente client){
        
        ArrayList<Vuelos> flights = confirmedFlights();
        Vuelos outPutFlight = null;
        
        if(flights != null){
            for(Vuelos f: confirmedFlights()){
                if(f.getDepartureDate().equals(departureDate) && f.getPassengers().contains(client)){
                    outPutFlight = f;
                }
            }
        }
        return outPutFlight;
        
    }
    public void deleteFlight(Vuelos flight){
        
        File file = new File(path);
        ArrayList<Vuelos> flights = confirmedFlights();
        
        if(flights != null){
            for(Vuelos f: flights){
                if(f.equals(flight)){
                    flights.remove(f);
                }
            }
        
            try{
                mapper.writeValue(file, flights);
            }catch(IOException e){
            }
        }
    }
    public void modifyFlight(Vuelos flight,LocalDate departureDate, DestinosDAO.localidad origen, DestinosDAO.localidad destino, Avion avion, int paxconfirmados, String horario,ArrayList<Persona> passengers){
        
        File file = new File(path);
        ArrayList<Vuelos> allFlights = confirmedFlights();
        if(allFlights != null){
            for(Vuelos v1: allFlights){
                if(v1.equals(flight)){
                    flight.setDestino(destino);
                    flight.setAvion(avion);
                    flight.setDepartureDate(departureDate);
                    flight.setPassengers(passengers);
                    flight.setOrigen(origen);
                    flight.setHorario(horario);
                }
            }
            try{
                mapper.writeValue(file,allFlights);
            }catch(IOException e){
            
            }
        }
        
    }
  
}
