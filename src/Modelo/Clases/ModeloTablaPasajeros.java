
package Modelo.Clases;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTablaPasajeros extends AbstractTableModel{

    ArrayList datos = new ArrayList();
    String [] nombreColumnas = new String[]{"DNI","Nombre","Apellido 1","Apellido 2","Telefono"};
     Class[] type=new Class[]{java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.Integer.class};
    
    public ModeloTablaPasajeros(ArrayList list){
        this.datos= list;
    }
    
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Object[]elemento=(Object[]) datos.get(rowIndex);
        return  elemento[columnIndex];
    }
    
    
     public void setValueAt(Object elemento,int row,int col){
        Object[]fila=(Object[]) datos.get(row);
        fila[col]=elemento;
        fireTableCellUpdated(row,col);
    }
    
    public String getColumnName(int col){
        return nombreColumnas[col];
    }
    
     public Class getColumnClass(int col){
        return type[col];
    }
     
      public void anadirFila(Object [] fila){
        datos.add(fila);
        fireTableDataChanged();
    }
     
       public void borrarFila(int fila){
        datos.remove(fila);
        fireTableDataChanged();
    }
      
    
}
