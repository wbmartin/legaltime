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
import legaltime.cache.UserInfoCache;
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
   static EasyLog easyLog;
   static Double currentServicesRendered;
   static ClientBean clientBean;
   static ClientCache clientCache;
   static InvoiceManager invoiceManager ;
   static     InvoiceBean invoiceBean;
   public InvoiceReport(){
        easyLog = EasyLog.getInstance();
        currentServicesRendered=0D;
        clientCache = ClientCache.getInstance();
        invoiceManager = InvoiceManager.getInstance();
        invoiceBean = invoiceManager.createInvoiceBean();
        

   }
   public JRDataSource createDataSource(int invoiceId_){
       return new JRBeanCollectionDataSource(GetLaborBeans(invoiceId_));
   }

   public JRDataSource createExpenses(int invoiceId_){
       return new JRBeanCollectionDataSource(getExpenseBeans(invoiceId_));
   }

   public  boolean makeReport(int invoiceId_){
       boolean success = false;
    JasperPrint jasperPrint;
    try
    {
        String UserDesktop = System.getProperty("user.home") + "/Desktop";
        UserDesktop = UserDesktop.replace("\\", "/");
        ClassLoader cl = ResourceAnchor.class.getClassLoader();
        InputStream jasperFile = cl.getResourceAsStream("legaltime/reports/BogerInvoice.jasper");
         loadInvoice(invoiceId_);
         
        JRDataSource laborItems = createDataSource(invoiceId_);
        jasperPrint = JasperFillManager.fillReport(
          jasperFile, getParams(invoiceId_),laborItems );

        JasperExportManager.exportReportToPdfFile(
          jasperPrint, UserDesktop+"/Invoice00123_JoeClient_20090715.pdf");
        success = true;
    }
    catch (JRException e)    {
        EasyLog.getInstance().addEntry(EasyLog.INFO, "Error Building Report", "Jasper Report Intro", EasyLog.getStackTrace(e));
      
    }

    return success;
  }

   static public void  loadInvoice(int invoiceId_){
       try {
            invoiceBean = invoiceManager.loadByWhere("where invoice_id = " + invoiceId_)[0];
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error retrieving invoice"
                    , "Invoice Report", ex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error No invoice found"
                    , "Invoice Report", ex);
        }
       clientBean = clientCache.getBeanById(invoiceBean.getClientId());
   }


   public java.util.HashMap getParams(int invoiceId_){
        java.util.HashMap params = new java.util.HashMap();
        params.put("InvoiceDate", invoiceBean.getInvoiceDt());
        params.put("ClientName", clientBean.getFirstName() +" "+ clientBean.getLastName());
        params.put("ClientAddress", clientBean.getAddress());
        params.put("ClientCity", clientBean.getCity());
        params.put("ClientState", clientBean.getState());
        params.put("ClientState", clientBean.getZip());
        params.put("CurrentServicesRenderedAmount",currentServicesRendered);
        params.put("PreviousBalance",0D);
        params.put("TotalToRemit",currentServicesRendered);
        params.put("Expenses", createExpenses(invoiceId_));
        params.put("UserInfoCache",UserInfoCache.getInstance());
        params.put("Expenses",null);
        return params;
   }

   public static ArrayList getExpenseBeans(int invoiceId_){
       ArrayList expenseLines;
       ExpenseInvoiceItemManager expenseInvoiceItemManagernvoice =
               ExpenseInvoiceItemManager.getInstance();
       ExpenseInvoiceItemBean[] beans = new ExpenseInvoiceItemBean[] {};
        try {
            beans = expenseInvoiceItemManagernvoice.loadByWhere("where invoice_id ="+ invoiceId_);
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);

        }
       expenseLines = new ArrayList(java.util.Arrays.asList(beans));
       
       return expenseLines;

   }

   public static ArrayList GetLaborBeans(int invoiceId_){
      LaborInvoiceItemManager laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
      LaborInvoiceItemBean bean = laborInvoiceItemManager.createLaborInvoiceItemBean();
      ArrayList<LaborInvoiceItemBean> laborInvoiceItems;
      LaborInvoiceItemBean[] beanList = null;
      
        try {
            beanList = laborInvoiceItemManager.loadByWhere("where invoice_id=" + invoiceId_);
            currentServicesRendered =0D;
            for(int ndx =0; ndx<beanList.length;ndx++){
                currentServicesRendered += beanList[ndx].getBillRate() * beanList[ndx].getHoursBilled();
            }
            if(clientBean.getBillType().equals("MONTHLY")){
                currentServicesRendered += clientBean.getMonthlyBillRate();
            }
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
        }

      laborInvoiceItems = new ArrayList(java.util.Arrays.asList(beanList));
      return laborInvoiceItems;

  }

}
