<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details of Purchases</title>
<script type="text/javascript">
function StartAndEndDateSearch() {
	document.getElementById("ILForm").action = "InfoListingAction";
	document.getElementById("ILForm").submit();
}

function TotalSalesSearch() {
	document.getElementById("ILForm").action = "TotalSalesAction";
	document.getElementById("ILForm").submit();
}




</script>
</head>
<body style="background-color: pink">
	<form id="ILForm" action="InfoListingAction">
		<p align="center">
			<strong> Details of Purchases</strong>
		</p>
		<p>
			<br> <i>Enter the start and end dates in yyyy-mm-dd format to view the complete list of purchases during that period :</i>
		</p>
		<br />	
		<p><i><strong><c:out value="${FailureErrorMsg}"></c:out></strong> </i></p>
	    Start Date<input type=text id=StartDate name="startdate" />
		EndDate<input type=text id=EndDate name="enddate" /> <br> <input
			style="margin-left: 420px" type="button" id=search value=Search
			name=Search onclick='StartAndEndDateSearch()' /><br>
<c:if test="${fn:length(infoList) gt 0}">
		<table border=1>
			<tr>
			<td>Order ID</td>
			<td>User ID</td>
			<td>Book ID</td>
			<td>Title</td>
			<td>Ordered Copies</td>
			<td>Order Price</td>
			<td>Order Status</td>
			</tr>
			<c:forEach var="item" items="${infoList}">
					<tr>
						<td><c:out value="${item.orderId}" /></td>
						<td><c:out value="${item.userId}" /></td>
						<td><c:out value="${item.bookId}" /></td>
						<td><c:out value="${item.title}" /></td>
						<td><c:out value="${item.bookQty}" /></td>
						<td><c:out value="${item.bookPrice}" /></td>
						<td><c:out value="${item.orderDesc}" /></td>
						
						

					</tr>
				</c:forEach>
		</table>
		</c:if>
		<br /> <br /> <br /> <br /> <br /> <br />
		<p>
			<br /> <i>Enter the month and year to get the total sales amount during that time :</i>
		</p>
		<br />
		<p><i><strong><c:out value="${FailureErrorMsg2}"></c:out></strong> </i></p>
		 Month<input type=text id=Month name = "month" /> 
		Year<input type=text id=Year name="year" /> 
		<br /> 
		<input style="margin-left: 380px" type="button" id=search value=Search name=Search onclick='TotalSalesSearch()'><br>
			
			<p>
			Total Order Price is  : $${TotalSalesPrice}
			</p>
				</form>
				<form id = "previouspage" action = "AdminAction">
			<br />
			<br />
			<p align = "center">
			<input type="submit" id= "PrevPage" name ="previous page" value = "Previous Page" 
			onclick = 'PrevPage()'/> 
			</p>
			</form>

</body>
</html>