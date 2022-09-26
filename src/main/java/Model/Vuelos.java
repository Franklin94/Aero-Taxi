package Model;

import DAO.DestinosDAO;
import java.time.LocalDate;
import java.util.ArrayList;


public class Vuelos {
    //Atributes:
    private LocalDate departureDate;
    private DestinosDAO.localidad origen;
    private DestinosDAO.localidad destino;
    private int disppax; //Disponibilidad de pax.
    private Avion avion; //Avión asignado.
    private int paxconfirmados; //Pax que viajan.
    private String horario; //Hora del vuelo.
    private float tiempovuelo; //Tiempo de vuelo.
    private float costo;
    private ArrayList<Persona> passengers; //Acompañantes del vuelo.
    private boolean status; //Cancelado o confirmado.
    //Methods:

    public Vuelos() {
    }

    public Vuelos(LocalDate departureDate, DestinosDAO.localidad origen, DestinosDAO.localidad destino, int disppax, Avion avion, int paxconfirmados, String horario, float tiempovuelo, float costo,ArrayList<Persona> passengers, boolean status) {
        this.departureDate = departureDate;
        this.origen = origen;
        this.destino = destino;
        this.disppax = disppax;
        this.avion = avion;
        this.paxconfirmados = paxconfirmados;
        this.horario = horario;
        this.tiempovuelo = tiempovuelo;
        this.costo = costo;
        this.passengers = passengers;
        this.status = status;
    }

    public LocalDate getFecha() {
        return departureDate;
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

    public void setFecha(LocalDate departureDate) {
        this.departureDate = departureDate;
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

    public ArrayList<Persona> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Persona> passengers) {
        this.passengers = passengers;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
       
}
