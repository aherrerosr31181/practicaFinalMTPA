package com.uemc.practicaMTPA.logica;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
/**
 * Class that represents a determined user.
 * @author Alejandro H.
 */
public class User {
    
    private String username;
    private String password;
    
    private int id;
    
   // public static Dictionary userDatabase = new Hashtable(); //esot puede no ser la mejor manera porque no es persistente pero de momento no tengo nada mejor
   public static HashMap<String, String> userDatabase = new HashMap<>();
    
    public User (String _username, String _password)
    {
        this.username = _username;
        this.password = _password;

        userDatabase.put(username, password);
    }
    
}
