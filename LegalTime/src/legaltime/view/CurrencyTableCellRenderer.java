/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bmartin
 */
 public class CurrencyTableCellRenderer extends DefaultTableCellRenderer {
    DecimalFormat formatter;
    static CurrencyTableCellRenderer instance=null;
    protected CurrencyTableCellRenderer() { super(); }

    static CurrencyTableCellRenderer getInstance(){
        if (instance == null){
            instance = new CurrencyTableCellRenderer();
        }
        return instance;
    }
    @Override
    public void setValue(Object value) {
        if (formatter==null) {
            formatter = new DecimalFormat("$#,##0.00");
        }
        try{
            setText((value == null) ? "" : formatter.format(value));
        }catch(Exception e){
            setText("");
        }
    }
}
