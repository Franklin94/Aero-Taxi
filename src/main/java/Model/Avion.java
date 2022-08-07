package Model;

public abstract class Avion {
    //Atributes:
    private float capcomb; //Capacidad de combustible;
    private float costoxkm; //Costo por km recorrido.
    private int maxpax; //Capacidad máxima de pasajeros;
    private float maxspeed; //Velocidad máxima en km/h; 
    private String propulsión; //Tipo de propulsion del motor: hélice, pistones, reacción.
    private int tarifaxtype; //Tarifa por tipo de avión;
    
    //Methods:

    public Avion() {
    }

    public Avion(float capcomb, float costoxkm, int maxpax, float maxspeed, String propulsión, int tarifaxtipe) {
        this.capcomb = capcomb;
        this.costoxkm = costoxkm;
        this.maxpax = maxpax;
        this.maxspeed = maxspeed;
        this.propulsión = propulsión;
        this.tarifaxtype = tarifaxtipe;
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

    public String getPropulsión() {
        return propulsión;
    }

    public int getTarifaxtipe() {
        return tarifaxtype;
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

    public void setPropulsión(String propulsión) {
        this.propulsión = propulsión;
    }

    public void setTarifaxtipe(int tarifaxtipe) {
        this.tarifaxtype = tarifaxtipe;
    }
    
    
}
