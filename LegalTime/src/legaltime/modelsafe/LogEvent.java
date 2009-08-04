/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.modelsafe;

import java.util.Date;

/**
 *
 * @author bmartin
 */
public class LogEvent {
    private Date dateOccured;
    private int logLevel;
    private String description;
    private String location;
    private String details;

    public LogEvent(){
        dateOccured = new Date();
        logLevel = 0;
        description ="Not Set";
        location ="Not Set";
        details ="Not Set";
    }

    public LogEvent(int logLevel_, String description_, String location_, String details_){
        this();
        logLevel=logLevel_;
        description=description_;
        location = location_;
        details =details_;
    }

    /**
     * @return the dateOccured
     */
    public Date getDateOccured() {
        return dateOccured;
    }

    /**
     * @return the logLevel
     */
    public int getLogLevel() {
        return logLevel;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }


}
