<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<th>Mobile Number</th>
				<th>Plan</th>
			</tr>
			<c:forEach items="${postpaidAccounts}" var="map">
				<tr>
					<td>${map.value.customer.customerID}</td>
					<td>${map.key}</td>
					<td>${map.value.plan.planName}</td>
				</tr>
			</c:forEach>
		</table>
		<br> <br> <a href="index"><button>Return to
				Home Page</button></a>
	</div>
</body>
</html>