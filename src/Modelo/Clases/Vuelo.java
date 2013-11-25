package Modelo.Clases;

import java.util.Date;

public class Vuelo {
    private int idVuelo;
    private int idAvion;
    private int idTrayecto;
    private Date fecha;
    private int horaSalida;
    private int horaLLegada;

    public Vuelo(int idVuelo, int idAvion, int idTrayecto, Date fecha, int horaSalida, int horaLLegada) {
        this.idVuelo = idVuelo;
        this.idAvion = idAvion;
        this.idTrayecto = idTrayecto;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
        this.horaLLegada = horaLLegada;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdTrayecto() {
        return idTrayecto;
    }

    public void setIdTrayecto(int idTrayecto) {
        this.idTrayecto = idTrayecto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(int horaLLegada) {
        this.horaLLegada = horaLLegada;
    }
    public void altaVuelo(){
        
    }
    public void bajaVuelo(){
        
    }
    public void modificarvuelo(){
        
    }
}
