<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#ffe4e1">
	<div align="center">
		<h5 align="center">
			<font size="8">Details of the Customer</font>
		</h5>
		<table border="1" align="center">
			<tr>
				<th>Customer ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email ID</th>
			</tr>
			<tr>
				<td>${customer.customerID}</td>
				<td>${customer.firstName}</td>
				<td>${customer.lastName}</td>
				<td>${customer.emailID}</td>
			</tr>
		</table>
		<br>
		<br> <a href="index"><button>Return to Home Page</button></a>
	</div>
</body>
</html>