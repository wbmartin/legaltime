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
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.exception.DAOException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.InputStream;
import legaltime.AppPrefs;
import legaltime.ResourceAnchor;
import legaltime.TextUtils;
import legaltime.model.ClientBean;
import legaltime.model.ClientManager;
import legaltime.modelsafe.EasyLog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author bmartin
 */
public class ClientAddressLabelReport   {
   LaborInvoiceItemManager laborInvoiceItemManager;
   static EasyLog easyLog;
  
   static ClientCache clientCache;
   
   private static AppPrefs appPrefs;
   private String reportPath;
   public ClientAddressLabelReport(){
        easyLog = EasyLog.getInstance();
        clientCache = ClientCache.getInstance();
        appPrefs = AppPrefs.getInstance();
   }
   public JRDataSource createDataSource(){
       return new JRBeanCollectionDataSource(getClientBeans());
   }

  
   public  boolean makeReport(){
       boolean success = false;
    JasperPrint jasperPrint;
    java.util.Date now = new java.util.Date();
    try
    {
        String outputPath = appPrefs.getValue(AppPrefs.REPORT_OUTPUT_PATH)
                + File.separatorChar
                 ;

          File dir = new File(outputPath);
        success =dir.exists();
        if(!success){
            success =(dir).mkdir();
        }
        if(!success){
            easyLog.addEntry(EasyLog.INFO, "Error Creating Report Directory " +
                    "Not Present and Could Not Create"
                    , "Client Address Report",outputPath );
            return false;
        }


        ClassLoader cl = ResourceAnchor.class.getClassLoader();
        InputStream jasperFile = cl.getResourceAsStream("legaltime/reports/ClientAddressLabels.jasper");
        reportPath =cl.getResource("legaltime/reports/").toString();
        String temp = cl.getResource("legaltime/reports/").toString();
        easyLog.addEntry(9,"FilePath", temp,"");
         
         String fileName = "ClientAddressLabels_"
                +Integer.toString(1900+now.getYear())
                +TextUtils.frontZeroFill( Integer.toString(now.getMonth()+1),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getDate()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getHours()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getMinutes()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getSeconds()),2)
                +".pdf";
         
        JRDataSource clientDataSource = createDataSource();
        jasperPrint = JasperFillManager.fillReport(
          jasperFile, getParams(),clientDataSource );

        JasperExportManager.exportReportToPdfFile(
          jasperPrint, outputPath+"/" + fileName);
        success = true;
        easyLog.addEntry(EasyLog.INFO, "Client Address Labels Created Successfully"
                    , "Client Address Label Report",outputPath );
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
                    , "Client Address Label Report", e);
       }
        return params;
   }



   
   public static ArrayList getClientBeans(){
        ArrayList<LaborInvoiceItemBean> Clients;
        ClientBean[] beans = null;
        try {
            beans = ClientManager.getInstance().loadByWhere("where active_yn ='Y'");
        } catch (DAOException ex) {
            Logger.getLogger(ClientAddressLabelReport.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "DAO Exception getting Clients"
                    , "Client Address Labels Report", ex);
        }


        Clients = new ArrayList(java.util.Arrays.asList(beans));
        return Clients;
    }


   

}
