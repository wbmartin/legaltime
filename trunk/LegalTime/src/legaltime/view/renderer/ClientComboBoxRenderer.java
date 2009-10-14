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
import javax.swing.border.EmptyBorder;
import legaltime.model.ClientBean;

/**
 *
 * @author bmartin
 */
public class ClientComboBoxRenderer extends JLabel implements ListCellRenderer{
ClientBean clientBean;
protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    public ClientComboBoxRenderer() {

//        setOpaque(false);
//        setAlignmentX(0F);
//        setAlignmentY(0F);
//        setHorizontalAlignment(LEADING);
//        setHorizontalTextPosition(TRAILING);
//        setIconTextGap(4);
//        setVerticalAlignment(CENTER);
//        setVerticalTextPosition(CENTER);
//
//        setBorder( javax.swing.BorderFactory.createEmptyBorder());
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       JLabel renderer = (JLabel) defaultRenderer
        .getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

       String result ="";
        //int selectedIndex = ((Integer)value).intValue();
try{
        clientBean = (ClientBean)value;
}catch(ClassCastException e){
//    e.printStackTrace();
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
            
            if (clientBean.getFirstName() == null){
                result = clientBean.getLastName();
            } else{
                result = clientBean.getLastName() + ", " +clientBean.getFirstName() ;
            }

            renderer.setText(result );
        }catch(NullPointerException nex){
            renderer = new JLabel();
            renderer.setVisible(false);
            renderer.setText(null);
            
        }
        

        return renderer;
    }

}
