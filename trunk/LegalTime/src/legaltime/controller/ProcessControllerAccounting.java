/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import legaltime.model.ClientAccountRegisterBean;
import legaltime.model.ClientAccountRegisterManager;
import legaltime.model.PaymentLogBean;
import legaltime.model.PaymentLogManager;
import legaltime.model.exception.DAOException;
import legaltime.modelsafe.EasyLog;
import legaltime.view.model.PaymentLogTableModel;

/**
 *
 * @author bmartin
 */
public class ProcessControllerAccounting {
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ATTEMPTED = "ATTEMPTED";
    private ClientAccountRegisterManager  clientAccountRegisterManager;
    private PaymentLogManager paymentLogManager;
    private EasyLog easyLog;
    private static ProcessControllerAccounting instance;
    private PaymentLogBean paymentLogBean;

    protected ProcessControllerAccounting() {
         clientAccountRegisterManager = ClientAccountRegisterManager.getInstance();
         paymentLogManager = PaymentLogManager.getInstance();
         easyLog = EasyLog.getInstance();
    }
    public static ProcessControllerAccounting getInstance(){
        if(instance == null){
            instance = new ProcessControllerAccounting();
        }
        return instance;

    }

    public boolean savePaymentLogBean(PaymentLogBean paymentLogBean_) {
        boolean result = false;
       try {

            paymentLogManager.save(paymentLogBean_);
            ClientAccountRegisterBean clientAccountRegisterBean =
                    clientAccountRegisterManager.createClientAccountRegisterBean();
            clientAccountRegisterBean.isNew(false);
            clientAccountRegisterBean.setClientAccountRegisterId(
                    paymentLogBean_.getClientAccountRegisterId());
            clientAccountRegisterBean.setClientId(paymentLogBean_.getClientId());
            clientAccountRegisterBean.setDescription(paymentLogBean_.getDescription());
            clientAccountRegisterBean.setEffectiveDate(paymentLogBean_.getEffectiveDate());
            clientAccountRegisterBean.setTranAmt(-1D*paymentLogBean_.getAmount());
            clientAccountRegisterBean.setTranType("PAY");

            clientAccountRegisterManager.save(clientAccountRegisterBean);

            result =true;
        } catch (DAOException ex) {
            Logger.getLogger(PaymentLogTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Updating Payment Log"
                    ,getClass().getName(),ex);
            result = false;
        }
        return result ;
    }

    public boolean addPaymentLogBean(PaymentLogBean paymentLogBean_){
        boolean result = false;

        ClientAccountRegisterBean clientAccountRegisterBean =
                clientAccountRegisterManager.createClientAccountRegisterBean();
        clientAccountRegisterBean.setClientId(paymentLogBean_.getClientId());
        clientAccountRegisterBean.setDescription(paymentLogBean_.getDescription());
        clientAccountRegisterBean.setEffectiveDate(paymentLogBean_.getEffectiveDate());
        clientAccountRegisterBean.setTranAmt(-1D*paymentLogBean_.getAmount());
        clientAccountRegisterBean.setTranType("PAY");
        try {
            clientAccountRegisterBean = clientAccountRegisterManager.save(clientAccountRegisterBean);
            paymentLogBean_.setClientAccountRegisterId(clientAccountRegisterBean.getClientAccountRegisterId());

            paymentLogManager.save(paymentLogBean_);


        } catch (DAOException ex) {
            Logger.getLogger(PaymentLogTableModel.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE,"Error Adding Payment Log Entry"
                    ,getClass().getName(),ex);
        }

        return result;
    }

     public void reversePaymentLogById(int paymentLogId_){
        try {
            paymentLogBean = paymentLogManager.loadByPrimaryKey(paymentLogId_);
            reversePaymentLog (paymentLogBean);
        } catch (DAOException ex) {
            Logger.getLogger(PaymentLogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     private void reversePaymentLog (PaymentLogBean paymentLogBean_){
         try {
            paymentLogBean = paymentLogBean_;
            PaymentLogBean reversal = paymentLogManager.createPaymentLogBean();
            reversal.copy(paymentLogBean);
            reversal.setAmount(-1D * paymentLogBean.getAmount());
            reversal.setDescription(paymentLogBean.getDescription() +" Reversal");
            reversal.setPaymentLogId(null);
            paymentLogManager.save(reversal);
            reversAccountRegisterTranById(paymentLogBean_.getClientAccountRegisterId(),"SYSTEM");
        } catch (DAOException ex) {
            Logger.getLogger(PaymentLogController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

     public String reversAccountRegisterTranById(int accountRegisterId_, String SystemOrUser){
         String result =RESULT_ATTEMPTED;
        try {
            ClientAccountRegisterBean bean = clientAccountRegisterManager.loadByPrimaryKey(accountRegisterId_);
            ClientAccountRegisterBean reversal = clientAccountRegisterManager.createClientAccountRegisterBean();
            if(SystemOrUser.equals("USER") && bean.getTranType().equals("PAY")){
                 result = "Payments may only be reversed by reverseing thePayment.";
                 easyLog.addEntry(EasyLog.INFO, "User attempted payment reversal on account register"
                    , getClass().getName(), "");
                return result;
            }
            reversal.copy(bean);
            reversal.setTranAmt(-1D* bean.getTranAmt());
            reversal.setDescription(bean.getDescription() +" Reversal");
            reversal.setClientAccountRegisterId(null);
            clientAccountRegisterManager.save(reversal);
            result =RESULT_SUCCESS;
        } catch (DAOException ex) {
            Logger.getLogger(ClientAccountRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            easyLog.addEntry(EasyLog.SEVERE, "ERROR: DAO exception reversAccountRegisterTranById"
                    , getClass().getName(), ex);
        }
         return result ;

    }

}
