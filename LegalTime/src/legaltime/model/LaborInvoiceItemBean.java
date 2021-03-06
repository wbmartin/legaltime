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

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * LaborInvoiceItemBean is a mapping of labor_invoice_item Table.
 * @author sql2java
*/
public class LaborInvoiceItemBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = 6030212671822620577L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private Double billRate;

    private boolean billRateIsModified = false;
    private boolean billRateIsInitialized = false;

    private Integer invoiceId;

    private boolean invoiceIdIsModified = false;
    private boolean invoiceIdIsInitialized = false;

    private Double hoursBilled;

    private boolean hoursBilledIsModified = false;
    private boolean hoursBilledIsInitialized = false;

    private String userKey;

    private boolean userKeyIsModified = false;
    private boolean userKeyIsInitialized = false;

    private String activityDescription;

    private boolean activityDescriptionIsModified = false;
    private boolean activityDescriptionIsInitialized = false;

    private java.util.Date activityDate;

    private boolean activityDateIsModified = false;
    private boolean activityDateIsInitialized = false;

    private Integer laborInvoiceItemId;

    private boolean laborInvoiceItemIdIsModified = false;
    private boolean laborInvoiceItemIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a LaborInvoiceItemBean is via the createLaborInvoiceItemBean method in LaborInvoiceItemManager or
     * via the factory class LaborInvoiceItemFactory create method
     */
    protected LaborInvoiceItemBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.last_update</li>
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
     * <li>full name: labor_invoice_item.bill_rate</li>
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
     * Getter method for invoiceId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.invoice_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of invoiceId
     */
    public Integer getInvoiceId()
    {
        return invoiceId;
    }

    /**
     * Setter method for invoiceId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to invoiceId
     */
    public void setInvoiceId(Integer newVal)
    {
        if ((newVal != null && invoiceId != null && (newVal.compareTo(invoiceId) == 0)) ||
            (newVal == null && invoiceId == null && invoiceIdIsInitialized)) {
            return;
        }
        invoiceId = newVal;
        invoiceIdIsModified = true;
        invoiceIdIsInitialized = true;
    }

    /**
     * Setter method for invoiceId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to invoiceId
     */
    public void setInvoiceId(int newVal)
    {
        setInvoiceId(new Integer(newVal));
    }

    /**
     * Determines if the invoiceId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isInvoiceIdModified()
    {
        return invoiceIdIsModified;
    }

    /**
     * Determines if the invoiceId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isInvoiceIdInitialized()
    {
        return invoiceIdIsInitialized;
    }

    /**
     * Getter method for hoursBilled.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.hours_billed</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of hoursBilled
     */
    public Double getHoursBilled()
    {
        return hoursBilled;
    }

    /**
     * Setter method for hoursBilled.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to hoursBilled
     */
    public void setHoursBilled(Double newVal)
    {
        if ((newVal != null && hoursBilled != null && (newVal.compareTo(hoursBilled) == 0)) ||
            (newVal == null && hoursBilled == null && hoursBilledIsInitialized)) {
            return;
        }
        hoursBilled = newVal;
        hoursBilledIsModified = true;
        hoursBilledIsInitialized = true;
    }

    /**
     * Setter method for hoursBilled.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to hoursBilled
     */
    public void setHoursBilled(double newVal)
    {
        setHoursBilled(new Double(newVal));
    }

    /**
     * Determines if the hoursBilled has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isHoursBilledModified()
    {
        return hoursBilledIsModified;
    }

    /**
     * Determines if the hoursBilled has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isHoursBilledInitialized()
    {
        return hoursBilledIsInitialized;
    }

    /**
     * Getter method for userKey.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.user_key</li>
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
     * Getter method for activityDescription.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.activity_description</li>
     * <li>column size: 255</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of activityDescription
     */
    public String getActivityDescription()
    {
        return activityDescription;
    }

    /**
     * Setter method for activityDescription.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to activityDescription
     */
    public void setActivityDescription(String newVal)
    {
        if ((newVal != null && activityDescription != null && (newVal.compareTo(activityDescription) == 0)) ||
            (newVal == null && activityDescription == null && activityDescriptionIsInitialized)) {
            return;
        }
        activityDescription = newVal;
        activityDescriptionIsModified = true;
        activityDescriptionIsInitialized = true;
    }

    /**
     * Determines if the activityDescription has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isActivityDescriptionModified()
    {
        return activityDescriptionIsModified;
    }

    /**
     * Determines if the activityDescription has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isActivityDescriptionInitialized()
    {
        return activityDescriptionIsInitialized;
    }

    /**
     * Getter method for activityDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.activity_date</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of activityDate
     */
    public java.util.Date getActivityDate()
    {
        return activityDate;
    }

    /**
     * Setter method for activityDate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to activityDate
     */
    public void setActivityDate(java.util.Date newVal)
    {
        if ((newVal != null && activityDate != null && (newVal.compareTo(activityDate) == 0)) ||
            (newVal == null && activityDate == null && activityDateIsInitialized)) {
            return;
        }
        activityDate = newVal;
        activityDateIsModified = true;
        activityDateIsInitialized = true;
    }

    /**
     * Setter method for activityDate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to activityDate
     */
    public void setActivityDate(long newVal)
    {
        setActivityDate(new java.util.Date(newVal));
    }

    /**
     * Determines if the activityDate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isActivityDateModified()
    {
        return activityDateIsModified;
    }

    /**
     * Determines if the activityDate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isActivityDateInitialized()
    {
        return activityDateIsInitialized;
    }

    /**
     * Getter method for laborInvoiceItemId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_invoice_item.labor_invoice_item_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of laborInvoiceItemId
     */
    public Integer getLaborInvoiceItemId()
    {
        return laborInvoiceItemId;
    }

    /**
     * Setter method for laborInvoiceItemId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to laborInvoiceItemId
     */
    public void setLaborInvoiceItemId(Integer newVal)
    {
        if ((newVal != null && laborInvoiceItemId != null && (newVal.compareTo(laborInvoiceItemId) == 0)) ||
            (newVal == null && laborInvoiceItemId == null && laborInvoiceItemIdIsInitialized)) {
            return;
        }
        laborInvoiceItemId = newVal;
        laborInvoiceItemIdIsModified = true;
        laborInvoiceItemIdIsInitialized = true;
    }

    /**
     * Setter method for laborInvoiceItemId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to laborInvoiceItemId
     */
    public void setLaborInvoiceItemId(int newVal)
    {
        setLaborInvoiceItemId(new Integer(newVal));
    }

    /**
     * Determines if the laborInvoiceItemId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isLaborInvoiceItemIdModified()
    {
        return laborInvoiceItemIdIsModified;
    }

    /**
     * Determines if the laborInvoiceItemId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isLaborInvoiceItemIdInitialized()
    {
        return laborInvoiceItemIdIsInitialized;
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
        return lastUpdateIsModified 		|| billRateIsModified  		|| invoiceIdIsModified  		|| hoursBilledIsModified  		|| userKeyIsModified  		|| activityDescriptionIsModified  		|| activityDateIsModified  		|| laborInvoiceItemIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        billRateIsModified = false;
        invoiceIdIsModified = false;
        hoursBilledIsModified = false;
        userKeyIsModified = false;
        activityDescriptionIsModified = false;
        activityDateIsModified = false;
        laborInvoiceItemIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(LaborInvoiceItemBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setBillRate(bean.getBillRate());
        setInvoiceId(bean.getInvoiceId());
        setHoursBilled(bean.getHoursBilled());
        setUserKey(bean.getUserKey());
        setActivityDescription(bean.getActivityDescription());
        setActivityDate(bean.getActivityDate());
        setLaborInvoiceItemId(bean.getLaborInvoiceItemId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("bill_rate", getBillRate() == null ? "" : getBillRate().toString());
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
        dictionnary.put("hours_billed", getHoursBilled() == null ? "" : getHoursBilled().toString());
        dictionnary.put("user_key", getUserKey() == null ? "" : getUserKey().toString());
        dictionnary.put("activity_description", getActivityDescription() == null ? "" : getActivityDescription().toString());
        dictionnary.put("activity_date", getActivityDate() == null ? "" : getActivityDate().toString());
        dictionnary.put("labor_invoice_item_id", getLaborInvoiceItemId() == null ? "" : getLaborInvoiceItemId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("labor_invoice_item_id", getLaborInvoiceItemId() == null ? "" : getLaborInvoiceItemId().toString());
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
        } else if ("invoice_id".equalsIgnoreCase(column) || "invoiceId".equalsIgnoreCase(column)) {
            return getInvoiceId() == null ? "" : getInvoiceId().toString();
        } else if ("hours_billed".equalsIgnoreCase(column) || "hoursBilled".equalsIgnoreCase(column)) {
            return getHoursBilled() == null ? "" : getHoursBilled().toString();
        } else if ("user_key".equalsIgnoreCase(column) || "userKey".equalsIgnoreCase(column)) {
            return getUserKey() == null ? "" : getUserKey().toString();
        } else if ("activity_description".equalsIgnoreCase(column) || "activityDescription".equalsIgnoreCase(column)) {
            return getActivityDescription() == null ? "" : getActivityDescription().toString();
        } else if ("activity_date".equalsIgnoreCase(column) || "activityDate".equalsIgnoreCase(column)) {
            return getActivityDate() == null ? "" : getActivityDate().toString();
        } else if ("labor_invoice_item_id".equalsIgnoreCase(column) || "laborInvoiceItemId".equalsIgnoreCase(column)) {
            return getLaborInvoiceItemId() == null ? "" : getLaborInvoiceItemId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof LaborInvoiceItemBean)) {
            return false;
        }

		LaborInvoiceItemBean obj = (LaborInvoiceItemBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getBillRate(), obj.getBillRate())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getHoursBilled(), obj.getHoursBilled())
            .append(getUserKey(), obj.getUserKey())
            .append(getActivityDescription(), obj.getActivityDescription())
            .append(getActivityDate(), obj.getActivityDate())
            .append(getLaborInvoiceItemId(), obj.getLaborInvoiceItemId())
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
            .append(getInvoiceId())
            .append(getHoursBilled())
            .append(getUserKey())
            .append(getActivityDescription())
            .append(getActivityDate())
            .append(getLaborInvoiceItemId())
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
            .append("invoice_id", getInvoiceId())
            .append("hours_billed", getHoursBilled())
            .append("user_key", getUserKey())
            .append("activity_description", getActivityDescription())
            .append("activity_date", getActivityDate())
            .append("labor_invoice_item_id", getLaborInvoiceItemId())
            .toString();
	}


    public int compareTo(Object object)
    {
        LaborInvoiceItemBean obj = (LaborInvoiceItemBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getBillRate(), obj.getBillRate())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getHoursBilled(), obj.getHoursBilled())
            .append(getUserKey(), obj.getUserKey())
            .append(getActivityDescription(), obj.getActivityDescription())
            .append(getActivityDate(), obj.getActivityDate())
            .append(getLaborInvoiceItemId(), obj.getLaborInvoiceItemId())
            .toComparison();
   }
}
