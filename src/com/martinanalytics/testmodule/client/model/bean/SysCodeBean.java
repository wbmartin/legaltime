
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * SysCodeRecord serves as the sys_code bean and carries all associated data
 * @author bmartin
 *
 */
public class SysCodeBean implements IsSerializable{
  	private Integer sysCodeId;
	private Integer clientId;
	private String codeType;
	private String key;
	private String value;
	private Date lastUpdate;
	private String notes;



/**
 * Primary constructor,  * @param sysCodeId_;
 * @param clientId_;
 * @param codeType_;
 * @param key_;
 * @param value_;
 * @param lastUpdate_;
 * @param notes_;

 */
  public  SysCodeBean( Integer sysCodeId_, Integer clientId_, String codeType_, String key_, String value_, Date lastUpdate_, String notes_){
	 setSysCodeId( sysCodeId_);
	 setClientId( clientId_);
	 setCodeType( codeType_);
	 setKey( key_);
	 setValue( value_);
	 setLastUpdate( lastUpdate_);
	 setNotes( notes_);
  }

  public  SysCodeBean(){
	 setSysCodeId( null);
	 setClientId( null);
	 setCodeType( null);
	 setKey( null);
	 setValue( null);
	 setLastUpdate( null);
	 setNotes( null);
  }

    /** 
     * Set the sysCodeId. 
     * 
     * @param sysCodeId the sysCodeId     */  
    public void setSysCodeId(Integer sysCodeId_) {  
        sysCodeId= sysCodeId_;  
    }  
  
    /** 
     * Return the sysCodeId. 
     * 
     * @return the sysCodeId     */  
    public Integer getSysCodeId() {  
        return sysCodeId;  
    }  

    /** 
     * Set the clientId. 
     * 
     * @param clientId the clientId     */  
    public void setClientId(Integer clientId_) {  
        clientId= clientId_;  
    }  
  
    /** 
     * Return the clientId. 
     * 
     * @return the clientId     */  
    public Integer getClientId() {  
        return clientId;  
    }  

    /** 
     * Set the codeType. 
     * 
     * @param codeType the codeType     */  
    public void setCodeType(String codeType_) {  
        codeType= codeType_;  
    }  
  
    /** 
     * Return the codeType. 
     * 
     * @return the codeType     */  
    public String getCodeType() {  
        return codeType;  
    }  

    /** 
     * Set the key. 
     * 
     * @param key the key     */  
    public void setKey(String key_) {  
        key= key_;  
    }  
  
    /** 
     * Return the key. 
     * 
     * @return the key     */  
    public String getKey() {  
        return key;  
    }  

    /** 
     * Set the value. 
     * 
     * @param value the value     */  
    public void setValue(String value_) {  
        value= value_;  
    }  
  
    /** 
     * Return the value. 
     * 
     * @return the value     */  
    public String getValue() {  
        return value;  
    }  

    /** 
     * Set the lastUpdate. 
     * 
     * @param lastUpdate the lastUpdate     */  
    public void setLastUpdate(Date lastUpdate_) {  
        lastUpdate= lastUpdate_;  
    }  
  
    /** 
     * Return the lastUpdate. 
     * 
     * @return the lastUpdate     */  
    public Date getLastUpdate() {  
        return lastUpdate;  
    }  

    /** 
     * Set the notes. 
     * 
     * @param notes the notes     */  
    public void setNotes(String notes_) {  
        notes= notes_;  
    }  
  
    /** 
     * Return the notes. 
     * 
     * @return the notes     */  
    public String getNotes() {  
        return notes;  
    }  



}