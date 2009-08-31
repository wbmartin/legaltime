/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.LegalTimeApp;
import legaltime.modelsafe.SQLGarage;
import legaltime.cache.ClientBillRateCache;
import legaltime.model.ClientAccountRegisterBean;
import legaltime.model.ClientAccountRegisterManager;
import legaltime.model.ExpenseInvoiceItemBean;
import legaltime.model.ExpenseInvoiceItemManager;
import legaltime.model.ExpenseRegisterBean;
import legaltime.model.ExpenseRegisterManager;
import legaltime.model.InvoiceBean;
import legaltime.model.InvoiceManager;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.model.Manager;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.reports.InvoiceReport;

import legaltime.view.InvoiceEditorView;

/**
 *
 * @author bmartin
 */
public class ProcessControllerInvoice {
    private ClientBillRateCache clientBillRateCache;
    private Manager manager;
    private LaborInvoiceItemBean laborInvoiceItemBean;
    private LaborInvoiceItemManager laborInvoiceItemManager;
    private ExpenseInvoiceItemBean expenseInvoiceItemBean;
    private ExpenseInvoiceItemManager expenseInvoiceItemManager;
    private LaborRegisterManager laborRegisterManager;
    private ExpenseRegisterManager expenseRegisterManager;
    private PaymentLogManager paymentLogManager;
    private EasyLog easyLog;
    private ClientAccountRegisterManager clientAccountRegisterManager;
    private ClientAccountRegisterBean clientAccountRegisterBean;
    LegalTimeApp app;

    public ProcessControllerInvoice(){
        easyLog = EasyLog.getInstance();
        clientBillRateCache = ClientBillRateCache.getInstance();
        manager = Manager.getInstance();
        laborRegisterManager =LaborRegisterManager.getInstance();
        
        clientAccountRegisterManager = ClientAccountRegisterManager.getInstance();
        paymentLogManager= PaymentLogManager.getInstance();
        app = LegalTimeApp.getApplication();
        laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
        expenseInvoiceItemManager = ExpenseInvoiceItemManager.getInstance();
        expenseRegisterManager = ExpenseRegisterManager.getInstance();
        
    }
    public double getInvoiceTotal(LaborRegisterBean[] laborRegisterBeans_
            ,ExpenseRegisterBean[] expenseRegisterBeans_){

        Double result = 0D;
        double billRate =0;
        try{
            for (int ndx=0; ndx< laborRegisterBeans_.length;ndx++){
               if (laborRegisterBeans_[ndx].getInvoiceable().equals(true)){

                   result += laborRegisterBeans_[ndx].getMinutes()/60D
                              * laborRegisterBeans_[ndx].getBillRate();
               }

            }
            for (int ndx=0; ndx< expenseRegisterBeans_.length;ndx++){
               if (expenseRegisterBeans_[ndx].getInvoiceable().equals(true)){

                   result += expenseRegisterBeans_[ndx].getAmount();
               }

            }
        }catch(NullPointerException e){
            easyLog.addEntry(EasyLog.INFO, "Error getting Invoice Total"
                    , getClass().getName(), e);

        }
        return result;
    }

    public boolean buildAndSaveInvoice(int clientId_
            , LaborRegisterBean[] laborRegisterBeans_
            , ExpenseRegisterBean[] expenseRegisterBeans_
            , PaymentLogBean[] paymentLogBeans_){
        boolean result = false;
        Double totalInvoiceAmount=0D;
        try {
            InvoiceManager invoiceManager = InvoiceManager.getInstance();
            InvoiceBean invoiceBean = invoiceManager.createInvoiceBean();
            invoiceBean.setClientId(clientId_);
            invoiceBean.setGeneratedDate(new java.util.Date());
            invoiceBean.setInvoiceDt(new java.util.Date());
            totalInvoiceAmount=getInvoiceTotal(
                    laborRegisterBeans_
                    , expenseRegisterBeans_);
            invoiceBean.setInvoiceTotalAmt(totalInvoiceAmount);
            invoiceBean.setPrevBalanceDue(getPreviousBalance(clientId_));

            
            manager.beginTransaction();
            invoiceBean = invoiceManager.save(invoiceBean);
            laborInvoiceItemBean = laborInvoiceItemManager.createLaborInvoiceItemBean();
            int rowCount = laborRegisterBeans_.length;

            for (int ndx = 0; ndx < rowCount ; ndx++) {
                if(laborRegisterBeans_[ndx].getInvoiceable()){
                    laborInvoiceItemBean = laborInvoiceItemManager.createLaborInvoiceItemBean();
                    laborInvoiceItemBean.setActivityDate(
                            laborRegisterBeans_[ndx].getActivityDate());
                    laborInvoiceItemBean.setActivityDescription(
                            laborRegisterBeans_[ndx].getDescription());
                    laborInvoiceItemBean.setBillRate(
                            laborRegisterBeans_[ndx].getBillRate());
                    laborInvoiceItemBean.setHoursBilled(
                            laborRegisterBeans_[ndx].getMinutes()/60D);
                    laborInvoiceItemBean.setInvoiceId(invoiceBean.getInvoiceId());
                    laborInvoiceItemBean.setUserKey(
                            laborRegisterBeans_[ndx].getUserKey());

                    laborInvoiceItemBean = laborInvoiceItemManager.save(laborInvoiceItemBean);

                    laborRegisterBeans_[ndx].setInvoiceId(invoiceBean.getInvoiceId());
                    laborRegisterManager.save(laborRegisterBeans_[ndx]);


                }


            }
            
            rowCount = expenseRegisterBeans_.length;

            for (int ndx = 0; ndx < rowCount ; ndx++) {
                if(expenseRegisterBeans_[ndx].getInvoiceable()){
                    expenseInvoiceItemBean = expenseInvoiceItemManager.createExpenseInvoiceItemBean();
                    expenseInvoiceItemBean.setExpenseDate(
                            expenseRegisterBeans_[ndx].getExpenseDate());
                    expenseInvoiceItemBean.setExpenseDescription(
                            expenseRegisterBeans_[ndx].getDescription());
                    expenseInvoiceItemBean.setAmount(
                            expenseRegisterBeans_[ndx].getAmount());
                    expenseInvoiceItemBean.setInvoiceId(invoiceBean.getInvoiceId());

                    expenseInvoiceItemBean = expenseInvoiceItemManager.save(expenseInvoiceItemBean);
                    expenseRegisterBeans_[ndx].setInvoiceId(invoiceBean.getInvoiceId());
                    expenseRegisterManager.save(expenseRegisterBeans_[ndx]);


                }


            }
            
            
            rowCount = paymentLogBeans_.length;

            for (int ndx = 0; ndx < rowCount ; ndx++) {
                  paymentLogBeans_[ndx].setInvoiceId(invoiceBean.getInvoiceId());
                   paymentLogBeans_[ndx] = paymentLogManager.save(paymentLogBeans_[ndx]);
            }



            clientAccountRegisterBean = clientAccountRegisterManager.createClientAccountRegisterBean();
            clientAccountRegisterBean.setClientId(clientId_);
            clientAccountRegisterBean.setTranType("INVCE");
            clientAccountRegisterBean.setTranAmt(totalInvoiceAmount);
            clientAccountRegisterBean.setEffectiveDate(invoiceBean.getInvoiceDt());
            clientAccountRegisterBean.setDescription("Invoice #"+ invoiceBean.getInvoiceId());
            clientAccountRegisterManager.save(clientAccountRegisterBean);


            manager.endTransaction(true);
            InvoiceReport test = new InvoiceReport();
            if(test.makeReport(invoiceBean.getInvoiceId())){
                 app.setLastActionText("Invoice Successfully Created.");
                 result = true;
            }else{
                app.setLastActionText("Error: Invoice Generation Failed.");
                 easyLog.addEntry(EasyLog.SEVERE, "Rollback of Invoice Creation Failed"
                        , getClass().getName(), "Error: InvoiceReport.makeReport returned false");
            }
        } catch (SQLException ex) {
            try {
                easyLog.addEntry(EasyLog.INFO, "Rolling Back Invoice"
                    , getClass().getName(), ex);
                manager.endTransaction(false);//rollback if failed
            } catch (SQLException ex1) {
                Logger.getLogger(InvoiceEditorView.class.getName()).log(Level.SEVERE, null, ex1);
                easyLog.addEntry(EasyLog.SEVERE, "Rollback of Invoice Creation Failed"
                        , getClass().getName(), ex);
            }
            Logger.getLogger(InvoiceEditorView.class.getName()).log(Level.SEVERE, null, ex);
             easyLog.addEntry(EasyLog.SEVERE, "Rolled back Invoice Creation"
                        , getClass().getName(), ex);
             app.setLastActionText("Failed Invoice creation");
        }catch(Exception e){
            easyLog.addEntry(EasyLog.SEVERE, "Error: General Exception"
                        , getClass().getName(), e);
        }
        return result;
    }

    public LaborRegisterBean[] getInvoiceableLaborItems(int clientId_ ){
        LaborRegisterBean[] invoiceableItems;
        try {
            invoiceableItems = laborRegisterManager.loadByWhere(
                    "where client_id = " + clientId_+
                    " and invoice_id is null ");
        } catch (DAOException ex) {
             invoiceableItems = null;
            Logger.getLogger(InvoiceEditorView.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Loading Invoiceable Labor Items",
                    getClass().getName(),ex);
            invoiceableItems = new LaborRegisterBean[] {};
        }
        return invoiceableItems;
    }

    public ExpenseRegisterBean[] getInvoiceableExpenseItems(int clientId_ ){
        ExpenseRegisterBean[] invoiceableItems;
        try {
            invoiceableItems = expenseRegisterManager.loadByWhere(
                    "where client_id = " + clientId_+
                    " and invoice_id is null ");
        } catch (DAOException ex) {
             invoiceableItems = null;
            Logger.getLogger(InvoiceEditorView.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Loading Invoiceable Expense Items",
                    getClass().getName(),ex);
            invoiceableItems = new ExpenseRegisterBean[] {};
        }
        return invoiceableItems;
    }

    public PaymentLogBean[] getInvoicePaymentLog(int clientId_ ){
        PaymentLogBean[] invoiceableItems;
        try {
            invoiceableItems = paymentLogManager.loadByWhere(
                    "where client_id = " + clientId_+
                    " and invoice_id is null ");
        } catch (DAOException ex) {
             invoiceableItems = null;
            Logger.getLogger(InvoiceEditorView.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Loading PaymentLog",
                    getClass().getName(),ex);
            invoiceableItems = new PaymentLogBean[] {};
        }
        return invoiceableItems;
    }

    public Double getPreviousBalance(int clientId_){
       Double previousBalance=0D;
       ResultSet rs = null;
       PreparedStatement ps;
        try {
            ps = manager.getConnection().prepareStatement(
                    SQLGarage.getPrevBalanceSQL(clientId_));
            rs = ps.executeQuery();
            if(rs.next()){
                previousBalance= rs.getDouble(1);
            }
            if(previousBalance.isNaN()){
                previousBalance=0D;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }


       return previousBalance;
   }

     public boolean generateInvoice(int clientId_){
        boolean result = false;
     
        result  = buildAndSaveInvoice(
                clientId_
                ,getInvoiceableLaborItems( clientId_)
                ,getInvoiceableExpenseItems( clientId_)
                ,getInvoicePaymentLog( clientId_));

        return result;
    }

     public boolean generateAllOutstandingInvoices(){
         boolean result = true;
         boolean singleResult = true;
         
        try {
            Statement s = manager.getConnection().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(SQLGarage.INVOICE_LIST_SQL);
            //LegalTimeApp.getApplication().getPrimaryView().setProgressBarProgressValue(progress);
            while (rs.next()){

                singleResult = generateInvoice(rs.getInt(1));
                if (!singleResult){result = false;}
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE, "Error Building All Invoices", getClass().getName(), ex);
        }


         return result;
     }
}
