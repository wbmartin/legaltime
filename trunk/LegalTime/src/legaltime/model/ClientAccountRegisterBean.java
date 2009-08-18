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
 * ClientAccountRegisterBean is a mapping of client_account_register Table.
 * @author sql2java
*/
public class ClientAccountRegisterBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -4500345372362947537L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private String tranType;

    private boolean tranTypeIsModified = false;
    private boolean tranTypeIsInitialized = false;

    private Double tranAmt;

    private boolean tranAmtIsModified = false;
    private boolean tranAmtIsInitialized = false;

    private String description;

    private boolean descriptionIsModified = false;
    private boolean descriptionIsInitialized = false;

    private Integer clientId;

    private boolean clientIdIsModified = false;
    private boolean clientIdIsInitialized = false;

    private Integer clientAccountRegisterId;

    private boolean clientAccountRegisterIdIsModified = false;
    private boolean clientAccountRegisterIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a ClientAccountRegisterBean is via the createClientAccountRegisterBean method in ClientAccountRegisterManager or
     * via the factory class ClientAccountRegisterFactory create method
     */
    protected ClientAccountRegisterBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_account_register.last_update</li>
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
     * Getter method for tranType.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_account_register.tran_type</li>
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
     * Getter method for tranAmt.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_account_register.tran_amt</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of tranAmt
     */
    public Double getTranAmt()
    {
        return tranAmt;
    }

    /**
     * Setter method for tranAmt.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to tranAmt
     */
    public void setTranAmt(Double newVal)
    {
        if ((newVal != null && tranAmt != null && (newVal.compareTo(tranAmt) == 0)) ||
            (newVal == null && tranAmt == null && tranAmtIsInitialized)) {
            return;
        }
        tranAmt = newVal;
        tranAmtIsModified = true;
        tranAmtIsInitialized = true;
    }

    /**
     * Setter method for tranAmt.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to tranAmt
     */
    public void setTranAmt(double newVal)
    {
        setTranAmt(new Double(newVal));
    }

    /**
     * Determines if the tranAmt has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isTranAmtModified()
    {
        return tranAmtIsModified;
    }

    /**
     * Determines if the tranAmt has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isTranAmtInitialized()
    {
        return tranAmtIsInitialized;
    }

    /**
     * Getter method for description.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_account_register.description</li>
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
     * Getter method for clientId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_account_register.client_id</li>
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
     * Getter method for clientAccountRegisterId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: client_account_register.client_account_register_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of clientAccountRegisterId
     */
    public Integer getClientAccountRegisterId()
    {
        return clientAccountRegisterId;
    }

    /**
     * Setter method for clientAccountRegisterId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to clientAccountRegisterId
     */
    public void setClientAccountRegisterId(Integer newVal)
    {
        if ((newVal != null && clientAccountRegisterId != null && (newVal.compareTo(clientAccountRegisterId) == 0)) ||
            (newVal == null && clientAccountRegisterId == null && clientAccountRegisterIdIsInitialized)) {
            return;
        }
        clientAccountRegisterId = newVal;
        clientAccountRegisterIdIsModified = true;
        clientAccountRegisterIdIsInitialized = true;
    }

    /**
     * Setter method for clientAccountRegisterId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to clientAccountRegisterId
     */
    public void setClientAccountRegisterId(int newVal)
    {
        setClientAccountRegisterId(new Integer(newVal));
    }

    /**
     * Determines if the clientAccountRegisterId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isClientAccountRegisterIdModified()
    {
        return clientAccountRegisterIdIsModified;
    }

    /**
     * Determines if the clientAccountRegisterId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isClientAccountRegisterIdInitialized()
    {
        return clientAccountRegisterIdIsInitialized;
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
        return lastUpdateIsModified 		|| tranTypeIsModified  		|| tranAmtIsModified  		|| descriptionIsModified  		|| clientIdIsModified  		|| clientAccountRegisterIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        tranTypeIsModified = false;
        tranAmtIsModified = false;
        descriptionIsModified = false;
        clientIdIsModified = false;
        clientAccountRegisterIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(ClientAccountRegisterBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setTranType(bean.getTranType());
        setTranAmt(bean.getTranAmt());
        setDescription(bean.getDescription());
        setClientId(bean.getClientId());
        setClientAccountRegisterId(bean.getClientAccountRegisterId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("tran_type", getTranType() == null ? "" : getTranType().toString());
        dictionnary.put("tran_amt", getTranAmt() == null ? "" : getTranAmt().toString());
        dictionnary.put("description", getDescription() == null ? "" : getDescription().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("client_account_register_id", getClientAccountRegisterId() == null ? "" : getClientAccountRegisterId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("client_account_register_id", getClientAccountRegisterId() == null ? "" : getClientAccountRegisterId().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
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
        } else if ("tran_type".equalsIgnoreCase(column) || "tranType".equalsIgnoreCase(column)) {
            return getTranType() == null ? "" : getTranType().toString();
        } else if ("tran_amt".equalsIgnoreCase(column) || "tranAmt".equalsIgnoreCase(column)) {
            return getTranAmt() == null ? "" : getTranAmt().toString();
        } else if ("description".equalsIgnoreCase(column) || "description".equalsIgnoreCase(column)) {
            return getDescription() == null ? "" : getDescription().toString();
        } else if ("client_id".equalsIgnoreCase(column) || "clientId".equalsIgnoreCase(column)) {
            return getClientId() == null ? "" : getClientId().toString();
        } else if ("client_account_register_id".equalsIgnoreCase(column) || "clientAccountRegisterId".equalsIgnoreCase(column)) {
            return getClientAccountRegisterId() == null ? "" : getClientAccountRegisterId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof ClientAccountRegisterBean)) {
            return false;
        }

		ClientAccountRegisterBean obj = (ClientAccountRegisterBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getTranType(), obj.getTranType())
            .append(getTranAmt(), obj.getTranAmt())
            .append(getDescription(), obj.getDescription())
            .append(getClientId(), obj.getClientId())
            .append(getClientAccountRegisterId(), obj.getClientAccountRegisterId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getTranType())
            .append(getTranAmt())
            .append(getDescription())
            .append(getClientId())
            .append(getClientAccountRegisterId())
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
            .append("tran_type", getTranType())
            .append("tran_amt", getTranAmt())
            .append("description", getDescription())
            .append("client_id", getClientId())
            .append("client_account_register_id", getClientAccountRegisterId())
            .toString();
	}


    public int compareTo(Object object)
    {
        ClientAccountRegisterBean obj = (ClientAccountRegisterBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getTranType(), obj.getTranType())
            .append(getTranAmt(), obj.getTranAmt())
            .append(getDescription(), obj.getDescription())
            .append(getClientId(), obj.getClientId())
            .append(getClientAccountRegisterId(), obj.getClientAccountRegisterId())
            .toComparison();
   }
}
