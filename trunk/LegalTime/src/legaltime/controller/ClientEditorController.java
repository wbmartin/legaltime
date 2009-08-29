/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import legaltime.LegalTimeApp;
import legaltime.cache.UserInfoCache;
import legaltime.model.ClientBean;
import legaltime.model.ClientBillRateBean;
import legaltime.model.ClientBillRateManager;
import legaltime.model.ClientManager;
import legaltime.model.FollowupBean;
import legaltime.model.FollowupManager;
import legaltime.model.Manager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.modelsafe.PersistanceManager;
import legaltime.view.ClientEditorView;
import legaltime.view.FollowupItemEditorView;
import legaltime.view.model.ClientBillRateTableModel;
import legaltime.view.model.ClientManagerTableModel;
import legaltime.view.model.FollowupTableModelAbbrev;

/**
 *
 * @author bmartin
 */
public class ClientEditorController implements  InternalFrameListener, ListSelectionListener, ActionListener {

    private static ClientEditorController instance = null;
    private ClientEditorView clientEditorView;
    private EasyLog easyLog;


    private ClientManagerTableModel clientManagerTableModel;
    private ClientBillRateTableModel clientBillRateTableModel;
    private PersistanceManager persistanceManager;
    private ClientManager clientManager;
    private LegalTimeApp mainController;
    private int currentSelectedRow=0;
    private ClientBillRateManager clientBillRateManager;
    private ClientBillRateBean[] clientBillRatebeans;
    private FollowupTableModelAbbrev clientFollowupTableModelAbbrev;
    private FollowupManager followupManager;

    protected ClientEditorController(LegalTimeApp mainController_) {

        mainController = mainController_;
        clientEditorView = new ClientEditorView(this);
        clientEditorView.addInternalFrameListener(this);
        persistanceManager = PersistanceManager.getInstance();
        clientBillRateManager = ClientBillRateManager.getInstance();
        followupManager = FollowupManager.getInstance();
        easyLog = EasyLog.getInstance();

        clientManager = ClientManager.getInstance();
        clientManagerTableModel = new ClientManagerTableModel();
        clientEditorView.getTblClientSelect().setModel(clientManagerTableModel);
        clientEditorView.getTblClientSelect().setAutoCreateRowSorter(true);

        clientBillRateManager = ClientBillRateManager.getInstance();
        clientBillRateTableModel = new ClientBillRateTableModel();
        clientEditorView.getTblBillRates().setModel(clientBillRateTableModel );
        clientEditorView.getTblBillRates().setAutoCreateRowSorter(true);
        clientEditorView.getTblBillRates().getRowSorter().toggleSortOrder(1);

         clientFollowupTableModelAbbrev = new FollowupTableModelAbbrev();
         clientEditorView.getTblClientFollowup().setModel(clientFollowupTableModelAbbrev);
         clientEditorView.getTblClientFollowup().setAutoCreateRowSorter(true);
         clientEditorView.getTblClientFollowup().getRowSorter().toggleSortOrder(1);
        //clientEditorView.getTblClientSelect().setAutoCreateRowSorter(true);

        RowFilter<ClientManagerTableModel, Object> rf = new RowFilter<ClientManagerTableModel,Object>() {
               public boolean include(Entry<? extends ClientManagerTableModel, ? extends Object> entry) {
                   if (entry.getValue(0) !=null) {return true; }
                   return false;
               }
         };
         TableRowSorter<ClientManagerTableModel> sorter = new TableRowSorter<ClientManagerTableModel>(clientManagerTableModel);
         sorter.setRowFilter(rf);
         clientEditorView.getTblClientSelect().setRowSorter(sorter);
         clientEditorView.getTblClientSelect().getRowSorter().toggleSortOrder(0);
         setListeners();
         if (clientManagerTableModel.getRowCount()>0){
            setSelectedRow(currentSelectedRow);
         }else{
             addNewClient();
         }
         
         //Note, init of client view must be below the Table construction
         clientEditorView.buildClientManagerTableDisplay();
   
         clientEditorView.buildClientFollowupTableDisplay();

          clientEditorView.getTblClientFollowup().addMouseListener(new PopupListener());

    }
    public static ClientEditorController getInstance(LegalTimeApp mainController_){
        if (instance == null){
            instance = new ClientEditorController( mainController_);
        }
        return instance;
    }

    public LegalTimeApp getMainController(){
        return mainController;
    }

    public void showClientEditorViewer() {
        if (clientEditorView == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            clientEditorView = new ClientEditorView(this);
        }

        clientEditorView.setVisible(true);
        mainController.getDesktop().add(clientEditorView);
        try {
            clientEditorView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

     private void setListeners(){
        clientEditorView.getTblClientSelect().getSelectionModel().addListSelectionListener(this);

    }

     public void saveChanges(){
        mainController.setStatusText("Evaluating changes...");
        mainController.setLastActionText("No changes to previous client.");
        synchBeanToDisplay(clientManagerTableModel.getBeanByRow(currentSelectedRow ));
       if(clientManagerTableModel.getBeanByRow(currentSelectedRow ).isModified()){
            try {
                clientManager.save(clientManagerTableModel.getBeanByRow(currentSelectedRow));
                mainController.setLastActionText("Saved Client Information");
            } catch (DAOException ex) {
                mainController.setLastActionText("Error saving client save : " + ex.getMessage());
                easyLog.addEntry(EasyLog.SEVERE, "Error Saving Client"
                        , this.getClass().getName(), ex);
            }
        }else{
            mainController.setLastActionText("No changes to previous client.");
        }
        mainController.setStatusText("Ready");
     }


//Controller Function
     public void valueChanged(ListSelectionEvent event) {
         int newSelectedRow;

            if (event.getValueIsAdjusting()) {
               saveChanges();
                return;
            }
            if(clientEditorView.getTblClientSelect().getRowCount() >0
              && clientEditorView.getTblClientSelect().getSelectedRow()>=0){
                newSelectedRow = clientEditorView.getTblClientSelect().getSelectedRow();
                currentSelectedRow=clientEditorView.getTblClientSelect().getRowSorter().convertRowIndexToModel(newSelectedRow);
                synchDisplayToBean(clientManagerTableModel.getBeanByRow(currentSelectedRow ));
            }
                //currentSelectedRow = newSelectedRow;
//            System.out.println(clientEditorView.getTblClientSelect().getSelectedRow() +" "+currentSelectedRow);
            mainController.setStatusText("Ready");
        }

      public void clearDisplay(){
         clientEditorView.getTxtAddress().setText("");
         clientEditorView.getTxtCellPhone().setText("");
         clientEditorView.getTxtCity().setText("");
         clientEditorView.getDtClientSince().setDate(null);
         clientEditorView.getTxtEmail().setText("");
         clientEditorView.getTxtFaxNum().setText("");
         clientEditorView.getTxtFirstName().setText("");
         clientEditorView.getTxtHomePhone().setText("");
         clientEditorView.getTxtLastName().setText("");
         clientEditorView.getTxtNote().setText("");
         clientEditorView.getTxtState().setText("");
         clientEditorView.getTxtWorkPhone().setText("");
         clientEditorView.getTxtZip().setText("");
         clientEditorView.getCboBillingPlan().setSelectedIndex(0);
         clientEditorView.getTxtMonthlyRate().setText("");

         clientBillRateTableModel.setDataArray(new ClientBillRateBean[]{});
         clientEditorView.getTblBillRates().revalidate();
     }

    public void synchBeanToDisplay(ClientBean bean_){
         bean_.setAddress(clientEditorView.getTxtAddress().getText());
         bean_.setCellPhone(clientEditorView.getTxtCellPhone().getText());
         bean_.setCity(clientEditorView.getTxtCity().getText());
         try{
            bean_.setClientSinceDt(clientEditorView.getDtClientSince().getDate());
         }catch(Exception e){
            bean_.setClientSinceDt(new Date());
         }
         bean_.setEmail(clientEditorView.getTxtEmail().getText());
         bean_.setFax(clientEditorView.getTxtFaxNum().getText());
         bean_.setFirstName(clientEditorView.getTxtFirstName().getText());
         bean_.setHomePhone(clientEditorView.getTxtHomePhone().getText());
         bean_.setLastName(clientEditorView.getTxtLastName().getText());
         bean_.setNote(clientEditorView.getTxtNote().getText());
         bean_.setState(clientEditorView.getTxtState().getText());
         bean_.setWorkPhone(clientEditorView.getTxtWorkPhone().getText());
         bean_.setZip(clientEditorView.getTxtZip().getText());
         bean_.setBillType((String)clientEditorView.getCboBillingPlan().getSelectedItem());
         try{
            bean_.setMonthlyBillRate(Double.parseDouble(
                    clientEditorView.getTxtMonthlyRate().getText()));
         }catch (Exception e){
            bean_.setMonthlyBillRate(0);
         }


     }
     //Controller Function
     public void synchDisplayToBean(ClientBean bean_){
         clientEditorView.getTxtAddress().setText(bean_.getAddress());
         clientEditorView.getTxtCellPhone().setText(bean_.getCellPhone());
         clientEditorView.getTxtCity().setText(bean_.getCity());
         clientEditorView.getDtClientSince().setDate(bean_.getClientSinceDt());
         clientEditorView.getTxtEmail().setText(bean_.getEmail());
         clientEditorView.getTxtFaxNum().setText(bean_.getFax());
         clientEditorView.getTxtFirstName().setText(bean_.getFirstName());
         clientEditorView.getTxtHomePhone().setText(bean_.getHomePhone());
         clientEditorView.getTxtLastName().setText(bean_.getLastName());
         clientEditorView.getTxtNote().setText(bean_.getNote());
         clientEditorView.getTxtState().setText(bean_.getState());
         clientEditorView.getTxtWorkPhone().setText(bean_.getWorkPhone());
         clientEditorView.getTxtZip().setText(bean_.getZip());
         clientEditorView.getCboBillingPlan().setSelectedItem(bean_.getBillType());
         try{
         clientEditorView.getTxtMonthlyRate().setText(bean_.getMonthlyBillRate().toString());
         }catch(NullPointerException ex){
            clientEditorView.getTxtMonthlyRate().setText("");
         }

         populateClientBillRateTable(bean_.getClientId().intValue());
        
         populateClientFollowupTable(bean_.getClientId().intValue());

     }
     public void populateClientFollowupTable(int clientId_){
         try {
            clientFollowupTableModelAbbrev.setList(followupManager.loadByWhere(
                    "where client_id=" +clientId_ + " and closed_dt is null order by due_dt" ));
            System.out.println("running");
        } catch (DAOException ex) {
            Logger.getLogger(ClientEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        clientEditorView.getTblClientFollowup().revalidate();
        clientEditorView.getTblClientFollowup().repaint();
        clientEditorView.getTblClientFollowup().getRowSorter().allRowsChanged();
     }


    public void clearChangesToEditedBean(){
         synchDisplayToBean(clientManagerTableModel.getBeanByRow(currentSelectedRow ));
        
     }



    public void addNewClient(){
        ClientBillRateBean clientBillRateBean;
        
        if (clientEditorView.getTblClientSelect().getRowCount() > 0) {
            saveChanges();
        }
        ClientBean newClientBean = clientManager.createClientBean();
        newClientBean.setLastName("_Customer");
        newClientBean.setFirstName("New");
        newClientBean.setClientSinceDt(new Date());
        newClientBean.setActiveYn("Y");
        newClientBean.setMonthlyBillRate(300);
        //synchDisplayToBean(newClientBean);
        try {
            newClientBean = clientManager.save(newClientBean);
            mainController.setLastActionText("Successfully added new client ");
        } catch (DAOException ex) {
            Logger.getLogger(ClientEditorView.class.getName()).log(Level.SEVERE, null, ex);
            mainController.setLastActionText("Error adding new client: " + ex.getMessage());
            easyLog.addEntry(EasyLog.SEVERE, "Error adding new client"
                    , getClass().getName(), ex);
        }
        persistanceManager.loadClientCache();
        //System.out.println("clients:" + ClientCache.getInstance().getLength());
        clientEditorView.getTblClientSelect().revalidate();
        clientEditorView.getTblClientSelect().repaint();
        clientEditorView.getTblClientSelect().getRowSorter().allRowsChanged();
        setSelectedRow(0);


        try {
            Manager.getInstance().getConnection().setAutoCommit(false);
            UserInfoCache userInfoCache = UserInfoCache.getInstance();
            for(int ndx =0;ndx< userInfoCache.getCache().length;ndx++){
                clientBillRateBean = clientBillRateManager.createClientBillRateBean();
                clientBillRateBean.setClientId(newClientBean.getClientId());
                clientBillRateBean.setUserKey(userInfoCache.getCache()[ndx].getUserKey());
                clientBillRateBean.setBillRate(userInfoCache.getCache()[ndx].getDefaultBillRate());
                clientBillRateManager.save(clientBillRateBean);

            }
            Manager.getInstance().getConnection().commit();
            Manager.getInstance().getConnection().setAutoCommit(true);
            populateClientBillRateTable(newClientBean.getClientId());

        } catch (DAOException ex) {
            Logger.getLogger(ClientEditorController.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE, "Error: Adding New Client Bill Rate"
                    , getClass().getName(), ex);
        } catch (java.sql.SQLException ex) {
            Logger.getLogger(ClientEditorController.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE, "Error: Adding New Client Bill Rate (SQL)"
                    , getClass().getName(), ex);
        }



     }



    public void deactivateClient(){
         mainController.setLastActionText("Client deactivation in process. ");
         clientManagerTableModel.getBeanByRow(currentSelectedRow).setActiveYn("N");
        try {
            clientManager.save(clientManagerTableModel.getBeanByRow(currentSelectedRow));
            mainController.setLastActionText("Client deactivated. ");
        } catch (DAOException ex) {
            Logger.getLogger(ClientEditorView.class.getName()).log(Level.SEVERE, null, ex);
            mainController.setLastActionText("Error saving client " + ex.getMessage());
        }
        clientEditorView.getTblClientSelect().revalidate();
        clientEditorView.getTblClientSelect().repaint();
        mainController.setLastActionText("Client deactivation completed. ");
        clientEditorView.getTblClientSelect().getRowSorter().allRowsChanged();
        clearDisplay();
     }


     private void setSelectedRow(int row ){
         clientEditorView.getTblClientSelect().getSelectionModel().setSelectionInterval(row, row);
     }

     private void populateClientBillRateTable(int client_){
        String whereClause= "where client_id = " + client_;
        try {
            clientBillRatebeans = clientBillRateManager.loadByWhere(whereClause);
            clientBillRateTableModel.setDataArray(clientBillRatebeans);
            clientEditorView.getTblBillRates().revalidate();
            clientEditorView.getTblBillRates().repaint();
            clientEditorView.getTblBillRates().getRowSorter().allRowsChanged();
            
        } catch (DAOException ex) {
            Logger.getLogger(ClientEditorController.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE, "Error Loading Client Bill Rates"
                    , getClass().getName(), ex);
        }
        clientEditorView.getTblBillRates().revalidate();
        clientEditorView.getTblBillRates().repaint();
        clientEditorView.getTblBillRates().getRowSorter().allRowsChanged();

     }




     public void internalFrameClosing( InternalFrameEvent e ) {
        saveChanges();
        
        persistanceManager.loadClientCache();
        clientEditorView.dispose();

     }

    public void internalFrameOpened(InternalFrameEvent e) {

    }

    public void internalFrameClosed(InternalFrameEvent e) {

    }

    public void internalFrameIconified(InternalFrameEvent e) {

    }

    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    public void internalFrameActivated(InternalFrameEvent e) {

    }

    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        try{
            //if (e.getActionCommand().equals("cboBillPlanChanged")){
            if(e.getActionCommand().equals("EDIT_FOLLOWUP_ITEM")){
                editSelectedFollowUpItem();
            }
            else if (e.getActionCommand().equals("cboBillPlanChanged")){
                if (clientEditorView.getCboBillingPlan().getSelectedItem().equals("HOURLY")){
                    clientEditorView.getTxtMonthlyRate().setEnabled(false);
                    clientEditorView.getTblBillRates().setVisible(true);
                }else{
                    clientEditorView.getTxtMonthlyRate().setEnabled(true);
                    clientEditorView.getTblBillRates().setVisible(false);
                }
            }else{
                System.err.println("Unexpected Action: " + e.getActionCommand());
            }
            

           //}
        }catch(NullPointerException ex){
            easyLog.addEntry(EasyLog.INFO, "Error Changing Billing Plan"
                    , getClass().getName(), ex);
        }
    }

    public void editSelectedFollowUpItem() {
        FollowupItemEditorView followupItemEditorView =
                    new FollowupItemEditorView(mainController.getMainFrame());
        FollowupBean followupBean;
        int currentSelectedFollowupRow=0;
        currentSelectedFollowupRow=clientEditorView.getTblClientFollowup().getRowSorter()
                .convertRowIndexToModel(clientEditorView.getTblClientFollowup().getSelectedRow());
        followupItemEditorView.setFollowupItem(clientFollowupTableModelAbbrev
                .getBeanByRow(currentSelectedFollowupRow));
        followupItemEditorView.setVisible(true);
        if(followupItemEditorView.isSelectionConfirmed()){
            followupBean = followupItemEditorView.getFollowupItem();
            try {
                followupManager.save(followupBean);
                populateClientFollowupTable(followupBean.getClientId());
            } catch (DAOException ex) {
                Logger.getLogger(ClientEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        followupItemEditorView.dispose();
    }
   
class PopupListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                clientEditorView.getFollowupTableMenu().show(e.getComponent(),
                           e.getX(), e.getY());

            }
        }
    }
}
