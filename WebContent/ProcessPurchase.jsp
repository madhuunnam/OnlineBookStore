<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Process Purchase</title>
</head>
<body style="background-color: pink">
<form action = "ProcessPurchaseAction">
	<p align="center">
		<strong> <i>Process Purchases Screen</i></strong>
	</p>
	<br>
	<br>
	<br>
	<p><i><c:out value="${processOrderFailure}"></c:out> </i></p>
<% request.getSession().setAttribute("orderList", request.getAttribute("orderList")); %>
<br /><br />
	<table border=1>
		<tr>

			<td>Select</td>
			<td>Order ID</td>
			<td>User ID</td>
			<td>Book ID</td>
			<td>Title</td>
			<td>Available Copies</td>
			<td>Ordered Copies</td>
			<td>Order Price</td>
			<td>Order Status</td>
		

		</tr>
		<c:forEach var="item" items="${orderList}">
					<tr>
						<td>
						<c:if test="${item.orderDesc == 'Placed'}">
							<input type="checkbox" id=selectedOrderCheckBoxId
								name=selectedOrderCheckBox value="${item.orderId}-${item.bookId}-${item.userId}" />
						</c:if>
						</td>
						<td><c:out value="${item.orderId}" /></td>
						<td><c:out value="${item.userId}" /></td>
						<td><c:out value="${item.bookId}" /></td>
						<td><c:out value="${item.title}" /></td>
						<td><c:out value="${item.availableCopies}" /></td>
						<td><c:out value="${item.bookQty}" /></td>
						<td><c:out value="${item.bookPrice}" /></td>
						<td><c:out value="${item.orderDesc}" /></td>
					</tr>
				</c:forEach>
	</table>
	<br>
	<br>
	<br>
	<br />
	<input style="position: absolute; left: 30%;" type="submit"
		value="Process Order" name="ProcessOrder" />
		</form>
		<form action = "AdminAction">
		<input style="position: absolute; left: 40%;" type="submit"
		value="Cancel" name="Cancel" />
		</form>

</body>
</html>