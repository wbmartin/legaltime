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
import legaltime.LegalTimeApp;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;
import legaltime.model.ExpenseRegisterBean;
import legaltime.model.ExpenseRegisterManager;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.view.InvoiceEditorView;
import legaltime.view.model.ClientComboBoxModel;
import legaltime.view.model.ExpenseRegisterTableModel;
import legaltime.view.model.LaborRegisterTableModel;
import legaltime.view.renderer.ClientComboBoxRenderer;

/**
 *
 * @author bmartin
 */
public class InvoiceViewController implements TableModelListener, ActionListener{
    private InvoiceEditorView invoiceEditorView;
    private InvoiceController invoiceController;
    private EasyLog easyLog;
    private LaborRegisterTableModel laborRegisterTableModel;

    private LaborRegisterBean[] invoiceableItems;
    private ExpenseRegisterBean[] expensableItems;

    private ClientComboBoxRenderer clientComboBoxRenderer;
    private LaborInvoiceItemBean laborInvoiceItemBean;
    private LaborInvoiceItemManager laborInvoiceItemManager;
    private LaborRegisterManager laborRegisterManager;
    private ClientComboBoxModel clientComboBoxModel;

    private ExpenseRegisterManager expenseRegisterManager;
    private ExpenseRegisterTableModel  expenseRegisterTableModel;

    private LegalTimeApp mainController;
    private static InvoiceViewController instance;

    protected InvoiceViewController(LegalTimeApp mainController_){
        mainController = mainController_;
        invoiceController = new InvoiceController();
        easyLog = EasyLog.getInstance();
        invoiceEditorView = new InvoiceEditorView(this);

        laborRegisterManager =LaborRegisterManager.getInstance();
        laborRegisterTableModel = new LaborRegisterTableModel();
        invoiceEditorView.getTblLaborRegister().setAutoCreateRowSorter(true);
        invoiceEditorView.getTblLaborRegister().setModel(laborRegisterTableModel);
        invoiceEditorView.getTblLaborRegister().getRowSorter().toggleSortOrder(1);
        laborRegisterTableModel.addTableModelListener(this);
        invoiceEditorView.formatTableLaborRegister();
        

        expenseRegisterManager =ExpenseRegisterManager.getInstance();
        expenseRegisterTableModel = new ExpenseRegisterTableModel();
        invoiceEditorView.getTblExpenseRegister().setAutoCreateRowSorter(true);
        invoiceEditorView.getTblExpenseRegister().setModel(expenseRegisterTableModel);
        invoiceEditorView.getTblExpenseRegister().getRowSorter().toggleSortOrder(1);
        expenseRegisterTableModel.addTableModelListener(this);
        invoiceEditorView.formatTableExpenseRegister();


        clientComboBoxModel = new ClientComboBoxModel();
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        invoiceEditorView.getCboClient().setModel(clientComboBoxModel);
        clientComboBoxRenderer = new ClientComboBoxRenderer ();
        invoiceEditorView.getCboClient().setRenderer(clientComboBoxRenderer );
        invoiceEditorView.getCboClient().addActionListener(this);

    }

    public static InvoiceViewController getInstance(LegalTimeApp mainController_){
        if (instance == null){
            instance = new InvoiceViewController( mainController_);
        }
        return instance;
    }


       public void showInvoiceEditorViewer() {
        if (invoiceEditorView == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            invoiceEditorView = new InvoiceEditorView(this);
        }
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        invoiceEditorView.getCboClient().revalidate();
        invoiceEditorView.setVisible(true);
        mainController.getDesktop().add(invoiceEditorView);
        try {
            invoiceEditorView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }


     public void setMainController(LegalTimeApp mainController_){
        mainController = mainController_;
    }
     public void tableChanged(TableModelEvent e) {
        invoiceEditorView.setAccountBalance(
             invoiceController.getInvoiceTotal(
               laborRegisterTableModel.getLaborRegisterBeans()
               , expenseRegisterTableModel.getExpenseRegisterBeans()));
    }

    public void actionPerformed(ActionEvent e) {
       refreshLaborAndExpenseRegisterTable();
    }

    public void refreshLaborAndExpenseRegisterTable(){
         int clientId;
        try{
            clientId= ((ClientBean) invoiceEditorView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
        
        refreshLaborRegisterTable(clientId);
        refreshExpenseRegisterTable(clientId);

        invoiceEditorView.setAccountBalance(
                invoiceController.getInvoiceTotal(invoiceableItems
                    , expensableItems));
        
    }
    public void refreshLaborRegisterTable(int clientId_){
        invoiceableItems = invoiceController.getInvoiceableLaborItems(clientId_);
        laborRegisterTableModel.setList(invoiceableItems);
        invoiceEditorView.getTblLaborRegister().revalidate();
        invoiceEditorView.getTblLaborRegister().getRowSorter().allRowsChanged();
    }

    public void  refreshExpenseRegisterTable(int clientId_){
        expensableItems = invoiceController.getInvoiceableExpenseItems(clientId_);
        expenseRegisterTableModel.setList(expensableItems);
        invoiceEditorView.getTblExpenseRegister().revalidate();
        invoiceEditorView.getTblExpenseRegister().getRowSorter().allRowsChanged();

    }

    public void generateInvoice(){

        int clientId=0;
        try{
            clientId = ((ClientBean)invoiceEditorView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException ex){
           JOptionPane.showMessageDialog(invoiceEditorView, "Please select a client to invoice.");
           easyLog.addEntry(EasyLog.INFO, "User Attempted Invoice before selecting Client", getClass().getName(), ex);
           return;

        }
        PaymentLogManager paymentLogManager = PaymentLogManager.getInstance();
        PaymentLogBean[] paymentsLogBeans = null;
        try {
            paymentsLogBeans = paymentLogManager.loadByWhere("where invoice_id is null and "
                    + "client_id = " + clientId);
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceViewController.class.getName()).log(Level.SEVERE, null, ex);
             easyLog.addEntry(EasyLog.INFO, "Error Loaing Payments", getClass().getName(), ex);

        }

        invoiceController.buildAndSaveInvoice(
                clientId
                ,laborRegisterTableModel.getLaborRegisterBeans()
                ,expenseRegisterTableModel.getExpenseRegisterBeans()
                ,paymentsLogBeans);
        JOptionPane.showMessageDialog(invoiceEditorView, "The PDF has been saved to the output " +
                "location specified in your File > Preferences Screen. " );

        refreshLaborAndExpenseRegisterTable();


    }

    public void addAdminExpense() {
        //invoiceEditorView.getTblExpenseRegister().getT
        int clientId=0;
        try{
            clientId = ((ClientBean)invoiceEditorView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException ex){
           JOptionPane.showMessageDialog(invoiceEditorView, "Please select a client to add an Expense to.");
           easyLog.addEntry(EasyLog.INFO, "User Attempted Adding Expense Before Selecting Client", getClass().getName(), ex);
           return;

        }
        expenseRegisterTableModel.addRow(clientId,0D,"Administrative Expense",true,new java.util.Date());
        refreshExpenseRegisterTable(clientId);
    }

}
