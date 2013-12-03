/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import Archivos.Archivo;
import Controlador.Controlador;
import Modelo.JDBC.BaseDatos;
import Vista.Login;
import Vista.Registro;
import java.io.FileNotFoundException;
import java.io.IOException;

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
         
         
         login.setVisible(true);
         
    }
}
