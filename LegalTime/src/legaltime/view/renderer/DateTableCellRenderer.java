/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.renderer;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bmartin
 */
 public class DateTableCellRenderer extends DefaultTableCellRenderer {
    SimpleDateFormat formatter;
    static DateTableCellRenderer instance=null;
    protected DateTableCellRenderer() { super();

    formatter = new SimpleDateFormat("MM/dd/yy");}

    public static DateTableCellRenderer getInstance(){
        if (instance == null){
            instance = new DateTableCellRenderer();
        }
        return instance;
    }
    @Override
    public void setValue(Object value) {
        
        try{
            setText((value == null) ? "" : formatter.format(value));
            setHorizontalAlignment(SwingConstants.RIGHT);
        }catch(Exception e){
            setText("");
        }
    }
}
