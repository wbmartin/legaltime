/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientManager.java
 *
 * Created on Jul 16, 2009, 7:40:11 PM
 */

package legaltime.view;

//import java.awt.Dimension;


import javax.swing.table.TableColumn;

import legaltime.controller.ClientEditorController;
import legaltime.view.renderer.CurrencyTableCellRenderer;
import org.jdesktop.application.Action;


/**
 *
 * @author bmartin
 */
public class ClientEditorView extends javax.swing.JInternalFrame  {
    ClientEditorController clientEditorController;
    /** Creates new form ClientManager */

    public ClientEditorView(ClientEditorController clientEditorController_) {
        initComponents();
        clientEditorController = clientEditorController_;
        cboBillingPlan.addActionListener(clientEditorController);
       // buildClientManagerTableColumnModel();
    }

     

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        scrlChooser = new javax.swing.JScrollPane();
        tblClientSelect = new javax.swing.JTable();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        lblCity = new javax.swing.JLabel();
        txtState = new javax.swing.JTextField();
        lblState = new javax.swing.JLabel();
        txtZip = new javax.swing.JTextField();
        lblZip = new javax.swing.JLabel();
        lblWorkPhone = new javax.swing.JLabel();
        txtWorkPhone = new javax.swing.JTextField();
        lblHomePhone = new javax.swing.JLabel();
        txtHomePhone = new javax.swing.JTextField();
        txtCellPhone = new javax.swing.JTextField();
        lblCellPhone = new javax.swing.JLabel();
        txtFaxNum = new javax.swing.JTextField();
        lblFaxNum = new javax.swing.JLabel();
        lblBillingInformation = new javax.swing.JLabel();
        cboBillingPlan = new javax.swing.JComboBox();
        lblBillingPlan = new javax.swing.JLabel();
        scrlBillRates = new javax.swing.JScrollPane();
        tblBillRates = new javax.swing.JTable();
        scrlFollowUp = new javax.swing.JScrollPane();
        tblClientFollowup = new javax.swing.JTable();
        lblFollowUpItems = new javax.swing.JLabel();
        lblFaxNum1 = new javax.swing.JLabel();
        dtClientSince = new com.toedter.calendar.JDateChooser();
        cmdAddClient = new javax.swing.JButton();
        cmdDeleteClient = new javax.swing.JButton();
        cmdClientAccount = new javax.swing.JButton();
        cmdViewInvoiceable = new javax.swing.JButton();
        scrlNotes = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextPane();
        lblNotes = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        cmdClearChanges = new javax.swing.JButton();
        lblMonthlyRate = new javax.swing.JLabel();
        txtMonthlyRate = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(ClientEditorView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        scrlChooser.setName("scrlChooser"); // NOI18N

        tblClientSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "City"
            }
        ));
        tblClientSelect.setName("tblClientSelect"); // NOI18N
        scrlChooser.setViewportView(tblClientSelect);
        tblClientSelect.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblClientSelect.columnModel.title0")); // NOI18N
        tblClientSelect.getColumnModel().getColumn(1).setMinWidth(25);
        tblClientSelect.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblClientSelect.getColumnModel().getColumn(1).setMaxWidth(300);
        tblClientSelect.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblClientSelect.columnModel.title1")); // NOI18N

        lblFirstName.setText(resourceMap.getString("lblFirstName.text")); // NOI18N
        lblFirstName.setName("lblFirstName"); // NOI18N

        txtFirstName.setText(resourceMap.getString("txtFirstName.text")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N

        txtLastName.setName("txtLastName"); // NOI18N
        txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLastNameFocusGained(evt);
            }
        });

        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N

        txtAddress.setName("txtAddress"); // NOI18N

        lblAddress.setText(resourceMap.getString("lblAddress.text")); // NOI18N
        lblAddress.setName("lblAddress"); // NOI18N

        txtCity.setName("txtCity"); // NOI18N

        lblCity.setText(resourceMap.getString("lblCity.text")); // NOI18N
        lblCity.setName("lblCity"); // NOI18N

        txtState.setName("txtState"); // NOI18N

        lblState.setText(resourceMap.getString("lblState.text")); // NOI18N
        lblState.setName("lblState"); // NOI18N

        txtZip.setName("txtZip"); // NOI18N

        lblZip.setText(resourceMap.getString("lblZip.text")); // NOI18N
        lblZip.setName("lblZip"); // NOI18N

        lblWorkPhone.setText(resourceMap.getString("lblWorkPhone.text")); // NOI18N
        lblWorkPhone.setName("lblWorkPhone"); // NOI18N

        txtWorkPhone.setName("txtWorkPhone"); // NOI18N

        lblHomePhone.setText(resourceMap.getString("lblHomePhone.text")); // NOI18N
        lblHomePhone.setName("lblHomePhone"); // NOI18N

        txtHomePhone.setText(resourceMap.getString("txtHomePhone.text")); // NOI18N
        txtHomePhone.setName("txtHomePhone"); // NOI18N

        txtCellPhone.setName("txtCellPhone"); // NOI18N

        lblCellPhone.setText(resourceMap.getString("lblCellPhone.text")); // NOI18N
        lblCellPhone.setName("lblCellPhone"); // NOI18N

        txtFaxNum.setName("txtFaxNum"); // NOI18N

        lblFaxNum.setText(resourceMap.getString("lblFaxNum.text")); // NOI18N
        lblFaxNum.setName("lblFaxNum"); // NOI18N

        lblBillingInformation.setText(resourceMap.getString("lblBillingInformation.text")); // NOI18N
        lblBillingInformation.setName("lblBillingInformation"); // NOI18N

        cboBillingPlan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MONTHLY", "HOURLY" }));
        cboBillingPlan.setActionCommand(resourceMap.getString("cboBillPlan.actionCommand")); // NOI18N
        cboBillingPlan.setName("cboBillPlan"); // NOI18N

        lblBillingPlan.setText(resourceMap.getString("lblBillingPlan.text")); // NOI18N
        lblBillingPlan.setName("lblBillingPlan"); // NOI18N

        scrlBillRates.setEnabled(false);
        scrlBillRates.setName("scrlBillRates"); // NOI18N

        tblBillRates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Attorney", "$300"},
                {"Assistant", "$75"},
                {"Clerk", "$200"},
                {null, null}
            },
            new String [] {
                "Bill Level", "Bill Rate"
            }
        ));
        tblBillRates.setName("tblBillRates"); // NOI18N
        scrlBillRates.setViewportView(tblBillRates);
        tblBillRates.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblBillRates.columnModel.title0")); // NOI18N
        tblBillRates.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblBillRates.columnModel.title1")); // NOI18N

        scrlFollowUp.setName("scrlFollowUp"); // NOI18N

        tblClientFollowup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Due Date", "Description"
            }
        ));
        tblClientFollowup.setEnabled(false);
        tblClientFollowup.setName("tblClientFollowup"); // NOI18N
        scrlFollowUp.setViewportView(tblClientFollowup);
        tblClientFollowup.getColumnModel().getColumn(0).setMinWidth(75);
        tblClientFollowup.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblClientFollowup.getColumnModel().getColumn(0).setMaxWidth(75);
        tblClientFollowup.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblClientFollowup.columnModel.title0")); // NOI18N
        tblClientFollowup.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblClientFollowup.columnModel.title1")); // NOI18N

        lblFollowUpItems.setText(resourceMap.getString("lblFollowUpItems.text")); // NOI18N
        lblFollowUpItems.setName("lblFollowUpItems"); // NOI18N

        lblFaxNum1.setText(resourceMap.getString("lblFaxNum1.text")); // NOI18N
        lblFaxNum1.setName("lblFaxNum1"); // NOI18N

        dtClientSince.setName("dtClientSince"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getActionMap(ClientEditorView.class, this);
        cmdAddClient.setAction(actionMap.get("addNewClient")); // NOI18N
        cmdAddClient.setText(resourceMap.getString("cmdAddClient.text")); // NOI18N
        cmdAddClient.setName("cmdAddClient"); // NOI18N

        cmdDeleteClient.setAction(actionMap.get("deactivateClient")); // NOI18N
        cmdDeleteClient.setText(resourceMap.getString("cmdDeleteClient.text")); // NOI18N
        cmdDeleteClient.setName("cmdDeleteClient"); // NOI18N

        cmdClientAccount.setText(resourceMap.getString("cmdClientAccount.text")); // NOI18N
        cmdClientAccount.setName("cmdClientAccount"); // NOI18N

        cmdViewInvoiceable.setText(resourceMap.getString("cmdViewInvoiceable.text")); // NOI18N
        cmdViewInvoiceable.setName("cmdViewInvoiceable"); // NOI18N

        scrlNotes.setName("scrlNotes"); // NOI18N

        txtNote.setName("txtNote"); // NOI18N
        scrlNotes.setViewportView(txtNote);

        lblNotes.setText(resourceMap.getString("lblNotes.text")); // NOI18N
        lblNotes.setName("lblNotes"); // NOI18N

        lblEmail.setText(resourceMap.getString("lblEmail.text")); // NOI18N
        lblEmail.setName("lblEmail"); // NOI18N

        txtEmail.setName("txtEmail"); // NOI18N

        cmdClearChanges.setAction(actionMap.get("clearChanges")); // NOI18N
        cmdClearChanges.setText(resourceMap.getString("cmdClearChanges.text")); // NOI18N
        cmdClearChanges.setName("cmdClearChanges"); // NOI18N

        lblMonthlyRate.setText(resourceMap.getString("lblMonthlyRate.text")); // NOI18N
        lblMonthlyRate.setName("lblMonthlyRate"); // NOI18N

        txtMonthlyRate.setText(resourceMap.getString("txtMonthlyRate.text")); // NOI18N
        txtMonthlyRate.setName("txtMonthlyRate"); // NOI18N

        jButton1.setAction(actionMap.get("editFollowupItem")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFollowUpItems)
                        .addContainerGap(647, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdClientAccount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdViewInvoiceable)
                        .addContainerGap(491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cmdAddClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDeleteClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClearChanges)
                        .addGap(82, 82, 82)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrlChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblBillingInformation)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(scrlBillRates, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(lblBillingPlan)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cboBillingPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblMonthlyRate)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtMonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblFaxNum, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblCellPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblFirstName, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblLastName, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblFaxNum1, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblCity, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblState, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblHomePhone, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblNotes, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblWorkPhone, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtFaxNum, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(dtClientSince, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtCity, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblZip)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtZip, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                                                    .addComponent(txtWorkPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtHomePhone, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(scrlNotes, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                                    .addComponent(txtCellPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))
                                        .addGap(40, 40, 40))))
                            .addComponent(scrlFollowUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFirstName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLastName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblState)
                            .addComponent(lblZip)
                            .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtWorkPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblWorkPhone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHomePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHomePhone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCellPhone)
                            .addComponent(txtCellPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFaxNum)
                            .addComponent(txtFaxNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFaxNum1)
                            .addComponent(dtClientSince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNotes)
                                .addGap(24, 24, 24)
                                .addComponent(lblBillingInformation))
                            .addComponent(scrlNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboBillingPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBillingPlan)
                            .addComponent(txtMonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMonthlyRate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrlBillRates, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrlChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdAddClient)
                            .addComponent(cmdDeleteClient)
                            .addComponent(cmdClearChanges))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFollowUpItems))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlFollowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdViewInvoiceable)
                    .addComponent(cmdClientAccount))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusGained
        if(txtLastName.getText().equals("_Customer")){
            txtLastName.selectAll();
        }
    }//GEN-LAST:event_txtLastNameFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cboBillingPlan;
    private javax.swing.JButton cmdAddClient;
    private javax.swing.JButton cmdClearChanges;
    private javax.swing.JButton cmdClientAccount;
    private javax.swing.JButton cmdDeleteClient;
    private javax.swing.JButton cmdViewInvoiceable;
    private com.toedter.calendar.JDateChooser dtClientSince;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBillingInformation;
    private javax.swing.JLabel lblBillingPlan;
    private javax.swing.JLabel lblCellPhone;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFaxNum;
    private javax.swing.JLabel lblFaxNum1;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblFollowUpItems;
    private javax.swing.JLabel lblHomePhone;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblMonthlyRate;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblState;
    private javax.swing.JLabel lblWorkPhone;
    private javax.swing.JLabel lblZip;
    private javax.swing.JScrollPane scrlBillRates;
    private javax.swing.JScrollPane scrlChooser;
    private javax.swing.JScrollPane scrlFollowUp;
    private javax.swing.JScrollPane scrlNotes;
    private javax.swing.JTable tblBillRates;
    private javax.swing.JTable tblClientFollowup;
    private javax.swing.JTable tblClientSelect;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCellPhone;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFaxNum;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtHomePhone;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMonthlyRate;
    private javax.swing.JTextPane txtNote;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtWorkPhone;
    private javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTable getTblClientSelect(){
        return tblClientSelect;
    }
    public javax.swing.JTable getTblClientFollowup(){
        return tblClientFollowup;
    }
    public javax.swing.JTextField getTxtAddress(){
        return txtAddress;

    }
    public javax.swing.JTextField getTxtCellPhone() {
        return txtCellPhone;
    }

    public javax.swing.JTextField getTxtCity() {
        return txtCity;
    }
    public com.toedter.calendar.JDateChooser getDtClientSince() {
        return dtClientSince;
    }

    public javax.swing.JTextField getTxtEmail() {
        return txtEmail;
    }

    public javax.swing.JTextField getTxtFaxNum() {
        return txtFaxNum;
    }

    public javax.swing.JTextField getTxtFirstName() {
       return txtFirstName;
    }

    public javax.swing.JTextField getTxtHomePhone() {
        return txtHomePhone;
    }

    public javax.swing.JTextField getTxtLastName() {
        return txtLastName;
    }

    public javax.swing.JTextPane getTxtNote() {
        return txtNote;
    }

    public javax.swing.JTextField getTxtWorkPhone() {
        return txtWorkPhone;
    }

    public javax.swing.JTextField getTxtZip() {
        return txtZip;
    }

    public javax.swing.JTextField getTxtState() {
        return txtState;
    }
    public javax.swing.JComboBox getCboBillingPlan(){
        return cboBillingPlan;
    }
    public javax.swing.JTable getTblBillRates(){
        return tblBillRates;
    }
    public javax.swing.JTextField getTxtMonthlyRate() {
        return txtMonthlyRate;
    }



    public void buildClientManagerTableDisplay() {


        TableColumn tc;
        //Name
         tc = tblClientSelect.getColumnModel().getColumn(0);
         tc.setPreferredWidth(300);
         tc.setMinWidth(100);
         tc.setMaxWidth(400);
         //City
         tc = tblClientSelect.getColumnModel().getColumn(1);
         tc.setPreferredWidth(100);
         tc.setMinWidth(50);
         tc.setMaxWidth(200);

         //BillLevel
         tc = tblBillRates.getColumnModel().getColumn(0);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(100);
         //BillRate
         tc = tblBillRates.getColumnModel().getColumn(1);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(100);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());
    }
    public void buildClientFollowupTableDisplay() {


        TableColumn tc;
        //Name
         tc = tblClientFollowup.getColumnModel().getColumn(0);
         tc.setPreferredWidth(100);
         tc.setMinWidth(25);
         tc.setMaxWidth(150);
         tc = tblClientFollowup.getColumnModel().getColumn(1);
         tc.setPreferredWidth(300);
         tc.setMinWidth(100);
         tc.setMaxWidth(600);
    }
 

     @Action
     public void clearChangesToEditedBean(){
        clientEditorController.clearChangesToEditedBean();
     }

     @Action
     public void addNewClient(){
       clientEditorController.addNewClient();
     }
     
     @Action
     public void deactivateClient(){
        clientEditorController.deactivateClient();
     }
     @Action
     public void editFollowupItem(){
        clientEditorController.editSelectedFollowUpItem();
     }




     
}

