
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * SecurityPrivilegeRecord serves as the security_privilege bean and carries all associated data
 * @author bmartin
 *
 */
public class SecurityPrivilegeBean implements IsSerializable{
  	private Integer clientId;
	private Integer securityPrivilegeId;
	private String privName;
	private String description;
	private Date lastUpdate;



/**
 * Primary constructor,  * @param clientId_;
 * @param securityPrivilegeId_;
 * @param privName_;
 * @param description_;
 * @param lastUpdate_;

 */
  public  SecurityPrivilegeBean( Integer clientId_, Integer securityPrivilegeId_, String privName_, String description_, Date lastUpdate_){
	 setClientId( clientId_);
	 setSecurityPrivilegeId( securityPrivilegeId_);
	 setPrivName( privName_);
	 setDescription( description_);
	 setLastUpdate( lastUpdate_);
  }

  public  SecurityPrivilegeBean(){
	 setClientId( null);
	 setSecurityPrivilegeId( null);
	 setPrivName( null);
	 setDescription( null);
	 setLastUpdate( null);
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
     * Set the securityPrivilegeId. 
     * 
     * @param securityPrivilegeId the securityPrivilegeId     */  
    public void setSecurityPrivilegeId(Integer securityPrivilegeId_) {  
        securityPrivilegeId= securityPrivilegeId_;  
    }  
  
    /** 
     * Return the securityPrivilegeId. 
     * 
     * @return the securityPrivilegeId     */  
    public Integer getSecurityPrivilegeId() {  
        return securityPrivilegeId;  
    }  

    /** 
     * Set the privName. 
     * 
     * @param privName the privName     */  
    public void setPrivName(String privName_) {  
        privName= privName_;  
    }  
  
    /** 
     * Return the privName. 
     * 
     * @return the privName     */  
    public String getPrivName() {  
        return privName;  
    }  

    /** 
     * Set the description. 
     * 
     * @param description the description     */  
    public void setDescription(String description_) {  
        description= description_;  
    }  
  
    /** 
     * Return the description. 
     * 
     * @return the description     */  
    public String getDescription() {  
        return description;  
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



}