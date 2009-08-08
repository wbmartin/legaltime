/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.DBManager;

import com.mysql.jdbc.ResultSetMetaData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.AppPrefs;
import legaltime.ResourceAnchor;
import legaltime.model.Manager;
import legaltime.modelsafe.EasyLog;

/**
 *
 * @author bmartin
 */
public class VersionManager {
    private Connection con;
    AppPrefs appPrefs;
    String version="";
    String verstionRequired ="DB-0.0.0.1";
    EasyLog easyLog;
    public static final String  NEW_VERSION_INSTALLED ="NEW_VERSION_INSTALLED";
    public static final String  NEW_VERSION_FAILED ="NEW_VERSION_FAILED";
    public static final String  NO_NEW_VERSION ="NO_NEW_VERSION";
    public VersionManager(){
        appPrefs = AppPrefs.getInstance();
        easyLog = EasyLog.getInstance();

        try {
            con = Manager.getInstance().getConnection();
            getDBVersion();

        } catch (SQLException ex) {
            Logger.getLogger(VersionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDBVersion() {
        String verSQL = "select description from sys_code where code_id ='DBVer';";
        version = null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        try{
            ps = con.prepareStatement(verSQL);
            rs = ps.executeQuery();

            if (rs.next()){
                version = rs.getString("description");
            }else{
                version = "DB-0.0.0.1";
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
            version = "DB-0.0.0.0";
        }

        return version;
    }

    public String installAllDbPatches(){
        String patchInstalled =NO_NEW_VERSION;
        getDBVersion();
        if (version.equals("DB-0.0.0.0") && backupDatabase()
                 && !patchInstalled.equals(NEW_VERSION_FAILED)){
            if(applyPatch("DB-0.0.0.1")){
                patchInstalled =NEW_VERSION_INSTALLED;
            }else{
                patchInstalled =NEW_VERSION_FAILED;
            }
            
        }
        if (version.equals("DB-0.0.0.1") && backupDatabase()
                && !patchInstalled.equals(NEW_VERSION_FAILED)){
            if(applyPatch("DB-0.0.0.2")){
              patchInstalled =NEW_VERSION_INSTALLED;
            }else{
               patchInstalled =NEW_VERSION_FAILED;
            }
            
        }

        return patchInstalled;
    }

    public boolean applyPatch(String patch){
        boolean success = false;
        StringBuffer sqlToExecute = new StringBuffer();
        String line;
        String finalSQL;
        BufferedReader br;
        int ndx=0;
        ArrayList<String> sqlCommands = new ArrayList<String>();
        ClassLoader cl = ResourceAnchor.class.getClassLoader();
        InputStream sqlTextStream = cl.getResourceAsStream(
                "legaltime/DBManager/SQLUpgrades/" + patch + ".sql");
        try{
             br = new BufferedReader(new InputStreamReader(sqlTextStream));
              while (null != (line = br.readLine())) {
                  if (!line.contains("--") && line.length()>0){
                    sqlToExecute.append(line);
                  }
                 if(line.contains(";")){
                     sqlCommands.add(sqlToExecute.toString());
                     sqlToExecute.setLength(0);
                 }
              }

        }catch(java.io.IOException e){
            easyLog.addEntry(EasyLog.SEVERE, "Error: Reading File While Applying Patch",
                    this.getClass().getName(), EasyLog.getStackTrace(e));
        }catch(NullPointerException e){
            easyLog.addEntry(EasyLog.SEVERE, "Error: SQL Patch File Note Found",
                    this.getClass().getName(), patch);
        }

        
        PreparedStatement ps=null;

        try {
            finalSQL=sqlToExecute.toString();
            System.out.println(finalSQL);
            con.setAutoCommit(false);
            for(ndx =0;ndx <sqlCommands.size();ndx++){
                if(sqlCommands.get(ndx).equals("")){continue;}
               ps = con.prepareStatement(sqlCommands.get(ndx));
               ps.execute();
            }
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            version = patch;
            if(sqlCommands.size() >0){success = true;}
        } catch (SQLException ex) {
            easyLog.addEntry(EasyLog.SEVERE, "Attempting Roll Back DB Upgrade"+patch,
                        this.getClass().getName(), ex);
            easyLog.addEntry(EasyLog.SEVERE, "Offending Command Triggering Roll Back DB Upgrade:"
                    +sqlCommands.get(ndx),this.getClass().getName(), ex);
            try {
                con.rollback();
                //System.out.println("Rolling Back DB Upgrade"+patch);
                easyLog.addEntry(EasyLog.SEVERE, "Rolled Back DB Upgrade"+patch,
                        this.getClass().getName(), "");
            } catch (SQLException ex1) {
                easyLog.addEntry(EasyLog.SEVERE, "Rolling Back DB Upgrade Failed: "+patch,
                        this.getClass().getName(), ex1);
            }
            
        }

        return success;
    }

    @SuppressWarnings("empty-statement")
    public boolean backupDatabase(){
        ArrayList<String> tables = new ArrayList<String>();
        Statement s;
        ResultSet rs;
        boolean result = false;
        String sql = "show tables;";
         ResultSetMetaData rsMetaData;
        int numberOfColumns ;
        StringBuffer writeLine= new StringBuffer();
        java.util.Date now = new java.util.Date();
        FileWriter fstream = null;
        BufferedWriter out = null;

        if(appPrefs.getValue(AppPrefs.EBACKUP_PATH).equals(AppPrefs.NOT_SET)){
            return false;
        }
        String filePathPrefix = appPrefs.getValue(AppPrefs.EBACKUP_PATH)
                +"\\"+ Integer.toString(1900+now.getYear())
                +"_"+ Integer.toString(now.getMonth())
                +"_"+ Integer.toString(now.getDay())
                +"_"+ Integer.toString(now.getHours())
                +"_"+ Integer.toString(now.getMinutes())
                +"_"+ Integer.toString(now.getSeconds()) +"_" ;
        

        try {
            s = con.createStatement();
            String[] type = {"TABLE"};
            rs = con.getMetaData().getTables(appPrefs.getValue(AppPrefs.DB_NAME), null, null, type );
            String tableSql="";
            while (rs.next()){
                tables.add(rs.getString(3));
                //System.out.println(rs.getString(3));
            }
            rs.close();
            for(int ndx=0; ndx < tables.size();ndx++ ){
                tableSql = "select * from " + tables.get(ndx) +";";
                rs = s.executeQuery(tableSql);
                rsMetaData = (ResultSetMetaData) rs.getMetaData();
                numberOfColumns = rsMetaData.getColumnCount();
                writeLine.setLength(0);
                for (int i = 1; i < numberOfColumns + 1; i++) {
                   writeLine.append(rsMetaData.getColumnName(i) +"|");
                }
                fstream = new FileWriter(filePathPrefix + tables.get(0) +".txt");
                out = new BufferedWriter(fstream);
                
                out.write(writeLine.toString());
                out.newLine();
                //System.out.print(writeLine.toString());
                writeLine.setLength(0);
                while (rs.next()){
                    for(int colNdx=1;colNdx<numberOfColumns+1;colNdx++){
                        writeLine.append( rs.getObject(colNdx).toString() + "|");
                    }
                    
                    out.write(writeLine.toString());
                    out.newLine();
                    //System.out.print(writeLine.toString());
                    writeLine.setLength(0);
                }
                out.close();
                fstream.close();
                rs.close();

            }
            
            result = true;
        } catch (SQLException ex) {
            easyLog.addEntry(EasyLog.SEVERE, "Failed E DB Backup",
                        this.getClass().getName(), ex);
            result = false;
        } catch(java.io.IOException e){
            result =false;
        }

        return result;

    }



}