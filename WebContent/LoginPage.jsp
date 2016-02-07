<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CS671BOOKS Login Screen</title>
</head>
<body style="background-color: pink">

	<form id="loginForm" name="loginForm" action = "LoginAction" >
		<h1 align="center">Login Screen</h1>
		<br />
		<p align = "center"><i><strong><c:out value="${loginFailureMsg}"></c:out></strong> </i></p>
		<p align="center">
			 Username <input type="text" name=Username /> <br />
		<p align="center">
			Password <input type="password" name=Password /> <br />
			<br /> 
			<input type="button" value=Login name=Login onclick = 'loginUser()'/>
			<input	type="button" value=Cancel name=Cancel onclick = 'LoginCancel()'/>	
		  </p>
		  </form> 
	
	<br /><br /><br /><br />
	<form action = "ShowSignUpAction">
	<p align = "center">
	<strong><i>Please Sign Up if you are a new user</i></strong>
	<input type = "submit" value = "Sign Up" name = "signup" id = "signup"	/>
	</p>
	</form>

<script type="text/javascript">
 
 function loginUser(){
	 document.getElementById("loginForm").action = "LoginAction" ;
	 document.getElementById("loginForm").submit();
	 
 }

 function LoginCancel()
 {
	 document.getElementById("loginForm").action = "WelcomePageAction" ;
	 document.getElementById("loginForm").submit();
 }
</script>
</body>
</html>