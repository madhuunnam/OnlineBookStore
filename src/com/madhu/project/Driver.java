package com.madhu.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public List<Users> dbconn() {
		String username;
		List<Users> userList = new ArrayList<Users>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:8889/mysql", "root", "root");

			Statement myStatement = myConn.createStatement();

			ResultSet myResultset = myStatement
					.executeQuery("select * from Login");
			while(myResultset.next())
			{
		    username =  myResultset.getString("Username");
		    System.out.println("names in the list:" +username);
			Users user = new Users();
			user.setUserName(username);
			userList.add(user);
			//System.out.println("Users"  + user);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}
		return userList;
	}
	 
}
