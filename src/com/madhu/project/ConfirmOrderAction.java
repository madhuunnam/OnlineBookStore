package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmOrderAction
 */
@WebServlet("/com/madhu/project/ConfirmOrderAction")
public class ConfirmOrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrderAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		PreparedStatement myStatement = null;
		PreparedStatement insertOrderContainStatement = null;
		String queryString = "";
		String queryStringForOrderContains = "";
		Statement orderIdStatement = null;
		ResultSet orderIdResultset = null;
		Integer userId = null;
		int orderId = 0;
		userId = (Integer) request.getSession().getAttribute("userID");
		CustomerOrder customerOrder = (CustomerOrder) request.getSession().getAttribute("customerOrder");
		

		try {
			 queryString= "Insert into CUSTOMER_ORDER (UserID,OrderQuantity,OrderPrice,OrderStatusCode,OrderDate,CardNo,StNo,city,state)"
			 		+ " values (?,?,?,?,?,?,?,?,?)";
			 
		
			myStatement = conn.prepareStatement(queryString);
			myStatement.setInt(1,userId);
			myStatement.setInt(2, customerOrder.getOrderQuantity());
			myStatement.setInt(3,customerOrder.getOrderPrice());
			myStatement.setInt(4, customerOrder.getOrderStatusCode());
			myStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			myStatement.setInt(6, customerOrder.getCardNo());
			myStatement.setString(7, customerOrder.getAddress());
			myStatement.setString(8, customerOrder.getCity());
			myStatement.setString(9, customerOrder.getState());
			
			myStatement.executeUpdate();
			
			orderIdStatement = conn.createStatement();
			orderIdResultset = orderIdStatement
					.executeQuery("select max(OrderID) as OrderID from CUSTOMER_ORDER");
			orderIdResultset.next();
			
			orderId = orderIdResultset.getInt("OrderID");
			
			List<OrderContains> orderContainsList = new ArrayList<OrderContains>();
			orderContainsList = customerOrder.getOrderContainsList();
			
			for (int i = 0; i < orderContainsList.size(); i++) {

				queryStringForOrderContains = "INSERT INTO ORDER_CONTAINS(OrderID,BookID,Book_price,Book_quan) "
						+ "values (?,?,?,?)";

				insertOrderContainStatement = conn
						.prepareStatement(queryStringForOrderContains);
				insertOrderContainStatement.setInt(1, orderId);
				insertOrderContainStatement.setInt(2, orderContainsList.get(i).getBookId());
				insertOrderContainStatement.setInt(3, orderContainsList.get(i).getTotalBookPrice());
				insertOrderContainStatement.setInt(4, orderContainsList.get(i).getBookQty());
				insertOrderContainStatement.executeUpdate();
				
			}
			
			
			
			RequestDispatcher dp = request
					.getRequestDispatcher("SearchAction");
			dp.forward(request, response);
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
