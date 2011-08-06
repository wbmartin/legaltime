
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * SecurityProfileRecord serves as the security_profile bean and carries all associated data
 * @author bmartin
 *
 */
public class SecurityProfileBean implements IsSerializable{
  	private Integer clientId;
	private Integer securityProfileId;
	private String profileName;
	private java.util.Date lastUpdate;



/**
 * Primary constructor,  * @param clientId_;
 * @param securityProfileId_;
 * @param profileName_;
 * @param lastUpdate_;

 */
  public  SecurityProfileBean( Integer clientId_, Integer securityProfileId_, String profileName_, java.util.Date lastUpdate_){
	 setClientId( clientId_);
	 setSecurityProfileId( securityProfileId_);
	 setProfileName( profileName_);
	 setLastUpdate( lastUpdate_);
  }

  public  SecurityProfileBean(){
	 setClientId( null);
	 setSecurityProfileId( null);
	 setProfileName( null);
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
     * Set the lastUpdate. 
     * 
     * @param lastUpdate the lastUpdate     */  
    public void setLastUpdate(java.util.Date lastUpdate_) {  
        lastUpdate= lastUpdate_;  
    }  
  
    /** 
     * Return the lastUpdate. 
     * 
     * @return the lastUpdate     */  
    public java.util.Date getLastUpdate() {  
        return lastUpdate;  
    }  



}