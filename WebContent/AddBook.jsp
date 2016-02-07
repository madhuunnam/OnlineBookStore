<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddBook</title>
</head>
<body style="background-color: pink">
	<form action="AddBookAction">
		<p>
			<br> <strong>Enter the below details of the book:</strong>
		</p>
		<p>
			<i><strong><c:out value="${addBookFailureErrorMsg}"></c:out></strong></i>
		</p>
		<br> Title :<input style="margin-left: 6.8%" type="text"
			id="Title" name=Title><br> Author :<input
			style="margin-left: 5.6%" type="text" id="Author" name=Author><br>
		Publisher :<input style="margin-left: 4.4%" type="text" id="Publisher"
			name=Publisher><br> pub_year :<input
			style="margin-left: 4.4%" type="text" id="pub_year" name=pub_year><br>
		Genre :<input style="margin-left: 6%" type="text" id="Genre"
			name=Genre><br> Copies :<input style="margin-left: 5.6%"
			type="text" id="Copies" name=Copies><br> Price :<input
			style="margin-left: 6.5%" type="text" id="Price" name=Price><br>

		<button id=Add type="submit" onclick='AddBook()'
			style="position: absolute; right: 75%; bottom: 50%;">Add
			Book</button>
	</form>
	<form action="AdminAction">
		<button id=cancel type="submit" onclick='Cancel()'
			style="position: absolute; right: 70%; bottom: 50%;">Cancel</button>
	</form>
</body>
</html>