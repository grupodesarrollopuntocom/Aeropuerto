package Modelo.Clases;
/**
 *
 * @author DAM
 */
public class Tripulacion extends Persona{
    private int idTripulacion;
    private String categoria;

    public Tripulacion(int idTripulacion, String categoria, String nombre, String apellido1, String apellido2, int edad, int telefono, String correoElectronico) {
        super(nombre, apellido1, apellido2, edad, telefono, correoElectronico);
        this.idTripulacion = idTripulacion;
        this.categoria = categoria;
    }

    public int getIdTripulacion() {
        return idTripulacion;
    }

    public void setIdTripulacion(int idTripulacion) {
        this.idTripulacion = idTripulacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void altaTripulacion(){
        
    }
    public void bajaTripulacion(){
        
    }
    public void modificarTripulacion(){
        
    }
    
}
