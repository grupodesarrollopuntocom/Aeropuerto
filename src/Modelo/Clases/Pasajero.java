package Modelo.Clases;

public class Pasajero extends Persona {

    private int idPasajero;
    private String clase;
    private int idVuelo;

    public Pasajero(int idPasajero, String clase, int idVuelo, String nombre, String apellido1, String apellido2, int edad, int telefono, String correoElectronico, String direccion, String dni) {
        super(nombre, apellido1, apellido2, edad, telefono, correoElectronico, direccion, dni);
        this.idPasajero = idPasajero;
        this.clase = clase;
        this.idVuelo = idVuelo;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }
    
}
