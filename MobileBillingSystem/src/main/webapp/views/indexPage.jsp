<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
</style>
</head>
<body bgcolor="#ffe4e1">
	<div align="center">
		<h1>
			<font size="7" color='#000000	'>Welcome to Mobile Billing Application!<br><br></font>
		</h1>
		<h1>
			<font size="6" color='#000000'>Choose an action</font>
		</h1>
		<br><br>
		<a href="registration"><button class="button"><font size="4">Register</font></button></a>
		<a href="displayPlanDetails"><button class="button"><font size="4">Open/Edit Postpaid Account</font></button></a>
		<a href="getMonthlyBill"><button class="button"><font size="4">Generate My Bill</font></button></a><br>
		<a href="customerDetails"><button class="button"><font size="4">Customer Details</font></button></a>
		<a href="postpaidAccountDetails"><button class="button"><font size="4">My Postpaid Account Details</font></button></a>
		<a href="planDetails"><button class="button"><font size="4">My Plan Details</font></button></a><br>
		<a href="customerAllBillDetails"><button class="button"><font size="4">My All Bills Details</font></button></a>
		<a href="monthlyBill"><button class="button"><font size="4">Monthly Bill</font></button></a>
		<a href="allCustomerDetailsDisplayController"><button class="button"><font size="4">All Customer Details</font></button></a>
		<a href="closeAccount"><button class="button"><font size="4">Close Account</font></button></a>
		<a href="deleteCustomer"><button class="button"><font size="4">Delete Customer</font></button></a>
	</div>
</body>
</html>