package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBookAction
 */
@WebServlet("/com/madhu/project/AddBookAction")
public class AddBookAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean success = false;
		String userEnteredBookId = request.getParameter("BookId");
		String userEnteredTitle = request.getParameter("Title");
		String userEnteredPublisher = request.getParameter("Publisher");
		String userEnteredPubYear = request.getParameter("pub_year");
		String userEnteredAuthor = request.getParameter("Author");
		String userEnteredCopies = request.getParameter("Copies");
		String userEnteredPrice = request.getParameter("Price");
		String userEnteredGenreDesc = request.getParameter("Genre");
		String addBookFailureErrorMsg = "";

		if (userEnteredTitle == null && userEnteredPublisher == null
				&& userEnteredPubYear == null && userEnteredAuthor == null
				&& userEnteredCopies == null && userEnteredPrice == null
				&& userEnteredGenreDesc == null) {
			String nextJSP = "/AddBook.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);

		} else {
			
			if (("".equals(userEnteredTitle))
					|| ("".equals(userEnteredPublisher))
					|| ("".equals(userEnteredPubYear))
					|| ("".equals(userEnteredAuthor))
					|| ("".equals(userEnteredCopies))
					|| ("".equals(userEnteredPrice))
					|| ("".equals(userEnteredGenreDesc))){
				
				addBookFailureErrorMsg = "Error Msg: Please enter all the fields";
				request.setAttribute("addBookFailureErrorMsg", addBookFailureErrorMsg);
				String nextJSP = "/AddBook.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
				
			}

			else if (!("".equals(userEnteredTitle))
					&& !("".equals(userEnteredPublisher))
					&& !("".equals(userEnteredPubYear))
					&& !("".equals(userEnteredAuthor))
					&& !("".equals(userEnteredCopies))
					&& !("".equals(userEnteredPrice))
					&& !("".equals(userEnteredGenreDesc))) {

				DbConnection dbconn = new DbConnection();
				Connection conn = dbconn.getDbConnection();
				Statement myGenreStatement = null;
				Statement myAuthorStatement = null;
				Statement myMaxGenreStatement = null;
				Statement myMaxAuthorStatement = null;
				Statement myMaxBookStatement = null;
				PreparedStatement myStatementInsertBook = null;
				PreparedStatement myStatementInsertGenre = null;
				PreparedStatement myStatementInsertWrittenBy = null;
				PreparedStatement myStatementInsertAuthor = null;
				String queryStringForBook = "";
				String queryStringForGenre = "";
				String queryStringForAuthor = "";
				String queryStringForWrittenBy = "";
				ResultSet myResultsetGenre = null;
				ResultSet myResultsetAuthor = null;
				String genreDesc;
				int genreCode = 0;
				String authorName;
				int authorId = 0;
				int bookId = 0;

				if (conn != null) {
					try {
						myGenreStatement = conn.createStatement();
						myResultsetGenre = myGenreStatement
								.executeQuery("select * from Genre");

						myAuthorStatement = conn.createStatement();
						myResultsetAuthor = myAuthorStatement
								.executeQuery("select * from Author");

						boolean matchFlag = false;
						while (myResultsetGenre.next()) {

							genreDesc = myResultsetGenre.getString("GenreDesc");
							genreCode = myResultsetGenre.getInt("GenreCode");
							System.out.println("Genere Code & Description - "
									+ genreCode + "---" + genreDesc);

							if (userEnteredGenreDesc.equals(genreDesc)) {
								System.out
										.println("Genre Matched the existing record");
								matchFlag = true;
								break;
							}
						}
						if (matchFlag == false) {
							System.out
									.println("Genre DIDNOT match the existing record");
							System.out.println("Inserting in to Genre table");
							queryStringForGenre = "INSERT INTO Genre(GenreDesc) values (?)";
							myStatementInsertGenre = conn
									.prepareStatement(queryStringForGenre);
							myStatementInsertGenre.setString(1,
									userEnteredGenreDesc);

							myStatementInsertGenre.executeUpdate();

							System.out
									.println("executing Query to get max gener code ");

							myMaxGenreStatement = conn.createStatement();
							ResultSet rsg = myMaxGenreStatement
									.executeQuery("select Max(GenreCode) as GenreCode from Genre ");
							rsg.next();
							genreCode = rsg.getInt("GenreCode");
						}
						matchFlag = false;
						while (myResultsetAuthor.next()) {
							authorName = myResultsetAuthor
									.getString("AuthorName");
							authorId = myResultsetAuthor.getInt("AuthorId");

							System.out.println("Author  ID & AuthorName - "
									+ authorId + "---" + authorName);
							if (userEnteredAuthor.equals(authorName)) {
								System.out
										.println("Author Matched the existing record");
								matchFlag = true;
								break;
							}
						}
						if (matchFlag == false) {
							System.out
									.println("Author DIDNOT match the existing record");
							System.out.println("Inserting in to Author table");
							queryStringForAuthor = "INSERT INTO Author (AUTHORName) values (?)";
							myStatementInsertAuthor = conn
									.prepareStatement(queryStringForAuthor);
							myStatementInsertAuthor.setString(1,
									userEnteredAuthor);

							myStatementInsertAuthor.executeUpdate();

							System.out
									.println("executing Query to get max AuthodID ");
							myMaxAuthorStatement = conn.createStatement();
							ResultSet rsa = myMaxAuthorStatement
									.executeQuery("select Max(AUTHORID) as AUTHORID from AUTHOR ");
							rsa.next();
							authorId = rsa.getInt("AUTHORID");
						}

						queryStringForBook = "INSERT INTO BOOK(Title,Price,Genrecode,publisher,pub_year,copies) values "
								+ "(?,?,?,?,?,?)";
						myStatementInsertBook = conn
								.prepareStatement(queryStringForBook);
						myStatementInsertBook.setString(1, userEnteredTitle);
						myStatementInsertBook.setString(2, userEnteredPrice);
						myStatementInsertBook.setInt(3, genreCode);
						myStatementInsertBook
								.setString(4, userEnteredPublisher);
						myStatementInsertBook.setString(5, userEnteredPubYear);
						myStatementInsertBook.setString(6, userEnteredCopies);
						System.out.println("Inserting in to BOOk table");
						myStatementInsertBook.executeUpdate();

						System.out
								.println("executing Query to get max BOOKID ");

						myMaxBookStatement = conn.createStatement();
						ResultSet rs = myMaxBookStatement
								.executeQuery("select Max(BookID) as BookID from Book ");
						rs.next();
						bookId = rs.getInt("BookID");

						System.out.println("Inserting in to WrittenBy table");
						queryStringForWrittenBy = "INSERT INTO WrittenBy(BookID,AUTHORID) values(?,?)";
						myStatementInsertWrittenBy = conn
								.prepareStatement(queryStringForWrittenBy);

						myStatementInsertWrittenBy.setInt(1, bookId);
						myStatementInsertWrittenBy.setInt(2, authorId);

						myStatementInsertWrittenBy.executeUpdate();

						myResultsetAuthor.close();
						myResultsetGenre.close();
						rs.close();
						success = true;
						// addBookFailureErrorMsg = "";
						RequestDispatcher dp = request
								.getRequestDispatcher("AdminAction");
						dp.forward(request, response);

					} catch (SQLException e) {
						System.out.println("Error occured ");
						e.printStackTrace();
					}

				}

			}

//			if (success == false) {
//				System.out.println("success is false");
//				// addBookFailureErrorMsg =
//				// "Please enter all the fields for the Book";
//				// request.setAttribute("AddBookFailure",
//				// addBookFailureErrorMsg);
//				String nextJSP = "/AddBook.jsp";
//				RequestDispatcher dispatcher = getServletContext()
//						.getRequestDispatcher(nextJSP);
//				dispatcher.forward(request, response);
//
//			}
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
