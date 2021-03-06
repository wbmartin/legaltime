/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExpenseManager.java
 *
 * Created on Jul 25, 2009, 10:09:44 AM
 */

package legaltime.view.recycle;

import legaltime.LegalTimeApp;

/**
 *
 * @author bmartin
 */
public class ExpenseManager extends javax.swing.JInternalFrame {
LegalTimeApp mainController;
    /** Creates new form ExpenseManager */
    public ExpenseManager() {
        initComponents();
    }
    public void setMainController(LegalTimeApp mainController_){
        mainController = mainController_;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboClientId = new javax.swing.JComboBox();
        txtDescription = new javax.swing.JTextField();
        lblClient = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        dtExpenseDt = new com.toedter.calendar.JDateChooser();
        lblExpenseDate = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExpenses = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(ExpenseManager.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        cboClientId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClientId.setName("cboClientId"); // NOI18N

        txtDescription.setText(resourceMap.getString("txtDescription.text")); // NOI18N
        txtDescription.setName("txtDescription"); // NOI18N

        lblClient.setText(resourceMap.getString("lblClient.text")); // NOI18N
        lblClient.setName("lblClient"); // NOI18N

        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        dtExpenseDt.setName("dtExpenseDt"); // NOI18N

        lblExpenseDate.setText(resourceMap.getString("lblExpenseDate.text")); // NOI18N
        lblExpenseDate.setName("lblExpenseDate"); // NOI18N

        lblAmount.setText(resourceMap.getString("lblAmount.text")); // NOI18N
        lblAmount.setName("lblAmount"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblExpenses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Client", "Description", "Amount"
            }
        ));
        tblExpenses.setName("tblExpenses"); // NOI18N
        jScrollPane1.setViewportView(tblExpenses);
        tblExpenses.getColumnModel().getColumn(0).setMinWidth(25);
        tblExpenses.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblExpenses.getColumnModel().getColumn(0).setMaxWidth(75);
        tblExpenses.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblExpenses.columnModel.title2")); // NOI18N
        tblExpenses.getColumnModel().getColumn(1).setMinWidth(50);
        tblExpenses.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblExpenses.getColumnModel().getColumn(1).setMaxWidth(300);
        tblExpenses.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblExpenses.columnModel.title0")); // NOI18N
        tblExpenses.getColumnModel().getColumn(2).setMinWidth(100);
        tblExpenses.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblExpenses.getColumnModel().getColumn(2).setMaxWidth(400);
        tblExpenses.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblExpenses.columnModel.title1")); // NOI18N
        tblExpenses.getColumnModel().getColumn(3).setMinWidth(25);
        tblExpenses.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblExpenses.getColumnModel().getColumn(3).setMaxWidth(150);
        tblExpenses.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblExpenses.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClient)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescription)
                            .addComponent(lblExpenseDate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboClientId, 0, 463, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dtExpenseDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(264, 264, 264)
                                .addComponent(lblAmount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                            .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboClientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtExpenseDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAmount))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescription)
                        .addGap(15, 15, 15)
                        .addComponent(lblExpenseDate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClientId;
    private com.toedter.calendar.JDateChooser dtExpenseDt;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblExpenseDate;
    private javax.swing.JTable tblExpenses;
    private javax.swing.JTextField txtDescription;
    // End of variables declaration//GEN-END:variables

}
