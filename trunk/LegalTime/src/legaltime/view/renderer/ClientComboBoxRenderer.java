/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import legaltime.model.ClientBean;

/**
 *
 * @author bmartin
 */
public class ClientComboBoxRenderer extends JLabel implements ListCellRenderer{
ClientBean clientBean;
    public ClientComboBoxRenderer() {
        setOpaque(false);
        setAlignmentX(0F);
        setAlignmentY(0F);
        setHorizontalAlignment(LEADING);
        setHorizontalTextPosition(TRAILING);
        setIconTextGap(4);
        setVerticalAlignment(CENTER);
        setVerticalTextPosition(CENTER);

        setBorder( javax.swing.BorderFactory.createEmptyBorder());
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //int selectedIndex = ((Integer)value).intValue();
try{
        clientBean = (ClientBean)value;
}catch(ClassCastException e){
//    e.printStackTrace();
}

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            setForeground(Color.BLACK);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        try{
            setText(clientBean.getLastName() +", "+ clientBean.getFirstName());
        }catch(NullPointerException nex){
            setText("");
        }
        

        return this;
    }

}
