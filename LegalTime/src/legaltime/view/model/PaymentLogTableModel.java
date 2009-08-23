/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import legaltime.LegalTimeApp;
import legaltime.controller.ProcessControllerAccounting;
import legaltime.model.ClientAccountRegisterManager;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class PaymentLogTableModel extends AbstractTableModel {
    
    private String[] columnNames ={"Date", "Description", "Amount"};

    private Class[] columnTypes={java.util.Date.class, String.class
            , Double.class };
    private boolean[] isEditable ={true,true, true};
    //TODO makeDate editiable by adding effective Date to


    
    private PaymentLogBean[] paymentLogBeans;
    private EasyLog easyLog;
    private LegalTimeApp legalTimeApp;
    private ArrayList<Double> runningTotal = new ArrayList<Double>();
    private ClientAccountRegisterManager  clientAccountRegisterManager;
    private PaymentLogManager paymentLogManager;
    ProcessControllerAccounting processControllerAccounting;



    
    

   public Object getValueAt(int row, int col) {
       if (paymentLogBeans.length ==0){
           return null;
       }
        switch (col){
            //TODDO add effective Date to ClientAccount Register
            case 0: return paymentLogBeans[row].getEffectiveDate();
            case 1: return paymentLogBeans[row].getDescription();
            case 2: return paymentLogBeans[row].getAmount();
            
            default: return "";
        }


    }

   public void setList(PaymentLogBean[] paymentLogBeans_){
       paymentLogBeans=paymentLogBeans_;
       


   }
    public PaymentLogTableModel(){
        
        easyLog = EasyLog.getInstance();
        legalTimeApp = (LegalTimeApp) LegalTimeApp.getInstance();
        processControllerAccounting = ProcessControllerAccounting.getInstance();

    }


    public int getRowCount() {
        try{
            return paymentLogBeans.length;
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
      try{
        switch(col){
            case 0: paymentLogBeans[row].setEffectiveDate((java.util.Date)value);
                    break;
            case 1: paymentLogBeans[row].setDescription((String) value);
                    break;
            case 2: paymentLogBeans[row].setAmount(Double.parseDouble(value.toString()));
                    break;
            
            default: System.err.println("Out of bounds"+ getClass().getName());
        }

            if(processControllerAccounting.savePaymentLogBean(paymentLogBeans[row])){
                legalTimeApp.setLastActionText("Updated Payment Log");
            }

        
        fireTableChanged(new TableModelEvent(this));
      }catch(Exception e){
          easyLog.addEntry(EasyLog.SEVERE,"Error Updating Payment Log"
                    ,getClass().getName(),e);
      }

    }



    /**
     * @return the PaymentLogBeans
     */
    public PaymentLogBean[] getPaymentLogBeans() {
        return paymentLogBeans;
    }

    public void addRow(int clientId_,String description_, Double amount_
            ,java.util.Date effectiveDate_){

        

        PaymentLogBean paymentLogBean = paymentLogManager.createPaymentLogBean();

        paymentLogBean.setClientId(clientId_);
        paymentLogBean.setDescription(description_);
        paymentLogBean.setAmount(amount_);
        paymentLogBean.setEffectiveDate(effectiveDate_);
        
        //todo paymentLogBean.setEffectiveDateDate(effectiveDate_);
        if (processControllerAccounting.addPaymentLogBean(paymentLogBean)){
             legalTimeApp.setLastActionText("Added Payment Log Entry");
        }
        fireTableChanged(new TableModelEvent(this));


    }

    public int getPaymentLogId(int row_) {
        try{
            return paymentLogBeans[row_].getPaymentLogId();
        }catch(NullPointerException e){
            return -1;
        }

        
    }

   
    /**
     * @return the billRates
     */
   
    



}
