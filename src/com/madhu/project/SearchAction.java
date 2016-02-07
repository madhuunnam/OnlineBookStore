package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class SearchAction
 */
@WebServlet("/com/madhu/project/SearchAction")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entering Search Action");
		DbConnection myconn = new DbConnection();
		Connection conn = myconn.getDbConnection();
		List<Book> books = new ArrayList<Book>();
		PreparedStatement myStatement = null;
		Statement myStatement1 = null;
		Integer userId = null;
		userId = (Integer) request.getSession().getAttribute("userID");

		ResultSet myResultset = null;
		String BookID;
		String Title;
		int Price;
		int Copies;
		String Genrecode;
		String Publisher;
		String authorName;
		int Pub_year;
		String userEnteredAuthor = request.getParameter("author");
		String userEnteredGenre = request.getParameter("genre");
		String userEnteredYearOfPub = request.getParameter("yearofpub");
		System.out.println(userEnteredAuthor);
		System.out.println(userEnteredGenre);
		System.out.println(userEnteredYearOfPub);
		String queryString = "";
		int flag = 0;
		if ((userEnteredYearOfPub == null || "".equals(userEnteredYearOfPub))
				&& (userEnteredAuthor == null || "".equals(userEnteredAuthor)
						&& (userEnteredGenre == null || ""
								.equals(userEnteredGenre)))) {
			flag = 7;

		} else if ((userEnteredAuthor == null || "".equals(userEnteredAuthor)
				&& (userEnteredGenre == null || "".equals(userEnteredGenre)))) {
			queryString = "select B.BookID,B.Title,A.AuthorName,B.Copies,B.Price,G.GenreDesc,B.Publisher,B.pub_year"
					+ " from Book B,Genre G,Author A,WrittenBy W where "
					+ "B.Genrecode=G.GenreCode and A.AuthorID=W.AuthorID and B.BookID= W.BookID and B.pub_year = ? order by B.BookID";
			flag = 1;
		} else if ((userEnteredAuthor == null || "".equals(userEnteredAuthor)
				&& (userEnteredYearOfPub == null || ""
						.equals(userEnteredYearOfPub)))) {
			queryString = "select B.BookID,B.Title,A.AuthorName,B.Copies,B.Price,G.GenreDesc,B.Publisher,B.pub_year "
					+ "from Book B, Genre G, Author A, WrittenBy W where B.Genrecode = G.GenreCode "
					+ "and A.AuthorID=W.AuthorID and B.BookID= W.BookID and G.GenreDesc = ? order by B.BookID";
			flag = 2;
		} else if ((userEnteredGenre == null || "".equals(userEnteredGenre)
				&& (userEnteredYearOfPub == null || ""
						.equals(userEnteredYearOfPub)))) {
			queryString = "select B.BookID,B.Title,A.AuthorName,B.Copies,B.Price,G.GenreDesc,B.Publisher,B.pub_year"
					+ " from Book B, WrittenBy W, Author A, Genre G where A.AuthorID=W.AuthorID "
					+ "and B.Genrecode= G.GenreCode and B.BookID= W.BookID and A.AuthorName = ? order by B.BookID";
			flag = 3;
		} else if ((userEnteredGenre == null || "".equals(userEnteredGenre))) {
			queryString = "select B.BookID,B.Title,A.AuthorName,B.Copies,B.Price,G.GenreDesc,B.Publisher,B.pub_year"
					+ " from Book B,WrittenBy W, Author A,Genre G where W.BookID= B.BookID "
					+ "and W.AuthorID=A.AuthorID and B.Genrecode= G.GenreCode and B.pub_year = ? and A.AuthorName=? order by B.BookID";
			flag = 4;
		} else if ((userEnteredAuthor == null || "".equals(userEnteredAuthor))) {
			queryString = "select B.BookID,B.Title,A.AuthorName,B.Copies,B.Price,G.GenreDesc,B.Publisher,B.pub_year "
					+ "from Book B, Genre G, Author A, WrittenBy W where "
					+ "B.Genrecode = G.GenreCode and A.AuthorID= W.AuthorID and B.BookID= W.BookID and G.GenreDesc = ? and "
					+ "B.pub_year=? order by B.BookID";
			flag = 5;
		} else if ((userEnteredYearOfPub == null || ""
				.equals(userEnteredYearOfPub))) {
			queryString = "select B.BookID,B.Title,A.AuthorName,B.Copies,B.Price,G.GenreDesc,B.Publisher,B.pub_year "
					+ " from Book B, Genre G, WrittenBy W, Author A where B.Genrecode = G.GenreCode "
					+ "and B.BookID= W.BookID and A.AuthorID= W.AuthorID "
					+ "and G.GenreDesc = ? and  A.AuthorName = ? order by B.BookID";
			flag = 6;
		} else {
			queryString = "select B.BookID,B.Title,G.GenreDesc,A.AuthorName,B.Price,B.Copies,B.Publisher,B.pub_year"
					+ " from Book B, WrittenBy W, Author A, Genre G where B.GenreCode= G.GenreCode "
					+ "and B.BookID = W.BookID and W.AuthorID= A.AuthorID and A.AuthorName = ? "
					+ "and G.GenreDesc = ? and B.pub_year= ? order by B.BookID";

		}

		if (conn != null) {
			try {
				myStatement = conn.prepareStatement(queryString);

				if (flag == 1) {
					myStatement.setString(1, userEnteredYearOfPub);
				} else if (flag == 2) {
					myStatement.setString(1, userEnteredGenre);
				} else if (flag == 3) {
					myStatement.setString(1, userEnteredAuthor);
				} else if (flag == 4) {
					myStatement.setString(1, userEnteredYearOfPub);
					myStatement.setString(2, userEnteredAuthor);

				} else if (flag == 5) {
					myStatement.setString(1, userEnteredGenre);
					myStatement.setString(2, userEnteredYearOfPub);
				} else if (flag == 6) {
					myStatement.setString(1, userEnteredGenre);
					myStatement.setString(2, userEnteredAuthor);
				} else if (flag == 7) {
					System.out.println("flag value is 7");
					queryString = "select B.BookID,B.Title,B.Price,B.Copies,G.GenreDesc,B.Publisher,B.Pub_year, "
							+ "A.AuthorName from Book B,Author A, WrittenBy W, Genre G where "
							+ "G.GenreCode= B.Genrecode and A.AuthorID= W.AuthorID and B.BookID = W.BookID order by B.BookID";
					myStatement1 = conn.createStatement();
					myResultset = myStatement1.executeQuery(queryString);


				} else {
					myStatement.setString(1, userEnteredAuthor);
					myStatement.setString(2, userEnteredGenre);
					myStatement.setString(3, userEnteredYearOfPub);
				}

				if (flag != 7) {
					myResultset = myStatement.executeQuery();
				}
				if (request.getParameter("fromLoginPage") == null) {
					while (myResultset.next()) {
						BookID = myResultset.getString("BookID");
						Title = myResultset.getString("Title");
						Price = myResultset.getInt("Price");
						Copies = myResultset.getInt("Copies");
						Genrecode = myResultset.getString("GenreDesc");
						Publisher = myResultset.getString("Publisher");
						Pub_year = myResultset.getInt("Pub_year");
						authorName = myResultset.getString("AuthorName");

						Book book = new Book();
						book.setBookID(BookID);
						book.setTitle(Title);
						book.setPrice(Price);
						book.setCopies(Copies);
						book.setGenrecode(Genrecode);
						book.setPublisher(Publisher);
						book.setPub_year(Pub_year);
						book.setAuthorName(authorName);
						books.add(book);

						System.out.println("Book Details: Book ID " + BookID
								+ " Title - " + Title);
						request.setAttribute("results", books);

					}
				}
			}

			catch (SQLException e) {
				System.out.println("Error occured ");
				e.printStackTrace();
			}
			
			List<Book> wishlistBooks = getCurrentWishListForUser(request, response,
					userId);
			request.setAttribute("wishlistResults", wishlistBooks);
			
			String nextJSP = "/SearchPage.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}
	private List<Book> getCurrentWishListForUser(HttpServletRequest request,
			HttpServletResponse response, Integer userId)
			{

		List<Book> books = new ArrayList<Book>();

		String queryStringWishList = "";
		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		
		PreparedStatement myStatementGetWishlistBookId = null;
		ResultSet myResultset = null;
		try {

			queryStringWishList = "Select B.BookID,B.Title,B.Copies,B.Price,B.publisher,B.pub_year,G.GenreDesc,A.AUTHORName from WishList WL,Book B,Genre G,AUTHOR A, WrittenBy WB "
					+ "where A.AUTHORID = WB.AUTHORID and B.Genrecode = G.GenreCode and B.BookID = WB.BookID and WL.BookID = B.BookID "
					+ "and WL.UserID = ? order by B.BookID";

			myStatementGetWishlistBookId = conn
					.prepareStatement(queryStringWishList);
			myStatementGetWishlistBookId.setInt(1, userId.intValue());

			myResultset = myStatementGetWishlistBookId.executeQuery();

			while (myResultset.next()) {

				Book book = new Book();
				book.setBookID(myResultset.getString("BookID"));
				book.setTitle(myResultset.getString("Title"));
				book.setPrice(myResultset.getInt("Price"));
				book.setCopies(myResultset.getInt("Copies"));
				book.setGenrecode(myResultset.getString("GenreDesc"));
				book.setPublisher(myResultset.getString("publisher"));
				book.setPub_year(myResultset.getInt("pub_year"));
				book.setAuthorName(myResultset.getString("AuthorName"));
				books.add(book);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request
				.getRequestDispatcher("AddToWishListAction");
		dp.forward(request, response);
		// TODO Auto-generated method stub
	}

}
