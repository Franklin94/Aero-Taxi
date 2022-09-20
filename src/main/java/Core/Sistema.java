package Core;

import DAO.AvionDAO;
import Model.Cliente;
import DAO.ClientesDAO;
import DAO.DestinosDAO;
import Model.Avion;
import Model.Bronze;
import Model.Gold;
import Model.Persona;
import Model.Silver;
import Model.Vuelos;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
//Atributes:
    boolean working; //Para hacer trabajar al sistema.

//Methods:
    //Sign in:
    public void signIn() throws IOException{
        
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
        ClientesDAO a = new ClientesDAO();
        a.guardarCliente(p);
        
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
    public void logIn() throws IOException{
                
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
    //Compra de pasajes:
    public void comprarVuelo(){
        
        //CONSULTA DE AVIONES DISPONIBLES.
        Scanner input = new Scanner(System.in);
        String date;
        System.out.println("Ingrese la fecha (yyyy/mm/dd) en la que desea realizar su viaje:");
        date = input.next();
        departureDateValidate(date);//Funciona como flag para inicializar la operación Compra.
        ArrayList<Avion> availablePlanes;
        AvionDAO a = new AvionDAO();
        boolean k1,k2,k3;
        k1=k2=k3=false;
        
        
        if(departureDateValidate(date) == true){
            
            availablePlanes = a.availablePlanes(); 
            //Muestra de los destinos:
            DestinosDAO destinations = new DestinosDAO();
            System.out.println("Seleccione la localidad de origen del vuelo:");
            DestinosDAO.localidad origen = destinations.seleccionarDestino();
            System.out.println("Seleccione la localidad de destino del vuelo:");
            DestinosDAO.localidad destino = destinations.seleccionarDestino();
            
            if(destinationValidate(origen,destino) == true){
                //QUE LA PERSONA PUEDA ELEGIR SU VUELO.
                
                if(availablePlanes != null){
                    for(Avion aircraft: availablePlanes){
                        if(aircraft instanceof Bronze){
                            k1 = true;
                        }
                        else if(aircraft instanceof Silver){
                            k2 = true;
                        }
                        else if(aircraft instanceof Gold){
                            k3 = true;
                        }
                    }
                    if(k1 == true){
                        System.out.println("Hay aviones de tipo Bronze disponibles.");
                    }
                    if(k2 == true){
                        System.out.println("Hay aviones de tipo Silver disopnibles.");
                    }
                    if(k3 == true){
                        System.out.println("Hay aviones de tipo Gold disponibles.");
                    }
                }
                else{
                    System.out.println("Lo lamentamos, de momento no hay aviones disponibles.");
                    
                }
                //CREAR UN VUELO Y COMPRARLO(PASAR POR LOS VALIDATES NECESARIOS)
                //Consulta del precio de un vuelo:
                System.out.println("Ingrese el número de pasajeros que irán en el vuelo:");
                String cantpax = input.next();
                int cantPax = Integer.parseInt(cantpax);
                float price = priceCalculator(origen, destino,cantPax);//Precio del vuelo (price is already shown by the method)
                //Confirmar vuelo y luego introducir los datos de los acompañantes:
                
            }
            else{
                //QUE REINTENTE LA OPERACIÓN (O QUE LA CANCELE).
            }
        
        }//DEFINIR QUÉ HACER CUANDO LA FECHA INGRESADA ARROJA UN FALSE.
        
        
    }
    //Validador de fecha de solicitud de vuelo:
    public boolean departureDateValidate(String date){
    
        LocalDate today = LocalDate.now();
        LocalDate departureDate = LocalDate.parse(date);
        boolean answer;
        
        if(today.isBefore(departureDate)) {
            System.out.println("Fecha solicitada no válida.\n¡No puede ingresar una fecha del pasado!");
            answer = false;
        } else if(today.isAfter(departureDate)){
            answer = true;
        }
        else{
            System.out.println("El vuelo debe ser reservado con al menos 1 día de anticipación.");
            answer = false;
        }
        return answer;           
        
    }
    public boolean destinationValidate(DestinosDAO.localidad departure, DestinosDAO.localidad destination){
       
        boolean flag;
        flag = !departure.equals(destination);
        return flag;
    }
    
    //Calculadora de precios del vuelo.
    public float priceCalculator(DestinosDAO.localidad origen, DestinosDAO.localidad destino, int cantpax){
        
        //El origen y el destino ya fueron previamente validados.
        
        DestinosDAO Q = new DestinosDAO();
        AvionDAO A = new AvionDAO();
        int cantkm = Q.cantidadKm(origen, destino);//Cantidad de kilómetros del vuelo.
        boolean bronze, silver, gold;
        bronze=silver=gold=false;
        Avion aircraft = A.selectAirplaneByType();
        
        
        if(aircraft instanceof Bronze){
            bronze = true;
        }
        else if(aircraft instanceof Silver){
            silver = true;
        }
        else if(aircraft instanceof Gold){
            gold = true;
        }
        Avion airplane = A.selectAirplaneByType();
        float costoxkm =airplane.getCostoxkm();
        int tarifaxtype=0;
        
        if(airplane instanceof Bronze){
            tarifaxtype = 3000;
        }
        else if(airplane instanceof Silver){
            tarifaxtype = 4000;
        }
        else if(airplane instanceof Gold){
            tarifaxtype = 6000;
        }
        
        float price = cantkm*costoxkm + cantpax*3500 + tarifaxtype;
        
        System.out.print("El precio del vuelo es de: $"+price);
        return price;
    }
    public boolean confirmFlight(int cantPax){
        //CREAR VUELO Y SETTEAR TODOS LOS DATOS PREVIAMENTE CARGADOS.
        
        Scanner input = new Scanner(System.in);
        System.out.println("Si desea confirmar el vuelo, presione 1(uno), o cualquier otra tecla en caso contrario.");
        String answer = input.next();
        boolean confirmation = false;
        
        
        if(answer.equals("1")){
            /*
                private String fecha;
                private DestinosDAO.localidad origen;
                private DestinosDAO.localidad destino;
                private int disppax; //Disponibilidad de pax.
                private Avion avion; //Avión asignado.
                private int paxconfirmados; //Pax que viajan.
                private String horario; //Hora del vuelo.
                private float tiempovuelo; //Tiempo de vuelo.
                private float costo;
                private ArrayList<Cliente> pax;
            */
            Vuelos flight = new Vuelos();
            ArrayList<Persona> passengers = addPax(cantPax);
            
        }
        else{
            
        }
        return confirmation;
    }
    public ArrayList<Persona> addPax(int cantPax){
        
        Scanner input = new Scanner(System.in);
        ArrayList<Persona> passengers = null;
        
        if(cantPax != 0){
            for(int i=0 ; i < cantPax-1 ; i++){

                System.out.println("Ingrese los datos del acompañante n°:"+(i+1));
                System.out.println("Nombre:");
                String name = input.next();
                System.out.println("Apellido:");
                String surname = input.next();
                System.out.println("Edad:");
                String age = input.next();
                System.out.println("DNI:");
                String DNI = input.next();
                Persona pax = new Persona(name, surname, DNI, age);
                passengers.add(pax);
            }
        }
        return passengers;       
        
    }
    
}
