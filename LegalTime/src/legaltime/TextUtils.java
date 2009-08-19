/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author bmartin
 */
public class TextUtils {

     public static String frontZeroFill(Object value_, int charCount_){
      
        StringBuffer value = new StringBuffer(value_.toString());
        for(int ndx =value.length();ndx<charCount_; ndx ++){
            value.insert(0, "0");
        }

        return value.toString();
    }

     public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }


}
