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
import legaltime.model.ClientBillRateBean;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * UserInfoBean is a mapping of user_info Table.
 * @author sql2java
*/
public class UserInfoBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = 6069872290497876129L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private Double defaultBillRate;

    private boolean defaultBillRateIsModified = false;
    private boolean defaultBillRateIsInitialized = false;

    private String firstName;

    private boolean firstNameIsModified = false;
    private boolean firstNameIsInitialized = false;

    private String lastName;

    private boolean lastNameIsModified = false;
    private boolean lastNameIsInitialized = false;

    private String userKey;

    private boolean userKeyIsModified = false;
    private boolean userKeyIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a UserInfoBean is via the createUserInfoBean method in UserInfoManager or
     * via the factory class UserInfoFactory create method
     */
    protected UserInfoBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: user_info.last_update</li>
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
     * Getter method for defaultBillRate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: user_info.default_bill_rate</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of defaultBillRate
     */
    public Double getDefaultBillRate()
    {
        return defaultBillRate;
    }

    /**
     * Setter method for defaultBillRate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to defaultBillRate
     */
    public void setDefaultBillRate(Double newVal)
    {
        if ((newVal != null && defaultBillRate != null && (newVal.compareTo(defaultBillRate) == 0)) ||
            (newVal == null && defaultBillRate == null && defaultBillRateIsInitialized)) {
            return;
        }
        defaultBillRate = newVal;
        defaultBillRateIsModified = true;
        defaultBillRateIsInitialized = true;
    }

    /**
     * Setter method for defaultBillRate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to defaultBillRate
     */
    public void setDefaultBillRate(double newVal)
    {
        setDefaultBillRate(new Double(newVal));
    }

    /**
     * Determines if the defaultBillRate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isDefaultBillRateModified()
    {
        return defaultBillRateIsModified;
    }

    /**
     * Determines if the defaultBillRate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isDefaultBillRateInitialized()
    {
        return defaultBillRateIsInitialized;
    }

    /**
     * Getter method for firstName.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: user_info.first_name</li>
     * <li>column size: 20</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Setter method for firstName.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to firstName
     */
    public void setFirstName(String newVal)
    {
        if ((newVal != null && firstName != null && (newVal.compareTo(firstName) == 0)) ||
            (newVal == null && firstName == null && firstNameIsInitialized)) {
            return;
        }
        firstName = newVal;
        firstNameIsModified = true;
        firstNameIsInitialized = true;
    }

    /**
     * Determines if the firstName has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isFirstNameModified()
    {
        return firstNameIsModified;
    }

    /**
     * Determines if the firstName has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isFirstNameInitialized()
    {
        return firstNameIsInitialized;
    }

    /**
     * Getter method for lastName.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: user_info.last_name</li>
     * <li>column size: 20</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Setter method for lastName.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to lastName
     */
    public void setLastName(String newVal)
    {
        if ((newVal != null && lastName != null && (newVal.compareTo(lastName) == 0)) ||
            (newVal == null && lastName == null && lastNameIsInitialized)) {
            return;
        }
        lastName = newVal;
        lastNameIsModified = true;
        lastNameIsInitialized = true;
    }

    /**
     * Determines if the lastName has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isLastNameModified()
    {
        return lastNameIsModified;
    }

    /**
     * Determines if the lastName has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isLastNameInitialized()
    {
        return lastNameIsInitialized;
    }

    /**
     * Getter method for userKey.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: user_info.user_key</li>
     * <li> imported key: client_bill_rate.user_key</li>
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
        return lastUpdateIsModified 		|| defaultBillRateIsModified  		|| firstNameIsModified  		|| lastNameIsModified  		|| userKeyIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        defaultBillRateIsModified = false;
        firstNameIsModified = false;
        lastNameIsModified = false;
        userKeyIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(UserInfoBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setDefaultBillRate(bean.getDefaultBillRate());
        setFirstName(bean.getFirstName());
        setLastName(bean.getLastName());
        setUserKey(bean.getUserKey());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("default_bill_rate", getDefaultBillRate() == null ? "" : getDefaultBillRate().toString());
        dictionnary.put("first_name", getFirstName() == null ? "" : getFirstName().toString());
        dictionnary.put("last_name", getLastName() == null ? "" : getLastName().toString());
        dictionnary.put("user_key", getUserKey() == null ? "" : getUserKey().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
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
        } else if ("default_bill_rate".equalsIgnoreCase(column) || "defaultBillRate".equalsIgnoreCase(column)) {
            return getDefaultBillRate() == null ? "" : getDefaultBillRate().toString();
        } else if ("first_name".equalsIgnoreCase(column) || "firstName".equalsIgnoreCase(column)) {
            return getFirstName() == null ? "" : getFirstName().toString();
        } else if ("last_name".equalsIgnoreCase(column) || "lastName".equalsIgnoreCase(column)) {
            return getLastName() == null ? "" : getLastName().toString();
        } else if ("user_key".equalsIgnoreCase(column) || "userKey".equalsIgnoreCase(column)) {
            return getUserKey() == null ? "" : getUserKey().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof UserInfoBean)) {
            return false;
        }

		UserInfoBean obj = (UserInfoBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getDefaultBillRate(), obj.getDefaultBillRate())
            .append(getFirstName(), obj.getFirstName())
            .append(getLastName(), obj.getLastName())
            .append(getUserKey(), obj.getUserKey())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getDefaultBillRate())
            .append(getFirstName())
            .append(getLastName())
            .append(getUserKey())
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
            .append("default_bill_rate", getDefaultBillRate())
            .append("first_name", getFirstName())
            .append("last_name", getLastName())
            .append("user_key", getUserKey())
            .toString();
	}


    public int compareTo(Object object)
    {
        UserInfoBean obj = (UserInfoBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getDefaultBillRate(), obj.getDefaultBillRate())
            .append(getFirstName(), obj.getFirstName())
            .append(getLastName(), obj.getLastName())
            .append(getUserKey(), obj.getUserKey())
            .toComparison();
   }
}
