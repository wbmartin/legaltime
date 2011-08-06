
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.SecurityUserService;
import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the SecurityUser Beans.
 */
public class SecurityUserServiceImpl extends RemoteServiceServlet
		implements SecurityUserService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public SecurityUserServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityUserBean_ the bean to add
         * @return the updated bean
	 */






	public SecurityUserBean insertSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityUserBean> resultList  = new ArrayList<SecurityUserBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select client_id, user_id, password_enc, security_profile_id, session_id, session_expire_dt, active_yn, last_update from security_user_iq('CHECK_AUTH',?,?,?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		ps.setString(++ndx,securityUserBean_.getUserId() );

		ps.setString(++ndx,securityUserBean_.getPasswordEnc() );

		ps.setInt(++ndx,securityUserBean_.getSecurityProfileId() );

		ps.setString(++ndx,securityUserBean_.getSessionId() );

		ps.setDate(++ndx,new java.sql.Date(securityUserBean_.getSessionExpireDt().getTime()) );

		ps.setString(++ndx,securityUserBean_.getActiveYn() );
     		rs =  ps.executeQuery();
		
		while(rs.next()){
		  resultList.add(decodeRow(rs));
		}
	  }catch (Exception e) {
		e.printStackTrace();
		result = "FAIL";
		if(e.getMessage().equals("ERROR: Invalid Session -- Access Denied")){
			System.err.println("FiredCustomExceptions");
			throw new GWTCustomException("ERROR: Invalid Session -- Access Denied");
		}else{
			throw new GWTCustomException("Retrieving SecurityUser Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param SecurityUserBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public SecurityUserBean updateSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityUserBean> resultList  = new ArrayList<SecurityUserBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, user_id, password_enc, security_profile_id, session_id, session_expire_dt, active_yn, last_update  from security_user_uq('CHECK_AUTH',?,?,?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setString(++ndx, securityUserBean_.getUserId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, securityUserBean_.getPasswordEnc());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setInt(++ndx,securityUserBean_.getSecurityProfileId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setString(++ndx, securityUserBean_.getSessionId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(securityUserBean_.getSessionExpireDt().getTime()));
  		}catch(NullPointerException nex){
  			ps.setDate(ndx, new java.sql.Date(0));
  		}
		try{
  			ps.setString(++ndx, securityUserBean_.getActiveYn());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(securityUserBean_.getLastUpdate().getTime()));
  		}catch(NullPointerException nex){
  			ps.setDate(ndx, new java.sql.Date(0));
  		}
    		rs =  ps.executeQuery();
		
		while(rs.next()){
		  resultList.add(decodeRow(rs));
		}
	  }catch (Exception e) {
		e.printStackTrace();
		result = "FAIL";
		if(e.getMessage().equals("ERROR: Invalid Session -- Access Denied")){
			System.err.println("FiredCustomExceptions");
			throw new GWTCustomException("ERROR: Invalid Session -- Access Denied");
		}else{
			throw new GWTCustomException("Updating securityUser Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityUserBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<SecurityUserBean> resultList  = new ArrayList<SecurityUserBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from security_user_dq('CHECK_AUTH',?,?,? ,?,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId()); 
		ps.setString(ndx++,securityUserBean_.getUserId() );

   		ps.setTimestamp(ndx++, new java.sql.Timestamp(securityUserBean_.getLastUpdate().getTime()));
		rs =  ps.executeQuery();
		
		while(rs.next()){
		  result = rs.getBoolean(1);
		}
	  }catch (Exception e) {	
		e.printStackTrace();
		result = false;
		if(e.getMessage().equals("ERROR: Invalid Session -- Access Denied")){
			System.err.println("FiredCustomExceptions");
			throw new GWTCustomException("ERROR: Invalid Session -- Access Denied");
		}else{
			throw new GWTCustomException("Retrieving SecurityUser Records Failed", e);
		}
		
	  }


	  return result;

	
	}


	/**
	 * Retrieve the entire list of beans from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param whereClause_ the filter to apply to the list, should begin with "where"
	 * @param orderByClause_ the sorting order in standard SQL, should being with "order by"
         * @return an arraylist of the beans
	 */
	public ArrayList<SecurityUserBean> selectSecurityUser(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityUserBean> resultList  = new ArrayList<SecurityUserBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, user_id, password_enc, security_profile_id, session_id, session_expire_dt, active_yn, last_update  from security_user_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
		ps.setInt(ndx++, userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId());
		ps.setString(ndx++, whereClause_);
		ps.setString(ndx++, orderByClause_);
		ps.setInt(ndx++,rowLimit_);
		ps.setInt(ndx++,startRow_);
		rs =  ps.executeQuery();
		while(rs.next()){
		  resultList.add(decodeRow(rs));
		}
	  }catch (Exception e) {
		e.printStackTrace();
		if(e.getMessage().equals("ERROR: Invalid Session -- Access Denied")){
			System.err.println("FiredCustomExceptions");
			throw new GWTCustomException("ERROR: Invalid Session -- Access Denied");
		}else{
			throw new GWTCustomException("Retrieving SecurityUser Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param securityUserId_ 
         * @return an arraylist of the beans
	 */
	public  SecurityUserBean getSecurityUserByPrKey(UserProfile userProfile_  		, String userId_ ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  SecurityUserBean result  = new SecurityUserBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, user_id, password_enc, security_profile_id, session_id, session_expire_dt, active_yn, last_update  from security_user_bypk('CHECK_AUTH',?,?,?,? );");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
 
		ps.setString(++ndx,userId_ );
		rs =  ps.executeQuery();
		if(rs.next()){
		  result =(decodeRow(rs));
		}
	  }catch (Exception e) {
		e.printStackTrace();
		if(e.getMessage().equals("ERROR: Invalid Session -- Access Denied")){
			System.err.println("FiredCustomExceptions");
			throw new GWTCustomException("ERROR: Invalid Session -- Access Denied");
		}else{
			throw new GWTCustomException("Retrieving SecurityUser Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the SecurityUserBean that was converted
         */
 	public SecurityUserBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          SecurityUserBean bean = new SecurityUserBean();


 		bean.setClientId(rs.getInt(1));
		  if(rs.wasNull()){bean.setClientId(null);}
 		bean.setUserId(rs.getString(2));
		  if(rs.wasNull()){bean.setUserId(null);}
 		bean.setPasswordEnc(rs.getString(3));
		  if(rs.wasNull()){bean.setPasswordEnc(null);}
 		bean.setSecurityProfileId(rs.getInt(4));
		  if(rs.wasNull()){bean.setSecurityProfileId(null);}
 		bean.setSessionId(rs.getString(5));
		  if(rs.wasNull()){bean.setSessionId(null);}
 		bean.setSessionExpireDt(rs.getTimestamp(6));
		  if(bean.getSessionExpireDt().equals(nullDate)){bean.setSessionExpireDt(null);}

 		bean.setActiveYn(rs.getString(7));
		  if(rs.wasNull()){bean.setActiveYn(null);}
 		bean.setLastUpdate(rs.getTimestamp(8));
		  if(bean.getLastUpdate().equals(nullDate)){bean.setLastUpdate(null);}



          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the SecurityUserBean that was converted
//         */
//	public SecurityUserBean saveSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException{
//		if (  securityUserBean_.getClientId() ==null ||  securityUserBean_.getClientId() ==0   || securityUserBean_.getSecurityUserId() ==null ||  securityUserBean_.getSecurityUserId() ==0  ){
//			return insertSecurityUserBean( userProfile_,  securityUserBean_);
//		}else{
//			return updateSecurityUserBean( userProfile_,  securityUserBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param securityUserBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<SecurityUserBean> saveSecurityUserBeanBatch(UserProfile userProfile_, ArrayList<SecurityUserBean> securityUserBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< securityUserBeanList_.size(); ndx++){
//			securityUserBeanList_.set(ndx, saveSecurityUserBean(userProfile_,securityUserBeanList_.get(ndx)));
//		}
//		return  securityUserBeanList_;
//	}
//
}
