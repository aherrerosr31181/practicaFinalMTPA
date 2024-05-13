package com.mycompany.igu.logica;

/**
 * Clase que representa un usuario.
 * @author Alejandro H.
 */
public class Usuario 
{
    private String username;
    private String password;
    
    private int id;

    public Usuario(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    /**
     * Devuelve el nombre de usuario.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sobreescribe el nombre de usuario.
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve la contraseña.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sobreescribe la contraseña.
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve el número de identificación único.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Calcula el identificador de un nuevo usuario.
     * @return resultado
     */
    public int idGenerator(){
        int resultado;
        
        resultado = (int)Math.random()*100;
        
    return resultado;
    }
    
}
