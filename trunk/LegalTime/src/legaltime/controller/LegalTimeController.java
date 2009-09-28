/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import legaltime.AppPrefs;
import legaltime.DBManager.VersionManager;
import legaltime.LegalTimeApp;
import legaltime.modelsafe.EasyLog;
import legaltime.modelsafe.PersistanceManager;
import legaltime.reports.ClientAddressLabelReport;
import legaltime.reports.ClientHourlyBillingRateReport;
import legaltime.reports.FollowupReport;
import legaltime.view.InvoicedClientAddressLabelDateConfirmation;
import legaltime.view.LegalTimeView;

/**
 *
 * @author bmartin
 */
public class LegalTimeController {
    private LegalTimeView legalTimeView;
    static LegalTimeController instance =null;
    private LegalTimeApp app;
    private VersionManager vm ;
    private PersistanceManager persistanceManager;
    EasyLog easyLog;
    AppPrefs appPrefs;
    protected LegalTimeController(LegalTimeApp app_){
        app = app_;
        appPrefs = AppPrefs.getInstance();
        legalTimeView =  new LegalTimeView(this);
        easyLog =EasyLog.getInstance();
        persistanceManager = PersistanceManager.getInstance();
        vm = new VersionManager();
        legalTimeView.getLblVersion().setText( "Version: " + LegalTimeApp.APP_VERSION
                    +"|"+ VersionManager.INSTALLED_VERSION);

        
    }
    public static  LegalTimeController getInstance(LegalTimeApp app_){

        if(instance == null){
            instance = new LegalTimeController(app_);
        }
        return instance;

    }

    /**
     * @return the legalTimeView
     */
    public LegalTimeView getLegalTimeView() {
        return legalTimeView;
    }
    public JDesktopPane getDesktop(){
        return legalTimeView.getDesktop();
    }

    /**
     * @return the app
     */
    public LegalTimeApp getApp() {
        return app;
    }

    public void manageUpdates(){

            String dbUpgradeResult = vm.installAllDbPatches();
            if(dbUpgradeResult.equals(VersionManager.NEW_VERSION_FAILED)){
                legalTimeView.setLastActionText("ERROR: DB upgrade failed.  Please review " +
                        "Logs and email developer the error detail.");
                JOptionPane.showInternalConfirmDialog(legalTimeView.getDesktop(),
                        "The application Failed a database upgrade and may " +
                        "not be stable.  Contact the developer."
                        ,"Warning",JOptionPane.WARNING_MESSAGE);

            }else if (dbUpgradeResult.equals(VersionManager.NEW_VERSION_INSTALLED)){

                legalTimeView.setLastActionText("New Database version successfully installed." +
                        "  Application Restart Required");
                JOptionPane.showInternalMessageDialog(legalTimeView.getDesktop(),
                        "Upgrades have completed.  The application will close." +
                        "  Please re-open the application."
                        ,"Notice",JOptionPane.WARNING_MESSAGE);
                app.exit();

            }

            try {
                if (VersionManager.INSTALLED_VERSION.equals("NOT SET")){
                    legalTimeView.setLastActionText("Critical Error Loading DB Version " +
                            "- Likely database connectivity issue.  " +
                            "Check Preferences Tab.");
                }
                else if(!VersionManager.INSTALLED_VERSION.equals(VersionManager.REQUIRED_DB_VERSION) ){
                    JOptionPane.showInternalMessageDialog(legalTimeView.getDesktop(),
                        "There is a Database Version conflict.  Expecting: "
                        + VersionManager.REQUIRED_DB_VERSION+" but found: "
                        + VersionManager.INSTALLED_VERSION
                        +".  The application may not be stable.  Please restart " +
                        "the application and contact the developer"
                        ,"Warning",JOptionPane.WARNING_MESSAGE);


                }
            }catch(java.lang.ArrayIndexOutOfBoundsException e){
                 JOptionPane.showInternalMessageDialog(legalTimeView.getDesktop(),
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

    public void ClientAddressLabelReport() {
        ClientAddressLabelReport clientAddressLabelReport = new ClientAddressLabelReport();
        clientAddressLabelReport.makeAllAddressLabelReport();
        JOptionPane.showInternalConfirmDialog(legalTimeView.getDesktop(), "The Report has been saved to the report" +
                " output location specified in your preferences: "  
                + appPrefs.getValue(AppPrefs.REPORT_OUTPUT_PATH)
                ,"Report Saved",JOptionPane.DEFAULT_OPTION);
    }

    public void showClientAccountRegisterView() {
        ClientAccountRegisterController.getInstance(this).showClientAccountRegisterView();
    }

    public void setStatusText(String newText){
        legalTimeView.setStatusText(newText);

    }
    public void setLastActionText(String newText){
        legalTimeView.setLastActionText( newText);
    }
    public void setProgressBarValue(int value){
        legalTimeView.setProgressBarProgressValue(value);
    }

    public void showClientEditorViewer() {
        ClientEditorController.getInstance(this).showClientEditorViewer();
    }
    public javax.swing.JFrame getMainFrame(){
        return app.getMainFrame();
    }

    public void showFollowupViewer() {
        FollowupController.getInstance(this).showFollowupViewer();
    }

    public void invoicedClientAddressLabelReport() {
        ClientAddressLabelReport clientAddressLabelReport = new ClientAddressLabelReport();
        InvoicedClientAddressLabelDateConfirmation InvoicedClientAddressLabelDateConfirmation;
        InvoicedClientAddressLabelDateConfirmation = new
                InvoicedClientAddressLabelDateConfirmation(getMainFrame());
        java.util.Date effectiveDate= null;

        InvoicedClientAddressLabelDateConfirmation.setVisible(true);
        if(InvoicedClientAddressLabelDateConfirmation.isSelectionConfirmed()){
            effectiveDate = InvoicedClientAddressLabelDateConfirmation.getDate();
             if(clientAddressLabelReport.makeInvoicedAddressLabelReport(effectiveDate)){
                JOptionPane.showInternalConfirmDialog(legalTimeView.getDesktop(), "The report has been saved to the report" +
                        " output location specified in your preferences: " +
                        appPrefs.getValue(AppPrefs.REPORT_OUTPUT_PATH)
                        ,"Report Saved",JOptionPane.DEFAULT_OPTION);
             }

        }
        InvoicedClientAddressLabelDateConfirmation.dispose();



    }

    public void FollowupReport() {
        FollowupReport followupReport = new FollowupReport();
        if(followupReport.makeFollowUpReport()){
          JOptionPane.showInternalConfirmDialog(legalTimeView.getDesktop(), "The report has been saved to the report" +
                " output location specified in your preferences: " +
                appPrefs.getValue(AppPrefs.REPORT_OUTPUT_PATH)
                ,"Report Saved",JOptionPane.DEFAULT_OPTION);
        }
    }

    public void clientHourlyBillRatesReport() {
      ClientHourlyBillingRateReport clientHourlyBillingRateReport = new ClientHourlyBillingRateReport();
        if(clientHourlyBillingRateReport.makeClientHourlyBillRatesReport()){
          JOptionPane.showInternalConfirmDialog(legalTimeView.getDesktop(), "The report has been saved to the report" +
                " output location specified in your preferences: " +
                appPrefs.getValue(AppPrefs.REPORT_OUTPUT_PATH)
                ,"Report Saved",JOptionPane.DEFAULT_OPTION);
        }
    }


}
