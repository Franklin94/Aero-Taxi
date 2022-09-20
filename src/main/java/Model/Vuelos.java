package Model;

import DAO.DestinosDAO;
import java.util.ArrayList;


public class Vuelos {
    //Atributes:
    private String fecha;
    private DestinosDAO.localidad origen;
    private DestinosDAO.localidad destino;
    private int disppax; //Disponibilidad de pax.
    private Avion avion; //Avión asignado.
    private int paxconfirmados; //Pax que viajan.
    private String horario; //Hora del vuelo.
    private float tiempovuelo; //Tiempo de vuelo.
    private float costo;
    private Cliente pax; //Array de los clientes que viajarán en dicho vuelo.
    private ArrayList<Persona> passengers; //Acompañantes del vuelo.
    //Methods:

    public Vuelos() {
    }

    public Vuelos(String fecha, DestinosDAO.localidad origen, DestinosDAO.localidad destino, int disppax, Avion avion, int paxconfirmados, String horario, float tiempovuelo, float costo,ArrayList<Persona> passengers) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.disppax = disppax;
        this.avion = avion;
        this.paxconfirmados = paxconfirmados;
        this.horario = horario;
        this.tiempovuelo = tiempovuelo;
        this.costo = costo;
        this.passengers = passengers;
    }

    public String getFecha() {
        return fecha;
    }

    public DestinosDAO.localidad getOrigen() {
        return origen;
    }

    public DestinosDAO.localidad getDestino() {
        return destino;
    }

    
    public int getDisppax() {
        return disppax;
    }

    public Avion getAvion() {
        return avion;
    }

    public int getPaxconfirmados() {
        return paxconfirmados;
    }

    public String getHorario() {
        return horario;
    }

    public float getTiempovuelo() {
        return tiempovuelo;
    }

    public float getCosto() {
        return costo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setOrigen(DestinosDAO.localidad origen) {
        this.origen = origen;
    }

    public void setDestino(DestinosDAO.localidad destino) {
        this.destino = destino;
    }


    public void setDisppax(int disppax) {
        this.disppax = disppax;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void setPaxconfirmados(int paxconfirmados) {
        this.paxconfirmados = paxconfirmados;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setTiempovuelo(float tiempovuelo) {
        this.tiempovuelo = tiempovuelo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Cliente getPax() {
        return pax;
    }

    public void setPax(Cliente pax) {
        this.pax = pax;
    }
    
    
       
}
