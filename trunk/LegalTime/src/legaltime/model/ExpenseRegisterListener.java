// ______________________________________________________
// Generated by sql2java - http://sql2java.sourceforge.net/
// jdbc driver used at code generation time: com.mysql.jdbc.Driver
//
// Please help us improve this tool by reporting:
// - problems and suggestions to
//   http://sourceforge.net/tracker/?group_id=54687
// - feedbacks and ideas on
//   http://sourceforge.net/forum/forum.php?forum_id=182208
// ______________________________________________________

package legaltime.model;

import legaltime.model.exception.DAOException;

/**
 * Listener that is notified of expense_register table changes.
 * @author sql2java
 */
public interface ExpenseRegisterListener
{
    /**
     * Invoked just before inserting a ExpenseRegisterBean record into the database.
     *
     * @param bean the ExpenseRegisterBean that is about to be inserted
     */
    public void beforeInsert(ExpenseRegisterBean bean) throws DAOException;


    /**
     * Invoked just after a ExpenseRegisterBean record is inserted in the database.
     *
     * @param bean the ExpenseRegisterBean that was just inserted
     */
    public void afterInsert(ExpenseRegisterBean bean) throws DAOException;


    /**
     * Invoked just before updating a ExpenseRegisterBean record in the database.
     *
     * @param bean the ExpenseRegisterBean that is about to be updated
     */
    public void beforeUpdate(ExpenseRegisterBean bean) throws DAOException;


    /**
     * Invoked just after updating a ExpenseRegisterBean record in the database.
     *
     * @param bean the ExpenseRegisterBean that was just updated
     */
    public void afterUpdate(ExpenseRegisterBean bean) throws DAOException;


    /**
     * Invoked just before deleting a ExpenseRegisterBean record in the database.
     *
     * @param bean the ExpenseRegisterBean that is about to be deleted
     */
    public void beforeDelete(ExpenseRegisterBean bean) throws DAOException;


    /**
     * Invoked just after deleting a ExpenseRegisterBean record in the database.
     *
     * @param bean the ExpenseRegisterBean that was just deleted
     */
    public void afterDelete(ExpenseRegisterBean bean) throws DAOException;

}
