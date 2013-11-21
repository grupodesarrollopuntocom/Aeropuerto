package Modelo.Clases;

public class Pasajero extends Persona{

    private int idPasajero;

    public Pasajero(int idPasajero, String dni, String nombre, String apellido1, String apellido2, int telefono) {
        super(dni, nombre, apellido1, apellido2, telefono);
        this.idPasajero = idPasajero;
    }
    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }
}
