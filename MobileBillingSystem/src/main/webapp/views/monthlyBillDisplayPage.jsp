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
			<font size="8">Monthly Bill</font>
		</h5>
		<table border="1" align="center">
			<tr>
				<th>Bill ID</th>
				<th>Bill Month</th>
				<th>Total Bill Amount</th>
				<th>Service Tax</th>
				<th>VAT</th>
			</tr>
				<tr>
					<td>${bill.billID}</td>
					<td>${bill.billMonth}</td>
					<td>${bill.totalBillAmount}</td>
					<td>${bill.servicesTax}</td>
					<td>${bill.vat}</td>
				</tr>
		</table>
		<br> <br> <a href="index"><button>Return to
				Home Page</button></a>
	</div>
</body>
</html>