
package com.uemc.practicaMTPA.logica;

import java.io.*;
import java.util.ArrayList;


public class Paquete 
        implements Serializable 
{                                 
    private String nick, ip, mensaje;
    
    /**
     * Vamos a incluir un arraylist dentro de esta clase para poder reutilizarla para 
     * el descubriimneto de direcciones ip, esto quizás habría que refactorizarlo más adelante
     * pero por ahora buscamos que la cosa funcione
     */
    private ArrayList<String> IPs;
    
    /**
     * constructor vacío
     */
    public Paquete()
    {
        
    }
    
    /**
     * 
     * @param nick
     * @param ip
     * @param mensaje 
     */
    public Paquete(String nick, String ip, String mensaje)
    {
        this.nick = nick;
        this.ip = ip;
        this.mensaje = mensaje;
    }
   
    /**
     * 
     * @param mensaje 
     */
    public Paquete(String mensaje)
    {
        this.mensaje = mensaje;
    }
    
    
    public ArrayList<String> getIPs() 
    {
        return IPs;
    }

   
    public void setIPs(ArrayList<String> IPs) 
    {
        this.IPs = IPs;
    }
    
     
    public String getNick() 
    {
        return nick;
    }

    public void setNick(String nick) 
    {
        this.nick = nick;
    }

    public String getIp() 
    {
        return ip;
    }

    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getMensaje() 
    {
        return mensaje;
    }

    public void setMensaje(String mensaje) 
    {
        this.mensaje = mensaje;
    }    
}
    

