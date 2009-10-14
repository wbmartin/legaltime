/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;
import legaltime.model.ClientManager;

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

    public void setSelectedItemById(int id_){
        int ndx;
        for(ndx=0; ndx< array.length; ndx++){
            if (array[ndx].getClientId() == id_){break;}
        }
        setSelectedItem(array[ndx]);
    }

    public ClientBean getSelectedItem() {
        return selItem;
    }

    public int getSize() {
        return array.length;
    }

    public Object getElementAt(int index) {
        
        if (array[index].getActiveYn().equals("Y")){
          return array[index];
        }
          return null;
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

    public void addAllClients() {
       ArrayList<ClientBean> temp = new ArrayList<ClientBean>();
       temp = new ArrayList(Arrays.asList(array));
       ClientBean allClients = ClientManager.getInstance().createClientBean();
       allClients.setLastName("All Clients");
       allClients.setActiveYn("Y");
       allClients.setClientId(-1);
       temp.add(0, allClients);
       array = (ClientBean[])temp.toArray(new ClientBean[temp.size()]);
    }



}
