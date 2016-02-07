<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPage</title>
</head>
<body style="background-color: pink">
	<form action="LogOffAction" method = "post">
		<p align="right" style="border-top: 100%">
			<input id=Logoff value="Logoff" type="submit">
		</p>
	</form>
	<form action="AdminAction">
		<p align="center">
			<strong> Welcome Administrator</strong>
		</p>

		<br>
		<table border=1>
			<tr>
				<td>BookID</td>
				<td>Title</td>
				<td>Author</td>
				<td>Price</td>
				<td>Copies</td>
				<td>Genre</td>
				<td>Publisher</td>
				<td>Pub_year</td>
			</tr>
			<c:forEach var="item" items="${results}">
				<tr>
					<td><c:out value="${item.bookID}" /></td>
					<td><c:out value="${item.title}" /></td>
					<td><c:out value="${item.authorName}" /></td>
					<td><c:out value="${item.price}" /></td>
					<td><c:out value="${item.copies}" /></td>
					<td><c:out value="${item.genrecode}" /></td>
					<td><c:out value="${item.publisher}" /></td>
					<td><c:out value="${item.pub_year}" /></td>

				</tr>
			</c:forEach>
		</table>
		<p>
			<i>Select any of the below operation:</i>
		</p>
	</form>
	<form action="AddBookAction">
		<p align="justify">
			1. Add a book to the inventory <input id=AddBook value="Add A Book"
				type="submit">
		</p>
	</form>

	<form action="UpdateBookAction">
		<p align="justify">
			2. Update a book to the inventory <input id=UpdateBook
				value="Update A Book" type="submit">
		</p>
	</form>
	<form action="OrderToProcessAction">
		<p align="justify">
			3. View or Process purchase <input id=ProcessPurchase
				value="Process Purchase" type="submit">
		</p>
	</form>
	<form action="ShowInfoListPageAction">
		<p align="justify">
			4. Information Listing <input id=InformationListing
				value="Information Listing" type="submit">
		</p>
	</form>

</body>
</html>