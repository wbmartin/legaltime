/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.renderer;


import java.text.SimpleDateFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import legaltime.cache.ClientCache;

/**
 *
 * @author bmartin
 */
 public class ClientTableCellRenderer extends DefaultTableCellRenderer {
    ClientCache clientCache;
    int ndx =0;
    static ClientTableCellRenderer instance=null;
    protected ClientTableCellRenderer() {
        super();
        clientCache = ClientCache.getInstance();
    }
 

    public static ClientTableCellRenderer getInstance(){
        if (instance == null){
            instance = new ClientTableCellRenderer();
        }
        return instance;
    }
    @Override
    public void setValue(Object value) {


        
        try{
            for(ndx =0;ndx<clientCache.getLength();ndx++){
                if(value.equals(clientCache.getCache()[ndx].getClientId())){
                    break;
                }
            }
            
            setText((value == null) ? "" : clientCache.getCache()[ndx].getLastName()
                    + ", " + clientCache.getCache()[ndx].getFirstName());
            setHorizontalAlignment(SwingConstants.LEFT);
        }catch(Exception e){
            setText("");
        }
    }
}
