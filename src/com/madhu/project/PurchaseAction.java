package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlaceOrderAction
 */
@WebServlet("/com/madhu/project/PurchaseAction")
public class PurchaseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		Integer userId = null;
		List<Book> books = new ArrayList<Book>();
		userId = (Integer) request.getSession().getAttribute("userID");
		Set<String> bookIdToPurchaseSet = new HashSet<String>();
		String[] selectedBookIds = request
				.getParameterValues("selectedBookCheckBox");
		for (int i = 0; i < selectedBookIds.length; i++) {
			bookIdToPurchaseSet.add(selectedBookIds[i]);
		}
		
		try {
			if (bookIdToPurchaseSet.equals(""))
			{
				RequestDispatcher dp = request
						.getRequestDispatcher("SearchAction");
				dp.forward(request, response);
				
			}
			else{
				Iterator<String> bookIdToPurchaseSetIterator = bookIdToPurchaseSet.iterator();
				/*for (int i = 0; i < bookIdToPurchaseSet.size(); i++)*/ 
					while (bookIdToPurchaseSetIterator.hasNext()){
					//System.out.println(selectedBookIds[i]);
					String bookId = bookIdToPurchaseSetIterator.next();
					PreparedStatement myStatement = null;
					ResultSet myResultset = null;
					String queryString = "";
					queryString ="select B.BookID, B.Title, B.Price, B.Copies from Book B where B.BookID = ? order by B.BookID";
					myStatement = conn.prepareStatement(queryString);
					myStatement.setInt(1,
							Integer.parseInt(bookId));
					myResultset = myStatement.executeQuery();
					myResultset.next();
						Book book = new Book();
						book.setBookID(myResultset.getString("BookID"));
						book.setTitle(myResultset.getString("Title"));
						book.setPrice(myResultset.getInt("Price"));
						book.setCopies(myResultset.getInt("Copies"));
						books.add(book);
					
				}	
					request.setAttribute("purchasedlist", books);
				
//					RequestDispatcher dp = request
//							.getRequestDispatcher("PurchaseAction");
//					dp.forward(request, response);
					
				String nextJSP = "/PlaceOrder.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
				} 
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
