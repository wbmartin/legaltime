/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private LegalTimeApp mainController;

    
    private FollowupBean[] followupItems;

    private ClientComboBoxRenderer clientComboBoxRenderer;
    private FollowupBean followupBean;
    private FollowupManager followupManager;
    
    private ClientComboBoxModel clientComboBoxModel;

    
    private static FollowupController instance;
    private AppPrefs appPrefs;
    private ClientComboBoxModel clientTableComboBoxModel;
    private ClientCache clientCache;

    protected FollowupController(LegalTimeApp mainController_){
        mainController = mainController_;
        easyLog = EasyLog.getInstance();
        appPrefs = AppPrefs.getInstance();
        followupView = new FollowupView(this);
        clientCache = ClientCache.getInstance();

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
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());

        followupView.getCboClient().setModel(clientComboBoxModel);
        clientComboBoxRenderer = new ClientComboBoxRenderer ();
        followupView.getCboClient().setRenderer(clientComboBoxRenderer );
        followupView.getCboClient().addActionListener(this);
        followupView.getCboClient().setMaximumRowCount(30);

    }

    public static FollowupController getInstance(LegalTimeApp mainController_){
        if (instance == null){
            instance = new FollowupController( mainController_);
        }
        return instance;
    }


       public void showFollowupViewer() {
        if (followupView == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            followupView = new FollowupView(this);
        }
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        followupView.getCboClient().revalidate();
        followupView.setVisible(true);
        mainController.getDesktop().add(followupView);
        try {
            followupView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }


     public void setMainController(LegalTimeApp mainController_){
        mainController = mainController_;
    }
     public void tableChanged(TableModelEvent e) {
       
    }

    public void actionPerformed(ActionEvent e) {
       refreshFollowupTable();
    }

    public void refreshFollowupTable(){
         int clientId;
        try{
            clientId= ((ClientBean) followupView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
        try {
            followupItems = followupManager.loadAll();
        } catch (DAOException ex) {
            Logger.getLogger(FollowupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        followupTableModel.setList(followupItems);
        followupView.getTblFollowup().revalidate();
        followupView.getTblFollowup().getRowSorter().allRowsChanged();

       

    }
    

    

//    public void addAdminExpense() {
//        //followupView.getTblExpenseRegister().getT
//        int clientId=0;
//        try{
//            clientId = ((ClientBean)followupView.getCboClient().getSelectedItem()).getClientId();
//        }catch(NullPointerException ex){
//           JOptionPane.showMessageDialog(followupView, "Please select a client to add an Expense to.");
//           easyLog.addEntry(EasyLog.INFO, "User Attempted Adding Expense Before Selecting Client", getClass().getName(), ex);
//           return;
//
//        }
//        expenseRegisterTableModel.addRow(clientId,0D,"Administrative Expense",true,new java.util.Date());
//        refreshFollowupTable(clientId);
//    }




    public void addFollowupItem(){
        followupBean = followupManager.createFollowupBean();

        followupBean.setClientId(68);
        followupBean.setDescription("TestFollowup");
        followupBean.setOpenedDate(new java.util.Date());
        followupBean.setClosedDt(new java.util.Date());
        followupBean.setDueDt(new java.util.Date());
        //todo followupBean.setEffectiveDateDate(effectiveDate_);
        try {
             followupManager.save(followupBean);
            mainController.setLastActionText("Added Client Account Register Entry");

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

}
