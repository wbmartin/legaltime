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

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import legaltime.model.GeneratedBean;
import legaltime.model.UserInfoBean;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ClientBillRateBean is a mapping of client_bill_rate Table.
 * @author sql2java
*/
public class ClientBillRateBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = 2536480142488521476L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private Double billRate;

    private boolean billRateIsModified = false;
    private boolean billRateIsInitialized = false;

    private String userKey;

    private boolean userKeyIsModified = false;
    private boolean userKeyIsInitialized = false;

    private Integer clientId;

    private boolean clientIdIsModified = false;
    private boolean clientIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a ClientBillRateBean is via the createClientBillRateBean method in ClientBillRateManager or
     * via the factory class ClientBillRateFactory create method
     */
    protected ClientBillRateBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_bill_rate.last_update</li>
     * <li>column size: 19</li>
     * <li>jdbc type returned by the driver: Types.TIMESTAMP</li>
     * </ul>
     *
     * @return the value of lastUpdate
     */
    public java.util.Date getLastUpdate()
    {
        return lastUpdate;
    }

    /**
     * Setter method for lastUpdate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to lastUpdate
     */
    public void setLastUpdate(java.util.Date newVal)
    {
        if ((newVal != null && lastUpdate != null && (newVal.compareTo(lastUpdate) == 0)) ||
            (newVal == null && lastUpdate == null && lastUpdateIsInitialized)) {
            return;
        }
        lastUpdate = newVal;
        lastUpdateIsModified = true;
        lastUpdateIsInitialized = true;
    }

    /**
     * Setter method for lastUpdate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to lastUpdate
     */
    public void setLastUpdate(long newVal)
    {
        setLastUpdate(new java.util.Date(newVal));
    }

    /**
     * Determines if the lastUpdate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isLastUpdateModified()
    {
        return lastUpdateIsModified;
    }

    /**
     * Determines if the lastUpdate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isLastUpdateInitialized()
    {
        return lastUpdateIsInitialized;
    }

    /**
     * Getter method for billRate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_bill_rate.bill_rate</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of billRate
     */
    public Double getBillRate()
    {
        return billRate;
    }

    /**
     * Setter method for billRate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to billRate
     */
    public void setBillRate(Double newVal)
    {
        if ((newVal != null && billRate != null && (newVal.compareTo(billRate) == 0)) ||
            (newVal == null && billRate == null && billRateIsInitialized)) {
            return;
        }
        billRate = newVal;
        billRateIsModified = true;
        billRateIsInitialized = true;
    }

    /**
     * Setter method for billRate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to billRate
     */
    public void setBillRate(double newVal)
    {
        setBillRate(new Double(newVal));
    }

    /**
     * Determines if the billRate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isBillRateModified()
    {
        return billRateIsModified;
    }

    /**
     * Determines if the billRate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isBillRateInitialized()
    {
        return billRateIsInitialized;
    }

    /**
     * Getter method for userKey.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_bill_rate.user_key</li>
     * <li> foreign key: user_info.user_key</li>
     * <li>column size: 20</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of userKey
     */
    public String getUserKey()
    {
        return userKey;
    }

    /**
     * Setter method for userKey.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to userKey
     */
    public void setUserKey(String newVal)
    {
        if ((newVal != null && userKey != null && (newVal.compareTo(userKey) == 0)) ||
            (newVal == null && userKey == null && userKeyIsInitialized)) {
            return;
        }
        userKey = newVal;
        userKeyIsModified = true;
        userKeyIsInitialized = true;
    }

    /**
     * Determines if the userKey has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isUserKeyModified()
    {
        return userKeyIsModified;
    }

    /**
     * Determines if the userKey has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isUserKeyInitialized()
    {
        return userKeyIsInitialized;
    }

    /**
     * Getter method for clientId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_bill_rate.client_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of clientId
     */
    public Integer getClientId()
    {
        return clientId;
    }

    /**
     * Setter method for clientId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to clientId
     */
    public void setClientId(Integer newVal)
    {
        if ((newVal != null && clientId != null && (newVal.compareTo(clientId) == 0)) ||
            (newVal == null && clientId == null && clientIdIsInitialized)) {
            return;
        }
        clientId = newVal;
        clientIdIsModified = true;
        clientIdIsInitialized = true;
    }

    /**
     * Setter method for clientId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to clientId
     */
    public void setClientId(int newVal)
    {
        setClientId(new Integer(newVal));
    }

    /**
     * Determines if the clientId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isClientIdModified()
    {
        return clientIdIsModified;
    }

    /**
     * Determines if the clientId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isClientIdInitialized()
    {
        return clientIdIsInitialized;
    }

    /** The UserInfo referenced by this bean. */
    private UserInfoBean referencedUserInfo;
    /** Getter method for UserInfoBean. */
    public UserInfoBean getUserInfoBean() {
        return this.referencedUserInfo;
    }
    /** Setter method for UserInfoBean. */
    public void setUserInfoBean(UserInfoBean reference) {
        this.referencedUserInfo = reference;
    }
    
    /**
     * Determines if the current object is new.
     *
     * @return true if the current object is new, false if the object is not new
     */
    public boolean isNew()
    {
        return _isNew;
    }

    /**
     * Specifies to the object if it has been set as new.
     *
     * @param isNew the boolean value to be assigned to the isNew field
     */
    public void isNew(boolean isNew)
    {
        this._isNew = isNew;
    }

    /**
     * Determines if the object has been modified since the last time this method was called.
     * <br>
     * We can also determine if this object has ever been modified since its creation.
     *
     * @return true if the object has been modified, false if the object has not been modified
     */
    public boolean isModified()
    {
        return lastUpdateIsModified 		|| billRateIsModified  		|| userKeyIsModified  		|| clientIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        billRateIsModified = false;
        userKeyIsModified = false;
        clientIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(ClientBillRateBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setBillRate(bean.getBillRate());
        setUserKey(bean.getUserKey());
        setClientId(bean.getClientId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("bill_rate", getBillRate() == null ? "" : getBillRate().toString());
        dictionnary.put("user_key", getUserKey() == null ? "" : getUserKey().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("user_key", getUserKey() == null ? "" : getUserKey().toString());
        return dictionnary;
    }

    /**
     * return a the value string representation of the given field
     */
    public String getValue(String column)
    {
        if (null == column || "".equals(column)) {
            return "";
        } else if ("last_update".equalsIgnoreCase(column) || "lastUpdate".equalsIgnoreCase(column)) {
            return getLastUpdate() == null ? "" : getLastUpdate().toString();
        } else if ("bill_rate".equalsIgnoreCase(column) || "billRate".equalsIgnoreCase(column)) {
            return getBillRate() == null ? "" : getBillRate().toString();
        } else if ("user_key".equalsIgnoreCase(column) || "userKey".equalsIgnoreCase(column)) {
            return getUserKey() == null ? "" : getUserKey().toString();
        } else if ("client_id".equalsIgnoreCase(column) || "clientId".equalsIgnoreCase(column)) {
            return getClientId() == null ? "" : getClientId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof ClientBillRateBean)) {
            return false;
        }

		ClientBillRateBean obj = (ClientBillRateBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getBillRate(), obj.getBillRate())
            .append(getUserKey(), obj.getUserKey())
            .append(getClientId(), obj.getClientId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getBillRate())
            .append(getUserKey())
            .append(getClientId())
            .toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return toString(ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * you can use the following styles:
	 * <li>ToStringStyle.DEFAULT_STYLE</li>
	 * <li>ToStringStyle.MULTI_LINE_STYLE</li>
     * <li>ToStringStyle.NO_FIELD_NAMES_STYLE</li>
     * <li>ToStringStyle.SHORT_PREFIX_STYLE</li>
     * <li>ToStringStyle.SIMPLE_STYLE</li>
	 */
	public String toString(ToStringStyle style) {
		return new ToStringBuilder(this, style)
            .append("last_update", getLastUpdate())
            .append("bill_rate", getBillRate())
            .append("user_key", getUserKey())
            .append("client_id", getClientId())
            .toString();
	}


    public int compareTo(Object object)
    {
        ClientBillRateBean obj = (ClientBillRateBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getBillRate(), obj.getBillRate())
            .append(getUserKey(), obj.getUserKey())
            .append(getClientId(), obj.getClientId())
            .toComparison();
   }
}
