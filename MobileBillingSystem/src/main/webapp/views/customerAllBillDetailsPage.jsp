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
			<form>
				<tr>
					<td>Customer ID</td>
					<td><input type="text" name="customerID" /></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNo" /></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit" formaction="customerAllBillDetailsController" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Generate Bill PDF" formaction="allBillsPdfReport" /></td>
				</tr>
			</form>
		</table>
		</div>
</body>
</html>