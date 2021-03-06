/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

import java.io.File;
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
import legaltime.AppPrefs;
import legaltime.ResourceAnchor;
import legaltime.TextUtils;
import legaltime.cache.UserInfoCache;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
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
   static Double currentExpenses;
   static Double totalPaymentsReceived;
   static ClientBean clientBean;
   static ClientCache clientCache;
   static InvoiceManager invoiceManager ;
   static InvoiceBean invoiceBean;
   private static AppPrefs appPrefs;
   private String reportPath;
   public InvoiceReport(){
        easyLog = EasyLog.getInstance();
        currentServicesRendered=0D;
        currentExpenses=0D;
        clientCache = ClientCache.getInstance();
        invoiceManager = InvoiceManager.getInstance();
        appPrefs = AppPrefs.getInstance();
        invoiceBean = invoiceManager.createInvoiceBean();
        

   }
   public JRDataSource createDataSource(int invoiceId_){
       return new JRBeanCollectionDataSource(getLaborBeans(invoiceId_));
   }

   public JRDataSource createExpenses(int invoiceId_){
       return new JRBeanCollectionDataSource(getExpenseBeans(invoiceId_));
   }
   public JRDataSource createPayments(int invoiceId_){
       return new JRBeanCollectionDataSource(getPaymentBeans(invoiceId_));
   }

   public boolean makeOriginalInvoice(int invoiceId_){
       java.util.Date now = new java.util.Date();
       loadInvoice(invoiceId_);
       String outputPath = appPrefs.getValue(AppPrefs.INVOICE_OUTPUT_PATH)
                + File.separatorChar
                + Integer.toString(1900+now.getYear())
                + TextUtils.frontZeroFill( Integer.toString(now.getMonth()+1),2)
                + TextUtils.frontZeroFill( Integer.toString(now.getDate()),2)
                + "INVOICE";
       String fileName = "Invoice"+ TextUtils.frontZeroFill(invoiceBean.getInvoiceId(), 5)
                 +"_" +clientBean.getLastName() + clientBean.getFirstName() + "_"
                 + Integer.toString(1900+now.getYear())
                +TextUtils.frontZeroFill( Integer.toString(now.getMonth()+1),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getDate()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getHours()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getMinutes()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getSeconds()),2)
                +".pdf";
       return makeReport(invoiceId_, outputPath, fileName);
   }

   public  boolean makeReport(int invoiceId_, String outputPath_, String fileName_){
       boolean success = false;
       JasperPrint jasperPrint;
    
        try
        {
        

          File dir = new File(outputPath_);
        success =dir.exists();
        if(!success){
            success =(dir).mkdir();
        }
        if(!success){
            easyLog.addEntry(EasyLog.INFO, "Error Creating Report Directory " +
                    "Not Present and Could Not Create"
                    , "Invoice Report",outputPath_ );
            return false;
        }


        ClassLoader cl = ResourceAnchor.class.getClassLoader();
        InputStream jasperFile = cl.getResourceAsStream("legaltime/reports/BogerInvoice.jasper");
        reportPath =cl.getResource("legaltime/reports/").toString();
        String temp = cl.getResource("legaltime/reports/").toString();
         
         
        fileName_ = TextUtils.prepareFileName(fileName_);
        JRDataSource laborItems = createDataSource(invoiceId_);
        jasperPrint = JasperFillManager.fillReport(
            jasperFile, getParams(invoiceId_),laborItems );

        JasperExportManager.exportReportToPdfFile(
          jasperPrint, outputPath_+File.separatorChar + fileName_);
        success = true;
        easyLog.addEntry(EasyLog.INFO, "Invoice Created Successfully"
                    , "Invoice Report",outputPath_ );
    }
    catch (JRException e)    {
        easyLog.addEntry(EasyLog.INFO, "Error Building Report", getClass().getName(), e);
        success = false;
      
    }catch(Exception e){
      easyLog.addEntry(EasyLog.INFO, "Error General Exception Building report", getClass().getName(), e);
      success = false;
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
        }catch(Exception e){
            easyLog.addEntry(EasyLog.SEVERE, "Error: General Exception loading invoice"
                    , "Invoice Report - loadInvoice", e);
        }
       clientBean = clientCache.getBeanById(invoiceBean.getClientId());
   }


   public java.util.HashMap getParams(int invoiceId_){
       java.util.HashMap params = new java.util.HashMap();
       double pleaseRemit =0;
       try{
        params.put("SUBREPORT_DIR",reportPath);
        params.put("InvoiceDate", invoiceBean.getInvoiceDt());
        params.put("ClientName", clientBean.getFirstName() +" "+ clientBean.getLastName());
        params.put("ClientAddress", clientBean.getAddress());
        params.put("ClientCity", clientBean.getCity());
        params.put("ClientState", clientBean.getState());
        params.put("ClientZip", clientBean.getZip());
        params.put("CurrentServicesRenderedAmount",currentServicesRendered);
        params.put("Expenses", createExpenses(invoiceId_));
        params.put("CurrentExpenseAmount",currentExpenses);
        pleaseRemit = currentServicesRendered + currentExpenses + invoiceBean.getPrevBalanceDue();
        if(pleaseRemit <0){pleaseRemit = 0;}
        params.put("TotalToRemit",pleaseRemit);
        params.put("UserInfoCache",UserInfoCache.getInstance());
        params.put("Payments",createPayments(invoiceId_));
        params.put("PreviousBalance",invoiceBean.getPrevBalanceDue()+ totalPaymentsReceived);
       }catch(Exception e){
           easyLog.addEntry(EasyLog.INFO, "Error General Exception getParams"
                    , "Invoice Report", e);
       }
       params.put("MortgagePayment", clientBean.getMortgagePmt());
       params.put("StatementType", clientBean.getBillType());
        return params;
   }



   public static ArrayList getExpenseBeans(int invoiceId_){
       ArrayList expenseLines = null;
       ExpenseInvoiceItemManager expenseInvoiceItemManagernvoice =
               ExpenseInvoiceItemManager.getInstance();
       ExpenseInvoiceItemBean[] beans = new ExpenseInvoiceItemBean[] {};
        try {
            beans = expenseInvoiceItemManagernvoice.loadByWhere("where invoice_id ="+ invoiceId_, " order by expense_date");
             currentExpenses =0D;
            for(int ndx =0; ndx<beans.length;ndx++){
                currentExpenses += beans[ndx].getAmount();
            }
             expenseLines = new ArrayList(java.util.Arrays.asList(beans));
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);

        }catch(Exception e){
           easyLog.addEntry(EasyLog.INFO, "Error General Exception getExpenseBeans"
                    , "Invoice Report", e);
       }

       
       
       return expenseLines;

   }

   public static ArrayList getLaborBeans(int invoiceId_){
      LaborInvoiceItemManager laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
      LaborInvoiceItemBean bean = laborInvoiceItemManager.createLaborInvoiceItemBean();
      ArrayList<LaborInvoiceItemBean> laborInvoiceItems;
      LaborInvoiceItemBean[] beanList = null;
      laborInvoiceItems =null;
        try {
            beanList = laborInvoiceItemManager.loadByWhere("where invoice_id=" + invoiceId_, " order by activity_date");
            currentServicesRendered =0D;
            for(int ndx =0; ndx<beanList.length;ndx++){
                currentServicesRendered += beanList[ndx].getBillRate() * beanList[ndx].getHoursBilled();
            }
            if (beanList.length== 0){
                beanList = new LaborInvoiceItemBean[1];
                beanList[0] = laborInvoiceItemManager.createLaborInvoiceItemBean();
                beanList[0].setBillRate(0D);
                beanList[0].setHoursBilled(0D);
                beanList[0].setActivityDate(new java.util.Date());
                beanList[0].setActivityDescription("No Hours Recorded");
            }
            laborInvoiceItems = new ArrayList(java.util.Arrays.asList(beanList));
            //Removing this monthly logic because it should be added into the labor history.
//            if(clientBean.getBillType().equals("MONTHLY")){
//                currentServicesRendered += clientBean.getMonthlyBillRate();
//            }
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
           easyLog.addEntry(EasyLog.INFO, "Error: General Exception getLaborBeans"
                    , "Invoice Report", e);
        }

      
      return laborInvoiceItems;

  }


    public static ArrayList getPaymentBeans(int invoiceId_){
      PaymentLogManager paymentLogManager = PaymentLogManager.getInstance();
      PaymentLogBean bean = paymentLogManager.createPaymentLogBean();
      ArrayList<PaymentLogBean> PaymentLogs;
      PaymentLogBean[] beanList = null;
      PaymentLogs = null;
        try {
            beanList = paymentLogManager.loadByWhere("where invoice_id=" + invoiceId_);
            totalPaymentsReceived=0D;
            for(int ndx =0; ndx<beanList.length;ndx++){
                totalPaymentsReceived += beanList[ndx].getAmount();
            }
            PaymentLogs = new ArrayList(java.util.Arrays.asList(beanList));
        } catch (DAOException ex) {
            Logger.getLogger(InvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
           easyLog.addEntry(EasyLog.INFO, "Error: General Exception getPaymentBeans"
                    , "Invoice Report", e);
        }

      
      return PaymentLogs;

  }

}
