
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.UserPublicService;
import com.martinanalytics.testmodule.client.model.bean.UserPublicBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the UserPublic Beans.
 */
public class UserPublicServiceImpl extends RemoteServiceServlet
		implements UserPublicService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public UserPublicServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param userPublicBean_ the bean to add
         * @return the updated bean
	 */






	public UserPublicBean insertUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<UserPublicBean> resultList  = new ArrayList<UserPublicBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select client_id, user_id, last_update, last_name, first_name, middle_name, office_address1, office_address2, office_city, office_state, office_zip, title, suffix, office_phone, fax, office_cell, comment from user_public_iq('CHECK_AUTH',?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(++ndx, userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		ps.setString(++ndx,userPublicBean_.getUserId() );

		ps.setString(++ndx,userPublicBean_.getLastName() );

		ps.setString(++ndx,userPublicBean_.getFirstName() );

		ps.setString(++ndx,userPublicBean_.getMiddleName() );

		ps.setString(++ndx,userPublicBean_.getOfficeAddress1() );

		ps.setString(++ndx,userPublicBean_.getOfficeAddress2() );

		ps.setString(++ndx,userPublicBean_.getOfficeCity() );

		ps.setString(++ndx,userPublicBean_.getOfficeState() );

		ps.setString(++ndx,userPublicBean_.getOfficeZip() );

		ps.setString(++ndx,userPublicBean_.getTitle() );

		ps.setString(++ndx,userPublicBean_.getSuffix() );

		ps.setString(++ndx,userPublicBean_.getOfficePhone() );

		ps.setString(++ndx,userPublicBean_.getFax() );

		ps.setString(++ndx,userPublicBean_.getOfficeCell() );

		ps.setString(++ndx,userPublicBean_.getComment() );
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
			throw new GWTCustomException("Retrieving UserPublic Records Failed", e);
		}

	  }
	  return resultList.get(0);
	}


	/**
	 * Update a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param UserPublicBean_ the bean to update, new values come through this bean
         * @return the updated bean
	 */
	public UserPublicBean updateUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<UserPublicBean> resultList  = new ArrayList<UserPublicBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, user_id, last_update, last_name, first_name, middle_name, office_address1, office_address2, office_city, office_state, office_zip, title, suffix, office_phone, fax, office_cell, comment  from user_public_uq('CHECK_AUTH',?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(++ndx,  userProfile_.getClientId());
		ps.setString(++ndx,  userProfile_.getUserId());
		ps.setString(++ndx, userProfile_.getSessionId());
		try{
  			ps.setString(++ndx, userPublicBean_.getUserId());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setTimestamp(++ndx, new java.sql.Timestamp(userPublicBean_.getLastUpdate().getTime()));
  		}catch(NullPointerException nex){
  			ps.setDate(ndx, new java.sql.Date(0));
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getLastName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getFirstName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getMiddleName());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficeAddress1());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficeAddress2());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficeCity());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficeState());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficeZip());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getTitle());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getSuffix());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficePhone());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getFax());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getOfficeCell());
  		}catch(NullPointerException nex){
  			ps.setNull(ndx, java.sql.Types.NVARCHAR);
  		}
		try{
  			ps.setString(++ndx, userPublicBean_.getComment());
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
			throw new GWTCustomException("Updating userPublic Records Failed", e);
		}
	
	  }
	  return resultList.get(0);
	}


	/**
	 * delete a record from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param userPublicBean_ the bean to delete, only primary keys value
         * @return true if the delete was successful
	 */
	public Boolean deleteUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  Boolean result = false;
	  //ArrayList<UserPublicBean> resultList  = new ArrayList<UserPublicBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select * from user_public_dq('CHECK_AUTH',?,?,? ,?,?);");
		ps.setInt(ndx++,  userProfile_.getClientId());
		ps.setString(ndx++,  userProfile_.getUserId());
		ps.setString(ndx++, userProfile_.getSessionId()); 
		ps.setString(ndx++,userPublicBean_.getUserId() );

   		ps.setTimestamp(ndx++, new java.sql.Timestamp(userPublicBean_.getLastUpdate().getTime()));
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
			throw new GWTCustomException("Retrieving UserPublic Records Failed", e);
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
	public ArrayList<UserPublicBean> selectUserPublic(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<UserPublicBean> resultList  = new ArrayList<UserPublicBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, user_id, last_update, last_name, first_name, middle_name, office_address1, office_address2, office_city, office_state, office_zip, title, suffix, office_phone, fax, office_cell, comment  from user_public_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
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
			throw new GWTCustomException("Retrieving UserPublic Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}





	/**
	 * Retrieve the  the bean from the database by Primary Key
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param clientId_ 
	 * @param userPublicId_ 
         * @return an arraylist of the beans
	 */
	public  UserPublicBean getUserPublicByPrKey(UserProfile userProfile_  		, String userId_ ) throws GWTCustomException{
	  int ndx =0;
	  PreparedStatement ps;
	  ResultSet rs;
	  UserPublicBean result  = new UserPublicBean();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  client_id, user_id, last_update, last_name, first_name, middle_name, office_address1, office_address2, office_city, office_state, office_zip, title, suffix, office_phone, fax, office_cell, comment  from user_public_bypk('CHECK_AUTH',?,?,?,? );");
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
			throw new GWTCustomException("Retrieving UserPublic Records Failed", e);
		}
		
	
	  }
	  return result;
	}

	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the UserPublicBean that was converted
         */
 	public UserPublicBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          UserPublicBean bean = new UserPublicBean();


 		bean.setClientId(rs.getInt(1));
		  if(rs.wasNull()){bean.setClientId(null);}
 		bean.setUserId(rs.getString(2));
		  if(rs.wasNull()){bean.setUserId(null);}
 		bean.setLastUpdate(rs.getTimestamp(3));
		  if(bean.getLastUpdate().equals(nullDate)){bean.setLastUpdate(null);}

 		bean.setLastName(rs.getString(4));
		  if(rs.wasNull()){bean.setLastName(null);}
 		bean.setFirstName(rs.getString(5));
		  if(rs.wasNull()){bean.setFirstName(null);}
 		bean.setMiddleName(rs.getString(6));
		  if(rs.wasNull()){bean.setMiddleName(null);}
 		bean.setOfficeAddress1(rs.getString(7));
		  if(rs.wasNull()){bean.setOfficeAddress1(null);}
 		bean.setOfficeAddress2(rs.getString(8));
		  if(rs.wasNull()){bean.setOfficeAddress2(null);}
 		bean.setOfficeCity(rs.getString(9));
		  if(rs.wasNull()){bean.setOfficeCity(null);}
 		bean.setOfficeState(rs.getString(10));
		  if(rs.wasNull()){bean.setOfficeState(null);}
 		bean.setOfficeZip(rs.getString(11));
		  if(rs.wasNull()){bean.setOfficeZip(null);}
 		bean.setTitle(rs.getString(12));
		  if(rs.wasNull()){bean.setTitle(null);}
 		bean.setSuffix(rs.getString(13));
		  if(rs.wasNull()){bean.setSuffix(null);}
 		bean.setOfficePhone(rs.getString(14));
		  if(rs.wasNull()){bean.setOfficePhone(null);}
 		bean.setFax(rs.getString(15));
		  if(rs.wasNull()){bean.setFax(null);}
 		bean.setOfficeCell(rs.getString(16));
		  if(rs.wasNull()){bean.setOfficeCell(null);}
 		bean.setComment(rs.getString(17));
		  if(rs.wasNull()){bean.setComment(null);}


          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the UserPublicBean that was converted
//         */
//	public UserPublicBean saveUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException{
//		if (  userPublicBean_.getClientId() ==null ||  userPublicBean_.getClientId() ==0   || userPublicBean_.getUserPublicId() ==null ||  userPublicBean_.getUserPublicId() ==0  ){
//			return insertUserPublicBean( userProfile_,  userPublicBean_);
//		}else{
//			return updateUserPublicBean( userProfile_,  userPublicBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param userPublicBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<UserPublicBean> saveUserPublicBeanBatch(UserProfile userProfile_, ArrayList<UserPublicBean> userPublicBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< userPublicBeanList_.size(); ndx++){
//			userPublicBeanList_.set(ndx, saveUserPublicBean(userProfile_,userPublicBeanList_.get(ndx)));
//		}
//		return  userPublicBeanList_;
//	}
//
}
