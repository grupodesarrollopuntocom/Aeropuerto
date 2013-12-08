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
import java.util.ArrayList;

/**
 *
 * @author DAM
 */
public class ProyectoAeropuerto {

    public static void main(String[] args) {
          Login login = new Login(null, true);
          Controlador control = new Controlador(login);

    }
}
