/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TimeRecorder.java
 *
 * Created on Jul 16, 2009, 6:11:13 AM
 */

package legaltime.view;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.*;
import legaltime.cache.ClientCache;
import legaltime.cache.UserInfoCache;
import legaltime.model.ClientBean;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.model.UserInfoBean;
import legaltime.model.UserInfoBean;
import legaltime.model.exception.DAOException;
import org.jdesktop.application.Action;

/**
 *
 * @author bmartin
 */
public class TimeRecorder extends javax.swing.JFrame {
    LegalTimeApp mainController;
    LaborRegisterBean laborRegisterBean;
    LaborRegisterManager laborRegisterManager;
    
    ClientComboBoxModel clientComboBoxModel;
    ClientCache clientCache;

    UserInfoCache userInfoCache;
    UserInfoComboBoxModel userInfoComboBoxModel;

    /** Creates new form TimeRecorder */
    public TimeRecorder() {
        initComponents();
        laborRegisterManager = LaborRegisterManager.getInstance();

        clientComboBoxModel = new ClientComboBoxModel();
        clientCache = ClientCache.getInstance();
        clientComboBoxModel.setList(clientCache.getCache());
        cboClient.setModel(clientComboBoxModel);
        cboClient.setRenderer(new ClientComboBoxRenderer());

        userInfoComboBoxModel= new UserInfoComboBoxModel();
        userInfoCache = UserInfoCache.getInstance();
        userInfoComboBoxModel.setList(userInfoCache.getCache());
        cboUserId.setModel(userInfoComboBoxModel);
        cboUserId.setRenderer(new UserInfoComboBoxRenderer());




    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDate = new javax.swing.JLabel();
        scrlDescrption = new javax.swing.JScrollPane();
        txtWorkDescription = new javax.swing.JTextPane();
        cboClient = new javax.swing.JComboBox();
        lblClient = new javax.swing.JLabel();
        lblBillTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdRecord = new javax.swing.JButton();
        dtRecordDate = new com.toedter.calendar.JDateChooser();
        cmdFullScreen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHour = new javax.swing.JList();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstMin = new javax.swing.JList();
        lblPerson = new javax.swing.JLabel();
        cboUserId = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Time Recorder"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(TimeRecorder.class);
        lblDate.setText(resourceMap.getString("lblDate.text")); // NOI18N
        lblDate.setName("lblDate"); // NOI18N

        scrlDescrption.setName("scrlDescrption"); // NOI18N

        txtWorkDescription.setName("txtWorkDescription"); // NOI18N
        scrlDescrption.setViewportView(txtWorkDescription);

        cboClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClient.setName("cboClient"); // NOI18N

        lblClient.setText(resourceMap.getString("lblClient.text")); // NOI18N
        lblClient.setName("lblClient"); // NOI18N

        lblBillTime.setText(resourceMap.getString("lblBillTime.text")); // NOI18N
        lblBillTime.setName("lblBillTime"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getActionMap(TimeRecorder.class, this);
        cmdRecord.setAction(actionMap.get("saveBean")); // NOI18N
        cmdRecord.setText(resourceMap.getString("cmdRecord.text")); // NOI18N
        cmdRecord.setName("cmdRecord"); // NOI18N

        dtRecordDate.setName("dtRecordDate"); // NOI18N

        cmdFullScreen.setText(resourceMap.getString("cmdFullScreen.text")); // NOI18N
        cmdFullScreen.setName("cmdFullScreen"); // NOI18N
        cmdFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFullScreenActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lstHour.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstHour.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstHour.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        lstHour.setName("lstHour"); // NOI18N
        lstHour.setVerifyInputWhenFocusTarget(false);
        lstHour.setVisibleRowCount(1);
        jScrollPane1.setViewportView(lstHour);

        jSpinner1.setName("jSpinner1"); // NOI18N

        jSpinner2.setName("jSpinner2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        lstMin.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "0", "6", "12", "18", "24", "30", "36", "42", "48", "54" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstMin.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstMin.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        lstMin.setName("lstMin"); // NOI18N
        lstMin.setVerifyInputWhenFocusTarget(false);
        lstMin.setVisibleRowCount(1);
        jScrollPane2.setViewportView(lstMin);

        lblPerson.setText(resourceMap.getString("lblPerson.text")); // NOI18N
        lblPerson.setName("lblPerson"); // NOI18N

        cboUserId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboUserId.setName("cboUserId"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboClient, 0, 349, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtRecordDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBillTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(64, 64, 64)
                        .addComponent(cmdFullScreen))
                    .addComponent(scrlDescrption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblPerson)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboUserId, 0, 164, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClient)
                        .addComponent(lblDate))
                    .addComponent(dtRecordDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerson)
                    .addComponent(cboUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(scrlDescrption, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdRecord)
                    .addComponent(lblBillTime)
                    .addComponent(jLabel2)
                    .addComponent(cmdFullScreen))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFullScreenActionPerformed
        this.setVisible(false);
        mainController.showMainWindow();
    }//GEN-LAST:event_cmdFullScreenActionPerformed

    public void setMainController(LegalTimeApp mainController_){
        mainController = mainController_;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeRecorder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClient;
    private javax.swing.JComboBox cboUserId;
    private javax.swing.JButton cmdFullScreen;
    private javax.swing.JButton cmdRecord;
    private com.toedter.calendar.JDateChooser dtRecordDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JLabel lblBillTime;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblPerson;
    private javax.swing.JList lstHour;
    private javax.swing.JList lstMin;
    private javax.swing.JScrollPane scrlDescrption;
    private javax.swing.JTextPane txtWorkDescription;
    // End of variables declaration//GEN-END:variables

    public void synchDisplayToBean(){
        
        laborRegisterBean = laborRegisterManager.createLaborRegisterBean();
       
        laborRegisterBean.setClientId(
                ((ClientBean)cboClient.getSelectedItem()).getClientId()
        );
        
        laborRegisterBean.setDate(dtRecordDate.getDate());
        laborRegisterBean.setDescription(txtWorkDescription.getText());
        int min =0;
        min = Integer.parseInt(lstHour.getSelectedValue().toString());
        min *= 60;
        min += Integer.parseInt(lstMin.getSelectedValue().toString());
        laborRegisterBean.setMinutes(min);
        
        laborRegisterBean.setUserKey(
                ((UserInfoBean)cboUserId.getSelectedItem()).getUserKey());
        

    }

    @Action
    public void saveBean(){
        synchDisplayToBean();
        try {
            laborRegisterManager.save(laborRegisterBean);
        } catch (DAOException ex) {
            Logger.getLogger(TimeRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearForm();
    }

    public void clearForm(){
        lstHour.setSelectedIndex(0);
        lstMin.setSelectedIndex(0);
        txtWorkDescription.setText("");

    }

  


}