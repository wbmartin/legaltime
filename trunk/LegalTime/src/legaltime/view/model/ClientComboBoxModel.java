/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;

/**
 *
 * @author bmartin
 */
public class ClientComboBoxModel implements ComboBoxModel {
    
    int selectedNdx=0;
    private Vector<ListDataListener> listModelListeners = new Vector<ListDataListener>();
    private ClientBean selItem ;
    private ClientBean[] array =  {};
    
    @SuppressWarnings("empty-statement")
    public ClientComboBoxModel(){
        
    }

    public void setSelectedItem(Object anItem) {
        selItem=(ClientBean)anItem;
    }

    public ClientBean getSelectedItem() {
        return selItem;
    }

    public int getSize() {
        return array.length;
    }

    public Object getElementAt(int index) {
        return array[index];
    }

    public void addListDataListener(ListDataListener l) {
        listModelListeners.add( l );
    }

    public void removeListDataListener(ListDataListener l) {
        listModelListeners.remove( l );
    }
    public void setList(ClientBean[] array_){
        array = array_;
    }



}
