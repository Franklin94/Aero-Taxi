package Model;
package DAO;

public class Admin extends Persona{

    //Atributes:
    
    private String password;
    private final boolean admin = true; //Condicion de ser admin.
    
    // BUSCAR CLIENTES O VUELOS, CREAR OTROS ADMINS.
    
    //Methods:

    public Admin(String name, String surname, String DNI, String age) {
        super(name, surname, DNI, age);
    }

    public Admin() {
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getDNI() {
        return DNI;
    }

    public String getAge() {
        return age;
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

    @Override
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setAge(String age) {
        this.age = age;
    }
    

    }
    

