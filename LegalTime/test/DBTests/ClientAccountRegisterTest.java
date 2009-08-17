/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBTests;

import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.model.ClientAccountRegisterBean;
import legaltime.model.ClientAccountRegisterManager;
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
public class ClientAccountRegisterTest {
    private ClientAccountRegisterBean clientAccountRegisterBean;

    public ClientAccountRegisterTest() {
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
    public void createNewRegisterTest(){
        ClientAccountRegisterManager clientAccountRegisterManager;
        clientAccountRegisterManager = ClientAccountRegisterManager.getInstance();
        clientAccountRegisterBean = clientAccountRegisterManager.createClientAccountRegisterBean();
        clientAccountRegisterBean.setClientId(1);
        clientAccountRegisterBean.setTranAmt(100D);

        try {
            clientAccountRegisterManager.save(clientAccountRegisterBean);
        } catch (DAOException ex) {
            Logger.getLogger(ClientAccountRegisterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }

}