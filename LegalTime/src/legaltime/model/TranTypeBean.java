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
 * TranTypeBean is a mapping of tran_type Table.
 * @author sql2java
*/
public class TranTypeBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -5593389433631363864L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private String description;

    private boolean descriptionIsModified = false;
    private boolean descriptionIsInitialized = false;

    private String tranType;

    private boolean tranTypeIsModified = false;
    private boolean tranTypeIsInitialized = false;

    private Integer tranTypeId;

    private boolean tranTypeIdIsModified = false;
    private boolean tranTypeIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a TranTypeBean is via the createTranTypeBean method in TranTypeManager or
     * via the factory class TranTypeFactory create method
     */
    protected TranTypeBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: tran_type.last_update</li>
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
     * Getter method for description.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: tran_type.description</li>
     * <li>column size: 20</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Setter method for description.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to description
     */
    public void setDescription(String newVal)
    {
        if ((newVal != null && description != null && (newVal.compareTo(description) == 0)) ||
            (newVal == null && description == null && descriptionIsInitialized)) {
            return;
        }
        description = newVal;
        descriptionIsModified = true;
        descriptionIsInitialized = true;
    }

    /**
     * Determines if the description has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isDescriptionModified()
    {
        return descriptionIsModified;
    }

    /**
     * Determines if the description has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isDescriptionInitialized()
    {
        return descriptionIsInitialized;
    }

    /**
     * Getter method for tranType.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: tran_type.tran_type</li>
     * <li>column size: 5</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of tranType
     */
    public String getTranType()
    {
        return tranType;
    }

    /**
     * Setter method for tranType.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to tranType
     */
    public void setTranType(String newVal)
    {
        if ((newVal != null && tranType != null && (newVal.compareTo(tranType) == 0)) ||
            (newVal == null && tranType == null && tranTypeIsInitialized)) {
            return;
        }
        tranType = newVal;
        tranTypeIsModified = true;
        tranTypeIsInitialized = true;
    }

    /**
     * Determines if the tranType has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isTranTypeModified()
    {
        return tranTypeIsModified;
    }

    /**
     * Determines if the tranType has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isTranTypeInitialized()
    {
        return tranTypeIsInitialized;
    }

    /**
     * Getter method for tranTypeId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: tran_type.tran_type_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of tranTypeId
     */
    public Integer getTranTypeId()
    {
        return tranTypeId;
    }

    /**
     * Setter method for tranTypeId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to tranTypeId
     */
    public void setTranTypeId(Integer newVal)
    {
        if ((newVal != null && tranTypeId != null && (newVal.compareTo(tranTypeId) == 0)) ||
            (newVal == null && tranTypeId == null && tranTypeIdIsInitialized)) {
            return;
        }
        tranTypeId = newVal;
        tranTypeIdIsModified = true;
        tranTypeIdIsInitialized = true;
    }

    /**
     * Setter method for tranTypeId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to tranTypeId
     */
    public void setTranTypeId(int newVal)
    {
        setTranTypeId(new Integer(newVal));
    }

    /**
     * Determines if the tranTypeId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isTranTypeIdModified()
    {
        return tranTypeIdIsModified;
    }

    /**
     * Determines if the tranTypeId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isTranTypeIdInitialized()
    {
        return tranTypeIdIsInitialized;
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
        return lastUpdateIsModified 		|| descriptionIsModified  		|| tranTypeIsModified  		|| tranTypeIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        descriptionIsModified = false;
        tranTypeIsModified = false;
        tranTypeIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(TranTypeBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setDescription(bean.getDescription());
        setTranType(bean.getTranType());
        setTranTypeId(bean.getTranTypeId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("description", getDescription() == null ? "" : getDescription().toString());
        dictionnary.put("tran_type", getTranType() == null ? "" : getTranType().toString());
        dictionnary.put("tran_type_id", getTranTypeId() == null ? "" : getTranTypeId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("tran_type_id", getTranTypeId() == null ? "" : getTranTypeId().toString());
        dictionnary.put("tran_type", getTranType() == null ? "" : getTranType().toString());
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
        } else if ("description".equalsIgnoreCase(column) || "description".equalsIgnoreCase(column)) {
            return getDescription() == null ? "" : getDescription().toString();
        } else if ("tran_type".equalsIgnoreCase(column) || "tranType".equalsIgnoreCase(column)) {
            return getTranType() == null ? "" : getTranType().toString();
        } else if ("tran_type_id".equalsIgnoreCase(column) || "tranTypeId".equalsIgnoreCase(column)) {
            return getTranTypeId() == null ? "" : getTranTypeId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof TranTypeBean)) {
            return false;
        }

		TranTypeBean obj = (TranTypeBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getDescription(), obj.getDescription())
            .append(getTranType(), obj.getTranType())
            .append(getTranTypeId(), obj.getTranTypeId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getDescription())
            .append(getTranType())
            .append(getTranTypeId())
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
            .append("description", getDescription())
            .append("tran_type", getTranType())
            .append("tran_type_id", getTranTypeId())
            .toString();
	}


    public int compareTo(Object object)
    {
        TranTypeBean obj = (TranTypeBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getDescription(), obj.getDescription())
            .append(getTranType(), obj.getTranType())
            .append(getTranTypeId(), obj.getTranTypeId())
            .toComparison();
   }
}
