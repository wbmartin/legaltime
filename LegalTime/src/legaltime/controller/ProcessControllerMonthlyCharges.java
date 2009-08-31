/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.model.ClientBean;
import legaltime.model.ClientManager;
import legaltime.model.LaborRegisterBean;
import legaltime.model.LaborRegisterManager;
import legaltime.model.Manager;
import legaltime.model.exception.DAOException;

/**
 *
 * @author bmartin
 */
public class ProcessControllerMonthlyCharges {
    public ProcessControllerMonthlyCharges(){

    }

    public void assessMonthlyRetainers(java.util.Date effectiveDate_){
        ClientManager clientManager ;
        ClientBean[] clients = null;
        clientManager = ClientManager.getInstance();
        LaborRegisterBean laborRegisterBean;
        LaborRegisterManager laborRegisterManager;
        laborRegisterManager = LaborRegisterManager.getInstance();

        try {
             clients = clientManager.loadByWhere("where bill_type ='MONTHLY' " +
                     " and monthly_bill_rate >0 and active_yn ='Y'");
        } catch (DAOException ex) {
            Logger.getLogger(ProcessControllerMonthlyCharges.class.getName()).log(Level.SEVERE, null, ex);
        }
        Manager manager = Manager.getInstance();
        try {
            manager.beginTransaction();

            for(int ndx =0; ndx<clients.length;ndx++){
                laborRegisterBean = laborRegisterManager.createLaborRegisterBean();
                laborRegisterBean.setClientId(clients[ndx].getClientId());
                laborRegisterBean.setActivityDate(effectiveDate_);
                laborRegisterBean.setBillRate(clients[ndx].getMonthlyBillRate());
                laborRegisterBean.setMinutes(60);
                laborRegisterBean.setDescription("Monthly Retainer Fee");
                laborRegisterBean.setInvoiceable(true);
                laborRegisterManager.save(laborRegisterBean);
            }
            manager.endTransaction(true);
            } catch (SQLException ex) {
            Logger.getLogger(ProcessControllerMonthlyCharges.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
