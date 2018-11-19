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
				<th>Plan ID</th>
				<th>Monthly Rental</th>
				<th>Free Local Calls</th>
				<th>Free STD Calls</th>
				<th>Free Local SMS</th>
				<th>Free STD SMS</th>
				<th>Free Internet Usage Units</th>
				<th>Local Call Rate</th>
				<th>STD Call Rate</th>
				<th>Local SMS Rate</th>
				<th>STD SMS Rate</th>
				<th>Internet Data Usage Rate</th>
				<th>Plan Circle</th>
				<th>Plan Name</th>
			</tr>
			<tr>
				<td>${plan.planID}</td>
				<td>${plan.monthlyRental}</td>
				<td>${plan.freeLocalCalls}</td>
				<td>${plan.freeStdCalls}</td>
				<td>${plan.freeLocalSMS}</td>
				<td>${plan.freeStdSMS}</td>
				<td>${plan.freeInternetDataUsageUnits}</td>
				<td>${plan.localCallRate}</td>
				<td>${plan.stdCallRate}</td>
				<td>${plan.localSMSRate}</td>
				<td>${plan.stdSMSRate}</td>
				<td>${plan.internetDataUsageRate}</td>
				<td>${plan.planCircle}</td>
				<td>${plan.planName}</td>
			</tr>
		</table>
		<br>
		<br> <a href="index"><button>Return to Home Page</button></a>
	</div>
</body>
</html>