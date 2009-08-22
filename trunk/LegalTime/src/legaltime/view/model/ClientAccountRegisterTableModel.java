/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import legaltime.LegalTimeApp;
import legaltime.model.ClientAccountRegisterBean;
import legaltime.model.ClientAccountRegisterManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class ClientAccountRegisterTableModel extends AbstractTableModel {
    
    private String[] columnNames ={"Date", "Description", "Increase","Decrease","Total"};

    private Class[] columnTypes={java.util.Date.class, String.class, Double.class
            , Double.class, Double.class};
    private boolean[] isEditable ={false,true, true,true,false};
    //TODO makeDate editiable by adding effective Date to


    private ClientAccountRegisterManager clientAccountRegisterManager;
    private ClientAccountRegisterBean[] clientAccountRegisterBeans;
    private EasyLog easyLog;
    private LegalTimeApp legalTimeApp;
    private ArrayList<Double> runningTotal = new ArrayList<Double>();


    
    

   public Object getValueAt(int row, int col) {
       if (clientAccountRegisterBeans.length ==0){
           return null;
       }
        switch (col){
            //TODDO add effective Date to ClientAccount Register
            case 0: return clientAccountRegisterBeans[row].getLastUpdate();
            case 1: return clientAccountRegisterBeans[row].getDescription();
            case 2: if (clientAccountRegisterBeans[row].getTranAmt()>0){
                        return clientAccountRegisterBeans[row].getTranAmt();
                    }else{
                        return null;
                    }
            case 3: if (clientAccountRegisterBeans[row].getTranAmt()<0){
                        return -1D * clientAccountRegisterBeans[row].getTranAmt();
                    }else{
                        return null;
                    }
            case 4: return runningTotal.get(row);
            default: return "";
        }


    }

   public void setList(ClientAccountRegisterBean[] clientAccountRegisterBeans_){
       clientAccountRegisterBeans=clientAccountRegisterBeans_;
       runningTotal  = new ArrayList<Double>();
       int ndx =0;
       Double runningSum =0D;
       for(ndx =0;ndx<clientAccountRegisterBeans.length;ndx++ ){
           runningSum +=clientAccountRegisterBeans[ndx].getTranAmt();
           runningTotal.add( runningSum);

       }


   }
    public ClientAccountRegisterTableModel(){
        clientAccountRegisterManager = ClientAccountRegisterManager.getInstance();
        easyLog = EasyLog.getInstance();
        legalTimeApp = (LegalTimeApp) LegalTimeApp.getInstance();

    }


    public int getRowCount() {
        try{
            return clientAccountRegisterBeans.length;
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
            case 0: //clientAccountRegisterBeans[row].setLastUpdate((java.util.Date)value);
                    break;
            case 1: clientAccountRegisterBeans[row].setDescription((String) value);
                    break;
            case 2: clientAccountRegisterBeans[row].setTranAmt(Double.parseDouble(value.toString()));
                    break;
            case 3: clientAccountRegisterBeans[row].setTranAmt(-1D*Double.parseDouble(value.toString()));
                    break;
            default: System.err.println("Out of bounds");
        }
        try {
            clientAccountRegisterManager.save(clientAccountRegisterBeans[row]);
            legalTimeApp.setLastActionText("Updated Client Account Register");

        } catch (DAOException ex) {
            Logger.getLogger(ClientAccountRegisterTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Updating Client Account Register"
                    ,getClass().getName(),ex);
        }
        fireTableChanged(new TableModelEvent(this));
      }catch(Exception e){
          easyLog.addEntry(EasyLog.SEVERE,"Error Updating Client Account Register"
                    ,getClass().getName(),e);
      }

    }



    /**
     * @return the ClientAccountRegisterBeans
     */
    public ClientAccountRegisterBean[] getClientAccountRegisterBeans() {
        return clientAccountRegisterBeans;
    }

    public void addRow(int ClientId_,String description_, Double amount_
            ,java.util.Date effectiveDate_){
        ClientAccountRegisterBean clientAccountRegisterBean = clientAccountRegisterManager.createClientAccountRegisterBean();

        clientAccountRegisterBean.setClientId(ClientId_);
        clientAccountRegisterBean.setDescription(description_);
        clientAccountRegisterBean.setTranAmt(amount_);
        //todo clientAccountRegisterBean.setEffectiveDateDate(effectiveDate_);
        try {
             clientAccountRegisterManager.save(clientAccountRegisterBean);
            legalTimeApp.setLastActionText("Added Client Account Register Entry");

        } catch (DAOException ex) {
            Logger.getLogger(ClientAccountRegisterTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Adding Client Account Register Entry"
                    ,getClass().getName(),ex);
        }
        fireTableChanged(new TableModelEvent(this));


    }

   
    /**
     * @return the billRates
     */
   
    



}
