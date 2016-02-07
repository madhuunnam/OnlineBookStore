package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InfoListingAction
 */
@WebServlet("/com/madhu/project/InfoListingAction")
public class InfoListingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoListingAction() {
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
		String FailureErrorMsg = "";
		String userEnteredStartDate = request.getParameter("startdate");
		String userEnteredEndDate = request.getParameter("enddate");

		List<OrderContains> infoList = new ArrayList<OrderContains>();

		 if ("".equals(userEnteredStartDate)
				|| "".equals(userEnteredEndDate)) {
		    FailureErrorMsg = "Error Msg: Please enter the Start and End Dates";
			request.setAttribute("FailureErrorMsg", FailureErrorMsg);
			String nextJSP = "/InfoListing.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);

		}

		else {
			try {
				PreparedStatement myStatement = null;
				String queryString = "";
				ResultSet myResultset = null;
				
				queryString = "select C.OrderID, B.BookID, C.UserID, B.Title,O.Book_price,O.Book_quan,OD.OrderDesc from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, ORDER_CONTAINS O, Book B  "
						+ " where OD.OrderStatusCode = C.OrderStatusCode and O.OrderID = C.OrderID and B.BookID = O.BookID  "
						+ "and C.OrderStatusCode = 2 and C.OrderDate BETWEEN ? and ? order by C.OrderID ";
				//queryString = "select C.OrderID , C.UserID, C.OrderQuantity,C.OrderPrice  from CUSTOMER_ORDER C  "
						//+ "where   C.OrderStatusCode = 2 "
						//+ " and C.OrderDate BETWEEN ? and ? ";
				myStatement = conn.prepareStatement(queryString);
				myStatement.setString(1, userEnteredStartDate);
				myStatement.setString(2, userEnteredEndDate);
				myResultset = myStatement.executeQuery();

				while (myResultset.next()) {
					OrderContains orderContainsObjectInfo = new OrderContains();
					
					int orderId = myResultset.getInt("OrderID");
					int userId = myResultset.getInt("UserID");
					int bookId = myResultset.getInt("BookID");
					String title = myResultset.getString("Title");	
					//int date = myResultset.getDate("OrderDate");
					int orderedTotalPriceOfBook = myResultset.getInt("Book_price");
					int orderedCopiesOfBook = myResultset.getInt("Book_quan");
					String orderStatus = myResultset.getString("OrderDesc");
					
					orderContainsObjectInfo.setUserId(userId);
					orderContainsObjectInfo.setTitle(title);
					orderContainsObjectInfo.setBookPrice(orderedTotalPriceOfBook);
					orderContainsObjectInfo.setBookQty(orderedCopiesOfBook);
					orderContainsObjectInfo.setOrderId(orderId);
					orderContainsObjectInfo.setOrderDesc(orderStatus);
					orderContainsObjectInfo.setBookId(bookId);
					
					infoList.add(orderContainsObjectInfo);
					request.setAttribute("infoList", infoList);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		String nextJSP = "/InfoListing.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
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
