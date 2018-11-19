<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usage Details</title>
</head>
<body bgcolor="#ffe4e1">
	<div align="center">
		<h2>Enter Usage Details:</h2>
		<table align="center">
			<form:form action="enterBillDetails" method="post"
				modelAttribute="bill">
				<tr>
					<td><font size="5">Customer ID:</font></td>
					<td><input type="number" name="customerID" /></td>
				</tr>
				<tr>
					<td><font size="5">Mobile Number:</font></td>
					<td><input type="number" name="mobileNo" /></td>
				</tr>
				<tr>
					<td><font size="5">Bill Month:</font></td>
					<td><form:select path="billMonth" name="billMonth">
							<option value="January">January</option>
							<option value="February">February</option>
							<option value="March">March</option>
							<option value="April">April</option>
							<option value="May">May</option>
							<option value="June">June</option>
							<option value="July">July</option>
							<option value="August">August</option>
							<option value="September">September</option>
							<option value="October">October</option>
							<option value="November">November</option>
							<option value="December">December</option>
					</form:select></td>
				<tr>
					<td><font size="5">No Of Local SMS:</font></td>
					<td><form:input path="noOfLocalSMS" size="30" /></td>
					<td><form:errors path="noOfLocalSMS" cssClass="error" /></td>
				</tr>
				<tr>
					<td><font size="5">No Of Std SMS:</font></td>
					<td><form:input path="noOfStdSMS" size="30" /></td>
					<td><form:errors path="noOfStdSMS" cssClass="error" /></td>
				</tr>
				<tr>
					<td><font size="5">No Of Local Calls:</font></td>
					<td><form:input path="noOfLocalCalls" size="30" /></td>
					<td><form:errors path="noOfLocalCalls" cssClass="error" /></td>
				</tr>
				<tr>
					<td><font size="5">No Of Std Calls:</font></td>
					<td><form:input path="noOfStdCalls" size="30" /></td>
					<td><form:errors path="noOfStdCalls" cssClass="error" /></td>
				</tr>
				<tr>
					<td><font size="5">Internet Data Usage Units:</font></td>
					<td><form:input path="internetDataUsageUnits" size="30" /></td>
					<td><form:errors path="internetDataUsageUnits"
							cssClass="error" /></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit"></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>