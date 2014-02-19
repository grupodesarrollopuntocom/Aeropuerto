/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CampoDePruebas;

import Modelo.Clases.Pasajero;
import Modelo.JDBC.BaseDatos;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Prueba {

    public class ProyectoAeropuerto {
    }

    public static void main(String[] args) {
        //(int idPasajero, String clase, int idVuelo, String nombre, String apellido1, String apellido2, int edad, int telefono, String correoElectronico, String direccion, String dni)
//        Pasajero pasajero = new Pasajero(5, "Primera", 3, "Amgio", "Amigo2", "Amigo3", 25, 618018500, "amigo@gmail.com", "calle false", "218018500A");
        BaseDatos bd = new BaseDatos();
//        bd = new BaseDatos();
//        bd.ingresaDatosPasajero(pasajero);
//            System.out.println("Finalizo correctamente");
        ArrayList lista = new ArrayList();
        lista = bd.consultaFila("pasajeros", "idpasajeros", String.valueOf(1), 11);
        Pasajero pasajero = new Pasajero(
                Integer.valueOf(String.valueOf(lista.get(0))),
                (String) lista.get(1), 
                Integer.valueOf(String.valueOf(lista.get(0))), 
                (String) lista.get(3), 
                (String) lista.get(4), 
                (String) lista.get(5), 
                Integer.valueOf(String.valueOf(lista.get(6))), 
                Integer.valueOf(String.valueOf(lista.get(7))), 
                (String) lista.get(8), 
                (String) lista.get(9), 
                (String) lista.get(10));
        System.out.println(pasajero.getNombre()+" "+pasajero.getEdad());
    }

}
