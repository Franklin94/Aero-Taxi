package Model;

import java.util.ArrayList;

public class Vuelos {
    //Atributes:
    private String fecha;
    private String origen;
    private String destino;
    private boolean disponible;//Vuelo diponible.
    private int disppax; //Disponibilidad de pax.
    private Avion avion; //Avión asignado.
    private int paxconfirmados; //Pax que viajan.
    private String horario; //Hora del vuelo.
    private float tiempovuelo; //Tiempo de vuelo.
    private String numvuelo; //Número de vuelo.
    private float costo;
    private ArrayList<Cliente> pax; //Array de los clientes que viajarán en dicho vuelo.
    
    //Methods:

    public Vuelos() {
    }

    public Vuelos(String fecha, String origen, String destino, boolean disponible, int disppax, Avion avion, int paxconfirmados, String horario, float tiempovuelo, String numvuelo, float costo) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.disponible = disponible;
        this.disppax = disppax;
        this.avion = avion;
        this.paxconfirmados = paxconfirmados;
        this.horario = horario;
        this.tiempovuelo = tiempovuelo;
        this.numvuelo = numvuelo;
        this.costo = costo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public boolean isDisponible() {
        return disponible;
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

    public String getNumvuelo() {
        return numvuelo;
    }

    public float getCosto() {
        return costo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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

    public void setNumvuelo(String numvuelo) {
        this.numvuelo = numvuelo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public ArrayList<Cliente> getPax() {
        return pax;
    }

    public void setPax(ArrayList<Cliente> pax) {
        this.pax = pax;
    }
    
    
       
}
