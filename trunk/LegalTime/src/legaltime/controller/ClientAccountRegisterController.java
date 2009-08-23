/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import legaltime.AppPrefs;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientCache;
import legaltime.model.ClientAccountRegisterBean;
import legaltime.model.ClientAccountRegisterManager;
import legaltime.model.ClientBean;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.view.ClientAccountRegisterView;
import legaltime.view.model.ClientAccountRegisterTableModel;
import legaltime.view.model.ClientComboBoxModel;
import legaltime.view.renderer.ClientComboBoxRenderer;

/**
 *
 * @author bmartin
 */
public class ClientAccountRegisterController implements TableModelListener, ActionListener{
    private static ClientAccountRegisterController instance;
    private ClientAccountRegisterView clientAccountRegisterView;
    private LegalTimeApp mainController;
    private EasyLog easyLog;
    private AppPrefs appPrefs;
    private ClientAccountRegisterTableModel clientAccountRegisterTableModel;
    private ClientComboBoxModel clientComboBoxModel;
    private ClientAccountRegisterBean[] clientAccountRegisterBeans;
    private ClientAccountRegisterManager clientAccountRegisterManager;
    private ClientComboBoxRenderer clientComboBoxRenderer;
    private ProcessControllerAccounting processControllerAccounting;
    protected ClientAccountRegisterController(LegalTimeApp mainController_){
        mainController = mainController_;
        clientAccountRegisterView = new ClientAccountRegisterView(this);
        easyLog = EasyLog.getInstance();
        appPrefs = AppPrefs.getInstance();
        processControllerAccounting = ProcessControllerAccounting.getInstance();
        //clientAccountRegisterBeans = new ClientAccountRegisterBean[];
        clientAccountRegisterManager = ClientAccountRegisterManager.getInstance();
        clientAccountRegisterTableModel = new ClientAccountRegisterTableModel();
        clientAccountRegisterView.getTblAccountRegister().setModel(clientAccountRegisterTableModel);
        clientAccountRegisterTableModel.addTableModelListener(this);
        clientAccountRegisterView.formatTblAccountRegister();
        clientComboBoxModel = new ClientComboBoxModel();
        clientComboBoxRenderer = new ClientComboBoxRenderer();
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        clientAccountRegisterView.getCboClient().setModel(clientComboBoxModel);
        clientAccountRegisterView.getCboClient().setRenderer(clientComboBoxRenderer );
        clientAccountRegisterView.getCboClient().setActionCommand("CLIENT_CHANGED");
        clientAccountRegisterView.getCboClient().addActionListener(this);
        clientAccountRegisterView.getCboClient().setMaximumRowCount(30);
        clientAccountRegisterView.getTblAccountRegister().addMouseListener(new PopupListener());

    }
    public static ClientAccountRegisterController getInstance(LegalTimeApp mainController_){
        if (instance == null){
            instance = new ClientAccountRegisterController(mainController_);
        }

        return instance;
    }

    public void tableChanged(TableModelEvent e) {

    }

    public void refreshTblAccountRegister(int clientId_){
        try {
            clientAccountRegisterBeans = clientAccountRegisterManager.loadByWhere("where client_id = " + clientId_);
        } catch (DAOException ex) {
            Logger.getLogger(ClientAccountRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE, "Error loading client account register beans by where"
                    , getClass().getName(), ex);

        }
        clientAccountRegisterTableModel.setList(clientAccountRegisterBeans);
        clientAccountRegisterView.getTblAccountRegister().revalidate();
        clientAccountRegisterView.scrollRowTblPaymentLog(clientAccountRegisterTableModel.getRowCount()-1);
    }
    public void refreshTblAccountRegister(){
     int clientId;
        try{
            clientId= ((ClientBean) clientAccountRegisterView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
     refreshTblAccountRegister(clientId);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("REVERSE_SELECTED_TRAN")){
            reverseSelectedTran();
        }else if(e.getActionCommand().equals("CLIENT_CHANGED")){
            refreshTblAccountRegister();
        }
        
    }
    public void showClientAccountRegisterView() {
        if (clientAccountRegisterView == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            clientAccountRegisterView = new ClientAccountRegisterView(this);
        }
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        clientAccountRegisterView.getCboClient().revalidate();
        clientAccountRegisterView.setVisible(true);
        mainController.getDesktop().add(clientAccountRegisterView);
        try {
            clientAccountRegisterView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            easyLog.addEntry(EasyLog.INFO, "ClientAccountRegister show vetoed"
                    , getClass().getName(), e);
        }
    }

    private void reverseSelectedTran() {
        int selectedRow =  clientAccountRegisterView.getTblAccountRegister().getSelectedRow();
        String result ="";
//        reversTranByAccountRegisterId(clientAccountRegisterTableModel.
//                getClientAccountRegisterId(selectedRow));
        result = processControllerAccounting.reversAccountRegisterTranById(clientAccountRegisterTableModel.
                getClientAccountRegisterId(selectedRow),"USER");
        if(!result.equals(ProcessControllerAccounting.RESULT_SUCCESS)){
            clientAccountRegisterView.showPopupMsg(result);
        }

        refreshTblAccountRegister();

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
                clientAccountRegisterView.getTableMenu().show(e.getComponent(),
                           e.getX(), e.getY());
                
            }
        }
    }

}
