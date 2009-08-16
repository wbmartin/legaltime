/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime;

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

}
