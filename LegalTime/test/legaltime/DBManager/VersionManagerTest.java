/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.DBManager;

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
public class VersionManagerTest {

    public VersionManagerTest() {
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
     * Test of getDBVersion method, of class VersionManager.
     */
    @Test
    public void testGetDBVersion() throws Exception {
        System.out.println("getDBVersion");
        VersionManager instance = new VersionManager();
        
        String result = instance.getDBVersion();
        //assertEquals(null, result);
        instance.installAllDbPatches();

    }

    @Test
    public void testDBBackup() throws Exception{
        System.out.println("Testing Backup");
        VersionManager instance = new VersionManager();

        String result = instance.getDBVersion();
        //assertEquals(null, result);
        instance.backupDatabase();

    }



}