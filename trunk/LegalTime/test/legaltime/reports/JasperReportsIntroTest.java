/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.reports;

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
        instance.makeReport(3);
        // TODO review the generated test code and remove the default call to fail.
        
    }

}