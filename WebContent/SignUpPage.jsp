<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: pink">
	<form action="SignUpAction">
	
	
		<br />
		<p>
			<br> <Strong>Enter the below details:</Strong>
		</p>
	<br />
		<p><i><strong><c:out value="${FailureMsg}"></c:out></strong> </i></p>
		<br> Username :<input style="margin-left: 4.5%" type="text"
			id="username" name=username><br> Password :<input
			style="margin-left: 4.8%" type="password" id="password" name=password><br>
		FirstName :<input style="margin-left: 4.2%" type="text" id="Firstname"
			name=Firstname><br> LastName :<input
			style="margin-left: 4.4%" type="text" id="Lastname" name=Lastname><br>

		<button id="CreateUser" type="submit"
			style="position: absolute; right: 75%; bottom: 55%;">Create
			User</button>
	</form>
	<form action = "WelcomePageAction">
		<button id=cancel type="submit" name = "cancel"
			style="position: absolute; right: 70%; bottom: 55%;">Cancel</button>
	</form>


</body>
</html>