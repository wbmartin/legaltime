/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
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
protected DefaultListCellRenderer defaultRenderer ;
    public UserInfoComboBoxRenderer() {
        defaultRenderer = new DefaultListCellRenderer();
//        setOpaque(false);
//        setAlignmentX(0F);
//        setAlignmentY(0F);
//        setHorizontalAlignment(LEADING);
//        setHorizontalTextPosition(TRAILING);
//        setIconTextGap(4);
//        setVerticalAlignment(CENTER);
//        setVerticalTextPosition(CENTER);
//
//
//        setBorder( javax.swing.BorderFactory.createEmptyBorder());
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer
            .getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        try{
                bean = (UserInfoBean)value;
        }catch(ClassCastException e){
        //    e.printStackTrace();
            //No idea why it balks at casting an object to a bean when it works
        }

        if (isSelected) {
            renderer.setBackground(list.getSelectionBackground());
            renderer.setForeground(list.getSelectionForeground());
            renderer.setForeground(Color.BLACK);
        } else {
            renderer.setBackground(list.getBackground());
            renderer.setForeground(list.getForeground());
            
        }


        try{
            renderer.setText(bean.getFirstName());
        }catch(NullPointerException nex){
            renderer.setText("");

        }

        return renderer;
    }

}
