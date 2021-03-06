/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PostPaymentHelper.java
 *
 * Created on Sep 7, 2009, 3:07:59 AM
 */

package legaltime.view;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.JOptionPane;
import legaltime.AppPrefs;
import legaltime.cache.ClientCache;
import legaltime.controller.PaymentLogController;
import legaltime.model.ClientBean;
import legaltime.modelsafe.EasyLog;
import legaltime.view.model.ClientComboBoxModel;
import legaltime.view.renderer.ClientComboBoxRenderer;

/**
 *
 * @author bmartin
 */
public class PostPaymentHelper extends javax.swing.JDialog
        implements java.awt.event.ActionListener {

 private boolean selectionConfirmed;
 ClientComboBoxModel clientComboBoxModel;
 ClientComboBoxRenderer clientComboBoxRenderer;
 EasyLog easyLog;
 AppPrefs appPrefs;

    public PostPaymentHelper(javax.swing.JFrame owner) {
        super(owner, true);
        appPrefs = AppPrefs.getInstance();
        initComponents();
        easyLog = EasyLog.getInstance();
        
        selectionConfirmed = false;
        clientComboBoxModel = new ClientComboBoxModel();
        clientComboBoxRenderer = new ClientComboBoxRenderer();
        clientComboBoxModel.setList(ClientCache.getInstance().getCache());
        cboClient.setModel(clientComboBoxModel);
        cboClient.setRenderer(clientComboBoxRenderer );
        cboClient.setMaximumRowCount(Integer.parseInt(appPrefs.getValue(AppPrefs.CLIENTCBO_DISPLAY_ROWS)));
        cmdAddPayment.addActionListener(this);
        cmdCancel.addActionListener(this);
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
        cmdAddPayment = new javax.swing.JButton();
        dtcEffectiveDate = new com.toedter.calendar.JDateChooser();
        lblEffectiveDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPaymentAmount = new javax.swing.JTextField();
        cmdCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(PostPaymentHelper.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        cboClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClient.setName("cboClient"); // NOI18N

        lblClient.setText(resourceMap.getString("lblClient.text")); // NOI18N
        lblClient.setName("lblClient"); // NOI18N

        cmdAddPayment.setText(resourceMap.getString("cmdAddPayment.text")); // NOI18N
        cmdAddPayment.setName("cmdAddPayment"); // NOI18N

        dtcEffectiveDate.setName("dtcEffectiveDate"); // NOI18N

        lblEffectiveDate.setText(resourceMap.getString("lblEffectiveDate.text")); // NOI18N
        lblEffectiveDate.setName("lblEffectiveDate"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtPaymentAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPaymentAmount.setText(resourceMap.getString("txtPaymentAmount.text")); // NOI18N
        txtPaymentAmount.setName("txtPaymentAmount"); // NOI18N

        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClient)
                            .addComponent(lblEffectiveDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dtcEffectiveDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPaymentAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(cmdAddPayment)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmdCancel)))
                            .addComponent(cboClient, 0, 245, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClient)
                    .addComponent(cboClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEffectiveDate)
                    .addComponent(dtcEffectiveDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAddPayment)
                    .addComponent(cmdCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClient;
    private javax.swing.JButton cmdAddPayment;
    private javax.swing.JButton cmdCancel;
    private com.toedter.calendar.JDateChooser dtcEffectiveDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblEffectiveDate;
    private javax.swing.JTextField txtPaymentAmount;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the cboClient
     */
    public javax.swing.JComboBox getCboClient() {
        return cboClient;
    }

    /**
     * @return the dtcEffectiveDate
     */
    public com.toedter.calendar.JDateChooser getDtcEffectiveDate() {
        return dtcEffectiveDate;
    }

    /**
     * @return the txtPaymentAmount
     */
    public javax.swing.JTextField getTxtPaymentAmount() {
        return txtPaymentAmount;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Post")){
            if(validateForm()){
                selectionConfirmed = true;
                setVisible(false);
            }
        }else if("Cancel".equals(e.getActionCommand())){
            selectionConfirmed = true;
            setVisible(false);
        }
    }

    /**
     * @return the selectionConfirmed
     */
    public boolean isSelectionConfirmed() {
        return selectionConfirmed;
    }

    /**
     * @param selectionConfirmed the selectionConfirmed to set
     */
    public void setSelectionConfirmed(boolean selectionConfirmed_) {
        this.selectionConfirmed = selectionConfirmed_;
    }

    public boolean validateForm(){
        boolean result = true;
        String message="";
          if (getCboClientId() ==0){
              message +=  "Please select a client.  ";
              result = false;
          }
          if (getPaymentAmount() ==0){
              message +=  "Please enter a non-zero payment.  ";
              result = false;
          }
        if (dtcEffectiveDate.getDate() ==null){
              message +=  "Please enter an effective date.  ";
              result = false;
          }
        if(!result){
            JOptionPane.showConfirmDialog(
                    this
                    , message
                    , "Please correct payment posting issues..."
                    , JOptionPane.DEFAULT_OPTION);
        }
        return result;

    }
    public int getCboClientId(){
        int clientId;
        try{
            clientId= ((ClientBean) cboClient.getSelectedItem()).getClientId();
        }catch(NullPointerException  ex){
            clientId=0;
            easyLog.addEntry(EasyLog.INFO, "Client Line indeterminate"
                    , getClass().getName(), ex);
        }
        return clientId;
    }

    public double getPaymentAmount(){
      double result = 0;
      try{
        result = Double.parseDouble(txtPaymentAmount.getText());
      }catch(NumberFormatException e){
          result =0;
      }
      return result;
    }
    public void showDialog(){
        
        selectionConfirmed = false;
        dtcEffectiveDate.setDate(new java.util.Date());
        txtPaymentAmount.setText("0.00");
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void showDialog(ClientBean clientBean_){
        clientComboBoxModel.setSelectedItem(clientBean_);
        showDialog();

    }



}
