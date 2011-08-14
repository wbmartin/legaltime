
package com.martinanalytics.testmodule.client.model.bean;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;



/**
 * UserPublicRecord serves as the user_public bean and carries all associated data
 * @author bmartin
 *
 */
public class UserPublicBean implements IsSerializable{
  	private Integer clientId;
	private String userId;
	private Date lastUpdate;
	private String lastName;
	private String firstName;
	private String middleName;
	private String officeAddress1;
	private String officeAddress2;
	private String officeCity;
	private String officeState;
	private String officeZip;
	private String title;
	private String suffix;
	private String officePhone;
	private String fax;
	private String officeCell;
	private String comment;



/**
 * Primary constructor,  * @param clientId_;
 * @param userId_;
 * @param lastUpdate_;
 * @param lastName_;
 * @param firstName_;
 * @param middleName_;
 * @param officeAddress1_;
 * @param officeAddress2_;
 * @param officeCity_;
 * @param officeState_;
 * @param officeZip_;
 * @param title_;
 * @param suffix_;
 * @param officePhone_;
 * @param fax_;
 * @param officeCell_;
 * @param comment_;

 */
  public  UserPublicBean( Integer clientId_, String userId_, Date lastUpdate_, String lastName_, String firstName_, String middleName_, String officeAddress1_, String officeAddress2_, String officeCity_, String officeState_, String officeZip_, String title_, String suffix_, String officePhone_, String fax_, String officeCell_, String comment_){
	 setClientId( clientId_);
	 setUserId( userId_);
	 setLastUpdate( lastUpdate_);
	 setLastName( lastName_);
	 setFirstName( firstName_);
	 setMiddleName( middleName_);
	 setOfficeAddress1( officeAddress1_);
	 setOfficeAddress2( officeAddress2_);
	 setOfficeCity( officeCity_);
	 setOfficeState( officeState_);
	 setOfficeZip( officeZip_);
	 setTitle( title_);
	 setSuffix( suffix_);
	 setOfficePhone( officePhone_);
	 setFax( fax_);
	 setOfficeCell( officeCell_);
	 setComment( comment_);
  }

  public  UserPublicBean(){
	 setClientId( null);
	 setUserId( null);
	 setLastUpdate( null);
	 setLastName( null);
	 setFirstName( null);
	 setMiddleName( null);
	 setOfficeAddress1( null);
	 setOfficeAddress2( null);
	 setOfficeCity( null);
	 setOfficeState( null);
	 setOfficeZip( null);
	 setTitle( null);
	 setSuffix( null);
	 setOfficePhone( null);
	 setFax( null);
	 setOfficeCell( null);
	 setComment( null);
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
     * Set the lastName. 
     * 
     * @param lastName the lastName     */  
    public void setLastName(String lastName_) {  
        lastName= lastName_;  
    }  
  
    /** 
     * Return the lastName. 
     * 
     * @return the lastName     */  
    public String getLastName() {  
        return lastName;  
    }  

    /** 
     * Set the firstName. 
     * 
     * @param firstName the firstName     */  
    public void setFirstName(String firstName_) {  
        firstName= firstName_;  
    }  
  
    /** 
     * Return the firstName. 
     * 
     * @return the firstName     */  
    public String getFirstName() {  
        return firstName;  
    }  

    /** 
     * Set the middleName. 
     * 
     * @param middleName the middleName     */  
    public void setMiddleName(String middleName_) {  
        middleName= middleName_;  
    }  
  
    /** 
     * Return the middleName. 
     * 
     * @return the middleName     */  
    public String getMiddleName() {  
        return middleName;  
    }  

    /** 
     * Set the officeAddress1. 
     * 
     * @param officeAddress1 the officeAddress1     */  
    public void setOfficeAddress1(String officeAddress1_) {  
        officeAddress1= officeAddress1_;  
    }  
  
    /** 
     * Return the officeAddress1. 
     * 
     * @return the officeAddress1     */  
    public String getOfficeAddress1() {  
        return officeAddress1;  
    }  

    /** 
     * Set the officeAddress2. 
     * 
     * @param officeAddress2 the officeAddress2     */  
    public void setOfficeAddress2(String officeAddress2_) {  
        officeAddress2= officeAddress2_;  
    }  
  
    /** 
     * Return the officeAddress2. 
     * 
     * @return the officeAddress2     */  
    public String getOfficeAddress2() {  
        return officeAddress2;  
    }  

    /** 
     * Set the officeCity. 
     * 
     * @param officeCity the officeCity     */  
    public void setOfficeCity(String officeCity_) {  
        officeCity= officeCity_;  
    }  
  
    /** 
     * Return the officeCity. 
     * 
     * @return the officeCity     */  
    public String getOfficeCity() {  
        return officeCity;  
    }  

    /** 
     * Set the officeState. 
     * 
     * @param officeState the officeState     */  
    public void setOfficeState(String officeState_) {  
        officeState= officeState_;  
    }  
  
    /** 
     * Return the officeState. 
     * 
     * @return the officeState     */  
    public String getOfficeState() {  
        return officeState;  
    }  

    /** 
     * Set the officeZip. 
     * 
     * @param officeZip the officeZip     */  
    public void setOfficeZip(String officeZip_) {  
        officeZip= officeZip_;  
    }  
  
    /** 
     * Return the officeZip. 
     * 
     * @return the officeZip     */  
    public String getOfficeZip() {  
        return officeZip;  
    }  

    /** 
     * Set the title. 
     * 
     * @param title the title     */  
    public void setTitle(String title_) {  
        title= title_;  
    }  
  
    /** 
     * Return the title. 
     * 
     * @return the title     */  
    public String getTitle() {  
        return title;  
    }  

    /** 
     * Set the suffix. 
     * 
     * @param suffix the suffix     */  
    public void setSuffix(String suffix_) {  
        suffix= suffix_;  
    }  
  
    /** 
     * Return the suffix. 
     * 
     * @return the suffix     */  
    public String getSuffix() {  
        return suffix;  
    }  

    /** 
     * Set the officePhone. 
     * 
     * @param officePhone the officePhone     */  
    public void setOfficePhone(String officePhone_) {  
        officePhone= officePhone_;  
    }  
  
    /** 
     * Return the officePhone. 
     * 
     * @return the officePhone     */  
    public String getOfficePhone() {  
        return officePhone;  
    }  

    /** 
     * Set the fax. 
     * 
     * @param fax the fax     */  
    public void setFax(String fax_) {  
        fax= fax_;  
    }  
  
    /** 
     * Return the fax. 
     * 
     * @return the fax     */  
    public String getFax() {  
        return fax;  
    }  

    /** 
     * Set the officeCell. 
     * 
     * @param officeCell the officeCell     */  
    public void setOfficeCell(String officeCell_) {  
        officeCell= officeCell_;  
    }  
  
    /** 
     * Return the officeCell. 
     * 
     * @return the officeCell     */  
    public String getOfficeCell() {  
        return officeCell;  
    }  

    /** 
     * Set the comment. 
     * 
     * @param comment the comment     */  
    public void setComment(String comment_) {  
        comment= comment_;  
    }  
  
    /** 
     * Return the comment. 
     * 
     * @return the comment     */  
    public String getComment() {  
        return comment;  
    }  



}