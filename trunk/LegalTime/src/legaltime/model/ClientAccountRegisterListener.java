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
 * Listener that is notified of client_account_register table changes.
 * @author sql2java
 */
public interface ClientAccountRegisterListener
{
    /**
     * Invoked just before inserting a ClientAccountRegisterBean record into the database.
     *
     * @param bean the ClientAccountRegisterBean that is about to be inserted
     */
    public void beforeInsert(ClientAccountRegisterBean bean) throws DAOException;


    /**
     * Invoked just after a ClientAccountRegisterBean record is inserted in the database.
     *
     * @param bean the ClientAccountRegisterBean that was just inserted
     */
    public void afterInsert(ClientAccountRegisterBean bean) throws DAOException;


    /**
     * Invoked just before updating a ClientAccountRegisterBean record in the database.
     *
     * @param bean the ClientAccountRegisterBean that is about to be updated
     */
    public void beforeUpdate(ClientAccountRegisterBean bean) throws DAOException;


    /**
     * Invoked just after updating a ClientAccountRegisterBean record in the database.
     *
     * @param bean the ClientAccountRegisterBean that was just updated
     */
    public void afterUpdate(ClientAccountRegisterBean bean) throws DAOException;


    /**
     * Invoked just before deleting a ClientAccountRegisterBean record in the database.
     *
     * @param bean the ClientAccountRegisterBean that is about to be deleted
     */
    public void beforeDelete(ClientAccountRegisterBean bean) throws DAOException;


    /**
     * Invoked just after deleting a ClientAccountRegisterBean record in the database.
     *
     * @param bean the ClientAccountRegisterBean that was just deleted
     */
    public void afterDelete(ClientAccountRegisterBean bean) throws DAOException;

}
