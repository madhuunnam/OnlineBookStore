<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Confirm Order</title>
</head>
<body style="background-color: pink">
<form action = "ConfirmOrderAction">
<p align = "center">
 <strong>Review the Order details and confirm </strong>
 </p>
 
 <% request.getSession().setAttribute("customerOrder", request.getAttribute("customerOrder")); %>
<table border=1">
				<tr>
					<td>BookID</td>
					<td>Title</td>
					<td>Price per Book</td>
					 <td>Ordered Copies</td>
					<td>Total book Price</td>
				</tr>
				<c:forEach var="item" items="${customerOrderContainsList}" >
					<tr>
						
						<td><c:out value="${item.bookId}" /></td>
						<td><c:out value="${item.title}" /></td>
						<td><c:out value="${item.bookPrice}" /></td>
						<td><c:out value="${item.bookQty}" /></td>
						<td><c:out value="${item.totalBookPrice}" /></td>
					</tr>
				</c:forEach>
				
			
			</table>
			<br/>
			<p>
				
				Total Order Price is  : $${customerOrder.orderPrice}
			
			</p>
			<br/>
			<p>
			 Shipping Address is : ${customerOrder.address},
			 						${customerOrder.city},
			 						${customerOrder.state}
			</p>
			
			<button id=purchase type="submit" 
			style="position: absolute;right:30%; bottom: 30%;">Confirm Order</button>
			</form>
			<form action = "SearchAction">
		<button id=cancel type="submit" onclick='Cancel()'
			style="position: absolute; right: 20%; bottom: 30%;">Cancel</button>
			</form>
			

</body>
</html>