/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author bmartin
 */
public class ClientManagerController implements ActionListener, ListSelectionListener{

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //System.out.println(e.get);
    }

     public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
               
                return;
            }
             System.out.println(event.getFirstIndex());

        }

}
