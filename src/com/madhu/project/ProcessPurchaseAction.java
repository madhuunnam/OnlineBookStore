package com.madhu.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessPurchaseAction
 */
@WebServlet("/com/madhu/project/ProcessPurchaseAction")
public class ProcessPurchaseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessPurchaseAction() {
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
		Set<String> orderIdToProcessSet = new HashSet<String>();

		List<OrderContains> orderContainsListObject = new ArrayList<OrderContains>();
		orderContainsListObject = (List<OrderContains>) request.getSession()
				.getAttribute("orderList");

		String[] selectedOrderIds = request
				.getParameterValues("selectedOrderCheckBox");

		for (int i = 0; i < selectedOrderIds.length; i++) {
			orderIdToProcessSet.add(selectedOrderIds[i]);
		}
		
		/*Check if all books are selected for that order ID*/
		
		//if(){
			
		//}
		
		List<OrderContains> finalOrderContainsList = new ArrayList<OrderContains>();
		
		for(int i=0;i<orderContainsListObject.size();i++){
			
			String orderId = Integer.toString(orderContainsListObject.get(i).getOrderId());
			String userID  = Integer.toString(orderContainsListObject.get(i).getUserId());
			String bookID =  Integer.toString(orderContainsListObject.get(i).getBookId());
			
			if((orderIdToProcessSet.contains(orderId+"-"+bookID+"-"+userID) )) {
				if(orderContainsListObject.get(i).getOrderDesc().contains("Placed")){
					finalOrderContainsList.add(orderContainsListObject.get(i));
				}
				else{
					String orderFailureErrorMsg = "The selected orders are already processed. Please select any order whose status is \"PLACED\".";
					request.setAttribute("processOrderFailure", orderFailureErrorMsg);
					request.setAttribute("orderList", request.getSession().getAttribute("orderList")) ;
					RequestDispatcher dp = request
							.getRequestDispatcher("/ProcessPurchase.jsp");
					dp.forward(request, response);
					
				}
			}
			
		}
		
		Map<String, Integer> orderedBookCountMap = new HashMap<String,Integer>();
		
		for(int i=0;i<finalOrderContainsList.size();i++){
			int bookID = finalOrderContainsList.get(i).getBookId();
			int bookIDCount = finalOrderContainsList.get(i).getBookQty();
			if(orderedBookCountMap.containsKey(Integer.toString(bookID))){
				int alreadyExistingBookIDCount = orderedBookCountMap.get(Integer.toString(bookID));
				int totalCount = bookIDCount + alreadyExistingBookIDCount ;
				orderedBookCountMap.put(Integer.toString(bookID), new Integer(totalCount));
				
			}else{
				orderedBookCountMap.put(Integer.toString(bookID), new Integer(bookIDCount));
			}
		}
		
		System.out.println("The map is " + orderedBookCountMap.toString());
		
		Set<String> orderedBookIDSet = new HashSet<String>(); 
		orderedBookIDSet = orderedBookCountMap.keySet();
		
		boolean orderSuccessFlag = true;
		String orderFailureErrorMsg = "The selected combination of Orders contains books that are not sufficient in the inventory.\n Update the inventory for following BookIDs- ";
		
		Iterator<String> orderedBookIDSetIterator = orderedBookIDSet.iterator();
		while(orderedBookIDSetIterator.hasNext()){
			String bookID = orderedBookIDSetIterator.next();
			int availableCopies =0;
			int totalOrderedCopies =0;
			if(bookID != null){
				availableCopies = getUpdatedAvailableCopiesForBook(Integer.parseInt(bookID), conn);
				totalOrderedCopies   = orderedBookCountMap.get(bookID);
				if( totalOrderedCopies > availableCopies){
					orderSuccessFlag = false;
					orderFailureErrorMsg = orderFailureErrorMsg +  bookID + " , " ;
				}
			}
		}
		
		if(orderSuccessFlag == false){
			System.out.println(orderFailureErrorMsg);
			request.setAttribute("processOrderFailure", orderFailureErrorMsg);
			request.setAttribute("orderList", request.getSession().getAttribute("orderList")) ;
			RequestDispatcher dp = request
					.getRequestDispatcher("/ProcessPurchase.jsp");
			dp.forward(request, response);
		}else{
			
			orderedBookIDSetIterator = orderedBookIDSet.iterator();
			while(orderedBookIDSetIterator.hasNext()){
				String bookID = orderedBookIDSetIterator.next();
				int totalOrderedCopies = orderedBookCountMap.get(bookID);
				int availableCopies = getUpdatedAvailableCopiesForBook(Integer.parseInt(bookID), conn);
				updateAvailableCopiesForBook( Integer.parseInt(bookID),conn , availableCopies-totalOrderedCopies);
			}
			
			Iterator<String> orderIdToProcessSetIterator = orderIdToProcessSet.iterator();
			
			while(orderIdToProcessSetIterator.hasNext()){
				String orderIDBookIDUserIDCombination = orderIdToProcessSetIterator.next();
				String orderID = "";
				orderID = orderIDBookIDUserIDCombination.substring(0, orderIDBookIDUserIDCombination.indexOf("-"));
				updateOrderStatus(2,conn,Integer.parseInt(orderID));
			}
			
			
			for(int i=0; i<finalOrderContainsList.size();i++){
				removeBookFromWishList(finalOrderContainsList.get(i).getUserId() , finalOrderContainsList.get(i).getBookId(),conn);	
			}
			
			RequestDispatcher dp = request
					.getRequestDispatcher("AdminAction");
			dp.forward(request, response);
		}
		
		
//
//		/*
//		 * 1. get the bookIds,orderedCopies list for each orderID in the
//		 * orderIdToProcess set from the orderContains 2. For each Book ID , get
//		 * Available Copies from Book table. 2.1 Check if available and ordered
//		 * copies are proper. If yes, subtract available copies and update book
//		 * table 2.2 if not , set the order status to pending.
//		 */
//		int subLoopBookID,subLoopUserID,orderedCopies;
//		
//		for (int i = 0; i < finalOrderContainsList.size(); i++) {
//			int mainLoopOrderID = finalOrderContainsList.get(i).getOrderId();
//			int mainLoopBookID =  finalOrderContainsList.get(i).getBookId();
//			int mainLoopUserID =  finalOrderContainsList.get(i).getUserId();
//			String mainLoopConditionString = mainLoopOrderID + "_" + mainLoopBookID + "_" + mainLoopUserID;
//			
//			boolean orderSuccess = false;
//			
//			List<OrderContains> successOrderContainsList = new ArrayList<OrderContains>();
//			for (int j = 0; j < finalOrderContainsList.size(); j++) {
//				int subLoopOrderID = finalOrderContainsList.get(j).getOrderId();
//				subLoopBookID = finalOrderContainsList.get(j).getBookId();
//				subLoopUserID = finalOrderContainsList.get(j).getUserId();
//				String subLoopConditionString = subLoopOrderID + "_" + subLoopBookID + "_" + subLoopUserID;
//				if (mainLoopOrderID == subLoopOrderID) {
//					if (!mainLoopConditionString.equals(subLoopConditionString)) {
//
//						orderedCopies = finalOrderContainsList.get(j)
//								.getBookQty();
//
//						int updatedAvailableCopies = 0;
//						updatedAvailableCopies = getUpdatedAvailableCopiesForBook(
//								subLoopBookID, conn);
//						if (orderedCopies > updatedAvailableCopies) {
//							orderSuccess = false;
//							break;
//						} else {
//							orderSuccess = true;
//							OrderContains b = new OrderContains();
//							b.setAvailableCopies(updatedAvailableCopies);
//							b.setBookQty(orderedCopies);
//							b.setUserId(subLoopUserID);
//							b.setBookId(subLoopBookID);
//							b.setOrderId(mainLoopOrderID);
//							successOrderContainsList.add(b);
//
//						}
//
//					}
//				}
//			}
//			if(orderSuccess == false){
//				//updateOrderStatus to Pending;
//				updateOrderStatus(3,conn,mainLoopOrderID);
//			}else{
//				for(int c=0;c<successOrderContainsList.size();c++){
//					OrderContains temp = new OrderContains();
//					temp = successOrderContainsList.get(c);
//					updateAvailableCopiesForBook(temp.getBookId() ,conn ,(temp.getAvailableCopies()-temp.getBookQty()));
//					removeBookFromWishList(temp.getUserId(),temp.getBookId(),conn);
//				}
//				//updateOrderStatus to processed;
//				updateOrderStatus(2,conn,mainLoopOrderID);
//			}
//
//		}
		

	}

	private int getUpdatedAvailableCopiesForBook(int bookId, Connection conn) {
		
		PreparedStatement myStatement = null;
		ResultSet myResultset = null;
		String queryString = "";
		int copies =0;
		try {
			
			queryString = "select copies from Book where BookID = ?";
			myStatement = conn.prepareStatement(queryString);
			myStatement.setInt(1, bookId);
			myResultset = myStatement.executeQuery();
			myResultset.next();
			copies = myResultset.getInt("copies");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copies;
	
	}

		
	
	private void removeBookFromWishList(int userId, int bookId, Connection conn) {
		try {
			PreparedStatement myStatement = null;
			String queryString = "";
			queryString = "DELETE from WishList where UserID = ? and BookID = ?";
			myStatement = conn.prepareStatement(queryString);
			myStatement.setInt(1, userId);
			myStatement.setInt(2, bookId);
			myStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void updateAvailableCopiesForBook(int bookID,Connection conn, int i) {
		try {
			PreparedStatement myStatement = null;
			String queryString = "";
			queryString = "UPDATE Book set copies = ? where BookID = ?";
			myStatement = conn.prepareStatement(queryString);
			myStatement.setInt(1, i);
			myStatement.setInt(2, bookID);
			myStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void updateOrderStatus(int statusCode, Connection conn, int orderId) {
		try {
			PreparedStatement myStatement = null;
			String queryString = "";
			queryString = "UPDATE CUSTOMER_ORDER set OrderStatusCode = ? where orderId = ?";
			myStatement = conn.prepareStatement(queryString);
			myStatement.setInt(1, statusCode);
			myStatement.setInt(2, orderId);
			myStatement.executeUpdate();
		} catch (SQLException e) {
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
