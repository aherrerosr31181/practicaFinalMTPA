package com.uemc.practicaMTPA.logica;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * El servidor será una aplicación de consola.
 * Lo dejo ya montado aunque falte de pulir el lado del cliente: 29 mayo 2024
 * 
 * 
 * falta por implementar la lógica de inicio de sesión...
 * @author Alejandro H.
 */
public class Servidor implements Runnable
{

    /**
     * Constructor de la clase servidor, pone en marcha el hilo 
     */
    public Servidor()
    {
        Thread hiloServidor = new Thread(this);
        hiloServidor.start();
    }
    
    //tenemos que implementar metodos para ver que partidas han empezado
    //cuales han acabado 
    //cuales están en curso
    //un metodo que cierre los sockets y los dataStream para finalizar 
    //todo eso en una aplicacion de consola que sea un bucle infinito que capte lo que escribamos por teclado....
    
    @Override
    public void run() 
    {
        
        try 
        {
            System.out.println("Servidor encendido.");
            
            ServerSocket servidor = new ServerSocket(9999);                                    
            String nick, ip, mensaje; 
            
            /**
             * En este ArrayList iremos almacenando las direcciones IP de los clientes que se conecten a nuestro servidor
             */
            ArrayList <String> listaIP = new ArrayList<String>();
            Paquete paqueteRecibido;
            
            while(true)
            {
                Socket miSocket = servidor.accept();    
                System.out.println("Conexión aceptada");
                /**
                 * Ahora ipRemota contiene un String con la dirección IP del cliente que se acaba de conectar
                 */
                ObjectInputStream paqueteDatos = new ObjectInputStream(miSocket.getInputStream());                                              
                paqueteRecibido = (Paquete) paqueteDatos.readObject(); 
                
                nick = paqueteRecibido.getNick();
                ip = paqueteRecibido.getIp();  // <--- se genera un error por aqui.... ya lo arreglaremos 
                mensaje = paqueteRecibido.getMensaje();  
                
               //si NO es la primera conexion de un cliente simplemente reenviamos el paquete al cliente correspondiente
                if(!mensaje.equals(" online"))
                {
                    //areatexto.append(nick + ": " + mensaje + "para " + ip + "\n");

                    Socket enviaDestinatario  = new Socket(ip, 9090);

                    ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
                    paqueteReenvio.writeObject(paqueteRecibido);

                    paqueteReenvio.close();
                    enviaDestinatario.close();
                    miSocket.close();
                }else{
                    
                    // SI ES LA PRIMERA VEZ QUE SE CONECTA SACAMOS SU IP Y SE LA MANDAMOS A TODOS LOS DEMÁS
                    //-------------------------------------------       DETECTA ONLINE ---------------------------------------------------------------------------------
                
                    /**
                     * Guardamos en localización la dirección del cliente con el que acabamos de conectar
                     */
                    InetAddress localizacion = miSocket.getInetAddress();

                    /**
                     * el metodo getInetAddress nos devuelve un objeto de tipo InetAdress, no String
                     * para tenerlo en formato string tal y como nosotros lo necesitamos hacemos lo siguiente
                     */

                    String ipRemota = localizacion.getHostAddress();

                    
                    /**
                     * Guardamos las direcciones IP de los clientes que se conecten a nuestro servidor en el ArrayList
                     */
                    listaIP.add(ipRemota);
                    
                    /**
                     * añadimos este arraylist al paquete que vamos a enviar a todos los clientes que estén conectados
                     */
                    paqueteRecibido.setIPs(listaIP);
                    
                    /**
                     * El siguiente paso es enviar este paquete con el nuevo campo que acabamos de añadir a cada uno de los clientes
                     * de tal forma que cada vez que se conecta un cliente nuevo el código entra en el else en el que nos encontramos ahora,
                     * detecta la IP, la mete en el arraylist y lo añade al correspondiente campo del paquete
                     */
                    
                    for(String s : listaIP) // <-- esto es código repetido es un poco desastre hay que refactorizar y darle una vueltecita 
                    {
                        Socket enviaDestinatario  = new Socket(s, 9090);

                        ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
                        paqueteReenvio.writeObject(paqueteRecibido);

                        paqueteReenvio.close();
                        enviaDestinatario.close();
                        miSocket.close();
                        
                    }

                    //-------------------------------------------------------------------------------------------------------------------------------------------------
                
                }
            }
            
        } catch (IOException  | ClassNotFoundException ex) {
            System.out.println("Se ha producido una excepción...");
        } 
        
    }
    
}
