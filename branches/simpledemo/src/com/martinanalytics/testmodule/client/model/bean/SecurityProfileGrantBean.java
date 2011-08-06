
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * SecurityProfileGrantRecord serves as the security_profile_grant bean and carries all associated data
 * @author bmartin
 *
 */
public class SecurityProfileGrantBean implements IsSerializable{
  	private Integer clientId;
	private Integer securityPrivilegeId;
	private Integer securityProfileId;
	private Date lastUpdate;



/**
 * Primary constructor,  * @param clientId_;
 * @param securityPrivilegeId_;
 * @param securityProfileId_;
 * @param lastUpdate_;

 */
  public  SecurityProfileGrantBean( Integer clientId_, Integer securityPrivilegeId_, Integer securityProfileId_, Date lastUpdate_){
	 setClientId( clientId_);
	 setSecurityPrivilegeId( securityPrivilegeId_);
	 setSecurityProfileId( securityProfileId_);
	 setLastUpdate( lastUpdate_);
  }

  public  SecurityProfileGrantBean(){
	 setClientId( null);
	 setSecurityPrivilegeId( null);
	 setSecurityProfileId( null);
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
     * Set the securityProfileId. 
     * 
     * @param securityProfileId the securityProfileId     */  
    public void setSecurityProfileId(Integer securityProfileId_) {  
        securityProfileId= securityProfileId_;  
    }  
  
    /** 
     * Return the securityProfileId. 
     * 
     * @return the securityProfileId     */  
    public Integer getSecurityProfileId() {  
        return securityProfileId;  
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