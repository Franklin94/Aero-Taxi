package Model;

public class Bronze extends Avion{
    //Atributes:
    private final boolean catering = false; //Servicio de catering.
    
    //Methods:

    public Bronze() {
    }

    public Bronze(float capcomb, float costoxkm, int maxpax, float maxspeed, String propulsión, int tarifaxtipe, String patente) {
        super(capcomb, costoxkm, maxpax, maxspeed, propulsión, tarifaxtipe, patente);
    }

    public boolean isCatering() {
        return catering;
    }
    
}
