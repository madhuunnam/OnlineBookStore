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
 * Servlet implementation class AddToWishListAction
 */
@WebServlet("/com/madhu/project/AddToWishListAction")
public class AddToWishListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToWishListAction() {
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
		Integer userId = null;
		System.out.println("Add to wishList action");
		userId = (Integer) request.getSession().getAttribute("userID");
		String[] selectedBookIDs = request
				.getParameterValues("selectedBookCheckBox");
		try {
			if (selectedBookIDs == null) {
				
				List<Book> books = getCurrentWishListForUser(request, response,
						userId, dbconn);
				request.setAttribute("wishlistResults", books);

				RequestDispatcher dp = request
						.getRequestDispatcher("SearchAction");
				dp.forward(request, response);

			} else {
				for (int i = 0; i < selectedBookIDs.length; i++) {

					System.out.println("Book ID - " + selectedBookIDs[i]);

					boolean alreadyExistsFlag = checkIfBookAlreadyExistsinWL(
							request, response, userId, selectedBookIDs[i]);
					if (alreadyExistsFlag == false) {
						queryString = "INSERT INTO WishList (BookID,UserID) values (?,?)";
						myStatement = conn.prepareStatement(queryString);
						myStatement.setInt(1,
								Integer.parseInt(selectedBookIDs[i]));
						myStatement.setInt(2, userId.intValue());

						myStatement.executeUpdate();
					}

				}
				List<Book> WishListBooks = getCurrentWishListForUser(request,
						response, userId, dbconn);
				request.setAttribute("wishlistResults", WishListBooks);
				RequestDispatcher dp = request
						.getRequestDispatcher("SearchAction");
				dp.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*Get the current wishlist values*/
	private List<Book> getCurrentWishListForUser(HttpServletRequest request,
			HttpServletResponse response, Integer userId, DbConnection dbconn)
			{

		List<Book> books = new ArrayList<Book>();

		String queryStringWishList = "";
		Connection conn = dbconn.getDbConnection();
		PreparedStatement myStatementGetWishlistBookId = null;
		ResultSet myResultset = null;
		try {

			queryStringWishList = "Select B.BookID, B.Title,B.Copies,B.Price,B.publisher,B.pub_year,G.GenreDesc,A.AUTHORName from WishList WL, Book B, Genre G, AUTHOR A, WrittenBy WB "
					+ "where A.AUTHORID = WB.AUTHORID and B.Genrecode = G.GenreCode and B.BookID = WB.BookID and WL.BookID = B.BookID "
					+ "and WL.UserID = ?  order by B.BookID ";

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

	private boolean checkIfBookAlreadyExistsinWL(HttpServletRequest request,
			HttpServletResponse response, Integer userId, String selectedBookIDs) {

		DbConnection dbconn = new DbConnection();
		Connection conn = dbconn.getDbConnection();
		try {
			PreparedStatement myStatementCurrentWishList = null;
			String queryStringCurrentWishList = null;
			ResultSet myResultsetCurrentWishList = null;
			queryStringCurrentWishList = "Select BookId from WishList where UserID = ?";
			myStatementCurrentWishList = conn
					.prepareStatement(queryStringCurrentWishList);
			myStatementCurrentWishList.setInt(1, userId.intValue());
			myResultsetCurrentWishList = myStatementCurrentWishList
					.executeQuery();
			while (myResultsetCurrentWishList.next()) {
				String currentWishlistbookID = myResultsetCurrentWishList
						.getString("BookID");
				if (currentWishlistbookID.equals(selectedBookIDs)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
