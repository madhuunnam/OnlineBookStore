package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlaceOrderAction
 */
@WebServlet("/com/madhu/project/ShippingDetailsAction")
public class ShippingDetailsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShippingDetailsAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		PreparedStatement myStatement = null;
		String queryString = "";

		String userEnteredAddress= request.getParameter("Address");
		String userEnteredCity=request.getParameter("City");
		String userEnteredState= request.getParameter("State");

		if (conn != null) {
			try {
			
			queryString = "Insert into SHIPPING_ADDRESS ('StNo','city','state') values (?,?,?,?)";
			
			myStatement = conn.prepareStatement(queryString);
			
			myStatement.setString(1, userEnteredAddress);
			myStatement.setString(2, userEnteredCity);
			myStatement.setString(3, userEnteredState);
		
			
			myStatement.executeUpdate();
			 
			}catch (SQLException e) {
				System.out.println("Error occured");
				e.printStackTrace();
			}

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
