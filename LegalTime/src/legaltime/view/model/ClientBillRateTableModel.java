/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.lang.Double;
import java.lang.String;
import javax.swing.table.AbstractTableModel;
import legaltime.cache.UserInfoCache;
import legaltime.model.ClientBillRateBean;

/**
 *
 * @author bmartin
 */
public class ClientBillRateTableModel extends AbstractTableModel {
   UserInfoCache userInfoCache ;
    String[] columnNames ={"Bill Level", "Hourly Rate"};
    //Class[] columnTypes ={String, Double};
    ClientBillRateBean[] clientBillRates;
    public ClientBillRateTableModel(){
        userInfoCache = UserInfoCache.getInstance();
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
