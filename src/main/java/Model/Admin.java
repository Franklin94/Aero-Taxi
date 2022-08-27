package Model;
package DAO;
import DAO.AvionDAO;
import DAO.ClientesDAO;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Admin {

    //Atributes:
    private String nombre;
    private String apellido;
    private String DNI;
    private String password;
    private final boolean admin = true; //Condicion de ser admin.
    
    // BUSCAR CLIENTES O VUELOS, CREAR OTROS ADMINS.
    
    //Methods:
    
    //Buscar cliente:
    public void buscarCliente(Cliente client){
        ClientesDAO client1 = new ClienteDAO();
        
    
    }
    
    //Cargar aviones al sistema:
    public static void altaAvion() throws IOException{
        
        
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
                    Bronze bnz = new Bronze(capcomb,costoxkm, maxpax,maxspeed,propulsión,tarifaxtipe, patente);
                    
                    AvionDAO a = new AvionDAO();
                    a.guardarAvion(bnz);
                    
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
                    Silver slv = new Silver(capcomb2, costoxkm2, maxpax2, maxspeed2, propulsión2, tarifaxtipe2, patente2);
                    
                    AvionDAO b = new AvionDAO();
                    b.guardarAvion(slv);
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
                    Gold gld = new Gold(wifi, capcomb3, costoxkm3,maxpax3, maxspeed3, propulsión3, tarifaxtipe3, patente3);
                    
                    AvionDAO c = new AvionDAO();
                    c.guardarAvion(gld);
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
    public void modificaAvion() throws IOException{
        
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
    public static void consultaAvion(){

        AvionDAO access = new AvionDAO();
        ArrayList<Avion> planes = access.actualAvion();
        
        if(planes != null){
            System.out.println("Lista de aviones de la empresa:"+planes.toString());
            
        }
        else{
            System.out.println("Aún no hay aviones registrados.");
        }
    }
    }
    

