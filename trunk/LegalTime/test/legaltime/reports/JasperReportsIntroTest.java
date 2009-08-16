/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
import legaltime.model.exception.DAOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bmartin
 */
public class JasperReportsIntroTest {

    public JasperReportsIntroTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of makeReport method, of class JasperReportsIntro.
     */
    @Test
    public void testMakeReport() {
        System.out.println("makeReport");
        InvoiceReport instance = new InvoiceReport();
        //instance.makeReport(3);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    @Test
    public void addPayment(){
        PaymentLogManager paymentLogManager = PaymentLogManager.getInstance();
        PaymentLogBean bean = paymentLogManager.createPaymentLogBean();
        bean.setAmount(100D);
        bean.setClientId(1);
        bean.setDescription("Payment Received");
        bean.setEffectiveDate(new java.util.Date());
        try {
            paymentLogManager.save(bean);
        } catch (DAOException ex) {
            Logger.getLogger(JasperReportsIntroTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}