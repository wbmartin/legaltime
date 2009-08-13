/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.modelsafe.EasyLog;
import legaltime.view.InvoiceEditorView;
import legaltime.view.model.ClientComboBoxModel;
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

    private ClientComboBoxRenderer clientComboBoxRenderer;
    private LaborInvoiceItemBean laborInvoiceItemBean;
    private LaborInvoiceItemManager laborInvoiceItemManager;
    private LaborRegisterManager laborRegisterManager;

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


        ClientComboBoxModel clientComboBoxModel = new ClientComboBoxModel();
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
               laborRegisterTableModel.getLaborRegisterBeans()));
    }

      public void actionPerformed(ActionEvent e) {
       refreshLaborRegisterTable();
    }

    public void refreshLaborRegisterTable(){
         int clientId;
        try{
            clientId= ((ClientBean) invoiceEditorView.getCboClient().getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
        invoiceableItems = invoiceController.getInvoiceableLaborItems(clientId);
        laborRegisterTableModel.setList(invoiceableItems);
        invoiceEditorView.getTblLaborRegister().revalidate();
        invoiceEditorView.getTblLaborRegister().getRowSorter().allRowsChanged();

        invoiceEditorView.setAccountBalance(invoiceController.getInvoiceTotal(invoiceableItems));
        double test = invoiceController.getInvoiceTotal(invoiceableItems);
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

        invoiceController.buildAndSaveInvoice(
                clientId
                ,laborRegisterTableModel.getLaborRegisterBeans());
        JOptionPane.showMessageDialog(invoiceEditorView, "The PDF has been saved to your desktop." +
                "In normal operations in would be saved to a centralized " +
                "archive and displayed for the user.");

        refreshLaborRegisterTable();


    }

}
