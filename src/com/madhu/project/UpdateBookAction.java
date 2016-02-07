package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBookAction
 */
@WebServlet("/com/madhu/project/UpdateBookAction")
public class UpdateBookAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateBookAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean flag = false;
		String userEnteredBookId = request.getParameter("BookId");
		String userEnteredCopies = request.getParameter("Copies");

		if ((userEnteredBookId != null || !("".equals(userEnteredBookId)))
				&& (userEnteredCopies != null || !("".equals(userEnteredCopies)))) {
			try {

				DbConnection dbconn = new DbConnection();
				Connection conn = dbconn.getDbConnection();
				PreparedStatement myBookStatement = null;
				String queryString = "UPDATE BOOK set Copies = ? where BookID = ?";

				myBookStatement = conn.prepareStatement(queryString);
				myBookStatement.setInt(1, Integer.parseInt(userEnteredCopies));
				myBookStatement.setInt(2, Integer.parseInt(userEnteredBookId));
				myBookStatement.executeUpdate();
				flag = true;
				RequestDispatcher dp = request
						.getRequestDispatcher("AdminAction");
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (flag == false) {

			String nextJSP = "/UpdateBook.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
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
