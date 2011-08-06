
package com.martinanalytics.testmodule.server.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.martinanalytics.testmodule.client.model.GWTCustomException;
import com.martinanalytics.testmodule.client.model.VwUserGrantService;
import com.martinanalytics.testmodule.client.model.bean.VwUserGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.server.model.DatabaseManager;
/**
 * Exposes CRUD and business logic fucntionality for the VwUserGrant Beans.
 */
public class VwUserGrantServiceImpl extends RemoteServiceServlet
		implements VwUserGrantService{
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	private static final long serialVersionUID = 1L;
	public VwUserGrantServiceImpl() {
		super();
		
	}
	/**
	 * Add a record the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param vwUserGrantBean_ the bean to add
         * @return the updated bean
	 */






	

	/**
	 * Retrieve the entire list of beans from the database
	 * @param userProfile_ the credentials to use for authentication and authorization
	 * @param whereClause_ the filter to apply to the list, should begin with "where"
	 * @param orderByClause_ the sorting order in standard SQL, should being with "order by"
         * @return an arraylist of the beans
	 */
	public ArrayList<VwUserGrantBean> selectVwUserGrant(UserProfile userProfile_, String whereClause_, String orderByClause_, int rowLimit_, int startRow_) throws GWTCustomException{
	  int ndx =1;
	  PreparedStatement ps;
	  ResultSet rs;
	  String result;
	  ArrayList<VwUserGrantBean> resultList  = new ArrayList<VwUserGrantBean>();
	  try {
		ps = databaseManager.getConnection().prepareStatement("select  user_id, client_id, profile_name, priv_name  from vw_user_grant_sq('CHECK_AUTH',?,?,?,?,?,?,?) ;");
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
			throw new GWTCustomException("Retrieving VwUserGrant Records Failed", e);
		}
		
	  }
	  
	  return resultList;
	}






	/**
	 * Convert a result set a bean
         * @param rs the result set to be converted
	 * @return the VwUserGrantBean that was converted
         */
 	public VwUserGrantBean decodeRow(ResultSet rs) throws SQLException{
	  java.util.Date nullDate = new java.util.Date(0);
          VwUserGrantBean bean = new VwUserGrantBean();


 		bean.setUserId(rs.getString(1));
		  if(rs.wasNull()){bean.setUserId(null);}
 		bean.setClientId(rs.getInt(2));
		  if(rs.wasNull()){bean.setClientId(null);}
 		bean.setProfileName(rs.getString(3));
		  if(rs.wasNull()){bean.setProfileName(null);}
 		bean.setPrivName(rs.getString(4));
		  if(rs.wasNull()){bean.setPrivName(null);}


          return bean;
        }

//	/**
//	 * Convert a result set a bean
//         * @param rs the result set to be converted
//	 * @return the VwUserGrantBean that was converted
//         */
//	public VwUserGrantBean saveVwUserGrantBean(UserProfile userProfile_, VwUserGrantBean vwUserGrantBean_) throws GWTCustomException{
//		if (  vwUserGrantBean_.getClientId() ==null ||  vwUserGrantBean_.getClientId() ==0   || vwUserGrantBean_.getVwUserGrantId() ==null ||  vwUserGrantBean_.getVwUserGrantId() ==0  ){
//			return insertVwUserGrantBean( userProfile_,  vwUserGrantBean_);
//		}else{
//			return updateVwUserGrantBean( userProfile_,  vwUserGrantBean_);
//		}
//
//	}
//	/**
//	 * Save a record to the database.  If the primary keys are null, an insert will occur, otherwise an update will occur
//	 * @param userProfile_ the credentials to use for authentication and authorization
//	 * @param vwUserGrantBeanList_ the list of beans to save
//         * @return an arraylist of the beans, updated with primary keys and last updates.
//	 */
//	public ArrayList<VwUserGrantBean> saveVwUserGrantBeanBatch(UserProfile userProfile_, ArrayList<VwUserGrantBean> vwUserGrantBeanList_) throws GWTCustomException{
//		for(int ndx =0; ndx< vwUserGrantBeanList_.size(); ndx++){
//			vwUserGrantBeanList_.set(ndx, saveVwUserGrantBean(userProfile_,vwUserGrantBeanList_.get(ndx)));
//		}
//		return  vwUserGrantBeanList_;
//	}
//
}
