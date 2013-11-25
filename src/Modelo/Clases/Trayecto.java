/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author DAM
 */
public class Trayecto {
    private int idTrayecto;
    private String aropueto;
    private String ciudad;
    private String pais;
    private String origen;
    private String destino;

    public Trayecto(int idTrayecto, String aropueto, String ciudad, String pais, String origen, String destino) {
        this.idTrayecto = idTrayecto;
        this.aropueto = aropueto;
        this.ciudad = ciudad;
        this.pais = pais;
        this.origen = origen;
        this.destino = destino;
    }

    public int getIdTrayecto() {
        return idTrayecto;
    }

    public void setIdTrayecto(int idTrayecto) {
        this.idTrayecto = idTrayecto;
    }

    public String getAropueto() {
        return aropueto;
    }

    public void setAropueto(String aropueto) {
        this.aropueto = aropueto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void altaTrayecto(){
        
    }
    public void bajaTrayecto(){
        
    }
    public void modificarTrayecto(){
        
    }
    
    
}
