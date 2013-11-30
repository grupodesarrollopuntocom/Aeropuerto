/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import Controlador.Controlador;
import Vista.Login;

/**
 *
 * @author DAM
 */
public class ProyectoAeropuerto {

 
   

    
           
    
    public static void main(String[] args) {
       
         ModeloLogin modeloLogin = new ModeloLogin();
         Login login = new Login();
         Controlador controlador = new Controlador(login, modeloLogin);
         
         login.setVisible(true);
         
    }
}
