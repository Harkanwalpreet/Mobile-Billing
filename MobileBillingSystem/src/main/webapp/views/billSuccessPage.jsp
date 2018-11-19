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

		<h2>Bill for the month of ${bill.billMonth}</h2>
		<table border="1px solid black" cellpadding="10">
			<tr>
				<td></td>
				<td>No. of Units Consumed</td>
				<td>Rate per Unit</td>
			</tr>
			<tr>
				<td>Bill ID:</td>
				<td align="center" colspan="2">${bill.billID}</td>
			</tr>
			<tr>
				<td>No Of Local SMS:</td>
				<td>${bill.noOfLocalSMS}</td>
				<td>${plan.localSMSRate}</td>
			</tr>
			<tr>
				<td>No Of Std SMS:</td>
				<td>${bill.noOfStdSMS}</td>
				<td>${plan.stdSMSRate}</td>
			</tr>
			<tr>
				<td>No Of Local Calls:</td>
				<td>${bill.noOfLocalCalls}</td>
				<td>${plan.localCallRate}</td>
			</tr>
			<tr>
				<td>No Of Std Calls:</td>
				<td>${bill.noOfStdCalls}</td>
				<td>${plan.stdCallRate}</td>
			</tr>
			<tr>
				<td>Internet Data Usage Units:</td>
				<td>${bill.internetDataUsageUnits}</td>
				<td>${plan.internetDataUsageRate}</td>
			</tr>
			<tr>
				<td>Monthly Rental:</td>
				<td align="center" colspan="2">${plan.monthlyRental}</td>
			</tr>
			<tr>
				<td>Service Tax:</td>
				<td align="center" colspan="2">${bill.servicesTax}</td>
			</tr>
			<tr>
				<td>VAT:</td>
				<td align="center" colspan="2">${bill.vat}</td>
			</tr>
			<tr>
				<td>Total Amount:</td>
				<td align="center" colspan="2">${bill.totalBillAmount}</td>
			</tr>
		</table>
		<br>
		<br> <a href="index"><button>Return to Home Page</button></a>
	</div>
</body>
</html>