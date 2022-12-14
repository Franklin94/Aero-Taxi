package Core;
package Model;
import DAO.AvionDAO;
import DAO.ClientesDAO;
import DAO.DestinosDAO;
import DAO.DestinosDAO.localidad;
import DAO.VuelosDAO;
import Model.Avion;
import Model.Bronze;
import Model.Cliente;
import Model.Gold;
import Model.Persona;
import Model.Silver;
import Model.Vuelos;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class AdminCore {   
    //Methods:
    
    //Buscar cliente:
    public Cliente buscarCliente(String DNI){
        
        ClientesDAO clients = new ClientesDAO();
        ArrayList<Cliente> clientsList = clients.actualCliente();
        Cliente c1 = null;
        Scanner input = new Scanner(System.in);
        int k=0;
        boolean flag = true;
        
        if(clientsList != null){
            
            while(flag == true){
                for(Cliente client: clientsList){
                    if(client.getDNI().equals(DNI)){
                        c1 = client;
                    }
                    else{
                        k++;
                    }
                }
                if(clientsList.size() == k){
                    System.out.println("El DNI ingresado no se corresponde con el de ningún cliente en la base de datos.");
                    System.out.println("¿Desea continuar con la búsqueda?\n1.Sí (presione 1);\n2.Cancelar (presione cualquier otra tecla).");
                    String answer = input.next();

                    if(!answer.equals("1")){
                        flag = false;
                    }
                    else{
                        System.out.println("Reingrese el DNI del cliente que desea buscar:");
                        DNI = input.next();
                    }
                }
            }
        }
        else{
            System.out.println("No hay clientes registrados aún.");
        }
        
        return c1;
    }
    //Mostrar todos los clientes registrados:
    public void mostrarDataClientes(){
        
        ClientesDAO c1 = new ClientesDAO();
        VuelosDAO v1 = new VuelosDAO();
        ArrayList<Cliente> clientesHistóricos = c1.actualCliente();
        ArrayList<Avion> planes = null;
        boolean bronze, silver, gold;
        bronze=silver=gold=false;
        float preciosAcumulados = 0;
        
        for(Cliente client: clientesHistóricos){//Datos personales de cada cliente:
            
            for(Vuelos vueloHistórico: v1.confirmedFlights()){//Búsqueda de los vuelos realizados por el cliente.
                ArrayList<Persona> people = vueloHistórico.getPassengers();

                if(people.contains(client)){
                    Avion plane = vueloHistórico.getAvion();
                    planes.add(plane);
                    preciosAcumulados = vueloHistórico.getCosto() + preciosAcumulados;
                }
            }
            System.out.println(client.toString());
            System.out.println("Dinero total gastado por el cliente: $"+preciosAcumulados);
            for(Avion plane: planes){
                if(plane instanceof Bronze){
                bronze = true;
                }
                else if(plane instanceof Silver){
                silver = true;
                }
                else{
                gold = true;
                }
            }
            if(gold == true){
                System.out.println("La mejor categoría utilizada por el cliente fue Gold.");
            }
            else if(silver == true){
                System.out.println("La mejor categoría utilizada por el cliente fue Silver.");
            }
            else{
                System.out.println("La mejor categoría utilizada por el cliente fue Bronze.");
            }
        }
       
    }
    //Cargar aviones al sistema:
    public void altaAvion(){
        
        
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        String ans;
        
        while(flag == true){
        
            System.out.println("Seleccione el tipo de avión que desea ingresar:\n1.Bronze;\n2.Silver;\n3.Gold;\n4.Cancelar.");
            ans = input.next();
            switch(ans){
                
                case "1"://Creación de un Bronze:
                    
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese la capaciad de combustible del avión:");
                    float capcomb = input.nextFloat();
                    System.out.println("Ingrese el costo por km:");
                    float costoxkm = input.nextFloat();
                    System.out.println("Ingrese la cantidad de pax que entran en el avión:");
                    int maxpax = input.nextInt();
                    System.out.println("Ingrese la velocidad máxima del avión:");
                    float maxspeed = input.nextFloat();
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese el tipo de proopulsión del avión:");
                    String propulsión = input.next();
                    System.out.println("Ingrese la patente del avión:");
                    String patente = input.next();
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese la tarifa correspondiente al tipo de avión:");
                    int tarifaxtipe = input.nextInt();
                    Bronze bnz = new Bronze(capcomb,costoxkm, maxpax,maxspeed,propulsión,tarifaxtipe, patente,true, tarifaxtipe,null);
                    
                    AvionDAO a = new AvionDAO();
                    try{
                        a.guardarAvion(bnz);
                    }catch(IOException e){
                        System.out.println("Hubo un error en la carga del avión.");
                    }
                    break;
                
                case "2"://Creación de un Silver:
                    
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese la capaciad de combustible del avión:");
                    float capcomb2 = input.nextFloat();
                    System.out.println("Ingrese el costo por km:");
                    float costoxkm2 = input.nextFloat();
                    System.out.println("Ingrese la cantidad de pax que entran en el avión:");
                    int maxpax2 = input.nextInt();
                    System.out.println("Ingrese la velocidad máxima del avión:");
                    float maxspeed2 = input.nextFloat();
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese el tipo de proopulsión del avión:");
                    String propulsión2 = input.next();
                    System.out.println("Ingrese la patente del avión:");
                    String patente2 = input.next();
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese la tarifa correspondiente al tipo de avión:");
                    int tarifaxtipe2 = input.nextInt();
                    Silver slv = new Silver(capcomb2, costoxkm2, maxpax2, maxspeed2, propulsión2, tarifaxtipe2, patente2,true,tarifaxtipe2,null);
                    
                    AvionDAO b = new AvionDAO();
                    try{    
                        b.guardarAvion(slv);
                    }catch(IOException e){
                        System.out.println("Hubo un error en la carga del avión.");
                    }
                    break;
                
                case "3"://Creación de un Gold:
                    
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese la capaciad de combustible del avión:");
                    float capcomb3 = input.nextFloat();
                    System.out.println("Ingrese el costo por km:");
                    float costoxkm3 = input.nextFloat();
                    System.out.println("Ingrese la cantidad de pax que entran en el avión:");
                    int maxpax3 = input.nextInt();
                    System.out.println("Ingrese la velocidad máxima del avión:");
                    float maxspeed3 = input.nextFloat();
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese el tipo de proopulsión del avión:");
                    String propulsión3 = input.next();
                    System.out.println("Ingrese la patente del avión:");
                    String patente3 = input.next();
                    input.nextLine();//Limpia el scanner.
                    System.out.println("Ingrese la tarifa correspondiente al tipo de avión:");
                    int tarifaxtipe3 = input.nextInt();
                    input.nextLine();
                    System.out.println("¿El avión posee servicio de wi fi?:\n1.Sí;\n2.No.");
                    String answer = input.next();
                    boolean wifi;
                    if(answer.equals("1")){
                        wifi = true;
                    }
                    else{
                        wifi = false;
                    }
                    Gold gld = new Gold(wifi, capcomb3, costoxkm3,maxpax3, maxspeed3, propulsión3, tarifaxtipe3, patente3, true,tarifaxtipe3,null);
                    
                    AvionDAO c = new AvionDAO();
                    try{
                        c.guardarAvion(gld);
                    }catch(IOException e){
                        System.out.println("Hubo un error en la carga del avión.");
                    }
                    break;
                
                case "4": //Opción crear avión elegida erróneamente.
                    
                    flag = false;
                    break;
                
                default: //Error de tipeo al seleccionar una opción:
                    
                    System.out.println("Ha ingresado una opción no válida. Por favor, vuelva a intentarlo.");
                    break;
            }
            System.out.println("¿Desea agregar otro avión al sistema?\n1.Sí\nPresione cualquier otra tecla en caso de respuesta negativa.");
            input.nextLine();
            String ans2 = input.next();
            
            if(!ans2.equals("1")){
                flag = false;
            }
            
        }
        
        }
    //DAR DE BAJA O MODIFICAR AVIONES EXISTENTES
    public void modificaAvion(){
        
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese la patente del avión que desea modificar/eliminar:");
        String patente = input.next();
        AvionDAO access = new AvionDAO();
        ArrayList<Avion> planes = access.actualAvion();
        Avion airplane;
        int k=0;
        
        if(planes != null){
            for(Avion aircraft: planes){
                if(aircraft.getPatente().equals(patente)){
                    airplane = aircraft;
                    System.out.println("Seleccione la opción que desea realizar:\n1.Modificar el avión.\n2.Eliminar avión.\n3.Cancelar.");
                    String ans;
                    ans = input.next();
                    
                    switch(ans){
                        case "1"://Modificar el avión:
                            planes.remove(planes.indexOf(airplane));//Elimina el aircraft que requiere modificación.
                            System.out.println("Reingrese todos los datos del avión por favor.");
                            altaAvion();
                            break;
                        
                        case "2"://Eliminar el avión requerido.
                            planes.remove(planes.indexOf(airplane));//Elimina el aircraft.
                            System.out.println("Aircarft deleted succesfully.");
                            break;
                        case "3":
                            System.out.println("Operación cancelada.");
                            break;
                            
                        default:
                            System.out.println("Ha habido un error al momento de ingresar la opción, por favor, vuelva a intentarlo.");
                            break;
                    }
                }
                else{
                    k++;
                }
            }
            
        }
        else{
            System.out.println("Aún no hay aviones ingresados.");
        }
        
    }
    //Consulta de aviones disponibles:
    public void consultaAvion(){

        AvionDAO access = new AvionDAO();
        ArrayList<Avion> planes = access.actualAvion();
        
        if(planes != null){
            System.out.println("Lista de aviones de la empresa:"+planes.toString());
            
        }
        else{
            System.out.println("Aún no hay aviones registrados.");
        }
    }
    private ArrayList<Vuelos> flightsBrowser(LocalDate date){
        
        
        VuelosDAO v1 = new VuelosDAO();
        ArrayList<Vuelos> flightsAtGivenDate = null;
        
        for(Vuelos flight: v1.confirmedFlights()){
            if(flight.getFecha().equals(date)){
                flightsAtGivenDate.add(flight);
            }
        }
        return flightsAtGivenDate;
    }
    //Consulta los vuelos en la fecha específica que sea ingresada:
    public void consultDailyFlights(LocalDate date){
        
        ArrayList<Vuelos> flightsAtGivenDate = flightsBrowser(date);
        int k=1;//Contador de vuelos.
        
        if(flightsAtGivenDate != null){
            
            for(Vuelos flight: flightsAtGivenDate){
                System.out.println((k)+flight.toString());
                k++;
            }
        }
        else{
            System.out.println("No hay vuelos previstos para la fecha ingresada.");
        }
    }
    //Modificación de vuelos:
    public void modificarVuelo(Vuelos flight,LocalDate departureDate, DestinosDAO.localidad origen, DestinosDAO.localidad destino, Avion avion, int paxconfirmados, String horario,ArrayList<Persona> passengers){
        
        VuelosDAO v = new VuelosDAO();
        v.modifyFlight(flight, departureDate, origen, destino, avion, paxconfirmados, horario, passengers);
       
    }
    public DestinosDAO.localidad selectTown(String option){
        
        localidad selection = null;
        switch(option){
            case "1":
                selection = DestinosDAO.localidad.BuenosAires;
                break;
            case "2":
                selection = DestinosDAO.localidad.Cordoba;
                break;
            case "3":
                selection = DestinosDAO.localidad.SANTIAGO;
                break;
            case "4":
                selection = DestinosDAO.localidad.MONTEVIDEO;
                break;
        }
        return selection;                                    
    }
}
