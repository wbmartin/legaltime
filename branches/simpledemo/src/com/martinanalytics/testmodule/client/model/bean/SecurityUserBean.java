
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * SecurityUserRecord serves as the security_user bean and carries all associated data
 * @author bmartin
 *
 */
public class SecurityUserBean implements IsSerializable{
  	private Integer clientId;
	private String userId;
	private String passwordEnc;
	private Integer securityProfileId;
	private String sessionId;
	private Date sessionExpireDt;
	private String activeYn;
	private Date lastUpdate;



/**
 * Primary constructor,  * @param clientId_;
 * @param userId_;
 * @param passwordEnc_;
 * @param securityProfileId_;
 * @param sessionId_;
 * @param sessionExpireDt_;
 * @param activeYn_;
 * @param lastUpdate_;

 */
  public  SecurityUserBean( Integer clientId_, String userId_, String passwordEnc_, Integer securityProfileId_, String sessionId_, Date sessionExpireDt_, String activeYn_, Date lastUpdate_){
	 setClientId( clientId_);
	 setUserId( userId_);
	 setPasswordEnc( passwordEnc_);
	 setSecurityProfileId( securityProfileId_);
	 setSessionId( sessionId_);
	 setSessionExpireDt( sessionExpireDt_);
	 setActiveYn( activeYn_);
	 setLastUpdate( lastUpdate_);
  }

  public  SecurityUserBean(){
	 setClientId( null);
	 setUserId( null);
	 setPasswordEnc( null);
	 setSecurityProfileId( null);
	 setSessionId( null);
	 setSessionExpireDt( null);
	 setActiveYn( null);
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
     * Set the userId. 
     * 
     * @param userId the userId     */  
    public void setUserId(String userId_) {  
        userId= userId_;  
    }  
  
    /** 
     * Return the userId. 
     * 
     * @return the userId     */  
    public String getUserId() {  
        return userId;  
    }  

    /** 
     * Set the passwordEnc. 
     * 
     * @param passwordEnc the passwordEnc     */  
    public void setPasswordEnc(String passwordEnc_) {  
        passwordEnc= passwordEnc_;  
    }  
  
    /** 
     * Return the passwordEnc. 
     * 
     * @return the passwordEnc     */  
    public String getPasswordEnc() {  
        return passwordEnc;  
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
     * Set the sessionId. 
     * 
     * @param sessionId the sessionId     */  
    public void setSessionId(String sessionId_) {  
        sessionId= sessionId_;  
    }  
  
    /** 
     * Return the sessionId. 
     * 
     * @return the sessionId     */  
    public String getSessionId() {  
        return sessionId;  
    }  

    /** 
     * Set the sessionExpireDt. 
     * 
     * @param sessionExpireDt the sessionExpireDt     */  
    public void setSessionExpireDt(Date sessionExpireDt_) {  
        sessionExpireDt= sessionExpireDt_;  
    }  
  
    /** 
     * Return the sessionExpireDt. 
     * 
     * @return the sessionExpireDt     */  
    public Date getSessionExpireDt() {  
        return sessionExpireDt;  
    }  

    /** 
     * Set the activeYn. 
     * 
     * @param activeYn the activeYn     */  
    public void setActiveYn(String activeYn_) {  
        activeYn= activeYn_;  
    }  
  
    /** 
     * Return the activeYn. 
     * 
     * @return the activeYn     */  
    public String getActiveYn() {  
        return activeYn;  
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