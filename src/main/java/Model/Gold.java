package Model;

public class Gold extends Avion{
    //Atributes:
    private final boolean catering = true; //Servicio de catering.
    private boolean wifi; //Servicio de wi-fi;
    
    //Methods:

    public Gold() {
    }

    public Gold(boolean wifi) {
        this.wifi = wifi;
    }

    public Gold(boolean wifi, float capcomb, float costoxkm, int maxpax, float maxspeed, String propulsión, int tarifaxtipe) {
        super(capcomb, costoxkm, maxpax, maxspeed, propulsión, tarifaxtipe);
        this.wifi = wifi;
    }

    public boolean isCatering() {
        return catering;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
    
}
