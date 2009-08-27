/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FollowupItemEditorView.java
 *
 * Created on Aug 23, 2009, 11:13:46 AM
 */

package legaltime.view;

import legaltime.controller.FollowupController;

/**
 *
 * @author bmartin
 */
public class FollowupItemEditorView extends javax.swing.JInternalFrame {
    FollowupController followupController;
    /** Creates new form FollowupItemEditorView */
    public FollowupItemEditorView(FollowupController followupController_) {
        followupController = followupController_;
        initComponents();
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
        dtClosedDate = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblClient = new javax.swing.JLabel();
        dtDueDate = new com.toedter.calendar.JDateChooser();
        dtOpenedDate = new com.toedter.calendar.JDateChooser();
        lblDueDate = new javax.swing.JLabel();
        lblClosedDate = new javax.swing.JLabel();
        lblOpenedDate = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(FollowupItemEditorView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        cboClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClient.setName("cboClient"); // NOI18N

        dtClosedDate.setName("dtClosedDate"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setName("txtDescription"); // NOI18N
        jScrollPane2.setViewportView(txtDescription);

        lblClient.setText(resourceMap.getString("lblClient.text")); // NOI18N
        lblClient.setName("lblClient"); // NOI18N

        dtDueDate.setName("dtDueDate"); // NOI18N

        dtOpenedDate.setName("dtOpenedDate"); // NOI18N

        lblDueDate.setText(resourceMap.getString("lblDueDate.text")); // NOI18N
        lblDueDate.setName("lblDueDate"); // NOI18N

        lblClosedDate.setText(resourceMap.getString("lblClosedDate.text")); // NOI18N
        lblClosedDate.setName("lblClosedDate"); // NOI18N

        lblOpenedDate.setText(resourceMap.getString("lblOpenedDate.text")); // NOI18N
        lblOpenedDate.setName("lblOpenedDate"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDueDate)
                            .addComponent(lblClosedDate)
                            .addComponent(lblOpenedDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtClosedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtOpenedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(cboClient, 0, 300, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dtDueDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtOpenedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtClosedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDueDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(lblOpenedDate, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblClosedDate)))
                        .addGap(26, 26, 26))
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClient;
    private com.toedter.calendar.JDateChooser dtClosedDate;
    private com.toedter.calendar.JDateChooser dtDueDate;
    private com.toedter.calendar.JDateChooser dtOpenedDate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblClosedDate;
    private javax.swing.JLabel lblDueDate;
    private javax.swing.JLabel lblOpenedDate;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the cboClient
     */
    public javax.swing.JComboBox getCboClient() {
        return cboClient;
    }

}
