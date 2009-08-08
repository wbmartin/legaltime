/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.modelsafe;

import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.AppPrefs;
import legaltime.cache.ClientBillRateCache;
import legaltime.cache.ClientCache;
import legaltime.cache.UserInfoCache;
import legaltime.model.ClientBillRateManager;
import legaltime.model.ClientManager;
import legaltime.model.Manager;
import legaltime.model.UserInfoManager;
import legaltime.model.exception.DAOException;

/**
 *
 * @author bmartin
 */
 public class PersistanceManager {
    static PersistanceManager instance=null;
    ClientCache clientCache;
    ClientManager clientManager;
    UserInfoCache userInfoCache;
    UserInfoManager userInfoManager;
    ClientBillRateCache clientBillRateCache;
    ClientBillRateManager clientBillRateManager;
    AppPrefs appPrefs;
    EasyLog easyLog;

    protected PersistanceManager(){
        Manager manager = Manager.getInstance();
        easyLog = EasyLog.getInstance();
        appPrefs =AppPrefs.getInstance();
        manager.setJdbcUrl(appPrefs.getJDBC_URL());
        manager.setJdbcUsername(appPrefs.getValue(AppPrefs.JDBC_USER));
        manager.setJdbcPassword(appPrefs.getValue(AppPrefs.JDBC_PASSWD));
        clientCache = ClientCache.getInstance();
        clientManager = ClientManager.getInstance();
       

        userInfoCache = UserInfoCache.getInstance();
        userInfoManager = UserInfoManager.getInstance();
        

        clientBillRateCache =ClientBillRateCache.getInstance();
        clientBillRateManager = ClientBillRateManager.getInstance();
        
        
        

    }
    public static PersistanceManager getInstance(){
         if (instance == null){
            instance = new PersistanceManager();

        }

        return instance;
    }

  public void loadCache(){
      loadClientBillRateCache();
       loadClientCache();
       loadUserInfoCache();
  }

    public DatabaseResult loadClientCache(){
        DatabaseResult result = DatabaseResult.PendingAction;

        try {
            clientCache.setList(clientManager.loadAll());
            result =DatabaseResult.Success;
        } catch (DAOException ex) {
            easyLog.addEntry(EasyLog.SEVERE, "Load Client Cache Failed",
                    this.getClass().getName(),ex);
            result = DatabaseResult.SelectFailed;
        }
        return result;

    }

    public DatabaseResult loadUserInfoCache(){
        DatabaseResult result = DatabaseResult.PendingAction;

        try {
            userInfoCache.setList(userInfoManager.loadAll());
            result =DatabaseResult.Success;
        } catch (DAOException ex) {
            easyLog.addEntry(EasyLog.SEVERE, "Load UserInfo Cache Failed",
                this.getClass().getName(),ex);
            result = DatabaseResult.SelectFailed;
        }
        return result;

    }
    
    public DatabaseResult loadClientBillRateCache(){
        DatabaseResult result = DatabaseResult.PendingAction;

        try {
            clientBillRateCache.setList(clientBillRateManager.loadAll());
            result =DatabaseResult.Success;
        } catch (DAOException ex) {
            Logger.getLogger(PersistanceManager.class.getName()).log(Level.SEVERE, null, ex);
            result = DatabaseResult.SelectFailed;
        }
        return result;

    }





}
