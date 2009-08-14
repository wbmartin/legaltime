/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import legaltime.LegalTimeApp;
import legaltime.model.ExpenseRegisterBean;
import legaltime.model.ExpenseRegisterManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class ExpenseRegisterTableModel extends AbstractTableModel {
    
    private String[] columnNames ={"?", "Date", "Description","Total"};
    private Class[] columnTypes={Boolean.class, Date.class, String.class, Double.class};
    private boolean[] isEditable ={true,true, true,true};


    private ExpenseRegisterManager expenseRegisterManager;
    private ExpenseRegisterBean[] expenseRegisterBeans;
    private EasyLog easyLog;
    private LegalTimeApp legalTimeApp;


    
    

   public Object getValueAt(int row, int col) {
       if (expenseRegisterBeans.length ==0){
           return null;
       }
        switch (col){
            case 0: return expenseRegisterBeans[row].getInvoiceable();
            case 1: return expenseRegisterBeans[row].getExpenseDate();
            case 2: return expenseRegisterBeans[row].getDescription();
            case 3: return expenseRegisterBeans[row].getAmount();
            default: return "";
        }


    }

   public void setList(ExpenseRegisterBean[] expenseRegisterBeans_){
       expenseRegisterBeans=expenseRegisterBeans_;


   }
    public ExpenseRegisterTableModel(){
        expenseRegisterManager = ExpenseRegisterManager.getInstance();
        easyLog = EasyLog.getInstance();
        legalTimeApp = (LegalTimeApp) LegalTimeApp.getInstance();

    }


    public int getRowCount() {
        try{
            return expenseRegisterBeans.length;
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
            case 0: expenseRegisterBeans[row].setInvoiceable((Boolean)value);
                    break;
            case 1: expenseRegisterBeans[row].setExpenseDate((Date) value);
                    break;
            case 2: expenseRegisterBeans[row].setDescription((String)value);
                    break;
            case 3: expenseRegisterBeans[row].setAmount(Double.parseDouble(value.toString()));
                    break;
            default: System.err.println("Out of bounds");
        }
        try {
            expenseRegisterManager.save(expenseRegisterBeans[row]);
            legalTimeApp.setLastActionText("Updated Expense Register");

        } catch (DAOException ex) {
            Logger.getLogger(ExpenseRegisterTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Updating Expense Register"
                    ,getClass().getName(),ex);
        }
        fireTableChanged(new TableModelEvent(this));
      }catch(Exception e){
          easyLog.addEntry(EasyLog.SEVERE,"Error Updating Expense Register"
                    ,getClass().getName(),e);
      }

    }



    /**
     * @return the ExpenseRegisterBeans
     */
    public ExpenseRegisterBean[] getExpenseRegisterBeans() {
        return expenseRegisterBeans;
    }

    public void addRow(int ClientId_,Double amount_
            ,String description_, Boolean invoiceable_
            ,java.util.Date expenseDate_){
        ExpenseRegisterBean expenseRegisterBean = expenseRegisterManager.createExpenseRegisterBean();
        expenseRegisterBean.setAmount(0D);
        expenseRegisterBean.setClientId(ClientId_);
        expenseRegisterBean.setDescription(description_);
        expenseRegisterBean.setInvoiceable(invoiceable_);
        expenseRegisterBean.setExpenseDate(expenseDate_);
        try {
            expenseRegisterManager.save(expenseRegisterBean);
            legalTimeApp.setLastActionText("Added Expense Register Entry");

        } catch (DAOException ex) {
            Logger.getLogger(ExpenseRegisterTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error adding Expense Register entry"
                    ,getClass().getName(),ex);
        }
        fireTableChanged(new TableModelEvent(this));


    }

   
    /**
     * @return the billRates
     */
   
    



}
