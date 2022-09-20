package Model;

import java.util.ArrayList;

public class Cliente extends Persona{
    
    //Atributes:
    
    private String password;//Contraseña para ingresar al sistema.
    
    
    //Methods:

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String DNI, String edad) {
        super(nombre, apellido, DNI, edad);
        
    }

    public String getNombre() {
        return super.name;
    }

    public String getApellido() {
        return super.surname;
    }

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
    
}
