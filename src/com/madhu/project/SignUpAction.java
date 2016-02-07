package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpAction
 */
@WebServlet("/com/madhu/project/SignUpAction")
public class SignUpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String failureMsg = "";
		String userEnteredUsername = request.getParameter("username");
		String userEnteredPassword = request.getParameter("password");
		String userEnteredFirstname = request.getParameter("Firstname");
		String userEnteredLastname = request.getParameter("Lastname");
		String userRole = "0";
		boolean matchFlag = false;
		boolean emptyFlag = false;

		if ("".equals(userEnteredUsername) || "".equals(userEnteredPassword)
				|| "".equals(userEnteredFirstname)
				|| "".equals(userEnteredLastname)) {
			emptyFlag = true;
			failureMsg = "Error Msg: Please enter all the four fields";
			request.setAttribute("FailureMsg", failureMsg);
		}
			
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		PreparedStatement myStatement = null;
		String queryString = "";
		Statement myStatementUsersFromDb = null;
		ResultSet myResultsetUsersFromDb = null;
	

		try {
			myStatementUsersFromDb = conn.createStatement();
			myResultsetUsersFromDb = myStatementUsersFromDb
					.executeQuery("select UserName from USER_DETAILS");

			while (myResultsetUsersFromDb.next()) {
				String userName = myResultsetUsersFromDb.getString("UserName");

				if (userEnteredUsername.equals(userName)) {
					matchFlag = true;
					failureMsg = "Error Msg: Username already exists. Please try another username";
					request.setAttribute("FailureMsg", failureMsg);
					System.out.println("User Already Exists");
					break;
				}
			}
		
			if (matchFlag == false && emptyFlag == false) {

				queryString = "INSERT INTO USER_DETAILS(`UserName`,`Password`,`FName`,`LName`,`Role`) values (?,?,?,?,?)";
				myStatement = conn.prepareStatement(queryString);
				myStatement.setString(1, userEnteredUsername);
				myStatement.setString(2, userEnteredPassword);
				myStatement.setString(3, userEnteredFirstname);
				myStatement.setString(4, userEnteredLastname);
				myStatement.setString(5, userRole);

				myStatement.executeUpdate();

				String nextJSP = "/LoginPage.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			} else {
				String nextJSP = "/SignUpPage.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
