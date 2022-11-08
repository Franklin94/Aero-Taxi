package Core;

import DAO.AdminDAO;
import DAO.AvionDAO;
import DAO.DestinosDAO.localidad;
import DAO.VuelosDAO;
import Model.Admin;
import Model.Avion;
import Model.Cliente;
import Model.Vuelos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
    
    //Atributes:
    private Sistema systemExecution;//Inicialización del sistema.
    private AdminCore adminCore; //Admin del sistema.
    private VuelosDAO vuelosDAO; //DAO para operar con vuelos.
//DAO para operar con vuelos.
    private AvionDAO avionDAO;
    //DAO para trabajar con admin's.
    //Methods:
    //DAO para operar con aviones.
    private final AdminDAO adminDAO; 

    public Menu(Sistema systemExecution) {
        this.adminDAO = new AdminDAO();
        this.systemExecution = systemExecution;
    }

    public Menu() {
        this.adminDAO = new AdminDAO();
    }
    

    public Sistema getSystemExecution() {
        return systemExecution;
    }

    public void setSystemExecution(Sistema systemExecution) {
        this.systemExecution = systemExecution;
    }
    
    
    public void startingPage(){
        
        Scanner input = new Scanner(System.in);
        String answer; 
        boolean cont = true;
        
        while(cont == true){
            
            System.out.println("Bienvenido a AeroTaxi.\nSeleccione la acción que desea realizar:\n1.Crear un usuario.\n2.Loguear.\n3.Menú Administrador.");
            answer = input.next();
            
            switch(answer){

                case "1":
                    systemExecution.signIn();
                    cont = false;
                    break;

                case "2":
                    System.out.println("Ingrese su n° de DNI por favor:");
                    String DNI = input.next();
                    System.out.println("Ingrese su contraseña por favor:");
                    String psw = input.next();
                    systemExecution.logIn(DNI,psw);
                    cont = false;
                    break;
                case "3":
                    boolean cnt = true;
                    String trueAdminPass = "admin123";//Clave para entrar al modo admin.
                    while(cnt){
                        System.out.println("Ingrese la contraseña para el modo Administrador:");
                        String adminPass = input.next();
                        if(adminPass.equals(trueAdminPass)){
                            System.out.println("1.Ingresar.\n2.Crear cuenta Admin.");
                            String answer2 = input.next();
                            switch(answer2){
                                case "1":
                                    System.out.println("Ingrese su n° de DNI por favor:");
                                    String DNI3 = input.next();
                                    System.out.println("Ingrese su contraseña por favor:");
                                    String psw3 = input.next();
                                    Admin loggedAdmin = adminDAO.getAdmin(DNI3,psw3);
                                    if(loggedAdmin == null){
                                        System.out.println("ACM1PT");
                                    }
                                    adminMenu(loggedAdmin);
                                    cnt = false;
                                    break;
                                case "2":
                                    System.out.println("Ingrese su nombre:");
                                    String name = input.next();
                                    System.out.println("Ingrese su apellido:");
                                    String surname = input.next();
                                    System.out.println("Ingrese su DNI:");
                                    String DNI2 = input.next();
                                    System.out.println("Ingrese su edad:");
                                    String age2 = input.next();
                                    System.out.println("Ingrese su password:");
                                    String psw2 = input.next();
                                    Admin admin = new Admin(name,surname,DNI2,age2,psw2,true);
                                    adminDAO.saveAdmin(admin);
                                    cnt = false;
                                    break;
                                default:
                                    System.out.println("El código ingresado es incorrecto.");
                                    break;
                            }
                        }
                        else{
                            System.out.println("El código ingresado es incorrecto. ¿Desea volver a intentar?\nPresione 1 (uno) en caso afirmativo, o cualquier otra tecla en caso contrario.");
                            String answer3 = input.next();
                            if(!answer3.equals("1")){
                                cnt = false;
                                
                            }
                        }
                    }
                    
                    

                default:
                    System.out.println("Opción ingresada no válida. Vuelva a intentarlo por favor.");
            }
        }     
    }
    public void clientMenu(Cliente client){
        
        System.out.println("Bienvenido a AeroTaxi "+client.getNombre()+" "+client.getApellido());
        Scanner input = new Scanner(System.in);
        System.out.println("Seleccione la acción que desea realizar:\n1.Consulta de vuelos disponibles.\n2.Ir a mis vuelos.\n3.Cancelaciones.");
        String ans = input.next();
        String ans2;
        String dni = client.getDNI();
        boolean access2 = true;
        
        while(access2 == true){
            switch(ans){
                case "1":
                    systemExecution.comprarVuelo();
                    System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                    ans2 = input.next();
                    if(!ans2.equals("1")){
                        access2 = false;
                        System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                    }
                    break;
                case "2":
                    systemExecution.consultarMisVuelos(dni);//ArrayList de los vuelos del cliente.
                    System.out.println("¿Desea realizar alguna otra operación?\n1.Sí\n2.No (presione cualquier otra tecla)");
                    ans2 = input.next();
                    if(!ans2.equals("1")){
                        access2 = false;
                        System.out.println("Muchas gracias por elegir y confiar en AeroTaxi.");
                    }
                    break;
                case "3":
                    ArrayList<Vuelos> myFlights = systemExecution.consultarMisVuelos(dni);
                    System.out.println("Seleccione el vuelo que desea cancelar:");
                    String ans3 = input.next();
                    int index = Integer.parseInt(ans3);
                    Vuelos vuelo = myFlights.get(index-1);
                    systemExecution.cancelarVuelo(vuelo,dni);
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
    public void adminMenu(Admin admin){
        
        System.out.println("Bienvenido a AeroTaxi "+admin.getName()+" "+admin.getSurname());
        Scanner input = new Scanner(System.in);
        System.out.println("Seleccione el campo con el que desea trabajar:\n1.Clientes.\n2.Aviones.\n3.Vuelos.\n4.Admin's.");
        String ans = input.next();
        String answer1, answer11,answer2, answer22,answer3, answer33,answer4, answer44,answer5, dni;
        boolean continue1,continue2,continue3,continue4,continue5,continue22;
        continue1=continue2=continue3=continue4=continue5=continue22=true;
        
        while(continue5 == true){
            switch(ans){
                case "1"://Clientes
                    while(continue1 == true){    
                        System.out.println("Seleccione la acción que desea realizar:\n1.Buscar un cliente (DNI).\n2.Mostrar datos históricos de clientes.\n3.Modificar cliente(s).");
                        //INGRESAR MÉTODO DE MODIFICACIÓN DE CLIENTE.
                        answer1 = input.next();
                        switch(answer1){
                            case "1":
                                System.out.println("Ingrese el DNI del cliente que está buscando:");
                                dni = input.next();
                                adminCore.buscarCliente(dni);
                                break;
                            case "2":
                                adminCore.mostrarDataClientes();
                                break;
                            case "3":
                                //INSERTAR MÉTODO DE MODIFICACIÓN DE CLIENTES.
                                break;
                            default:
                                System.out.println("Opción seleccionada no válida. Vuelva a intentarlo.");
                                break;
                        }
                        System.out.println("¿Desea realizar otra operación con los clientes?\n1.Sí (presione uno).\n2.No (presione cualquier otra tecla).");
                        answer11 = input.next();
                        if(!answer11.equals("1")){
                            continue1 = false;
                            }
                    }
                    break;
                case "2"://Aviones
                    while(continue2 == true){
                        System.out.println("Seleccione la acción que desea realizar:\n1.Subir/cargar un avión.\n2.Modificar un avión.\n3.Consulta de aviones de la empresa.");
                        answer2 = input.next();
                        switch(answer2){
                            case "1":
                                adminCore.altaAvion();
                                break;
                            case "2":
                                adminCore.modificaAvion();
                                break;
                            case "3":
                                adminCore.consultaAvion();
                                break;
                            default:
                                System.out.println("La opción seleccionada no es válida. Vuelva a intentarlo.");
                                break;
                        }
                        System.out.println("¿Desea realizar alguna otra operación con los aviones?\n1. Sí (presione uno).\n2.No (presiona cualquier otra tecla).");
                        answer22 = input.next();
                        if(!answer22.equals("1")){
                            continue2 = false;
                        }                        
                    }
                    break;
                case "3"://Vuelos
                    while(continue3 == true){
                        System.out.println("Seleccione la opción que desea realizar:\n1.Consultar vuelos confirmados.\n2.Modificar datos de vuelos.");
                        //INSERTAR UN MÉTODO PARA MODIFICAR VUELOS.
                        answer3 = input.next();
                        switch(answer3){
                            case "1":
                                System.out.println("Ingrese la fecha en la que planea realizar su vuelo: (dd-mm-yyyy)");
                                String depDate = input.next();//Departure date.
                                LocalDate departureDate = LocalDate.parse(depDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                adminCore.consultDailyFlights(departureDate);
                                break;
                            case "2":
                                //INSERTAR UN MÉTODO PARA MODIFICAR VUELOS.
                                System.out.println("Ingrese la fecha en la que planea realizar su vuelo: (dd-mm-yyyy)");
                                String depDate2 = input.next();//Departure date.
                                LocalDate departureDate2 = LocalDate.parse(depDate2, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                adminCore.consultDailyFlights(departureDate2);
                                System.out.println("Ingrese el DNI del cliente asociado al vuelo que desea modificar:");
                                String ansDNI = input.next();
                                Cliente client = adminCore.buscarCliente(ansDNI);
                                Vuelos flight = vuelosDAO.getFlight(departureDate2,client);
                                
                                while(continue22 == true){
                                    System.out.println("Seleccione el campo que desea modificar:\n1.Fecha del vuelo.\n2.Origen.\n3.Destino.\n4.Avion.\n5.Horario.\n6.Pasajeros\n7.Status.");
                                    String ans222 = input.next();
                                    switch(ans222){
                                        case "1":
                                            System.out.println("Ingrese la fecha en la que planea realizar su vuelo: (dd-mm-yyyy)");
                                            String definitiveDate = input.next();//Departure date.
                                            LocalDate defDate = LocalDate.parse(definitiveDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                            flight.setFecha(defDate);
                                            break;
                                        case "2":
                                            localidad city = null;
                                            while(city == null){
                                                System.out.println("Seleccione la ciudad de origen del vuelo:\n1.Buenos Aires.\n2.Córdoba.\n3.Santiago.\n4.Montevideo.");
                                                String newCity = input.next();
                                                city = adminCore.selectTown(newCity);
                                            }
                                            flight.setOrigen(city);
                                            break;
                                        case "3":
                                            localidad city2 = null;
                                            while(city2 == null){
                                                System.out.println("Seleccione la ciudad de destino del vuelo:\n1.Buenos Aires.\n2.Córdoba.\n3.Santiago.\n4.Montevideo.");
                                                String newCity2 = input.next();
                                                city2 = adminCore.selectTown(newCity2);
                                            }
                                            flight.setDestino(city2);
                                            break;
                                        case "4":
                                            System.out.println("Ingrese la fecha en la que planea realizar su vuelo: (dd-mm-yyyy)");
                                            String definitiveDate2 = input.next();//Departure date.
                                            Avion plane = avionDAO.selectAirplaneByType(definitiveDate2);
                                            flight.setAvion(plane);
                                            break;
                                        case "5":
                                            System.out.println("Seleccione el nuevo horario de vuelo:(hh:mm)");
                                            String horario = input.next();
                                            flight.setHorario(horario);
                                            break;
                                        case "6":
                                            //INSERTAR MÉTOD QUE LEVANTE LOS PAX YA CARGADOS Y SEAN REEMPLAZADOS POR LOS NUEVOS.
                                            break;
                                        case "7":
                                            if(flight.isStatus()){
                                                flight.setStatus(false);
                                            }
                                            else{
                                                flight.setStatus(true);
                                            }
                                            break;
                                        default:
                                            System.out.println("Opcióningresaa no válida.\n¿Desea realizar alguna otra modificación?\n1.Si (presoine uno).\n2.Presione cualquier otra tecla en caso contrario.");
                                            String confirmation = input.next();
                                            
                                            if(!confirmation.equals("1")){
                                                continue22 = false;
                                            }
                                    }
                                } 
                                break;
                            default:
                                System.out.println("La opción seleccionada no es válida.");
                                break;
                        }
                        System.out.println("¿Desea realizar alguna otra operación con los vuelos?\n1. Sí (presione uno).\n2.No (presione cualquier otra tecla).");
                        answer33 = input.next();
                        if(!answer33.equals("1")){
                            continue3 = false;
                        }
                    }
                    break;
                case "4": //Admin's:
                    while(continue4 == true){
                        System.out.println("Seleccione la acción que desea realizar:\n1.Dar de alta un Admin.\n2.Dar de baja un Admin.\n3.Modificar un Admin.");
                        answer4 = input.next();
                        switch(answer4){
                            case "1":
                                //INSERTAR MÉTODO PARA DAR DE ALTA UN ADMIN.
                                System.out.println("Ingrese el nombre del nuevo Admin:");
                                String name1 = input.next();
                                System.out.println("Ingrese el apellido del nuevo Admin:");
                                String surname1 = input.next();
                                System.out.println("Ingrese el DNI del nuevo Admin:");
                                String DNI1 = input.next();
                                System.out.println("Ingrese la edad del nuevo Admin:");
                                String age1 = input.next();
                                System.out.println("Ingrese la password del nuevo Admin:");
                                String psw1 = input.next();
                                Admin newAdmin = new Admin(name1,surname1,DNI1,age1,psw1,true);
                                adminDAO.saveAdmin(newAdmin);
                                break;
                            case "2":
                                //INSERTAR MÉTODO PARA DAR DE BAJA A UN ADMIN.
                                System.out.println("Ingrese el DNI del Admin que desea eliminar:");
                                String DNI2 = input.next();
                                System.out.println("Ingrese la password del Admin que desea eleiminar:");
                                String psw2 = input.next();
                                Admin adminToDelete = adminDAO.getAdmin(DNI2, psw2);
                                adminDAO.deleteAdmin(adminToDelete);
                                break;
                            case "3":
                                //INSERTAR MÉTODO PARA MODIFICAR ADMIN.
                                System.out.println("Ingrese el DNI del Admin que desea modificar:");
                                String DNI3 = input.next();
                                System.out.println("Ingrese la password del Admin que desea modificar:");
                                String psw3 = input.next();
                                Admin adminToModify = adminDAO.getAdmin(DNI3, psw3);
                                adminDAO.modifyAdmin(adminToModify,adminToModify.getName(),adminToModify.getSurname(),DNI3,adminToModify.getAge(),psw3);
                                break;
                            default:
                                System.out.println("Opción seleccionada no válida.");
                                break;
                        }
                        System.out.println("¿Desea realizar alguna otra operación con Admin's?\n1.Sí (presione uno).\n2.No (presione cualquier otra tecla).");
                        answer44 = input.next();
                        if(!answer44.equals("1")){
                            continue4 = false;
                        }
                    }
                    break;
                default:
                    System.out.println("Opción seleccionada no válida.");
                    break;
            }
            System.out.println("¿Desearealizar otra operación?\n1.Sí (presoine uno).\n2.No (presione cualquier otra tecla).");
            answer5 = input.next();
            if(!answer5.equals("1")){
                continue5 = false;
            }
        }

    }
}
