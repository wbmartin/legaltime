
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.SecurityProfileGrantService;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the SecurityProfileGrant Beans.
 */
public class SecurityProfileGrantServiceImpl extends RemoteServiceServlet
		implements SecurityProfileGrantService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public SecurityProfileGrantServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityProfileGrantBean_ the bean to add
         * @return the updated bean
	 */






	public SecurityProfileGrantBean insertSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityProfileGrantBean> resultList  = new ArrayList<SecurityProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select client_id, security_privilege_id, security_profile_id, last_update from security_profile_grant_iq('CHECK_AUTH',?,?,?, ?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		ps.setInt(++ndx,securityProfileGrantBean_.getSecurityPrivilegeId() );

		ps.setInt(++ndx,securityProfileGrantBean_.getSecurityProfileId() );
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
			throw new GWTCustomException("Retrieving SecurityProfileGrant Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param SecurityProfileGrantBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public SecurityProfileGrantBean updateSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityProfileGrantBean> resultList  = new ArrayList<SecurityProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_privilege_id, security_profile_id, last_update  from security_profile_grant_uq('CHECK_AUTH',?,?,?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setInt(++ndx,securityProfileGrantBean_.getSecurityPrivilegeId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setInt(++ndx,securityProfileGrantBean_.getSecurityProfileId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(securityProfileGrantBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Updating securityProfileGrant Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityProfileGrantBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<SecurityProfileGrantBean> resultList  = new ArrayList<SecurityProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from security_profile_grant_dq('CHECK_AUTH',?,?,? ,?,?,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId()); 
		ps.setInt(ndx++,securityProfileGrantBean_.getSecurityPrivilegeId() );
 
		ps.setInt(ndx++,securityProfileGrantBean_.getSecurityProfileId() );

   		ps.setTimestamp(ndx++, new java.sql.Timestamp(securityProfileGrantBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Retrieving SecurityProfileGrant Records Failed", e);
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
	public ArrayList<SecurityProfileGrantBean> selectSecurityProfileGrant(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityProfileGrantBean> resultList  = new ArrayList<SecurityProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_privilege_id, security_profile_id, last_update  from security_profile_grant_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
		ps.setInt(ndx++, userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId());
		ps.setString(ndx++, whereClause_);
		ps.setString(ndx++, orderByClause_);
		ps.setInt(ndx++,rowLimit_);
		ps.setInt(ndx++,startRow_);
	
//		System.err.println("Inputs:" +userProfile_.getClientId()+" "+ userProfile_.getUserId()
//				+" "+ userProfile_.getSessionId()
//				+" "+ whereClause_
//				+" "+ orderByClause_
//				+" "+rowLimit_
//				+" "+startRow_ +"******************8");
		rs =  ps.executeQuery();
		while(rs.next()){
		  resultList.add(decodeRow(rs));
		 // System.err.println("found securityProfile Grant");
		}
	  }catch (Exception e) {
		e.printStackTrace();
		if(e.getMessage().equals("ERROR: Invalid Session -- Access Denied")){
			System.err.println("FiredCustomExceptions");
			throw new GWTCustomException("ERROR: Invalid Session -- Access Denied");
		}else{
			throw new GWTCustomException("Retrieving SecurityProfileGrant Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param securityProfileGrantId_ 
         * @return an arraylist of the beans
	 */
	public  SecurityProfileGrantBean getSecurityProfileGrantByPrKey(UserProfile userProfile_  		, Integer securityPrivilegeId_		, Integer securityProfileId_ ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  SecurityProfileGrantBean result  = new SecurityProfileGrantBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_privilege_id, security_profile_id, last_update  from security_profile_grant_bypk('CHECK_AUTH',?,?,?,?,? );");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
 
		ps.setInt(++ndx,securityPrivilegeId_ ); 
		ps.setInt(++ndx,securityProfileId_ );
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
			throw new GWTCustomException("Retrieving SecurityProfileGrant Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the SecurityProfileGrantBean that was converted
         */
 	public SecurityProfileGrantBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          SecurityProfileGrantBean bean = new SecurityProfileGrantBean();


   		bean.setClientId(rs.getInt(1));
  		  if(rs.wasNull()){bean.setClientId(null);}
   		bean.setSecurityPrivilegeId(rs.getInt(2));
  		  if(rs.wasNull()){bean.setSecurityPrivilegeId(null);}
   		bean.setSecurityProfileId(rs.getInt(3));
  		  if(rs.wasNull()){bean.setSecurityProfileId(null);}
   		bean.setLastUpdate(rs.getTimestamp(4));
  		  if(bean.getLastUpdate() != null && bean.getLastUpdate().equals(nullDate)){bean.setLastUpdate(null);}

          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the SecurityProfileGrantBean that was converted
//         */
//	public SecurityProfileGrantBean saveSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException{
//		if (  securityProfileGrantBean_.getClientId() ==null ||  securityProfileGrantBean_.getClientId() ==0   || securityProfileGrantBean_.getSecurityProfileGrantId() ==null ||  securityProfileGrantBean_.getSecurityProfileGrantId() ==0  ){
//			return insertSecurityProfileGrantBean( userProfile_,  securityProfileGrantBean_);
//		}else{
//			return updateSecurityProfileGrantBean( userProfile_,  securityProfileGrantBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param securityProfileGrantBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<SecurityProfileGrantBean> saveSecurityProfileGrantBeanBatch(UserProfile userProfile_, ArrayList<SecurityProfileGrantBean> securityProfileGrantBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< securityProfileGrantBeanList_.size(); ndx++){
//			securityProfileGrantBeanList_.set(ndx, saveSecurityProfileGrantBean(userProfile_,securityProfileGrantBeanList_.get(ndx)));
//		}
//		return  securityProfileGrantBeanList_;
//	}
//
}