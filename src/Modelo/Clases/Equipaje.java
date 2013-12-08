/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author DAM
 */
public class Equipaje {
    private int idEquipaje;
    private String informacion;
    private int dimesionAncho;
    private int dimensionAlto;
    private boolean facturado;

    public Equipaje(int idEquipaje, String informacion, int dimesionAncho, int dimensionAlto, boolean facturado) {
        this.idEquipaje = idEquipaje;
        this.informacion = informacion;
        this.dimesionAncho = dimesionAncho;
        this.dimensionAlto = dimensionAlto;
        this.facturado = facturado;
    }

    public int getIdEquipaje() {
        return idEquipaje;
    }

    public void setIdEquipaje(int idEquipaje) {
        this.idEquipaje = idEquipaje;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public int getDimesionAncho() {
        return dimesionAncho;
    }

    public void setDimesionAncho(int dimesionAncho) {
        this.dimesionAncho = dimesionAncho;
    }

    public int getDimensionAlto() {
        return dimensionAlto;
    }

    public void setDimensionAlto(int dimensionAlto) {
        this.dimensionAlto = dimensionAlto;
    }

    public boolean isFacturado() {
        return facturado;
    }

    public void setFacturado(boolean facturado) {
        this.facturado = facturado;
    }
    public void ingresarEquipaje(){
        
    }
    public void borrarEquipaje(){
        
    }
    public void modificarEquipaje(){
        
    }
    
}
