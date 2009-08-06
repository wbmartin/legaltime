/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.modelsafe;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author bmartin
 */
public class EasyLog {
    private ArrayList<LogEvent> log;
    private static EasyLog instance = null;
    public static final int SEVERE =9;
    public static final int WARN =5;
    public static final int INFO =1;
    protected EasyLog(){
        log= new ArrayList<LogEvent>();
    }
    public static EasyLog getInstance(){
        if(instance ==null){
            instance = new EasyLog();
        }

        return instance;
    }
    public void addEntry(int logLevel_, String description_, String location_,String detail_){
        log.add(new LogEvent(logLevel_, description_, location_, detail_));
    }
    public void addEntry(int logLevel_, String description_, String location_,Throwable detail_){
        log.add(new LogEvent(logLevel_, description_, location_, getStackTrace(detail_)));
    }
    public int getEntryCount(){
        return log.size();
    }
    public LogEvent getLogEvent(int ndx_){
        return log.get(ndx_);
    }
    public void clearLog(){
        log.clear();
    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }




}
