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
import legaltime.model.InvoiceBean;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * LaborRegisterBean is a mapping of labor_register Table.
 * @author sql2java
*/
public class LaborRegisterBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -3026649466803903423L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private String userKey;

    private boolean userKeyIsModified = false;
    private boolean userKeyIsInitialized = false;

    private Integer invoiceId;

    private boolean invoiceIdIsModified = false;
    private boolean invoiceIdIsInitialized = false;

    private Double billRate;

    private boolean billRateIsModified = false;
    private boolean billRateIsInitialized = false;

    private Boolean invoiceable;

    private boolean invoiceableIsModified = false;
    private boolean invoiceableIsInitialized = false;

    private java.util.Date activityDate;

    private boolean activityDateIsModified = false;
    private boolean activityDateIsInitialized = false;

    private java.util.Date endTime;

    private boolean endTimeIsModified = false;
    private boolean endTimeIsInitialized = false;

    private java.util.Date startTime;

    private boolean startTimeIsModified = false;
    private boolean startTimeIsInitialized = false;

    private Integer minutes;

    private boolean minutesIsModified = false;
    private boolean minutesIsInitialized = false;

    private String description;

    private boolean descriptionIsModified = false;
    private boolean descriptionIsInitialized = false;

    private Integer clientId;

    private boolean clientIdIsModified = false;
    private boolean clientIdIsInitialized = false;

    private Integer laborRegisterId;

    private boolean laborRegisterIdIsModified = false;
    private boolean laborRegisterIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a LaborRegisterBean is via the createLaborRegisterBean method in LaborRegisterManager or
     * via the factory class LaborRegisterFactory create method
     */
    protected LaborRegisterBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.last_update</li>
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
     * Getter method for userKey.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.user_key</li>
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
     * Getter method for invoiceId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.invoice_id</li>
     * <li> foreign key: invoice.invoice_id</li>
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
     * Getter method for billRate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.bill_rate</li>
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
     * Getter method for invoiceable.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.invoiceable</li>
     * <li>column size: 0</li>
     * <li>jdbc type returned by the driver: Types.BIT</li>
     * </ul>
     *
     * @return the value of invoiceable
     */
    public Boolean getInvoiceable()
    {
        return invoiceable;
    }

    /**
     * Setter method for invoiceable.
     * <br>
     * Attention, there will be no comparison with current value which
     * means calling this method will mark the field as 'modified' in all cases.
     *
     * @param newVal the new value to be assigned to invoiceable
     */
    public void setInvoiceable(Boolean newVal)
    {
        if ((newVal != null && invoiceable != null && newVal.equals(invoiceable)) ||
            (newVal == null && invoiceable == null && invoiceableIsInitialized)) {
            return;
        }
        invoiceable = newVal;
        invoiceableIsModified = true;
        invoiceableIsInitialized = true;
    }

    /**
     * Setter method for invoiceable.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to invoiceable
     */
    public void setInvoiceable(boolean newVal)
    {
        setInvoiceable(new Boolean(newVal));
    }

    /**
     * Determines if the invoiceable has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isInvoiceableModified()
    {
        return invoiceableIsModified;
    }

    /**
     * Determines if the invoiceable has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isInvoiceableInitialized()
    {
        return invoiceableIsInitialized;
    }

    /**
     * Getter method for activityDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.activity_date</li>
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
     * Getter method for endTime.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.end_time</li>
     * <li>column size: 19</li>
     * <li>jdbc type returned by the driver: Types.TIMESTAMP</li>
     * </ul>
     *
     * @return the value of endTime
     */
    public java.util.Date getEndTime()
    {
        return endTime;
    }

    /**
     * Setter method for endTime.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to endTime
     */
    public void setEndTime(java.util.Date newVal)
    {
        if ((newVal != null && endTime != null && (newVal.compareTo(endTime) == 0)) ||
            (newVal == null && endTime == null && endTimeIsInitialized)) {
            return;
        }
        endTime = newVal;
        endTimeIsModified = true;
        endTimeIsInitialized = true;
    }

    /**
     * Setter method for endTime.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to endTime
     */
    public void setEndTime(long newVal)
    {
        setEndTime(new java.util.Date(newVal));
    }

    /**
     * Determines if the endTime has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isEndTimeModified()
    {
        return endTimeIsModified;
    }

    /**
     * Determines if the endTime has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isEndTimeInitialized()
    {
        return endTimeIsInitialized;
    }

    /**
     * Getter method for startTime.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.start_time</li>
     * <li>column size: 19</li>
     * <li>jdbc type returned by the driver: Types.TIMESTAMP</li>
     * </ul>
     *
     * @return the value of startTime
     */
    public java.util.Date getStartTime()
    {
        return startTime;
    }

    /**
     * Setter method for startTime.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to startTime
     */
    public void setStartTime(java.util.Date newVal)
    {
        if ((newVal != null && startTime != null && (newVal.compareTo(startTime) == 0)) ||
            (newVal == null && startTime == null && startTimeIsInitialized)) {
            return;
        }
        startTime = newVal;
        startTimeIsModified = true;
        startTimeIsInitialized = true;
    }

    /**
     * Setter method for startTime.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to startTime
     */
    public void setStartTime(long newVal)
    {
        setStartTime(new java.util.Date(newVal));
    }

    /**
     * Determines if the startTime has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isStartTimeModified()
    {
        return startTimeIsModified;
    }

    /**
     * Determines if the startTime has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isStartTimeInitialized()
    {
        return startTimeIsInitialized;
    }

    /**
     * Getter method for minutes.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.minutes</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of minutes
     */
    public Integer getMinutes()
    {
        return minutes;
    }

    /**
     * Setter method for minutes.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to minutes
     */
    public void setMinutes(Integer newVal)
    {
        if ((newVal != null && minutes != null && (newVal.compareTo(minutes) == 0)) ||
            (newVal == null && minutes == null && minutesIsInitialized)) {
            return;
        }
        minutes = newVal;
        minutesIsModified = true;
        minutesIsInitialized = true;
    }

    /**
     * Setter method for minutes.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to minutes
     */
    public void setMinutes(int newVal)
    {
        setMinutes(new Integer(newVal));
    }

    /**
     * Determines if the minutes has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isMinutesModified()
    {
        return minutesIsModified;
    }

    /**
     * Determines if the minutes has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isMinutesInitialized()
    {
        return minutesIsInitialized;
    }

    /**
     * Getter method for description.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.description</li>
     * <li>column size: 65535</li>
     * <li>jdbc type returned by the driver: Types.LONGVARCHAR</li>
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
     * <li>full name: labor_register.client_id</li>
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
     * Getter method for laborRegisterId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: labor_register.labor_register_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of laborRegisterId
     */
    public Integer getLaborRegisterId()
    {
        return laborRegisterId;
    }

    /**
     * Setter method for laborRegisterId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to laborRegisterId
     */
    public void setLaborRegisterId(Integer newVal)
    {
        if ((newVal != null && laborRegisterId != null && (newVal.compareTo(laborRegisterId) == 0)) ||
            (newVal == null && laborRegisterId == null && laborRegisterIdIsInitialized)) {
            return;
        }
        laborRegisterId = newVal;
        laborRegisterIdIsModified = true;
        laborRegisterIdIsInitialized = true;
    }

    /**
     * Setter method for laborRegisterId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to laborRegisterId
     */
    public void setLaborRegisterId(int newVal)
    {
        setLaborRegisterId(new Integer(newVal));
    }

    /**
     * Determines if the laborRegisterId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isLaborRegisterIdModified()
    {
        return laborRegisterIdIsModified;
    }

    /**
     * Determines if the laborRegisterId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isLaborRegisterIdInitialized()
    {
        return laborRegisterIdIsInitialized;
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
    
    /** The Invoice referenced by this bean. */
    private InvoiceBean referencedInvoice;
    /** Getter method for InvoiceBean. */
    public InvoiceBean getInvoiceBean() {
        return this.referencedInvoice;
    }
    /** Setter method for InvoiceBean. */
    public void setInvoiceBean(InvoiceBean reference) {
        this.referencedInvoice = reference;
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
        return lastUpdateIsModified 		|| userKeyIsModified  		|| invoiceIdIsModified  		|| billRateIsModified  		|| invoiceableIsModified  		|| activityDateIsModified  		|| endTimeIsModified  		|| startTimeIsModified  		|| minutesIsModified  		|| descriptionIsModified  		|| clientIdIsModified  		|| laborRegisterIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        userKeyIsModified = false;
        invoiceIdIsModified = false;
        billRateIsModified = false;
        invoiceableIsModified = false;
        activityDateIsModified = false;
        endTimeIsModified = false;
        startTimeIsModified = false;
        minutesIsModified = false;
        descriptionIsModified = false;
        clientIdIsModified = false;
        laborRegisterIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(LaborRegisterBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setUserKey(bean.getUserKey());
        setInvoiceId(bean.getInvoiceId());
        setBillRate(bean.getBillRate());
        setInvoiceable(bean.getInvoiceable());
        setActivityDate(bean.getActivityDate());
        setEndTime(bean.getEndTime());
        setStartTime(bean.getStartTime());
        setMinutes(bean.getMinutes());
        setDescription(bean.getDescription());
        setClientId(bean.getClientId());
        setLaborRegisterId(bean.getLaborRegisterId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("user_key", getUserKey() == null ? "" : getUserKey().toString());
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
        dictionnary.put("bill_rate", getBillRate() == null ? "" : getBillRate().toString());
        dictionnary.put("invoiceable", getInvoiceable() == null ? "" : getInvoiceable().toString());
        dictionnary.put("activity_date", getActivityDate() == null ? "" : getActivityDate().toString());
        dictionnary.put("end_time", getEndTime() == null ? "" : getEndTime().toString());
        dictionnary.put("start_time", getStartTime() == null ? "" : getStartTime().toString());
        dictionnary.put("minutes", getMinutes() == null ? "" : getMinutes().toString());
        dictionnary.put("description", getDescription() == null ? "" : getDescription().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("labor_register_id", getLaborRegisterId() == null ? "" : getLaborRegisterId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("labor_register_id", getLaborRegisterId() == null ? "" : getLaborRegisterId().toString());
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
        } else if ("user_key".equalsIgnoreCase(column) || "userKey".equalsIgnoreCase(column)) {
            return getUserKey() == null ? "" : getUserKey().toString();
        } else if ("invoice_id".equalsIgnoreCase(column) || "invoiceId".equalsIgnoreCase(column)) {
            return getInvoiceId() == null ? "" : getInvoiceId().toString();
        } else if ("bill_rate".equalsIgnoreCase(column) || "billRate".equalsIgnoreCase(column)) {
            return getBillRate() == null ? "" : getBillRate().toString();
        } else if ("invoiceable".equalsIgnoreCase(column) || "invoiceable".equalsIgnoreCase(column)) {
            return getInvoiceable() == null ? "" : getInvoiceable().toString();
        } else if ("activity_date".equalsIgnoreCase(column) || "activityDate".equalsIgnoreCase(column)) {
            return getActivityDate() == null ? "" : getActivityDate().toString();
        } else if ("end_time".equalsIgnoreCase(column) || "endTime".equalsIgnoreCase(column)) {
            return getEndTime() == null ? "" : getEndTime().toString();
        } else if ("start_time".equalsIgnoreCase(column) || "startTime".equalsIgnoreCase(column)) {
            return getStartTime() == null ? "" : getStartTime().toString();
        } else if ("minutes".equalsIgnoreCase(column) || "minutes".equalsIgnoreCase(column)) {
            return getMinutes() == null ? "" : getMinutes().toString();
        } else if ("description".equalsIgnoreCase(column) || "description".equalsIgnoreCase(column)) {
            return getDescription() == null ? "" : getDescription().toString();
        } else if ("client_id".equalsIgnoreCase(column) || "clientId".equalsIgnoreCase(column)) {
            return getClientId() == null ? "" : getClientId().toString();
        } else if ("labor_register_id".equalsIgnoreCase(column) || "laborRegisterId".equalsIgnoreCase(column)) {
            return getLaborRegisterId() == null ? "" : getLaborRegisterId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof LaborRegisterBean)) {
            return false;
        }

		LaborRegisterBean obj = (LaborRegisterBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getUserKey(), obj.getUserKey())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getBillRate(), obj.getBillRate())
            .append(getInvoiceable(), obj.getInvoiceable())
            .append(getActivityDate(), obj.getActivityDate())
            .append(getEndTime(), obj.getEndTime())
            .append(getStartTime(), obj.getStartTime())
            .append(getMinutes(), obj.getMinutes())
            .append(getDescription(), obj.getDescription())
            .append(getClientId(), obj.getClientId())
            .append(getLaborRegisterId(), obj.getLaborRegisterId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getUserKey())
            .append(getInvoiceId())
            .append(getBillRate())
            .append(getInvoiceable())
            .append(getActivityDate())
            .append(getEndTime())
            .append(getStartTime())
            .append(getMinutes())
            .append(getDescription())
            .append(getClientId())
            .append(getLaborRegisterId())
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
            .append("user_key", getUserKey())
            .append("invoice_id", getInvoiceId())
            .append("bill_rate", getBillRate())
            .append("invoiceable", getInvoiceable())
            .append("activity_date", getActivityDate())
            .append("end_time", getEndTime())
            .append("start_time", getStartTime())
            .append("minutes", getMinutes())
            .append("description", getDescription())
            .append("client_id", getClientId())
            .append("labor_register_id", getLaborRegisterId())
            .toString();
	}


    public int compareTo(Object object)
    {
        LaborRegisterBean obj = (LaborRegisterBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getUserKey(), obj.getUserKey())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getBillRate(), obj.getBillRate())
            .append(getInvoiceable(), obj.getInvoiceable())
            .append(getActivityDate(), obj.getActivityDate())
            .append(getEndTime(), obj.getEndTime())
            .append(getStartTime(), obj.getStartTime())
            .append(getMinutes(), obj.getMinutes())
            .append(getDescription(), obj.getDescription())
            .append(getClientId(), obj.getClientId())
            .append(getLaborRegisterId(), obj.getLaborRegisterId())
            .toComparison();
   }
}
