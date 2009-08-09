/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.cache.ClientCache;
import legaltime.model.ClientAccountRegisterBean;
import legaltime.model.ClientBean;
import legaltime.model.ExpenseInvoiceItemBean;
import legaltime.model.ExpenseInvoiceItemManager;
import legaltime.model.InvoiceBean;
import legaltime.model.InvoiceManager;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import legaltime.model.exception.DAOException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author bmartin
 */
public class JRInvoiceDataSource   {
    LaborInvoiceItemManager laborInvoiceItemManager;
public JRInvoiceDataSource(){
    
}
   public JRDataSource createDataSource(){

       return new JRBeanCollectionDataSource(createBeanCollection());
   }

   public JRDataSource createExpenses(){

       return new JRBeanCollectionDataSource(getExpenseBeans());
   }

   public java.util.HashMap getParams(){
       InvoiceManager invoiceManager = InvoiceManager.getInstance();
       InvoiceBean invoiceBean = invoiceManager.createInvoiceBean();
       ClientCache clientCache = ClientCache.getInstance();
        try {
            invoiceBean = invoiceManager.loadByWhere("where invoice_id = 3")[0];
        } catch (DAOException ex) {
            Logger.getLogger(JRInvoiceDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
       ClientBean clientBean = clientCache.getBeanById(invoiceBean.getClientId());
      

       java.util.HashMap params = new java.util.HashMap();
        params.put("InvoiceDate", invoiceBean.getInvoiceDt());
        params.put("ClientName", clientBean.getFirstName() +" "+ clientBean.getLastName());
        params.put("Expenses", createExpenses());
        return params;
   }

   public static ArrayList getExpenseBeans(){
       ExpenseInvoiceItemManager expenseInvoiceItemManagernvoice =
               ExpenseInvoiceItemManager.getInstance();
       ExpenseInvoiceItemBean[] beans = new ExpenseInvoiceItemBean[] {};
        try {
            beans = expenseInvoiceItemManagernvoice.loadByWhere("where invoice_id =3");
        } catch (DAOException ex) {
            Logger.getLogger(JRInvoiceDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
       return new ArrayList(java.util.Arrays.asList(beans));

   }

   public static ArrayList createBeanCollection(){
      LaborInvoiceItemManager laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
      LaborInvoiceItemBean bean = laborInvoiceItemManager.createLaborInvoiceItemBean();
      ArrayList<LaborInvoiceItemBean> laborInvoiceItems;
      LaborInvoiceItemBean[] beanList = null;
        try {
            beanList = laborInvoiceItemManager.loadByWhere("where invoice_id=3");
        } catch (DAOException ex) {
            Logger.getLogger(JRInvoiceDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }

      laborInvoiceItems = new ArrayList(java.util.Arrays.asList(beanList));;
      return laborInvoiceItems;
      //beans[0] = laborInvoiceItemManager.createLaborInvoiceItemBean();
      //beans[0].setActivityDate(new Date());
//      bean.setActivityDate(new Date());
//      bean.setActivityDescription("test");
//      bean.setBillRate(300D);
//      bean.setHoursBilled(1D);
//      bean.setInvoiceId(1);
//      bean.setLaborInvoiceItemId(1);
//
//
//        coll.add(bean);
//        LaborInvoiceItemBean bean2 = laborInvoiceItemManager.createLaborInvoiceItemBean();
//      //beans[0] = laborInvoiceItemManager.createLaborInvoiceItemBean();
//      //beans[0].setActivityDate(new Date());
//      bean2.setActivityDate(new Date());
//      bean2.setActivityDescription("test");
//      bean2.setBillRate(400D);
//      bean2.setHoursBilled(1D);
//      bean2.setInvoiceId(1);
//      bean2.setLaborInvoiceItemId(1);
//      coll.add(bean2);
//      return coll;

  }

}
