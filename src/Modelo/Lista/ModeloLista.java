
package Modelo.Lista;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class ModeloLista extends AbstractListModel{

    private ArrayList datos = new ArrayList();
    
    @Override
    public int getSize() {
        return this.datos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.datos.get(index);
    }
    
    public void anyadirElemento(Object elemento){
        this.datos.add(elemento);
        this.fireIntervalAdded(this, this.getSize(), this.getSize()+1);
    }
    
    public void eliminarElemento(Object elemento){
        int x = (int) elemento;
        this.datos.remove(x);
        this.fireIntervalRemoved(this, this.getSize(), this.getSize()+1);
    }
}