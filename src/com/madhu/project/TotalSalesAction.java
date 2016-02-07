package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TotalSalesAction
 */
@WebServlet("/com/madhu/project/TotalSalesAction")
public class TotalSalesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalSalesAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		String userEnteredMonth = request.getParameter("month");
		String userEnteredYear = request.getParameter("year");
		String setStartDate = null;
		String setEndDate = null;
		String FailureErrorMsg2 = "";
		
		if("".equals(userEnteredMonth) || "".equals(userEnteredYear)){
			FailureErrorMsg2 = "Error Msg: Please enter the Month and Year";
			request.setAttribute("FailureErrorMsg2", FailureErrorMsg2);
			String nextJSP = "/InfoListing.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
		
		else if (userEnteredMonth.equals("January")){
			setStartDate = userEnteredYear +"-01-01";
			setEndDate =  userEnteredYear +"-01-31";
		}
		else if(userEnteredMonth.equals("February")){
			
				int year = Integer.parseInt(userEnteredYear);
				int result = year/4;
			if (result ==0 )
			{
			setStartDate = userEnteredYear +"-02-01";
			setEndDate =  userEnteredYear +"-02-29";
			}
			else {
				setStartDate = userEnteredYear +"-01-01";
				setEndDate =  userEnteredYear +"-01-28";
			}
		}
		else if(userEnteredMonth.equals("March")){
			setStartDate = userEnteredYear +"-03-01";
			setEndDate =  userEnteredYear +"-03-31";
		}
		else if(userEnteredMonth.equals("April")){
			setStartDate = userEnteredYear +"-04-01";
			setEndDate =  userEnteredYear +"-04-30";
		}
		else if(userEnteredMonth.equals("May")){
			setStartDate = userEnteredYear +"-05-01";
			setEndDate =  userEnteredYear +"-05-31";
		}
		else if(userEnteredMonth.equals("June")){
			setStartDate = userEnteredYear +"-06-01";
			setEndDate =  userEnteredYear +"-06-30";
		}
		else if(userEnteredMonth.equals("July")){
			setStartDate = userEnteredYear +"-07-01";
			setEndDate =  userEnteredYear +"-07-31";
		}
		else if(userEnteredMonth.equals("August")){
			setStartDate = userEnteredYear +"-08-01";
			setEndDate =  userEnteredYear +"-08-31";
		}
		else if(userEnteredMonth.equals("September")){
			setStartDate = userEnteredYear +"-09-01";
			setEndDate =  userEnteredYear +"-09-30";
		}
		else if(userEnteredMonth.equals("October")){
			setStartDate = userEnteredYear +"-10-01";
			setEndDate =  userEnteredYear +"-10-31";
		}
		else if(userEnteredMonth.equals("November")){
			setStartDate = userEnteredYear +"-11-01";
			setEndDate =  userEnteredYear +"-11-30";
		}
		else if(userEnteredMonth.equals("December")){
			setStartDate = userEnteredYear +"-12-01";
			setEndDate =  userEnteredYear +"-12-31";
		}
		
		try {
			PreparedStatement myStatement = null;
			String queryString = "";
			ResultSet myResultset = null;
			System.out.println("start date " + setStartDate);
			System.out.println("end date " +setEndDate);
			queryString = "select sum(OrderPrice) as TotalPrice from CUSTOMER_ORDER C where "
					+ " C.OrderStatusCode = 2 and C.OrderDate BETWEEN ? and ?  ";
			myStatement = conn.prepareStatement(queryString);
			myStatement.setString(1, setStartDate);
			myStatement.setString(2, setEndDate);
			myResultset = myStatement.executeQuery();
			myResultset.next();
			int totalPrice = myResultset.getInt("TotalPrice");
			System.out.println("Total price " +totalPrice);
			request.setAttribute("TotalSalesPrice", totalPrice);
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		String nextJSP = "/InfoListing.jsp";
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
