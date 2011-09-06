
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.SysCodeService;
import com.martinanalytics.testmodule.client.model.bean.SysCodeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the SysCode Beans.
 */
public class SysCodeServiceImpl extends RemoteServiceServlet
		implements SysCodeService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public SysCodeServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param sysCodeBean_ the bean to add
         * @return the updated bean
	 */






	public SysCodeBean insertSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SysCodeBean> resultList  = new ArrayList<SysCodeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select sys_code_id, code_type, key, value, last_update, notes from sys_code_iq('CHECK_AUTH',?,?,?, ?, ?, ?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
		  ps.setString(++ndx,sysCodeBean_.getCodeType() );
		}catch(Exception e_){
		  ps.setString(ndx,null);
		  Log.debug("Warning - threw an Exception:" + e_.getMessage() + " on codeType");
		}

		try{
		  ps.setString(++ndx,sysCodeBean_.getKey() );
		}catch(Exception e_){
		  ps.setString(ndx,null);
		  Log.debug("Warning - threw an Exception:" + e_.getMessage() + " on key");
		}

		try{
		  ps.setString(++ndx,sysCodeBean_.getValue() );
		}catch(Exception e_){
		  ps.setString(ndx,null);
		  Log.debug("Warning - threw an Exception:" + e_.getMessage() + " on value");
		}

		try{
		  ps.setString(++ndx,sysCodeBean_.getNotes() );
		}catch(Exception e_){
		  ps.setString(ndx,null);
		  Log.debug("Warning - threw an Exception:" + e_.getMessage() + " on notes");
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
			throw new GWTCustomException("Retrieving SysCode Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param SysCodeBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public SysCodeBean updateSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SysCodeBean> resultList  = new ArrayList<SysCodeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  sys_code_id, code_type, key, value, last_update, notes  from sys_code_uq('CHECK_AUTH',?,?,?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setInt(++ndx,sysCodeBean_.getSysCodeId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.INTEGER);
  		}
		try{
  			ps.setString(++ndx, sysCodeBean_.getCodeType());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, sysCodeBean_.getKey());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, sysCodeBean_.getValue());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(sysCodeBean_.getLastUpdate().getTime()));
  		}catch(NullPointerException nex){
  			ps.setDate(ndx, new java.sql.Date(0));
  		}
		try{
  			ps.setString(++ndx, sysCodeBean_.getNotes());
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
			throw new GWTCustomException("Updating sysCode Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param sysCodeBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<SysCodeBean> resultList  = new ArrayList<SysCodeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from sys_code_dq('CHECK_AUTH',?,?,? ,?,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId()); 
		ps.setInt(ndx++,sysCodeBean_.getSysCodeId() );

   		ps.setTimestamp(ndx++, new java.sql.Timestamp(sysCodeBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Retrieving SysCode Records Failed", e);
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
	public ArrayList<SysCodeBean> selectSysCode(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<SysCodeBean> resultList  = new ArrayList<SysCodeBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  sys_code_id, code_type, key, value, last_update, notes  from sys_code_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
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
			throw new GWTCustomException("Retrieving SysCode Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param sysCodeId_ 
         * @return an arraylist of the beans
	 */
	public  SysCodeBean getSysCodeByPrKey(UserProfile userProfile_  		, Integer sysCodeId_ ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  SysCodeBean result  = new SysCodeBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  sys_code_id, code_type, key, value, last_update, notes  from sys_code_bypk('CHECK_AUTH',?,?,?,? );");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
 
		ps.setInt(++ndx,sysCodeId_ );
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
			throw new GWTCustomException("Retrieving SysCode Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the SysCodeBean that was converted
         */
 	public SysCodeBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          SysCodeBean bean = new SysCodeBean();


 		bean.setSysCodeId(rs.getInt(1));

		  if(rs.wasNull()){bean.setSysCodeId(null);}
 		bean.setCodeType(rs.getString(2));

		  if(rs.wasNull()){bean.setCodeType(null);}
 		bean.setKey(rs.getString(3));

		  if(rs.wasNull()){bean.setKey(null);}
 		bean.setValue(rs.getString(4));

		  if(rs.wasNull()){bean.setValue(null);}
 		bean.setLastUpdate(rs.getTimestamp(5));
		  //if(bean.getLastUpdate().equals(nullDate)){bean.setLastUpdate(null);}
		  if(rs.wasNull()){bean.setLastUpdate(null);}
 		bean.setNotes(rs.getString(6));

		  if(rs.wasNull()){bean.setNotes(null);}


          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the SysCodeBean that was converted
//         */
//	public SysCodeBean saveSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException{
//		if (  sysCodeBean_.getClientId() ==null ||  sysCodeBean_.getClientId() ==0   || sysCodeBean_.getSysCodeId() ==null ||  sysCodeBean_.getSysCodeId() ==0  ){
//			return insertSysCodeBean( userProfile_,  sysCodeBean_);
//		}else{
//			return updateSysCodeBean( userProfile_,  sysCodeBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param sysCodeBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<SysCodeBean> saveSysCodeBeanBatch(UserProfile userProfile_, ArrayList<SysCodeBean> sysCodeBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< sysCodeBeanList_.size(); ndx++){
//			sysCodeBeanList_.set(ndx, saveSysCodeBean(userProfile_,sysCodeBeanList_.get(ndx)));
//		}
//		return  sysCodeBeanList_;
//	}
//
}
