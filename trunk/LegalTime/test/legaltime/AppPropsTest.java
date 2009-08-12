/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime;

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
public class AppPropsTest {

    public AppPropsTest() {
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
     * Test of getInstance method, of class AppProps.
     */
  
    /**
     * Test of read method, of class AppProps.
     */
  

    /**
     * Test of write method, of class AppProps.
     */
//    @Test
//    public void testWrite() {
//        System.out.println("write");
//        AppProps instance = new AppProps();
//        instance.write();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testSetEBackupPath(){
        AppPrefs appPrefs = AppPrefs.getInstance();
        appPrefs.getPrefs().put(AppPrefs.EBACKUP_PATH,AppPrefs.NOT_SET );
    }


}