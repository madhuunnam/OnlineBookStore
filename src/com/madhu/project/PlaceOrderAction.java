package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlaceOrderAction
 */
@WebServlet("/com/madhu/project/PlaceOrderAction")
public class PlaceOrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrderAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer userId = null;
		String userEnteredCardNo = request.getParameter("Cardno");
		String userEnteredAddress = request.getParameter("Address");
		String userEnteredCity = request.getParameter("City");
		String userEnteredState = request.getParameter("State");
		userId = (Integer) request.getSession().getAttribute("userID");
		int statusCode = 1;

		CustomerOrder order = new CustomerOrder();
		
		order.setCardNo(Integer.parseInt(userEnteredCardNo));
		order.setAddress(userEnteredAddress);
		order.setCity(userEnteredCity);
		order.setState(userEnteredState);
		order.setOrderStatusCode(statusCode);

		List<Book> purchasedList = null;
		purchasedList = (List) request.getSession()
				.getAttribute("selectedList");
		int totalOrderPrice = 0;
		int toatlOrderQuantity = 0;

		for (int i = 0; i < purchasedList.size(); i++) {

			String bookID = purchasedList.get(i).getBookID();
			int bookPrice = purchasedList.get(i).getPrice();
			
			
			
			System.out
					.println("The book id selected for purchase is " + bookID);
			String bookIDCopies = request.getParameter(bookID);
			System.out.println("The book Copies entered is " + bookIDCopies);
			totalOrderPrice = totalOrderPrice
					+ (Integer.parseInt(bookIDCopies) * bookPrice);
			toatlOrderQuantity = toatlOrderQuantity
					+ (Integer.parseInt(bookIDCopies));
			
			OrderContains orderContainsObj = new OrderContains();
			orderContainsObj.setBookId(Integer.parseInt(bookID));
			orderContainsObj.setBookPrice(bookPrice);
			orderContainsObj.setBookQty(Integer.parseInt(bookIDCopies));
			orderContainsObj.setTitle(purchasedList.get(i).getTitle());
			order.getOrderContainsList().add(orderContainsObj);

		}

		order.setOrderPrice(totalOrderPrice);
		order.setOrderQuantity(toatlOrderQuantity);
		
		//request.getSession().setAttribute("customerOrder", order);
		
		System.out.println("orderPrice" + totalOrderPrice);
		System.out.println("orderQty" + toatlOrderQuantity);

		request.setAttribute("customerOrderContainsList", order.getOrderContainsList());
		request.setAttribute("customerOrder", order);
		
		String nextJSP = "/ConfirmOrder.jsp";
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
