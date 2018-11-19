<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body bgcolor="#ffe4e1">
	<div align="center">
		<h1>Enroll customer details here</h1>
		<table>
			<form:form action="registerCustomer" method="post"
				modelAttribute="customer">

				<tr>
					<td><font size="5">First Name:</font></td>
					<td><form:input path="firstName" size="30" /></td>
					<td><form:errors path="firstName" cssClass="error" /></td>
				</tr>

				<tr>
					<td><font size="5">Last Name:</font></td>
					<td><form:input path="lastName" size="30" /></td>
					<td><form:errors path="lastName" cssClass="error" /></td>
				</tr>

				<tr>
					<td><font size="5">Email ID:</font></td>
					<td><form:input path="emailID" size="30" /></td>
					<td><form:errors path="emailID" cssClass="error" /></td>
				</tr>

				<tr>
					<td><font size="5">Date Of Birth:</font></td>
					<td><form:input path="dateOfBirth" size="30" /></td>
					<td><form:errors path="dateOfBirth" cssClass="error" /></td>
				</tr>

				<tr>
					<td><font size="5">Pin code:</font></td>
					<td><form:input path="billingAddress.pinCode" size="30" /></td>
					<td><form:errors path="billingAddress.pinCode"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td><font size="5">City:</font></td>
					<td><form:input path="billingAddress.city" size="30" /></td>
					<td><form:errors path="billingAddress.city" cssClass="error" /></td>
				</tr>

				<tr>
					<td><font size="5">State:</font></td>
					<td><form:input path="billingAddress.state" size="30" /></td>
					<td><form:errors path="billingAddress.state" cssClass="error" /></td>
				</tr>
				<br>
				<br>
				<tr>
					<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
					<td colspan="2" align="center"><input type="submit" value="Submit"></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>