/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import legaltime.cache.ClientBillRateCache;
import legaltime.cache.ClientCache;
import legaltime.cache.UserInfoCache;
import legaltime.model.LaborRegisterBean;
import legaltime.model.UserInfoBean;

/**
 *
 * @author bmartin
 */
public class LaborRegisterTableModel extends AbstractTableModel {
    LaborRegisterBean[] laborRegisterBeans;
    String[] columnNames ={"?", "Date", "Description","Hours",
            "Associate","Rate","Total"};
    Class[] columnTypes={Boolean.class, Date.class, String.class, String.class,
            String.class, Double.class, Double.class};
    boolean[] isEditable ={true,true, true,true,
            true, true, false};
    ArrayList<Boolean> invoiceLaborRegister;
    ArrayList<Double> billRates;

    UserInfoCache userInfoCache;
    ClientBillRateCache clientBillRateCache;

    
    

   public Object getValueAt(int row, int col) {
        switch (col){
            case 0: return invoiceLaborRegister.get(0);
            case 1: return laborRegisterBeans[row].getDate();
            case 2: return laborRegisterBeans[row].getDescription();
            case 3: return laborRegisterBeans[row].getMinutes()/60D;
            case 4: return laborRegisterBeans[row].getUserKey();
            case 5: return billRates.get(row);//currency.format(billRates.get(row));
            case 6: return billRates.get(row)* laborRegisterBeans[row].getMinutes()/60D;
            default: return "";
        }


    }

   public void setList(LaborRegisterBean[] laborRegisterBeans_){
       laborRegisterBeans=laborRegisterBeans_;
       invoiceLaborRegister = new ArrayList<Boolean>();
       billRates = new ArrayList<Double>();
       double billRate =0D;
       for(int ndx =0;ndx<laborRegisterBeans.length;ndx++){
          invoiceLaborRegister.add(Boolean.TRUE) ;
          billRate =  clientBillRateCache.getBillRate(
                  laborRegisterBeans[ndx].getClientId()
                  ,laborRegisterBeans[ndx].getUserKey()
                  );
          billRates.add(billRate);
       }

   }
    public LaborRegisterTableModel(){
        userInfoCache = UserInfoCache.getInstance();

    }


    public int getRowCount() {
        try{
            return laborRegisterBeans.length;
        }catch (Exception e){
            return 0;
        }

    }

    public int getColumnCount() {
        return columnNames.length;
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
            case 0: invoiceLaborRegister.set(row, (Boolean)value);
            case 4: laborRegisterBeans[row].setUserKey(((UserInfoBean)((JComboBox)value).getSelectedItem()).getUserKey());
            default: System.err.println("Out of bounds");
        }

    }

    public Double getTotalBill(){
        Double result = 0D;
        double billRate =0;

        for (int ndx=0; ndx< laborRegisterBeans.length;ndx++){
           if (invoiceLaborRegister.get(ndx)== Boolean.TRUE){
                billRate =clientBillRateCache.getBillRate(
                        laborRegisterBeans[ndx].getClientId()
                        , laborRegisterBeans[ndx].getUserKey());
               result += laborRegisterBeans[ndx].getMinutes()/60 * billRate;
           }

        }
        return result;
    }

    



}
