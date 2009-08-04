/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBTests;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.modelsafe.PersistanceManager;
import legaltime.model.ClientBean;
import legaltime.model.ClientManager;
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
public class BasicConnectivityTest {
    PersistanceManager pm = PersistanceManager.getInstance();

    public BasicConnectivityTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


    @Test
    public void testTrue(){
        System.out.println("Testing the test");
        assertEquals(1,1);
    }
    @Test
    public void testClass(){
        System.out.println("Testing the Class");
        assertEquals(pm.test(),1);
    }

    @Test
    public void testClientInsert(){

        ClientManager clientManager = ClientManager.getInstance();
        ClientBean clientBean = clientManager.createClientBean();
        clientBean.setActiveYn("Y");
        clientBean.setAddress("107 Oak Trail");
        clientBean.setCellPhone("(803) 781-0000");
        clientBean.setCity("Columbia");
        clientBean.setEmail("person.name@company.com");
        clientBean.setClientSinceDt(new Date());
        clientBean.setFax("(803) 781-0001");
        clientBean.setFirstName("Joe");
        clientBean.setLastName("Customer");
        clientBean.setHomePhone("(803) 781-0001");
        clientBean.setNote("A lot of text");
        clientBean.setZip("29710-0000");
        clientBean.setState("SC");
        clientBean.setWorkPhone("(803) 781-0003");
        ClientBean clientBeanCompare=null;
        try {
            clientBean = clientManager.save(clientBean);
        } catch (DAOException ex) {
            Logger.getLogger(BasicConnectivityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clientBeanCompare = clientManager.loadByPrimaryKey(Integer.valueOf(clientBean.getClientId()));
        } catch (DAOException ex) {
            Logger.getLogger(BasicConnectivityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Testing Insert for Client");
        assertEquals(clientBean.compareTo(clientBeanCompare),1);

    }


}