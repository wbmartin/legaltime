/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientAccounting.java
 *
 * Created on Jul 19, 2009, 8:37:20 AM
 */

package legaltime.view;


import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import legaltime.LegalTimeApp;
import legaltime.controller.ClientAccountRegisterController;
import legaltime.view.renderer.CurrencyTableCellRenderer;
import legaltime.view.renderer.DateTableCellRenderer;
import org.jdesktop.application.Action;

/**
 *
 * @author bmartin
 */
public class ClientAccountRegisterView extends javax.swing.JInternalFrame {
    LegalTimeApp mainController;
    ClientAccountRegisterController clientAccountRegisterController;
    javax.swing.JMenuItem reverseTranMenuItem;
    private final ClientAccountRegisterController clientAccountingController;
    private javax.swing.JPopupMenu tableMenu;
    /** Creates new form ClientAccounting */
    public ClientAccountRegisterView(ClientAccountRegisterController clientAccountRegisterController_) {
        clientAccountingController = clientAccountRegisterController_;
        initComponents();
        tableMenu = new javax.swing.JPopupMenu("Right Click");
        reverseTranMenuItem = new javax.swing.JMenuItem("Reverse Transaction");
        reverseTranMenuItem.setActionCommand("REVERSE_SELECTED_TRAN");
        tableMenu.add(reverseTranMenuItem);
        reverseTranMenuItem.addActionListener(clientAccountingController);
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccountRegister = new javax.swing.JTable();
        cboClient = new javax.swing.JComboBox();
        lblClient = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(ClientAccountRegisterView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblAccountRegister.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"7/15/09", "Invoice 123", "200", null, "200"},
                {"7/20/09", "Pmt Recd", null, "200", "0"},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Description", "Increase", "Decrease", "Balance"
            }
        ));
        tblAccountRegister.setName("tblAccountRegister"); // NOI18N
        jScrollPane1.setViewportView(tblAccountRegister);
        tblAccountRegister.getColumnModel().getColumn(0).setMinWidth(75);
        tblAccountRegister.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblAccountRegister.getColumnModel().getColumn(0).setMaxWidth(75);
        tblAccountRegister.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblAccountRegister.columnModel.title4")); // NOI18N
        tblAccountRegister.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblAccountRegister.columnModel.title0")); // NOI18N
        tblAccountRegister.getColumnModel().getColumn(2).setMinWidth(75);
        tblAccountRegister.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblAccountRegister.getColumnModel().getColumn(2).setMaxWidth(75);
        tblAccountRegister.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblAccountRegister.columnModel.title1")); // NOI18N
        tblAccountRegister.getColumnModel().getColumn(3).setMinWidth(75);
        tblAccountRegister.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblAccountRegister.getColumnModel().getColumn(3).setMaxWidth(75);
        tblAccountRegister.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblAccountRegister.columnModel.title2")); // NOI18N
        tblAccountRegister.getColumnModel().getColumn(4).setMinWidth(75);
        tblAccountRegister.getColumnModel().getColumn(4).setPreferredWidth(75);
        tblAccountRegister.getColumnModel().getColumn(4).setMaxWidth(75);
        tblAccountRegister.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblAccountRegister.columnModel.title3")); // NOI18N

        cboClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClient.setName("cboClient"); // NOI18N

        lblClient.setText(resourceMap.getString("lblClient.text")); // NOI18N
        lblClient.setName("lblClient"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getActionMap(ClientAccountRegisterView.class, this);
        jButton1.setAction(actionMap.get("showClientEditorView")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

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
                    .addComponent(jButton1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClient;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClient;
    private javax.swing.JTable tblAccountRegister;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTable getTblAccountRegister(){
        return tblAccountRegister;
    }
  
    public javax.swing.JComboBox getCboClient(){
        return cboClient;
    }
     /**
     * @return the tableMenu
     */
    public javax.swing.JPopupMenu getTableMenu() {
        return tableMenu;
    }

    public void formatTblAccountRegister(){
         TableColumn tc;
         //Date
         tc = tblAccountRegister.getColumnModel().getColumn(0);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(75);
         tc.setCellRenderer(DateTableCellRenderer.getInstance());
         //Description
         tc = tblAccountRegister.getColumnModel().getColumn(1);
         tc.setPreferredWidth(200);
         tc.setMinWidth(25);
         tc.setMaxWidth(400);
         //Increase
         tc = tblAccountRegister.getColumnModel().getColumn(2);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(75);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());
         //Decrease
         tc = tblAccountRegister.getColumnModel().getColumn(3);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(75);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());

         //Total
         tc = tblAccountRegister.getColumnModel().getColumn(4);
         tc.setPreferredWidth(50);
         tc.setMinWidth(25);
         tc.setMaxWidth(75);
         tc.setCellRenderer(CurrencyTableCellRenderer.getInstance());


    }
     public void scrollRowTblPaymentLog(int row) {
       SwingUtils.scrollTableToVisible(tblAccountRegister, row, 1);
    }

    public void showPopupMsg(String msg_) {
        JOptionPane.showMessageDialog(this, msg_);
    }
    @Action
    public void showClientEditorView(){
        clientAccountingController.showClientEditorView();
    }

   

    


}
