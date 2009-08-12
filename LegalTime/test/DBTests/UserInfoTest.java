/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBTests;

import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.model.UserInfoBean;
import legaltime.model.UserInfoManager;
import legaltime.model.exception.DAOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bmartin
 */
public class UserInfoTest {

    public UserInfoTest() {
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
     @Test
     public void testInsert() {
         UserInfoManager userInfoManager = UserInfoManager.getInstance();

     UserInfoBean userInfoBean = userInfoManager.createUserInfoBean();
     
     userInfoBean.setFirstName("Brian");
     userInfoBean.setLastName("Boger");
     userInfoBean.setUserKey("brian");
        try {
            userInfoManager.save(userInfoBean);
        } catch (DAOException ex) {
            Logger.getLogger(UserInfoTest.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error Saving UserInfo", getClass().getName(), ex);
        }

     userInfoBean = userInfoManager.createUserInfoBean();
     
     userInfoBean.setFirstName("Clerk");
     userInfoBean.setLastName("");
     userInfoBean.setUserKey("clerk");
        try {
            userInfoManager.save(userInfoBean);
        } catch (DAOException ex) {
            Logger.getLogger(UserInfoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

     }

}