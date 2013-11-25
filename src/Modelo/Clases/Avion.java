/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author DAM
 */
public class Avion {
    private int idAvion;
    private String matricula;
    private String modelo;
    private int numeroAsientos;

    public Avion(int idAvion, String matricula, String modelo, int numeroAsientos) {
        this.idAvion = idAvion;
        this.matricula = matricula;
        this.modelo = modelo;
        this.numeroAsientos = numeroAsientos;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }
    public void altaAvion(){
        
    }
    public void bajaAvion(){
        
    }
    public void modificarAvion(){
        
    }
}
