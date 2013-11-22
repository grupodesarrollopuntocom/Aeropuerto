package Modelo.Clases;

public class Trayecto {

    private int idTrayecto;
    private String origen;
    private String destino;
    private int asientosLibres;
    private int asientosOcupados;

    public Trayecto(int idTrayecto, String origen, String destino, int asientosLibres, int asientosOcupados) {
        this.idTrayecto = idTrayecto;
        this.origen = origen;
        this.destino = destino;
        this.asientosLibres = asientosLibres;
        this.asientosOcupados = asientosOcupados;
    }

    public int getIdTrayecto() {
        return idTrayecto;
    }

    public void setIdTrayecto(int idTrayecto) {
        this.idTrayecto = idTrayecto;
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

    public int getAsientosLibres() {
        return asientosLibres;
    }

    public void setAsientosLibres(int asientosLibres) {
        this.asientosLibres = asientosLibres;
    }

    public int getAsientosOcupados() {
        return asientosOcupados;
    }

    public void setAsientosOcupados(int asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }
}
