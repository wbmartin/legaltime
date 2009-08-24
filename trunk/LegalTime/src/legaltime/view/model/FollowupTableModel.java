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
import legaltime.model.FollowupBean;
import legaltime.model.FollowupManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class FollowupTableModel extends AbstractTableModel {
    
    private String[] columnNames ={"Due Date", "Client", "Description","Open Date","Close Date"};

    private Class[] columnTypes={java.util.Date.class, String.class, String.class
            , java.util.Date.class, java.util.Date.class};
    private boolean[] isEditable ={true,true, true,true,true};
    //TODO makeDate editiable by adding effective Date to


    private FollowupManager followupManager;
    private FollowupBean[] followupBeans;
    private EasyLog easyLog;
    private LegalTimeApp legalTimeApp;
    


    
    

   public Object getValueAt(int row, int col) {
       if (followupBeans.length ==0){
           return null;
       }
        switch (col){
            //TODDO add effective Date to ClientAccount Register
            case 0: return followupBeans[row].getDueDt();
            case 1: return followupBeans[row].getClientId();
            case 2: return followupBeans[row].getDescription();
            case 3: return followupBeans[row].getOpenedDate();
            case 4: return followupBeans[row].getClosedDt();
            default: return "";
        }


    }

   public void setList(FollowupBean[] followupBeans_){
       followupBeans=followupBeans_;
       

   }
    public FollowupTableModel(){
        followupManager = FollowupManager.getInstance();
        easyLog = EasyLog.getInstance();
        legalTimeApp = (LegalTimeApp) LegalTimeApp.getInstance();

    }


    public int getRowCount() {
        try{
            return followupBeans.length;
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
            case 0: followupBeans[row].setDueDt((java.util.Date)value);
                    break;
            case 1: followupBeans[row].setClientId(Integer.getInteger( value.toString()));
                    break;
            case 2: followupBeans[row].setDescription(value.toString());
                    break;
            case 3: followupBeans[row].setOpenedDate((java.util.Date)value);
                    break;
            case 4: followupBeans[row].setClosedDt((java.util.Date)value);
                    break;
            default: System.err.println("Out of bounds" + getClass().getName());
        }
        try {
            followupManager.save(followupBeans[row]);
            legalTimeApp.setLastActionText("Updated Client Account Register");

        } catch (DAOException ex) {
            Logger.getLogger(FollowupTableModel.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the FollowupBeans
     */
    public FollowupBean[] getFollowupBeans() {
        return followupBeans;
    }

    

    public int getFollowupId(int row_) {
        try{
            return followupBeans[row_].getFollowupId();
        }catch(NullPointerException e){
            return -1;
        }

        
    }

   
    /**
     * @return the billRates
     */
   
    



}
