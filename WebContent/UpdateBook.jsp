<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Book</title>
<script type="text/javascript">


	function updateBook() {

		var bookId = document.getElementById("BookId");

		var copies = document.getElementById("Copies");
		var errorMessage = "";

		if (bookId == null || bookId.value == null || bookId.value == "") {
			errorMessage = errorMessage + "Enter Valid Book ID \n";
		}
		if (copies == null || copies.value == null || copies.value == "") {
			errorMessage = errorMessage + "Enter Valid number Of copies \n";

		}
		if (errorMessage != "") {
			alert(errorMessage);
		} else {
			document.getElementById("updateActionForm").submit();
		}
	}
</script>

</head>
<body style="background-color: pink">
	<form id="updateActionForm" action="UpdateBookAction"><br /><br />
		<p align='center'><i><strong>Enter the below BookId and Copies to Update a
			book:</strong></i></p>
			<p align = center>
		<br /> BookId :<input type="text" id="BookId" name=BookId /> <br /><br />
		Copies :<input type="text" id="Copies" name=Copies /> <br /> </p>
		<input
			type="button" style = "position:absolute; right : 40% ;bottom: 65%"name=UpdateBook id=Update value=UpdateBook
			onclick="updateBook()">
	</form>
<form action="AdminAction">
		<button id=cancel type="submit" onclick='Cancel()'
			style="position: absolute; right: 35%; bottom: 65%;">Cancel</button>
	</form>
</body>
</html>