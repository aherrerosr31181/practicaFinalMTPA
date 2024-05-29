package com.uemc.practicaMTPA.logica;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Aleja
 */
public class Cliente implements Runnable
{
    
    public Cliente()
    {
        //aqui dentro imagino que hay que poner en marcha los hilos y 
        //lanzar la aplicacion de escritorio del cliente
    }

    
    @Override
    public void run()
    {
        
        //Aqui dentro implementamos la logica de escucha cosntante para que el cliente acepte lo que le manda el servidor 
         try
        {
            ServerSocket servidorCliente = new ServerSocket(9090);
            Socket cliente;           
            Paquete paqueteRecibido;
            
            while(true)
            {
                cliente = servidorCliente.accept();    
                
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());                                             
                paqueteRecibido = (Paquete) flujoEntrada.readObject();  
                
                // SI NO EL PAQUETE DE RECONOCIMIENTO SE PROCESA LA INFORMACION RECIBIDA DE UNA MANERA
                if(!paqueteRecibido.getMensaje().equals(" online"))
                {
                    //campochat.append(paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje() + "\n");
                    
                    
                //EN CAMBIO SI RECIBE EL PAQUETE DE RECONOCIMIENTO MUESTRA LA LISTA CON TODAS LAS IPs...
                }else{
                    
                    /**
                     * Dentro de este else tenemos que agregar los elementos al JComboBox
                     */
                    ArrayList <String> IPsMenu = new ArrayList<String>();
                    
                    IPsMenu = paqueteRecibido.getIPs();
                    
                    //ip.removeAll(); //para no cargar un arraylist detras de otro y asi sucesivamente 
                    
                    for(String z : IPsMenu)
                    {
                       // ip.addItem(z);
                    }     
                }
            }      
            
        }catch(Exception e){
            System.out.println("Error de recepción...");
        }   
        
    }
    
    
    //-------------------------------------------------------  ENVIO SOCKET RECONOCIMIENTO ------------------------------------------------------------------------------------------------------------
    public void EnvioOnline()
            
    {
        
            try
            {
                Socket miSocket = new Socket("192.168.1.45", 9999); //Contiene la direccion ip del servidor, puerto del servidor que está a la escucha  // 192.168.19.215 //

                /**
                 * una vez creado el socket tenemos que crear el paquete que le haremos llegar al servidor a través de este socket y su correspondiente stream
                 */
                Paquete datosReconocimiento = new Paquete(" online");
                ObjectOutputStream paqueteDatos = new ObjectOutputStream(miSocket.getOutputStream());
                paqueteDatos.writeObject(datosReconocimiento);

                miSocket.close();

            }catch(Exception ex){
                System.out.println("Error al enviar socket de reconocimiento...");
            }

        
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


}
