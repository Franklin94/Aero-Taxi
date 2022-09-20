package DAO;

import Core.Sistema;
import Model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientesDAO {
    
    //CREADOR DE CLIENTES, VERIFICACIÓN DE EXISTENCIA, DAR DE BAJA CLIENTE.
    
    //Methods:
    public ClientesDAO(){
    }

    //Eliminar un cliente:
    public void modificarCliente() throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<Cliente> clients = actualCliente();
        boolean flag = true;
        int k=0;
        
        while(flag == true){
            System.out.println("Ingrese el dni del cliente que desea modificar:");
            String DNI = input.next();
            
            for(Cliente klient: clients){

                if(klient.getDNI().equals(DNI)){
                    clients.remove(clients.indexOf(klient));
                    System.out.println("Reingrese todos los datos del cliente por favor.");
                    Sistema a = new Sistema();
                    a.signIn();
                    flag = false;    
                }
                else{
                    k++;
                }
            }
            if(clients.size() == k){
                System.out.println("El DNI ingresado es incorrecto o el usuario no existe.");
                System.out.println("Seleccione la operación que desea realizar:\n1.Modificar sus datos (presione 1). \n2.Cancelar (presione cualquier tecla)");
                String ans = input.next();
                if(!ans.equals("1")){
                    flag = false;
                }
            }
        }
    }
    
    //Levantar clientes pre-existentes:
    
    public ArrayList<Cliente> actualCliente(){
        
        String path = "\\clientes";
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Cliente> client = null;
        
        if(file.exists()){
            try{
                client = mapper.readValue(file, ArrayList.class);
                
                
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
        }
        return client;
    }
    //Guardar clientes:
    
    public void guardarCliente(Cliente p){
        
        String path = "\\clientes";
        File file = new File(path);
        ArrayList<Cliente> client = actualCliente();
        ObjectMapper mapper = new ObjectMapper();
        
        if(client != null){
            
            client.add(p);
            try{
                mapper.writeValue(file, client);

            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
        }
        else{
            try{
                mapper.writeValue(file, p);
            
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
            }
        }
        System.out.println("¡Usuario guardado exitosamente!");    
    }
    
}
