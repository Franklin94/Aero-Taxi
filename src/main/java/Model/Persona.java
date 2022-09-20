package Model;

public class Persona {
    
    //Atributes:
    String name;
    String surname;
    String DNI;
    String age;
    
    //Methods:

    public Persona(String name, String surname, String DNI, String age) {
        this.name = name;
        this.surname = surname;
        this.DNI = DNI;
        this.age = age;
    }

    public Persona() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDNI() {
        return DNI;
    }
    
}
