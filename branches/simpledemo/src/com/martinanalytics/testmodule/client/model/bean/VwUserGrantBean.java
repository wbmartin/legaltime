
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * VwUserGrantRecord serves as the vw_user_grant bean and carries all associated data
 * @author bmartin
 *
 */
public class VwUserGrantBean implements IsSerializable{
  	private String userId;
	private Integer clientId;
	private String profileName;
	private String privName;



/**
 * Primary constructor,  * @param userId_;
 * @param clientId_;
 * @param profileName_;
 * @param privName_;

 */
  public  VwUserGrantBean( String userId_, Integer clientId_, String profileName_, String privName_){
	 setUserId( userId_);
	 setClientId( clientId_);
	 setProfileName( profileName_);
	 setPrivName( privName_);
  }

  public  VwUserGrantBean(){
	 setUserId( null);
	 setClientId( null);
	 setProfileName( null);
	 setPrivName( null);
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



}