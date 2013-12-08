package Modelo.Clases;

import Modelo.JDBC.BaseDatos;

public class ModeloLogin {
    private String nombre;
    private String contrasenia;
    private BaseDatos bd = new BaseDatos();

    public ModeloLogin(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    //METODO COMPRUEBA SI LOS DATOS SON CORRECTOS
    public boolean compruebaDatosLogin(){
        boolean comprobar = false;
        bd.consultaLogin();
        for (int i = 0; i < bd.consultaLogin().size(); i++) {
            if(this.nombre.equals(bd.consultaLogin().get(i)) && this.contrasenia.equals(bd.consultaLogin().get(i+1))){
                comprobar = true;
                i = bd.consultaLogin().size();
            }
            else
                comprobar = false;
        }
        return comprobar;
    }
}
