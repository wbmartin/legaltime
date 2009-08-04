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
 * Comparator class is used to sort the ClientBean objects.
 * @author sql2java
 */
public class ClientComparator implements Comparator
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
     * Constructor class for ClientComparator.
     * <br>
     * Example:
     * <br>
     * <code>Arrays.sort(pArray, new ClientComparator(ClientManager.ID_LAST_UPDATE, bReverse));<code>
     *
     * @param iType the field from which you want to sort
     * <br>
     * Possible values are:
     * <ul>
     *   <li>ClientManager.ID_LAST_UPDATE
     *   <li>ClientManager.ID_ACTIVE_YN
     *   <li>ClientManager.ID_NOTE
     *   <li>ClientManager.ID_CLIENT_SINCE_DT
     *   <li>ClientManager.ID_EMAIL
     *   <li>ClientManager.ID_FAX
     *   <li>ClientManager.ID_CELL_PHONE
     *   <li>ClientManager.ID_HOME_PHONE
     *   <li>ClientManager.ID_WORK_PHONE
     *   <li>ClientManager.ID_ZIP
     *   <li>ClientManager.ID_STATE
     *   <li>ClientManager.ID_CITY
     *   <li>ClientManager.ID_ADDRESS
     *   <li>ClientManager.ID_LAST_NAME
     *   <li>ClientManager.ID_FIRST_NAME
     *   <li>ClientManager.ID_CLIENT_ID
     * </ul>
     */
    public ClientComparator(int iType)
    {
        this(iType, false);
    }

    /**
     * Constructor class for ClientComparator.
     * <br>
     * Example:
     * <br>
     * <code>Arrays.sort(pArray, new ClientComparator(ClientManager.ID_LAST_UPDATE, bReverse));<code>
     *
     * @param iType the field from which you want to sort.
     * <br>
     * Possible values are:
     * <ul>
     *   <li>ClientManager.ID_LAST_UPDATE
     *   <li>ClientManager.ID_ACTIVE_YN
     *   <li>ClientManager.ID_NOTE
     *   <li>ClientManager.ID_CLIENT_SINCE_DT
     *   <li>ClientManager.ID_EMAIL
     *   <li>ClientManager.ID_FAX
     *   <li>ClientManager.ID_CELL_PHONE
     *   <li>ClientManager.ID_HOME_PHONE
     *   <li>ClientManager.ID_WORK_PHONE
     *   <li>ClientManager.ID_ZIP
     *   <li>ClientManager.ID_STATE
     *   <li>ClientManager.ID_CITY
     *   <li>ClientManager.ID_ADDRESS
     *   <li>ClientManager.ID_LAST_NAME
     *   <li>ClientManager.ID_FIRST_NAME
     *   <li>ClientManager.ID_CLIENT_ID
     * </ul>
     *
     * @param bReverse set this value to true, if you want to reverse the sorting results
     */
    public ClientComparator(int iType, boolean bReverse)
    {
        this.iType = iType;
        this.bReverse = bReverse;
    }

    /**
     * Implementation of the compare method.
     */
    public int compare(Object pObj1, Object pObj2)
    {
        ClientBean b1 = (ClientBean)pObj1;
        ClientBean b2 = (ClientBean)pObj2;
        int iReturn = 0;
        switch(iType)
        {
            case ClientManager.ID_LAST_UPDATE:
                if (b1.getLastUpdate() == null && b2.getLastUpdate() != null) {
                    iReturn = -1;
                } else if (b1.getLastUpdate() == null && b2.getLastUpdate() == null) {
                    iReturn = 0;
                } else if (b1.getLastUpdate() != null && b2.getLastUpdate() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getLastUpdate().compareTo(b2.getLastUpdate());
                }
                break;
            case ClientManager.ID_ACTIVE_YN:
                if (b1.getActiveYn() == null && b2.getActiveYn() != null) {
                    iReturn = -1;
                } else if (b1.getActiveYn() == null && b2.getActiveYn() == null) {
                    iReturn = 0;
                } else if (b1.getActiveYn() != null && b2.getActiveYn() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getActiveYn().compareTo(b2.getActiveYn());
                }
                break;
            case ClientManager.ID_NOTE:
                if (b1.getNote() == null && b2.getNote() != null) {
                    iReturn = -1;
                } else if (b1.getNote() == null && b2.getNote() == null) {
                    iReturn = 0;
                } else if (b1.getNote() != null && b2.getNote() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getNote().compareTo(b2.getNote());
                }
                break;
            case ClientManager.ID_CLIENT_SINCE_DT:
                if (b1.getClientSinceDt() == null && b2.getClientSinceDt() != null) {
                    iReturn = -1;
                } else if (b1.getClientSinceDt() == null && b2.getClientSinceDt() == null) {
                    iReturn = 0;
                } else if (b1.getClientSinceDt() != null && b2.getClientSinceDt() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getClientSinceDt().compareTo(b2.getClientSinceDt());
                }
                break;
            case ClientManager.ID_EMAIL:
                if (b1.getEmail() == null && b2.getEmail() != null) {
                    iReturn = -1;
                } else if (b1.getEmail() == null && b2.getEmail() == null) {
                    iReturn = 0;
                } else if (b1.getEmail() != null && b2.getEmail() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getEmail().compareTo(b2.getEmail());
                }
                break;
            case ClientManager.ID_FAX:
                if (b1.getFax() == null && b2.getFax() != null) {
                    iReturn = -1;
                } else if (b1.getFax() == null && b2.getFax() == null) {
                    iReturn = 0;
                } else if (b1.getFax() != null && b2.getFax() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getFax().compareTo(b2.getFax());
                }
                break;
            case ClientManager.ID_CELL_PHONE:
                if (b1.getCellPhone() == null && b2.getCellPhone() != null) {
                    iReturn = -1;
                } else if (b1.getCellPhone() == null && b2.getCellPhone() == null) {
                    iReturn = 0;
                } else if (b1.getCellPhone() != null && b2.getCellPhone() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getCellPhone().compareTo(b2.getCellPhone());
                }
                break;
            case ClientManager.ID_HOME_PHONE:
                if (b1.getHomePhone() == null && b2.getHomePhone() != null) {
                    iReturn = -1;
                } else if (b1.getHomePhone() == null && b2.getHomePhone() == null) {
                    iReturn = 0;
                } else if (b1.getHomePhone() != null && b2.getHomePhone() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getHomePhone().compareTo(b2.getHomePhone());
                }
                break;
            case ClientManager.ID_WORK_PHONE:
                if (b1.getWorkPhone() == null && b2.getWorkPhone() != null) {
                    iReturn = -1;
                } else if (b1.getWorkPhone() == null && b2.getWorkPhone() == null) {
                    iReturn = 0;
                } else if (b1.getWorkPhone() != null && b2.getWorkPhone() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getWorkPhone().compareTo(b2.getWorkPhone());
                }
                break;
            case ClientManager.ID_ZIP:
                if (b1.getZip() == null && b2.getZip() != null) {
                    iReturn = -1;
                } else if (b1.getZip() == null && b2.getZip() == null) {
                    iReturn = 0;
                } else if (b1.getZip() != null && b2.getZip() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getZip().compareTo(b2.getZip());
                }
                break;
            case ClientManager.ID_STATE:
                if (b1.getState() == null && b2.getState() != null) {
                    iReturn = -1;
                } else if (b1.getState() == null && b2.getState() == null) {
                    iReturn = 0;
                } else if (b1.getState() != null && b2.getState() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getState().compareTo(b2.getState());
                }
                break;
            case ClientManager.ID_CITY:
                if (b1.getCity() == null && b2.getCity() != null) {
                    iReturn = -1;
                } else if (b1.getCity() == null && b2.getCity() == null) {
                    iReturn = 0;
                } else if (b1.getCity() != null && b2.getCity() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getCity().compareTo(b2.getCity());
                }
                break;
            case ClientManager.ID_ADDRESS:
                if (b1.getAddress() == null && b2.getAddress() != null) {
                    iReturn = -1;
                } else if (b1.getAddress() == null && b2.getAddress() == null) {
                    iReturn = 0;
                } else if (b1.getAddress() != null && b2.getAddress() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getAddress().compareTo(b2.getAddress());
                }
                break;
            case ClientManager.ID_LAST_NAME:
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
            case ClientManager.ID_FIRST_NAME:
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
            case ClientManager.ID_CLIENT_ID:
                if (b1.getClientId() == null && b2.getClientId() != null) {
                    iReturn = -1;
                } else if (b1.getClientId() == null && b2.getClientId() == null) {
                    iReturn = 0;
                } else if (b1.getClientId() != null && b2.getClientId() == null) {
                    iReturn = 1;
                } else {
                    iReturn = b1.getClientId().compareTo(b2.getClientId());
                }
                break;
            default:
                throw new IllegalArgumentException("Type passed for the field is not supported");
        }

        return bReverse ? (-1 * iReturn) : iReturn;
    }}
