package Modelo.Clases;

public class Personal extends Persona {

    private int idEmpleado;
    private String categoria;

    public Personal(int idEmpleado, String categoria, String dni, String nombre, String apellido1, String apellido2, int telefono) {
        super(dni, nombre, apellido1, apellido2, telefono);
        this.idEmpleado = idEmpleado;
        this.categoria = categoria;
    }
    


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
