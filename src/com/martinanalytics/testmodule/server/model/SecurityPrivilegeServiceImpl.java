
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.SecurityPrivilegeService;
import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the SecurityPrivilege Beans.
 */
public class SecurityPrivilegeServiceImpl extends RemoteServiceServlet
		implements SecurityPrivilegeService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public SecurityPrivilegeServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityPrivilegeBean_ the bean to add
         * @return the updated bean
	 */






	public SecurityPrivilegeBean insertSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityPrivilegeBean> resultList  = new ArrayList<SecurityPrivilegeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select client_id, security_privilege_id, priv_name, description, last_update from security_privilege_iq('CHECK_AUTH',?,?,?, ?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		ps.setString(++ndx,securityPrivilegeBean_.getPrivName() );

		ps.setString(++ndx,securityPrivilegeBean_.getDescription() );
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
			throw new GWTCustomException("Retrieving SecurityPrivilege Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param SecurityPrivilegeBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public SecurityPrivilegeBean updateSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityPrivilegeBean> resultList  = new ArrayList<SecurityPrivilegeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_privilege_id, priv_name, description, last_update  from security_privilege_uq('CHECK_AUTH',?,?,?, ?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setInt(++ndx,securityPrivilegeBean_.getSecurityPrivilegeId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setString(++ndx, securityPrivilegeBean_.getPrivName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, securityPrivilegeBean_.getDescription());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(securityPrivilegeBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Updating securityPrivilege Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param securityPrivilegeBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<SecurityPrivilegeBean> resultList  = new ArrayList<SecurityPrivilegeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from security_privilege_dq('CHECK_AUTH',?,?,? ,?,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId()); 
		ps.setInt(ndx++,securityPrivilegeBean_.getSecurityPrivilegeId() );

   		ps.setTimestamp(ndx++, new java.sql.Timestamp(securityPrivilegeBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Retrieving SecurityPrivilege Records Failed", e);
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
	public ArrayList<SecurityPrivilegeBean> selectSecurityPrivilege(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SecurityPrivilegeBean> resultList  = new ArrayList<SecurityPrivilegeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_privilege_id, priv_name, description, last_update  from security_privilege_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
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
			throw new GWTCustomException("Retrieving SecurityPrivilege Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param securityPrivilegeId_ 
         * @return an arraylist of the beans
	 */
	public  SecurityPrivilegeBean getSecurityPrivilegeByPrKey(UserProfile userProfile_  		, Integer securityPrivilegeId_ ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  SecurityPrivilegeBean result  = new SecurityPrivilegeBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_privilege_id, priv_name, description, last_update  from security_privilege_bypk('CHECK_AUTH',?,?,?,? );");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
 
		ps.setInt(++ndx,securityPrivilegeId_ );
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
			throw new GWTCustomException("Retrieving SecurityPrivilege Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the SecurityPrivilegeBean that was converted
         */
 	public SecurityPrivilegeBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          SecurityPrivilegeBean bean = new SecurityPrivilegeBean();


 		bean.setClientId(rs.getInt(1));
		  if(rs.wasNull()){bean.setClientId(null);}
 		bean.setSecurityPrivilegeId(rs.getInt(2));
		  if(rs.wasNull()){bean.setSecurityPrivilegeId(null);}
 		bean.setPrivName(rs.getString(3));
		  if(rs.wasNull()){bean.setPrivName(null);}
 		bean.setDescription(rs.getString(4));
		  if(rs.wasNull()){bean.setDescription(null);}
 		bean.setLastUpdate(rs.getTimestamp(5));
		  if(bean.getLastUpdate().equals(nullDate)){bean.setLastUpdate(null);}



          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the SecurityPrivilegeBean that was converted
//         */
//	public SecurityPrivilegeBean saveSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException{
//		if (  securityPrivilegeBean_.getClientId() ==null ||  securityPrivilegeBean_.getClientId() ==0   || securityPrivilegeBean_.getSecurityPrivilegeId() ==null ||  securityPrivilegeBean_.getSecurityPrivilegeId() ==0  ){
//			return insertSecurityPrivilegeBean( userProfile_,  securityPrivilegeBean_);
//		}else{
//			return updateSecurityPrivilegeBean( userProfile_,  securityPrivilegeBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param securityPrivilegeBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<SecurityPrivilegeBean> saveSecurityPrivilegeBeanBatch(UserProfile userProfile_, ArrayList<SecurityPrivilegeBean> securityPrivilegeBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< securityPrivilegeBeanList_.size(); ndx++){
//			securityPrivilegeBeanList_.set(ndx, saveSecurityPrivilegeBean(userProfile_,securityPrivilegeBeanList_.get(ndx)));
//		}
//		return  securityPrivilegeBeanList_;
//	}
//
}
