/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import legaltime.AppPrefs;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientCache;
import legaltime.cache.UserInfoCache;
import legaltime.model.ClientBean;
import legaltime.model.ExpenseRegisterBean;
import legaltime.model.ExpenseRegisterManager;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.modelsafe.EasyLog;
import legaltime.view.InvoiceEditorView;
import legaltime.view.model.ClientComboBoxModel;
import legaltime.view.model.ExpenseRegisterTableModel;
import legaltime.view.model.LaborRegisterTableModel;
import legaltime.view.model.UserInfoComboBoxModel;
import legaltime.view.renderer.ClientComboBoxRenderer;
import org.jdesktop.application.Task;

/**
 *
 * @author bmartin
 */
public class InvoiceController implements TableModelListener, ActionListener{
    private InvoiceEditorView invoiceEditorView;
    private ProcessControllerInvoice processControllerInvoice;
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
    
     private UserInfoCache userInfoCache;
    private UserInfoComboBoxModel userInfoComboBoxModel;

    private LegalTimeApp mainController;
    private static InvoiceController instance;
    private AppPrefs appPrefs;

    protected InvoiceController(LegalTimeApp mainController_){
        mainController = mainController_;
        processControllerInvoice = new ProcessControllerInvoice();
        easyLog = EasyLog.getInstance();
        appPrefs = AppPrefs.getInstance();
        invoiceEditorView = new InvoiceEditorView(this);
        mainController.getDesktop().add(invoiceEditorView);

        laborRegisterManager =LaborRegisterManager.getInstance();
        laborRegisterTableModel = new LaborRegisterTableModel();
        invoiceEditorView.getTblLaborRegister().setAutoCreateRowSorter(true);
        invoiceEditorView.getTblLaborRegister().setModel(laborRegisterTableModel);
        invoiceEditorView.getTblLaborRegister().getRowSorter().toggleSortOrder(1);
        laborRegisterTableModel.addTableModelListener(this);
        userInfoComboBoxModel = new UserInfoComboBoxModel();
         userInfoCache=UserInfoCache.getInstance();
         userInfoComboBoxModel.setList(userInfoCache.getCache());
        invoiceEditorView.formatTableLaborRegister();
        


        expenseRegisterManager =ExpenseRegisterManager.getInstance();
        expenseRegisterTableModel = new ExpenseRegisterTableModel();
        invoiceEditorView.getTblExpenseRegister().setAutoCreateRowSorter(true);
        invoiceEditorView.getTblExpenseRegister().setModel(expenseRegisterTableModel);
        invoiceEditorView.getTblExpenseRegister().getRowSorter().toggleSortOrder(1);
        expenseRegisterTableModel.addTableModelListener(this);
        invoiceEditorView.formatTableExpenseRegister();


        clientComboBoxModel = new ClientComboBoxModel();
        //clientComboBoxModel.setList(ClientCache.getInstance().getCache());

        invoiceEditorView.getCboClient().setModel(clientComboBoxModel);
        clientComboBoxRenderer = new ClientComboBoxRenderer ();
        invoiceEditorView.getCboClient().setRenderer(clientComboBoxRenderer );
        invoiceEditorView.getCboClient().addActionListener(this);
        invoiceEditorView.getCboClient().setMaximumRowCount(Integer.parseInt(appPrefs.getValue(AppPrefs.CLIENTCBO_DISPLAY_ROWS)));

         
         
         
 


    }

    public static InvoiceController getInstance(LegalTimeApp mainController_){
        if (instance == null){
            instance = new InvoiceController( mainController_);
        }
        return instance;
    }


       public void showInvoiceEditorViewer() {

        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        invoiceEditorView.getCboClient().updateUI();
        invoiceEditorView.setVisible(true);
        
        try {
            invoiceEditorView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }


     public void setMainController(LegalTimeApp mainController_){
        mainController = mainController_;
    }
     public void tableChanged(TableModelEvent e) {
        invoiceEditorView.setAccountBalance(
             processControllerInvoice.getInvoiceTotal(
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
                processControllerInvoice.getInvoiceTotal(invoiceableItems
                    , expensableItems));

    }
    public void refreshLaborRegisterTable(int clientId_){
        invoiceableItems = processControllerInvoice.getInvoiceableLaborItems(clientId_);
        laborRegisterTableModel.setList(invoiceableItems);
        invoiceEditorView.getTblLaborRegister().revalidate();
        invoiceEditorView.getTblLaborRegister().getRowSorter().allRowsChanged();
    }

    public void  refreshExpenseRegisterTable(int clientId_){
        expensableItems = processControllerInvoice.getInvoiceableExpenseItems(clientId_);
        expenseRegisterTableModel.setList(expensableItems);
        invoiceEditorView.getTblExpenseRegister().revalidate();
        invoiceEditorView.getTblExpenseRegister().getRowSorter().allRowsChanged();

    }

    public void generateInvoice(){

        int clientId=0;
        boolean invoiceResult = false;
        try{
            clientId = ((ClientBean)invoiceEditorView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException ex){
           JOptionPane.showMessageDialog(invoiceEditorView, "Please select a client to invoice.");
           easyLog.addEntry(EasyLog.INFO, "User Attempted Invoice before selecting Client", getClass().getName(), ex);
           return;

        }

        invoiceResult=processControllerInvoice.generateInvoice(clientId);
        

        if(invoiceResult){
                    JOptionPane.showMessageDialog(invoiceEditorView, "The PDF " +
                            "has been saved to the output " +
                            "location specified in your File > Preferences Screen: "
                            + appPrefs.getValue(AppPrefs.INVOICE_OUTPUT_PATH) );

        refreshLaborAndExpenseRegisterTable();
        }else{
                    JOptionPane.showMessageDialog(invoiceEditorView
                            , "The invoice did not generate successfully." +
                            "Please check the system logs and send error" +
                            "messages to the developer." );
        }


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

    /**
     * @return the userInfoComboBoxModel
     */
    public UserInfoComboBoxModel getUserInfoComboBoxModel() {
        return userInfoComboBoxModel;
    }

    public void generateAllInvoices(){

        int dialogResult =JOptionPane.showConfirmDialog(
                invoiceEditorView
                , "You are about to generate invoices, do you want to continue"
                , "User Confirmation Required"
                , JOptionPane.YES_NO_OPTION);
        if(dialogResult != JOptionPane.YES_OPTION){return;}
          Task task = new Task(LegalTimeApp.getApplication()) {

            @Override
            protected Object doInBackground() throws Exception {
                return processControllerInvoice.generateAllOutstandingInvoices();
            }

        };
        task.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
               
                if("done".equals(propertyName)){
                        boolean result = true;//processControllerInvoice.generateAllOutstandingInvoices();

                        if(result){

                             JOptionPane.showMessageDialog(invoiceEditorView, "The Invoice PDFs " +
                                            "have been saved to the output " +
                                            "location specified in your File > Preferences Screen: "
                                            + appPrefs.getValue(AppPrefs.INVOICE_OUTPUT_PATH) );
                        }else{
                             JOptionPane.showMessageDialog(invoiceEditorView
                                            , "Not all invoices generated successfully." +
                                            "Please check the system logs and send error" +
                                            "messages to the developer."
                                            ,"Error Generating Invoices"
                                            ,JOptionPane.ERROR_MESSAGE );
                        }
                }
              }
        });
        LegalTimeApp.getApplication().getPrimaryView().getTaskMonitor().setForegroundTask(task);
        task.execute();
        

    }



}
