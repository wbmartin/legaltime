package com.martinanalytics.testmodule.client.model;

public class SQLGarage {

	public static String getLoginAuthenticationSQL(){
        String sqlResult ="select * from  initSession(?,?);";
        // 1 - username
        // 2 - password
        
        return sqlResult;
	}

}
