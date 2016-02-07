<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: pink">
<form id = "StatusDisplay">
	<br />
	<br />
	<p align="center">
		<strong> Please find below is the status of all your Orders</strong>
	</p>
	<br />
	<table border=1 align = "center">
		<tr>
			<td>OrderId</td>
			<td>Title</td>
			<td>Status</td>

		</tr>
		<c:forEach var="item" items="${Statusdesc}">
			<tr>
				<td><c:out value="${item.orderId}" /></td>
				<td><c:out value="${item.title}" /></td>
				<td><c:out value="${item.orderDesc}" /></td>


			</tr>
		</c:forEach>
	</table>
	<br />
	</form>
	<form action = "AddToWishListAction">
	<p align="center">
			 <input type="submit" name="OK" value = "OK" /> <br />
	</form>
</body>
</html>