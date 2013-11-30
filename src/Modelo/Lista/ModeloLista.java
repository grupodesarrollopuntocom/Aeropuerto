
package Modelo.Lista;

import java.util.ArrayList;
import javax.swing.AbstractListModel;


public class ModeloLista extends AbstractListModel{

    
    ArrayList datos = new ArrayList();
    
    public ModeloLista(ArrayList list){
        this.datos = list;
    }
    
    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getElementAt(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
