/*
 * LegalTimeApp.java
 */

package legaltime;

import javax.swing.JDesktopPane;
import legaltime.controller.LegalTimeController;
import legaltime.view.LegalTimeView;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class LegalTimeApp extends SingleFrameApplication {

public static final String APP_VERSION = "0.0.2.5";
static LegalTimeController legalTimeController;
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
        
        legalTimeController = LegalTimeController.getInstance(this);
        show(legalTimeController.getLegalTimeView());
        legalTimeController.manageUpdates();
        legalTimeController.loadCache();
    }
    public void hideMainWindow(){
        legalTimeController.getLegalTimeView().getFrame().setVisible(false);
    }
    public void showMainWindow(){
        legalTimeController.getLegalTimeView().getFrame().setVisible(true);
    }
    public LegalTimeView getPrimaryView(){
        return legalTimeController.getLegalTimeView();
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
        legalTimeController.getLegalTimeView().setStatusText(newText);

    }
    public void setLastActionText(String newText){
        legalTimeController.getLegalTimeView().setLastActionText( newText);
    }
    public void setProgressBarValue(int value){
        legalTimeController.getLegalTimeView().setProgressBarProgressValue(value);
    }

    public JDesktopPane getDesktop(){
        return legalTimeController.getLegalTimeView().getDesktop();
    }

   
}
