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
 * Listener that is notified of followup table changes.
 * @author sql2java
 */
public interface FollowupListener
{
    /**
     * Invoked just before inserting a FollowupBean record into the database.
     *
     * @param bean the FollowupBean that is about to be inserted
     */
    public void beforeInsert(FollowupBean bean) throws DAOException;


    /**
     * Invoked just after a FollowupBean record is inserted in the database.
     *
     * @param bean the FollowupBean that was just inserted
     */
    public void afterInsert(FollowupBean bean) throws DAOException;


    /**
     * Invoked just before updating a FollowupBean record in the database.
     *
     * @param bean the FollowupBean that is about to be updated
     */
    public void beforeUpdate(FollowupBean bean) throws DAOException;


    /**
     * Invoked just after updating a FollowupBean record in the database.
     *
     * @param bean the FollowupBean that was just updated
     */
    public void afterUpdate(FollowupBean bean) throws DAOException;


    /**
     * Invoked just before deleting a FollowupBean record in the database.
     *
     * @param bean the FollowupBean that is about to be deleted
     */
    public void beforeDelete(FollowupBean bean) throws DAOException;


    /**
     * Invoked just after deleting a FollowupBean record in the database.
     *
     * @param bean the FollowupBean that was just deleted
     */
    public void afterDelete(FollowupBean bean) throws DAOException;

}
