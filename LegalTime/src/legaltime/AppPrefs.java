/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime;

import java.util.prefs.Preferences;

/**
 *
 * @author bmartin
 */

public class AppPrefs {
    private static AppPrefs instance = null;
    private Preferences prefs = Preferences.userNodeForPackage (getClass ());
    public static final String  MYSQL_IP ="MYSQL_IP";
    public static final String  DB_NAME ="DB_NAME";

    public static final String  JDBC_USER ="JDBC_USER";
    public static final String  JDBC_PASSWD ="JDBC_PASSWD";
    public static final String  NOT_SET ="NOT SET";
    protected AppPrefs(){
       
        String temp = prefs.get(MYSQL_IP, NOT_SET);
        if (temp.equals(NOT_SET)){
            prefs.put(MYSQL_IP, "127.0.0.1");
        }
        temp = prefs.get(DB_NAME, NOT_SET);
        if (temp.equals(NOT_SET)){
            prefs.put(DB_NAME, "legal_time");
        }
        temp = prefs.get(JDBC_USER, NOT_SET);
        if (temp.equals(NOT_SET)){
            prefs.put(JDBC_USER, "root");
        }
        temp = prefs.get(JDBC_PASSWD, NOT_SET);
        if (temp.equals(NOT_SET)){
            //changed to prompt user on startup if not setup. 2009-08-04 WBM
            prefs.put(JDBC_PASSWD, NOT_SET);
        }
        

    }
    public String getJDBC_URL(){
        String jdbcUrl ="";
        jdbcUrl = "jdbc:mysql://" + prefs.get(MYSQL_IP, NOT_SET)
                + ":3306/" + prefs.get(DB_NAME, NOT_SET);
        return jdbcUrl;
    }
    public static AppPrefs getInstance(){
        if(instance == null){
            instance = new AppPrefs();
        }
        return instance;
    }

    /**
     * @return the prefs
     */
    public Preferences getPrefs() {
        return prefs;
    }

    public String getValue(String key_){
        String ret=prefs.get(key_, NOT_SET);
        return ret;
    }


    

}