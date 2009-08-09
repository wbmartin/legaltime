/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientBillRateCache;
import legaltime.cache.ClientCache;
import legaltime.cache.UserInfoCache;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.model.UserInfoBean;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class LaborRegisterTableModel extends AbstractTableModel {
    private LaborRegisterBean[] laborRegisterBeans;
    private String[] columnNames ={"?", "Date", "Description","Hours",
            "Associate","Rate","Total"};
    private Class[] columnTypes={Boolean.class, Date.class, String.class, String.class,
            String.class, Double.class, Double.class};
    private boolean[] isEditable ={true,true, true,true,
            true, true, false};


    private LaborRegisterManager laborRegisterManager;

    private UserInfoCache userInfoCache;
    private ClientBillRateCache clientBillRateCache;
    private EasyLog easyLog;
    private LegalTimeApp legalTimeApp;


    
    

   public Object getValueAt(int row, int col) {
       if (laborRegisterBeans.length ==0){
           return null;
       }
        switch (col){
            case 0: return laborRegisterBeans[row].getInvoiceable();
            case 1: return laborRegisterBeans[row].getActivityDate();
            case 2: return laborRegisterBeans[row].getDescription();
            case 3: return laborRegisterBeans[row].getMinutes()/60D;
            case 4: return laborRegisterBeans[row].getUserKey();
            case 5: return laborRegisterBeans[row].getBillRate();//currency.format(billRates.get(row));
            case 6: return laborRegisterBeans[row].getBillRate()* laborRegisterBeans[row].getMinutes()/60D;
            default: return "";
        }


    }

   public void setList(LaborRegisterBean[] laborRegisterBeans_){
       laborRegisterBeans=laborRegisterBeans_;


   }
    public LaborRegisterTableModel(){
        userInfoCache = UserInfoCache.getInstance();
        clientBillRateCache = ClientBillRateCache.getInstance();
        laborRegisterManager = LaborRegisterManager.getInstance();
        easyLog = EasyLog.getInstance();
        legalTimeApp = (LegalTimeApp) LegalTimeApp.getInstance();

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
            case 0: laborRegisterBeans[row].setInvoiceable((Boolean)value);
                    break;
            case 4: laborRegisterBeans[row].setUserKey(((UserInfoBean)((JComboBox)value).getSelectedItem()).getUserKey());
                    break;
            default: System.err.println("Out of bounds");
        }
        try {
            laborRegisterManager.save(laborRegisterBeans[row]);
            legalTimeApp.setLastActionText("Updated Labor Register");

        } catch (DAOException ex) {
            Logger.getLogger(LaborRegisterTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error updating Labor Register"
                    ,getClass().getName(),ex);
        }
        fireTableChanged(new TableModelEvent(this));


    }



    /**
     * @return the laborRegisterBeans
     */
    public LaborRegisterBean[] getLaborRegisterBeans() {
        return laborRegisterBeans;
    }

   
    /**
     * @return the billRates
     */
   
    



}
