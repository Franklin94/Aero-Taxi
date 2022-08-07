package Core;

import Model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sistema {
    
//Atributes:
    boolean working; //Para hacer trabajar al sistema.

//Methods:
    //Sign in:
    public static void signIn() throws IOException{
        
        String name;
        String surname;
        String dni;
        String age;
        String psw,psw2;
        Cliente p = new Cliente();//New client.
        Scanner input = new Scanner(System.in);
        System.out.println("A continuación, ingrese sus datos personales:");
        System.out.println("Nombre:");
        name = input.next();
        System.out.println("Apellido:");
        surname = input.next();
        System.out.println("DNI:");
        dni = input.next();
        System.out.println("Edad:");
        age = input.next();
        System.out.println("Contraseña:");
        psw = input.next();
        System.out.println("Reingrese su contraseña:");
        psw2 = input.next();
        
        if(psw.equals(psw2)){//Verificación de que la contraseña ingresada, haya sido escrita correctamente:
           
            p.setNombre(name);
            p.setApellido(surname);
            p.setDNI(dni);
            p.setEdad(age);
            p.setPassword(psw);
        }
        else{
            while(!psw.equals(psw2)){
                System.out.println("Ingrese su contraseña:");
                psw = input.next();
                System.out.println("Reingrese su contraseña:");
                psw2 = input.next();
            }
            p.setNombre(name);
            p.setApellido(surname);
            p.setDNI(dni);
            p.setEdad(age);
            p.setPassword(psw);
        }
        
       //Creación del archivo: 
        
        String path="C:\\Users\\Franco Gabriel\\Documents\\NetBeansProjects\\AeroTaxi\\DAO";
        File file = new File(path);
        
            try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, p);

            }catch(IOException e){
                System.out.println("Hubo un problema al guardar sus datos");
            }
        System.out.println("¡Usuario creado exitosamente!");        
    }
    //Log in:
    public static void logIn() throws IOException{
        
        String dni, psw;
        String path = "C:\\Users\\Franco Gabriel\\Documents\\NetBeansProjects\\AeroTaxi\\DAO";
        Scanner input = new Scanner(System.in);
        Cliente p;
        System.out.println("Ingrese su n° de DNI por favor:");
        dni = input.next();
        System.out.println("Ingrese su contraseña por favor:");
        psw = input.next();
        
        //Buscar en el archivo al cliente cuyo dni coincida
    }
}
