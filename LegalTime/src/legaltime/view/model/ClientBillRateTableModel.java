/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import legaltime.cache.UserInfoCache;
import legaltime.model.ClientBillRateBean;
import legaltime.model.ClientBillRateManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class ClientBillRateTableModel extends AbstractTableModel {
   UserInfoCache userInfoCache ;
    String[] columnNames ={"Bill Level", "Hourly Rate"};
    Class[] columnTypes={String.class, Double.class};
    boolean[] isEditable ={false,true};
    //Class[] columnTypes ={String, Double};
    ClientBillRateBean[] clientBillRates;
    ClientBillRateManager clientBillRateManager;
    EasyLog easyLog;


    public ClientBillRateTableModel(){
        userInfoCache = UserInfoCache.getInstance();
        clientBillRateManager = ClientBillRateManager.getInstance();
        easyLog = EasyLog.getInstance();
    }


    public void setDataArray(ClientBillRateBean[] clientBillRates_){
        clientBillRates = clientBillRates_;
    }
    public int getRowCount() {
        try{
            return clientBillRates.length;
        }catch (Exception e){
            return 0;
        }

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    //@Override
//     public Class getColumClass(int col){
//        return columnTypes[col];
//     }

    public Object getValueAt(int row, int col) {

        switch (col){
            case 0: return userInfoCache.getNameFromUserKey(clientBillRates[row].getUserKey());
            case 1: return clientBillRates[row].getBillRate();
            default: return "";
        }



    }

    @Override
    public String getColumnName(int colIndex){
        return columnNames[colIndex];

    }
    @Override
    public Class getColumnClass(int col){
        return columnTypes[col];


    }
    @Override
    public boolean isCellEditable(int row, int col){
        return isEditable[col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        switch(col){
            
            case 1: clientBillRates[row].setBillRate((Double)value);
            default: System.err.println("Out of bounds");
        }
        try {
            clientBillRateManager.save(clientBillRates[row]);
        } catch (DAOException ex) {
            Logger.getLogger(ClientBillRateTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error: Update Client Bill Rate Failed"
                    ,getClass().getName(),ex);
        }

    }


    
    public Integer getClientIdByRow(int row){
        return clientBillRates[row].getClientId();
    }
    public String getUserKeyByRow(int row){
        return clientBillRates[row].getUserKey();
    }

    public ClientBillRateBean getBeanByRow(int row){
        return clientBillRates[row];
    }

}
