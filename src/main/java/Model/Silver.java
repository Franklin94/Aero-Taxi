package Model;

public class Silver extends Avion{
    //Atributes:
    private final boolean catering = true; //Servicio de catering.
    
    //Methods:

    public Silver() {
    }

    public Silver(float capcomb, float costoxkm, int maxpax, float maxspeed, String propulsión, int tarifaxtipe, String patente) {
        super(capcomb, costoxkm, maxpax, maxspeed, propulsión, tarifaxtipe, patente);
    }
     
    public boolean isCatering() {
        return catering;
    }
    
}
