package com.mycompany.igu.logica;

import com.mycompany.igu.igu.LoginFrame;

/**
 * Lógica principal del proyecto.
 * @author Alejandro H.
 */
public class PracticaMTPA {

    public static void main(String[] args) 
    {
        LoginFrame lf = new LoginFrame(); //creamos objeto de tipo pantalla (el JFRAME)
        lf.setVisible(true);                  //hcemos visible la pantalla
        lf.setLocationRelativeTo(null);       //para que aparezca centrado  
    }
}
