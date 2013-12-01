/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import Controlador.Controlador;
import Vista.Login;
import Vista.Registro;

/**
 *
 * @author DAM
 */
public class ProyectoAeropuerto {

 
   
      public static void main(String[] args) {
       
         ModeloLogin modeloLogin = new ModeloLogin();
         Login login = new Login();
         Registro registro = new Registro(login, true);
         Controlador controlador = new Controlador(login, modeloLogin);
         
         
         login.setVisible(true);
         
    }
}
