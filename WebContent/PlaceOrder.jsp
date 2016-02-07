<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
</head>
<body style="background-color: pink">
	<form action="PlaceOrderAction">
		
		<% request.getSession().setAttribute("selectedList", request.getAttribute("purchasedlist") ); %>
		<h1 align="center">Order Details</h1>
		 <table border=1>
                               <tr>
                                       
                                       
                                       <td>Book ID</td>
                                       <td>Title</td>
                                       <td>Price per Book</td>
                                       <td>Available Copies</td>
                                       <td>Copies</td>
                                                                             
                               </tr>
                               <c:forEach var="item" items="${purchasedlist}">
					<tr>
						<td><c:out value="${item.bookID}" /></td>
						<td><c:out value="${item.title}" /></td>
						<td><c:out value="${item.price}" /></td>
						<td><c:out value="${item.copies}" /></td>
						<td><input type = "text" name ="${item.bookID}"  /></td>
					</tr>
				</c:forEach>
</table>

<br>
<br>
<br>
		<p>
			Card Number<input style="margin-left:6.8%" type="text" name="Cardno" /><br/>
			Address<input style="margin-left:10%" type="text" name="Address" /><br/>
			City<input style="margin-left:12.3%" type="text" name="City" /><br/>
			State<input style="margin-left:12%" type="text" name="State" /><br/>
			
		</p>
			<br/><input style="position:absolute; left:30%;" type="submit"value="Place Order" name="PlaceOrder"/>
			</form>
			<form action= "SearchAction">
			<input style="position:absolute; right:50%;" type="submit"value=Cancel name= PlaceOrderCancel/>
	</form>



</body>
</html>