package Model;

public abstract class Avion {
    //Atributes:
    private float capcomb; //Capacidad de combustible;
    private float costoxkm; //Costo por km recorrido.
    private int maxpax; //Capacidad máxima de pasajeros;
    private float maxspeed; //Velocidad máxima en km/h; 
    private enum propulsión{ REACCION, HELICE , PISTONES}; //Tipo de propulsion del motor: hélice, pistones, reacción.
    private enum tarifaxtype{BRONZE, SILVER, GOLD}; //Tarifa por tipo de avión;
    private String patente;//Patente identificadora del avión.
    private boolean available; //Disponibilidad del avión.
    private int tarifaxTipo; //Tarifa por tipo de avion.
    
    //Methods:

    public Avion() {
    }

    public Avion(float capcomb, float costoxkm, int maxpax, float maxspeed, String patente, boolean available, int tarifaxTipo) {
        this.capcomb = capcomb;
        this.costoxkm = costoxkm;
        this.maxpax = maxpax;
        this.maxspeed = maxspeed;
        this.patente = patente;
        this.available = available;
    }

    public int getTarifaxTipo() {
        return tarifaxTipo;
    }

    public void setTarifaxTipo(int tarifaxTipo) {
        this.tarifaxTipo = tarifaxTipo;
    }
    

    public float getCapcomb() {
        return capcomb;
    }

    public float getCostoxkm() {
        return costoxkm;
    }

    public int getMaxpax() {
        return maxpax;
    }

    public float getMaxspeed() {
        return maxspeed;
    }


    public void setCapcomb(float capcomb) {
        this.capcomb = capcomb;
    }

    public void setCostoxkm(float costoxkm) {
        this.costoxkm = costoxkm;
    }

    public void setMaxpax(int maxpax) {
        this.maxpax = maxpax;
    }

    public void setMaxspeed(float maxspeed) {
        this.maxspeed = maxspeed;
    }


    public String getPatente() {
        return patente;
    }


    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
