/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author DAM
 */
public class Pasajero extends Persona{
    private int idPasajero;

    public Pasajero(int idPasajero, String nombre, String apellido1, String apellido2, int edad, int telefono, String correoElectronico) {
        super(nombre, apellido1, apellido2, edad, telefono, correoElectronico);
        this.idPasajero = idPasajero;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }
    public void altaPasajero(){
        
    }
    public void bajaPasajero(){
        
    }
    public void modificarPasajero(){
        
    }
}
