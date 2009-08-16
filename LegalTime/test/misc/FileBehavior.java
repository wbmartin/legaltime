/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

import java.io.File;
import legaltime.TextUtils;
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
public class FileBehavior {

    public FileBehavior() {
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
    public void DirectoryTest(){
        java.util.Date now = new java.util.Date(10000000);
        String path = "C:\\Test"+ Integer.toString(1900+now.getYear())
                +TextUtils.frontZeroFill( Integer.toString(now.getMonth()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getDay()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getHours()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getMinutes()),2)
                +TextUtils.frontZeroFill( Integer.toString(now.getSeconds()),2) ;
        File dir = new File(path);
        boolean success =dir.exists();
        if(!success){
            success =(dir).mkdir();
        }
         
        assertEquals(true,success);

    }

}