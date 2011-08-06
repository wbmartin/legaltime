
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.SecurityProfileService;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the SecurityProfile Beans.
 */
public class SecurityProfileServiceImpl extends RemoteServiceServlet
		implements SecurityProfileService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public SecurityProfileServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityProfileBean_ the bean to add
         * @return the updated bean
	 */






	public SecurityProfileBean insertSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityProfileBean> resultList  = new ArrayList<SecurityProfileBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select client_id, security_profile_id, profile_name, last_update from security_profile_iq('CHECK_AUTH',?,?,?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		ps.setString(++ndx,securityProfileBean_.getProfileName() );
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
			throw new GWTCustomException("Retrieving SecurityProfile Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param SecurityProfileBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public SecurityProfileBean updateSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityProfileBean> resultList  = new ArrayList<SecurityProfileBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_profile_id, profile_name, last_update  from security_profile_uq('CHECK_AUTH',?,?,?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setInt(++ndx,securityProfileBean_.getSecurityProfileId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setString(++ndx, securityProfileBean_.getProfileName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(securityProfileBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Updating securityProfile Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityProfileBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<SecurityProfileBean> resultList  = new ArrayList<SecurityProfileBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from security_profile_dq('CHECK_AUTH',?,?,? ,?,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId()); 
		ps.setInt(ndx++,securityProfileBean_.getSecurityProfileId() );

   		ps.setTimestamp(ndx++, new java.sql.Timestamp(securityProfileBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Retrieving SecurityProfile Records Failed", e);
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
	public ArrayList<SecurityProfileBean> selectSecurityProfile(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityProfileBean> resultList  = new ArrayList<SecurityProfileBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_profile_id, profile_name, last_update  from security_profile_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
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
			throw new GWTCustomException("Retrieving SecurityProfile Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param securityProfileId_ 
         * @return an arraylist of the beans
	 */
	public  SecurityProfileBean getSecurityProfileByPrKey(UserProfile userProfile_  		, Integer securityProfileId_ ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  SecurityProfileBean result  = new SecurityProfileBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_profile_id, profile_name, last_update  from security_profile_bypk('CHECK_AUTH',?,?,?,? );");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
 
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
			throw new GWTCustomException("Retrieving SecurityProfile Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the SecurityProfileBean that was converted
         */
 	public SecurityProfileBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          SecurityProfileBean bean = new SecurityProfileBean();

   		bean.setClientId(rs.getInt(1));
  		  if(rs.wasNull()){bean.setClientId(null);}
   		bean.setSecurityProfileId(rs.getInt(2));
  		  if(rs.wasNull()){bean.setSecurityProfileId(null);}
   		bean.setProfileName(rs.getString(3));
  		  if(rs.wasNull()){bean.setProfileName(null);}
   		bean.setLastUpdate(rs.getTimestamp(4));
  		  if(bean.getLastUpdate().equals(nullDate)){bean.setLastUpdate(null);}


          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the SecurityProfileBean that was converted
//         */
//	public SecurityProfileBean saveSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException{
//		if (  securityProfileBean_.getClientId() ==null ||  securityProfileBean_.getClientId() ==0   || securityProfileBean_.getSecurityProfileId() ==null ||  securityProfileBean_.getSecurityProfileId() ==0  ){
//			return insertSecurityProfileBean( userProfile_,  securityProfileBean_);
//		}else{
//			return updateSecurityProfileBean( userProfile_,  securityProfileBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param securityProfileBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<SecurityProfileBean> saveSecurityProfileBeanBatch(UserProfile userProfile_, ArrayList<SecurityProfileBean> securityProfileBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< securityProfileBeanList_.size(); ndx++){
//			securityProfileBeanList_.set(ndx, saveSecurityProfileBean(userProfile_,securityProfileBeanList_.get(ndx)));
//		}
//		return  securityProfileBeanList_;
//	}
//
}