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

import java.util.Comparator;


/**
 * Comparator class is used to sort the VwClientFollowupBean objects.
 * @author sql2java
 */
public class VwClientFollowupComparator implements Comparator
{
    /**
     * Holds the field on which the comparison is performed.
     */
    private int iType;
    /**
     * Value that will contain the information about the order of the sort: normal or reversal.
     */
    private boolean bReverse;

    /**
     * Constructor class for VwClientFollowupComparator.
     * <br>
     * Example:
     * <br>
     * <code>Arrays.sort(pArray, new VwClientFollowupComparator(VwClientFollowupManager.ID_LAST_NAME, bReverse));<code>
     *
     * @param iType the field from which you want to sort
     * <br>
     * Possible values are:
     * <ul>
     *   <li>VwClientFollowupManager.ID_LAST_NAME
     *   <li>VwClientFollowupManager.ID_FIRST_NAME
     *   <li>VwClientFollowupManager.ID_DESCRIPTION
     *   <li>VwClientFollowupManager.ID_CLOSED_DT
     *   <li>VwClientFollowupManager.ID_OPENED_DATE
     *   <li>VwClientFollowupManager.ID_DUE_DT
     *   <li>VwClientFollowupManager.ID_FOLLOWUP_ID
     * </ul>
     */
    public VwClientFollowupComparator(int iType)
    {
        this(iType, false);
    }

    /**
     * Constructor class for VwClientFollowupComparator.
     * <br>
     * Example:
     * <br>
     * <code>Arrays.sort(pArray, new VwClientFollowupComparator(VwClientFollowupManager.ID_LAST_NAME, bReverse));<code>
     *
     * @param iType the field from which you want to sort.
     * <br>
     * Possible values are:
     * <ul>
     *   <li>VwClientFollowupManager.ID_LAST_NAME
     *   <li>VwClientFollowupManager.ID_FIRST_NAME
     *   <li>VwClientFollowupManager.ID_DESCRIPTION
     *   <li>VwClientFollowupManager.ID_CLOSED_DT
     *   <li>VwClientFollowupManager.ID_OPENED_DATE
     *   <li>VwClientFollowupManager.ID_DUE_DT
     *   <li>VwClientFollowupManager.ID_FOLLOWUP_ID
     * </ul>
     *
     * @param bReverse set this value to true, if you want to reverse the sorting results
     */
    public VwClientFollowupComparator(int iType, boolean bReverse)
    {
        this.iType = iType;
        this.bReverse = bReverse;
    }

    /**
     * Implementation of the compare method.
     */
    public int compare(Object pObj1, Object pObj2)
    {
        VwClientFollowupBean b1 = (VwClientFollowupBean)pObj1;
        VwClientFollowupBean b2 = (VwClientFollowupBean)pObj2;
        int iReturn = 0;
        switch(iType)
        {
            case VwClientFollowupManager.ID_LAST_NAME:
                if (b1.getLastName() == null && b2.getLastName() != null) {
                    iReturn = -1;
                } else if (b1.getLastName() == null && b2.getLastName() == null) {
                    iReturn = 0;
                } else if (b1.getLastName() != null && b2.getLastName() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getLastName().compareTo(b2.getLastName());
                }
                break;
            case VwClientFollowupManager.ID_FIRST_NAME:
                if (b1.getFirstName() == null && b2.getFirstName() != null) {
                    iReturn = -1;
                } else if (b1.getFirstName() == null && b2.getFirstName() == null) {
                    iReturn = 0;
                } else if (b1.getFirstName() != null && b2.getFirstName() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getFirstName().compareTo(b2.getFirstName());
                }
                break;
            case VwClientFollowupManager.ID_DESCRIPTION:
                if (b1.getDescription() == null && b2.getDescription() != null) {
                    iReturn = -1;
                } else if (b1.getDescription() == null && b2.getDescription() == null) {
                    iReturn = 0;
                } else if (b1.getDescription() != null && b2.getDescription() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getDescription().compareTo(b2.getDescription());
                }
                break;
            case VwClientFollowupManager.ID_CLOSED_DT:
                if (b1.getClosedDt() == null && b2.getClosedDt() != null) {
                    iReturn = -1;
                } else if (b1.getClosedDt() == null && b2.getClosedDt() == null) {
                    iReturn = 0;
                } else if (b1.getClosedDt() != null && b2.getClosedDt() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getClosedDt().compareTo(b2.getClosedDt());
                }
                break;
            case VwClientFollowupManager.ID_OPENED_DATE:
                if (b1.getOpenedDate() == null && b2.getOpenedDate() != null) {
                    iReturn = -1;
                } else if (b1.getOpenedDate() == null && b2.getOpenedDate() == null) {
                    iReturn = 0;
                } else if (b1.getOpenedDate() != null && b2.getOpenedDate() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getOpenedDate().compareTo(b2.getOpenedDate());
                }
                break;
            case VwClientFollowupManager.ID_DUE_DT:
                if (b1.getDueDt() == null && b2.getDueDt() != null) {
                    iReturn = -1;
                } else if (b1.getDueDt() == null && b2.getDueDt() == null) {
                    iReturn = 0;
                } else if (b1.getDueDt() != null && b2.getDueDt() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getDueDt().compareTo(b2.getDueDt());
                }
                break;
            case VwClientFollowupManager.ID_FOLLOWUP_ID:
                if (b1.getFollowupId() == null && b2.getFollowupId() != null) {
                    iReturn = -1;
                } else if (b1.getFollowupId() == null && b2.getFollowupId() == null) {
                    iReturn = 0;
                } else if (b1.getFollowupId() != null && b2.getFollowupId() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getFollowupId().compareTo(b2.getFollowupId());
                }
                break;
            default:
                throw new IllegalArgumentException("Type passed for the field is not supported");
        }

        return bReverse ? (-1 * iReturn) : iReturn;
    }}
