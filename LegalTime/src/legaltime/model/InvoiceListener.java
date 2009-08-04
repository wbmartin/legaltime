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
 * Listener that is notified of invoice table changes.
 * @author sql2java
 */
public interface InvoiceListener
{
    /**
     * Invoked just before inserting a InvoiceBean record into the database.
     *
     * @param bean the InvoiceBean that is about to be inserted
     */
    public void beforeInsert(InvoiceBean bean) throws DAOException;


    /**
     * Invoked just after a InvoiceBean record is inserted in the database.
     *
     * @param bean the InvoiceBean that was just inserted
     */
    public void afterInsert(InvoiceBean bean) throws DAOException;


    /**
     * Invoked just before updating a InvoiceBean record in the database.
     *
     * @param bean the InvoiceBean that is about to be updated
     */
    public void beforeUpdate(InvoiceBean bean) throws DAOException;


    /**
     * Invoked just after updating a InvoiceBean record in the database.
     *
     * @param bean the InvoiceBean that was just updated
     */
    public void afterUpdate(InvoiceBean bean) throws DAOException;


    /**
     * Invoked just before deleting a InvoiceBean record in the database.
     *
     * @param bean the InvoiceBean that is about to be deleted
     */
    public void beforeDelete(InvoiceBean bean) throws DAOException;


    /**
     * Invoked just after deleting a InvoiceBean record in the database.
     *
     * @param bean the InvoiceBean that was just deleted
     */
    public void afterDelete(InvoiceBean bean) throws DAOException;

}