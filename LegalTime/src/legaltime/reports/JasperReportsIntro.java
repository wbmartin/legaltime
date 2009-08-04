/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import legaltime.ResourceAnchor;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.modelsafe.EasyLog;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;


public class JasperReportsIntro
{
  public  void makeReport()
  {
    
    JasperPrint jasperPrint;
    try
    {
String UserDesktop = System.getProperty("user.home") + "/Desktop";
UserDesktop = UserDesktop.replace("\\", "/");
ClassLoader cl = ResourceAnchor.class.getClassLoader();
InputStream jasperFile = cl.getResourceAsStream("legaltime/reports/BogerInvoice.jasper");
        HashMap params = new HashMap();
        params.put("test", "value");
        JRInvoiceDataSource source = new JRInvoiceDataSource();
        source.createDataSource();
      jasperPrint = JasperFillManager.fillReport(
          jasperFile, params, source.createDataSource());
      JasperExportManager.exportReportToPdfFile(
          jasperPrint, UserDesktop+"/Invoice00123_JoeClient_20090715.pdf");
    }
    catch (JRException e)
    {
        EasyLog.getInstance().addEntry(EasyLog.INFO, "Error Building Report", "Jasper Report Intro", EasyLog.getStackTrace(e));
      e.printStackTrace();
    }
  }

  

}