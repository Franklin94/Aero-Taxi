package Model;


public class Cliente extends Persona{
    
    //Atributes:
    
    private String password;//Contraseña para ingresar al sistema.
    private boolean active; //posibilidad de ingresar al sistema (borrado o no).
    
    //Methods:

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String DNI, String edad) {
        super(nombre, apellido, DNI, edad);
        
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getNombre() {
        return super.name;
    }

    public String getApellido() {
        return super.surname;
    }

    @Override
    public String getDNI() {
        return DNI;
    }

    public String getEdad() {
        return super.age;
    }

    public void setNombre(String nombre) {
        super.name = nombre;
    }

    public void setApellido(String apellido) {
        super.surname = apellido;
    }

    @Override
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setEdad(String edad) {
        super.age = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
}
