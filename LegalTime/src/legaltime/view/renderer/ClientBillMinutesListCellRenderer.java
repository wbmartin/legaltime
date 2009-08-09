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

/**
 *
 * @author bmartin
 */
public class ClientBillMinutesListCellRenderer extends JLabel implements ListCellRenderer {

protected DefaultListCellRenderer defaultRenderer;




     public ClientBillMinutesListCellRenderer() {
         defaultRenderer = new DefaultListCellRenderer ();
         
     }
     public Component getListCellRendererComponent(
         JList list,
         Object value,
         int index,
         boolean isSelected,
         boolean cellHasFocus)
     {
         JLabel renderer = (JLabel) defaultRenderer
        .getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         if (Integer.parseInt(value.toString()) > 9){
            renderer.setText( value.toString() +" ");
         }
         else{
             renderer.setText( value.toString() );
         }
         //setBackground(isSelected ? Color.red : Color.white);
         //setForeground(isSelected ? Color.white : Color.black);
         return renderer;
     }




}
