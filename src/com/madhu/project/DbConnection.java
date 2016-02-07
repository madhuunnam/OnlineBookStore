package com.madhu.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection getDbConnection(){
		Connection myConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:8889/DBMSProject8", "root", "root");
           
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}    
        return myConn;
		}
}
