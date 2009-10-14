/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import legaltime.AppPrefs;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;
import legaltime.model.FollowupBean;
import legaltime.model.FollowupManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.view.FollowupItemEditorView;
import legaltime.view.FollowupView;
import legaltime.view.model.ClientComboBoxModel;
import legaltime.view.model.FollowupTableModel;
import legaltime.view.renderer.ClientComboBoxRenderer;

/**
 *
 * @author bmartin
 */
public class FollowupController implements TableModelListener, ActionListener{
    private FollowupView followupView;
    private EasyLog easyLog;
    private FollowupTableModel followupTableModel;
    private LegalTimeController mainController;

    
    private FollowupBean[] followupItems;

    
    private FollowupBean followupBean;
    private FollowupManager followupManager;
    private FollowupItemEditorView followupItemEditorView;
    
    private ClientComboBoxModel clientComboBoxModel;
    private ClientComboBoxRenderer clientComboBoxRenderer;
    
    private static FollowupController instance;
    private AppPrefs appPrefs;
    private ClientComboBoxModel clientTableComboBoxModel;
    private ClientCache clientCache;
    String filterWhereClause;

    

    protected FollowupController(LegalTimeController mainController_){
        mainController = mainController_;
        easyLog = EasyLog.getInstance();
        appPrefs = AppPrefs.getInstance();
        followupView = new FollowupView(this);
        mainController.getDesktop().add(followupView);
        
        clientCache = ClientCache.getInstance();
        filterWhereClause="";

        followupManager =FollowupManager.getInstance();
        followupTableModel = new FollowupTableModel();
        followupView.getTblFollowup().setAutoCreateRowSorter(true);
        followupView.getTblFollowup().setModel(followupTableModel);
        followupView.getTblFollowup().getRowSorter().toggleSortOrder(1);
        followupTableModel.addTableModelListener(this);
        clientTableComboBoxModel = new ClientComboBoxModel();
        clientTableComboBoxModel.setList(clientCache.getCache());

        followupView.formatTableFollowup();


       


        clientComboBoxModel = new ClientComboBoxModel();
        //clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        followupView.getCboClient().setModel(clientComboBoxModel);
        clientComboBoxRenderer = new ClientComboBoxRenderer ();
        followupView.getCboClient().setRenderer(clientComboBoxRenderer );
        followupView.getCboClient().addActionListener(this);
        followupView.getCboClient().setMaximumRowCount(appPrefs.CLIENTCBO_DISPLAY_ROWS);
        refreshFollowupTable();
      
    }

    public static FollowupController getInstance(LegalTimeController mainController_){
        if (instance == null){
            instance = new FollowupController( mainController_);
        }
        return instance;
    }

    public void showClientAccountRegisterView(int clientId_) {
        showFollowupViewer();
        clientComboBoxModel.setSelectedItemById(clientId_);
        refreshFollowupTable();
    }
       public void showFollowupViewer() {
           boolean firstShow = false;
        if (followupView == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            followupView = new FollowupView(this);
            firstShow = true;
            
        }
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        clientComboBoxModel.addAllClients();
        followupView.getCboClient().updateUI();
        followupView.setVisible(true);
       
        //if (firstShow){
            
        //}
        try {
            followupView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }




     public void setMainController(LegalTimeController mainController_){
        mainController = mainController_;
    }
     public void tableChanged(TableModelEvent e) {
       
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == null){
            //JOptionPane.showConfirmDialog(followupView, "");
        }
        else if (e.getActionCommand().equals("cboClientChanged")){
            applyFilter();
        }
       
    }

    public void refreshFollowupTable(){
        filterWhereClause = buildWhereClause();
        try {
            followupItems = followupManager.loadByWhere(filterWhereClause);
        } catch (DAOException ex) {
            Logger.getLogger(FollowupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        followupTableModel.setList(followupItems);
        followupView.getTblFollowup().revalidate();
        followupView.getTblFollowup().getRowSorter().allRowsChanged();

       

    }
    

    
    public void addFollowupItem(){
        followupBean = followupManager.createFollowupBean();
        int clientId;
        try{
            clientId= ((ClientBean) followupView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
        if(clientId ==0){
            JOptionPane.showConfirmDialog(followupView, "Please select the followup client", "Selecta client", JOptionPane.WARNING_MESSAGE);
            return;
        }
        followupBean.setClientId(clientId);
        followupBean.setDescription("New Follow Up Item");
        followupBean.setOpenedDate(new java.util.Date());
        followupBean.setClosedDt(null);
        followupBean.setDueDt(new java.util.Date());
        //todo followupBean.setEffectiveDateDate(effectiveDate_);
        try {
             followupManager.save(followupBean);
            mainController.setLastActionText("Added Followup Item Entry");

        } catch (DAOException ex) {
            Logger.getLogger(FollowupTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Adding Client Account Register Entry"
                    ,getClass().getName(),ex);
        }
        refreshFollowupTable();


    }

    /**
     * @return the clientTableComboBoxModel
     */
    public ClientComboBoxModel getClientTableComboBoxModel() {
        return clientTableComboBoxModel;
    }

    public void applyFilter() {
        
        refreshFollowupTable();
    }

    public void clearFilter() {
        clientComboBoxModel.setSelectedItem(null);
        followupView.getCkOpenEvents().setSelected(true);
        filterWhereClause = buildWhereClause();
        refreshFollowupTable();
    }

    private String buildWhereClause(){
        StringBuffer whereClause = new StringBuffer();
        whereClause.setLength(0);
        whereClause.append("where 1=1 ");


         int clientId;
        try{
            clientId= ((ClientBean) followupView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
         if (clientId>0){
             whereClause.append(" and client_id = " +clientId +" ");
         }

         if(followupView.getCkOpenEvents().isSelected()){
             whereClause.append(" and closed_dt is null ");
         }

         return whereClause.toString();
    }



    

}
