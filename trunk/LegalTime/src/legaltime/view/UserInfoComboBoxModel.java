/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view;

import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import legaltime.cache.ClientCache;
import legaltime.model.UserInfoBean;

/**
 *
 * @author bmartin
 */
public class UserInfoComboBoxModel implements ComboBoxModel {
    
    int selectedNdx=0;
    private Vector<ListDataListener> listModelListeners = new Vector<ListDataListener>();
    private UserInfoBean selItem ;
    private UserInfoBean[] array =  {};
    
    @SuppressWarnings("empty-statement")
    public UserInfoComboBoxModel(){
        
    }

    public void setSelectedItem(Object anItem) {
        selItem=(UserInfoBean)anItem;
    }

    public UserInfoBean getSelectedItem() {
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
    public void setList(UserInfoBean[] array_){
        array = array_;
    }



}
