package Model;

import java.time.LocalDate;

public class Silver extends Avion{
    //Atributes:
    private final boolean catering = true; //Servicio de catering.
    
    //Methods:

    public Silver() {
    }

    public Silver(float capcomb, float costoxkm, int maxpax, float maxspeed, String propulsión, int tarifaxtype, String patente, boolean available,  int tarifaxTipo, LocalDate dateOfUse) {
        super(capcomb, costoxkm, maxpax, maxspeed, patente, available, tarifaxTipo, dateOfUse);
    }

     
    public boolean isCatering() {
        return catering;
    }
    
}
