<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#ffe4e1">
	<div align="center">
		<table>
			<h2>Enter your Customer details:</h2>
			<form:form action="planDetailsController" method="post">
				<tr>
					<td><font size="5">Customer ID</font></td>
					<td><input type="number" name="customerID" /></td>
				</tr>
				<tr>
					<td><font size="5">Mobile Number</font></td>
					<td><input type="number" name="mobileNo" /></td>
				</tr>
				<tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
					<td colspan="2" align="center"><input type="submit" name="Submit" /></td>
				</tr>
			</form:form>
		</table>
		</div>
</body>
</html>