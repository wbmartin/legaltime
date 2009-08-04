/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view;

import javax.swing.table.AbstractTableModel;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;

/**
 *
 * @author bmartin
 */
public class ClientManagerTableModel extends AbstractTableModel {
    ClientCache clientCache ;
    String[] columnNames ={"Name", "City"};

    public ClientManagerTableModel(){
        clientCache = ClientCache.getInstance();
    }


    public int getRowCount() {
        try{
            return clientCache.getCache().length;
        }catch (Exception e){
            return 0;
        }

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int row, int col) {
        if(row < clientCache.getLength()
                && clientCache.getCache()[row].getActiveYn().equals("N")){
                return null;
        }
        switch (col){  
            case 0: return clientCache.getCache()[row].getLastName()
                    +", " +clientCache.getCache()[row].getFirstName();
            case 1: return clientCache.getCache()[row].getCity();
            default: return "";
        }
        
        
    }

    @Override
    public String getColumnName(int colIndex){
        return columnNames[colIndex];

    }

    public Integer getClientIdByRow(int row){
        return clientCache.getCache()[row].getClientId();
    }

    public ClientBean getBeanByRow(int row){
        return clientCache.getCache()[row];
    }


    



}
