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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import legaltime.AppPrefs;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.view.PaymentLogView;
import legaltime.view.PostPaymentHelper;
import legaltime.view.model.ClientComboBoxModel;
import legaltime.view.model.PaymentLogTableModel;
import legaltime.view.renderer.ClientComboBoxRenderer;

/**
 *
 * @author bmartin
 */
public class PaymentLogController implements TableModelListener, ActionListener{
    private static PaymentLogController instance;
    private PaymentLogView paymentLogView;
    private PostPaymentHelper postPaymentHelper;
    private LegalTimeApp mainController;
    private EasyLog easyLog;
    private AppPrefs appPrefs;
    private PaymentLogTableModel paymentLogTableModel;
    private ClientComboBoxModel clientComboBoxModel;
    private PaymentLogBean[] paymentLogBeans;
    private PaymentLogManager paymentLogManager;
    private ClientComboBoxRenderer clientComboBoxRenderer;
    ProcessControllerAccounting processControllerAccounting;
    protected PaymentLogController(LegalTimeApp mainController_){
        mainController = mainController_;
        processControllerAccounting = ProcessControllerAccounting.getInstance();
        paymentLogView = new PaymentLogView(this);
        postPaymentHelper = new PostPaymentHelper(mainController.getMainFrame());
        mainController.getDesktop().add(paymentLogView);
        easyLog = EasyLog.getInstance();
        appPrefs = AppPrefs.getInstance();
        //paymentLogBeans = new paymentLogBean[];
        paymentLogManager = PaymentLogManager.getInstance();
        paymentLogTableModel = new PaymentLogTableModel();
        paymentLogView.getTblPaymentLog().setModel(paymentLogTableModel);
        paymentLogTableModel.addTableModelListener(this);
        paymentLogView.formatTblPaymentLog();
        clientComboBoxModel = new ClientComboBoxModel();
        clientComboBoxRenderer = new ClientComboBoxRenderer();
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        paymentLogView.getCboClient().setModel(clientComboBoxModel);
        paymentLogView.getCboClient().setRenderer(clientComboBoxRenderer );
        paymentLogView.getCboClient().setActionCommand("CLIENT_CHANGED");
        paymentLogView.getCboClient().addActionListener(this);
        paymentLogView.getCboClient().setMaximumRowCount(30);
        paymentLogView.getTblPaymentLog().addMouseListener(new PopupListener());
        paymentLogView.getCmdAddPayment().addActionListener(this);
        paymentLogView.getCmdAddPayment().setActionCommand("POST_PAYMENT");
        //==================


    }
    public static PaymentLogController getInstance(LegalTimeApp mainController_){
        if (instance == null){
            instance = new PaymentLogController(mainController_);
        }

        return instance;
    }

    public void tableChanged(TableModelEvent e) {

    }

    public void refreshTblPaymentLog(int clientId_){
        try {
            paymentLogBeans = paymentLogManager.loadByWhere("where client_id = " + clientId_);
        } catch (DAOException ex) {
            Logger.getLogger(PaymentLogController.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error Loading Payment Log beans by where"
                    , getClass().getName(), ex);
        }
        paymentLogTableModel.setList(paymentLogBeans);
        paymentLogView.getTblPaymentLog().revalidate();
        paymentLogView.scrollRowTblPaymentLog(paymentLogTableModel.getRowCount()-1);

    }

    public int getPaymentLogCboClientId(){
        int clientId;
        try{
            clientId= ((ClientBean) paymentLogView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
        return clientId;
    }
    
    

    public void refreshTblPaymentLog(){
     refreshTblPaymentLog(getPaymentLogCboClientId());
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("REVERSE_SELECTED_TRAN")){
            reverseSelectedTran();
        }else if(e.getActionCommand().equals("POST_PAYMENT")){
            postPaymentHelper.showDialog((ClientBean)paymentLogView.getCboClient().getSelectedItem());
            if (postPaymentHelper.isSelectionConfirmed()){
              addPayment(postPaymentHelper.getCboClientId()
                    ,postPaymentHelper.getDtcEffectiveDate().getDate()
                    ,postPaymentHelper.getPaymentAmount()
                    );
            }
        }else if(e.getActionCommand().equals("CLIENT_CHANGED")){
            refreshTblPaymentLog();
        }
        
    }
    public void showPaymentLogView() {
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        paymentLogView.getCboClient().revalidate();
        paymentLogView.setVisible(true);
        
        try {
            paymentLogView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            easyLog.addEntry(EasyLog.INFO, "Show Payment Log View Vetoed"
                    , getClass().getName(), e);
        }
    }

    private void reverseSelectedTran() {
        int selectedRow =  paymentLogView.getTblPaymentLog().getSelectedRow();
        processControllerAccounting.reversePaymentLogById(paymentLogTableModel.
                getPaymentLogId(selectedRow));
        refreshTblPaymentLog();

    }
   

    private void addPayment(int clientId_, java.util.Date effectiveDate_, Double amount_) {
        
        //paymentLogTableModel.addRow(clientId, "Payment Received", 0D, new java.util.Date());
        PaymentLogBean paymentLogBean;
        paymentLogBean = paymentLogManager.createPaymentLogBean();
        paymentLogBean.setClientId(clientId_);
        paymentLogBean.setAmount(amount_);
        paymentLogBean.setDescription("Payment Received");
        paymentLogBean.setEffectiveDate( effectiveDate_);
        processControllerAccounting.addPaymentLogBean(paymentLogBean);

        refreshTblPaymentLog();

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
                paymentLogView.getTableMenu().show(e.getComponent(),
                           e.getX(), e.getY());
                
            }
        }
    }

}
