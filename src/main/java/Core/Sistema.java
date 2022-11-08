package Core;

import DAO.AvionDAO;
import Model.Cliente;
import DAO.ClientesDAO;
import DAO.DestinosDAO;
import DAO.VuelosDAO;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    

//Methods:
    //Sign in:
    public void signIn() {
        
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
                logIn(dni,psw);
                break;
            default:
                System.out.println("Gracias por confiar en Aerotaxi. ¡Hasta pronto!");
                break;
              
        }
    }        
    //Log in:
    public void logIn(String DNI, String password) {
                
        boolean access = true; //Da de alta el funcionamiento.
        Scanner input = new Scanner(System.in);
        Cliente p;
        String path = "clientes";
        File file = new File(path);
        String psw2;
        //Buscar en el archivo al cliente cuyo dni coincida
        
        while(access == true){
            //if(file.exists()){//Verificando que la ruta ingresada exista.

            try{
                //Lectura:
                ObjectMapper mapper = new ObjectMapper();

                Cliente client = mapper.readValue(file, Cliente.class);
                psw2 = client.getPassword();

                if(password.equals(psw2)){
                    
                    System.out.println("Bienvenido a AeroTaxi "+client.getNombre()+" "+client.getApellido());
                    boolean access2 = true;
                    //SE DEBERÍA DESPLEGAR ESTE MENÚ DE OPCIONES PARA EL CLIENTE COMÚN, NO PARA UN ADMIN DEL SISTEMA.
                    while(access2 == true){
                        System.out.println("Seleccione la acción que desea realizar:\n1.Consulta de vuelos disponibles.\n2.Ir a mis vuelos.\n3.Cancelaciones.");
                        String ans = input.next();
                        String ans2;
                        
                        switch(ans){
                            case "1":
                                comprarVuelo();
                                System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                                ans2 = input.next();
                                if(!ans2.equals("1")){
                                    access2 = false;
                                    System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                                }
                                break;
                            case "2":
                                consultarMisVuelos(DNI);//ArrayList de los vuelos del cliente.
                                System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                                ans2 = input.next();
                                if(!ans2.equals("1")){
                                    access2 = false;
                                    System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                                }
                                break;
                            case "3":
                                ArrayList<Vuelos> myFlights = consultarMisVuelos(DNI);
                                System.out.println("Seleccione el vuelo que desea cancelar:");
                                String ans3 = input.next();
                                int index = Integer.parseInt(ans3);
                                Vuelos vuelo = myFlights.get(index-1);
                                cancelarVuelo(vuelo,DNI);
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
                    while(!password.equals(psw2)){
                        System.out.println("La contraseña ingresada es incorrecta. Por favor, ingrésela nuevamente:");
                        psw2 = input.next();
                    }
                    
                }
            }catch(IOException e){
                e.getMessage();
                e.getStackTrace();
                System.out.println("El usuario ingresado no existe.");
                System.out.println("¿Desea volver a intentar ingresar sus datos (marque 1) o registrarse (marque 2)?\nPresione otra tecla para cancelar la operación.");
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
        //}//Log out:
            System.out.println("¿Desea realizar alguna otra operación?\nSeleccione 1(uno) en caso afirmativo o cualquier otra tecla en caso contrario.");
            String answer6 = input.next();
            if(!answer6.equals("1")){
                access = false;
                System.out.println("Muchas gracias por confiar en AeroTaxi. Hasta pronto!");
            }
        }
    }
    //Compra de pasajes:
    public void comprarVuelo(){
        
        //CONSULTA DE AVIONES DISPONIBLES.
        Scanner input = new Scanner(System.in);
        String date;
        System.out.println("Ingrese la fecha (dd-mm-yyyy) en la que desea realizar su viaje:");
        date = input.next();
        System.out.println(date);
        LocalDate departureDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        departureDateValidate(date);//Funciona como flag para inicializar la operación Compra.
        ArrayList<Avion> availablePlanes;
        AvionDAO a = new AvionDAO();
        boolean k1,k2,k3;
        k1=k2=k3=false;
        boolean proseguir = true;
        
        while(proseguir == true){
            if(departureDateValidate(date) == true){

                LocalDate date2 = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                availablePlanes = a.availablePlanes(departureDate); 
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
                        proseguir = false;
                    }
                    //CREAR UN VUELO Y COMPRARLO(PASAR POR LOS VALIDATES NECESARIOS)
                    //Consulta del precio de un vuelo:
                    boolean continuar=true;
                    while(continuar==true){
                        
                        System.out.println("Ingrese el número de pasajeros que irán en el vuelo:");
                        String cantpax = input.next();
                        int cantPax = Integer.parseInt(cantpax);

                        boolean cantPaxOk = cantPaxValidate(cantPax,date);    
                        if(cantPaxOk == true){
                            float price = priceCalculator(origen, destino, cantPax, date);//Precio del vuelo (price is already shown by the method)
                            //Confirmar vuelo y luego introducir los datos de los acompañantes:
                            Avion avion = a.selectAirplaneByType(date);
                            confirmFlight(origen, destino, date2, avion, cantPax, price);
                            if(confirmFlight(origen, destino, date2, avion, cantPax, price) == true){
                                avion.setDateOfUse(date2);
                                System.out.println("Vuelo confirmado correctamente. Muchas gracias por elegir AeroTaxi.");
                            }
                        }
                        else{
                            System.out.println("El n° de pasageros ingresados excede la capacidad de nuestros aviones disponibles.");
                            System.out.println("¿Desea continuar?\nPresione 1 (uno) en caso afirmativo, o cualquier otra tecla en caso contrario.");
                            String answer7 = input.next();
                            if(!answer7.equals("1")){
                                continuar = false;
                            }
                        }
                    }
                    

                }
                else{
                    System.out.println("Destino ingresado no válido. ¿Desea volver a ingresar el destino y origen?\n Presion 1(uno) en caso afirmativo, y cualquier otra tecla en caso contrario.");
                    String answer4 = input.next();
                    if(!answer4.equals("1")){
                        proseguir = false;
                        System.out.println("Muchas gracias por utilizar AeroTaxi.");
                    }
                }

            }
            else{
                System.out.println("Fecha ingresada no válida. ¿Desea volver a ingresar la misma?\n Presion 1(uno) en caso afirmativo, y cualquier otra tecla en caso contrario.");
                String answer5 = input.next();
                if(!answer5.equals("1")){
                    proseguir = false;
                }
            }
        }    
        
    }
    //Cancelar vuelo:
    public void cancelarVuelo(Vuelos vuelo, String DNI){
        
        LocalDate today = LocalDate.now();
        
        if(today.plusDays(1).isBefore(vuelo.getFecha())){ 
            VuelosDAO vuelosDAO = new VuelosDAO();
            ArrayList<Vuelos> flights = vuelosDAO.confirmedFlights();
            
            for(Vuelos vuelos: flights){
                ArrayList<Persona> passengers = vuelos.getPassengers();
                for(Persona pax: passengers){
                    if(pax.getDNI().equals(DNI)){
                        vuelo.setStatus(false);
                    }
                }
            }
        }
        else{
            System.out.println("El período para dar de baja su vuelo expiró, por lo que NO puede cancelar o cambiar la fecha del mismo.");
        }
    }
    
    public ArrayList<Vuelos> consultarMisVuelos(String DNI){
        
        VuelosDAO vuelosDAO = new VuelosDAO();
        ArrayList<Vuelos> flights = vuelosDAO.confirmedFlights();
        ArrayList<Vuelos> clientFlights = null;
        int k=1;
        
        for(Vuelos vuelo: flights){
            ArrayList<Persona> passengers = vuelo.getPassengers();
            for(Persona cliente: passengers){
                if(cliente.getDNI().equals(DNI)){
                    clientFlights.add(vuelo);
                }
            }
        }
        for(Vuelos flight: clientFlights){
            System.out.println((k)+"° vuelo:"+flight);
            k++;
        }
        return clientFlights;
    }
    //Validador de cantidad de pasajeros solicitados:
    private boolean cantPaxValidate(int cantPax, String date){
        
        AvionDAO a = new AvionDAO();
        LocalDate departureDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        ArrayList<Avion> aircrafts = a.availablePlanes(departureDate);
        boolean existance = false;
        
        for(Avion plane: aircrafts){
            if(plane.isAvailable() && plane.getMaxpax() <= cantPax ){
                existance = true;
            }
        }
        if(existance == false){
            System.out.println("No hay aviones disponibles con esa capacidad de pasajeros.");
        }
        return existance;
    }
    
    //Validador de fecha de solicitud de vuelo:
    public boolean departureDateValidate(String date){
    
        LocalDate today = LocalDate.now();
        LocalDate departureDate;
        departureDate = LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        boolean answer;
        
        if(today.isAfter(departureDate)) {
            System.out.println("Fecha solicitada no válida.\n¡No puede ingresar una fecha del pasado!");
            answer = false;
        }
        else if(today.isBefore(departureDate)){
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
    public float priceCalculator(DestinosDAO.localidad origen, DestinosDAO.localidad destino, int cantpax, String date){
        
        //El origen y el destino ya fueron previamente validados.
        
        DestinosDAO Q = new DestinosDAO();
        AvionDAO A = new AvionDAO();
        int cantkm = Q.cantidadKm(origen, destino);//Cantidad de kilómetros del vuelo.
        boolean bronze, silver, gold;
        bronze=silver=gold=false;
        Avion aircraft = A.selectAirplaneByType(date);
        
        
        if(aircraft instanceof Bronze){
            bronze = true;
        }
        else if(aircraft instanceof Silver){
            silver = true;
        }
        else if(aircraft instanceof Gold){
            gold = true;
        }
        Avion airplane = A.selectAirplaneByType(date);
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
    public boolean confirmFlight(DestinosDAO.localidad origen, DestinosDAO.localidad destino, LocalDate departureDate,Avion avion, int cantPax, float precio){

        Scanner input = new Scanner(System.in);
        System.out.println("Si desea confirmar el vuelo, presione 1(uno), o cualquier otra tecla en caso contrario.");
        String answer = input.next();
        boolean confirmation;
        
        if(answer.equals("1")){
            
            ArrayList<Persona> passengers = addPax(cantPax);
            int disspax = avion.getMaxpax() - cantPax;
            System.out.println("Ingrese el horario en el que desea realizar su vuelo: (hh:mm)");
            String horario = input.next();
            boolean flag = timeValidate(horario);
            
            while(flag == false){
                System.out.println("Reingrese el horario en el que desea realizar su vuelo por favor: (hh:mm)");
                horario = input.next();
                flag = timeValidate(horario);
            }
            Vuelos flight = new Vuelos(departureDate, origen, destino, disspax, avion, cantPax, horario, 5, precio, passengers, true);
            VuelosDAO flightSaving = new VuelosDAO();
            flightSaving.flightSaver(flight);
            confirmation = true;
        }
        else{
            confirmation = false;
            System.out.println("Muchas gracias. Vuelva pronto.");
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
    public boolean timeValidate(String hour){
        
        boolean flag = true;
        
            try{
                LocalTime time = LocalTime.parse(hour, DateTimeFormatter.ISO_LOCAL_TIME);
                
            }catch(java.time.DateTimeException e){
                System.out.println("Ha ingresado un horario incorrecto. Vuelva a intentarlo por favor: (hh:mm)");
                flag = false;    
            }
        
        return flag;    
    }
    public boolean personaValidate(Persona persona){
        
    }
    
}
