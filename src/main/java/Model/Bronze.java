package Model;

public class Bronze extends Avion{
    //Atributes:
    private final boolean catering = false; //Servicio de catering.
    
    //Methods:

    public Bronze() {
    }

    public Bronze(float capcomb, float costoxkm, int maxpax, float maxspeed, String propulsión, int tarifaxtype, String patente, boolean available,  int tarifaxTipo) {
        super(capcomb, costoxkm, maxpax, maxspeed, patente, available, tarifaxTipo);
    }
    
    public boolean isCatering() {
        return catering;
    }
    
}
