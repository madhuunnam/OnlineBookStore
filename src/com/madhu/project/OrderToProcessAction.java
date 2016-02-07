package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class ProcessPurchaseAction
 */
@WebServlet("/com/madhu/project/OrderToProcessAction")
public class OrderToProcessAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderToProcessAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		
		try {
			Statement myStatement = null;
			ResultSet myResultset = null;
			myStatement = conn.createStatement();
			myResultset = myStatement.executeQuery("select C.OrderID, B.BookID, C.UserID, B.Title,B.Copies,O.Book_price,O.Book_quan,OD.OrderDesc from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, ORDER_CONTAINS O, Book B  "
					+ " where OD.OrderStatusCode = C.OrderStatusCode and O.OrderID = C.OrderID and B.BookID = O.BookID order by C.OrderID ");
			List<OrderContains> orderDetailsList = new ArrayList<OrderContains>();
			
			while(myResultset.next()){
				
				OrderContains orderContainsObject = new OrderContains();
				
				int orderId = myResultset.getInt("OrderID");
				int userId = myResultset.getInt("UserID");
				int bookId = myResultset.getInt("BookID");
				String title = myResultset.getString("Title");	
				int availableCopies = myResultset.getInt("copies");
				int orderedTotalPriceOfBook = myResultset.getInt("Book_price");
				int orderedCopiesOfBook = myResultset.getInt("Book_quan");
				String orderStatus = myResultset.getString("OrderDesc");
				
				orderContainsObject.setUserId(userId);
				orderContainsObject.setTitle(title);
				orderContainsObject.setBookPrice(orderedTotalPriceOfBook);
				orderContainsObject.setBookQty(orderedCopiesOfBook);
				orderContainsObject.setOrderId(orderId);
				orderContainsObject.setOrderDesc(orderStatus);
				orderContainsObject.setAvailableCopies(availableCopies);
				orderContainsObject.setBookId(bookId);
				
				orderDetailsList.add(orderContainsObject);
				request.setAttribute("orderList", orderDetailsList);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error occured ");
			e.printStackTrace();
		}
		
		
		String nextJSP = "/ProcessPurchase.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
