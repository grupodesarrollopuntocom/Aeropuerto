package Modelo.Tabla;

import Modelo.JDBC.BaseDatos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaEquipaje extends AbstractTableModel {

    private BaseDatos bd;
    ArrayList datosVuelo = new ArrayList();
    String[] nombreColumnas = new String[]{"IdEquipaje", "Descripcion", "Peso", "Dimensiones"};
    Class[] type = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};

    public ModeloTablaEquipaje() {
        bd = new BaseDatos();
        Object[][] pica = new String[bd.consultaTablaEquipaje().length][4];
        pica = bd.consultaTablaEquipaje();
        for (int i = 0; i < pica.length; i++) {
                datosVuelo.add(new String[]{pica[i][0].toString(), pica[i][1].toString(), pica[i][2].toString(), pica[i][3].toString()});    
        }
    }

    @Override
    public int getRowCount() {
        return datosVuelo.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] elemento = (Object[]) datosVuelo.get(rowIndex);
        return elemento[columnIndex];
    }

    public void setValueAt(Object elemento, int row, int col) {
        Object[] fila = (Object[]) datosVuelo.get(row);
        fila[col] = elemento;
        fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return nombreColumnas[col];
    }

    public Class getColumnClass(int col) {
        return type[col];
    }

    public void anadirFila(Object[] fila) {
        datosVuelo.add(fila);
        fireTableDataChanged();
    }

    public void borrarFila(int fila) {
        datosVuelo.remove(fila);
        fireTableDataChanged();
    }
}
