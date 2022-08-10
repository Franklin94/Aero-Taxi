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
        
        if(psw.equals(psw2)){//Verificación de que la contraseña ingresada haya sido escrita correctamente:
           
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
        
        String path="C:\\Users\\Franco Gabriel\\Documents\\NetBeansProjects\\AeroTaxi\\DAO"+"\\"+dni;
        File file = new File(path);
        
            try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, p);

            }catch(IOException e){
                System.out.println("Ocurrió un error al momento de guardar sus datos");
            }
        System.out.println("¡Usuario creado exitosamente!");        
    }
    //Log in:
    public static void logIn() throws IOException{
        
        String dni, psw;//Datos solicitados para poder loguear.
        boolean access = true; //Da de alta el funcionamiento.
        Scanner input = new Scanner(System.in);
        Cliente p;
        System.out.println("Ingrese su n° de DNI por favor:");
        dni = input.next();
        System.out.println("Ingrese su contraseña por favor:");
        psw = input.next();
        String path = "C:\\Users\\Franco Gabriel\\Documents\\NetBeansProjects\\AeroTaxi\\DAO"+"\\"+dni;
        File file = new File(path);
        String psw2;
        int k=0;
        //Buscar en el archivo al cliente cuyo dni coincida
        
        while(access == true){
            if(file.exists()){//Verificando que la ruta ingresada existe.

                try{
                //Lectura:
                ObjectMapper mapper = new ObjectMapper();

                Cliente client = mapper.readValue(file, Cliente.class);
                psw2 = client.getPassword();

                if(psw.equals(psw2)){ // LLAMAR(1ero crearlos) MÉTODOS PARA REALIZAR LAS TRANSACCIONES NECESARIAS.
                    //COMPRA DE PASAJES, CANCELACIONES, CONSULTA DE VUELOS DISPONIBLES.
                    System.out.println("");
                }
                else{
                    while(!psw.equals(psw2) && k<=3){
                        System.out.println("La contraseña ingresada es incorrecta. Por favor, ingrésela nuevamente:");
                        psw2 = input.next();
                        k++;
                    }
                    if(k==4){
                        System.out.println("Ha excedido el n° de intentos permitidos. Vuelva a intentar más tarde.");
                        //¿Insertar un "bloqueo"?
                    }
                    // LLAMAR(1ero crearlos) MÉTODOS PARA REALIZAR LAS TRANSACCIONES NECESARIAS.
                    //COMPRA DE PASAJES, CANCELACIONES, CONSULTA DE VUELOS DISPONIBLES.
                }
                }catch(IOException e){
                    e.getMessage();
                    e.getStackTrace();
                    System.out.println("El usuario ingresado no existe.");
                    System.out.println("¿Desea volver a intentar ingresar sus datos (marque 1) o reistrarse (marque 2)?\nPresione otra tecla para cancelar la operación.");
                    String answer = input.next();

                    switch(answer){
                        case "1": //Reingreso de datos.
                            access = true;
                            break;
                        case "2": //Registración de cliente.
                            signIn();
                            break;
                        default:
                            System.out.println("Muchas gracias por su tiempo, vuelva pronto.");
                            access = false;
                            break;
                    }
                }
            }
        }
    }
    //Compra de pasajes:
    public static void comprar(){
        
    }
}
