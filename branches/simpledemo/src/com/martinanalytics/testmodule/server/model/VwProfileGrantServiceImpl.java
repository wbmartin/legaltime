
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.VwProfileGrantService;
import com.martinanalytics.testmodule.client.model.bean.VwProfileGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the VwProfileGrant Beans.
 */
public class VwProfileGrantServiceImpl extends RemoteServiceServlet
		implements VwProfileGrantService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public VwProfileGrantServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param vwProfileGrantBean_ the bean to add
         * @return the updated bean
	 */






	public VwProfileGrantBean insertVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<VwProfileGrantBean> resultList  = new ArrayList<VwProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select client_id, security_profile_id, profile_name, security_privilege_id, priv_name from vw_profile_grant_iq('CHECK_AUTH',?,?,?, ?, ?, ?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		ps.setInt(++ndx,vwProfileGrantBean_.getSecurityProfileId() );

		ps.setString(++ndx,vwProfileGrantBean_.getProfileName() );

		ps.setInt(++ndx,vwProfileGrantBean_.getSecurityPrivilegeId() );

		ps.setString(++ndx,vwProfileGrantBean_.getPrivName() );
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
			throw new GWTCustomException("Retrieving VwProfileGrant Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param VwProfileGrantBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public VwProfileGrantBean updateVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<VwProfileGrantBean> resultList  = new ArrayList<VwProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_profile_id, profile_name, security_privilege_id, priv_name  from vw_profile_grant_uq('CHECK_AUTH',?,?,?, ?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setInt(++ndx,vwProfileGrantBean_.getSecurityProfileId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setString(++ndx, vwProfileGrantBean_.getProfileName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setInt(++ndx,vwProfileGrantBean_.getSecurityPrivilegeId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setString(++ndx, vwProfileGrantBean_.getPrivName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
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
			throw new GWTCustomException("Updating vwProfileGrant Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param vwProfileGrantBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<VwProfileGrantBean> resultList  = new ArrayList<VwProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from vw_profile_grant_dq('CHECK_AUTH',?,?,? ,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId());
   		ps.setTimestamp(ndx++, new java.sql.Timestamp(vwProfileGrantBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Retrieving VwProfileGrant Records Failed", e);
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
	public ArrayList<VwProfileGrantBean> selectVwProfileGrant(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<VwProfileGrantBean> resultList  = new ArrayList<VwProfileGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_profile_id, profile_name, security_privilege_id, priv_name  from vw_profile_grant_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
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
			throw new GWTCustomException("Retrieving VwProfileGrant Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param vwProfileGrantId_ 
         * @return an arraylist of the beans
	 */
	public  VwProfileGrantBean getVwProfileGrantByPrKey(UserProfile userProfile_   ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  VwProfileGrantBean result  = new VwProfileGrantBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, security_profile_id, profile_name, security_privilege_id, priv_name  from vw_profile_grant_bypk('CHECK_AUTH',?,?,? );");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());

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
			throw new GWTCustomException("Retrieving VwProfileGrant Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the VwProfileGrantBean that was converted
         */
 	public VwProfileGrantBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          VwProfileGrantBean bean = new VwProfileGrantBean();


 		bean.setClientId(rs.getInt(1));
		  if(rs.wasNull()){bean.setClientId(null);}
 		bean.setSecurityProfileId(rs.getInt(2));
		  if(rs.wasNull()){bean.setSecurityProfileId(null);}
 		bean.setProfileName(rs.getString(3));
		  if(rs.wasNull()){bean.setProfileName(null);}
 		bean.setSecurityPrivilegeId(rs.getInt(4));
		  if(rs.wasNull()){bean.setSecurityPrivilegeId(null);}
 		bean.setPrivName(rs.getString(5));
		  if(rs.wasNull()){bean.setPrivName(null);}


          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the VwProfileGrantBean that was converted
//         */
//	public VwProfileGrantBean saveVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException{
//		if (  vwProfileGrantBean_.getClientId() ==null ||  vwProfileGrantBean_.getClientId() ==0   || vwProfileGrantBean_.getVwProfileGrantId() ==null ||  vwProfileGrantBean_.getVwProfileGrantId() ==0  ){
//			return insertVwProfileGrantBean( userProfile_,  vwProfileGrantBean_);
//		}else{
//			return updateVwProfileGrantBean( userProfile_,  vwProfileGrantBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param vwProfileGrantBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<VwProfileGrantBean> saveVwProfileGrantBeanBatch(UserProfile userProfile_, ArrayList<VwProfileGrantBean> vwProfileGrantBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< vwProfileGrantBeanList_.size(); ndx++){
//			vwProfileGrantBeanList_.set(ndx, saveVwProfileGrantBean(userProfile_,vwProfileGrantBeanList_.get(ndx)));
//		}
//		return  vwProfileGrantBeanList_;
//	}
//
}
