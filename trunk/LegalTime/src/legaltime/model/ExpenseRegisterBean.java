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
import legaltime.model.InvoiceBean;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ExpenseRegisterBean is a mapping of expense_register Table.
 * @author sql2java
*/
public class ExpenseRegisterBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -1381173338100835430L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

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

    private java.util.Date expenseDate;

    private boolean expenseDateIsModified = false;
    private boolean expenseDateIsInitialized = false;

    private Boolean invoiceable;

    private boolean invoiceableIsModified = false;
    private boolean invoiceableIsInitialized = false;

    private Integer expenseRegisterId;

    private boolean expenseRegisterIdIsModified = false;
    private boolean expenseRegisterIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a ExpenseRegisterBean is via the createExpenseRegisterBean method in ExpenseRegisterManager or
     * via the factory class ExpenseRegisterFactory create method
     */
    protected ExpenseRegisterBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_register.last_update</li>
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
     * Getter method for clientId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_register.client_id</li>
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
     * <li>full name: expense_register.invoice_id</li>
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
     * <li>full name: expense_register.amount</li>
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
     * <li>full name: expense_register.description</li>
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
     * Getter method for expenseDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_register.expense_date</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of expenseDate
     */
    public java.util.Date getExpenseDate()
    {
        return expenseDate;
    }

    /**
     * Setter method for expenseDate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to expenseDate
     */
    public void setExpenseDate(java.util.Date newVal)
    {
        if ((newVal != null && expenseDate != null && (newVal.compareTo(expenseDate) == 0)) ||
            (newVal == null && expenseDate == null && expenseDateIsInitialized)) {
            return;
        }
        expenseDate = newVal;
        expenseDateIsModified = true;
        expenseDateIsInitialized = true;
    }

    /**
     * Setter method for expenseDate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to expenseDate
     */
    public void setExpenseDate(long newVal)
    {
        setExpenseDate(new java.util.Date(newVal));
    }

    /**
     * Determines if the expenseDate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isExpenseDateModified()
    {
        return expenseDateIsModified;
    }

    /**
     * Determines if the expenseDate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isExpenseDateInitialized()
    {
        return expenseDateIsInitialized;
    }

    /**
     * Getter method for invoiceable.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_register.invoiceable</li>
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
     * Getter method for expenseRegisterId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_register.expense_register_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of expenseRegisterId
     */
    public Integer getExpenseRegisterId()
    {
        return expenseRegisterId;
    }

    /**
     * Setter method for expenseRegisterId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to expenseRegisterId
     */
    public void setExpenseRegisterId(Integer newVal)
    {
        if ((newVal != null && expenseRegisterId != null && (newVal.compareTo(expenseRegisterId) == 0)) ||
            (newVal == null && expenseRegisterId == null && expenseRegisterIdIsInitialized)) {
            return;
        }
        expenseRegisterId = newVal;
        expenseRegisterIdIsModified = true;
        expenseRegisterIdIsInitialized = true;
    }

    /**
     * Setter method for expenseRegisterId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to expenseRegisterId
     */
    public void setExpenseRegisterId(int newVal)
    {
        setExpenseRegisterId(new Integer(newVal));
    }

    /**
     * Determines if the expenseRegisterId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isExpenseRegisterIdModified()
    {
        return expenseRegisterIdIsModified;
    }

    /**
     * Determines if the expenseRegisterId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isExpenseRegisterIdInitialized()
    {
        return expenseRegisterIdIsInitialized;
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
        return lastUpdateIsModified 		|| clientIdIsModified  		|| invoiceIdIsModified  		|| amountIsModified  		|| descriptionIsModified  		|| expenseDateIsModified  		|| invoiceableIsModified  		|| expenseRegisterIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        clientIdIsModified = false;
        invoiceIdIsModified = false;
        amountIsModified = false;
        descriptionIsModified = false;
        expenseDateIsModified = false;
        invoiceableIsModified = false;
        expenseRegisterIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(ExpenseRegisterBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setClientId(bean.getClientId());
        setInvoiceId(bean.getInvoiceId());
        setAmount(bean.getAmount());
        setDescription(bean.getDescription());
        setExpenseDate(bean.getExpenseDate());
        setInvoiceable(bean.getInvoiceable());
        setExpenseRegisterId(bean.getExpenseRegisterId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
        dictionnary.put("amount", getAmount() == null ? "" : getAmount().toString());
        dictionnary.put("description", getDescription() == null ? "" : getDescription().toString());
        dictionnary.put("expense_date", getExpenseDate() == null ? "" : getExpenseDate().toString());
        dictionnary.put("invoiceable", getInvoiceable() == null ? "" : getInvoiceable().toString());
        dictionnary.put("expense_register_id", getExpenseRegisterId() == null ? "" : getExpenseRegisterId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("expense_register_id", getExpenseRegisterId() == null ? "" : getExpenseRegisterId().toString());
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
        } else if ("client_id".equalsIgnoreCase(column) || "clientId".equalsIgnoreCase(column)) {
            return getClientId() == null ? "" : getClientId().toString();
        } else if ("invoice_id".equalsIgnoreCase(column) || "invoiceId".equalsIgnoreCase(column)) {
            return getInvoiceId() == null ? "" : getInvoiceId().toString();
        } else if ("amount".equalsIgnoreCase(column) || "amount".equalsIgnoreCase(column)) {
            return getAmount() == null ? "" : getAmount().toString();
        } else if ("description".equalsIgnoreCase(column) || "description".equalsIgnoreCase(column)) {
            return getDescription() == null ? "" : getDescription().toString();
        } else if ("expense_date".equalsIgnoreCase(column) || "expenseDate".equalsIgnoreCase(column)) {
            return getExpenseDate() == null ? "" : getExpenseDate().toString();
        } else if ("invoiceable".equalsIgnoreCase(column) || "invoiceable".equalsIgnoreCase(column)) {
            return getInvoiceable() == null ? "" : getInvoiceable().toString();
        } else if ("expense_register_id".equalsIgnoreCase(column) || "expenseRegisterId".equalsIgnoreCase(column)) {
            return getExpenseRegisterId() == null ? "" : getExpenseRegisterId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof ExpenseRegisterBean)) {
            return false;
        }

		ExpenseRegisterBean obj = (ExpenseRegisterBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getClientId(), obj.getClientId())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getAmount(), obj.getAmount())
            .append(getDescription(), obj.getDescription())
            .append(getExpenseDate(), obj.getExpenseDate())
            .append(getInvoiceable(), obj.getInvoiceable())
            .append(getExpenseRegisterId(), obj.getExpenseRegisterId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getClientId())
            .append(getInvoiceId())
            .append(getAmount())
            .append(getDescription())
            .append(getExpenseDate())
            .append(getInvoiceable())
            .append(getExpenseRegisterId())
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
            .append("client_id", getClientId())
            .append("invoice_id", getInvoiceId())
            .append("amount", getAmount())
            .append("description", getDescription())
            .append("expense_date", getExpenseDate())
            .append("invoiceable", getInvoiceable())
            .append("expense_register_id", getExpenseRegisterId())
            .toString();
	}


    public int compareTo(Object object)
    {
        ExpenseRegisterBean obj = (ExpenseRegisterBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getClientId(), obj.getClientId())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getAmount(), obj.getAmount())
            .append(getDescription(), obj.getDescription())
            .append(getExpenseDate(), obj.getExpenseDate())
            .append(getInvoiceable(), obj.getInvoiceable())
            .append(getExpenseRegisterId(), obj.getExpenseRegisterId())
            .toComparison();
   }
}
