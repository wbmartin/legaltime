/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.cache.ClientCache;
import legaltime.model.ClientBean;
import legaltime.model.ExpenseInvoiceItemBean;
import legaltime.model.ExpenseInvoiceItemManager;
import legaltime.model.InvoiceBean;
import legaltime.model.InvoiceManager;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.exception.DAOException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.InputStream;
import legaltime.ResourceAnchor;
import legaltime.modelsafe.EasyLog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author bmartin
 */
public class InvoiceReport   {
   LaborInvoiceItemManager laborInvoiceItemManager;
   EasyLog easyLog;
   public InvoiceReport(){
        easyLog = EasyLog.getInstance();
   }
   public JRDataSource createDataSource(){
       return new JRBeanCollectionDataSource(GetLaborBeans(3));
   }

   public JRDataSource createExpenses(){
       return new JRBeanCollectionDataSource(getExpenseBeans(3));
   }

   public  void makeReport()
  {

    JasperPrint jasperPrint;
    try
    {
        String UserDesktop = System.getProperty("user.home") + "/Desktop";
        UserDesktop = UserDesktop.replace("\\", "/");
        ClassLoader cl = ResourceAnchor.class.getClassLoader();
        InputStream jasperFile = cl.getResourceAsStream("legaltime/reports/BogerInvoice.jasper");

        InvoiceReport source = new InvoiceReport();
        jasperPrint = JasperFillManager.fillReport(
          jasperFile, source.getParams(3), source.createDataSource());

        JasperExportManager.exportReportToPdfFile(
          jasperPrint, UserDesktop+"/Invoice00123_JoeClient_20090715.pdf");
    }
    catch (JRException e)
    {
        EasyLog.getInstance().addEntry(EasyLog.INFO, "Error Building Report", "Jasper Report Intro", EasyLog.getStackTrace(e));
      e.printStackTrace();
    }
  }


   public java.util.HashMap getParams(int invoiceId_){
       InvoiceManager invoiceManager = InvoiceManager.getInstance();
       InvoiceBean invoiceBean = invoiceManager.createInvoiceBean();
       ClientCache clientCache = ClientCache.getInstance();
        try {
            invoiceBean = invoiceManager.loadByWhere("where invoice_id = " + invoiceId_)[0];
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error retrieving invoice"
                    , getClass().getName(), ex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error No invoice found"
                    , getClass().getName(), ex);
        }
       ClientBean clientBean = clientCache.getBeanById(invoiceBean.getClientId());
      

       java.util.HashMap params = new java.util.HashMap();
        params.put("InvoiceDate", invoiceBean.getInvoiceDt());
        params.put("ClientName", clientBean.getFirstName() +" "+ clientBean.getLastName());
        params.put("ClientAddress", clientBean.getAddress());
        params.put("ClientCity", clientBean.getCity());
        params.put("ClientState", clientBean.getState());
        params.put("ClientState", clientBean.getZip());
        params.put("CurrentServicesRenderedAmount",0D);
        params.put("PreviousBalance",0D);
        params.put("TotalToRemit",0D);
        params.put("Expenses", createExpenses());
        return params;
   }

   public static ArrayList getExpenseBeans(int invoiceId_){
       ExpenseInvoiceItemManager expenseInvoiceItemManagernvoice =
               ExpenseInvoiceItemManager.getInstance();
       ExpenseInvoiceItemBean[] beans = new ExpenseInvoiceItemBean[] {};
        try {
            beans = expenseInvoiceItemManagernvoice.loadByWhere("where invoice_id ="+ invoiceId_);
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);

        }
       return new ArrayList(java.util.Arrays.asList(beans));

   }

   public static ArrayList GetLaborBeans(int invoiceId_){
      LaborInvoiceItemManager laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
      LaborInvoiceItemBean bean = laborInvoiceItemManager.createLaborInvoiceItemBean();
      ArrayList<LaborInvoiceItemBean> laborInvoiceItems;
      LaborInvoiceItemBean[] beanList = null;
        try {
            beanList = laborInvoiceItemManager.loadByWhere("where invoice_id=" + invoiceId_);
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
        }

      laborInvoiceItems = new ArrayList(java.util.Arrays.asList(beanList));
      return laborInvoiceItems;

  }

}
