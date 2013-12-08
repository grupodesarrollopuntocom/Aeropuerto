package Modelo.Clases;

import java.util.Date;

public class Vuelo {

    private int idVuelo;
    private int idAvion;
    private String origen;
    private String destino;
    private String horaSalida;
    private String horaLLegada;

    public Vuelo(int idVuelo, int idAvion, String origen, String destino, String horaSalida, String horaLLegada) {
        this.idVuelo = idVuelo;
        this.idAvion = idAvion;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLLegada = horaLLegada;
    }

    public Vuelo(int idVuelo) {
        this.idVuelo = idVuelo;
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

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(String horaLLegada) {
        this.horaLLegada = horaLLegada;
    }

    public void compruebaOrigenCorrecto() {
    }

    public boolean esPalabraCaracteres(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (!((palabra.charAt(i) > 64 && palabra.charAt(i) < 91)
                    || (palabra.charAt(i) > 96 && palabra.charAt(i) < 123))) {
                return false;
            }
        }
        return true;
    }

    public boolean compruebaHora(String hora) {
        boolean comprueba = false;
        String cadena1, cadena2, cadena3;
        if (hora.length() == 5) {
            cadena1 = hora.substring(0, 2);
            cadena2 = hora.substring(3, 5);
            cadena3 = hora.substring(2, 3);
            if (this.esNumero(cadena1) && this.esNumero(cadena2)) {
                if (Integer.parseInt(cadena1) < 24 && Integer.parseInt(cadena2) < 60 && cadena3.equals(":")) {
                    comprueba = true;
                }
            }
        }
        return comprueba;
    }

    public boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
