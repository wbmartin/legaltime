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
 * ExpenseInvoiceItemBean is a mapping of expense_invoice_item Table.
 * @author sql2java
*/
public class ExpenseInvoiceItemBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = 2541437596433541156L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private Integer invoiceId;

    private boolean invoiceIdIsModified = false;
    private boolean invoiceIdIsInitialized = false;

    private Double amount;

    private boolean amountIsModified = false;
    private boolean amountIsInitialized = false;

    private String expenseDescription;

    private boolean expenseDescriptionIsModified = false;
    private boolean expenseDescriptionIsInitialized = false;

    private java.util.Date expenseDate;

    private boolean expenseDateIsModified = false;
    private boolean expenseDateIsInitialized = false;

    private Integer expenseInvoiceItemId;

    private boolean expenseInvoiceItemIdIsModified = false;
    private boolean expenseInvoiceItemIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a ExpenseInvoiceItemBean is via the createExpenseInvoiceItemBean method in ExpenseInvoiceItemManager or
     * via the factory class ExpenseInvoiceItemFactory create method
     */
    protected ExpenseInvoiceItemBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_invoice_item.last_update</li>
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
     * Getter method for invoiceId.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_invoice_item.invoice_id</li>
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
     * <li>full name: expense_invoice_item.amount</li>
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
     * Getter method for expenseDescription.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_invoice_item.expense_description</li>
     * <li>column size: 255</li>
     * <li>jdbc type returned by the driver: Types.VARCHAR</li>
     * </ul>
     *
     * @return the value of expenseDescription
     */
    public String getExpenseDescription()
    {
        return expenseDescription;
    }

    /**
     * Setter method for expenseDescription.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to expenseDescription
     */
    public void setExpenseDescription(String newVal)
    {
        if ((newVal != null && expenseDescription != null && (newVal.compareTo(expenseDescription) == 0)) ||
            (newVal == null && expenseDescription == null && expenseDescriptionIsInitialized)) {
            return;
        }
        expenseDescription = newVal;
        expenseDescriptionIsModified = true;
        expenseDescriptionIsInitialized = true;
    }

    /**
     * Determines if the expenseDescription has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isExpenseDescriptionModified()
    {
        return expenseDescriptionIsModified;
    }

    /**
     * Determines if the expenseDescription has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isExpenseDescriptionInitialized()
    {
        return expenseDescriptionIsInitialized;
    }

    /**
     * Getter method for expenseDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_invoice_item.expense_date</li>
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
     * Getter method for expenseInvoiceItemId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: expense_invoice_item.expense_invoice_item_id</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.INTEGER</li>
     * </ul>
     *
     * @return the value of expenseInvoiceItemId
     */
    public Integer getExpenseInvoiceItemId()
    {
        return expenseInvoiceItemId;
    }

    /**
     * Setter method for expenseInvoiceItemId.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to expenseInvoiceItemId
     */
    public void setExpenseInvoiceItemId(Integer newVal)
    {
        if ((newVal != null && expenseInvoiceItemId != null && (newVal.compareTo(expenseInvoiceItemId) == 0)) ||
            (newVal == null && expenseInvoiceItemId == null && expenseInvoiceItemIdIsInitialized)) {
            return;
        }
        expenseInvoiceItemId = newVal;
        expenseInvoiceItemIdIsModified = true;
        expenseInvoiceItemIdIsInitialized = true;
    }

    /**
     * Setter method for expenseInvoiceItemId.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to expenseInvoiceItemId
     */
    public void setExpenseInvoiceItemId(int newVal)
    {
        setExpenseInvoiceItemId(new Integer(newVal));
    }

    /**
     * Determines if the expenseInvoiceItemId has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isExpenseInvoiceItemIdModified()
    {
        return expenseInvoiceItemIdIsModified;
    }

    /**
     * Determines if the expenseInvoiceItemId has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isExpenseInvoiceItemIdInitialized()
    {
        return expenseInvoiceItemIdIsInitialized;
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
        return lastUpdateIsModified 		|| invoiceIdIsModified  		|| amountIsModified  		|| expenseDescriptionIsModified  		|| expenseDateIsModified  		|| expenseInvoiceItemIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        invoiceIdIsModified = false;
        amountIsModified = false;
        expenseDescriptionIsModified = false;
        expenseDateIsModified = false;
        expenseInvoiceItemIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(ExpenseInvoiceItemBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setInvoiceId(bean.getInvoiceId());
        setAmount(bean.getAmount());
        setExpenseDescription(bean.getExpenseDescription());
        setExpenseDate(bean.getExpenseDate());
        setExpenseInvoiceItemId(bean.getExpenseInvoiceItemId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
        dictionnary.put("amount", getAmount() == null ? "" : getAmount().toString());
        dictionnary.put("expense_description", getExpenseDescription() == null ? "" : getExpenseDescription().toString());
        dictionnary.put("expense_date", getExpenseDate() == null ? "" : getExpenseDate().toString());
        dictionnary.put("expense_invoice_item_id", getExpenseInvoiceItemId() == null ? "" : getExpenseInvoiceItemId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("expense_invoice_item_id", getExpenseInvoiceItemId() == null ? "" : getExpenseInvoiceItemId().toString());
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
        } else if ("invoice_id".equalsIgnoreCase(column) || "invoiceId".equalsIgnoreCase(column)) {
            return getInvoiceId() == null ? "" : getInvoiceId().toString();
        } else if ("amount".equalsIgnoreCase(column) || "amount".equalsIgnoreCase(column)) {
            return getAmount() == null ? "" : getAmount().toString();
        } else if ("expense_description".equalsIgnoreCase(column) || "expenseDescription".equalsIgnoreCase(column)) {
            return getExpenseDescription() == null ? "" : getExpenseDescription().toString();
        } else if ("expense_date".equalsIgnoreCase(column) || "expenseDate".equalsIgnoreCase(column)) {
            return getExpenseDate() == null ? "" : getExpenseDate().toString();
        } else if ("expense_invoice_item_id".equalsIgnoreCase(column) || "expenseInvoiceItemId".equalsIgnoreCase(column)) {
            return getExpenseInvoiceItemId() == null ? "" : getExpenseInvoiceItemId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof ExpenseInvoiceItemBean)) {
            return false;
        }

		ExpenseInvoiceItemBean obj = (ExpenseInvoiceItemBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getAmount(), obj.getAmount())
            .append(getExpenseDescription(), obj.getExpenseDescription())
            .append(getExpenseDate(), obj.getExpenseDate())
            .append(getExpenseInvoiceItemId(), obj.getExpenseInvoiceItemId())
            .isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
            .append(getLastUpdate())
            .append(getInvoiceId())
            .append(getAmount())
            .append(getExpenseDescription())
            .append(getExpenseDate())
            .append(getExpenseInvoiceItemId())
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
            .append("invoice_id", getInvoiceId())
            .append("amount", getAmount())
            .append("expense_description", getExpenseDescription())
            .append("expense_date", getExpenseDate())
            .append("expense_invoice_item_id", getExpenseInvoiceItemId())
            .toString();
	}


    public int compareTo(Object object)
    {
        ExpenseInvoiceItemBean obj = (ExpenseInvoiceItemBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getInvoiceId(), obj.getInvoiceId())
            .append(getAmount(), obj.getAmount())
            .append(getExpenseDescription(), obj.getExpenseDescription())
            .append(getExpenseDate(), obj.getExpenseDate())
            .append(getExpenseInvoiceItemId(), obj.getExpenseInvoiceItemId())
            .toComparison();
   }
}
