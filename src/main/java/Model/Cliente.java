package Model;

public class Cliente {
    
    //Atributes:
    private String nombre;
    private String apellido;
    private String DNI;
    private String edad;
    private String password;//Contraseña para ingresar al sistema.
    
    //Methods:

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String DNI, String edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public String getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
