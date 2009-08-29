/*
 * LegalTimeView.java
 */

package legaltime.view;

import java.awt.Color;
import legaltime.*;
import java.awt.Dimension;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import legaltime.DBManager.VersionManager;
import legaltime.controller.ClientAccountRegisterController;
import legaltime.controller.ClientEditorController;
import legaltime.controller.FollowupController;
import legaltime.controller.InvoiceController;
import legaltime.controller.MonthlyCycleManager;
import legaltime.controller.PaymentLogController;
import legaltime.modelsafe.EasyLog;

import legaltime.modelsafe.PersistanceManager;
import legaltime.reports.ClientAddressLabelReport;


/**
 * The application's main frame.
 */
public class LegalTimeView extends FrameView {
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private TimeRecorder timeRecorder;
    private ClientEditorView clientManager;
    private FollowupView followupManager;
    private ClientAccountRegisterView clientAccounting;
    private InvoiceEditorView invoiceManager;
    private ExpenseManager expenseManager;
    private TimeEditor timeEditor;
    private PersistanceManager persistanceManager;
    private PreferencesEditorView preferencesManager;
    private DBAdminView dBAdminView;
    private LogView logView;
    private final EasyLog easyLog;
    private LegalTimeApp legalTimeApp;
    private VersionManager vm ;


    public LegalTimeView(SingleFrameApplication app) {
        super(app);
            legalTimeApp = (LegalTimeApp) app;
        
            AppPrefs appPrefs = AppPrefs.getInstance();
            persistanceManager = PersistanceManager.getInstance();
            easyLog =EasyLog.getInstance();
            vm = new VersionManager();
            initComponents();
            LegalTimeApp.getApplication().getMainFrame().setPreferredSize(new Dimension(800, 600));
             
            // status bar initialization - message timeout, idle icon and busy animation, etc
            ResourceMap resourceMap = getResourceMap();
            int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
            messageTimer = new Timer(messageTimeout, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    statusMessageLabel.setText("");
                }
            });
            messageTimer.setRepeats(false);
            int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
            for (int i = 0; i < busyIcons.length; i++) {
                busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
            }
            busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                    //statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
                }
            });
            idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
            //statusAnimationLabel.setIcon(idleIcon);
            progressBar.setVisible(false);
            // connecting action tasks to status bar via TaskMonitor
            TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
            taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    String propertyName = evt.getPropertyName();
                    if ("started".equals(propertyName)) {
                        if (!busyIconTimer.isRunning()) {
                            //statusAnimationLabel.setIcon(busyIcons[0]);
                            busyIconIndex = 0;
                            busyIconTimer.start();
                        }
                        progressBar.setVisible(true);
                        progressBar.setIndeterminate(true);
                    } else if ("done".equals(propertyName)) {
                        busyIconTimer.stop();
                        //statusAnimationLabel.setIcon(idleIcon);
                        progressBar.setVisible(false);
                        progressBar.setValue(0);
                    } else if ("message".equals(propertyName)) {
                        String text = (String) (evt.getNewValue());
                        statusMessageLabel.setText((text == null) ? "" : text);
                        messageTimer.restart();
                    } else if ("progress".equals(propertyName)) {
                        int value = (Integer) (evt.getNewValue());
                        progressBar.setVisible(true);
                        progressBar.setIndeterminate(false);
                        progressBar.setValue(value);
                    }
                }
            });
            //added default close behavior 2009-07-18
            this.getFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            if (appPrefs.getValue(AppPrefs.JDBC_PASSWD).equals(AppPrefs.NOT_SET)) {
                showPreferencesManager();
            }

            
        
            
            lblVersion.setText( "Version: " + LegalTimeApp.APP_VERSION
                    +"|"+ VersionManager.INSTALLED_VERSION);
    }

    public void manageUpdates(){
        
            String dbUpgradeResult = vm.installAllDbPatches();
            if(dbUpgradeResult.equals(VersionManager.NEW_VERSION_FAILED)){
                setLastActionText("ERROR: DB upgrade failed.  Please review " +
                        "Logs and email developer the error detail.");
                JOptionPane.showInternalConfirmDialog(getDesktop(), 
                        "The application Failed a database upgrade and may " +
                        "not be stable.  Contact the developer."
                        ,"Warning",JOptionPane.WARNING_MESSAGE);

            }else if (dbUpgradeResult.equals(VersionManager.NEW_VERSION_INSTALLED)){

                setLastActionText("New Database version successfully installed." +
                        "  Application Restart Required");
                JOptionPane.showInternalMessageDialog(getDesktop(),
                        "Upgrades have completed.  The application will close." +
                        "  Please re-open the application."
                        ,"Notice",JOptionPane.WARNING_MESSAGE);
                legalTimeApp.exit();
            }

            try {
                if (VersionManager.INSTALLED_VERSION.equals("NOT SET")){
                    setLastActionText("Critical Error Loading DB Version " +
                            "- Likely database connectivity issue.  " +
                            "Check Preferences Tab.");
                }
                else if(!VersionManager.INSTALLED_VERSION.equals(VersionManager.REQUIRED_DB_VERSION) ){
                    JOptionPane.showInternalMessageDialog(getDesktop(),
                        "There is a Database Version conflict.  Expecting: "
                        + VersionManager.REQUIRED_DB_VERSION+" but found: "
                        + VersionManager.INSTALLED_VERSION
                        +".  The application may not be stable.  Please restart " +
                        "the application and contact the developer"
                        ,"Warning",JOptionPane.WARNING_MESSAGE);


                }
            }catch(java.lang.ArrayIndexOutOfBoundsException e){
                 JOptionPane.showInternalMessageDialog(getDesktop(),
                        "There application couldn't determine the Database Version.  " +
                        "The application is not stable.  " +
                        "Please contact the developer"
                        ,"Warning",JOptionPane.WARNING_MESSAGE);
                 easyLog.addEntry(EasyLog.INFO, "Error Determining Database Version"
                    , getClass().getName(), e);
            }
                      

    }

    public void loadCache(){
        persistanceManager.loadCache();
    }

    @Action
    public void showAboutBox() {

        if (aboutBox == null) {
            JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            aboutBox = new LegalTimeAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        LegalTimeApp.getApplication().show(aboutBox);
        
    }

   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        desktop = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        preferencesMenuItem = new javax.swing.JMenuItem();
        clientMenu = new javax.swing.JMenu();
        clientManagerMenuItem = new javax.swing.JMenuItem();
        clientAccountingMenuItem = new javax.swing.JMenuItem();
        followupManagerMenuItem = new javax.swing.JMenuItem();
        BillingMenu = new javax.swing.JMenu();
        timeKeeperMenuItem = new javax.swing.JMenuItem();
        InvoiceManagerMenuItem = new javax.swing.JMenuItem();
        monthlyBillingCycleMenuItem = new javax.swing.JMenuItem();
        invoiceAllOutstandingInvoicesMenuItem = new javax.swing.JMenuItem();
        PaymentLogMenuItem = new javax.swing.JMenuItem();
        ExpenseManagerMenuItem = new javax.swing.JMenuItem();
        timeEditorMenuItem = new javax.swing.JMenuItem();
        ReportsMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        viewSystemLogMenuItem = new javax.swing.JMenuItem();
        DBAdminMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        statusMessageLabel = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        lblLastAction = new javax.swing.JLabel();
        statusbarSeparator = new javax.swing.JSeparator();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(905, 500));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(LegalTimeView.class);
        desktop.setBackground(resourceMap.getColor("desktop.background")); // NOI18N
        desktop.setName("desktop"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 891, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getActionMap(LegalTimeView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        preferencesMenuItem.setAction(actionMap.get("showPreferencesManager")); // NOI18N
        preferencesMenuItem.setText(resourceMap.getString("preferencesMenuItem.text")); // NOI18N
        preferencesMenuItem.setName("preferencesMenuItem"); // NOI18N
        fileMenu.add(preferencesMenuItem);

        menuBar.add(fileMenu);

        clientMenu.setText(resourceMap.getString("clientMenu.text")); // NOI18N
        clientMenu.setName("clientMenu"); // NOI18N

        clientManagerMenuItem.setAction(actionMap.get("showClientManager")); // NOI18N
        clientManagerMenuItem.setText(resourceMap.getString("clientManagerMenuItem.text")); // NOI18N
        clientManagerMenuItem.setName("clientManagerMenuItem"); // NOI18N
        clientMenu.add(clientManagerMenuItem);

        clientAccountingMenuItem.setAction(actionMap.get("showClientAccounting")); // NOI18N
        clientAccountingMenuItem.setText(resourceMap.getString("clientAccountingMenuItem.text")); // NOI18N
        clientAccountingMenuItem.setName("clientAccountingMenuItem"); // NOI18N
        clientMenu.add(clientAccountingMenuItem);

        followupManagerMenuItem.setAction(actionMap.get("showFollowupManager")); // NOI18N
        followupManagerMenuItem.setText(resourceMap.getString("followupManagerMenuItem.text")); // NOI18N
        followupManagerMenuItem.setName("followupManagerMenuItem"); // NOI18N
        clientMenu.add(followupManagerMenuItem);

        menuBar.add(clientMenu);

        BillingMenu.setText(resourceMap.getString("BillingMenu.text")); // NOI18N
        BillingMenu.setName("BillingMenu"); // NOI18N

        timeKeeperMenuItem.setAction(actionMap.get("showTimeRecorder")); // NOI18N
        timeKeeperMenuItem.setText(resourceMap.getString("timeKeeperMenuItem.text")); // NOI18N
        timeKeeperMenuItem.setName("timeKeeperMenuItem"); // NOI18N
        BillingMenu.add(timeKeeperMenuItem);

        InvoiceManagerMenuItem.setAction(actionMap.get("showInvoiceManager")); // NOI18N
        InvoiceManagerMenuItem.setText(resourceMap.getString("InvoiceManagerMenuItem.text")); // NOI18N
        InvoiceManagerMenuItem.setName("InvoiceManagerMenuItem"); // NOI18N
        BillingMenu.add(InvoiceManagerMenuItem);

        monthlyBillingCycleMenuItem.setAction(actionMap.get("showMonthlyBillingCycle")); // NOI18N
        monthlyBillingCycleMenuItem.setText(resourceMap.getString("monthlyBillingCycleMenuItem.text")); // NOI18N
        monthlyBillingCycleMenuItem.setName("monthlyBillingCycleMenuItem"); // NOI18N
        BillingMenu.add(monthlyBillingCycleMenuItem);

        invoiceAllOutstandingInvoicesMenuItem.setText(resourceMap.getString("invoiceAllOutstandingInvoicesMenuItem.text")); // NOI18N
        invoiceAllOutstandingInvoicesMenuItem.setName("invoiceAllOutstandingInvoicesMenuItem"); // NOI18N
        BillingMenu.add(invoiceAllOutstandingInvoicesMenuItem);

        PaymentLogMenuItem.setAction(actionMap.get("showPaymentLog")); // NOI18N
        PaymentLogMenuItem.setText(resourceMap.getString("PaymentLogMenuItem.text")); // NOI18N
        PaymentLogMenuItem.setName("PaymentLogMenuItem"); // NOI18N
        BillingMenu.add(PaymentLogMenuItem);

        ExpenseManagerMenuItem.setAction(actionMap.get("showExpenseManager")); // NOI18N
        ExpenseManagerMenuItem.setText(resourceMap.getString("ExpenseManagerMenuItem.text")); // NOI18N
        ExpenseManagerMenuItem.setName("ExpenseManagerMenuItem"); // NOI18N
        BillingMenu.add(ExpenseManagerMenuItem);

        timeEditorMenuItem.setAction(actionMap.get("showTimeEditor")); // NOI18N
        timeEditorMenuItem.setText(resourceMap.getString("timeEditorMenuItem.text")); // NOI18N
        timeEditorMenuItem.setName("timeEditorMenuItem"); // NOI18N
        BillingMenu.add(timeEditorMenuItem);

        menuBar.add(BillingMenu);

        ReportsMenu.setAction(actionMap.get("showExpenseManager")); // NOI18N
        ReportsMenu.setText(resourceMap.getString("ReportsMenu.text")); // NOI18N
        ReportsMenu.setName("ReportsMenu"); // NOI18N

        jMenuItem1.setAction(actionMap.get("buildClientAddressLabelReport")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        ReportsMenu.add(jMenuItem1);

        menuBar.add(ReportsMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        viewSystemLogMenuItem.setAction(actionMap.get("showLogViewer")); // NOI18N
        viewSystemLogMenuItem.setText(resourceMap.getString("viewSystemLogMenuItem.text")); // NOI18N
        viewSystemLogMenuItem.setName("viewSystemLogMenuItem"); // NOI18N
        helpMenu.add(viewSystemLogMenuItem);

        DBAdminMenuItem.setAction(actionMap.get("showDBAdmin")); // NOI18N
        DBAdminMenuItem.setText(resourceMap.getString("DBAdminMenuItem.text")); // NOI18N
        DBAdminMenuItem.setName("DBAdminMenuItem"); // NOI18N
        helpMenu.add(DBAdminMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setMaximumSize(new java.awt.Dimension(32767, 30));
        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(684, 30));
        statusPanel.setRequestFocusEnabled(false);
        statusPanel.setLayout(new java.awt.GridBagLayout());

        statusMessageLabel.setText(resourceMap.getString("statusMessageLabel.text")); // NOI18N
        statusMessageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        statusPanel.add(statusMessageLabel, gridBagConstraints);

        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVersion.setText(resourceMap.getString("lblVersion.text")); // NOI18N
        lblVersion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblVersion.setName("lblVersion"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        statusPanel.add(lblVersion, gridBagConstraints);

        progressBar.setAutoscrolls(true);
        progressBar.setName("progressBar"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        statusPanel.add(progressBar, gridBagConstraints);

        lblLastAction.setText(resourceMap.getString("lblLastAction.text")); // NOI18N
        lblLastAction.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblLastAction.setName("lblLastAction"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        statusPanel.add(lblLastAction, gridBagConstraints);

        statusbarSeparator.setName("statusbarSeparator"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        statusPanel.add(statusbarSeparator, gridBagConstraints);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu BillingMenu;
    private javax.swing.JMenuItem DBAdminMenuItem;
    private javax.swing.JMenuItem ExpenseManagerMenuItem;
    private javax.swing.JMenuItem InvoiceManagerMenuItem;
    private javax.swing.JMenuItem PaymentLogMenuItem;
    private javax.swing.JMenu ReportsMenu;
    private javax.swing.JMenuItem clientAccountingMenuItem;
    private javax.swing.JMenuItem clientManagerMenuItem;
    private javax.swing.JMenu clientMenu;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem followupManagerMenuItem;
    private javax.swing.JMenuItem invoiceAllOutstandingInvoicesMenuItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblLastAction;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem monthlyBillingCycleMenuItem;
    private javax.swing.JMenuItem preferencesMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JSeparator statusbarSeparator;
    private javax.swing.JMenuItem timeEditorMenuItem;
    private javax.swing.JMenuItem timeKeeperMenuItem;
    private javax.swing.JMenuItem viewSystemLogMenuItem;
    // End of variables declaration//GEN-END:variables


public javax.swing.JDesktopPane getDesktop(){
    return desktop;
}


    @Action
    public void showTimeRecorder() {
        if (timeRecorder == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            timeRecorder = new TimeRecorder();
            timeRecorder.setMainController(LegalTimeApp.getApplication());
            
        }
        
        LegalTimeApp.getApplication().show(timeRecorder);
        LegalTimeApp.getApplication().hideMainWindow();
    }

    @Action
    public void showClientManager() {
        ClientEditorController.getInstance(LegalTimeApp.getApplication()).showClientEditorViewer();
    }
    @Action
    public void showPaymentLog() {
        PaymentLogController.getInstance(LegalTimeApp.getApplication()).showPaymentLogView();
    }

    @Action
    public void showMonthlyBillingCycle() {
        MonthlyCycleManager monthlyCycleManager = new MonthlyCycleManager();
        monthlyCycleManager.assessMonthlyRetainers(new java.util.Date());
    }
    @Action
    public void showFollowupManager(){
        FollowupController.getInstance(LegalTimeApp.getApplication()).showFollowupViewer();

    }

    @Action
    public void showClientAccounting(){
        ClientAccountRegisterController.getInstance(LegalTimeApp.getApplication()).showClientAccountRegisterView();
    }

    @Action
    public void showInvoiceManager(){
        InvoiceController.getInstance(LegalTimeApp.getApplication()).showInvoiceEditorViewer();
//        if (invoiceManager == null) {
//            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
//            invoiceManager = new InvoiceEditorView();
//            invoiceManager.setMainController(LegalTimeApp.getApplication());
//
//        }
//        invoiceManager.setVisible(true);
//        desktop.add(invoiceManager);
//        try {
//            invoiceManager.setSelected(true);
//        } catch (java.beans.PropertyVetoException e) {}
    }

    @Action
    public void showExpenseManager(){
        if (expenseManager == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            expenseManager = new ExpenseManager();
            expenseManager.setMainController(LegalTimeApp.getApplication());

        }
        expenseManager.setVisible(true);
        desktop.add(expenseManager);
        try {
            expenseManager.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    @Action
    public void showTimeEditor(){
        if (timeEditor == null) {
            //JFrame mainFrame = LegalTimeApp.getApplication().getMainFrame();
            timeEditor = new TimeEditor();
            timeEditor.setMainController(LegalTimeApp.getApplication());

        }
        timeEditor.setVisible(true);
        desktop.add(timeEditor);
        try {
            timeEditor.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    @Action
    public void showPreferencesManager(){
        if (preferencesManager == null) {
            preferencesManager = new PreferencesEditorView();
            preferencesManager.setMainController(LegalTimeApp.getApplication());
        }
        preferencesManager.setVisible(true);
        desktop.add(preferencesManager);
        try {
            preferencesManager.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    @Action
    public void showDBAdmin(){
        if (dBAdminView == null) {
            dBAdminView = new DBAdminView();
            dBAdminView.setMainController(LegalTimeApp.getApplication());
        }
        dBAdminView.setVisible(true);
        desktop.add(dBAdminView);
        try {
            dBAdminView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    @Action
    public void showLogViewer(){
        
            logView = new LogView();
            
        
        logView.setVisible(true);
        desktop.add(logView);
        try {
            logView.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

//    @Action
//    public void showFollowupItemEditor(){
//         FollowupController.getInstance(LegalTimeApp.getApplication()).showFollowupItemEditorView();
//    }

    public void setStatusText(String newText){
        statusMessageLabel.setText("Status: "+ newText);
    }
    public void setLastActionText(String newText){
        lblLastAction.setText("Last Action: "+ newText);
        if (newText.contains("Err")){
            lblLastAction.setBackground(Color.red);
            
        }else{
            lblLastAction.setBackground(statusMessageLabel.getBackground());
        }
        //This should be handled within the EasyLog
        //easyLog.addEntry(EasyLog.INFO, newText, getClass().getName(), "LAST ACTTION");
    }
    public void setProgressBarProgressValue(int value){
        if (value ==0 || value ==100){
            progressBar.setIndeterminate(true);
            progressBar.setVisible(false);
        }
        else{
            progressBar.setIndeterminate(true);
            progressBar.setVisible(true);
        }
        progressBar.setValue(value);
    }

    @Action
    public void buildClientAddressLabelReport(){
        ClientAddressLabelReport clientAddressLabelReport = new ClientAddressLabelReport();
        clientAddressLabelReport.makeReport();
    }


}
