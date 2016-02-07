package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/com/madhu/project/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String loginFailureMsg = "";
		String userName;
		String password;
		int role = 0;
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		Statement myStatement = null;
		ResultSet myResultset = null;

		String userEnteredUserName = request.getParameter("Username");
		String userEnteredPassword = request.getParameter("Password");
		int userId = 0;

		if (conn != null) {
			try {
				myStatement = conn.createStatement();

				myResultset = myStatement.executeQuery("select * from USER_DETAILS");
				boolean loginStatus = false;
				boolean loginRole = false;
				while (myResultset.next()) {
					userName = myResultset.getString("UserName");
					password = myResultset.getString("Password");
					userId = myResultset.getInt("UserID");
					role = myResultset.getInt("Role");
					System.out.println("names in the list:" + userName);
					System.out.println("names in the list:" + password);
					System.out.println(userEnteredUserName);
					System.out.println(userEnteredPassword);

					if (userName != null
							&& userName.equals(userEnteredUserName)) {
						if (password != null
								&& password.equals(userEnteredPassword)) {
							
							request.getSession().setAttribute("userID",userId);
							if (role == 0) {
								loginStatus = true;
								loginRole = true;
								break;
							} else {
								loginStatus = true;
								loginRole = false;
								break;
							}

						}
					}
				}

				if (loginStatus == false) {
					// redirect to login failed page
					loginFailureMsg = "Error Msg: Username and password do not match";
					request.getSession().setAttribute("loginFailureMsg",loginFailureMsg);
					String nextJSP = "/LoginPage.jsp";
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(nextJSP);
					dispatcher.forward(request, response);
				} else if (loginStatus == true && loginRole == true) {
					// redirect to search page
				
//					String nextJSP = "/SearchPage.jsp";
//					RequestDispatcher dispatcher = getServletContext()
//							.getRequestDispatcher(nextJSP);
//					dispatcher.forward(request, response);
					RequestDispatcher dp = request
							.getRequestDispatcher("AddToWishListAction");
					dp.forward(request, response);
				}

				else if (loginStatus == true && loginRole == false) {
					// redirect to admin page

					RequestDispatcher dp = request
							.getRequestDispatcher("AdminAction");
					dp.forward(request, response);
					//
					// List<Book> books = new ArrayList<Book>();
					// Statement myStatementBook = null;
					// ResultSet myResultsetBook = null;
					// String bookID;
					// String title;
					// int price;
					// int copies;
					// String genrecode;
					// String publisher;
					// String authorName;
					// int pub_year;
					//
					// myStatementBook = conn.createStatement();
					// myResultsetBook = myStatementBook
					// .executeQuery("select B.BookID,B.Title,B.Price,B.Copies,G.GenreDesc,B.Publisher,B.Pub_year, "
					// +
					// "A.AuthorName from Book B,Author A, WrittenBy W, Genre G where "
					// +
					// "G.GenreCode= B.Genrecode and A.AuthorID= W.AuthorID and B.BookID = W.BookID");
					//
					// while (myResultsetBook.next()) {
					// bookID = myResultsetBook.getString("BookID");
					// title = myResultsetBook.getString("Title");
					// price = myResultsetBook.getInt("Price");
					// copies = myResultsetBook.getInt("Copies");
					// genrecode = myResultsetBook.getString("GenreDesc");
					// publisher = myResultsetBook.getString("Publisher");
					// pub_year = myResultsetBook.getInt("Pub_year");
					// authorName = myResultsetBook.getString("AuthorName");
					//
					// Book book = new Book();
					// book.setBookID(bookID);
					// book.setTitle(title);
					// book.setPrice(price);
					// book.setCopies(copies);
					// book.setGenrecode(genrecode);
					// book.setPublisher(publisher);
					// book.setPub_year(pub_year);
					// book.setAuthorName(authorName);
					// books.add(book);
					//
					// }
					// request.setAttribute("results", books);
					// String nextJSP = "/AdminPage.jsp";
					// RequestDispatcher dispatcher = getServletContext()
					// .getRequestDispatcher(nextJSP);
					// dispatcher.forward(request,
					// response);
				}

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
