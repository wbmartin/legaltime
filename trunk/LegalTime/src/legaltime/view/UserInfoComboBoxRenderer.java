/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import legaltime.model.ClientBean;
import legaltime.model.UserInfoBean;

/**
 *
 * @author bmartin
 */
public class UserInfoComboBoxRenderer extends JLabel implements ListCellRenderer{
UserInfoBean bean;
    public UserInfoComboBoxRenderer() {
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
                bean = (UserInfoBean)value;
        }catch(ClassCastException e){
        //    e.printStackTrace();
            //No idea why it balks at casting an object to a bean when it works
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
            setText(bean.getFirstName());
        }catch(NullPointerException nex){
            setText("");

        }

        return this;
    }

}
