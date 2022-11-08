package DAO;
import Model.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AdminDAO {
    private final String path = "admin"; 
 
    public AdminDAO() {
        
    }
    
    //Methods:
    
    public void saveAdmin(Admin admin){
        
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        ArrayList<Admin> admins = getAllAdmins();
        
        if(admins != null){
            
            admins.add(admin);
            try{
                mapper.writeValue(file, admins);
                System.out.println("HOLAAAA");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                mapper.writeValue(file, admin);
            }catch(IOException e){
            }
        }
    }
    public ArrayList<Admin> getAllAdmins(){
        
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        ArrayList<Admin> adminsList = new ArrayList<Admin>();
        
        if(file.exists()){
            try{
                adminsList = mapper.readValue(file, ArrayList.class);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return adminsList;  
    }
    public Admin getAdmin(String DNI, String password){
        
        ArrayList<Admin> adminsList;
        adminsList = getAllAdmins();
        Admin outputAdmin = null;
        
        if(adminsList != null){
            for(Admin admin: adminsList){
                if(admin.getDNI().equals(DNI) && admin.getPassword().equals(password)){
                    outputAdmin = admin;
                }
            }
        }
        return outputAdmin;      
    }
    public void deleteAdmin(Admin admin){
        
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        ArrayList<Admin> adminsList = getAllAdmins();
        
        if(admin != null && adminsList != null){
            for(Admin a: adminsList){
                if(admin.equals(a)){
                  adminsList.remove(a);
                }
            }
        }
        try{
            mapper.writeValue(file, adminsList);
        }catch(IOException e){
        }
    }
    public void modifyAdmin(Admin admin, String name, String surname, String DNI, String age, String psw){
        
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        ArrayList<Admin> adminsList = getAllAdmins();
        
        if(admin != null && adminsList != null){
            for(Admin a: adminsList){
                if(admin.equals(a)){
                  admin.setName(name);
                  admin.setAge(age);
                  admin.setDNI(DNI);
                  admin.setPassword(psw);
                  admin.setSurname(surname);
                }
            }
        }
        try{
            mapper.writeValue(file, adminsList);
        }catch(IOException e){
        }
    }
}
