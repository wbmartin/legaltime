/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.DBManager;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.TextUtils;
import legaltime.model.Manager;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class SQLHandler {
    private EasyLog easyLog;
    private StringBuffer results;
    private Connection con;
    private static SQLHandler instance= null;
    protected SQLHandler() {
        easyLog = EasyLog.getInstance();
        results = new StringBuffer();

         try {
            con = Manager.getInstance().getConnection();


        } catch (SQLException ex) {
            Logger.getLogger(VersionManager.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.INFO, "Error Gettting Connection", getClass().getName(), ex);
        }
    }
    public static SQLHandler getInstance(){
        if (instance == null){
            instance = new SQLHandler();
        }
        return instance;
    }

    public String getResults(){
        return results.toString();
    }


    public boolean executeCommands(String text){
        boolean success = false;
        StringBuffer sqlToExecute = new StringBuffer();
        String line;
        String finalSQL;
        BufferedReader br;
        int ndx=0;
        int updateCount=0;
        ArrayList<String> sqlCommands = new ArrayList<String>();
        results.append("\n------Begin Batch----------\n");
        results.append(text);
        results.append("\n------End   Batch----------\n");

        String[] lines =  text.split("\n");

        try{

              for(ndx = 0; ndx< lines.length; ndx++) {
                  line = lines[ndx];
                  if (!line.contains("--") && line.length()>0){
                    sqlToExecute.append(" " + line);
                  }
                 if(line.contains(";")){
                     sqlCommands.add(sqlToExecute.toString());
                     sqlToExecute.setLength(0);
                 }
              }

        }catch(NullPointerException e){
            easyLog.addEntry(EasyLog.SEVERE, "Error: Null Pointer exception parsing lines for SQL Batch",
                    this.getClass().getName(), "");
        }


        PreparedStatement ps=null;

        try {
//            finalSQL=sqlToExecute.toString();
//            System.out.println(finalSQL);
            con.setAutoCommit(false);
            for(ndx =0;ndx <sqlCommands.size();ndx++){
                if(sqlCommands.get(ndx).equals("")){continue;}
                updateCount=0;
                  results.append("\n-----\nSQLCommand" + sqlCommands.get(ndx)
                          +"\n++++++\n" );
                  ps = con.prepareStatement(sqlCommands.get(ndx));
                  updateCount=ps.executeUpdate();
                  results.append("Update Count: " + updateCount );
            }
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            
            if(sqlCommands.size() >0){success = true;}
        } catch (SQLException ex) {
            results.append("Generated Exception:\n"+ TextUtils.getStackTrace(ex));
            easyLog.addEntry(EasyLog.SEVERE, "Attempting Roll Back User Trans",
                        this.getClass().getName(), ex);
            easyLog.addEntry(EasyLog.SEVERE, "Offending Command Triggering Roll Back User Trans:"
                    +sqlCommands.get(ndx),this.getClass().getName(), ex);
            try {
                con.rollback();
                //System.out.println("Rolling Back DB Upgrade"+patch);
                easyLog.addEntry(EasyLog.SEVERE, "Rolled Back User Trans",
                        this.getClass().getName(), "");
            } catch (SQLException ex1) {
                easyLog.addEntry(EasyLog.SEVERE, "Rolling Back User Trans Failed: ",
                        this.getClass().getName(), ex1);
            }

        }

        return success;
    }


}
