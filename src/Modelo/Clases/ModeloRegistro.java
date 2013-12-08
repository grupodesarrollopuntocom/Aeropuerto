
package Modelo.Clases;


public class ModeloRegistro {
    
    private String nombre, apellido1, apellido2, dni, telefono, direccion, correo, edad;
    
    public ModeloRegistro(){
        
    }

    public ModeloRegistro(String nombre, String apellido1, String apellido2, String dni, String telefono, String direccion, String correo, String edad) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.edad = edad;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    
    public void recogeDatos(String nombre, String apellido1, String apellido2, String direccion, String dni, String edad, String correo, String telefono){
        
        this.getNombre();
        this.getApellido1();
        this.getApellido2();
        this.getDireccion();
        this.getDni();
        this.getEdad();
        this.getCorreo();
        this.getTelefono();
        
        
    }
    
    public void guardaDatos(ModeloRegistro registro){
        
        registro.setNombre(nombre);
        registro.setApellido1(apellido1);
        registro.setApellido1(apellido2);
        registro.setDni(dni);
        registro.setEdad(edad);
        registro.setTelefono(telefono);
        registro.setDireccion(direccion);
        registro.setCorreo(correo);
        
    }
    
}
