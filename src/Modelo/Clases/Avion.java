
package Modelo.Clases;
public class Avion {
    private int codigo;
    private String Companya;
    private int capacidad;
    private int peso;
    private double dimensionLargo;
    private double dimensionAncho;
    private int cont;
    private String prueba;
    

    public Avion(int codigo, String Companya, int capacidad, int peso, double dimensionLargo, double dimensionAncho) {
        this.codigo = codigo;
        this.Companya = Companya;
        this.capacidad = capacidad;
        this.peso = peso;
        this.dimensionLargo = dimensionLargo;
        this.dimensionAncho = dimensionAncho;
    }
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the Companya
     */
    public String getCompanya() {
        return Companya;
    }

    /**
     * @param Companya the Companya to set
     */
    public void setCompanya(String Companya) {
        this.Companya = Companya;
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the dimensionLargo
     */
    public double getDimensionLargo() {
        return dimensionLargo;
    }

    /**
     * @param dimensionLargo the dimensionLargo to set
     */
    public void setDimensionLargo(double dimensionLargo) {
        this.dimensionLargo = dimensionLargo;
    }

    /**
     * @return the dimensionAncho
     */
    public double getDimensionAncho() {
        return dimensionAncho;
    }

    /**
     * @param dimensionAncho the dimensionAncho to set
     */
    public void setDimensionAncho(double dimensionAncho) {
        this.dimensionAncho = dimensionAncho;
    }
    
    
}
