package DAO;

import Model.Avion;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        String path = "airplanes";
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
            System.out.println("El avión ha sido guardado exitosamente.");
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
            System.out.println("Los aviones han sido guardados exitosamente.");
        }
        
    }
    //Método para que el VUELO venga a buscar su avion correspondiente.
  
  
    

