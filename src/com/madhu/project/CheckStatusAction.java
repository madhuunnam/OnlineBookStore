package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckStatusAction
 */
@WebServlet("/com/madhu/project/CheckStatusAction")
public class CheckStatusAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckStatusAction() {
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
		Integer userId = null;
		userId = (Integer) request.getSession().getAttribute("userID");
		try {
			PreparedStatement myStatement = null;
			String queryString = "";
			ResultSet myResultset = null;
			queryString = "select B.Title, C.OrderID, OD.OrderDesc from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, "
					+ "Book B, ORDER_CONTAINS O where "
					+ " C.OrderStatusCode=OD.OrderStatusCode and "
					+ " O.OrderID = C.OrderID and O.BookID = B.BookID and C.UserID = ? order by C.OrderID";
			myStatement = conn.prepareStatement(queryString);
			myStatement.setInt(1, userId);
			myResultset = myStatement.executeQuery();
			List<OrderStatus> orderStatusList = new ArrayList<OrderStatus>();

			while (myResultset.next()) {
				String orderIdFromdb = myResultset.getString("OrderId");
				String orderDescFromdb = myResultset.getString("OrderDesc");
				String bookTitle = myResultset.getString("Title");
				OrderStatus orderStatusAndId = new OrderStatus();
				orderStatusAndId.setOrderId(orderIdFromdb);
				orderStatusAndId.setOrderDesc(orderDescFromdb);
				orderStatusAndId.setTitle(bookTitle);
				orderStatusList.add(orderStatusAndId);
			}
			
			request.setAttribute("Statusdesc", orderStatusList);
			String nextJSP = "/OrderStatusDisplayPage.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);

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
