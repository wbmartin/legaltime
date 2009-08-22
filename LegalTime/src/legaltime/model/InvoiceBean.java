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
import legaltime.model.ExpenseRegisterBean;
import legaltime.model.LaborRegisterBean;
import legaltime.model.PaymentLogBean;
import legaltime.model.ClientBean;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * InvoiceBean is a mapping of invoice Table.
 * @author sql2java
*/
public class InvoiceBean
    implements Serializable, GeneratedBean
{
	private static final long serialVersionUID = -4561487277150064050L;
	
    private java.util.Date lastUpdate;

    private boolean lastUpdateIsModified = false;
    private boolean lastUpdateIsInitialized = false;

    private Integer clientId;

    private boolean clientIdIsModified = false;
    private boolean clientIdIsInitialized = false;

    private Double prevBalanceDue;

    private boolean prevBalanceDueIsModified = false;
    private boolean prevBalanceDueIsInitialized = false;

    private Double invoiceTotalAmt;

    private boolean invoiceTotalAmtIsModified = false;
    private boolean invoiceTotalAmtIsInitialized = false;

    private java.util.Date generatedDate;

    private boolean generatedDateIsModified = false;
    private boolean generatedDateIsInitialized = false;

    private java.util.Date invoiceDt;

    private boolean invoiceDtIsModified = false;
    private boolean invoiceDtIsInitialized = false;

    private Integer invoiceId;

    private boolean invoiceIdIsModified = false;
    private boolean invoiceIdIsInitialized = false;

    private boolean _isNew = true;

    /**
     * Prefered methods to create a InvoiceBean is via the createInvoiceBean method in InvoiceManager or
     * via the factory class InvoiceFactory create method
     */
    protected InvoiceBean()
    {
    }

    /**
     * Getter method for lastUpdate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: invoice.last_update</li>
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
     * <li>full name: invoice.client_id</li>
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
     * Getter method for prevBalanceDue.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: invoice.prev_balance_due</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of prevBalanceDue
     */
    public Double getPrevBalanceDue()
    {
        return prevBalanceDue;
    }

    /**
     * Setter method for prevBalanceDue.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to prevBalanceDue
     */
    public void setPrevBalanceDue(Double newVal)
    {
        if ((newVal != null && prevBalanceDue != null && (newVal.compareTo(prevBalanceDue) == 0)) ||
            (newVal == null && prevBalanceDue == null && prevBalanceDueIsInitialized)) {
            return;
        }
        prevBalanceDue = newVal;
        prevBalanceDueIsModified = true;
        prevBalanceDueIsInitialized = true;
    }

    /**
     * Setter method for prevBalanceDue.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to prevBalanceDue
     */
    public void setPrevBalanceDue(double newVal)
    {
        setPrevBalanceDue(new Double(newVal));
    }

    /**
     * Determines if the prevBalanceDue has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isPrevBalanceDueModified()
    {
        return prevBalanceDueIsModified;
    }

    /**
     * Determines if the prevBalanceDue has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isPrevBalanceDueInitialized()
    {
        return prevBalanceDueIsInitialized;
    }

    /**
     * Getter method for invoiceTotalAmt.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: invoice.invoice_total_amt</li>
     * <li>column size: 22</li>
     * <li>jdbc type returned by the driver: Types.DOUBLE</li>
     * </ul>
     *
     * @return the value of invoiceTotalAmt
     */
    public Double getInvoiceTotalAmt()
    {
        return invoiceTotalAmt;
    }

    /**
     * Setter method for invoiceTotalAmt.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to invoiceTotalAmt
     */
    public void setInvoiceTotalAmt(Double newVal)
    {
        if ((newVal != null && invoiceTotalAmt != null && (newVal.compareTo(invoiceTotalAmt) == 0)) ||
            (newVal == null && invoiceTotalAmt == null && invoiceTotalAmtIsInitialized)) {
            return;
        }
        invoiceTotalAmt = newVal;
        invoiceTotalAmtIsModified = true;
        invoiceTotalAmtIsInitialized = true;
    }

    /**
     * Setter method for invoiceTotalAmt.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to invoiceTotalAmt
     */
    public void setInvoiceTotalAmt(double newVal)
    {
        setInvoiceTotalAmt(new Double(newVal));
    }

    /**
     * Determines if the invoiceTotalAmt has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isInvoiceTotalAmtModified()
    {
        return invoiceTotalAmtIsModified;
    }

    /**
     * Determines if the invoiceTotalAmt has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isInvoiceTotalAmtInitialized()
    {
        return invoiceTotalAmtIsInitialized;
    }

    /**
     * Getter method for generatedDate.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: invoice.generated_date</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of generatedDate
     */
    public java.util.Date getGeneratedDate()
    {
        return generatedDate;
    }

    /**
     * Setter method for generatedDate.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to generatedDate
     */
    public void setGeneratedDate(java.util.Date newVal)
    {
        if ((newVal != null && generatedDate != null && (newVal.compareTo(generatedDate) == 0)) ||
            (newVal == null && generatedDate == null && generatedDateIsInitialized)) {
            return;
        }
        generatedDate = newVal;
        generatedDateIsModified = true;
        generatedDateIsInitialized = true;
    }

    /**
     * Setter method for generatedDate.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to generatedDate
     */
    public void setGeneratedDate(long newVal)
    {
        setGeneratedDate(new java.util.Date(newVal));
    }

    /**
     * Determines if the generatedDate has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isGeneratedDateModified()
    {
        return generatedDateIsModified;
    }

    /**
     * Determines if the generatedDate has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isGeneratedDateInitialized()
    {
        return generatedDateIsInitialized;
    }

    /**
     * Getter method for invoiceDt.
     * <br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: invoice.invoice_dt</li>
     * <li>column size: 10</li>
     * <li>jdbc type returned by the driver: Types.DATE</li>
     * </ul>
     *
     * @return the value of invoiceDt
     */
    public java.util.Date getInvoiceDt()
    {
        return invoiceDt;
    }

    /**
     * Setter method for invoiceDt.
     * <br>
     * The new value is set only if compareTo() says it is different,
     * or if one of either the new value or the current value is null.
     * In case the new value is different, it is set and the field is marked as 'modified'.
     *
     * @param newVal the new value to be assigned to invoiceDt
     */
    public void setInvoiceDt(java.util.Date newVal)
    {
        if ((newVal != null && invoiceDt != null && (newVal.compareTo(invoiceDt) == 0)) ||
            (newVal == null && invoiceDt == null && invoiceDtIsInitialized)) {
            return;
        }
        invoiceDt = newVal;
        invoiceDtIsModified = true;
        invoiceDtIsInitialized = true;
    }

    /**
     * Setter method for invoiceDt.
     * <br>
     * Convenient for those who do not want to deal with Objects for primary types.
     *
     * @param newVal the new value to be assigned to invoiceDt
     */
    public void setInvoiceDt(long newVal)
    {
        setInvoiceDt(new java.util.Date(newVal));
    }

    /**
     * Determines if the invoiceDt has been modified.
     *
     * @return true if the field has been modified, false if the field has not been modified
     */
    public boolean isInvoiceDtModified()
    {
        return invoiceDtIsModified;
    }

    /**
     * Determines if the invoiceDt has been initialized.
     * <br>
     * It is useful to determine if a field is null on purpose or just because it has not been initialized.
     *
     * @return true if the field has been initialized, false otherwise
     */
    public boolean isInvoiceDtInitialized()
    {
        return invoiceDtIsInitialized;
    }

    /**
     * Getter method for invoiceId.
     * <br>
     * PRIMARY KEY.<br>
     * Meta Data Information (in progress):
     * <ul>
     * <li>full name: invoice.invoice_id</li>
     * <li> imported key: expense_register.invoice_id</li>
     * <li> imported key: labor_register.invoice_id</li>
     * <li> imported key: payment_log.invoice_id</li>
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
        return lastUpdateIsModified 		|| clientIdIsModified  		|| prevBalanceDueIsModified  		|| invoiceTotalAmtIsModified  		|| generatedDateIsModified  		|| invoiceDtIsModified  		|| invoiceIdIsModified  ;
    }

    /**
     * Resets the object modification status to 'not modified'.
     */
    public void resetIsModified()
    {
        lastUpdateIsModified = false;
        clientIdIsModified = false;
        prevBalanceDueIsModified = false;
        invoiceTotalAmtIsModified = false;
        generatedDateIsModified = false;
        invoiceDtIsModified = false;
        invoiceIdIsModified = false;
    }

    /**
     * Copies the passed bean into the current bean.
     *
     * @param bean the bean to copy into the current bean
     */
    public void copy(InvoiceBean bean)
    {
        setLastUpdate(bean.getLastUpdate());
        setClientId(bean.getClientId());
        setPrevBalanceDue(bean.getPrevBalanceDue());
        setInvoiceTotalAmt(bean.getInvoiceTotalAmt());
        setGeneratedDate(bean.getGeneratedDate());
        setInvoiceDt(bean.getInvoiceDt());
        setInvoiceId(bean.getInvoiceId());
    }

    /**
     * return a dictionnary of the object
     */
    public Map getDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("last_update", getLastUpdate() == null ? "" : getLastUpdate().toString());
        dictionnary.put("client_id", getClientId() == null ? "" : getClientId().toString());
        dictionnary.put("prev_balance_due", getPrevBalanceDue() == null ? "" : getPrevBalanceDue().toString());
        dictionnary.put("invoice_total_amt", getInvoiceTotalAmt() == null ? "" : getInvoiceTotalAmt().toString());
        dictionnary.put("generated_date", getGeneratedDate() == null ? "" : getGeneratedDate().toString());
        dictionnary.put("invoice_dt", getInvoiceDt() == null ? "" : getInvoiceDt().toString());
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
        return dictionnary;
    }

    /**
     * return a dictionnary of the pk columns
     */
    public Map getPkDictionnary()
    {
        Map dictionnary = new HashMap();
        dictionnary.put("invoice_id", getInvoiceId() == null ? "" : getInvoiceId().toString());
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
        } else if ("prev_balance_due".equalsIgnoreCase(column) || "prevBalanceDue".equalsIgnoreCase(column)) {
            return getPrevBalanceDue() == null ? "" : getPrevBalanceDue().toString();
        } else if ("invoice_total_amt".equalsIgnoreCase(column) || "invoiceTotalAmt".equalsIgnoreCase(column)) {
            return getInvoiceTotalAmt() == null ? "" : getInvoiceTotalAmt().toString();
        } else if ("generated_date".equalsIgnoreCase(column) || "generatedDate".equalsIgnoreCase(column)) {
            return getGeneratedDate() == null ? "" : getGeneratedDate().toString();
        } else if ("invoice_dt".equalsIgnoreCase(column) || "invoiceDt".equalsIgnoreCase(column)) {
            return getInvoiceDt() == null ? "" : getInvoiceDt().toString();
        } else if ("invoice_id".equalsIgnoreCase(column) || "invoiceId".equalsIgnoreCase(column)) {
            return getInvoiceId() == null ? "" : getInvoiceId().toString();
        }
        return "";
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof InvoiceBean)) {
            return false;
        }

		InvoiceBean obj = (InvoiceBean) object;
		return new EqualsBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getClientId(), obj.getClientId())
            .append(getPrevBalanceDue(), obj.getPrevBalanceDue())
            .append(getInvoiceTotalAmt(), obj.getInvoiceTotalAmt())
            .append(getGeneratedDate(), obj.getGeneratedDate())
            .append(getInvoiceDt(), obj.getInvoiceDt())
            .append(getInvoiceId(), obj.getInvoiceId())
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
            .append(getPrevBalanceDue())
            .append(getInvoiceTotalAmt())
            .append(getGeneratedDate())
            .append(getInvoiceDt())
            .append(getInvoiceId())
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
            .append("prev_balance_due", getPrevBalanceDue())
            .append("invoice_total_amt", getInvoiceTotalAmt())
            .append("generated_date", getGeneratedDate())
            .append("invoice_dt", getInvoiceDt())
            .append("invoice_id", getInvoiceId())
            .toString();
	}


    public int compareTo(Object object)
    {
        InvoiceBean obj = (InvoiceBean) object;
        return new CompareToBuilder()
            .append(getLastUpdate(), obj.getLastUpdate())
            .append(getClientId(), obj.getClientId())
            .append(getPrevBalanceDue(), obj.getPrevBalanceDue())
            .append(getInvoiceTotalAmt(), obj.getInvoiceTotalAmt())
            .append(getGeneratedDate(), obj.getGeneratedDate())
            .append(getInvoiceDt(), obj.getInvoiceDt())
            .append(getInvoiceId(), obj.getInvoiceId())
            .toComparison();
   }
}