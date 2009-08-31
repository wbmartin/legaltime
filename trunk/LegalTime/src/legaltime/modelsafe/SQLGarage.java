/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.modelsafe;

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

    public static String getPrevBalanceSQL(int clientId_){
        return "select sum(tran_amt) from "
                    + "client_account_register "
                    + "where client_id = " + clientId_ ;
    }

}
