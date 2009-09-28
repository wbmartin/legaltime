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
import legaltime.model.ClientBean;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * FollowupBean is a mapping of followup Table.
 * @author sql2java
*/
public class FollowupBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -2319686644959649747L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private String description;

    private boolean descriptionIsModified = false;
    private boolean descriptionIsInitialized = false;

    private java.util.Date closedDt;

    private boolean closedDtIsModified = false;
    private boolean closedDtIsInitialized = false;

    private java.util.Date openedDate;

    private boolean openedDateIsModified = false;
    private boolean openedDateIsInitialized = false;

    private java.util.Date dueDt;

    private boolean dueDtIsModified = false;
    private boolean dueDtIsInitialized = false;

    private Integer clientId;

    private boolean clientIdIsModified = false;
    private boolean clientIdIsInitialized = false;

    private Integer followupId;

    private boolean followupIdIsModified = false;
    private boolean followupIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a FollowupBean is via the createFollowupBean method in FollowupManager or
     * via the factory class FollowupFactory create method
     */
    protected FollowupBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: followup.last_update</li>
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
     * <li>full name: followup.description</li>
     * <li>column size: 255</li>
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
     * Getter method for closedDt.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: followup.closed_dt</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of closedDt
     */
    public java.util.Date getClosedDt()
    {
        return closedDt;
    }

    /**
     * Setter method for closedDt.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to closedDt
     */
    public void setClosedDt(java.util.Date newVal)
    {
        if ((newVal != null && closedDt != null && (newVal.compareTo(closedDt) == 0)) ||
            (newVal == null && closedDt == null && closedDtIsInitialized)) {
            return;
        }
        closedDt = newVal;
        closedDtIsModified = true;
        closedDtIsInitialized = true;
    }

    /**
     * Setter method for closedDt.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to closedDt
     */
    public void setClosedDt(long newVal)
    {
        setClosedDt(new java.util.Date(newVal));
    }

    /**
     * Determines if the closedDt has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isClosedDtModified()
    {
        return closedDtIsModified;
    }

    /**
     * Determines if the closedDt has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isClosedDtInitialized()
    {
        return closedDtIsInitialized;
    }

    /**
     * Getter method for openedDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: followup.opened_date</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of openedDate
     */
    public java.util.Date getOpenedDate()
    {
        return openedDate;
    }

    /**
     * Setter method for openedDate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to openedDate
     */
    public void setOpenedDate(java.util.Date newVal)
    {
        if ((newVal != null && openedDate != null && (newVal.compareTo(openedDate) == 0)) ||
            (newVal == null && openedDate == null && openedDateIsInitialized)) {
            return;
        }
        openedDate = newVal;
        openedDateIsModified = true;
        openedDateIsInitialized = true;
    }

    /**
     * Setter method for openedDate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to openedDate
     */
    public void setOpenedDate(long newVal)
    {
        setOpenedDate(new java.util.Date(newVal));
    }

    /**
     * Determines if the openedDate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isOpenedDateModified()
    {
        return openedDateIsModified;
    }

    /**
     * Determines if the openedDate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isOpenedDateInitialized()
    {
        return openedDateIsInitialized;
    }

    /**
     * Getter method for dueDt.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: followup.due_dt</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of dueDt
     */
    public java.util.Date getDueDt()
    {
        return dueDt;
    }

    /**
     * Setter method for dueDt.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to dueDt
     */
    public void setDueDt(java.util.Date newVal)
    {
        if ((newVal != null && dueDt != null && (newVal.compareTo(dueDt) == 0)) ||
            (newVal == null && dueDt == null && dueDtIsInitialized)) {
            return;
        }
        dueDt = newVal;
        dueDtIsModified = true;
        dueDtIsInitialized = true;
    }

    /**
     * Setter method for dueDt.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to dueDt
     */
    public void setDueDt(long newVal)
    {
        setDueDt(new java.util.Date(newVal));
    }

    /**
     * Determines if the dueDt has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isDueDtModified()
    {
        return dueDtIsModified;
    }

    /**
     * Determines if the dueDt has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isDueDtInitialized()
    {
        return dueDtIsInitialized;
    }

    /**
     * Getter method for clientId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: followup.client_id</li>
     * <li> foreign key: client.client_id</li>
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

    /**
     * Getter method for followupId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: followup.followup_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of followupId
     */
    public Integer getFollowupId()
    {
        return followupId;
    }

    /**
     * Setter method for followupId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to followupId
     */
    public void setFollowupId(Integer newVal)
    {
        if ((newVal != null && followupId != null && (newVal.compareTo(followupId) == 0)) ||
            (newVal == null && followupId == null && followupIdIsInitialized)) {
            return;
        }
        followupId = newVal;
        followupIdIsModified = true;
        followupIdIsInitialized = true;
    }

    /**
     * Setter method for followupId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to followupId
     */
    public void setFollowupId(int newVal)
    {
        setFollowupId(new Integer(newVal));
    }

    /**
     * Determines if the followupId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isFollowupIdModified()
    {
        return followupIdIsModified;
    }

    /**
     * Determines if the followupId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isFollowupIdInitialized()
    {
        return followupIdIsInitialized;
    }

    /** The Client referenced by this bean. */
    private ClientBean referencedClient;
    /** Getter method for ClientBean. */
    public ClientBean getClientBean() {
        return this.referencedClient;
    }
    /** Setter method for ClientBean. */
    public void setClientBean(ClientBean reference) {
        this.referencedClient = reference;
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
        return lastUpdateIsModified 		|| descriptionIsModified  		|| closedDtIsModified  		|| openedDateIsModified  		|| dueDtIsModified  		|| clientIdIsModified  		|| followupIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        descriptionIsModified = false;
        closedDtIsModified = false;
        openedDateIsModified = false;
        dueDtIsModified = false;
        clientIdIsModified = false;
        followupIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(FollowupBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setDescription(bean.getDescription());
        setClosedDt(bean.getClosedDt());
        setOpenedDate(bean.getOpenedDate());
        setDueDt(bean.getDueDt());
        setClientId(bean.getClientId());
        setFollowupId(bean.getFollowupId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("description", getDescription() == null ? "" : getDescription().toString());
        dictionnary.put("closed_dt", getClosedDt() == null ? "" : getClosedDt().toString());
        dictionnary.put("opened_date", getOpenedDate() == null ? "" : getOpenedDate().toString());
        dictionnary.put("due_dt", getDueDt() == null ? "" : getDueDt().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("followup_id", getFollowupId() == null ? "" : getFollowupId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("followup_id", getFollowupId() == null ? "" : getFollowupId().toString());
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
        } else if ("closed_dt".equalsIgnoreCase(column) || "closedDt".equalsIgnoreCase(column)) {
            return getClosedDt() == null ? "" : getClosedDt().toString();
        } else if ("opened_date".equalsIgnoreCase(column) || "openedDate".equalsIgnoreCase(column)) {
            return getOpenedDate() == null ? "" : getOpenedDate().toString();
        } else if ("due_dt".equalsIgnoreCase(column) || "dueDt".equalsIgnoreCase(column)) {
            return getDueDt() == null ? "" : getDueDt().toString();
        } else if ("client_id".equalsIgnoreCase(column) || "clientId".equalsIgnoreCase(column)) {
            return getClientId() == null ? "" : getClientId().toString();
        } else if ("followup_id".equalsIgnoreCase(column) || "followupId".equalsIgnoreCase(column)) {
            return getFollowupId() == null ? "" : getFollowupId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof FollowupBean)) {
            return false;
        }

		FollowupBean obj = (FollowupBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getDescription(), obj.getDescription())
            .append(getClosedDt(), obj.getClosedDt())
            .append(getOpenedDate(), obj.getOpenedDate())
            .append(getDueDt(), obj.getDueDt())
            .append(getClientId(), obj.getClientId())
            .append(getFollowupId(), obj.getFollowupId())
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
            .append(getClosedDt())
            .append(getOpenedDate())
            .append(getDueDt())
            .append(getClientId())
            .append(getFollowupId())
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
            .append("closed_dt", getClosedDt())
            .append("opened_date", getOpenedDate())
            .append("due_dt", getDueDt())
            .append("client_id", getClientId())
            .append("followup_id", getFollowupId())
            .toString();
	}


    public int compareTo(Object object)
    {
        FollowupBean obj = (FollowupBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getDescription(), obj.getDescription())
            .append(getClosedDt(), obj.getClosedDt())
            .append(getOpenedDate(), obj.getOpenedDate())
            .append(getDueDt(), obj.getDueDt())
            .append(getClientId(), obj.getClientId())
            .append(getFollowupId(), obj.getFollowupId())
            .toComparison();
   }
}
