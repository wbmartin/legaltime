/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view;

import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import legaltime.cache.ClientCache;

/**
 *
 * @author bmartin
 */
public class StandardComboBoxModel implements ComboBoxModel {
    
    int selectedNdx=0;
    private Vector<ListDataListener> listModelListeners = new Vector<ListDataListener>();
    private Object selItem = new Object();
    private Object[] array =  {};
    
    @SuppressWarnings("empty-statement")
    public StandardComboBoxModel(){
        
    }

    public void setSelectedItem(Object anItem) {
        selItem=anItem;
    }

    public Object getSelectedItem() {
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
    public void setList(Object[] array_){
        array = array_;
    }

}
