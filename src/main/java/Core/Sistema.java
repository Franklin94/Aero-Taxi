package Core;

import Model.Avion;
import Model.Bronze;
import Model.Cliente;
import Model.Gold;
import Model.Silver;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        String ans;
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
                e.getMessage();
                e.getStackTrace();
            }
        System.out.println("¡Usuario creado exitosamente!");
        System.out.println("¿Desea loguear ahora?\nSi (presione el n° 1).\nNo(presione cualquier otra tecla).");
        ans = input.next();
        switch(ans){
            case "1":
                logIn();
                break;
            default:
                System.out.println("Gracias por confiar en Aerotaxi. ¡Hasta pronto!");
                break;
        }
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
                    System.out.println("Bienvenido a AeroTaxi "+client.getNombre()+" "+client.getApellido());
                    String ans = input.next();
                    String ans2;
                    boolean access2 = true;
                    
                    while(access2 == true){
                        System.out.println("Seleccione la acción que desea realizar:\n1.Consulta de vuelos disponibles.\n2.Ir a mis vuelos.\n3.Cancelaciones.");
                        
                        switch(ans){
                            case "1":
                                //INSERTAR METODO CONSULTA DE VUELOS DISPONIBLES.
                                System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                                ans2 = input.next();
                                if(!ans2.equals("1")){
                                    access2 = false;
                                    System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                                }
                                break;
                            case "2":
                                //INSERTAR MÉTODO VER MIS VUELOS.
                                System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                                ans2 = input.next();
                                if(!ans2.equals("1")){
                                    access2 = false;
                                    System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                                }
                                break;
                            case "3":
                                //INSERTAR MÉTODO CANCELACIONES.
                                System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                                ans2 = input.next();
                                if(!ans2.equals("1")){
                                    access2 = false;
                                    System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                                }
                                break;
                            default:
                                System.out.println("La opción ingresada es errónea, por favor, vuelva a intentarlo.");
                                access2 = true;
                        }
                    }
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
    //Método creador de aviones (no interfiere el usuario):
    public static void airplanesCreator(){
    
    //Bronze airplanes:
    
        Bronze bronze1 = new Bronze(3000, 150, 50, 900, "Motor a pistones", 3000, "BRZ 001");
        Bronze bronze2 = new Bronze(3000, 150, 50, 900, "Motor a pistones", 3000, "BRZ 002");
        Bronze bronze3 = new Bronze(3000, 150, 50, 900, "Motor a pistones", 3000, "BRZ 003");
        Bronze bronze4 = new Bronze(3000, 150, 50, 900, "Motor a pistones", 3000, "BRZ 004");
        Bronze bronze5 = new Bronze(3000, 150, 50, 900, "Motor a pistones", 3000, "BRZ 005");
        Bronze bronze6 = new Bronze(3000, 150, 50, 900, "Motor a pistones", 3000, "BRZ 006");
    
    //Silver airplanes:
    
        Silver silver1 = new Silver(3000, 200, 50, 950, "Motor a pistones", 4000, "SIL 001");
        Silver silver2 = new Silver(3000, 200, 50, 950, "Motor a pistones", 4000, "SIL 002");
        Silver silver3 = new Silver(3000, 200, 50, 950, "Motor a pistones", 4000, "SIL 003");
        Silver silver4 = new Silver(3000, 200, 50, 950, "Motor a pistones", 4000, "SIL 004");
        Silver silver5 = new Silver(3000, 200, 50, 950, "Motor a pistones", 4000, "SIL 005");
        Silver silver6 = new Silver(3000, 200, 50, 950, "Motor a pistones", 4000, "SIL 006");
        
    //Gold airplanes:
        
        Gold gold1 = new Gold(true, 3000, 300, 50, 975,"Motor a reacción", 6000, "GLD 001");
        Gold gold2 = new Gold(true, 3000, 300, 50, 975,"Motor a reacción", 6000, "GLD 002");
        Gold gold3 = new Gold(true, 3000, 300, 50, 975,"Motor a reacción", 6000, "GLD 003");
        Gold gold4 = new Gold(false, 3000, 300, 50, 975,"Motor a reacción", 6000, "GLD 004");
        Gold gold5 = new Gold(false, 3000, 300, 50, 975,"Motor a reacción", 6000, "GLD 005");
        Gold gold6 = new Gold(true, 3000, 300, 50, 975,"Motor a reacción", 6000, "GLD 006");
    
    //Mandarlos a la base de datos:
    
        ArrayList<Avion> planes = new ArrayList<Avion>();
        planes.add(bronze1);
        planes.add(bronze2);
        planes.add(bronze3);
        planes.add(bronze4);
        planes.add(bronze5);
        planes.add(bronze6);
        planes.add(silver1);
        planes.add(silver2);
        planes.add(silver3);
        planes.add(silver4);
        planes.add(silver5);
        planes.add(silver6);
        planes.add(gold1);
        planes.add(gold2);
        planes.add(gold3);
        planes.add(gold4);
        planes.add(gold5);
        planes.add(gold6);
    
        String path = "C:\\Users\\Franco Gabriel\\Documents\\NetBeansProjects\\AeroTaxi\\DAO\\airplanes";
        File file = new File(path);
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, planes);
            
            
        }catch(IOException e){
            e.getMessage();
            e.getStackTrace();
        }
    }
    //Método para que el VUELO venga a buscar su avion correspondiente.
    public static Avion airplanesCollector(String patente) throws IOException{
        
        String path = "C:\\Users\\Franco Gabriel\\Documents\\NetBeansProjects\\AeroTaxi\\DAO\\airplanes";
        File file = new File(path);
        Avion avion = null;
        
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Avion> planes = mapper.readValue(file, ArrayList.class);
            
        for(Avion avioneta: planes){
            if(avioneta.getPatente().equals(patente)){
                avion = avioneta;
            }    
        }
        
        return avion;
    }
    //Compra de pasajes:
    public static void comprar(){
        
    }
}
