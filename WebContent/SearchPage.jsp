<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search page</title>
<script type="text/javascript">
function AddToWishlist() {
	document.getElementById("searchForm").action = "AddToWishListAction";
	document.getElementById("searchForm").submit();
}
function Purchase() {
	document.getElementById("searchForm").action = "PurchaseAction";
	document.getElementById("searchForm").submit();
}
function Status() {
    document.getElementById("searchForm").action = "CheckStatusAction";
    document.getElementById("searchForm").submit();
}
</script>

</head>
<body style="background-color: pink">
	<form action="LogOffAction" method = "post">
		<p align="right" style="border-top: 100%">
			<input id=Logoff value="Logoff" type="submit">
		</p>
	</form>
	<form id="searchForm" action="SearchAction">
		<p align="center">
			<strong> Welcome Customer</strong>
		</p>
		<button id=status type="button" onclick='Status()'
                               style="position: absolute; right: 10%;">Status of Order</button>
		<p>
			<br> <i>Search for books by author or Genre or year of
				publication:</i>
		</p>
		<br> Author<input type=text id=Author name="author">
		Genre<input type=text id=Genre name="genre"> Year of Pub<input
			type=text id=Yearofpub name="yearofpub"> <br> <input
			style="margin-left: 550px" type="submit" id=search value=Search
			name=Search onclick='Search()'><br>
		<c:if test="${fn:length(results) gt 0}">
			<table border=1>
				<tr>
					<td>Select</td>
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
						<td><input type="checkbox" id=selectedBookCheckBoxId
							name=selectedBookCheckBox value="${item.bookID}" /></td>
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
		</c:if>
		<p align="center">
			<button id=addToWishlist type="button" onclick='AddToWishlist()'
				style="position: absolute ;right: 30% ">Add to Wishlist</button>
		</p>
		<p style="margin-left: 900px; bottom: 500%">
			<i>Wishlist</i>
		</p>
		<table border=1 style="margin-left: 700px;">
			<tr>
				<td>Select</td>
				<td>BookID</td>
				<td>Title</td>
				<td>Author</td>
				<td>Price</td>
				<td>Copies</td>
				<td>Genre</td>
				<td>Publisher</td>
				<td>Pub_year</td>
			</tr>
			<c:forEach var="item" items="${wishlistResults}">
				<tr>
					<td><input type="checkbox" id=selectedBookCheckBoxId
						name=selectedBookCheckBox value="${item.bookID}" /></td>
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


		

		<button id=purchase type="button" onclick='Purchase()'
			style="position: absolute; right: 50% ;bottom: 10%;">Purchase</button>
				</form>
		<form action = "SearchAction">
		<button id=cancel type="submit" onclick='Cancel()'
			style="position: absolute; right: 40%; bottom: 10%;">Cancel</button>
</form>


</body>
</html>