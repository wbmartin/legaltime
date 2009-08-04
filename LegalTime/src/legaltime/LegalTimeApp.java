/*
 * LegalTimeApp.java
 */

package legaltime;

import legaltime.view.LegalTimeView;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class LegalTimeApp extends SingleFrameApplication {
LegalTimeView legalTimeView;

    /**
     * At startup create and show the main frame of the application.
     */
 /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        
        launch(LegalTimeApp.class, args);
    }

    @Override protected void startup() {
        legalTimeView =  new LegalTimeView(this);
        show(legalTimeView);
    }
    public void hideMainWindow(){
        legalTimeView.getFrame().setVisible(false);
    }
    public void showMainWindow(){
        legalTimeView.getFrame().setVisible(true);
    }


    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of LegalTimeApp
     */
    public static LegalTimeApp getApplication() {
        return Application.getInstance(LegalTimeApp.class);
    }

     public void setStatusText(String newText){
        legalTimeView.setStatusText(newText);

    }
    public void setLastActionText(String newText){
        legalTimeView.setLastActionText( newText);
    }
    public void setProgressBarValue(int value){
        legalTimeView.setProgressBarProgressValue(value);
    }

   
}
