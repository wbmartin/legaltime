/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

import java.util.Date;
import java.util.Vector;
import legaltime.model.LaborInvoiceItemBean;
import legaltime.model.LaborInvoiceItemManager;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author bmartin
 */
public class JRInvoiceDataSource   {
public JRInvoiceDataSource(){
    
}
   public JRDataSource createDataSource(){

       return new JRBeanCollectionDataSource(createBeanCollection());
   }

   public static Vector createBeanCollection(){
        java.util.Vector coll = new java.util.Vector();
      //LaborInvoiceItemBean[] beans = new LaborInvoiceItemBean[1];
      LaborInvoiceItemManager laborInvoiceItemManager = LaborInvoiceItemManager.getInstance();
      LaborInvoiceItemBean bean = laborInvoiceItemManager.createLaborInvoiceItemBean();
      //beans[0] = laborInvoiceItemManager.createLaborInvoiceItemBean();
      //beans[0].setActivityDate(new Date());
      bean.setActivityDate(new Date());
      bean.setActivityDescription("test");
      bean.setBillRate(300D);
      bean.setHoursBilled(1D);
      bean.setInvoiceId(1);
      bean.setLaborInvoiceItemId(1);


        coll.add(bean);
        LaborInvoiceItemBean bean2 = laborInvoiceItemManager.createLaborInvoiceItemBean();
      //beans[0] = laborInvoiceItemManager.createLaborInvoiceItemBean();
      //beans[0].setActivityDate(new Date());
      bean2.setActivityDate(new Date());
      bean2.setActivityDescription("test");
      bean2.setBillRate(400D);
      bean2.setHoursBilled(1D);
      bean2.setInvoiceId(1);
      bean2.setLaborInvoiceItemId(1);
      coll.add(bean2);
      return coll;

  }

}
