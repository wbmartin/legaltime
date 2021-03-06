/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FileChooserView.java
 *
 * Created on Aug 6, 2009, 4:51:26 AM
 */

package legaltime.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import org.jdesktop.application.Action;

/**
 *
 * @author bmartin
 */
public class FileChooserView extends JDialog implements java.awt.event.ActionListener {
    private boolean selectionConfirmed;
    public static final int FILES = JFileChooser.FILES_ONLY;
    public static final int DIRECTORIES = JFileChooser.DIRECTORIES_ONLY;
    public static final int FILES_AND_DIRECTORIES = JFileChooser.FILES_AND_DIRECTORIES;

    /** Creates new form FileChooserView */
    public FileChooserView(Frame owner) {
        super(owner, true);
        initComponents();
        fileChooser.addActionListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(legaltime.LegalTimeApp.class).getContext().getResourceMap(FileChooserView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        fileChooser.setName("fileChooser"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables
    
    public String getSelectedFilePath(){
        return fileChooser.getSelectedFile().getAbsolutePath();
    }
    public java.io.File getSelectedFile(){
        return fileChooser.getSelectedFile();

    }
    /**
     *
     * @param mode_
     * JFileChooser.FILES_ONLY
     * JFileChooser.DIRECTORIES_ONLY
     * JFileChooser.FILES_AND_DIRECTORIES
     */
    public void setFileSelectionMode(int mode_){
        fileChooser.setFileSelectionMode(mode_);
    }
    public boolean isSelectionConfirmed(){
        return selectionConfirmed;
    }

  
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if("ApproveSelection".equals(e.getActionCommand())){
            this.setVisible(false);
            selectionConfirmed = true;

        }else if("CancelSelection".equals(e.getActionCommand())){
             this.setVisible(false);
            selectionConfirmed = false;
        }

    }
}
