/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.LegalTimeApp;
import legaltime.cache.ClientBillRateCache;
import legaltime.model.InvoiceBean;
import legaltime.model.InvoiceManager;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.model.Manager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.reports.InvoiceReport;

import legaltime.view.InvoiceEditorView;

/**
 *
 * @author bmartin
 */
public class InvoiceController {
    private ClientBillRateCache clientBillRateCache;
    private Manager manager;
    private LaborInvoiceItemBean laborInvoiceItemBean;
    private LaborInvoiceItemManager laborInvoiceItemManager;
    private LaborRegisterManager laborRegisterManager;
    private EasyLog easyLog;
    LegalTimeApp app;

    public InvoiceController(){
        easyLog = EasyLog.getInstance();
        clientBillRateCache = ClientBillRateCache.getInstance();
        manager = Manager.getInstance();
        laborRegisterManager =LaborRegisterManager.getInstance();
        app = LegalTimeApp.getApplication();
    }
    public double getInvoiceTotal(LaborRegisterBean[] laborRegisterBeans){

        Double result = 0D;
        double billRate =0;
        try{
            for (int ndx=0; ndx< laborRegisterBeans.length;ndx++){
               if (laborRegisterBeans[ndx].getInvoiceable().equals(true)){
                    billRate =clientBillRateCache.getBillRate(
                            laborRegisterBeans[ndx].getClientId()
                            , laborRegisterBeans[ndx].getUserKey());
                   result += laborRegisterBeans[ndx].getMinutes()/60 * billRate;
               }

            }
        }catch(NullPointerException e){
            easyLog.addEntry(EasyLog.INFO, "Error getting Invoice Total"
                    , getClass().getName(), e);

        }
        return result;
    }

    public void buildAndSaveInvoice(int clientId_,
            LaborRegisterBean[] laborRegisterBeans_){
        try {
            InvoiceManager invoiceManager = InvoiceManager.getInstance();
            InvoiceBean invoiceBean = invoiceManager.createInvoiceBean();
            invoiceBean.setClientId(clientId_);
            invoiceBean.setGeneratedDate(new java.util.Date());
            invoiceBean.setInvoiceDt(new java.util.Date());
            invoiceBean.setInvoiceTotalAmt(getInvoiceTotal(laborRegisterBeans_));
            invoiceBean.setPrevBalanceDue(0);

            laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
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
                            laborRegisterBeans_[ndx].getMinutes()/60);
                    laborInvoiceItemBean.setInvoiceId(invoiceBean.getInvoiceId());

                    laborInvoiceItemBean = laborInvoiceItemManager.save(laborInvoiceItemBean);

                    laborRegisterBeans_[ndx].setInvoiceId(invoiceBean.getInvoiceId());
                    laborRegisterManager.save(laborRegisterBeans_[ndx]);


                }


            }
            manager.endTransaction(true);
            InvoiceReport test = new InvoiceReport();
            test.makeReport();
             app.setLastActionText("Invoice Successfully Created.");
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
        }
    }

    public LaborRegisterBean[] getInvoiceableLaborItems(int clientId_ ){
        LaborRegisterBean[] invoiceableItems;
        try {
            invoiceableItems = laborRegisterManager.loadByWhere(
                    "where client_id = " + clientId_+
                    " and invoice_id is null");
        } catch (DAOException ex) {
             invoiceableItems = null;
            Logger.getLogger(InvoiceEditorView.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Loading Invoiceable Items",
                    getClass().getName(),ex);
            invoiceableItems = new LaborRegisterBean[] {};
        }
        return invoiceableItems;
    }
}
