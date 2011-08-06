
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * VwProfileGrantRecord serves as the vw_profile_grant bean and carries all associated data
 * @author bmartin
 *
 */
public class VwProfileGrantBean implements IsSerializable{
  	private Integer clientId;
	private Integer securityProfileId;
	private String profileName;
	private Integer securityPrivilegeId;
	private String privName;
	private Date lastUpdate;



/**
 * Primary constructor,  * @param clientId_;
 * @param securityProfileId_;
 * @param profileName_;
 * @param securityPrivilegeId_;
 * @param privName_;
 * @param lastUpdate_;

 */
  public  VwProfileGrantBean( Integer clientId_, Integer securityProfileId_, String profileName_, Integer securityPrivilegeId_, String privName_, Date lastUpdate_){
	 setClientId( clientId_);
	 setSecurityProfileId( securityProfileId_);
	 setProfileName( profileName_);
	 setSecurityPrivilegeId( securityPrivilegeId_);
	 setPrivName( privName_);
	 setLastUpdate( lastUpdate_);
  }

  public  VwProfileGrantBean(){
	 setClientId( null);
	 setSecurityProfileId( null);
	 setProfileName( null);
	 setSecurityPrivilegeId( null);
	 setPrivName( null);
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
     * Set the profileName. 
     * 
     * @param profileName the profileName     */  
    public void setProfileName(String profileName_) {  
        profileName= profileName_;  
    }  
  
    /** 
     * Return the profileName. 
     * 
     * @return the profileName     */  
    public String getProfileName() {  
        return profileName;  
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