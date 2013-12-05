/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import Archivos.Archivo;
import Controlador.Controlador;
import Modelo.JDBC.BaseDatos;
import Vista.InterfazAvion;
import Vista.InterfazDatosPersona;
import Vista.InterfazInfoPersona;
import Vista.Login;
import Vista.PrincipalVuelo;
import Vista.Registro;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;

/**
 *
 * @author DAM
 */
public class ProyectoAeropuerto {

 
   
      public static void main(String[] args) throws FileNotFoundException, IOException {
           //BaseDatos bd = new BaseDatos();
           //bd.consultaLogin();
          
         
         
         ModeloLogin modeloLogin = new ModeloLogin();
         Login login = new Login();
         Registro registro = new Registro(login, true);
         Controlador controlador = new Controlador(login, modeloLogin);
         PrincipalVuelo principalVuelo = new PrincipalVuelo();
         InterfazAvion interfazAvion = new InterfazAvion(login, true);
         InterfazDatosPersona interfazDatosPersona = new InterfazDatosPersona(login, true);
         InterfazInfoPersona infoPersona = new InterfazInfoPersona(login, true);
         
         
         
         login.setVisible(true);
         
    }
}
