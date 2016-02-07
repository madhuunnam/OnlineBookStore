package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class AdminAction
 */
@WebServlet("/com/madhu/project/AdminAction")
public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		DbConnection myconn = new DbConnection();
		Connection conn = myconn.getDbConnection();
		List<Book> books = new ArrayList<Book>();
		Statement myStatement = null;
		ResultSet myResultset = null;
		String bookID;
		String title;
		int price;
		int copies;
		String genrecode;
		String publisher;
		String authorName;
		int pub_year;

		if (conn != null) {
			try {
				myStatement = conn.createStatement();
				myResultset = myStatement
						.executeQuery("select B.BookID,B.Title,B.Price,B.Copies,G.GenreDesc,B.Publisher,B.Pub_year, "
								+ "A.AuthorName from Book B,Author A, WrittenBy W, Genre G where "
								+ "G.GenreCode= B.Genrecode and A.AuthorID= W.AuthorID and B.BookID = W.BookID order by B.BookID");

				while (myResultset.next()) {
					bookID = myResultset.getString("BookID");
					title = myResultset.getString("Title");
					price = myResultset.getInt("Price");
					copies = myResultset.getInt("Copies");
					genrecode = myResultset.getString("GenreDesc");
					publisher = myResultset.getString("Publisher");
					pub_year = myResultset.getInt("Pub_year");
					authorName = myResultset.getString("AuthorName");

					Book book = new Book();
					book.setBookID(bookID);
					book.setTitle(title);
					book.setPrice(price);
					book.setCopies(copies);
					book.setGenrecode(genrecode);
					book.setPublisher(publisher);
					book.setPub_year(pub_year);
					book.setAuthorName(authorName);
					books.add(book);

				}
				request.setAttribute("results", books);
				String nextJSP = "/AdminPage.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				System.out.println("Error occured ");
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
