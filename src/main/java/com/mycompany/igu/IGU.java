package com.mycompany.igu;

import com.mycompany.igu.igu.PantallaLogin;

/**
 *
 * @author Alejandro H.
 */
public class IGU {

    public static void main(String[] args) 
    {
        PantallaLogin plog = new PantallaLogin(); //creamos objeto de tipo pantalla (el JFRAME)
        plog.setVisible(true);                  //hcemos visible la pantalla
        plog.setLocationRelativeTo(null);       //para que aparezca centrado  
    }
}
