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
 * PaymentLogBean is a mapping of payment_log Table.
 * @author sql2java
*/
public class PaymentLogBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -4566444731095083730L;
	
    private Integer clientId;

    private boolean clientIdIsModified = false;
    private boolean clientIdIsInitialized = false;

    private Integer invoiceId;

    private boolean invoiceIdIsModified = false;
    private boolean invoiceIdIsInitialized = false;

    private Double amount;

    private boolean amountIsModified = false;
    private boolean amountIsInitialized = false;

    private String description;

    private boolean descriptionIsModified = false;
    private boolean descriptionIsInitialized = false;

    private java.util.Date effectiveDate;

    private boolean effectiveDateIsModified = false;
    private boolean effectiveDateIsInitialized = false;

    private Integer paymentLogId;

    private boolean paymentLogIdIsModified = false;
    private boolean paymentLogIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a PaymentLogBean is via the createPaymentLogBean method in PaymentLogManager or
     * via the factory class PaymentLogFactory create method
     */
    protected PaymentLogBean()
    {
    }

    /**
     * Getter method for clientId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: payment_log.client_id</li>
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
     * Getter method for invoiceId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: payment_log.invoice_id</li>
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
     * Getter method for amount.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: payment_log.amount</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of amount
     */
    public Double getAmount()
    {
        return amount;
    }

    /**
     * Setter method for amount.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to amount
     */
    public void setAmount(Double newVal)
    {
        if ((newVal != null && amount != null && (newVal.compareTo(amount) == 0)) ||
            (newVal == null && amount == null && amountIsInitialized)) {
            return;
        }
        amount = newVal;
        amountIsModified = true;
        amountIsInitialized = true;
    }

    /**
     * Setter method for amount.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to amount
     */
    public void setAmount(double newVal)
    {
        setAmount(new Double(newVal));
    }

    /**
     * Determines if the amount has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isAmountModified()
    {
        return amountIsModified;
    }

    /**
     * Determines if the amount has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isAmountInitialized()
    {
        return amountIsInitialized;
    }

    /**
     * Getter method for description.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: payment_log.description</li>
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
     * Getter method for effectiveDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: payment_log.effective_date</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of effectiveDate
     */
    public java.util.Date getEffectiveDate()
    {
        return effectiveDate;
    }

    /**
     * Setter method for effectiveDate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to effectiveDate
     */
    public void setEffectiveDate(java.util.Date newVal)
    {
        if ((newVal != null && effectiveDate != null && (newVal.compareTo(effectiveDate) == 0)) ||
            (newVal == null && effectiveDate == null && effectiveDateIsInitialized)) {
            return;
        }
        effectiveDate = newVal;
        effectiveDateIsModified = true;
        effectiveDateIsInitialized = true;
    }

    /**
     * Setter method for effectiveDate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to effectiveDate
     */
    public void setEffectiveDate(long newVal)
    {
        setEffectiveDate(new java.util.Date(newVal));
    }

    /**
     * Determines if the effectiveDate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isEffectiveDateModified()
    {
        return effectiveDateIsModified;
    }

    /**
     * Determines if the effectiveDate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isEffectiveDateInitialized()
    {
        return effectiveDateIsInitialized;
    }

    /**
     * Getter method for paymentLogId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: payment_log.payment_log_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of paymentLogId
     */
    public Integer getPaymentLogId()
    {
        return paymentLogId;
    }

    /**
     * Setter method for paymentLogId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to paymentLogId
     */
    public void setPaymentLogId(Integer newVal)
    {
        if ((newVal != null && paymentLogId != null && (newVal.compareTo(paymentLogId) == 0)) ||
            (newVal == null && paymentLogId == null && paymentLogIdIsInitialized)) {
            return;
        }
        paymentLogId = newVal;
        paymentLogIdIsModified = true;
        paymentLogIdIsInitialized = true;
    }

    /**
     * Setter method for paymentLogId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to paymentLogId
     */
    public void setPaymentLogId(int newVal)
    {
        setPaymentLogId(new Integer(newVal));
    }

    /**
     * Determines if the paymentLogId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isPaymentLogIdModified()
    {
        return paymentLogIdIsModified;
    }

    /**
     * Determines if the paymentLogId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isPaymentLogIdInitialized()
    {
        return paymentLogIdIsInitialized;
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
        return clientIdIsModified 		|| invoiceIdIsModified  		|| amountIsModified  		|| descriptionIsModified  		|| effectiveDateIsModified  		|| paymentLogIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        clientIdIsModified = false;
        invoiceIdIsModified = false;
        amountIsModified = false;
        descriptionIsModified = false;
        effectiveDateIsModified = false;
        paymentLogIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(PaymentLogBean bean)
    {
        setClientId(bean.getClientId());
        setInvoiceId(bean.getInvoiceId());
        setAmount(bean.getAmount());
        setDescription(bean.getDescription());
        setEffectiveDate(bean.getEffectiveDate());
        setPaymentLogId(bean.getPaymentLogId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
        dictionnary.put("amount", getAmount() == null ? "" : getAmount().toString());
        dictionnary.put("description", getDescription() == null ? "" : getDescription().toString());
        dictionnary.put("effective_date", getEffectiveDate() == null ? "" : getEffectiveDate().toString());
        dictionnary.put("payment_log_id", getPaymentLogId() == null ? "" : getPaymentLogId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("payment_log_id", getPaymentLogId() == null ? "" : getPaymentLogId().toString());
        return dictionnary;
    }

    /**
     * return a the value string representation of the given field
     */
    public String getValue(String column)
    {
        if (null == column || "".equals(column)) {
            return "";
        } else if ("client_id".equalsIgnoreCase(column) || "clientId".equalsIgnoreCase(column)) {
            return getClientId() == null ? "" : getClientId().toString();
        } else if ("invoice_id".equalsIgnoreCase(column) || "invoiceId".equalsIgnoreCase(column)) {
            return getInvoiceId() == null ? "" : getInvoiceId().toString();
        } else if ("amount".equalsIgnoreCase(column) || "amount".equalsIgnoreCase(column)) {
            return getAmount() == null ? "" : getAmount().toString();
        } else if ("description".equalsIgnoreCase(column) || "description".equalsIgnoreCase(column)) {
            return getDescription() == null ? "" : getDescription().toString();
        } else if ("effective_date".equalsIgnoreCase(column) || "effectiveDate".equalsIgnoreCase(column)) {
            return getEffectiveDate() == null ? "" : getEffectiveDate().toString();
        } else if ("payment_log_id".equalsIgnoreCase(column) || "paymentLogId".equalsIgnoreCase(column)) {
            return getPaymentLogId() == null ? "" : getPaymentLogId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof PaymentLogBean)) {
            return false;
        }

		PaymentLogBean obj = (PaymentLogBean) object;
		return new EqualsBuilder()
            .append(getClientId(), obj.getClientId())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getAmount(), obj.getAmount())
            .append(getDescription(), obj.getDescription())
            .append(getEffectiveDate(), obj.getEffectiveDate())
            .append(getPaymentLogId(), obj.getPaymentLogId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getClientId())
            .append(getInvoiceId())
            .append(getAmount())
            .append(getDescription())
            .append(getEffectiveDate())
            .append(getPaymentLogId())
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
            .append("client_id", getClientId())
            .append("invoice_id", getInvoiceId())
            .append("amount", getAmount())
            .append("description", getDescription())
            .append("effective_date", getEffectiveDate())
            .append("payment_log_id", getPaymentLogId())
            .toString();
	}


    public int compareTo(Object object)
    {
        PaymentLogBean obj = (PaymentLogBean) object;
        return new CompareToBuilder()
            .append(getClientId(), obj.getClientId())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getAmount(), obj.getAmount())
            .append(getDescription(), obj.getDescription())
            .append(getEffectiveDate(), obj.getEffectiveDate())
            .append(getPaymentLogId(), obj.getPaymentLogId())
            .toComparison();
   }
}