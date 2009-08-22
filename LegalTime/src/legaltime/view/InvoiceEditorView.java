/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceManager.java
 *
 * Created on Jul 19, 2009, 8:52:08 AM
 */

package legaltime.view;

import java.text.DecimalFormat;
import legaltime.view.renderer.CurrencyTableCellRenderer;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;


import javax.swing.table.TableColumn;
import legaltime.LegalTimeApp;
import legaltime.controller.InvoiceController;
import legaltime.view.renderer.DateTableCellEditor;
import org.jdesktop.application.Action;


/**
 *
 * @author bmartin
 */
public class InvoiceEditorView extends javax.swing.JInternalFrame  {

    private DecimalFormat currencyFormatter;
    private InvoiceController invoiceViewController ;
    
    /** Creates new form InvoiceManager */
    public InvoiceEditorView(InvoiceController invoiceViewController_) {
         invoiceViewController = invoiceViewController_;
        initComponents();
       

        currencyFormatter = new DecimalFormat("$#,##0.00");


        


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboClient = new javax.swing.JComboBox();
        lblClient = new javax.swing.JLabel();
        lblAccountBalance = new javax.swing.JLabel();
        lblAccountBalanceValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaborRegister = new javax.swing.JTable();
        lblBillableHours = new javax.swing.JLabel();
        lblOtherCharges = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblExpenseRegister = new javax.swing.JTable();
        cmdGenerateInvoice = new javax.swing.JButton();
        cmdAddExpense = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(InvoiceEditorView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        cboClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClient.setName("cboClient"); // NOI18N

        lblClient.setText(resourceMap.getString("lblClient.text")); // NOI18N
        lblClient.setName("lblClient"); // NOI18N

        lblAccountBalance.setText(resourceMap.getString("lblAccountBalance.text")); // NOI18N
        lblAccountBalance.setName("lblAccountBalance"); // NOI18N

        lblAccountBalanceValue.setText(resourceMap.getString("lblAccountBalanceValue.text")); // NOI18N
        lblAccountBalanceValue.setName("lblAccountBalanceValue"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblLaborRegister.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "?", "Date", "Bill Level", "Description", "Hours", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblLaborRegister.setName("tblLaborRegister"); // NOI18N
        jScrollPane1.setViewportView(tblLaborRegister);
        tblLaborRegister.getColumnModel().getColumn(0).setMinWidth(25);
        tblLaborRegister.getColumnModel().getColumn(0).setPreferredWidth(25);
        tblLaborRegister.getColumnModel().getColumn(0).setMaxWidth(50);
        tblLaborRegister.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        tblLaborRegister.getColumnModel().getColumn(1).setMinWidth(75);
        tblLaborRegister.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblLaborRegister.getColumnModel().getColumn(1).setMaxWidth(75);
        tblLaborRegister.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        tblLaborRegister.getColumnModel().getColumn(2).setMinWidth(100);
        tblLaborRegister.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblLaborRegister.getColumnModel().getColumn(2).setMaxWidth(100);
        tblLaborRegister.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        tblLaborRegister.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        tblLaborRegister.getColumnModel().getColumn(4).setMinWidth(50);
        tblLaborRegister.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblLaborRegister.getColumnModel().getColumn(4).setMaxWidth(50);
        tblLaborRegister.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        tblLaborRegister.getColumnModel().getColumn(5).setMinWidth(50);
        tblLaborRegister.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblLaborRegister.getColumnModel().getColumn(5).setMaxWidth(50);
        tblLaborRegister.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

        lblBillableHours.setText(resourceMap.getString("lblBillableHours.text")); // NOI18N
        lblBillableHours.setName("lblBillableHours"); // NOI18N

        lblOtherCharges.setText(resourceMap.getString("lblOtherCharges.text")); // NOI18N
        lblOtherCharges.setName("lblOtherCharges"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblExpenseRegister.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Date", "Description", "Total"
            }
        ));
        tblExpenseRegister.setName("tblExpenseRegister"); // NOI18N
        jScrollPane2.setViewportView(tblExpenseRegister);
        tblExpenseRegister.getColumnModel().getColumn(0).setMinWidth(75);
        tblExpenseRegister.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblExpenseRegister.getColumnModel().getColumn(0).setMaxWidth(100);
        tblExpenseRegister.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblExpenseRegister.columnModel.title0")); // NOI18N
        tblExpenseRegister.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblExpenseRegister.columnModel.title1")); // NOI18N
        tblExpenseRegister.getColumnModel().getColumn(2).setMinWidth(75);
        tblExpenseRegister.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblExpenseRegister.getColumnModel().getColumn(2).setMaxWidth(75);
        tblExpenseRegister.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblExpenseRegister.columnModel.title2")); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getActionMap(InvoiceEditorView.class, this);
        cmdGenerateInvoice.setAction(actionMap.get("generateInvoice")); // NOI18N
        cmdGenerateInvoice.setText(resourceMap.getString("cmdGenerateInvoice.text")); // NOI18N
        cmdGenerateInvoice.setName("cmdGenerateInvoice"); // NOI18N

        cmdAddExpense.setAction(actionMap.get("addExpense")); // NOI18N
        cmdAddExpense.setText(resourceMap.getString("cmdAddExpense.text")); // NOI18N
        cmdAddExpense.setName("cmdAddExpense"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblClient)
                        .addGap(18, 18, 18)
                        .addComponent(cboClient, 0, 509, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblOtherCharges)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdAddExpense))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBillableHours)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(lblAccountBalance)
                        .addGap(46, 46, 46)
                        .addComponent(lblAccountBalanceValue)
                        .addGap(31, 31, 31)
                        .addComponent(cmdGenerateInvoice)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBillableHours)
                        .addComponent(lblAccountBalanceValue)
                        .addComponent(lblAccountBalance))
                    .addComponent(cmdGenerateInvoice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOtherCharges)
                    .addComponent(cmdAddExpense))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClient;
    private javax.swing.JButton cmdAddExpense;
    private javax.swing.JButton cmdGenerateInvoice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAccountBalance;
    private javax.swing.JLabel lblAccountBalanceValue;
    private javax.swing.JLabel lblBillableHours;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblOtherCharges;
    private javax.swing.JTable tblExpenseRegister;
    private javax.swing.JTable tblLaborRegister;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JComboBox getCboClient(){
        return cboClient;
    }
    public javax.swing.JTable getTblLaborRegister(){
        return tblLaborRegister;
    }
    public javax.swing.JTable getTblExpenseRegister(){
        return tblExpenseRegister;
    }

    @Action
    public void generateInvoice(){
        invoiceViewController.generateInvoice();
     }

    public void initializeComponents(){

    }

    public void formatTableLaborRegister() {
         TableColumn tc;
         //CurrencyTableCellRenderer currencyTableCellRenderer  = CurrencyTableCellRenderer.getInstance();
        //Name
         //?
         tc = tblLaborRegister.getColumnModel().getColumn(0);
         tc.setPreferredWidth(25);
         tc.setMinWidth(25);
         tc.setMaxWidth(25);
         //tc.setCellEditor( new DefaultCellEditor(new JCheckBox()));
         //CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
         JCheckBox check = new JCheckBox();
         tc.setCellEditor(new DefaultCellEditor(check));
         //tc.setCellRenderer(new CheckBoxRenderer());
         //Date
         tc = tblLaborRegister.getColumnModel().getColumn(1);
         tc.setPreferredWidth(100);
         tc.setMinWidth(50);
         tc.setMaxWidth(100);
         //Description
         tc = tblLaborRegister.getColumnModel().getColumn(2);
         tc.setPreferredWidth(300);
         tc.setMinWidth(50);
         tc.setMaxWidth(400);
         //Hours
         tc = tblLaborRegister.getColumnModel().getColumn(3);
         tc.setPreferredWidth(75);
         tc.setMinWidth(50);
         tc.setMaxWidth(100);
 //         DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//    renderer.setHorizontalAlignment(SwingConstants.RIGHT);
//    tc.setCellRenderer(renderer);

         //Associate
         tc = tblLaborRegister.getColumnModel().getColumn(4);
         tc.setPreferredWidth(75);
         tc.setMinWidth(50);
         tc.setMaxWidth(100);
         javax.swing.JComboBox cboUser = new javax.swing.JComboBox();
         //cboUser.setModel(new UserInfoComboBoxModel());
//TODO Cleanup cbouser
         cboUser.addItem("test");
         cboUser.addItem("test2");
         tc.setCellEditor(new DefaultCellEditor(cboUser));
         //Rate
         tc = tblLaborRegister.getColumnModel().getColumn(5);
         tc.setPreferredWidth(75);
         tc.setMinWidth(50);
         tc.setMaxWidth(100);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());
         //Total
         tc = tblLaborRegister.getColumnModel().getColumn(6);
         tc.setPreferredWidth(75);
         tc.setMinWidth(50);
         tc.setMaxWidth(100);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());

    }
public void formatTableExpenseRegister() {
         TableColumn tc;
         //CurrencyTableCellRenderer currencyTableCellRenderer  = CurrencyTableCellRenderer.getInstance();
        //Name
         //?
         tc = tblExpenseRegister.getColumnModel().getColumn(0);
         tc.setPreferredWidth(25);
         tc.setMinWidth(25);
         tc.setMaxWidth(25);

         //Date
         tc = tblExpenseRegister.getColumnModel().getColumn(1);
         tc.setPreferredWidth(75);
         tc.setMinWidth(25);
         tc.setMaxWidth(200);
         tc.setCellEditor(new DateTableCellEditor());
         //Description
         tc = tblExpenseRegister.getColumnModel().getColumn(2);
         tc.setPreferredWidth(200);
         tc.setMinWidth(25);
         tc.setMaxWidth(350);
         //Amount
         tc = tblExpenseRegister.getColumnModel().getColumn(3);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(150);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());
}



    public void setAccountBalance(Double newValue_){
        lblAccountBalanceValue.setText(
          currencyFormatter.format(newValue_));
    }
    @Action
    public void addExpense(){
         invoiceViewController.addAdminExpense();
    }
}
