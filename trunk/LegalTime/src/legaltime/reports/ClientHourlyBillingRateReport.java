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
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.exception.DAOException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.InputStream;
import legaltime.AppPrefs;
import legaltime.ResourceAnchor;
import legaltime.TextUtils;
import legaltime.model.ClientManager;
import legaltime.model.VwClientHourlyRateBean;
import legaltime.model.VwClientHourlyRateManager;
import legaltime.modelsafe.EasyLog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author bmartin
 */
public class ClientHourlyBillingRateReport   {
   LaborInvoiceItemManager laborInvoiceItemManager;
   static EasyLog easyLog;
  
   static ClientCache clientCache;
   
   private static AppPrefs appPrefs;
   private String reportPath;
   private ClientManager clientManager;
   public ClientHourlyBillingRateReport(){
        easyLog = EasyLog.getInstance();
        clientCache = ClientCache.getInstance();
        appPrefs = AppPrefs.getInstance();
        clientManager = ClientManager.getInstance();
   }
   public JRDataSource createDataSource_AllClients(){
       ArrayList<VwClientHourlyRateBean> clientsArrayList;
        VwClientHourlyRateBean[] beans = null;
        try {
            beans = VwClientHourlyRateManager.getInstance().loadAll();
        } catch (DAOException ex) {
            Logger.getLogger(ClientHourlyBillingRateReport.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "DAO Exception getting Clients"
                    , "Client Hourly Billing Report", ex);
        }


        clientsArrayList = new ArrayList(java.util.Arrays.asList(beans));
        
       return new JRBeanCollectionDataSource(clientsArrayList);
   }
   
    public boolean makeClientHourlyBillRatesReport(){
           java.util.Date now = new java.util.Date();
           String outputPath = appPrefs.getValue(AppPrefs.REPORT_OUTPUT_PATH)
                + File.separatorChar ;

           String fileName = "ClientHourlyBillRates_"
                +Integer.toString(1900+now.getYear())
                +TextUtils.frontZeroFill( Integer.toString(now.getMonth()+1),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getDate()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getHours()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getMinutes()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getSeconds()),2)
                +".pdf";
           JRDataSource clientDataSource = createDataSource_AllClients();
           return makeReport( outputPath, fileName, clientDataSource);
       }

  
   public  boolean makeReport(String outputPath_, String fileName_, JRDataSource clientDataSource_){
       boolean success = false;
    JasperPrint jasperPrint;
    java.util.Date now = new java.util.Date();
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
                    , "Client Address Report",outputPath_ );
            return false;
        }


        ClassLoader cl = ResourceAnchor.class.getClassLoader();
        InputStream jasperFile = cl.getResourceAsStream("legaltime/reports/ClientHourlyBillRatesReport.jasper");
        reportPath =cl.getResource("legaltime/reports/").toString();
        String temp = cl.getResource("legaltime/reports/").toString();
        easyLog.addEntry(9,"FilePath", temp,"");
         
         
         
        
        jasperPrint = JasperFillManager.fillReport(
          jasperFile, getParams(),clientDataSource_ );

        JasperExportManager.exportReportToPdfFile(
          jasperPrint, outputPath_+File.separatorChar + fileName_);
        success = true;
        easyLog.addEntry(EasyLog.INFO, "Client Hourly Bill Rates Report Created Successfully"
                    , "Client Hourly Bill Rates Report",outputPath_ );
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

  


   public java.util.HashMap getParams(){
       java.util.HashMap params = new java.util.HashMap();
       try{
        params.put("SUBREPORT_DIR",reportPath);
        
       }catch(Exception e){
           easyLog.addEntry(EasyLog.INFO, "Error General Exception getParams"
                    , "Client Hourly Bill Rates Report", e);
       }
        return params;
   }
   

}
