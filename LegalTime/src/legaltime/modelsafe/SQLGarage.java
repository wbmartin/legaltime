/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.modelsafe;

import legaltime.model.ClientManager;

/**
 *
 * @author bmartin
 */
public class SQLGarage {
    public static final String INVOICE_LIST_SQL =
            "select client_id from labor_register where invoice_id is null " +
            " union select client_id from expense_register where invoice_id is null " +
            " union select client_id from payment_log where invoice_id is null " +
            " order by client_id;";

    public static final String DBVERSION_SQL =
            "select description from sys_code where code_id ='DBVer';";
     public static final String OPEN_FOLLOWUP_WHERE_SQL =
            "where closed_dt is null";
     public static final String OPEN_FOLLOWUP_ORDER_SQL =
            "order by due_dt";

    public static String getPrevBalanceSQL(int clientId_){
        return "select sum(tran_amt) from "
                    + " client_account_register "
                    + " where client_id = " + clientId_ ;
    }

    public static String getInvoicedClientsSQL(java.util.Date effectiveDate){
        return "select distinct " + ClientManager.ALL_FULL_FIELDS + " from "
                    + " client left join invoice "
                    + " on client.client_id =invoice.client_id "
                    + " where invoice.invoice_dt = '" + javaDateToSQLDate(effectiveDate) +"'" ;
    }

   // select distinct client.* from  client left join invoice on client.client_id =invoice.client_id  where invoice.invoice_dt = '2009-09-02'

    public static String javaDateToSQLDate(java.util.Date dt_){
        String result="";
        result = (dt_.getYear()+1900) +"-"+ (dt_.getMonth()+1) +"-"+ dt_.getDate();
        return result;

    }

}
